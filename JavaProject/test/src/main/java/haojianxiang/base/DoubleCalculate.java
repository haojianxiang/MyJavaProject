package haojianxiang.base;

import java.math.BigDecimal;


public class DoubleCalculate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(1.1/10);
		Double aa = divide(4.9, 10.0);
		System.out.println(aa);
		Double b = div(4.9, 10.0, 2);
		System.out.println(b);
	}
	
	public static double divide(Double dividend,Double divisor){
		double result = 0.0;
		BigDecimal dividendValue = BigDecimal.valueOf(dividend);
		BigDecimal divisorValue = BigDecimal.valueOf(divisor);
		result = dividendValue.divide(divisorValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
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
}
