package haojianxiang.strategy;


public class Context {
	
	Strategy strategy;
	int type;
	
	public Context(int type){
		this.type = type;
	}
	
	public void contextInterface(){
		switch (type) {
		case 1:
			strategy = new StrategyA();
			break;
		case 2:
			strategy = new StrategyB();
			break;
		case 3:
			strategy = new StrategyC();
			break;
		default:
			break;
		}
		strategy.algorithmInterface("");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
