package almanac.cn.huangdayu.almanac.dto

import almanac.cn.huangdayu.almanac.aggregates.julian.Julian
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import almanac.cn.huangdayu.almanac.utils.ConstantsUtils
import almanac.cn.huangdayu.almanac.utils.DateTimeUtils
import almanac.cn.huangdayu.almanac.utils.PropertiesUtils
import almanac.cn.huangdayu.almanac.utils.CoordinatesUtils
import j2cjlib.utils.J2CjDateTimeExtension
import j2cjlib.utils.castOrThrow
import j2cjlib.utils.parse
import UNSUPPORTED.java.time.Instant
import UNSUPPORTED.java.util.Calendar
import UNSUPPORTED.java.util.GregorianCalendar
import std.time.DateTime


/**
 * 时区模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneDTO {

    /**
     * 是否公元前
     */
    private var era: Int32 = 0

    /**
     * 时区下标
     */
    private var index: Float64 = 0.0
    private var year: Int32 = 0
    private var month: Int32 = 0
    private var day: Int32 = 0
    private var hour: Int32 = 0
    private var minute: Int32 = 0
    private var second: Int32 = 0
    /**
     * 星期
     * TODO 或许可以考虑用上面的week变量，如果是周日是一周的第一天，那么久需要减1
     */
    private var week: Int32 = 0
    /**
     * 时区
     */
    private var timeZone: ?String = None
    /**
     * 省份
     */
    private var province: ?String = None
    /**
     * 市/县/区
     */
    private var area: ?String = None
    /**
     * 位置
     */
    private var address: ?String = None
    /***
     * 纬度
     */
    private var latitudeValue: Float64 = 0.0
    /***
     * 经度
     */
    private var longitudeValue: Float64 = 0.0

    /**
     * 经度
     */
    private var longitude: ?String = None
    /**
     * 纬度
     */
    private var latitude: ?String = None
    /**
     * 港口
     */
    private var portName: ?String = None
    /**
     * 位置
     */
    private var position: ?String = None

    /**
     * FIXME 2021-01-23 因使用Calendar对象，导致时间无法进入公元前，改用GregorianCalendar对象，ERA表示公元前标志
     */
    private var gregorianCalendar: ?<-- No type mapping: java.util.GregorianCalendar --> = None

    /**
     * 公历月内日序数
     */
    private var dayIndexOfMonth: Int32 = 0
    /**
     * 公历月天数
     */
    private var daysOfMonth: Int32 = 0
    /**
     * 本月的总周数
     */
    private var weeksOfMonth: Int32 = 0
    /**
     * 月首的星期
     */
    private var weekFirstOfMonth: Int32 = 0
    /**
     * 当前日的星期
     */
    private var weekOfCurrentDay: Int32 = 0
    /**
     * 本日所在的周序号
     */
    private var weekIndexOfMonth: Int32 = 0


    private init() { }

    public init(province: ?String, area: ?String, dateTime: ?String) {
        this(province, area, DateTimeUtils.toDate(dateTime))
    }

    public init(province: ?String, area: ?String, instant: ?<-- No type mapping: java.time.Instant -->) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(instant.<-- Missing mapping for java.time.Instant member: toEpochMilli -->()))
    }

    public init(province: ?String, area: ?String, calendar: ?<-- No type mapping: java.util.Calendar -->) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(calendar.<-- Missing mapping for java.util.Calendar member: getTimeInMillis -->()))
    }

    public init(province: ?String, area: ?String, date: ?DateTime) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(date.getOrThrow().getTime()))
    }

    public init(province: ?String, area: ?String, currentTimeMillis: Int64) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(currentTimeMillis))
    }

    public init(province: ?String, area: ?String, year: Int32, month: Int32, day: Int32, hourOfDay: Int32, minute: Int32, second: Int32) {
        this(province, area, <-- Missing mapping for java.util.GregorianCalendar constructor: GregorianCalendar -->(year, month - 1i32, day, hourOfDay, minute, second))
    }

    /**
     * 指定月
     *
     * @param timeZoneDTO
     */
    public init(timeZoneDTO: ?TimeZoneDTO) {
        this(timeZoneDTO.getOrThrow().getProvince(), timeZoneDTO.getOrThrow().getArea(), <-- Missing mapping for java.util.GregorianCalendar constructor: GregorianCalendar -->(timeZoneDTO.getOrThrow().getEraYear(), timeZoneDTO.getOrThrow().getMonth() - 1i32, timeZoneDTO.getOrThrow().getDay(), timeZoneDTO.getOrThrow().getHour(), timeZoneDTO.getOrThrow().getMinute(), timeZoneDTO.getOrThrow().getSecond()))
    }

    public init(province: ?String, area: ?String, gregorianCalendar: ?<-- No type mapping: java.util.GregorianCalendar -->) {
        this.gregorianCalendar = gregorianCalendar
        this.year = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->)
        this.month = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->) + 1i32
        this.day = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->)
        this.week = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_WEEK -->)
        this.hour = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: HOUR_OF_DAY -->)
        this.minute = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MINUTE -->)
        this.second = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: SECOND -->)
        this.era = gregorianCalendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: ERA -->)
        this.index = Float64(DateTimeUtils.getTimZoneInt(castOrThrow<<-- No type mapping: java.util.GregorianCalendar -->, <-- No type mapping: java.util.Calendar -->>(gregorianCalendar)))
        this.timeZone = TimeZoneUtils.getTimeZone(gregorianCalendar)
        this.province = province
        this.area = area
        this.address = TimeZoneUtils.judgeArea(this.province, this.area).getOrThrow()[1]
        let jwd = TimeZoneUtils.decodeJWD(this.address)
        this.latitudeValue = (parse<Float64>(jwd.getOrThrow()[0..2]) + parse<Float64>(jwd.getOrThrow()[2..4]) / 60.0)
        this.longitudeValue = (parse<Float64>(jwd.getOrThrow()[4..7]) + parse<Float64>(jwd.getOrThrow()[7..]) / 60.0)
        this.portName = TimeZoneUtils.getPortName(PropertiesUtils.getLatitude(), PropertiesUtils.getLongitude(), this.latitudeValue, this.longitudeValue)
        this.position = (Some((province.<-- Missing mapping for java.lang.String member: replaceAll -->("省", "") ?? "null") + " " + (area.<-- Missing mapping for java.lang.String member: replaceAll -->("市", "").<-- Missing mapping for java.lang.String member: replaceAll -->("区", "").<-- Missing mapping for java.lang.String member: replaceAll -->("县", "").<-- Missing mapping for java.lang.String member: replaceAll -->("镇", "").<-- Missing mapping for java.lang.String member: replaceAll -->("乡", "") ?? "null")))
        this.longitude = CommonUtils.setStringPointDouble(this.longitudeValue, true)
        this.latitude = CommonUtils.setStringPointDouble(this.latitudeValue, false)
    }

    public func nextDay(day: Int32, julian: ?Julian): ?TimeZoneDTO {
        this.setDay(day + 1i32)
        let timeZoneDTO = TimeZoneDTO(this)
        // 公历月内日序数
        timeZoneDTO.setDayIndexOfMonth(day)
        // 公历月天数
        timeZoneDTO.setDaysOfMonth(julian.getOrThrow().getNumberDayOfMonth())
        // 月首的星期
        timeZoneDTO.setWeekFirstOfMonth(julian.getOrThrow().getWeekFirstDayOfMonth())
        // 当前日的星期
        timeZoneDTO.setWeekOfCurrentDay((julian.getOrThrow().getWeekFirstDayOfMonth() + day) % 7i32)
        // 本日所在的周序号
        timeZoneDTO.setWeekIndexOfMonth(((julian.getOrThrow().getWeekFirstDayOfMonth() + day) / 7i32))
        // 本月的总周数
        timeZoneDTO.setWeeksOfMonth(((julian.getOrThrow().getWeekFirstDayOfMonth() + julian.getOrThrow().getNumberDayOfMonth() - 1i32) / 7i32) + 1i32)
        return timeZoneDTO
    }

    /**
     * FIXME 2021-02-18 当时间进入公元前时，取反计算会导致值-1,不知何因
     *
     * @return
     */
    public func getEraYear(): Int32 {
        return if (getEra() == 0i32) { -getYear() + 1i32 } else { getYear() }
    }

    public func getDateTimeInfo(): ?String {
        return getEraYear().toString() + "-" + (CommonUtils.fillZero(month) ?? "null") + "-" + (CommonUtils.fillZero(day) ?? "null") + " " + (CommonUtils.fillZero(hour) ?? "null") + ":" + (CommonUtils.fillZero(minute) ?? "null") + ":" + (CommonUtils.fillZero(second) ?? "null")
    }

    public func getDateInfo(): ?String {
        return getEraYear().toString() + "-" + (CommonUtils.fillZero(month) ?? "null") + "-" + (CommonUtils.fillZero(day) ?? "null")
    }

    public func getInfo(): ?String {
        return (if (era == 0i32) { "公元前" } else { "" }) + (getDateTimeInfo() ?? "null") + " " + (ConstantsUtils.WEEK_NAME[Int64(week)] ?? "null")
    }

    public func getEra(): Int32 {
        return era
    }

    public func setEra(era: Int32): Unit {
        this.era = era
    }

    public func getIndex(): Float64 {
        return index
    }

    public func setIndex(index: Float64): Unit {
        this.index = index
    }

    public func getYear(): Int32 {
        return year
    }

    public func setYear(year: Int32): Unit {
        this.year = year
    }

    public func getMonth(): Int32 {
        return month
    }

    public func setMonth(month: Int32): Unit {
        this.month = month
    }

    public func getDay(): Int32 {
        return day
    }

    public func setDay(day: Int32): Unit {
        this.day = day
    }

    public func getHour(): Int32 {
        return hour
    }

    public func setHour(hour: Int32): Unit {
        this.hour = hour
    }

    public func getMinute(): Int32 {
        return minute
    }

    public func setMinute(minute: Int32): Unit {
        this.minute = minute
    }

    public func getSecond(): Int32 {
        return second
    }

    public func setSecond(second: Int32): Unit {
        this.second = second
    }

    public func getWeek(): Int32 {
        return week
    }

    public func setWeek(week: Int32): Unit {
        this.week = week
    }

    public func getTimeZone(): ?String {
        return timeZone
    }

    public func setTimeZone(timeZone: ?String): Unit {
        this.timeZone = timeZone
    }

    public func getProvince(): ?String {
        return province
    }

    public func setProvince(province: ?String): Unit {
        this.province = province
    }

    public func getArea(): ?String {
        return area
    }

    public func setArea(area: ?String): Unit {
        this.area = area
    }

    public func getAddress(): ?String {
        return address
    }

    public func setAddress(address: ?String): Unit {
        this.address = address
    }

    public func getLatitudeValue(): Float64 {
        return latitudeValue
    }

    public func setLatitudeValue(latitudeValue: Float64): Unit {
        this.latitudeValue = latitudeValue
    }

    public func getLongitudeValue(): Float64 {
        return longitudeValue
    }

    public func setLongitudeValue(longitudeValue: Float64): Unit {
        this.longitudeValue = longitudeValue
    }

    public func getLongitude(): ?String {
        return longitude
    }

    public func setLongitude(longitude: ?String): Unit {
        this.longitude = longitude
    }

    public func getLatitude(): ?String {
        return latitude
    }

    public func setLatitude(latitude: ?String): Unit {
        this.latitude = latitude
    }

    public func getPortName(): ?String {
        return portName
    }

    public func setPortName(portName: ?String): Unit {
        this.portName = portName
    }

    public func getPosition(): ?String {
        return position
    }

    public func setPosition(position: ?String): Unit {
        this.position = position
    }

    public func getGregorianCalendar(): ?<-- No type mapping: java.util.GregorianCalendar --> {
        return gregorianCalendar
    }

    public func setGregorianCalendar(gregorianCalendar: ?<-- No type mapping: java.util.GregorianCalendar -->): Unit {
        this.gregorianCalendar = gregorianCalendar
    }

    public func getDayIndexOfMonth(): Int32 {
        return dayIndexOfMonth
    }

    public func setDayIndexOfMonth(dayIndexOfMonth: Int32): Unit {
        this.dayIndexOfMonth = dayIndexOfMonth
    }

    public func getDaysOfMonth(): Int32 {
        return daysOfMonth
    }

    public func setDaysOfMonth(daysOfMonth: Int32): Unit {
        this.daysOfMonth = daysOfMonth
    }

    public func getWeeksOfMonth(): Int32 {
        return weeksOfMonth
    }

    public func setWeeksOfMonth(weeksOfMonth: Int32): Unit {
        this.weeksOfMonth = weeksOfMonth
    }

    public func getWeekFirstOfMonth(): Int32 {
        return weekFirstOfMonth
    }

    public func setWeekFirstOfMonth(weekFirstOfMonth: Int32): Unit {
        this.weekFirstOfMonth = weekFirstOfMonth
    }

    public func getWeekOfCurrentDay(): Int32 {
        return weekOfCurrentDay
    }

    public func setWeekOfCurrentDay(weekOfCurrentDay: Int32): Unit {
        this.weekOfCurrentDay = weekOfCurrentDay
    }

    public func getWeekIndexOfMonth(): Int32 {
        return weekIndexOfMonth
    }

    public func setWeekIndexOfMonth(weekIndexOfMonth: Int32): Unit {
        this.weekIndexOfMonth = weekIndexOfMonth
    }
}