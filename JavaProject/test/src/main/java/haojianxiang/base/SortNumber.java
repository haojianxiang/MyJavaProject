package haojianxiang.base;

import haojianxiang.factory.RandomFactory;
import haojianxiang.util.ListUtil;

import java.util.ArrayList;
import java.util.List;




public class SortNumber {
	public static void main(String[] args) {
		
		boolean sequent = true;//sequent正序  reverse倒序
		Integer[] numbers = RandomFactory.exportRandom(100, 1, 100);
//		Integer[] numbers = {1,2,3,4,5,6,7,8,9};
		List<Integer> numbersSorted = BubbleSort(numbers,sequent);
		
		for (Integer num : numbersSorted) {
			System.out.println(num);
		}
	}
	
	public static List<Integer> BubbleSort(Integer[] numbers,boolean sequent){//冒泡排序
		long startTime = System.currentTimeMillis();
		List<Integer> numberLst = new ArrayList<Integer>();
		boolean canFinish = false;
		for (int j = 0; j < numbers.length; j++) {
			if (!canFinish) {
				List<Boolean> isChange = new ArrayList<Boolean>();
				for (int i = 0; i < numbers.length-1; i++) {
					if (sequent) {
						if (numbers[i]<=numbers[i+1]) {
							continue;
						}else {
							int a = numbers[i];
							int b = numbers[i+1];
							numbers[i] = b;
							numbers[i+1] = a;
							isChange.add(false);
						}
					}else {
						if (numbers[i]>=numbers[i+1]) {
							continue;
						}else {
							int a = numbers[i];
							int b = numbers[i+1];
							numbers[i] = b;
							numbers[i+1] = a;
							isChange.add(false);
						}
					}
					
				}
				if (!isChange.contains(false)) {
					canFinish = true;
				}
			}else {
				break;
			}
		}
		numberLst = ListUtil.arrayToList(numbers);
		System.out.println("The time of sort is "+(System.currentTimeMillis()-startTime)+" ms");
		return numberLst;
	}
	
	
	
	
}
