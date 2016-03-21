package haojianxiang.factory;

public class ChooseFactory {
	public static Factory chooseFactory(int buyerLevel){
		Factory factory = null;
		switch (buyerLevel) {
		case 1:
			factory = new product1();
			break;
		case 2:
			factory = new product2();
			break;
		default:
			break;
		}
		return factory;
	}
}
