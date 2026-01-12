package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.exception.AlmanacException
import UNSUPPORTED.java.io.BufferedReader
import UNSUPPORTED.java.io.FileReader
import UNSUPPORTED.java.lang.ClassLoader
import UNSUPPORTED.java.util.Properties
import std.reflect.TypeInfo

/**
 * 行政区&经纬度工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class PropertiesUtils {

    private init() { }

    private static let LATITUDE = getLatitudeProperties()
    private static let LONGITUDE = getLongitudeProperties()
    private static let ADMINISTRATIVE = getAdministrativeProperties()

    public static func getProperties(name: ?String): ?<-- No type mapping: java.util.Properties --> {
        let properties = <-- Missing mapping for java.util.Properties constructor: Properties -->()
        try {
            properties.<-- Missing mapping for java.util.Properties member: load -->(TypeInfo.of<PropertiesUtils>().<-- Missing mapping for java.lang.Class member: getClassLoader -->().<-- Missing mapping for java.lang.ClassLoader member: getResourceAsStream -->(name))
        } catch (e: Exception) {
            try {
                properties.<-- Missing mapping for java.util.Properties member: load -->(<-- Missing mapping for java.io.BufferedReader constructor: BufferedReader -->(<-- Missing mapping for java.io.FileReader constructor: FileReader -->(name)))
            } catch (ex: Exception) {
                throw AlmanacException("获取配置文件异常", ex)
            }
        }
        return properties
    }

    /**
     * 中国行政区
     *
     * @return
     */
    public static func getAdministrativeProperties(): ?<-- No type mapping: java.util.Properties --> {
        return getProperties("administrative.properties")
    }

    /**
     * 纬度
     *
     * @return
     */
    public static func getLatitudeProperties(): ?<-- No type mapping: java.util.Properties --> {
        return getProperties("latport.properties")
    }

    /**
     * 经度
     *
     * @return
     */
    public static func getLongitudeProperties(): ?<-- No type mapping: java.util.Properties --> {
        return getProperties("loogport.properties")
    }

    public static func getLatitude(): ?<-- No type mapping: java.util.Properties --> {
        return LATITUDE
    }

    public static func getLongitude(): ?<-- No type mapping: java.util.Properties --> {
        return LONGITUDE
    }

    public static func getADMINISTRATIVE(): ?<-- No type mapping: java.util.Properties --> {
        return ADMINISTRATIVE
    }
}