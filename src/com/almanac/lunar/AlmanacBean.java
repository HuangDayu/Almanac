package com.almanac.lunar;

import com.almanac.lunar.AlmanacBean;

public class AlmanacBean {
	
	/** 2000.0起算儒略日,北京时12:00 */
	private int julian_Days;
	/** 所在公历月内日序数 */
	private int gregorian_DayIndexForMonth;
	/** 所在公历年,同 */
	private int gregorian_Year;
	/** 所在公历月,同 */
	private int gregorian_Month;
	/** 日名称(公历) */
	private int gregorian_Day;
	/** 所在公历月的总天数 */
	private int gregorian_DaysOfMonth;
	/** 所在月的月首的星期,同 */
	private int gregorian_WeekFirstForMonth;
	/** 星期 */
	private int gregorian_Week;
	/** 在本月中的周序号 */
	private int gregorian_WeekIndexForMonth;
	/** 本月的总周数 */
	private int gregorian_WeeksOfMonth;
	/** 年(回历) */
	private int islamic_Year;
	/** 月(回历) */
	private int islamic_Month;
	/** 日(回历) */
	private int islamic_Day;
	/** 距农历月首的编移量,0对应初一 */
	private int lunar_MonthOffset;
	/** 农历月名称 */
	private String lunar_MonthName;
	/** 农历日名称 */
	private String lunar_DayName;
	/** 农历月大小 */
	private int lunar_Days_OfMonth;
	/** 农历闰状况(值为'闰'或空串) */
	private String lunar_Lunar_isLeap;
	/** 下个农历月名称,判断除夕时要用到 */
	private String lunar_NextLunarMonthName;
	/** 24节气 */
	private String lunar_SolarTerm;
	/** 距冬至的天数 */
	private int lunar_Last_Dongzhi;
	/** 距夏至的天数 */
	private int lunar_Last_Xiazhi;
	/** 距立秋的天数 */
	private int lunar_Last_Liqiu;
	/** 距芒种的天数 */
	private int lunar_Last_Mangzhong;
	/** 距小暑的天数 */
	private int lunar_Last_Xiaoshu;
	/** 干支年 */
	private String chineseEra_Year;
	/** 干支月 */
	private String chineseEra_Month;
	/** 干支日 */
	private String chineseEra_Day;
	/** 干支时 */
	private String chineseEra_Time;
	/** 星座 */
	private String constellation;
	/** 农历年:农历纪年(10进制,1984年起算) */
	private int lunar_Period_Of_Years;
	/** 农历月 */
	private int lunar_Period_Of_Months;
	/** 月相名称 */
	private String moon_PhaseName;
	/** 月相时刻(儒略日) */
	private double moon_PhaseTime;
	/** 月相时间串 */
	private String moon_PhaseTimeStr;
	/** 节气名称 */
	private String lunar_SolarTerm_Name;
	/** 节气时刻(儒略日) */
	private double lunar_SolarTerm_Time;
	/** 节气时间串 */
	private String lunar_SolarTerm_Time_Str;
	/** 黄帝纪年 */
	private int lunar_king_Years;
	/** 重要喜庆日子名称(可将日子名称置红) */
	private String happyDay_Name;
	/** 重要日子名称 */
	private String majorDay_Name;
	/** 各种日子名称(连成一大串) */
	private String allDay_Name;
	/** 放假日子(可用于日期数字置红) */
	private int holiday_INT;
	/** 该年对应的生肖 */
	private String lunarDate_Animal_Year;
	/** 干支纪年 */
	private String LunarDate_China_Era_Year;
	/** 年号纪年 */
	private String LunarDate_China_Era_Year_Number;
	/** 日出 */
	private String sunRise;
	/** 日落 */
	private String sunSet;
	/** 中天 */
	private String midTime;
	/** 天亮 */
	private String dawnTime;
	/** 天黑 */
	private String nightTime;
	/** 昼长 */
	private String dayTime;
	/** 夜长 */
	private String eveningTime;
	/** 月出 */
	private String moonRise;
	/** 月落 */
	private String moonSet;
	/** 月中 */
	private String moonMiddleTime;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	public int getJulian_Days() {
		return julian_Days;
	}
	public void setJulian_Days(int julian_Days) {
		this.julian_Days = julian_Days;
	}
	public int getGregorian_DayIndexForMonth() {
		return gregorian_DayIndexForMonth;
	}
	public void setGregorian_DayIndexForMonth(int gregorian_DayIndexForMonth) {
		this.gregorian_DayIndexForMonth = gregorian_DayIndexForMonth;
	}
	public int getGregorian_Year() {
		return gregorian_Year;
	}
	public void setGregorian_Year(int gregorian_Year) {
		this.gregorian_Year = gregorian_Year;
	}
	public int getGregorian_Month() {
		return gregorian_Month;
	}
	public void setGregorian_Month(int gregorian_Month) {
		this.gregorian_Month = gregorian_Month;
	}
	public int getGregorian_Day() {
		return gregorian_Day;
	}
	public void setGregorian_Day(int gregorian_Day) {
		this.gregorian_Day = gregorian_Day;
	}
	public int getGregorian_DaysOfMonth() {
		return gregorian_DaysOfMonth;
	}
	public void setGregorian_DaysOfMonth(int gregorian_DaysOfMonth) {
		this.gregorian_DaysOfMonth = gregorian_DaysOfMonth;
	}
	public int getGregorian_WeekFirstForMonth() {
		return gregorian_WeekFirstForMonth;
	}
	public void setGregorian_WeekFirstForMonth(int gregorian_WeekFirstForMonth) {
		this.gregorian_WeekFirstForMonth = gregorian_WeekFirstForMonth;
	}
	public int getGregorian_Week() {
		return gregorian_Week;
	}
	public void setGregorian_Week(int gregorian_Week) {
		this.gregorian_Week = gregorian_Week;
	}
	public int getGregorian_WeekIndexForMonth() {
		return gregorian_WeekIndexForMonth;
	}
	public void setGregorian_WeekIndexForMonth(int gregorian_WeekIndexForMonth) {
		this.gregorian_WeekIndexForMonth = gregorian_WeekIndexForMonth;
	}
	public int getGregorian_WeeksOfMonth() {
		return gregorian_WeeksOfMonth;
	}
	public void setGregorian_WeeksOfMonth(int gregorian_WeeksOfMonth) {
		this.gregorian_WeeksOfMonth = gregorian_WeeksOfMonth;
	}
	public int getIslamic_Year() {
		return islamic_Year;
	}
	public void setIslamic_Year(int islamic_Year) {
		this.islamic_Year = islamic_Year;
	}
	public int getIslamic_Month() {
		return islamic_Month;
	}
	public void setIslamic_Month(int islamic_Month) {
		this.islamic_Month = islamic_Month;
	}
	public int getIslamic_Day() {
		return islamic_Day;
	}
	public void setIslamic_Day(int islamic_Day) {
		this.islamic_Day = islamic_Day;
	}
	public int getLunar_MonthOffset() {
		return lunar_MonthOffset;
	}
	public void setLunar_MonthOffset(int lunar_MonthOffset) {
		this.lunar_MonthOffset = lunar_MonthOffset;
	}
	public String getLunar_MonthName() {
		return lunar_MonthName;
	}
	public void setLunar_MonthName(String lunar_MonthName) {
		this.lunar_MonthName = lunar_MonthName;
	}
	public String getLunar_DayName() {
		return lunar_DayName;
	}
	public void setLunar_DayName(String lunar_DayName) {
		this.lunar_DayName = lunar_DayName;
	}
	public int getLunar_Days_OfMonth() {
		return lunar_Days_OfMonth;
	}
	public void setLunar_Days_OfMonth(int lunar_Days_OfMonth) {
		this.lunar_Days_OfMonth = lunar_Days_OfMonth;
	}
	public String getLunar_Lunar_isLeap() {
		return lunar_Lunar_isLeap;
	}
	public void setLunar_Lunar_isLeap(String lunar_Lunar_isLeap) {
		this.lunar_Lunar_isLeap = lunar_Lunar_isLeap;
	}
	public String getLunar_NextLunarMonthName() {
		return lunar_NextLunarMonthName;
	}
	public void setLunar_NextLunarMonthName(String lunar_NextLunarMonthName) {
		this.lunar_NextLunarMonthName = lunar_NextLunarMonthName;
	}
	public String getLunar_SolarTerm() {
		return lunar_SolarTerm;
	}
	public void setLunar_SolarTerm(String lunar_SolarTerm) {
		this.lunar_SolarTerm = lunar_SolarTerm;
	}
	public int getLunar_Last_Dongzhi() {
		return lunar_Last_Dongzhi;
	}
	public void setLunar_Last_Dongzhi(int lunar_Last_Dongzhi) {
		this.lunar_Last_Dongzhi = lunar_Last_Dongzhi;
	}
	public int getLunar_Last_Xiazhi() {
		return lunar_Last_Xiazhi;
	}
	public void setLunar_Last_Xiazhi(int lunar_Last_Xiazhi) {
		this.lunar_Last_Xiazhi = lunar_Last_Xiazhi;
	}
	public int getLunar_Last_Liqiu() {
		return lunar_Last_Liqiu;
	}
	public void setLunar_Last_Liqiu(int lunar_Last_Liqiu) {
		this.lunar_Last_Liqiu = lunar_Last_Liqiu;
	}
	public int getLunar_Last_Mangzhong() {
		return lunar_Last_Mangzhong;
	}
	public void setLunar_Last_Mangzhong(int lunar_Last_Mangzhong) {
		this.lunar_Last_Mangzhong = lunar_Last_Mangzhong;
	}
	public int getLunar_Last_Xiaoshu() {
		return lunar_Last_Xiaoshu;
	}
	public void setLunar_Last_Xiaoshu(int lunar_Last_Xiaoshu) {
		this.lunar_Last_Xiaoshu = lunar_Last_Xiaoshu;
	}
	public String getChineseEra_Year() {
		return chineseEra_Year;
	}
	public void setChineseEra_Year(String chineseEra_Year) {
		this.chineseEra_Year = chineseEra_Year;
	}
	public String getChineseEra_Month() {
		return chineseEra_Month;
	}
	public void setChineseEra_Month(String chineseEra_Month) {
		this.chineseEra_Month = chineseEra_Month;
	}
	public String getChineseEra_Day() {
		return chineseEra_Day;
	}
	public void setChineseEra_Day(String chineseEra_Day) {
		this.chineseEra_Day = chineseEra_Day;
	}
	public String getChineseEra_Time() {
		return chineseEra_Time;
	}
	public void setChineseEra_Time(String chineseEra_Time) {
		this.chineseEra_Time = chineseEra_Time;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public int getLunar_Period_Of_Years() {
		return lunar_Period_Of_Years;
	}
	public void setLunar_Period_Of_Years(int lunar_Period_Of_Years) {
		this.lunar_Period_Of_Years = lunar_Period_Of_Years;
	}
	public int getLunar_Period_Of_Months() {
		return lunar_Period_Of_Months;
	}
	public void setLunar_Period_Of_Months(int lunar_Period_Of_Months) {
		this.lunar_Period_Of_Months = lunar_Period_Of_Months;
	}
	public String getMoon_PhaseName() {
		return moon_PhaseName;
	}
	public void setMoon_PhaseName(String moon_PhaseName) {
		this.moon_PhaseName = moon_PhaseName;
	}
	public double getMoon_PhaseTime() {
		return moon_PhaseTime;
	}
	public void setMoon_PhaseTime(double moon_PhaseTime) {
		this.moon_PhaseTime = moon_PhaseTime;
	}
	public String getMoon_PhaseTimeStr() {
		return moon_PhaseTimeStr;
	}
	public void setMoon_PhaseTimeStr(String moon_PhaseTimeStr) {
		this.moon_PhaseTimeStr = moon_PhaseTimeStr;
	}
	public String getLunar_SolarTerm_Name() {
		return lunar_SolarTerm_Name;
	}
	public void setLunar_SolarTerm_Name(String lunar_SolarTerm_Name) {
		this.lunar_SolarTerm_Name = lunar_SolarTerm_Name;
	}
	public double getLunar_SolarTerm_Time() {
		return lunar_SolarTerm_Time;
	}
	public void setLunar_SolarTerm_Time(double lunar_SolarTerm_Time) {
		this.lunar_SolarTerm_Time = lunar_SolarTerm_Time;
	}
	public String getLunar_SolarTerm_Time_Str() {
		return lunar_SolarTerm_Time_Str;
	}
	public void setLunar_SolarTerm_Time_Str(String lunar_SolarTerm_Time_Str) {
		this.lunar_SolarTerm_Time_Str = lunar_SolarTerm_Time_Str;
	}
	public int getLunar_king_Years() {
		return lunar_king_Years;
	}
	public void setLunar_king_Years(int lunar_king_Years) {
		this.lunar_king_Years = lunar_king_Years;
	}
	public String getHappyDay_Name() {
		return happyDay_Name;
	}
	public void setHappyDay_Name(String happyDay_Name) {
		this.happyDay_Name = happyDay_Name;
	}
	public String getMajorDay_Name() {
		return majorDay_Name;
	}
	public void setMajorDay_Name(String majorDay_Name) {
		this.majorDay_Name = majorDay_Name;
	}
	public String getAllDay_Name() {
		return allDay_Name;
	}
	public void setAllDay_Name(String allDay_Name) {
		this.allDay_Name = allDay_Name;
	}
	public int getHoliday_INT() {
		return holiday_INT;
	}
	public void setHoliday_INT(int holiday_INT) {
		this.holiday_INT = holiday_INT;
	}
	public String getLunarDate_Animal_Year() {
		return lunarDate_Animal_Year;
	}
	public void setLunarDate_Animal_Year(String lunarDate_Animal_Year) {
		this.lunarDate_Animal_Year = lunarDate_Animal_Year;
	}
	public String getLunarDate_China_Era_Year() {
		return LunarDate_China_Era_Year;
	}
	public void setLunarDate_China_Era_Year(String lunarDate_China_Era_Year) {
		LunarDate_China_Era_Year = lunarDate_China_Era_Year;
	}
	public String getLunarDate_China_Era_Year_Number() {
		return LunarDate_China_Era_Year_Number;
	}
	public void setLunarDate_China_Era_Year_Number(String lunarDate_China_Era_Year_Number) {
		LunarDate_China_Era_Year_Number = lunarDate_China_Era_Year_Number;
	}
	public String getSunRise() {
		return sunRise;
	}
	public void setSunRise(String sunRise) {
		this.sunRise = sunRise;
	}
	public String getSunSet() {
		return sunSet;
	}
	public void setSunSet(String sunSet) {
		this.sunSet = sunSet;
	}
	public String getMidTime() {
		return midTime;
	}
	public void setMidTime(String midTime) {
		this.midTime = midTime;
	}
	public String getDawnTime() {
		return dawnTime;
	}
	public void setDawnTime(String dawnTime) {
		this.dawnTime = dawnTime;
	}
	public String getNightTime() {
		return nightTime;
	}
	public void setNightTime(String nightTime) {
		this.nightTime = nightTime;
	}
	public String getDayTime() {
		return dayTime;
	}
	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}
	public String getEveningTime() {
		return eveningTime;
	}
	public void setEveningTime(String eveningTime) {
		this.eveningTime = eveningTime;
	}
	public String getMoonRise() {
		return moonRise;
	}
	public void setMoonRise(String moonRise) {
		this.moonRise = moonRise;
	}
	public String getMoonSet() {
		return moonSet;
	}
	public void setMoonSet(String moonSet) {
		this.moonSet = moonSet;
	}
	public String getMoonMiddleTime() {
		return moonMiddleTime;
	}
	public void setMoonMiddleTime(String moonMiddleTime) {
		this.moonMiddleTime = moonMiddleTime;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
