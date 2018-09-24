package com.almanac.lunar;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class AlmanacDataImpl implements AlmanacData {
	private LunarDate lunarDate = null;
	private SunAndMoon sumAndMoon = null;
	private SunAndMoonBean sunAndMoonBean = null;
	private String[] areas = null;
	private Calendar calendar = null;
	private DataBean dataBean = null;

	public AlmanacDataImpl(DataBean dataBean) {
		this.calendar = dataBean.getCalendar();
		this.dataBean = dataBean;
		LunarCalendar lunarCalendar = new LunarCalendar(this.calendar);
		lunarDate = new LunarDate(lunarCalendar.getLunarDateClassObj(), lunarCalendar);
		sumAndMoon = new SunAndMoon(dataBean);
		sunAndMoonBean = sumAndMoon.getSunAndMoonBean();
	};

	@Override
	public String getDate() {
		return TimeUtil.dateFormat(calendar, "yyyy年MM月dd日");
	}

	@Override
	public String getTime() {
		return TimeUtil.dateFormat(calendar, "HH时mm分ss秒SS毫秒");
	}

	@Override
	public String getWeek() {
		return TimeUtil.getWeek(calendar);
	}

	@Override
	public String getPosition() {
		// return areas[0] + " " + areas[1].substring(4, areas[1].length());
		return dataBean.getProvince() + " " + dataBean.getArea();
	}

	@Override
	public String getLongitude() {
		return sunAndMoonBean.getLongitude();
	}

	@Override
	public String getLatitude() {
		return sunAndMoonBean.getLatitude();
	}

	@Override
	public String getTimeZone() {
		String strDateFormat = TimeUtil.formatDateByFormat(calendar, "Z");// +0800

		String str2 = strDateFormat.substring(1, 3);
		int timeZoneInt = 0;
		int j = Integer.valueOf(str2.substring(0));
		if (j > 0) {
			timeZoneInt = Integer.valueOf(str2);
		} else {
			timeZoneInt = Integer.valueOf(str2.substring(1));
		}

		String[] timeZoneChese = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };

		if (strDateFormat.contains("-")) {
			return strDateFormat + " 西" + timeZoneChese[timeZoneInt - 1] + "区";
		} else {
			return strDateFormat + " 东" + timeZoneChese[timeZoneInt - 1] + "区";
		}
	}

	@Override
	public String getDiurnalTime() {
		return sunAndMoonBean.getDayTime();
	}

	@Override
	public String getNightTime() {
		return sunAndMoonBean.getEveningTime();
	}

	@Override
	public String getDawnTime() {
		return sunAndMoonBean.getDawnTime();
	}

	@Override
	public String getSunriseTime() {
		return sunAndMoonBean.getSunRise();
	}

	@Override
	public String getMidDayTime() {
		return sunAndMoonBean.getMidTime();
	}

	@Override
	public String getSunsetTime() {
		return sunAndMoonBean.getSunSet();
	}

	@Override
	public String getDarkTime() {
		return sunAndMoonBean.getNightTime();
	}

	@Override
	public String getMoonOutTime() {
		return sunAndMoonBean.getMoonRise();
	}

	@Override
	public String getMidMoonTime() {
		return sunAndMoonBean.getMoonMiddleTime();
	}

	@Override
	public String getMoonDownTime() {
		return sunAndMoonBean.getMoonSet();
	}

	@Override
	public String getPortName() {
		return GetPort.getProtName(sumAndMoon);
	}

	@Override
	public String getJulianDay() {
		return String.valueOf(JulianCalendar.getJuLian(calendar));
	}

	@Override
	public String getChineseEraYear() {
		return lunarDate.getChineseEra_Year();
	}

	@Override
	public String getChineseEraMonth() {
		return lunarDate.getChineseEra_Month();
	}

	@Override
	public String getChineseEraDay() {
		return lunarDate.getChineseEra_Day();
	}

	@Override
	public String getChineseEraTime() {
		return lunarDate.getChineseEra_Time();
	}

	@Override
	public String getChronology() {
		return lunarDate.getLunar_king_Years_Obj() + "年";
	}

	@Override
	public String getZodiac() {
		return lunarDate.getLunarDate_Animal_Year();
	}

	@Override
	public String getLunarYear() {
		return lunarDate.getLunarYearString_Obj();
	}

	@Override
	public String getLunarMonth() {
		return lunarDate.getLunarMonthString_Obj() + "月";
	}

	@Override
	public String getLunarDay() {
		return lunarDate.getLunarDayString_Obj();
	}

	@Override
	public String getLunarTime() {
		String Hstr = null, Mstr = null;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		double sum = hour + 0.01 * minute;
		if (sum < 1 || sum >= 23) {
			Hstr = Annals.DIZHI[0] + "时" + "[三更]";
		}
		if (sum >= 1 && sum < 3) {
			Hstr = Annals.DIZHI[1] + "时" + "[四更]";
		}
		if (sum >= 3 && sum < 5) {
			Hstr = Annals.DIZHI[2] + "时" + "[五更]";
		}
		if (sum >= 5 && sum < 7) {
			Hstr = Annals.DIZHI[3] + "时";
		}
		if (sum >= 7 && sum < 9) {
			Hstr = Annals.DIZHI[4] + "时";
		}
		if (sum >= 9 && sum < 11) {
			Hstr = Annals.DIZHI[5] + "时";
		}
		if (sum >= 11 && sum < 13) {
			Hstr = Annals.DIZHI[6] + "时";
		}
		if (sum >= 13 && sum < 15) {
			Hstr = Annals.DIZHI[7] + "时";
		}
		if (sum >= 15 && sum < 17) {
			Hstr = Annals.DIZHI[8] + "时";
		}
		if (sum >= 17 && sum < 19) {
			Hstr = Annals.DIZHI[9] + "时";
		}
		if (sum >= 19 && sum < 21) {
			Hstr = Annals.DIZHI[10] + "时" + "[一更]";
		}
		if (sum >= 21 && sum < 23) {
			Hstr = Annals.DIZHI[11] + "时" + "[二更]";
		}
		if (minute >= 14 && minute <= 15) {
			Mstr = "一刻";
		} else if (minute >= 28 && minute <= 30) {
			Mstr = "二刻";
		} else if (minute >= 42 && minute <= 45) {
			Mstr = "三刻";
		} else if (minute >= 48 && minute <= 60) {
			Mstr = "四刻";
		} else {
			Mstr = "";
		}
		return Hstr + Mstr;
	}

	@Override
	public String getLunar() {
		return getLunarYear() + getZodiac() + "年" + getLunarMonth() + getLunarDay();
	}

	@Override
	public String getLunarDays() {
		return lunarDate.getLunarDaySumInMonth_Obj() + "天";
	}

	@Override
	public String isLunarBigMonth() {
		if (!lunarDate.isBigLunarMonthBool_Obj()) {
			return "否";
		} else {
			return "是";
		}
	}

	@Override
	public String isLeapMonth() {
		if (!lunarDate.isLeapMonthBool_Obj()) {
			return "否";
		} else {
			return "是";
		}
	}

	@Override
	public String isLeapYear() {
		if (!lunarDate.isLeapYearBool_Obj()) {
			return "否";
		} else {
			return "是";
		}
	}

	@Override
	public int getIslamicYear() {
		return lunarDate.getIslamic_Year_Obj();
	}

	@Override
	public int getIslamicMonth() {
		return lunarDate.getIslamic_Month_Obj();
	}

	@Override
	public int getIslamicDay() {
		return lunarDate.getIslamic_Day_Obj();
	}

	@Override
	public String getIslamic() {
		return getIslamicYear() + "年" + getIslamicMonth() + "月" + getIslamicDay() + "日";
	}

	@Override
	public String getConstellation() {
		return lunarDate.getConstellation_Obj();
	}

	@Override
	public String getTianGan() {
		return lunarDate.getChinaEra_TianGan_String();
	}

	@Override
	public String getDiZhi() {
		return lunarDate.getChinaEra_DiZhi_String();
	}

	@Override
	public String getBaZi() {
		return lunarDate.getChinaGanZhiDate_BaZi();
	}

	@Override
	public String getHuangLi() {
		return lunarDate.getChinaGanZhiDate_HuangLi();
	}

	@Override
	public String getYearNumber() {
		return lunarDate.getLunarDate_China_Era_Year_Number();
	}

	@Override
	public String getHolidayVacations() {
		String str = lunarDate.getAllDay_Name_Obj();
		if (str == "") {
			return "无";
		} else if (str == null) {
			return "无";
		} else {
			return str;
		}
	}

	@Override
	public String getMoonPhase() {
		String str = lunarDate.getMoon_PhaseName_Obj();
		if (str == "") {
			return "无";
		} else if (str == null) {
			return "无";
		} else {
			return str +" "+lunarDate.getMoon_PhaseTime_Obj();
		}
	}

	@Override
	public String getNextSolarTerm() {
		return lunarDate.getNowOrNext24SolarTerm();
	}

	@Override
	public String getSolarTerm(String solarTerm) {
		return lunarDate.getSolarTermDate(solarTerm);
	}

	@Override
	public String[] getAllSolarTerm() {
		return lunarDate.getSolarTerm();
	}

	@Override
	public String getSolarTermDoc(String solarTerm) {
		return SolarTermUtil.solarTermsMap.get(solarTerm);
	}

}
