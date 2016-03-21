package haojianxiang.innerClass;

public class Armor {
	int armorNum;
	public Armor(int num){
		this.armorNum = num;
	}
	
	public String getArmor(){
		String armor;
		switch (armorNum) {
		case 1:
			armor = "盾牌";
			break;
		case 2:
			armor = "板甲";
			break;
		case 3:
			armor = "锁甲";
			break;
		default:
			armor = "布甲";
			break;
		}
		System.out.println("You get a armor :"+armor);
		return armor;
	}
}

