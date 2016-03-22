package haojianxiang.innerclass;

public class Weapon {
	int weaponNum;
	int attackVal;
	int weaponSpeed;
	String weaponName;
	public Weapon(){
	}
	public Weapon(int num){
		this.weaponNum = num;
	}
	
	public Weapon getWeapon(){
		Weapon weapon = new Weapon();
		switch (weaponNum) {
		case 1:
			weaponName = "刀";
			attackVal = 100;
			weaponSpeed = 20;
			break;
		case 2:
			weaponName = "匕首";
			attackVal = 60;
			weaponSpeed = 40;
			break;
		case 3:
			weaponName = "斧子";
			attackVal = 150;
			weaponSpeed = 10;
			break;
		default:
			weaponName = "木头棍子";
			attackVal = 10;
			weaponSpeed = 20;
			break;
		}
		weapon.weaponName = weaponName;
		weapon.attackVal = attackVal;
		weapon.weaponSpeed = weaponSpeed;
		System.out.println("You get a weapon :"+weaponName);
		return weapon;
	}
	
}

