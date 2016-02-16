package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	public static void main(String[] args) {
		List<Integer> lst1 = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lst1.add(i);
		}
		System.out.println(listToArray(Integer.class,lst1));
		Integer[] array1 = listToArray(Integer.class,lst1);
		for (int i = 0; i < array1.length; i++) {
			System.out.println(array1[i]);
		}
		System.out.println("\n");
		Integer[] array2 = {110,2,3,4,5,6,447,8,9};
		List<Integer> lst2 = arrayToList(array2);
		for (int i = 0; i < lst2.size(); i++) {
			System.out.println(lst2.get(i));
		}
	}
	
	public static <T> T[] listToArray(Class<T> type,List<T> lst){
		T[] array = (T[]) Array.newInstance(type, lst.size());
		System.out.println();
		for (int i = 0; i < lst.size(); i++) {
			array[i] = lst.get(i);
		}
		return array;
	}
	
	public static <T> List<T> arrayToList(T[] array){
		List<T> list= new ArrayList<T>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
}
