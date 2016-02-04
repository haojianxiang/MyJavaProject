package base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.DateUtil;
import util.MathUtil;

public class JavaBase {
	
	public static void main(String[] aa) throws ParseException{
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse("2015-09-18 10:39:00");
		Date date2 = sdf.parse("2015-09-18 10:40:00");
		Long time = DateUtil.getTimeSub(date1, date2);
//		System.out.println(time);
		
		SimpleDateFormat df=new SimpleDateFormat("MM yyyy dd HH:ss:mm");
		
		Date date8 = new Date();
		String str = df.format(date8);
//		System.out.println(str + " ##");
		
		
		String targetLang = "ru,pt,it";
		String[] targetLangs = targetLang.split(",");
		
		List<String> targetLangs2 = new ArrayList<String>();
		targetLangs2.add("ru");
		targetLangs2.add("pt");
		targetLangs2.add("it");
		
		boolean finish = true;
		for (String tar:targetLangs) {//取任务中所有语言，与结果中比对
			if (!targetLangs2.contains(tar)) {
//				System.out.println("这个订单没有完毕!  orderId:");
				finish = false;
				break;
			}
		}
		if (finish) {
//			System.out.println("这个订单已经完毕!  orderId:");
		}
		
		Long orderId = 123456789999L;
		Long ru = 10001L;
		
		String s = ""+orderId+ru;
		Long taskId = Long.parseLong(s);
//		System.out.println(taskId);
		
		String text = "asa$gft   $gFG%DGFT@ ad ada  asd  asdas   afasf   ";
		String[] textArray = text.split("\\s{1,}");
//		System.out.println(textArray.length);
		for (String sb :textArray) {
//			System.out.println(sb);
		}
		
		String taskIdStr = "12345678911111";
		taskIdStr = taskIdStr.substring(0, taskIdStr.length()-5);
		Long taskId1 = Long.valueOf(taskIdStr);
//		System.out.println(taskId1);
		
		
		Calendar now = Calendar.getInstance();  
        now.setTime(new Date());  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + 3);  
        Date eTime =  now.getTime();  
		String eTimesString = DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", eTime);
//		System.out.println(eTimesString);
		
		Date eTime1 =  DateUtil.getTimeAfterTheDate(new Date(), 3);
		String eTimesString1 = DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", eTime1);
//		System.out.println(eTimesString1);
		
//		String regex = "(?i)[^a-z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
		String regex     = "[a-zA-Z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
		String string = "壹壹壹*壹壹\"壹壹壹壹\\壹壹壹壹a壹壹";
		String[] textArray1 = string.split(regex);
		for (String sb :textArray1) {
//			System.out.println(sb);
		}
		
		int wordsCount = 10;
		double amount = 0.0;
		for (int i=0;i<4;i++) {
			amount += wordsCount*1.11001D;
		}
		amount = MathUtil.getFormatScale2(amount);
//		System.out.println(amount);
		
		String sunyuTaskDtoString = "<string xmlns=\"http://www.sunyu.com/\">{\"wordsCount\":46,\"amount\":49.7352,\"errorDescr\":null,\"success\":true}</string>";
		sunyuTaskDtoString = sunyuTaskDtoString.replaceAll("\\>\\{", "\\>\\&#\\{");
		sunyuTaskDtoString = sunyuTaskDtoString.replaceAll("\\}\\<", "\\}\\#&\\<");
		sunyuTaskDtoString = sunyuTaskDtoString.replaceAll("\\<.*\\&#", "");
		sunyuTaskDtoString = sunyuTaskDtoString.replaceAll("\\#&.*\\>", "");
		
//		System.out.println(sunyuTaskDtoString);
		
		String aaaa = "A";
		for (int i = 0; i < 3000; i++) {
			aaaa +="A";
		}
		aaaa += "B";
//		System.out.println(aaaa);
		
		String regex1 = "[\u4e00-\u9fa5]$";
		String str1 = "什是";
		if (str1.matches(regex1)) {
//			System.out.println("yes");
		}else {
//			System.out.println("no");
		}
		
		Object mid = 5656788L;
//		Integer mid1 = (Integer)mid;
		Integer mid2 = Integer.valueOf(mid.toString());
//		System.out.println(mid1);
//		System.out.println(mid2);
		
		String a = "a"+"a";
		String b = "aa";
//		System.out.println(a==b);
		
		int threadNum = 5;
		int loopCnt = 100;
		for (int z = 1; z <= threadNum; z++) {
    		int loopCntSub = loopCnt/threadNum;
    		int startNum = (z-1)*loopCntSub;
    		int endNum = z*loopCntSub;
    		
    		if (z==5) {
    			endNum = loopCnt;
			}
//    		System.out.println(startNum+" "+endNum);
    		for(int i=startNum;i<endNum;i++){
//    			System.out.println("第"+i+"条数据");
    		}
		}
		
