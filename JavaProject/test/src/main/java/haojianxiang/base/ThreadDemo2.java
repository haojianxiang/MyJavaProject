package haojianxiang.base;

import haojianxiang.util.DataSplit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class ThreadDemo2 extends Thread {

	public static void main(String[] args) {
		println("start->" + new Date());
		final int threadNum = 14;
		//数据源
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < 5000; i++) {
			lst.add("帅不帅"+i);
		}
		
		CountDownLatch latch=new CountDownLatch(threadNum);//同步辅助类
		Map<Integer, List<String>> map = DataSplit.split(threadNum, lst);//分片
		for (int tabindex = 0; tabindex < threadNum; tabindex++) {
			List<String> sublst = null;
			sublst = map.get(tabindex);
			new ThreadDemo2(tabindex,latch,sublst).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			println("init --end->" + new Date());
//			println(latch.getCount());
			System.exit(0);
		}
//		
	}
	
	int tabindex;
	CountDownLatch latch;
	List<String> lst;
	
	public ThreadDemo2(int tabindex,CountDownLatch latch,List<String> lst) {
		super();
		this.tabindex = tabindex;
		this.latch = latch;
		this.lst = lst;
	}

	public void run() {
//		this.lst = new ArrayList<String>();
		Thread thread = Thread.currentThread();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		rundel(tabindex,thread);
		latch.countDown();
	}
	
	public void rundel(int tabindex,Thread thread){
		println(thread.getName()+" __list size is :"+lst.size());
	}
	
	static void println(Object obj) {
		System.out.println(new Date() + "->" + obj);
	}
}