package cn.huangdayu.almanac.utils;

import java.text.DecimalFormat;
import java.util.Properties;


/**
 * 港口信息工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PortUtils {

    public static String getProtName(Properties properLat,Properties properLoog) {
        // getProperties(setStr(setTwoPointDouble(x),setTwoPointDouble(y)))
        double x = SunMoonUtils.getDoubleLatitude();
        double y = SunMoonUtils.getDoubleLongitude();
        for (int i = 0; i < 10; i++) {
            // System.out.println(x + "," + y);
            if (properLat.getProperty(setTwoPointString(x)) == null) {
                x = setTwoPointDouble(x + 0.01);
            } else {
                return properLat.getProperty(setTwoPointString(x));
            }
            if (properLoog.getProperty(String.valueOf(setTwoPointDouble(y))) == null) {
                y = setTwoPointDouble(y + 0.01);
            } else {
                return properLoog.getProperty(setTwoPointString(y));
            }
        }
        return properLat.getProperty(setTwoPointString(x));
    }

    private static String setTwoPointString(double d) {

        DecimalFormat df1 = new DecimalFormat("########.00");
        String str = df1.format(d);
        return str;
    }

    /***
     * 保留两个小数点方法包装
     */
    private static double setTwoPointDouble(double d) {

        DecimalFormat df1 = new DecimalFormat("########.00");
        return Double.valueOf(df1.format(d));

        // DecimalFormat df1 = new DecimalFormat ( "###,##0.00" ) ;
        // return Double.valueOf(df1.format(d));

        // BigDecimal b = new BigDecimal(d);
        // double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

}
