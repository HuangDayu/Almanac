package com.almanac.utils;

import java.text.DecimalFormat;
import java.util.Properties;


/**
 * 港口信息工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PortUtils {

    private static Properties properLat;
    private static Properties properLoog;

    public static void init(Properties properLat, Properties properLoog) {
        PortUtils.properLat = properLat;
        PortUtils.properLoog = properLoog;
    }

    public static String setStr(double a, double b) {
        return a + "|" + b;
    }

    public static String getProtName() {
        // getProperties(setStr(setTwoPointDouble(x),setTwoPointDouble(y)))
        double x = SunMoonUtils.getDoubleLatitude();
        double y = SunMoonUtils.getDoubleLongitude();
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
        return properLat.getProperty(str);
    }

    public static String getPropertiesLoog(String str) {
        return properLoog.getProperty(str);
    }

}
