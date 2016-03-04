package haojianxiang.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {
	
	public static void main(String[] args){
		int threadsize = 8;
		ExecutorService pool = Executors.newFixedThreadPool(threadsize);
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			lst.add("wow"+i);
		}
		for (int i = 0; i < threadsize; i++) {
			Thread1 thread1 = new Thread1(i,lst);
			pool.submit(thread1);
		}
		for (String str:lst) {
			System.out.print(str+",");
		}
		pool.shutdown();
	} 
}
	class Thread1 implements Runnable{
		int a ;
		List<String> lst;
		Thread1(int a ,List<String> lst){
			this.a = a;
			this.lst = lst;
		}
		@Override
		public void run() {
			synchronized(this) {  
				System.out.println(Thread.currentThread().getName()+"____"+a);
				for (int i = 0; i < 10; i++) {
					lst.remove(i);
				}
//				for (int i = 0; i < lst.size(); i++) {
//					System.out.print(lst.get(i)+",");
//				}
//				System.out.println("");
			}
			
		}
		
	}
