package base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadDemo {

	static final Integer threadsize = 10;
	static ExecutorService pool = Executors.newFixedThreadPool(threadsize);
	
	public static void main(String[] args) {
		List<Integer> lst = new ArrayList<Integer>();//数据源
		int i = 100;
		while(i>0){
			lst.add(i);
			i--;
		}
		
		CountDownLatch latch=new CountDownLatch(threadsize);//同步辅助类
		for (int j = 0; j < threadsize; j++) {//数据分片
			List<Integer> lt = new ArrayList<Integer>();
			int pagesize = lst.size()/threadsize;
			if (j!=threadsize-1) {//前几片
				for (int k = 0; k < pagesize; k++) {
					lt.add(lst.get(j*pagesize+k)); 
				}
			}else {//最后一片
				for (int k =(threadsize-1)*pagesize;k<lst.size();k++ ) {
					lt.add(lst.get(k));
				}
			}
			RunByThread run  = new RunByThread(1,lt,latch);//提交线程
			pool.submit(run);
		}
		pool.shutdown();
	}
	
}

	class RunByThread implements Runnable{
	Integer i;
	List<Integer> lst;
	CountDownLatch latch;
	RunByThread(Integer i,List<Integer> lst,CountDownLatch latch) {
		this.i = i;
		this.lst = lst;
		this.latch = latch;
	}
	
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+" --- "+lst);
	}
}