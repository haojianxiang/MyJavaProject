package haojianxiang.innerclass.boss;

import haojianxiang.innerclass.Fighter;

public abstract class AbstractBoss {
	
	String bossName;
	int blood;
	int magic;
	int defense;
	int attack;
	int speed;
	
	
	public abstract void attack(Fighter fighter);
	
	public abstract int defense(Fighter fighter);
	
	public abstract void kill(Fighter fighter);
	
	
}
