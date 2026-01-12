package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.exception.AlmanacException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

/**
 * 行政区&经纬度工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PropertiesUtils {

    private PropertiesUtils() {
    }

    private static final Properties LATITUDE = getProperties("latport.properties");
    private static final Properties LONGITUDE = getProperties("loogport.properties");
    private static final Properties ADMINISTRATIVE = getProperties("administrative.properties");

    private static Properties getProperties(String name) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(name));
        } catch (Exception e) {
            try {
                properties.load(new BufferedReader(new FileReader(name)));
            } catch (Exception ex) {
                throw new AlmanacException("获取配置文件异常", ex);
            }
        }
        return properties;
    }

    /**
     * 中国行政区
     *
     * @return
     */
    public static Properties getAdministrativeProperties() {
        return ADMINISTRATIVE;
    }

    /**
     * 纬度
     *
     * @return
     */
    public static Properties getLatitudeProperties() {
        return LATITUDE;
    }

    /**
     * 经度
     *
     * @return
     */
    public static Properties getLongitudeProperties() {
        return LONGITUDE;
    }


}
