package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.exception.AlmanacException;

import java.io.IOException;
import java.util.Properties;

/**
 * 行政区&经纬度工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PropertiesUtils {

    private static final Properties LATPORT, LOOGPORT, ADMINISTRATIVE;

    static {
        // 初始化配置
        LATPORT = getLatitudeProperties();
        LOOGPORT = getLongitudeProperties();
        ADMINISTRATIVE = getAdministrativeProperties();
    }


    public static Properties getProperties(String name) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(name));
        } catch (IOException e) {
            throw new AlmanacException("获取配置文件异常", e);
        }
        return properties;
    }

    /**
     * 中国行政区
     *
     * @return
     */
    public static Properties getAdministrativeProperties() {
        return getProperties("administrative.properties");
    }

    /**
     * 纬度
     *
     * @return
     */
    public static Properties getLatitudeProperties() {
        return getProperties("latport.properties");
    }

    /**
     * 经度
     *
     * @return
     */
    public static Properties getLongitudeProperties() {
        return getProperties("loogport.properties");
    }

    public static Properties getLATPORT() {
        return LATPORT;
    }

    public static Properties getLOOGPORT() {
        return LOOGPORT;
    }

    public static Properties getADMINISTRATIVE() {
        return ADMINISTRATIVE;
    }
}
