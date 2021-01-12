package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.PortUtils;
import cn.huangdayu.almanac.utils.SolarTermUtils;

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacDTO {

    /**
     * 2000.0起算儒略日,北京时12:00
     */
    private int julianDays;
    /**
     * 所在公历月内日序数
     */
    private int gregorianDayIndexForMonth;
    /**
     * 所在公历年,同
     */
    private int gregorianYear;
    /**
     * 所在公历月,同
     */
    private int gregorianMonth;
    /**
     * 日名称(公历)
     */
    private int gregorianDay;
    /**
     * 所在公历月的总天数
     */
    private int gregorianDaysOfMonth;
    /**
     * 所在月的月首的星期,同
     */
    private int gregorianWeekFirstForMonth;
    /**
     * 星期
     */
    private int gregorianWeek;
    /**
     * 在本月中的周序号
     */
    private int gregorianWeekIndexForMonth;
    /**
     * 本月的总周数
     */
    private int gregorianWeeksOfMonth;
    /**
     * 年(回历)
     */
    private int islamicYear;
    /**
     * 月(回历)
     */
    private int islamicMonth;
    /**
     * 日(回历)
     */
    private int islamicDay;
    /**
     * 距农历月首的编移量,0对应初一
     */
    private int lunarMonthOffset;
    /**
     * 农历月名称
     */
    private String lunarMonthName;
    /**
     * 农历日名称
     */
    private String lunarDayName;
    /**
     * 农历月大小
     */
    private int lunarDaysOfMonth;
    /**
     * 农历闰状况(值为'闰'或空串)
     */
    private String lunarLeapStr;
    /**
     * 是否闰年
     */
    private Boolean leapYear;
    /**
     * 下个农历月名称,判断除夕时要用到
     */
    private String lunarNextLunarMonthName;
    /**
     * 24节气
     */
    private String lunarSolarTerm;
    /**
     * 距冬至的天数
     */
    private int lunarLastDZ;
    /**
     * 距夏至的天数
     */
    private int lunarLastXZ;
    /**
     * 距立秋的天数
     */
    private int lunarLastLQ;
    /**
     * 距芒种的天数
     */
    private int lunarLastMZ;
    /**
     * 距小暑的天数
     */
    private int lunarLastXS;
    /**
     * 干支年
     */
    private String chineseEraYear;
    /**
     * 干支月
     */
    private String chineseEraMonth;
    /**
     * 干支日
     */
    private String chineseEraDay;
    /**
     * 干支时
     */
    private String chineseEraTime;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 农历年:农历纪年(10进制,1984年起算)
     */
    private int lunarPeriodOfYears;
    /**
     * 农历月
     */
    private int lunarPeriodOfMonths;
    /**
     * 月相名称
     */
    private String moonPhaseName;
    /**
     * 月相时刻(儒略日)
     */
    private double moonPhaseTime;
    /**
     * 月相时间串
     */
    private String moonPhaseTimeStr;
    /**
     * 节气名称
     */
    private String lunarSolarTermName;
    /**
     * 节气时刻(儒略日)
     */
    private double lunarSolarTermTime;
    /**
     * 节气时间串
     */
    private String lunarSolarTermTimeStr;
    /**
     * 黄帝纪年
     */
    private int kingChronology;
    /**
     * 重要喜庆日子名称(可将日子名称置红)
     */
    private String happyDayName;
    /**
     * 重要日子名称
     */
    private String majorDayName;
    /**
     * 各种日子名称(连成一大串)
     */
    private String allDayName;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private int holidayDayIndex;
    /**
     * 该年对应的生肖
     */
    private String zodiac;
    /**
     * 干支纪年
     */
    private String lunarDateChinaEraYear;
    /**
     * 年号纪年
     */
    private String lunarDateChinaEraYearNumber;
    /**
     * 日出
     */
    private String sunRiseTime;
    /**
     * 日落
     */
    private String sunSetTime;
    /**
     * 中天
     */
    private String midDayTime;
    /**
     * 天亮
     */
    private String dawnTime;
    /**
     * 天黑
     */
    private String darkTime;
    /**
     * 昼长
     */
    private String diurnalTime;
    /**
     * 夜长
     */
    private String nightTime;
    /**
     * 月出
     */
    private String moonRiseTime;
    /**
     * 月落
     */
    private String moonSetTime;
    /**
     * 月中
     */
    private String moonMiddleTime;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

    /**
     * 港口
     */
    private String protName;

    /**
     * 儒略日
     */
    private int julianDay;

    /**
     * 农历时间
     */
    private String lunarTime;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 位置
     */
    private String position;
    /**
     * 星期
     */
    private String week;
    /**
     * 公历（中文）
     */
    private String westernCalendarCN;
    /**
     * 西历
     */
    private String westernCalendar;
    /**
     * 格式化时间
     */
    private String timeFormer;
    /**
     * 时间
     */
    private String time;
    /**
     * 格式化日期
     */
    private String dateFormer;
    /**
     * 日期
     */
    private String date;

    public int getJulianDays() {
        return julianDays;
    }

    public void setJulianDays(int julianDays) {
        this.julianDays = julianDays;
    }

    public int getGregorianDayIndexForMonth() {
        return gregorianDayIndexForMonth;
    }

    public void setGregorianDayIndexForMonth(int gregorianDayIndexForMonth) {
        this.gregorianDayIndexForMonth = gregorianDayIndexForMonth;
    }

    public int getGregorianYear() {
        return gregorianYear;
    }

    public void setGregorianYear(int gregorianYear) {
        this.gregorianYear = gregorianYear;
    }

    public int getGregorianMonth() {
        return gregorianMonth;
    }

    public void setGregorianMonth(int gregorianMonth) {
        this.gregorianMonth = gregorianMonth;
    }

    public int getGregorianDay() {
        return gregorianDay;
    }

    public void setGregorianDay(int gregorianDay) {
        this.gregorianDay = gregorianDay;
    }

    public int getGregorianDaysOfMonth() {
        return gregorianDaysOfMonth;
    }

    public void setGregorianDaysOfMonth(int gregorianDaysOfMonth) {
        this.gregorianDaysOfMonth = gregorianDaysOfMonth;
    }

    public int getGregorianWeekFirstForMonth() {
        return gregorianWeekFirstForMonth;
    }

    public void setGregorianWeekFirstForMonth(int gregorianWeekFirstForMonth) {
        this.gregorianWeekFirstForMonth = gregorianWeekFirstForMonth;
    }

    public int getGregorianWeek() {
        return gregorianWeek;
    }

    public void setGregorianWeek(int gregorianWeek) {
        this.gregorianWeek = gregorianWeek;
    }

    public int getGregorianWeekIndexForMonth() {
        return gregorianWeekIndexForMonth;
    }

    public void setGregorianWeekIndexForMonth(int gregorianWeekIndexForMonth) {
        this.gregorianWeekIndexForMonth = gregorianWeekIndexForMonth;
    }

    public int getGregorianWeeksOfMonth() {
        return gregorianWeeksOfMonth;
    }

    public void setGregorianWeeksOfMonth(int gregorianWeeksOfMonth) {
        this.gregorianWeeksOfMonth = gregorianWeeksOfMonth;
    }


    public void setIslamicYear(int islamicYear) {
        this.islamicYear = islamicYear;
    }


    public void setIslamicMonth(int islamicMonth) {
        this.islamicMonth = islamicMonth;
    }


    public void setIslamicDay(int islamicDay) {
        this.islamicDay = islamicDay;
    }

    public int getLunarMonthOffset() {
        return lunarMonthOffset;
    }

    public void setLunarMonthOffset(int lunarMonthOffset) {
        this.lunarMonthOffset = lunarMonthOffset;
    }

    public String getLunarMonthName() {
        return lunarMonthName;
    }

    public void setLunarMonthName(String lunarMonthName) {
        this.lunarMonthName = lunarMonthName;
    }

    public String getLunarDayName() {
        return lunarDayName;
    }

    public void setLunarDayName(String lunarDayName) {
        this.lunarDayName = lunarDayName;
    }

    public int getLunarDaysOfMonth() {
        return lunarDaysOfMonth;
    }

    public void setLunarDaysOfMonth(int lunarDaysOfMonth) {
        this.lunarDaysOfMonth = lunarDaysOfMonth;
    }

    public String getLunarLeapStr() {
        return lunarLeapStr;
    }

    public void setLunarLeapStr(String lunarLeapStr) {
        this.lunarLeapStr = lunarLeapStr;
    }

    public String getLunarNextLunarMonthName() {
        return lunarNextLunarMonthName;
    }

    public void setLunarNextLunarMonthName(String lunarNextLunarMonthName) {
        this.lunarNextLunarMonthName = lunarNextLunarMonthName;
    }

    public String getLunarSolarTerm() {
        return lunarSolarTerm;
    }

    public void setLunarSolarTerm(String lunarSolarTerm) {
        this.lunarSolarTerm = lunarSolarTerm;
    }

    public int getLunarLastDZ() {
        return lunarLastDZ;
    }

    public void setLunarLastDZ(int lunarLastDZ) {
        this.lunarLastDZ = lunarLastDZ;
    }

    public int getLunarLastXZ() {
        return lunarLastXZ;
    }

    public void setLunarLastXZ(int lunarLastXZ) {
        this.lunarLastXZ = lunarLastXZ;
    }

    public int getLunarLastLQ() {
        return lunarLastLQ;
    }

    public void setLunarLastLQ(int lunarLastLQ) {
        this.lunarLastLQ = lunarLastLQ;
    }

    public int getLunarLastMZ() {
        return lunarLastMZ;
    }

    public void setLunarLastMZ(int lunarLastMZ) {
        this.lunarLastMZ = lunarLastMZ;
    }

    public int getLunarLastXS() {
        return lunarLastXS;
    }

    public void setLunarLastXS(int lunarLastXS) {
        this.lunarLastXS = lunarLastXS;
    }


    public void setChineseEraYear(String chineseEraYear) {
        this.chineseEraYear = chineseEraYear;
    }


    public void setChineseEraMonth(String chineseEraMonth) {
        this.chineseEraMonth = chineseEraMonth;
    }


    public void setChineseEraDay(String chineseEraDay) {
        this.chineseEraDay = chineseEraDay;
    }


    public void setChineseEraTime(String chineseEraTime) {
        this.chineseEraTime = chineseEraTime;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public int getLunarPeriodOfYears() {
        return lunarPeriodOfYears;
    }

    public void setLunarPeriodOfYears(int lunarPeriodOfYears) {
        this.lunarPeriodOfYears = lunarPeriodOfYears;
    }

    public int getLunarPeriodOfMonths() {
        return lunarPeriodOfMonths;
    }

    public void setLunarPeriodOfMonths(int lunarPeriodOfMonths) {
        this.lunarPeriodOfMonths = lunarPeriodOfMonths;
    }

    public String getMoonPhaseName() {
        return moonPhaseName;
    }

    public void setMoonPhaseName(String moonPhaseName) {
        this.moonPhaseName = moonPhaseName;
    }

    public double getMoonPhaseTime() {
        return moonPhaseTime;
    }

    public void setMoonPhaseTime(double moonPhaseTime) {
        this.moonPhaseTime = moonPhaseTime;
    }

    public String getMoonPhaseTimeStr() {
        return moonPhaseTimeStr;
    }

    public void setMoonPhaseTimeStr(String moonPhaseTimeStr) {
        this.moonPhaseTimeStr = moonPhaseTimeStr;
    }

    public String getLunarSolarTermName() {
        return lunarSolarTermName;
    }

    public void setLunarSolarTermName(String lunarSolarTermName) {
        this.lunarSolarTermName = lunarSolarTermName;
    }

    public double getLunarSolarTermTime() {
        return lunarSolarTermTime;
    }

    public void setLunarSolarTermTime(double lunarSolarTermTime) {
        this.lunarSolarTermTime = lunarSolarTermTime;
    }

    public String getLunarSolarTermTimeStr() {
        return lunarSolarTermTimeStr;
    }

    public void setLunarSolarTermTimeStr(String lunarSolarTermTimeStr) {
        this.lunarSolarTermTimeStr = lunarSolarTermTimeStr;
    }

    public int getKingChronology() {
        return kingChronology;
    }

    public void setKingChronology(int kingChronology) {
        this.kingChronology = kingChronology;
    }

    public String getHappyDayName() {
        return happyDayName;
    }

    public void setHappyDayName(String happyDayName) {
        this.happyDayName = happyDayName;
    }

    public String getMajorDayName() {
        return majorDayName;
    }

    public void setMajorDayName(String majorDayName) {
        this.majorDayName = majorDayName;
    }

    public String getAllDayName() {
        return allDayName;
    }

    public void setAllDayName(String allDayName) {
        this.allDayName = allDayName;
    }

    public int getHolidayDayIndex() {
        return holidayDayIndex;
    }

    public void setHolidayDayIndex(int holidayDayIndex) {
        this.holidayDayIndex = holidayDayIndex;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getLunarDateChinaEraYear() {
        return lunarDateChinaEraYear;
    }

    public void setLunarDateChinaEraYear(String lunarDateChinaEraYear) {
        this.lunarDateChinaEraYear = lunarDateChinaEraYear;
    }

    public String getLunarDateChinaEraYearNumber() {
        return lunarDateChinaEraYearNumber;
    }

    public void setLunarDateChinaEraYearNumber(String lunarDateChinaEraYearNumber) {
        this.lunarDateChinaEraYearNumber = lunarDateChinaEraYearNumber;
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = DateTimeUtils.getFormattingTime(sunRiseTime);
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = DateTimeUtils.getFormattingTime(sunSetTime);
    }


    public void setMidDayTime(String midDayTime) {
        this.midDayTime = DateTimeUtils.getFormattingTime(midDayTime);
    }


    public void setDawnTime(String dawnTime) {
        this.dawnTime = DateTimeUtils.getFormattingTime(dawnTime);
    }


    public void setDarkTime(String darkTime) {
        this.darkTime = DateTimeUtils.getFormattingTime(darkTime);
    }


    public void setDiurnalTime(String diurnalTime) {
        this.diurnalTime = DateTimeUtils.getFormattingTime(diurnalTime);
    }


    public void setNightTime(String nightTime) {
        this.nightTime = DateTimeUtils.getFormattingTime(nightTime);
    }

    public String getMoonRiseTime() {
        return moonRiseTime;
    }

    public void setMoonRiseTime(String moonRiseTime) {
        this.moonRiseTime = DateTimeUtils.getFormattingTime(moonRiseTime);
    }

    public String getMoonSetTime() {
        return moonSetTime;
    }

    public void setMoonSetTime(String moonSetTime) {
        this.moonSetTime = DateTimeUtils.getFormattingTime(moonSetTime);
    }

    public String getMoonMiddleTime() {
        return moonMiddleTime;
    }

    public void setMoonMiddleTime(String moonMiddleTime) {
        this.moonMiddleTime = DateTimeUtils.getFormattingTime(moonMiddleTime);
    }


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Boolean getLeapYear() {
        return leapYear;
    }

    public void setLeapYear(Boolean leapYear) {
        this.leapYear = leapYear;
    }

    public String getProtName() {
        return protName;
    }

    public void setProtName(String protName) {
        this.protName = protName;
    }

    public void setJulianDay(int julianDay) {
        this.julianDay = julianDay;
    }

    public int getJulianDay() {
        return julianDay;
    }

    public String getDate() {
        return this.date;
    }


    public String getDateFormer() {
        return this.dateFormer;
    }


    public String getTime() {
        return this.time;
    }


    public String getTimeFormer() {
        return this.timeFormer;
    }

    public void setTimeFormer(String timeFormer) {
        this.timeFormer = timeFormer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDateFormer(String dateFormer) {
        this.dateFormer = dateFormer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWesternCalendar() {
        return this.westernCalendar;
    }

    public void setWesternCalendar(String westernCalendar) {
        this.westernCalendar = westernCalendar;
    }

    public String getWesternCalendarCN() {
        return this.westernCalendarCN;
    }

    public void setWesternCalendarCN(String westernCalendarCN) {
        this.westernCalendarCN = westernCalendarCN;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return this.week;
    }


    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMidDayTime() {
        return midDayTime;
    }

    public String getDawnTime() {
        return dawnTime;
    }

    public String getDarkTime() {
        return darkTime;
    }

    public String getDiurnalTime() {
        return diurnalTime;
    }

    public String getNightTime() {
        return nightTime;
    }

    public String getPortName() {
        return PortUtils.getProtName();
    }


    public String getChronology() {
        return "开元" + this.getKingChronology() + "年";
    }

    public String getZodiac() {
        return zodiac;
    }

    public String getLunarMonth() {
        return this.getLunarMonthName() + "月";
    }

    public void setLunarTime(String lunarTime) {
        this.lunarTime = lunarTime;
    }

    public String getLunarTime() {
        return this.lunarTime;
    }

    public String getChineseEraYear() {
        return chineseEraYear;
    }

    public String getChineseEraMonth() {
        return chineseEraMonth;
    }

    public String getChineseEraDay() {
        return chineseEraDay;
    }

    public String getChineseEraTime() {
        return chineseEraTime;
    }

    public String getLunar() {
        return this.getChineseEraYear() + this.getZodiac() + "年" + this.getLunarMonth() + this.getLunarDayName();
    }


    public String getLunarDays() {
        return this.getLunarDaysOfMonth() + "天";
    }


    public String isLunarBigMonth() {
        if (!(this.getLunarDaysOfMonth() > 29)) {
            return "否";
        } else {
            return "是";
        }
    }


    public String isLeapMonth() {
        if (!"闰".equals(this.getLunarLeapStr())) {
            return "否";
        } else {
            return "是";
        }
    }


    public String isLeapYear() {
        if (this.getLeapYear()) {
            return "是";
        } else {
            return "否";
        }
    }

    public int getIslamicYear() {
        return islamicYear;
    }


    public int getIslamicMonth() {
        return islamicMonth;
    }

    public int getIslamicDay() {
        return islamicDay;
    }

    public String getIslamic() {
        return this.getIslamicYear() + "年" + this.getIslamicMonth() + "月" + this.getIslamicDay() + "日";
    }

    public String getConstellation() {
        return constellation;
    }

    public String getTianGan() {
        String tiangan = getBaZi();
        char c1 = tiangan.charAt(0);
        char c2 = tiangan.charAt(2);
        char c3 = tiangan.charAt(4);
        char c4 = tiangan.charAt(6);
        return String.valueOf(c1) + c2 + c3 + c4;
    }


    public String getDiZhi() {
        String tiangan = getBaZi();
        char c1 = tiangan.charAt(1);
        char c2 = tiangan.charAt(3);
        char c3 = tiangan.charAt(5);
        char c4 = tiangan.charAt(7);
        return String.valueOf(c1) + c2 + c3 + c4;
    }


    public String getBaZi() {
        return this.getChineseEraYear() + this.getChineseEraMonth() + this.getChineseEraDay()
                + this.getChineseEraTime();
    }


    public String getHuangLi() {
        return this.getChineseEraYear() + "年" + this.getChineseEraMonth() + "月" + this.getChineseEraDay() + "日"
                + this.getChineseEraTime() + "时";
    }


    public String getYearNumber() {
        return this.getLunarDateChinaEraYearNumber();
    }


    public String getHolidayVacations() {
        String str = this.getAllDayName();
        if (str == "") {
            return "无";
        } else if (str == null) {
            return "无";
        } else {
            return str;
        }
    }


    public String getMoonPhase() {
        String str = this.getMoonPhaseName();
        if (str == "") {
            return "无";
        } else if (str == null) {
            return "无";
        } else {
            return str + " " + this.getMoonPhaseTimeStr();
        }
    }


    public String getNextSolarTerm() {
        return SolarTermUtils.getNextSolarTerm(this);
    }


    public String getSolarTerm(String solarTerm) {
        return SolarTermUtils.getSolarTermDate(this, solarTerm);
    }


    public String[] getAllSolarTerm() {
        return SolarTermUtils.getAllSolarTerm(this.getGregorianYear());
    }


    public String getSolarTermDoc(String solarTerm) {
        return SolarTermUtils.getSolarTermsMap().get(solarTerm);
    }

}
