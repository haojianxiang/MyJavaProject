package haojianxiang.innerClass;

public class FightTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fighter fighter = new Fighter("三胖");
		String weapon = fighter.getMyweapon(1);//实际上是fighter继承了武器类
		String armor = fighter.getMyArmor(3);//实际上是fighter继承了护甲类
		System.out.println("Fighter("+fighter.fightName+") have a weapon \""+weapon+"\" and a armor \""+armor+"\".");
	}

}
