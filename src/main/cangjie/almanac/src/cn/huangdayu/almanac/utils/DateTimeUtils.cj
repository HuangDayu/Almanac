package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import j2cjlib.utils.J2CjDateTimeExtension
import j2cjlib.utils.castOrThrow
import j2cjlib.utils.parse
import j2cjlib.utils.shr
import j2cjlib.utils.split
import j2cjlib.utils.stringValueOf
import UNSUPPORTED.java.text.DateFormat
import UNSUPPORTED.java.text.ParseException
import UNSUPPORTED.java.text.SimpleDateFormat
import UNSUPPORTED.java.time.Instant
import UNSUPPORTED.java.util.Calendar
import UNSUPPORTED.java.util.GregorianCalendar
import UNSUPPORTED.java.util.TimeZone
import std.regex.Regex
import std.sync.Monitor
import std.time.DateTime

/**
 * 日期时间工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class DateTimeUtils {
    public init() { }

    public static var timeStr: ?String = "yyyy年MM月dd日  HH时mm分ss秒 时区:Z 星期中的天数:E 年中的周数:w 月份中的周数:W 年中的天数:D 月份中的天数:d 月份中的星期:F "

    public static func dateToCalendar(date: ?DateTime): ?<-- No type mapping: java.util.Calendar --> {
        return castOrThrow<<-- No type mapping: java.util.GregorianCalendar -->, <-- No type mapping: java.util.Calendar -->>(timeInMillisToCalendar(date.getOrThrow().getTime()))
    }

    public static func dateToCalendar2(date: ?DateTime): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: setTime -->(date)
        return calendar
    }

    public static func timeInMillisToCalendar(TimeInMillis: Int64): ?<-- No type mapping: java.util.GregorianCalendar --> {
        let calendar = <-- Missing mapping for java.util.GregorianCalendar constructor: GregorianCalendar -->()
        calendar.<-- Missing mapping for java.util.Calendar member: setTimeInMillis -->(TimeInMillis)
        return calendar
    }

    public static func timeInMillisToCalendar2(TimeInMillis: Int64): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: setTimeInMillis -->(TimeInMillis)
        return calendar
    }

    public static func instantToCalendar(instant: ?<-- No type mapping: java.time.Instant -->): ?<-- No type mapping: java.util.Calendar --> {
        return castOrThrow<<-- No type mapping: java.util.GregorianCalendar -->, <-- No type mapping: java.util.Calendar -->>(timeInMillisToCalendar(instant.<-- Missing mapping for java.time.Instant member: toEpochMilli -->()))
    }

    /***
     * new Date().toInstant()
     *
     * @param instant
     *            格式2018-09-07T08:16:03.206Z
     * @return
     */
    public static func instantToCalendar(instant: ?String): ?<-- No type mapping: java.util.Calendar --> {
        let sqlits: ?Array<?String> = split(Regex("T|-|Z|:|\\."), instant)
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(parse<Int32>(sqlits.getOrThrow()[0].getOrThrow()), (parse<Int32>(sqlits.getOrThrow()[1].getOrThrow())) - 1i32, parse<Int32>(sqlits.getOrThrow()[2].getOrThrow()), parse<Int32>(sqlits.getOrThrow()[3].getOrThrow()) + 8i32, parse<Int32>(sqlits.getOrThrow()[4].getOrThrow()), parse<Int32>(sqlits.getOrThrow()[5].getOrThrow()))
        return calendar
    }

    public static func strToCalendar(str: ?String): ?<-- No type mapping: java.util.Calendar --> {
        return dateToCalendar(toDate(str))
    }

    public static func intToCalendar(year: Int32, month: Int32, day: Int32, hourOfDay: Int32, minute: Int32): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(year, month - 1i32, day, hourOfDay, minute)
        return calendar
    }

    public static func intToCalendar(year: Int32, month: Int32, day: Int32, hourOfDay: Int32, minute: Int32, second: Int32): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(year, month - 1i32, day, hourOfDay, minute, second)
        return calendar
    }

    public static func intToCalendar(year: Int32, month: Int32, day: Int32, hourOfDay: Int32, minute: Int32, second: Float64): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(year, month - 1i32, day, hourOfDay, minute, Int32(second))
        return calendar
    }

    /**
     * 使用clendar类来计算日期
     *
     * @param year
     * @param month
     * @param day
     * @param hourOfDay
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static func intToCalendar(year: Int32, month: Int32, day: Int32, hourOfDay: Int32, minute: Int32, second: Int32, millisecond: Int32): ?<-- No type mapping: java.util.Calendar --> {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->, year)
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->, month - 1i32) // 老外用0-11，11就相当于12月
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->, day)
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: HOUR_OF_DAY -->, hourOfDay)
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MINUTE -->, minute)
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: SECOND -->, second)
        calendar.<-- Missing mapping for java.util.Calendar member: set -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MILLISECOND -->, millisecond)
        return calendar
    }

    public static func toCalendar(timeZoneDTO: ?TimeZoneDTO): ?<-- No type mapping: java.util.Calendar --> {
        return intToCalendar(timeZoneDTO.getOrThrow().getYear(), timeZoneDTO.getOrThrow().getMonth(), timeZoneDTO.getOrThrow().getDay(), timeZoneDTO.getOrThrow().getHour(), timeZoneDTO.getOrThrow().getMinute(), timeZoneDTO.getOrThrow().getSecond())
    }

    public static func toTimeZone(timeZoneDTO: ?TimeZoneDTO, calendar: ?<-- No type mapping: java.util.Calendar -->): Unit {
        timeZoneDTO.getOrThrow().setYear(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->))
        timeZoneDTO.getOrThrow().setMonth(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->) + 1i32)
        timeZoneDTO.getOrThrow().setDay(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->))
        timeZoneDTO.getOrThrow().setWeek(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_WEEK -->))
        timeZoneDTO.getOrThrow().setHour(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: HOUR_OF_DAY -->))
        timeZoneDTO.getOrThrow().setMinute(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MINUTE -->))
        timeZoneDTO.getOrThrow().setSecond(calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: SECOND -->))
        timeZoneDTO.getOrThrow().setIndex(Float64(DateTimeUtils.getTimZoneInt(calendar)))
        // +0800
        let format = DateTimeUtils.formatDateByFormat(calendar, "Z")
        let value: ?String = format.getOrThrow()[1..3]
        var i = 1i32
        let j = parse<Int32>(value.getOrThrow())
        if (j > 0i32) {
            i = parse<Int32>(value.getOrThrow())
        } else {
            i = parse<Int32>(value.getOrThrow()[1..])
        }
        if (format.getOrThrow().contains("-")) {
            timeZoneDTO.getOrThrow().setTimeZone((format ?? "null") + " 西" + (ConstantsUtils.TIMEZONE[Int64(i - 1i32)] ?? "null") + "区")
        } else {
            timeZoneDTO.getOrThrow().setTimeZone((format ?? "null") + " 东" + (ConstantsUtils.TIMEZONE[Int64(i - 1i32)] ?? "null") + "区")
        }
    }

    public static func calculateTimeZone(now: ?TimeZoneDTO, old: ?TimeZoneDTO): Unit {
        toTimeZone(now, toCalendar(old))
    }

    public static func toUTC(date: ?DateTime): ?String {
        return date.<-- Missing mapping for java.util.Date member: toInstant -->().<-- Missing mapping for java.time.Instant member: toString -->()
    }

    public static func dateFormat(calendar: ?<-- No type mapping: java.util.Calendar -->, format: ?String): ?String {
        return dateFormat(calendarToDate(calendar), format)
    }

    /***
     * 时间格式处理
     *
     * @param date
     * @param format
     *            yyyyMMddHHmmssZZZ
     * @return
     */
    public static func dateFormat(date: ?DateTime, format: ?String): ?String {
        var result: ?String = None
        if (date.isSome()) {
            try {
                let sdf = <-- Missing mapping for java.text.SimpleDateFormat constructor: SimpleDateFormat -->(format)
                result = sdf.<-- Missing mapping for java.text.DateFormat member: format -->(date)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return result
    }

    /**
     * 2018-09-07T09:24:05.350Z http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
     * BUG 2021-02-18 疑似有year计算错误的问题
     *
     * @return date
     */
    public static func toDate(str: ?String): ?DateTime {
        var j2cj_param_generated_str = str
        var format: ?String = None
        if (j2cj_param_generated_str.getOrThrow().contains(".") || j2cj_param_generated_str.getOrThrow().contains("T") || j2cj_param_generated_str.getOrThrow().contains("Z")) {
            j2cj_param_generated_str = j2cj_param_generated_str.getOrThrow().replace("T", " ").<-- Missing mapping for java.lang.String member: replaceAll -->("Z", "")
            format = "yyyy-MM-dd HH:mm:ss.SS"
        } else {
            if (j2cj_param_generated_str.getOrThrow().contains("年") && 2i32 == Int32(split(Regex(":"), j2cj_param_generated_str).size)) {
                format = "yyyy年MM月dd日 HH:mm"
            } else {
                if (j2cj_param_generated_str.getOrThrow().contains("年")) {
                    format = "yyyy年MM月dd日 HH:mm:ss"
                } else {
                    if (2i32 == Int32(split(Regex(":"), j2cj_param_generated_str).size)) {
                        format = "yyyy-MM-dd HH:mm"
                    } else {
                        format = "yyyy-MM-dd HH:mm:ss"
                    }
                }
            }
        }
        let dateFormat: <-- No type mapping: java.text.DateFormat --> = <-- Missing mapping for java.text.SimpleDateFormat constructor: SimpleDateFormat -->(format)
        var date: ?DateTime = None
        try {
            date = dateFormat.<-- Missing mapping for java.text.DateFormat member: parse -->(j2cj_param_generated_str)
        } catch (e: <-- No type mapping: java.text.ParseException -->) {
            e.printStackTrace()
        }
        return date
    }

    public static func toDate(y: Int32, M: Int32, d: Int32, h: Int32, m: Int32): ?DateTime {
        let str: ?String = y.toString() + "-" + M.toString() + "-" + d.toString() + " " + h.toString() + ":" + m.toString()
        return toDate(str)
    }

    public static func toDate(y: Int32, M: Int32, d: Int32, h: Int32, m: Int32, s: Int32): ?DateTime {
        let str: ?String = y.toString() + "-" + M.toString() + "-" + d.toString() + " " + h.toString() + ":" + m.toString() + ":" + s.toString()
        return toDate(str)
    }

    public static func toDate(y: Int32, M: Int32, d: Int32, h: Int32, m: Int32, s: Int32, ss: Int32): ?DateTime {
        let str: ?String = y.toString() + "-" + M.toString() + "-" + d.toString() + " " + h.toString() + ":" + m.toString() + ":" + s.toString() + "." + ss.toString()
        return toDate(str)
    }

    /***
     * 时间24小时化
     *
     * @param str
     * @return
     */
    public static func getFormattingTime(str: ?String): ?String {
        var newStr: ?String = ""
        if (str.getOrThrow().contains(":") && !str.getOrThrow().contains("年") && !str.getOrThrow().contains("-") && !str.getOrThrow().contains("月")) {
            let strsArea: ?Array<?String> = split(Regex(":"), str)
            var i = 0i32
            while (i < Int32(strsArea.getOrThrow().size)) {
                if (parse<Int32>(strsArea.getOrThrow()[Int64(i)].getOrThrow()) < 10i32) {
                    strsArea.getOrThrow()[Int64(i)] = "0" + (strsArea.getOrThrow()[Int64(i)] ?? "null")
                }
                i++
            }
            newStr = (strsArea.getOrThrow()[0] ?? "null") + ":" + (strsArea.getOrThrow()[1] ?? "null") + ":" + (strsArea.getOrThrow()[2] ?? "null")
        } else {
            newStr = str
        }
        return newStr
    }

    public static func getTimZoneInt(calendar: ?<-- No type mapping: java.util.Calendar -->): Int32 {
        return getTimZoneInt(calendar.<-- Missing mapping for java.util.Calendar member: getTime -->())
    }


    public static func getTimZoneInt(date: ?DateTime): Int32 {
        let strDateFormat = formatDateByFormat(date, "Z") // +0800
        let str2: ?String = strDateFormat.getOrThrow()[1..3]
        let j = parse<Int32>(str2.getOrThrow()[0..])
        var timeZoneInt = 0i32
        if (j > 0i32) {
            timeZoneInt = parse<Int32>(str2.getOrThrow())
        } else {
            timeZoneInt = parse<Int32>(str2.getOrThrow()[1..])
        }
        return timeZoneInt
    }

    public static func formatDateByFormat(calendar: ?<-- No type mapping: java.util.Calendar -->, format: ?String): ?String {
        return formatDateByFormat(calendarToDate(calendar), format)
    }

    /***
     * 时间格式处理
     *
     * @param date
     * @param format
     *            yyyyMMddHHmmssZZZ
     * @return
     */
    public static func formatDateByFormat(date: ?DateTime, format: ?String): ?String {
        var result: ?String = ""
        if (date.isSome()) {
            try {
                let sdf = <-- Missing mapping for java.text.SimpleDateFormat constructor: SimpleDateFormat -->(format)
                result = sdf.<-- Missing mapping for java.text.DateFormat member: format -->(date)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return result
    }

    public static func getFormatDate(str: ?String): ?String {
        return <-- Missing mapping for java.text.SimpleDateFormat constructor: SimpleDateFormat -->(str).<-- Missing mapping for java.text.DateFormat member: format -->(DateTime.now()).<-- Missing mapping for java.lang.String member: toString -->()
    }

    /***
     * Calendar 转成 Date
     *
     * @param calendar
     * @return
     */
    public static func calendarToDate(calendar: ?<-- No type mapping: java.util.Calendar -->): ?DateTime {
        let year = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->)
        let month = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->) + 1i32
        let day = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->)
        let week = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_WEEK -->)
        let hour = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: HOUR_OF_DAY -->)
        let minute = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MINUTE -->)
        let second = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: SECOND -->)
        let millisecond = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MILLISECOND -->)
        return toDate(year, month, day, hour, minute, second, millisecond)
    }

    public static func javaMain(args: ?Array<?String>): Unit {
        let calendar = <-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: getInstance -->()
        println(stringValueOf(calendarToDate(calendar)))
    }

    public static func getWeek(calendar: ?<-- No type mapping: java.util.Calendar -->): ?String {
        var y = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->)
        var m = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->) + 1i32
        let d = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->)
        // String week[] = new String[] { "日", "一", "二", "三", "四", "五", "六" };
        // String week[] = new String[] { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        if (m < 3i32) {
            m += 12i32
            y--
        }
        let w = (d + 1i32 + 2i32 * m + 3i32 * (m + 1i32) / 5i32 + y + (shr(y, 2i32)) - y / 100i32 + y / 400i32) % 7i32

        return ConstantsUtils.WEEK_NAME[Int64(w)]
    }

    public static func getTime(d: Float64): ?String {
        let formatter = <-- Missing mapping for java.text.SimpleDateFormat constructor: SimpleDateFormat -->("HH:mm:ss")
        let currentTime = <-- Missing mapping for java.util.Date constructor: Date -->(Int64(d))
        let dateString = formatter.<-- Missing mapping for java.text.DateFormat member: format -->(currentTime)
        return dateString
    }

    private static var utcCal: ?<-- No type mapping: java.util.GregorianCalendar --> = None

    /**
     * 创建GregorianCalendar对象
     */
    private static func makeUTCCalendar(): Unit {
        synchronized (j2cjGeneratedStaticLockObject) {
            if (DateTimeUtils.utcCal.isNone()) {
                DateTimeUtils.utcCal = <-- Missing mapping for java.util.GregorianCalendar constructor: GregorianCalendar -->(<-- No type mapping: java.util.TimeZone -->.<-- Missing mapping for java.util.TimeZone member: getTimeZone -->("UTC"))
            }
        }
    }

    /***
     * 返回全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数
     *
     * @param y
     * @param m
     * @param d
     * @param hh
     * @param mm
     * @param ss
     * @return

        public static synchronized long UTC(int y, int m, int d, int hh, int mm, int ss) {
            DateTimeUtils.makeUTCCalendar();
            synchronized (utcCal) {
                utcCal.clear();
                utcCal.set(y, m, d, hh, mm, ss);
                return utcCal.getTimeInMillis();
            }
        }*/

    /***
     * 取 Date 对象中用全球标准时间 (UTC) 表示的日期
     *
     * @param date
     * @return

        public static synchronized int getUTCDay(Date date) {
            DateTimeUtils.makeUTCCalendar();
            synchronized (utcCal) {
                utcCal.clear();
                utcCal.setTimeInMillis(date.getTime());
                return utcCal.get(Calendar.DAY_OF_MONTH);
            }
        }*/

    private static let j2cjGeneratedStaticLockObject = Monitor()
}