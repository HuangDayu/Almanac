package com.almanac.lunar;

public interface Almanac {
	/**
	 * 日期
	 * 
	 * @return
	 */
	public String getDate();
	
	/***
	 * 原格式日期
	 * @return
	 */
	public String getDateFormer();

	/***
	 * 时间
	 * 
	 * @return
	 */
	public String getTime();
	
	/**
	 * 原格式时间
	 * @return
	 */
	public String getTimeFormer();

	/**
	 * 星期几,周几
	 * 
	 * @return
	 */
	public String getWeek();

	/**
	 * 位置
	 * 
	 * @return
	 */
	public String getPosition();

	/**
	 * 经度
	 * 
	 * @return
	 */
	public String getLongitude();

	/**
	 * 纬度
	 * 
	 * @return
	 */
	public String getLatitude();

	/**
	 * 时区
	 * 
	 * @return
	 */
	public String getTimeZone();

	/**
	 * 昼长
	 * 
	 * @return
	 */
	public String getDiurnalTime();

	/**
	 * 夜长
	 * 
	 * @return
	 */
	public String getNightTime();

	/**
	 * 天亮时间
	 * 
	 * @return
	 */
	public String getDawnTime();

	/**
	 * 日出时间
	 * 
	 * @return
	 */
	public String getSunriseTime();

	/***
	 * 中天时间
	 * 
	 * @return
	 */
	public String getMidDayTime();

	/**
	 * 日落时间
	 * 
	 * @return
	 */
	public String getSunsetTime();

	/**
	 * 天黑时间
	 * 
	 * @return
	 */
	public String getDarkTime();

	/***
	 * 月出时间
	 * 
	 * @return
	 */
	public String getMoonOutTime();

	/**
	 * 月中时间
	 * 
	 * @return
	 */
	public String getMidMoonTime();

	/**
	 * 月落时间
	 * 
	 * @return
	 */
	public String getMoonDownTime();

	/**
	 * 港口
	 * 
	 * @return
	 */
	public String getPortName();

	/**
	 * 儒略日
	 * 
	 * @return
	 */
	public String getJulianDay();

	/**
	 * 天干地支 年
	 * 
	 * @return
	 */
	public String getChineseEraYear();

	/**
	 * 天干地支 月
	 * 
	 * @return
	 */
	public String getChineseEraMonth();

	/**
	 * 天干地支 日
	 * 
	 * @return
	 */
	public String getChineseEraDay();

	/**
	 * 天干地支 时
	 * 
	 * @return
	 */
	public String getChineseEraTime();

	/**
	 * 皇帝纪年
	 * 
	 * @return
	 */
	public String getChronology();

	/**
	 * 生肖
	 * 
	 * @return
	 */
	public String getZodiac();

	/**
	 * 农历 年
	 * 
	 * @return
	 */
	public String getLunarYear();

	/**
	 * 农历 月
	 * 
	 * @return
	 */
	public String getLunarMonth();

	/**
	 * 农历 日
	 * 
	 * @return
	 */
	public String getLunarDay();

	/**
	 * 农历 时[更]刻
	 * 
	 * @return
	 */
	public String getLunarTime();

	/**
	 * 农历(年，月，日，时[更]，刻)
	 * 
	 * @return
	 */
	public String getLunar();

	/**
	 * 农历 月天数
	 * 
	 * @return
	 */
	public String getLunarDays();

	/**
	 * 是否是农历大月
	 * 
	 * @return
	 */
	public String isLunarBigMonth();

	/**
	 * 是否是农历闰月
	 * 
	 * @return
	 */
	public String isLeapMonth();

	/**
	 * 是否是农历闰年
	 * 
	 * @return
	 */
	public String isLeapYear();

	/**
	 * 回历 年
	 * 
	 * @return
	 */
	public int getIslamicYear();

	/**
	 * 回历 月
	 * 
	 * @return
	 */
	public int getIslamicMonth();

	/**
	 * 回历 日
	 * 
	 * @return
	 */
	public int getIslamicDay();

	/***
	 * 回历(伊斯兰历)
	 * 
	 * @return
	 */
	public String getIslamic();

	/**
	 * 星座
	 * 
	 * @return
	 */
	public String getConstellation();

	/**
	 * 天干
	 * 
	 * @return
	 */
	public String getTianGan();

	/**
	 * 地支
	 * 
	 * @return
	 */
	public String getDiZhi();

	/**
	 * 八字
	 * 
	 * @return
	 */
	public String getBaZi();

	/**
	 * 黄历
	 * 
	 * @return 戊戌年辛酉月辛亥日乙未时
	 */
	public String getHuangLi();

	/**
	 * 年号
	 * 
	 * @return
	 */
	public String getYearNumber();

	/**
	 * 节假日
	 * 
	 * @return
	 */
	public String getHolidayVacations();

	/**
	 * 月相
	 * 
	 * @return
	 */
	public String getMoonPhase();

	/**
	 * 下一个节气
	 * 
	 * @return
	 */
	public String getNextSolarTerm();

	/**
	 * 获取指定节气的日期
	 * 
	 * @param solarTerm
	 * @return
	 */
	public String getSolarTerm(String solarTerm);

	/**
	 * 获取所有节气
	 * 
	 * @return
	 */
	public String[] getAllSolarTerm();

	/**
	 * 获取节气的详细说明
	 * 
	 * @param solarTerm
	 * @return 节气说明
	 */
	public String getSolarTermDoc(String solarTerm);
}
