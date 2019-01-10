package com.almanac.lunar;

public interface Almanac {
	/**
	 * 日期
	 * 
	 * @return
	 */
	String getDate();

	/***
	 * 原格式日期
	 * 
	 * @return
	 */
	String getDateFormer();

	/***
	 * 时间
	 * 
	 * @return
	 */
	String getTime();

	/**
	 * 原格式时间
	 * 
	 * @return
	 */
	String getTimeFormer();

	/**
	 * 星期几,周几
	 * 
	 * @return
	 */
	String getWeek();
	
	/***
	 * 西历
	 * @return
	 */
	String getWesternCalendar();
	
	/***
	 * 西历中文版
	 * @return
	 */
	String getWesternCalendarCN();

	/**
	 * 位置
	 * 
	 * @return
	 */
	String getPosition();

	/**
	 * 经度
	 * 
	 * @return
	 */
	String getLongitude();

	/**
	 * 纬度
	 * 
	 * @return
	 */
	String getLatitude();

	/**
	 * 时区
	 * 
	 * @return
	 */
	String getTimeZone();

	/**
	 * 昼长
	 * 
	 * @return
	 */
	String getDiurnalTime();

	/**
	 * 夜长
	 * 
	 * @return
	 */
	String getNightTime();

	/**
	 * 天亮时间
	 * 
	 * @return
	 */
	String getDawnTime();

	/**
	 * 日出时间
	 * 
	 * @return
	 */
	String getSunriseTime();

	/***
	 * 中天时间
	 * 
	 * @return
	 */
	String getMidDayTime();

	/**
	 * 日落时间
	 * 
	 * @return
	 */
	String getSunsetTime();

	/**
	 * 天黑时间
	 * 
	 * @return
	 */
	String getDarkTime();

	/***
	 * 月出时间
	 * 
	 * @return
	 */
	String getMoonOutTime();

	/**
	 * 月中时间
	 * 
	 * @return
	 */
	String getMidMoonTime();

	/**
	 * 月落时间
	 * 
	 * @return
	 */
	String getMoonDownTime();

	/**
	 * 港口
	 * 
	 * @return
	 */
	String getPortName();

	/**
	 * 儒略日
	 * 
	 * @return
	 */
	String getJulianDay();

	/**
	 * 天干地支 年
	 * 
	 * @return
	 */
	String getChineseEraYear();

	/**
	 * 天干地支 月
	 * 
	 * @return
	 */
	String getChineseEraMonth();

	/**
	 * 天干地支 日
	 * 
	 * @return
	 */
	String getChineseEraDay();

	/**
	 * 天干地支 时
	 * 
	 * @return
	 */
	String getChineseEraTime();

	/**
	 * 皇帝纪年
	 * 
	 * @return
	 */
	String getChronology();

	/**
	 * 生肖
	 * 
	 * @return
	 */
	String getZodiac();

	/**
	 * 农历 年
	 * 
	 * @return
	 */
	String getLunarYear();

	/**
	 * 农历 月
	 * 
	 * @return
	 */
	String getLunarMonth();

	/**
	 * 农历 日
	 * 
	 * @return
	 */
	String getLunarDay();

	/**
	 * 农历 时[更]刻
	 * 
	 * @return
	 */
	String getLunarTime();

	/**
	 * 农历(年，月，日，时[更]，刻)
	 * 
	 * @return
	 */
	String getLunar();

	/**
	 * 农历 月天数
	 * 
	 * @return
	 */
	String getLunarDays();

	/**
	 * 是否是农历大月
	 * 
	 * @return
	 */
	String isLunarBigMonth();

	/**
	 * 是否是农历闰月
	 * 
	 * @return
	 */
	String isLeapMonth();

	/**
	 * 是否是农历闰年
	 * 
	 * @return
	 */
	String isLeapYear();

	/**
	 * 回历 年
	 * 
	 * @return
	 */
	int getIslamicYear();

	/**
	 * 回历 月
	 * 
	 * @return
	 */
	int getIslamicMonth();

	/**
	 * 回历 日
	 * 
	 * @return
	 */
	int getIslamicDay();

	/***
	 * 回历(伊斯兰历)
	 * 
	 * @return
	 */
	String getIslamic();

	/**
	 * 星座
	 * 
	 * @return
	 */
	String getConstellation();

	/**
	 * 天干
	 * 
	 * @return
	 */
	String getTianGan();

	/**
	 * 地支
	 * 
	 * @return
	 */
	String getDiZhi();

	/**
	 * 八字
	 * 
	 * @return
	 */
	String getBaZi();

	/**
	 * 黄历
	 * 
	 * @return 戊戌年辛酉月辛亥日乙未时
	 */
	String getHuangLi();

	/**
	 * 年号
	 * 
	 * @return
	 */
	String getYearNumber();

	/**
	 * 节假日
	 * 
	 * @return
	 */
	String getHolidayVacations();

	/**
	 * 月相
	 * 
	 * @return
	 */
	String getMoonPhase();

	/**
	 * 下一个节气
	 * 
	 * @return
	 */
	String getNextSolarTerm();

	/**
	 * 获取指定节气的日期
	 * 
	 * @param solarTerm
	 * @return
	 */
	String getSolarTerm(String solarTerm);

	/**
	 * 获取所有节气
	 * 
	 * @return
	 */
	String[] getAllSolarTerm();

	/**
	 * 获取节气的详细说明
	 * 
	 * @param solarTerm
	 * @return 节气说明
	 */
	String getSolarTermDoc(String solarTerm);
}
