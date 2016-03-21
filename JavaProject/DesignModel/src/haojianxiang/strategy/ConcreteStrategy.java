package haojianxiang.strategy;

public class ConcreteStrategy {
	public ConcreteStrategy(){
		
	}
}
class StrategyA extends Strategy{

	public void algorithmInterface() {
		System.out.println("this is not override function!");
	}

	@Override
	public void algorithmInterface(String arg) {
		System.out.println("this is strategy A");
	}
	
}
class StrategyB extends Strategy{

	@Override
	public void algorithmInterface(String arg) {
		System.out.println("this is strategy B");
	}
	
}
class StrategyC extends Strategy{

	@Override
	public void algorithmInterface(String arg) {
		System.out.println("this is strategy C");
	}
	
}