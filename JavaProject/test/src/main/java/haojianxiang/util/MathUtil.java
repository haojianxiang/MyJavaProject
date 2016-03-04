package haojianxiang.util;

import java.math.BigDecimal;

/**
 * java浮点精度计算工具类
 * @author yangqin@dhgate.com
 *
 */
public class MathUtil {

	public static double round(double value, int scale) {
		int roundingMode = BigDecimal.ROUND_HALF_UP;
	
		return round(value,scale, roundingMode);
	}
	
	public static double round(double value, int scale, int roundingMode) {

		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	public static double sum(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();
	}

	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	public static double mul(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if(d2==0){
			throw new IllegalArgumentException(
			"The d2 must be not zero");
		}
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] arg) {

			System.out.println(MathUtil.div(64, 9, 3));
	}
	
	/**
     * 精确到小数点后两位 四舍五入
     */
    public static  double getFormatScale2(double value){
    	value = value * 100 ;
    	value = Math.round(value);
    	value = value/100 ;
    	
    	return value ;
    }
    
    /**
     * 向上取整，不足0.5按0.5计算，超过0.5按1计算
     * */
    public static  double getCeilHalf(double value){
    	//value = 7.00000;
		double result = 0;
    	//System.out.println("--:"+value);
    	double l_value = Math.floor(value);
    	double decimal = value - l_value;
    	if(decimal>0 && decimal<=0.5){
    		result = l_value + 0.5;
    	}else if(decimal>0.5){
    		result = l_value + 1;
    	}else{
    		result = l_value;
    	}
    	//System.out.println("--:"+result);	
    	return result ;
    }

}
