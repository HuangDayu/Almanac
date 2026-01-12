package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.exception.AlmanacException;

import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

import static cn.huangdayu.almanac.utils.CommonUtils.getTwoPointDouble;


/**
 * 经纬度坐标工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class CoordinatesUtils {

    /**
     * 经纬度坐标转换密钥
     */
    private static final String COORDINATES_CODE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String PROVINCE = "天津,TJ,河北,HE,青海,QH,西藏,XZ,浙江,ZJ,重庆,CQ,河南,HA,福建,FJ,贵州,GZ,广西,GX,江西,JX,新疆,XJ,甘肃,GS,湖北,Hb,江苏,JS,辽宁,Ln,吉林,JL,安徽,AH,山西,sx,陕西,SN,港澳臺,GAT,云南,YN,宁夏,NX,广东,GD,上海,SH,山东,SD,四川,SC,湖南,HN,黑龙,HL,北京,BJ,内蒙,NM,海南,HI";


    public static double[] decodeCoordinatesByArea(String province, String area) {
        String coordinates = decodeCoordinates(province, area);
        double[] value = new double[2];
        value[0] = (Double.parseDouble(coordinates.substring(4, 7)) + Double.parseDouble(coordinates.substring(7)) / 60);
        value[1] = (Double.parseDouble(coordinates.substring(0, 2)) + Double.parseDouble(coordinates.substring(2, 4)) / 60);
        return value;
    }

    /**
     * 通过地址获取经纬度坐标
     *
     * @param province
     * @param area
     * @return
     */
    private static String decodeCoordinates(String province, String area) {
        province = province.replaceAll("省", "");
        area = area.replaceAll("[市区县镇乡]", "");
        String[] provinces = PROVINCE.split(",");
        for (int i = 0; i < provinces.length; i++) {
            if (province.contains(provinces[i])) {
                String[] areas = PropertiesUtils.getAdministrativeProperties().getProperty(provinces[i + 1]).split(" ");
                for (int j = 1; j < areas.length; j++) {
                    if (areas[j].contains(area) && area.length() > 1) {
                        return decodeCoordinates(areas[j]);
                    }
                }
                break;
            }
        }
        throw new AlmanacException("地址输入错误,地址只能到县级。请确保地址：" + province + area + " 是正确的。", null);
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
     * 解密经纬度坐标
     *
     * @param encode
     * @return
     */
    private static String decodeCoordinates(String encode) {
        StringBuilder jwd = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (2 == i) {
                jwd.append(String.format("%03d", COORDINATES_CODE.indexOf(encode.charAt(i)) + 73));
            } else {
                jwd.append(String.format("%02d", COORDINATES_CODE.indexOf(encode.charAt(i))));
            }
        }
        return jwd.toString();
    }

    /****
     * 加密经纬度坐标
     *
     * @param i
     * @return
     */
    public static String encodeCoordinates(Integer i) {
        StringBuilder jwd = new StringBuilder();
        //int i = 230811316;
        int ge = i % 100;
        int shi = i % 100000 - ge;
        int bai = i % 10000000 - shi;
        int qian = i % 1000000000 - bai;
        shi = shi / 100 - 73;
        bai = bai / 100000;
        qian = qian / 10000000;
        jwd.append(COORDINATES_CODE.charAt(qian)).append(COORDINATES_CODE.charAt(bai)).append(COORDINATES_CODE.charAt(shi))
                .append(COORDINATES_CODE.charAt(ge));
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
