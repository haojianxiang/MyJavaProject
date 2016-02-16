package factory;

import java.util.Random;

public class RandomFactory {
	public static void main(String[] args) {
		exportRandom(10, 1, 100);
	}
	
	
	public static int[] exportRandom(Integer arrayLength, Integer minNum, Integer maxNum){
		if (minNum>maxNum) {
			return null;
		}
		long startTime = System.currentTimeMillis();
		int randomSize = 100;
		if (arrayLength!=null) {
			randomSize = arrayLength;
		}
		int[] randomArray = new int[randomSize];
		Random random = new Random();
		boolean isFinish = false;
		int i = 0;
		while (!isFinish) {
			int randomNum = 0;
			if (maxNum!=null&&(minNum==null||(minNum!=null&&minNum>0))) {
				randomNum = random.nextInt(maxNum);
			}else {
				randomNum = random.nextInt();
			}
			
			if (minNum!=null) {
				if (randomNum<minNum) {
					continue;
				}
			}
			if (maxNum!=null) {
				if (randomNum>maxNum) {
					continue;
				}
			}
			randomArray[i] = randomNum;
			System.out.println("第"+(i+1)+"个:"+randomNum);
			i++;
			if (i==randomArray.length) {
				isFinish = true;
			}
		}
		for (int j = 0; j < randomArray.length; j++) {
			System.out.print(randomArray[j]+",");
		}
		System.out.println("\n"+randomArray.length);
		System.out.println("The time of product is "+(System.currentTimeMillis()-startTime)+" ms");
		return randomArray;
	}
	
	
}
