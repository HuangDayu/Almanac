package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.exception.AlmanacException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.huangdayu.almanac.utils.CommonUtils.getTwoPointDouble;


/**
 * 处理地址工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneUtils {

    private static String codeStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    /**
     * 处理地址的方法
     *
     * @param prov
     * @param area
     * @return
     */
    public static String[] judgeArea(String prov, String area) {
        prov = prov.replaceAll("省", "");// 字符替代
        area = area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", "");
        String[] areas = null;
        String[] province = {"天津", "TJ", "河北", "HE", "青海", "QH", "西藏", "XZ", "浙江", "ZJ", "重庆", "CQ", "河南", "HA", "福建", "FJ", "贵州", "GZ", "广西", "GX", "江西", "JX", "新疆", "XJ", "甘肃", "GS", "湖北", "Hb", "江苏", "JS", "辽宁", "Ln", "吉林", "JL", "安徽", "AH", "山西", "sx", "陕西", "SN", "港澳臺", "GAT", "云南", "YN", "宁夏", "NX", "广东", "GD", "上海", "SH", "山东", "SD", "四川", "SC", "湖南", "HN", "黑龙", "HL", "北京", "BJ", "内蒙", "NM", "海南", "HI"};
        for (int i = 0; i < province.length; i++) {
            if (prov.contains(province[i])) {
                areas = PropertiesUtils.getAdministrativeProperties().getProperty(province[i + 1]).split(" ");// 根据给定正则表达式的匹配拆分此字符串。
                break;
            }
        }
        if (areas != null) {
            for (int i = 1; i < areas.length; i++) {
                if (areas[i].contains(area) && area.length() > 1) {
                    return new String[]{areas[0], areas[i]};
                }
            }
        }
        throw new AlmanacException("地址输入错误,地址只能到县级。请确保地址：" + prov + area + " 是正确的。", null);
    }

    public static String getPortName(Properties properLat, Properties properLong, double latitude, double longitude) {
        // getProperties(setStr(setTwoPointDouble(x),setTwoPointDouble(y)))
        double x = getTwoPointDouble(latitude);
        double y = getTwoPointDouble(longitude);
        for (int i = 0; i < 10; i++) {
            // System.out.println(x + "," + y);
            if (properLat.getProperty(setTwoPointString(x)) == null) {
                x = setTwoPointDouble(x + 0.01);
            } else {
                return properLat.getProperty(setTwoPointString(x));
            }
            if (properLong.getProperty(String.valueOf(setTwoPointDouble(y))) == null) {
                y = setTwoPointDouble(y + 0.01);
            } else {
                return properLong.getProperty(setTwoPointString(y));
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


    /****
     * 加密
     *
     * @param encode
     * @return
     */
    public static String decodeJWD(String encode) {
        StringBuilder jwd = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (2 == i) {
                jwd.append(String.format("%03d", codeStr.indexOf(encode.charAt(i)) + 73));
            } else {
                jwd.append(String.format("%02d", codeStr.indexOf(encode.charAt(i))));
            }
        }
        return jwd.toString();
    }

    /****
     * 解密
     *
     * @param decode
     * @return
     */
    public static String encodeJWD(Integer decode) {
        StringBuilder jwd = new StringBuilder();
        int i = 230811316;
        int ge = i % 100;
        int shi = i % 100000 - ge;
        int bai = i % 10000000 - shi;
        int qian = i % 1000000000 - bai;
        shi = shi / 100 - 73;
        bai = bai / 100000;
        qian = qian / 10000000;
        jwd.append(codeStr.charAt(qian)).append(codeStr.charAt(bai)).append(codeStr.charAt(shi))
                .append(codeStr.charAt(ge));
        return jwd.toString();
    }

    public static String getTimeZone(GregorianCalendar gregorianCalendar){
        String format = DateTimeUtils.formatDateByFormat(gregorianCalendar, "Z");// +0800
        if (format != null && format.length() > 0) {
            String value = format.substring(1, 3);
            int i = 1, j = Integer.parseInt(value);
            if (j > 0) {
                i = Integer.parseInt(value);
            } else {
                i = Integer.parseInt(value.substring(1));
            }
            if (format.contains("-")) {
                return format + " 西" + ConstantsUtils.TIMEZONE[i - 1] + "区";
            } else {
                return  format + " 东" + ConstantsUtils.TIMEZONE[i - 1] + "区";
            }
        }
        return null;
    }
}
