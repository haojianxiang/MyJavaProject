package haojianxiang.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadDemo2 extends Thread {

	public static void main(String[] args) {
		println("start->" + new Date());
		final int threadNum = 16;
		//数据源
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			lst.add("帅不帅"+i);
		}
		
		CountDownLatch latch=new CountDownLatch(16);//同步辅助类
		for (int tabindex = 0; tabindex < threadNum; tabindex++) {
			List<String> sublst = new ArrayList<String>();
			int sub = lst.size()/threadNum;//一片个数
			if (tabindex!=threadNum-1) {
				for (int i = 0; i < sub; i++) {//前n片
					sublst.add(lst.get(tabindex*sub+i));
				}
			}else {//最后一片
				for (int j = sub*(threadNum-1); j < lst.size(); j++) {
					sublst.add(lst.get(j));
				}
			}
			
			new ThreadDemo2(tabindex,latch,sublst).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			println("init --end->" + new Date());
			println(latch.getCount());
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
			thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rundel(tabindex,thread);
		System.out.println(tabindex);
		latch.countDown();
	}
	
	public void rundel(int tabindex,Thread thread){
		println(thread.getName()+lst);
	}
	
	static void println(Object obj) {
		System.out.println(new Date() + "->" + obj);
	}
}