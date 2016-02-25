package base;

abstract class AbstractDemo{
	
	private String name;
	private int id;
	private double money;
	
	public AbstractDemo(String name, int id, double money) {
		super();
		this.name = name;
		this.id = id;
		this.money = money;
	}
	
	public abstract void work();
}

