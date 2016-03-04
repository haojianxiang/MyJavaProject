package haojianxiang.produce$consumer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 活动变为展示中状态及相关处理 -----刷全店铺活动产品redis
 */
public final class ProduceConsumerFactory<T, E> {

	public static final String KEY_THREAD_SIZE = "thread_size";
	public static final String KEY_QUEUE_SIZE = "queue_size";

	private static final int DEFAULT_THREAD_SIZE = 10;
	private static final int QUEUE_SIZE = 50;

	/**
	 * 数据处理接口
	 */
	private IData<T, E> idata;

	public ProduceConsumerFactory(IData<T, E> idata) {
		this.idata = idata;
	}

	public Result<E> start(Map<String, String> jobParam) {
		// 开始日志
		this.sendRunInfo("factory start..");
		// 初始化参数
		// 启动线程数量
		int threadSize = DEFAULT_THREAD_SIZE;
		// 队列数量
		int queueSize = QUEUE_SIZE;
		if (jobParam != null) {
			String strThreadSize = jobParam.get(KEY_THREAD_SIZE);
			if (null != strThreadSize) {
				threadSize = Integer.parseInt(strThreadSize);
			}
			String strQueueSize = jobParam.get(KEY_QUEUE_SIZE);
			if (strQueueSize != null) {
				queueSize = Integer.parseInt(strQueueSize);
			}
		}
		BlockingQueue<T> queue = new LinkedBlockingQueue<T>(queueSize);

		// 各种锁
		CountDownLatch startlatch = new CountDownLatch(1);
		CountDownLatch endlatch = new CountDownLatch(threadSize);
		// 初始化池
		ExecutorService pool = Executors.newFixedThreadPool(threadSize + 1);

		// 整理结果
		Result<E> allresult = new Result<E>();
		try {
			// 初始化消费者
			List<Consumer> list = new ArrayList<Consumer>();
			for (int i = 0; i < threadSize; i++) {
				Consumer consumer = new Consumer(queue, startlatch, endlatch);
				list.add(consumer);
			}

			// 创建生产者
			Producter producter = new Producter(queue, startlatch);
			pool.submit(producter);

			// 这里结果
			int count = 0;
			List<E> resultlist = new ArrayList<E>();
			List<Future<Result<E>>> resultList = pool.invokeAll(list);
			for (int i = 0; i < resultList.size(); i++) {
				Future<Result<E>> future = resultList.get(i);
				Result<E> re = future.get();
				count = count + re.getCount();
				resultlist.addAll(re.getData());
			}
			allresult.setCount(count);
			allresult.setData(resultlist);

			// 都处理完了，在执行后面的池释放
			startlatch.await();
			endlatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			// 都处理完，毁灭线程池
			this.sendRunInfo("factory-pool.shutdown..");
			pool.shutdown();
		}
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
					this.sendRunInfo("FlashRediesAllStorePromoProdJob-pool-did-not-terminate");
				}
			}
		} catch (Exception ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			this.sendRunInfo("FlashRediesAllStorePromoProdJob InterruptedException.."+ie.getMessage());
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}

		// 结束日志
		this.sendRunInfo("factory end..");
		return allresult;
	}

	/**
	 * 日志
	 */
	private void sendRunLog(String string) {
		idata.sendRunLog("[DEBUG]"+string);
	}
	
	private void sendRunInfo(String string) {
		idata.sendRunInfo("[INFO]"+string);
	}

	/**
	 * 生产数据
	 */
	private List<T> getData() {
		return idata.getData();
	}

	/**
	 * 消费数据
	 */
	private E handleData(T t) {
		return idata.handleData(t);
	}

	/**
	 * 生产工厂
	 */
	class Producter extends Thread {
		BlockingQueue<T> queue;
		CountDownLatch startlatch;
		boolean isfirstquery = true;

		public Producter(BlockingQueue<T> queue, CountDownLatch startlatch) {
			super();
			this.queue = queue;
			this.startlatch = startlatch;
		}
		
		@Override
		public void run() {
			int count = 0;

			while (true) {
				// 获取开始时间已经到了，但状态还是未开始的活动列表
				List<T> dataList = getData();
				if (isfirstquery) {
					isfirstquery = false;
					// // 激活消费者
					startlatch.countDown();
				}

				if (dataList != null && dataList.size() > 0) {
					sendRunLog("factory-will-product-count:" + dataList.size());
					for (int i = 0; i < dataList.size(); i++) {
						T t = dataList.get(i);
						try {
							queue.put(t);
						} catch (InterruptedException e) {
							sendRunLog("factory-put-InterruptedException->" + e);
						}
					}

				} else {
					sendRunLog("factory-no-data");
					break;
				}
			}
			sendRunLog("factory-count->" + count);
		}

	}

	/**
	 * 消费工厂
	 */
	class Consumer implements Callable<Result<E>> {
		BlockingQueue<T> queue;
		CountDownLatch startlatch;
		CountDownLatch endlatch;
		int faluercount = 3;

		public Consumer(BlockingQueue<T> queue, CountDownLatch startlatch, CountDownLatch endlatch) {
			super();
			this.queue = queue;
			this.startlatch = startlatch;
			this.endlatch = endlatch;
		}

		@Override
		public Result<E> call() throws Exception {
			Result<E> result = new Result<E>();
			List<E> list = new ArrayList<E>();
			try {
				// 等待生产者激活
				startlatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int count = 0;
			while (faluercount > 0) {
				try {
					T t = queue.poll(3, TimeUnit.SECONDS);
					if (t == null) {
						// 处理失败次数退出循环
						faluercount--;
						Thread.sleep(1000);
						sendRunLog("factory-Consumer-" + Thread.currentThread().getName() + "-" + faluercount);
						continue;
					}
					sendRunLog("factory-Consumer-" + Thread.currentThread().getName() + "-" + t);
					E e = handleData(t);
					if (e != null) {
						list.add(e);
					}
					count++;
					// 重置标志位
					faluercount = 3;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			sendRunLog("factory-Consumer-" + Thread.currentThread().getName() + ",count-" + count);
			result.setCount(count);
			result.setData(list);
			// 退出当前线程
			endlatch.countDown();
			return result;
		}
	}
}

/**
 * 数据处理接口
 * 
 * @param <T>
 * @param <E>
 */
interface IData<T, E> {
	/**
	 * 生产数据
	 */
	public abstract List<T> getData();

	/**
	 * 消费数据
	 */
	public abstract E handleData(T t);
	
	/**
	 * 日志相关信息
	 */
	public void sendRunLog(String string);
	/**
	 * 日志相关信息
	 */
	public void sendRunInfo(String string);
}

class Result<T> implements Serializable {
	private static final long serialVersionUID = -6981665986839950327L;
	private int count = 0;
	private List<T> data = null;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [count=" + count + ", data=" + data + "]";
	}
}
