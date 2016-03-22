package haojianxiang.innerclass;

public class FightTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fighter fighter = new Fighter("张文博");
		Weapon weapon = fighter.getMyweapon(10);//实际上是fighter继承了武器类
		Armor armor = fighter.getMyArmor(30);//实际上是fighter继承了护甲类
		System.out.println("Fighter("+fighter.fightName+") have a weapon \""+weapon+"\" and a armor \""+armor+"\".");
	}

}
