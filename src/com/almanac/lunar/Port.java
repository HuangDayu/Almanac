package com.almanac.lunar;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.almanac.lunar.Port;
import com.almanac.lunar.SunAndMoon;

/***
 * 获取港口信息类
 * 
 * @author Administrator
 *
 */
public class Port {
	private static Properties propt_lat;
	private static Properties propt_loog;

	public Port(Properties propt_lat, Properties propt_loog) {
		this.propt_lat = propt_lat;
		this.propt_loog = propt_loog;
	}

	public static String setStr(double a, double b) {
		return a + "|" + b;
	}

	public  String getProtName(SunAndMoon sun) {
		// getProperties(setStr(setTwoPointDouble(x),setTwoPointDouble(y)))
		double x = sun.getDoubleLatitude();
		double y = sun.getDoubleLongitude();
		for (int i = 0; i < 10; i++) {
			// System.out.println(x + "," + y);
			if (getPropertiesLat(setTwoPointString(x)) == null) {
				x = setTwoPointDouble(x + 0.01);
			} else {
				return getPropertiesLat(setTwoPointString(x));
			}
			if (getPropertiesLoog(String.valueOf(setTwoPointDouble(y))) == null) {
				y = setTwoPointDouble(y + 0.01);
			} else {
				return getPropertiesLoog(setTwoPointString(y));
			}
		}
		return getPropertiesLat(setTwoPointString(x));
	}

	public static String setTwoPointString(double d) {

		DecimalFormat df1 = new DecimalFormat("########.00");
		String str = df1.format(d);
		return str;
	}

	/***
	 * 保留两个小数点方法包装
	 */
	public static double setTwoPointDouble(double d) {

		DecimalFormat df1 = new DecimalFormat("########.00");
		return Double.valueOf(df1.format(d));

		// DecimalFormat df1 = new DecimalFormat ( "###,##0.00" ) ;
		// return Double.valueOf(df1.format(d));

		// BigDecimal b = new BigDecimal(d);
		// double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/***
	 * @param str
	 *            key
	 * @return volue
	 */
	public static String getPropertiesLat(String str) {
		return propt_lat.getProperty(str);
	}

	public static String getPropertiesLoog(String str) {
		return propt_loog.getProperty(str);
	}

}
