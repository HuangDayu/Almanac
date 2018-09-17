package com.almanac.old.lunar;

import com.almanac.old.lunar.GetSetData;

/***
 * 日落日出数据类
 * @author Administrator
 *
 */
public class GetSetData {
	/***
	 * 日出
	 */
	private static String sunRise;
	/***
	 * 日落
	 */
	private static String sunSet;
	/***
	 * 中天
	 */
	private static String midTime;
	/**
	 * 天亮
	 */
	private static String dawnTime;
	/***
	 * 天黑
	 */
	private static String nightTime;
	/***
	 * 昼长
	 */
	private static String dayTime;
	/***
	 * 夜长
	 */
	private static String eveningTime;
	/***
	 * 月出
	 */
	private static String moonRise;
	/***
	 * 月落
	 */
	private static String moonSet;
	/***
	 * 月中
	 */
	private static String moonMiddleTime;
	/***
	 * 经度
	 */
	private static String longitude;
	
	/***
	 * 纬度
	 */
	private static String latitude;

	public static String getSunRise() {
		return sunRise;
	}

	public static void setSunRise(String sunRise) {
		GetSetData.sunRise = sunRise;
	}

	public static String getSunSet() {
		return sunSet;
	}

	public static void setSunSet(String sunSet) {
		GetSetData.sunSet = sunSet;
	}

	public static String getMidTime() {
		return midTime;
	}

	public static void setMidTime(String midTime) {
		GetSetData.midTime = midTime;
	}

	public static String getDawnTime() {
		return dawnTime;
	}

	public static void setDawnTime(String dawnTime) {
		GetSetData.dawnTime = dawnTime;
	}

	public static String getNightTime() {
		return nightTime;
	}

	public static void setNightTime(String nightTime) {
		GetSetData.nightTime = nightTime;
	}

	public static String getDayTime() {
		return dayTime;
	}

	public static void setDayTime(String dayTime) {
		GetSetData.dayTime = dayTime;
	}

	public static String getEveningTime() {
		return eveningTime;
	}

	public static void setEveningTime(String eveningTime) {
		GetSetData.eveningTime = eveningTime;
	}

	public static String getMoonRise() {
		return moonRise;
	}

	public static void setMoonRise(String moonRise) {
		GetSetData.moonRise = moonRise;
	}

	public static String getMoonSet() {
		return moonSet;
	}

	public static void setMoonSet(String moonSet) {
		GetSetData.moonSet = moonSet;
	}

	public static String getMoonMiddleTime() {
		return moonMiddleTime;
	}

	public static void setMoonMiddleTime(String moonMiddleTime) {
		GetSetData.moonMiddleTime = moonMiddleTime;
	}

	public static String getLongitude() {
		return longitude;
	}

	public static void setLongitude(String longitude) {
		GetSetData.longitude = longitude;
	}

	public static String getLatitude() {
		return latitude;
	}

	public static void setLatitude(String latitude) {
		GetSetData.latitude = latitude;
	}
}
