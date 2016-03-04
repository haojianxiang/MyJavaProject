package haojianxiang.base;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class JavaBaseIO {
	
	
	public static void main(String[] args){
		Long begin = System.currentTimeMillis();
		Integer w = 10000;
		Integer k = 1000;
		Integer b = 100;
		Integer s = 10;
		String resultIO = "";
		
		for (int i = 1; i <= 99999; i++) {
			StringBuffer str = new StringBuffer();
			str.append("早上好!  ----");
			StringBuffer times = new StringBuffer();
			if (i/w>0) {
				times.append(i/w+"万");
			}
			if (i/k>0) {
				String s1 = i/k+"";
				String s2 = s1;
				if (s2.length()>1) {
					s2 = s1.substring(s1.length()-1,s1.length());
				}
				if (!"0".equals(s2)) {
					times.append(s2+"千");
				}else {
					times.append("零");
				}
			}
			if (i/b>0) {
				String s1 = i/b+"";
				String s2 = s1;
				if (s2.length()>1) {
					s2 = s1.substring(s1.length()-1,s1.length());
				}				if (!"0".equals(s2)) {
					times.append(s2+"百");
				}else {
					times.append("零");
				}
			}
			if (i/s>0) {
				String s1 = i/s+"";
				String s2 = s1;
				if (s2.length()>1) {
					s2 = s1.substring(s1.length()-1,s1.length());
				}				if (!"0".equals(s2)) {
					times.append(s2+"十");
				}else {
					times.append("零");
				}
			}
			String s1 = i+"";
			String s2 = s1;
			if (s2.length()>1) {
				s2 = s1.substring(s1.length()-1,s1.length());
			}			if (!"0".equals(s2)) {
				times.append(s2);
			}

			String result = str.toString()+"第"+times.toString()+"遍"+"\n";
			result = result.replaceAll("1", "一");
			result = result.replaceAll("2", "二");
			result = result.replaceAll("3", "三");
			result = result.replaceAll("4", "四");
			result = result.replaceAll("5", "五");
			result = result.replaceAll("6", "六");
			result = result.replaceAll("7", "七");
			result = result.replaceAll("8", "八");
			result = result.replaceAll("9", "九");
			result = result.replaceAll("零+", "零");
			result = result.replaceAll("零遍", "遍");
			System.out.println(result);
			writerTxt(result);
		}
		System.out.println("耗时"+(System.currentTimeMillis()-begin)+"ms");
	}
	
	private static void writerTxt(String string) {
		BufferedWriter fw = null;

		try {
			File file = new File("D://text.txt");
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
			fw.append(string);
			fw.newLine();
			fw.flush(); // 全部写入缓存中的内容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
