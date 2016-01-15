package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 日期工具类
 * 
 */
public class DateUtil {
	
	private static Log log = LogFactory.getLog(DateUtil.class);
	public static long secondsPerDay = 24 * 60 * 60 * 1000;
	/**
	 * 获取某天的开始时间
	 * @return
	 */
	public static Date getDayStartTime(int days){
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, days);
		cl.set(Calendar.HOUR_OF_DAY, 0);
		cl.set(Calendar.MINUTE, 0);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MILLISECOND, 0);
//		log.debug("getDayStartTime() ----"+cl.getTime().toString());
		return 	cl.getTime();
	}

	/**
	 * 获取上一天的结束时间
	 * @return
	 */
	public static Date getLastDayEndTime(){
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.DATE, -1);
		cl.set(Calendar.HOUR_OF_DAY, 23);
		cl.set(Calendar.MINUTE, 59);
		cl.set(Calendar.SECOND, 59);
		cl.set(Calendar.MILLISECOND, 999);
//		log.debug("getLastDayEndTime() ----"+cl.getTime().toString());
		return 	cl.getTime();
	}

	/**
	 * 两个日期相减相差天数（不考虑时间）
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getDaySub(Date beginDate, Date endDate) {
		Calendar beginCL = Calendar.getInstance();
		beginCL.setTime(beginDate);
		beginCL.set(Calendar.HOUR_OF_DAY, 0);
		beginCL.set(Calendar.MINUTE, 0);
		beginCL.set(Calendar.SECOND, 0);
		beginCL.set(Calendar.MILLISECOND, 0);

		Calendar endCL = Calendar.getInstance();
		endCL.setTime(endDate);
		endCL.set(Calendar.HOUR_OF_DAY, 0);
		endCL.set(Calendar.MINUTE, 0);
		endCL.set(Calendar.SECOND, 0);
		endCL.set(Calendar.MILLISECOND, 0);
		
		long day = (endCL.getTimeInMillis() - beginCL.getTimeInMillis()) / (24 * 60 * 60 * 1000);
//		log.debug("getDaySub() ----"+day);
		return day;
	}
	
	/**
	 * 获取两个日期时间差，返回时间差的绝对值
	 * @param date1 
	 * @param date2
	 * @return
	 */
	public static Long getTimeSub(Date date1,Date date2){
		Long time = (long) -1;
		try {
			time = date2.getTime()-date1.getTime();
			if (time<0) {
				time = date1.getTime()-date2.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		log.debug("getTimeSub() ----"+time);
		return time;
	}
	
	/**
	 * 转换成日期
	 */
	public static Date strToDateYYYYMMDD(String sDate) {
        java.util.Date date = null;
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            date = format.parse(sDate);
        } catch (ParseException e) {
        }
//        log.debug("strToDateYYYYMMDD() ----"+date.toString());
        return date;
    }
	
	/**
	 * date to String
	 * @param format
	 * @param date
	 * @return
	 */
	public static String dateToString(String format,Date date){
		
		if (format.isEmpty()) {
			return null;
		}
		SimpleDateFormat df=new SimpleDateFormat(format);
		String str = df.format(date);
//		log.debug("dateToString() ----"+str);
		return str;
	}
	
	/**
	 * 获取一个几天后的时间
	 * @param date 待处理时间
	 * @param day 几天
	 * @return
	 */
	public static Date getTimeAfterTheDate(Date date,int day){
		Calendar now = Calendar.getInstance();  
        now.setTime(date);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
//        log.debug("getTimeAfterTheDate() ----"+now.getTime().toString());
        return now.getTime();  
	}


}