package haojianxiang.innerClass;

public class Weapon {
	int weaponNum;
	public Weapon(int num){
		this.weaponNum = num;
	}
	
	public String getWeapon(){
		String weapon;
		switch (weaponNum) {
		case 1:
			weapon = "刀";
			break;
		case 2:
			weapon = "枪";
			break;
		case 3:
			weapon = "斧子";
			break;
		default:
			weapon = "木头棍子";
			break;
		}
		System.out.println("You get a weapon :"+weapon);
		return weapon;
	}
	
}

