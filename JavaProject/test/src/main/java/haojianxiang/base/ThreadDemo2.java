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
		CountDownLatch latch=new CountDownLatch(16);//同步辅助类
		for (int tabindex = 0; tabindex < 16; tabindex++) {
			new ThreadDemo2(tabindex,latch).start();
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
	
	public ThreadDemo2(int tabindex,CountDownLatch latch) {
		super();
		this.tabindex = tabindex;
		this.latch = latch;
	}

	public void run() {
		this.lst = new ArrayList<String>();
		Thread thread = Thread.currentThread();
		try {
			thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			lst.add("帅不帅"+i);
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