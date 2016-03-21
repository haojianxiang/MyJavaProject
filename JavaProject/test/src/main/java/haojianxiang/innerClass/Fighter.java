package haojianxiang.innerClass;

/**
 * 使用内部类实现多继承
 * @author haojianxiang
 *
 */
public class Fighter {
	String fightName;
	String weapon;
	String armor;
	public Fighter(){
		this.fightName = "无名氏";
	}
	public Fighter(String name){
		this.fightName = name;
	}
	
	public String getMyweapon(int num){
		MyWeapon myWeapon = new MyWeapon(num);
		this.weapon = myWeapon.getWeapon();
		return weapon;
	}
	
	public String getMyArmor(int num){
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
