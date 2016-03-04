package haojianxiang.base;

public class SleepTest {
	public static void main(String[] args) {
		run();
	}
	
	public static void run(){
		System.out.println("a");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("b");
	}
}
