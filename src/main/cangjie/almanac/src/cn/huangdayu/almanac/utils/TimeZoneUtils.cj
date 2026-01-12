package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.exception.AlmanacException
import j2cjlib.utils.ValueBox
import j2cjlib.utils.castOrThrow
import j2cjlib.utils.indexOfWrap
import j2cjlib.utils.parse
import j2cjlib.utils.split
import UNSUPPORTED.java.text.DecimalFormat
import UNSUPPORTED.java.text.NumberFormat
import UNSUPPORTED.java.util.Calendar
import UNSUPPORTED.java.util.GregorianCalendar
import UNSUPPORTED.java.util.Properties
import std.regex.Regex


/**
 * 处理地址工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneUtils {
    public init() { }

    private static var codeStr: ?String = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"


    /**
     * 处理地址的方法
     *
     * @param prov
     * @param area
     * @return
     */
    public static func judgeArea(prov: ?String, area: ?String): ?Array<?String> {
        var j2cj_param_generated_prov = prov
        var j2cj_param_generated_area = area
        j2cj_param_generated_prov = j2cj_param_generated_prov.<-- Missing mapping for java.lang.String member: replaceAll -->("省", "") // 字符替代
        j2cj_param_generated_area = j2cj_param_generated_area.<-- Missing mapping for java.lang.String member: replaceAll -->("市", "").<-- Missing mapping for java.lang.String member: replaceAll -->("区", "").<-- Missing mapping for java.lang.String member: replaceAll -->("县", "").<-- Missing mapping for java.lang.String member: replaceAll -->("镇", "").<-- Missing mapping for java.lang.String member: replaceAll -->("乡", "")
        var areas: ?Array<?String> = None
        let province: Array<?String> = ["天津", "TJ", "河北", "HE", "青海", "QH", "西藏", "XZ", "浙江", "ZJ", "重庆", "CQ", "河南", "HA", "福建", "FJ", "贵州", "GZ", "广西", "GX", "江西", "JX", "新疆", "XJ", "甘肃", "GS", "湖北", "Hb", "江苏", "JS", "辽宁", "Ln", "吉林", "JL", "安徽", "AH", "山西", "sx", "陕西", "SN", "港澳臺", "GAT", "云南", "YN", "宁夏", "NX", "广东", "GD", "上海", "SH", "山东", "SD", "四川", "SC", "湖南", "HN", "黑龙", "HL", "北京", "BJ", "内蒙", "NM", "海南", "HI"]
        do {
            var i = 0i32
            while (i < Int32(province.size)) {
                if (j2cj_param_generated_prov.getOrThrow().contains(province[Int64(i)].getOrThrow())) {
                    areas = split(Regex(" "), PropertiesUtils.getAdministrativeProperties().<-- Missing mapping for java.util.Properties member: getProperty -->(province[Int64(i + 1i32)])) // 根据给定正则表达式的匹配拆分此字符串。
                    break
                }
                i++
            }
        } while (false)
        if (areas.isSome()) {
            do {
                var i = 1i32
                while (i < Int32(areas.getOrThrow().size)) {
                    if (areas.getOrThrow()[Int64(i)].getOrThrow().contains(j2cj_param_generated_area.getOrThrow()) && Int32(j2cj_param_generated_area.getOrThrow().size) > 1i32) {
                        return [areas.getOrThrow()[0], areas.getOrThrow()[Int64(i)]]
                    }
                    i++
                }
            } while (false)
        }
        throw AlmanacException("地址输入错误,地址只能到县级。请确保地址：" + (j2cj_param_generated_prov ?? "null") + (j2cj_param_generated_area ?? "null") + " 是正确的。", None)
    }

    public static func getPortName(properLat: ?<-- No type mapping: java.util.Properties -->, properLong: ?<-- No type mapping: java.util.Properties -->, latitude: Float64, longitude: Float64): ?String {
        // getProperties(setStr(setTwoPointDouble(x),setTwoPointDouble(y)))
        var x = CommonUtils.getTwoPointDouble(latitude)
        var y = CommonUtils.getTwoPointDouble(longitude)
        var i = 0i32
        while (i < 10i32) {
            // System.out.println(x + "," + y);
            if (properLat.<-- Missing mapping for java.util.Properties member: getProperty -->(setTwoPointString(x)).isNone()) {
                x = setTwoPointDouble(x + 0.01)
            } else {
                return properLat.<-- Missing mapping for java.util.Properties member: getProperty -->(setTwoPointString(x))
            }
            if (properLong.<-- Missing mapping for java.util.Properties member: getProperty -->(setTwoPointDouble(y).toString()).isNone()) {
                y = setTwoPointDouble(y + 0.01)
            } else {
                return properLong.<-- Missing mapping for java.util.Properties member: getProperty -->(setTwoPointString(y))
            }
            i++
        }
        return properLat.<-- Missing mapping for java.util.Properties member: getProperty -->(setTwoPointString(x))
    }

    private static func setTwoPointString(d: Float64): ?String {

        let df1 = <-- Missing mapping for java.text.DecimalFormat constructor: DecimalFormat -->("########.00")
        let str = df1.<-- Missing mapping for java.text.NumberFormat member: format -->(d)
        return str
    }

    /***
     * 保留两个小数点方法包装
     */
    private static func setTwoPointDouble(d: Float64): Float64 {

        let df1 = <-- Missing mapping for java.text.DecimalFormat constructor: DecimalFormat -->("########.00")
        return parse<Float64>(df1.<-- Missing mapping for java.text.NumberFormat member: format -->(d).getOrThrow())

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
    public static func decodeJWD(encode: ?String): ?String {
        let jwd = StringBuilder()
        var i = 0i32
        while (i < 4i32) {
            if (2i32 == i) {
                jwd.append(String.<-- Missing mapping for java.lang.String member: format -->("%03d", [ValueBox<Int32>(indexOfWrap(codeStr.getOrThrow().indexOf(UInt8(UInt32(Rune(encode.getOrThrow()[Int64(i)]))))) + 73i32)]))
            } else {
                jwd.append(String.<-- Missing mapping for java.lang.String member: format -->("%02d", [ValueBox<Int32>(indexOfWrap(codeStr.getOrThrow().indexOf(UInt8(UInt32(Rune(encode.getOrThrow()[Int64(i)]))))))]))
            }
            i++
        }
        return jwd.<-- Missing mapping for java.lang.StringBuilder member: toString -->()
    }

    /****
     * 解密
     *
     * @param decode
     * @return
     */
    public static func encodeJWD(decode: ?Int32): ?String {
        let jwd = StringBuilder()
        let i = 230811316i32
        let ge = i % 100i32
        var shi = i % 100000i32 - ge
        var bai = i % 10000000i32 - shi
        var qian = i % 1000000000i32 - bai
        shi = shi / 100i32 - 73i32
        bai = bai / 100000i32
        qian = qian / 10000000i32
        jwd.append(Rune(codeStr.getOrThrow()[Int64(qian)]))
        jwd.append(Rune(codeStr.getOrThrow()[Int64(bai)]))
        jwd.append(Rune(codeStr.getOrThrow()[Int64(shi)]))
        jwd.append(Rune(codeStr.getOrThrow()[Int64(ge)]))
        return jwd.<-- Missing mapping for java.lang.StringBuilder member: toString -->()
    }

    public static func getTimeZone(gregorianCalendar: ?<-- No type mapping: java.util.GregorianCalendar -->): ?String {
        let format = DateTimeUtils.formatDateByFormat(castOrThrow<<-- No type mapping: java.util.GregorianCalendar -->, <-- No type mapping: java.util.Calendar -->>(gregorianCalendar), "Z") // +0800
        if (format.isSome() && Int32(format.getOrThrow().size) > 0i32) {
            let value: ?String = format.getOrThrow()[1..3]
            var i = 1i32
            let j = parse<Int32>(value.getOrThrow())
            if (j > 0i32) {
                i = parse<Int32>(value.getOrThrow())
            } else {
                i = parse<Int32>(value.getOrThrow()[1..])
            }
            if (format.getOrThrow().contains("-")) {
                return (format ?? "null") + " 西" + (ConstantsUtils.TIMEZONE[Int64(i - 1i32)] ?? "null") + "区"
            } else {
                return (format ?? "null") + " 东" + (ConstantsUtils.TIMEZONE[Int64(i - 1i32)] ?? "null") + "区"
            }
        }
        return None
    }
}