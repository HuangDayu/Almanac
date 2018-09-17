package com.almanac.lunar;

import com.almanac.lunar.SunAndMoonBean;

/***
 * 日落日出数据类
 * 
 * @author Administrator
 *
 */
public class SunAndMoonBean {
	/***
	 * 日出
	 */
	private String sunRise;
	/***
	 * 日落
	 */
	private String sunSet;
	/***
	 * 中天
	 */
	private String midTime;
	/**
	 * 天亮
	 */
	private String dawnTime;
	/***
	 * 天黑
	 */
	private String nightTime;
	/***
	 * 昼长
	 */
	private String dayTime;
	/***
	 * 夜长
	 */
	private String eveningTime;
	/***
	 * 月出
	 */
	private String moonRise;
	/***
	 * 月落
	 */
	private String moonSet;
	/***
	 * 月中
	 */
	private String moonMiddleTime;
	/***
	 * 经度
	 */
	private String longitude;

	/***
	 * 纬度
	 */
	private String latitude;

	public String getSunRise() {
		return TimeUtil.getFormattingTime(sunRise);
	}

	public void setSunRise(String sunRise) {
		this.sunRise = sunRise;
	}

	public String getSunSet() {
		return TimeUtil.getFormattingTime(sunSet);
	}

	public void setSunSet(String sunSet) {
		this.sunSet = sunSet;
	}

	public String getMidTime() {
		return TimeUtil.getFormattingTime(midTime);
	}

	public void setMidTime(String midTime) {
		this.midTime = midTime;
	}

	public String getDawnTime() {
		return TimeUtil.getFormattingTime(dawnTime);
	}

	public void setDawnTime(String dawnTime) {
		this.dawnTime = dawnTime;
	}

	public String getNightTime() {
		return TimeUtil.getFormattingTime(nightTime);
	}

	public void setNightTime(String nightTime) {
		this.nightTime = nightTime;
	}

	public String getDayTime() {
		return TimeUtil.getFormattingTime(dayTime);
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}

	public String getEveningTime() {
		return TimeUtil.getFormattingTime(eveningTime);
	}

	public void setEveningTime(String eveningTime) {
		this.eveningTime = eveningTime;
	}

	public String getMoonRise() {
		return TimeUtil.getFormattingTime(moonRise);
	}

	public void setMoonRise(String moonRise) {
		this.moonRise = moonRise;
	}

	public String getMoonSet() {
		return TimeUtil.getFormattingTime(moonSet);
	}

	public void setMoonSet(String moonSet) {
		this.moonSet = moonSet;
	}

	public String getMoonMiddleTime() {
		return TimeUtil.getFormattingTime(moonMiddleTime);
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