		Integer[] sellerIntegers = {371034671,370956601,371005135,260529219,370952014,371018062,215065932,215996878,371027852,216713039,211277986,211187097,142751381,371035935,214761839,260809800,371021973,371040654,371018996,165676345,371029782,370879384,370943360,371030593,270842449,216449878,371012685,214606276,371020004,370882762,215174144,371022641,252963313,370951234,215074204,216148432,216714184,211391751,216211097,370954888,371017367,371018760,213562663,371024617,371037652,371037273,237533610,371026311,211278739,370882054,370953726,371008580,214566377,371024502,216437303,370892856,215173542,371094345,215073261,370950247,213024903,213548937,371021898,214766948,236848941,371016732,371036898,371018093,371031998,216154496,371045024,371021749,211183994,370885403,370948854,270806450,371036084,371022335,215566974,370876590,215232202,370972209,215155870,215984842,371029904,213554308,370952397,236866662,371036241,371023003,371038835,371020094,371039578,371025398,211275121,370880307,370949315,371033290,371025579,216501371,370880093,215194024,371024248,215153296,96489412,371025794,371023924,370953335,213041039,371039504,371017502,370952023,371036803,371023320,211179760,370884287,370952636,371031355,371021729,216375329,370878593,215190851,371027214,215160157,371024137,177927113,371011683,213041678,371032840,371016367,370956788,370951685,371042854,371027373,371045548,370938967,371020972,371026957,216439641,371043507,215188866,371094534,215161446,371018885,371020287,371031298,371024936,370951716,370954356,371029055,370888432,370951412,371022207,371023909,216374904,371006437,371021585,102759700,215072669,371010032,371038250,371019118,370958315,370955965,370950067,371037522,371025016,216502208,371028818,215152996,371037653,370952475,370938489,371026219,216125355,269592001,214598533,370952933,370950998,371028207,269590346,371032319,371026529,370971790,269590625,371022136,371093818,371094068,269588891,371046306,371093278,370971388,371027927,371045040,269591104,269591589,371025847,269592219,269592427,371023589,371029340,269590806,269589437,371042075,269594167,269587333,269588333,269587989,269589564,269589216,269584101,269587747};
		Integer[] eosIntegers = {371024617,371023924,371023003,371021973,371020287,371018093,371011683,371010032,371024936,371020094,371019118,371018760,371018062,371017502,371016732,371016367,371036898,371036241,370956601,370955965,370954888,370954356,370952475,370952023,370951685,371039504,371038835,371038250,371037273,371035935,371032840,371031998,371031298,211278739,211277986,211275121,211183994,211179760,216973867,216710670,216693201,216208507,215392557,215161446,215153296,215152996,215074204,214598533,215155870,215073261,215065932,215160157,215072669,371029904,371027852,371025794,371024137,371021898,371018885,371017367,270806450,214606276,214566377,371043507,371006437,371005135,370892856,370882762,370880093,370878593,370876590,371037522,371036084,371033290,371031355,371022207,371020972,371012685,371008580,371028207,371026957,371026219,371025579,371025016,371024502,371023909,371022335,371021729,371020004,213024903,216714184,216148432,216713039,215996878,236866662,236848941,216125355,215566974,216502208,216449878,216439641,216375329,216374904,216501371,216437303,371094534,370972209,370971790,370971388,269584101,371094345,371094068,371093818,371093278,371046306,371045040,371042075,371040654,371032319,371030593,371029782,371029340,371028818,371027927,371027214,371026529,371025847,371024248,371023589,371022641,371022136,371021585,269594167,269592427,269592219,269592001,269591589,269591104,269590806,269590625,269590346,269589564,269589437,269587333,269587747,269587989,269588333,269588891,269589216,371045024,371042854,371039578,371037652,371036803,371034671,370953726,370952636,370951412,370950998,370950067,370949315,370948854,370943360,370938967,370938489,371029055,371027373,371026311,371025398,371023320,371021749,371018996,216211097,215984842,213548937,177927113,142751381,102759700,96489412,252963313,370951095,370952976,370954393,370956543,370948762,370950298,370951234,370953335,371037653,370950247,370952014,370952933,370958315,370951716,370952397,370956788,262095270,262099619,262108767,262111006,262112507,262114763,261918646,261926871,262088687,262090483,262106813,262110243,262113661,261918088,262108011,262109610,262111537,261913758,261915437,261929990,262089401,262090746,262095903,262106232,261902273,261926133,261928089,262094326,215065736,215067030,215172605,215171135,215172281,215169514,215062969,270842449,370885403,370879384,370880307,370888432,371045548,370882054,370884287,260529219,216154496,237533610,230102390,165676345,230100860,168041622,215188866,215194024,215173542,215174144,215190851,215232202,260809800,214766948,214761839,213562663,213554308,213041678,213041039,211391751,211187097};
		
		List<Integer> seller = new ArrayList<Integer>();
		List<Integer> eos = new ArrayList<Integer>();
		for (int i = 0; i < sellerIntegers.length; i++) {
			seller.add(sellerIntegers[i]);
		}
		
		for (int i = 0; i < eosIntegers.length; i++) {
			eos.add(eosIntegers[i]);
		}
		
		int num = 0;
		for (int i = 0; i < eos.size(); i++) {
			if (!seller.contains(eos.get(i))) {
//				System.out.print(eos.get(i)+",");
				num++;
			}
		}
//		System.out.println(num);
		
		
		double doubleValue =1;
		for (int i = 0; i < 100; i++) {
//			System.out.println(doubleValue/10);
		}
		
        int i=188;
        switch(i)
        {
              case 1:
               System.out.println("one");
               break;
              case 10:
               System.out.println("ten");
               break;
              case 5:
               System.out.println("five");
               break;    
              case 3:
               System.out.println("three");
               break;
              default:
               System.out.println("other");
        }

        
        
	}
}
