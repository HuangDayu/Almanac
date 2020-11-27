package com.almanac.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 行政区&经纬度工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PropertiesUtils {
    /**
     * 中国行政区
     *
     * @return
     */
    public static Properties getAdministrativeProperties() {
        Properties propt = new Properties();
        try {
            propt.load(PropertiesUtils.class.getClassLoader()
                    .getResourceAsStream("config/administrative.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propt;
    }

    ;

    /**
     * 纬度
     *
     * @return
     */
    public static Properties getLatportProperties() {
        Properties propt_lat = new Properties();
        try {
            propt_lat.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("config/latport.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propt_lat;
    }

    ;

    /**
     * 经度
     *
     * @return
     */
    public static Properties getLoogportProperties() {
        Properties propt_loog = new Properties();
        try {
            propt_loog.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("config/loogport.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propt_loog;
    }

    ;

}
