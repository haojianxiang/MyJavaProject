package haojianxiang.factory;

public class Shop {
	
	public static void main(String[] args){
		
		Factory factory = ChooseFactory.chooseFactory(2);//传递参数选择工厂
		factory.setI(1000);
		factory.produce("键盘");
	}
	
}
