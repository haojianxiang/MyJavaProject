package haojianxiang.innerclass;

public class Armor {
	int armorNum;
	String armorName;
	int armorVal;
	int armorSpeed;
	public Armor(){
	}
	public Armor(int num){
		this.armorNum = num;
	}
	
	public Armor getArmor(){
		Armor armor = new Armor();
		switch (armorNum) {
		case 1:
			this.armorName = "盾牌";
			this.armorVal = 10;
			this.armorSpeed = -5;
			break;
		case 2:
			this.armorName = "板甲";
			this.armorVal = 15;
			this.armorSpeed = -10;
			break;
		case 3:
			this.armorName = "锁甲";
			this.armorVal = 5;
			this.armorSpeed = -5;
			break;
		default:
			this.armorName = "破罗衫";
			this.armorVal = 1;
			this.armorSpeed = -1;
			break;
		}
		armor.armorName = armorName;
		armor.armorVal = armorVal;
		armor.armorSpeed = armorSpeed;
		System.out.println("You get a armor :"+armorName);
		return armor;
	}
}

