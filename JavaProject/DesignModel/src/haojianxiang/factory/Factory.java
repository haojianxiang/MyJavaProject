package haojianxiang.factory;

public abstract class Factory {
	private int i;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	void produce(String str){
		System.out.println(i);
		System.out.println(str);
	}
	
}

class product1 extends Factory{
	@Override
	void produce(String str) {
		super.produce(str);
	}
	
}

class product2 extends Factory{
	
	@Override
	void produce(String str) {
		System.out.println("star product:"+str);
	}
}