package haojianxiang.strategy;

import java.io.IOException;

public abstract class Strategy {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
	}
	
	/**
	 * 算法方法
	 */
	public abstract void algorithmInterface(String arg);
	
	/**
	 * 非抽象方法test
	 */
	public void test() {
		//test
	}
}
