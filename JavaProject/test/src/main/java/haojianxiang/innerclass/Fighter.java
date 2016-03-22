package haojianxiang.innerclass;

/**
 * 使用内部类实现多继承
 * @author haojianxiang
 *
 */
public class Fighter {
	int blood;
	int magic;
	String fightName;
	Weapon weapon;
	Armor armor;
	public Fighter(){
		this.fightName = "无名氏";
	}
	public Fighter(String name){
		this.fightName = name;
	}
	
	public Weapon getMyweapon(int num){
		MyWeapon myWeapon = new MyWeapon(num);
		this.weapon = myWeapon.getWeapon();
		return weapon;
	}
	
	public Armor getMyArmor(int num){
		MyArmor myArmor = new MyArmor(num);
		this.armor = myArmor.getArmor();
		return armor;
	}
	
	private class MyWeapon extends Weapon{

		public MyWeapon(int num) {
			super(num);
		}
		
	}
	
	private class MyArmor extends Armor{

		public MyArmor(int num) {
			super(num);
		}
		
	}
}
