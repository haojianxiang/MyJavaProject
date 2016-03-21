package haojianxiang.strategy;

public class User {
	public static void main(String [] args){
//		Strategy strategya = new StrategyA();//强耦合
//		strategya.algorithmInterface("");
		
//		Context context = new Context(new StrategyA());//弱耦合，但用户需要了解两个类
		
		Context context = new Context(2);//弱耦合，用户只需要了解一个类
		context.contextInterface();
		
	}
}
