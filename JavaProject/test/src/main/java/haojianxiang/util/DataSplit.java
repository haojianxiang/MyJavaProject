package haojianxiang.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSplit {
	public static <T> Map<Integer,List<T>> split(Integer subNum, List<T> dataSource){
		Map<Integer,List<T>> rst = new HashMap<Integer, List<T>>(subNum);
		for (int i = 0; i < subNum; i++) {
			List<T> subLst = new ArrayList<T>();
			rst.put(i, subLst);
		}
		for (int i = 0; i < dataSource.size(); i++) {
			T t = dataSource.get(i);
			int hashcode = t.hashCode();
//			System.out.println(hashcode);
			int mark = hashcode%subNum;
			System.out.println(mark);
			List<T> subLst = rst.get(mark);
			subLst.add(t);
			rst.remove(mark);
			rst.put(mark, subLst);
		}
		return rst;
	}
	
	public static <T> Map<Integer,List<T>> splitOld(Integer subNum, List<T> dataSource){
		Map<Integer,List<T>> rst = new HashMap<Integer, List<T>>(subNum);
		int count=0;
		for (int j = 0; j < subNum; j++) {
			List<T> lt = new ArrayList<T>();
			int pagesize = dataSource.size()/subNum;
			if ((dataSource.size()%subNum)>(dataSource.size()/subNum)) {
				pagesize +=1;
			}
			if (j!=subNum-1) {//前几片
				for (int k = 0; k < pagesize; k++) {
					lt.add(dataSource.get(j*pagesize+k));
					count++;
				}
			}else {//最后一片
				for (int k =count;k<dataSource.size();k++ ) {
					lt.add(dataSource.get(k));
				}
			}
			rst.put(j, lt);
		}
		return rst;
	}
	
	
	public static void main(String[] args){
		int subNum = 1000;
		int wholeSize=299999;
		
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < wholeSize; i++) {
			lst.add("aaaaaaaaaaaaaaaa"+i);
		}
//		Map<Integer, List<String>> map= split(subNum, lst);
//		for (int i = 0; i < map.size(); i++) {
//			System.out.println(map.get(i).size());
//		}
		System.out.println("************************");
		int total = 0;
		Map<Integer, List<String>> map2= splitOld(subNum, lst);
		for (int i = 0; i < map2.size(); i++) {
			System.out.println(map2.get(i).size());
			total+=map2.get(i).size();
		}
		System.out.println(total);
		
	}
	
}
