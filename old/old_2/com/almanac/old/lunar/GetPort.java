package com.almanac.old.lunar;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.almanac.old.lunar.GetPort;
import com.almanac.old.lunar.SunAndMoon;

/***
 * 获取港口信息类
 * @author Administrator
 *
 */
public class GetPort {
	static Properties propt_lat, propt_loog;
	static {
		propt_lat = new Properties();
		propt_loog = new Properties();
		try {
			propt_lat.load(GetPort.class.getClassLoader().getResourceAsStream("config/latport.properties"));
			propt_loog.load(GetPort.class.getClassLoader().getResourceAsStream("config/loogport.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String setStr(double a, double b) {
		return a + "|" + b;
	}

	public static String getProtName(SunAndMoon sun) {
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
		String str2 = propt_lat.getProperty(str);
		return str2;
	}

	public static String getPropertiesLoog(String str) {
		String str2 = propt_loog.getProperty(str);
		return str2;
	}

}
