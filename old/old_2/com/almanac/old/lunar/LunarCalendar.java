package com.almanac.old.lunar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.almanac.old.lunar.Annals;
import com.almanac.old.lunar.AstronomyArithmetic;
import com.almanac.old.lunar.Common;
import com.almanac.old.lunar.FestivalAndHoliday;
import com.almanac.old.lunar.IslamicCalendar;
import com.almanac.old.lunar.JulianCalendar;
import com.almanac.old.lunar.LunarDate;
import com.almanac.old.lunar.QiShuo;

public class LunarCalendar {

	/** 月历. 某月全部的日历数据 */
	private LunarDate[] lunarDates;
	/** 日历 */
	public LunarDate lunarDate;
	/** 某年气朔的数据信息 */
	private QiShuo qiShuo;

	Calendar calendar = Calendar.getInstance();// 创建实例

	/** 本月第一天的星期 */
	private int lc_WeekFirst_Fo_Month;

	public LunarCalendar() {
		this(new Date());
	}

	public LunarCalendar(Date date) {
		this(date.getTime());
	}

	private LunarCalendar(long TimeInMillis) {

		calendar.setTimeInMillis(TimeInMillis);// 初始化实例，给定时间
		lunarCalendarCalc(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.HOUR_OF_DAY));
		lunarDate = lunarDates[calendar.get(Calendar.DATE) - 1];
	}

	/***
	 * 获取农历数据类集合对象
	 * 
	 * @return
	 */
	public LunarDate[] getLunarDateListObj() {
		return lunarDates;
	}

	/***
	 * 获取农历数据类对象
	 * 
	 * @return
	 */
	public LunarDate getLunarDateClassObj() {
		return lunarDate;
	}

	/***
	 * 获取气朔类对象
	 * 
	 * @return
	 */
	public QiShuo getQiShuoClassObj() {
		return qiShuo;
	}

	/**
	 * 获取某年的二十四节气信息
	 * 
	 * @return
	 */
	public String[] getAllSolarTerm(int year) {
		return set24SolarTerm(year);
	}

	/***
	 * 农历计算方法，构造方法初始化，取每个月的1号
	 * 
	 * @param year
	 * @param month
	 */
	private void lunarCalendarCalc(int year, int month, int hours) {
		int ChineseEra_Year, julianMutualValue_year, julianMutualValue_month;
		// 日历物件初始化
		qiShuo = new QiShuo();
		JulianCalendar julianCalendar = new JulianCalendar(year, month, 1, 12, 0, 0.1);
		julianMutualValue_year = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000); // 公历月首,中午
		julianCalendar.setMonth(julianCalendar.getMonth() + 1);
		if (julianCalendar.getMonth() > 12) {
			julianCalendar.setYear(julianCalendar.getYear() + 1);
			julianCalendar.setMonth(1);
		}
		julianMutualValue_month = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000
				- julianMutualValue_year); // 本月天数(公历)

		this.lc_WeekFirst_Fo_Month = (julianMutualValue_year + Common.Julian_for_2000 + 1 + 7000000) % 7; // 本月第一天的星期

		// 所属公历年对应的农历干支纪年
		ChineseEra_Year = year - 1984 + 12000;

		// 为静态数据
		LunarDate lunarDateCalc1 = new LunarDate();
		lunarDateCalc1
				.setLunarDate_China_Era_Year(Annals.TIANGAN[ChineseEra_Year % 10] + Annals.DIZHI[ChineseEra_Year % 12]); // 干支纪年
		lunarDateCalc1.setLunarDate_Animal_Year(Annals.SHENGXIAO[ChineseEra_Year % 12]); // 该年对应的生肖
		lunarDateCalc1.setLunarDate_China_Era_Year_Number(Annals.getNH(year));// 干支纪年

		// 儒略日
		int julian_Days;
		// 月日视黄经的差值
		double w;

		// 提取各日信息
		LunarDate[] getSetDates = new LunarDate[julianMutualValue_month];

		for (int i = 0, j = 0; i < julianMutualValue_month; i++) {
			LunarDate lunarDateCalc = new LunarDate();
			lunarDateCalc.setJulian_Days(julianMutualValue_year + i); // 儒略日,北京时12:00
			lunarDateCalc.setGregorian_DayIndexForMonth(i); // 公历月内日序数
			lunarDateCalc.setGregorian_Year(year); // 公历年
			lunarDateCalc.setGregorian_Month(month); // 公历月
			lunarDateCalc.setGregorian_DaysOfMonth(julianMutualValue_month); // 公历月天数
			lunarDateCalc.setGregorian_WeekFirstForMonth(this.lc_WeekFirst_Fo_Month); // 月首的星期
			lunarDateCalc.setGregorian_Week((this.lc_WeekFirst_Fo_Month + i) % 7); // 当前日的星期
			lunarDateCalc.setGregorian_WeekIndexForMonth((int) Math.floor((this.lc_WeekFirst_Fo_Month + i) / 7)); // 本日所在的周序号
			lunarDateCalc.setGregorian_WeeksOfMonth(
					(int) Math.floor((this.lc_WeekFirst_Fo_Month + julianMutualValue_month - 1) / 7) + 1); // 本月的总周数
			julianCalendar.setFromJD(lunarDateCalc.getJulian_Days() + Common.Julian_for_2000);
			lunarDateCalc.setGregorian_Day(julianCalendar.getDay()); // 公历日名称

			// 农历月历
			// =======================
			// 此处有线程安全问题, 暂时直接实例化，ssq.calcY是为减少计算次数而做的判断。（同一年数据一样）
			if (lunarDateCalc.getJulian_Days() < qiShuo.ZQ[0] || lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[24]) {
				qiShuo.calcY(lunarDateCalc.getJulian_Days());
			}
			// =======================
			int mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.HS[0]) / 30);
			if (mk < 13 && qiShuo.HS[mk + 1] <= lunarDateCalc.getJulian_Days()) {
				mk++; // 农历所在月的序数
			}

			lunarDateCalc.setLunar_MonthOffset(lunarDateCalc.getJulian_Days() - qiShuo.HS[mk]); // 距农历月首的编移量,0对应初一
			lunarDateCalc.setLunar_DayName(Annals.DAY_NAME[lunarDateCalc.getLunar_MonthOffset()]); // 农历日名称
			lunarDateCalc.setLunar_Last_Dongzhi(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0]); // 距冬至的天数
			lunarDateCalc.setLunar_Last_Xiazhi(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[12]); // 距夏至的天数
			lunarDateCalc.setLunar_Last_Liqiu(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[15]); // 距立秋的天数
			lunarDateCalc.setLunar_Last_Mangzhong(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[11]); // 距芒种的天数
			lunarDateCalc.setLunar_Last_Xiaoshu(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[13]); // 距小暑的天数
			if (lunarDateCalc.getJulian_Days() == qiShuo.HS[mk]
					|| lunarDateCalc.getJulian_Days() == julianMutualValue_year) { // 月的信息
				lunarDateCalc.setLunar_MonthName(qiShuo.ym[mk]); // 月名称
				lunarDateCalc.setLunar_Days_OfMonth(qiShuo.dx[mk]); // 月大小
				lunarDateCalc.setLunar_Lunar_isLeap((qiShuo.leap != 0 && qiShuo.leap == mk) ? "闰" : ""); // 闰状况
				lunarDateCalc.setLunar_NextLunarMonthName(mk < 13 ? qiShuo.ym[mk + 1] : "未知"); // 下个月名称,判断除夕时要用到
			} else {
				LunarDate lunarDate2 = getSetDates[i - 1];
				lunarDateCalc.setLunar_MonthName(lunarDate2.getLunar_MonthName());
				lunarDateCalc.setLunar_Days_OfMonth(lunarDate2.getLunar_Days_OfMonth());
				lunarDateCalc.setLunar_Lunar_isLeap(lunarDate2.getLunar_Lunar_isLeap());
				lunarDateCalc.setLunar_NextLunarMonthName(lunarDate2.getLunar_NextLunarMonthName());
			}
			int qk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0] - 7) / 15.2184);
			if (qk < 23 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[qk + 1]) {
				qk++; // 节气的取值范围是0-23
			}
			if (lunarDateCalc.getJulian_Days() == qiShuo.ZQ[qk]) {
				lunarDateCalc.setLunar_SolarTerm(Annals.JIEQI[qk]);
			} else {
				lunarDateCalc.setLunar_SolarTerm("");
			}

			// ob.yxmc = ob.yxjd = ob.yxsj ="";//月相名称,月相时刻(儒略日),月相时间串
			// ob.jqmc = ob.jqjd = ob.jqsj ="";//定气名称,节气时刻(儒略日),节气时间串

			// 干支纪年处理
			// 以立春为界定年首
			julian_Days = (int) (qiShuo.ZQ[3] + (lunarDateCalc.getJulian_Days() < qiShuo.ZQ[3] ? -365 : 0) + 365.25 * 16
					- 35); // 以立春为界定纪年
			lunarDateCalc.setLunar_Period_Of_Years((int) Math.floor(julian_Days / 365.2422 + 0.5)); // 农历纪年(10进制,1984年起算)
			// 以下几行以正月初一定年首
			julian_Days = qiShuo.HS[2]; // 一般第3个月为春节
			for (j = 0; j < 14; j++) { // 找春节
				if (!qiShuo.ym[j].equals("正") || qiShuo.leap == j && j != 0) {
					continue;
				}
				julian_Days = qiShuo.HS[j];
				if (lunarDateCalc.getJulian_Days() < julian_Days) {
					julian_Days -= 365;
					break;
				} // 无需再找下一个正月
			}
			julian_Days = julian_Days + 5810; // 计算该年春节与1984年平均春节(立春附近)相差天数估计
			int Lyear0 = (int) Math.floor(julian_Days / 365.2422 + 0.5); // 农历纪年(10进制,1984年起算)

			julian_Days = lunarDateCalc.getLunar_Period_Of_Years() + 12000;
			//System.out.println("lunarDateCalc.setChineseEra_Year:"+julian_Days);
			lunarDateCalc.setChineseEra_Year(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]); // 干支纪年(立春)
			julian_Days = Lyear0 + 12000;
			// String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
			// 与Lyear2区别在于春节才视为新年
			lunarDateCalc.setLunar_king_Years(Lyear0 + 1984 + 2698); // 黄帝纪年

			// 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
			mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0]) / 30.43685);
			if (mk < 12 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[2 * mk + 1]) {
				mk++; // 相对大雪的月数计算,mk的取值范围0-12
			}

			julian_Days = mk + (int) Math.floor((qiShuo.ZQ[12] + 390) / 365.2422) * 12 + 900000; // 相对于1998年12月7(大雪)的月数,900000为正数基数
			lunarDateCalc.setLunar_Period_Of_Months(julian_Days % 12);
			//System.out.println("lunarDateCalc.setChineseEra_Month"+julian_Days);
			lunarDateCalc.setChineseEra_Month(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]);

			// 纪日,2000年1月7日起算
			julian_Days = lunarDateCalc.getJulian_Days() - 6 + 9000000;
			//System.out.println("lunarDateCalc.setChineseEra_Day"+julian_Days);
			lunarDateCalc.setChineseEra_Day(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]);

			/************ 天干地支时，大鱼叔叔编写，算法可能有误 ****************/
			int shi = (hours + 1) / 2;
			//System.out.println("lunarDateCalc.setChineseEra_Time"+julian_Days);
			lunarDateCalc.setChineseEra_Time(Annals.TIANGAN[(shi + julian_Days * 12) % 10] + Annals.DIZHI[shi % 12]);
			/************ 天干地支时，大鱼叔叔编写，算法可能有误 ****************/

			// 星座
			mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0] - 15) / 30.43685);
			if (mk < 11 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[2 * mk + 2]) {
				mk++; // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
			}
			lunarDateCalc.setConstellation(Annals.XINGZUO[(mk + 12) % 12] + "座");
			// 回历
			IslamicCalendar.setIslamicCalendar(lunarDateCalc.getJulian_Days(), lunarDateCalc);
			// 节日
			// ob.A = ob.B = ob.C = ""; ob.Fjia = 0;

			// 计算公历节日
			FestivalAndHoliday.getDayName(lunarDateCalc, lunarDateCalc); // 公历
			// 计算农历节日
			Annals.getDayName(lunarDateCalc, lunarDateCalc); // 农历

			getSetDates[i] = lunarDateCalc;
		}

		// 以下是月相与节气的处理
		double d;
		int xn;
		double jd2 = julianMutualValue_year + Common.dt_T(julianMutualValue_year) - (double) 8 / 24;
		// 月相查找
		w = AstronomyArithmetic.MS_aLon(jd2 / 36525, 10, 3);
		w = (int) Math.floor((w - 0.78) / Math.PI * 2) * Math.PI / 2;
		do {
			d = Annals.so_accurate(w);
			julian_Days = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 4 + 4000000.01) % 4;
			w += Common.pi2 / 4;
			if (julian_Days >= julianMutualValue_year + julianMutualValue_month) {
				break;
			}
			if (julian_Days < julianMutualValue_year) {
				continue;
			}
			LunarDate lunarDate = getSetDates[julian_Days - julianMutualValue_year];
			lunarDate.setMoon_PhaseName(Annals.YUEXIANG[xn]); // 取得月相名称
			lunarDate.setMoon_PhaseTime(d);
			lunarDate.setMoon_PhaseTimeStr(julianCalendar.timeStr(d));
		} while (julian_Days + 5 < julianMutualValue_year + julianMutualValue_month);

		// 节气查找
		w = AstronomyArithmetic.S_aLon(jd2 / 36525, 3);
		w = (int) Math.floor((w - 0.13) / Common.pi2 * 24) * Common.pi2 / 24;
		do {
			d = Annals.qi_accurate(w);
			julian_Days = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 24 + 24000006.01) % 24;
			w += Common.pi2 / 24;
			if (julian_Days >= julianMutualValue_year + julianMutualValue_month) {
				break;
			}
			if (julian_Days < julianMutualValue_year) {
				continue;
			}
			LunarDate lunarDate = getSetDates[julian_Days - julianMutualValue_year];
			lunarDate.setLunar_SolarTerm_Name(Annals.JIEQI[xn]); // 取得节气名称
			lunarDate.setLunar_SolarTerm_Time(d);
			lunarDate.setLunar_SolarTerm_Time_Str(julianCalendar.timeStr(d));
		} while (julian_Days + 12 < julianMutualValue_year + julianMutualValue_month);

		this.lunarDates = getSetDates;// 实例化对象
	}

	/***
	 * 定气测试函数
	 * 
	 * @param year
	 * @return
	 */
	private String[] set24SolarTerm(int year) {
		String[] solarTerms = new String[24];
		double T;
		// String s = "", s2 = "";
		int y = Common.year2Ayear(String.valueOf(year)) - 2001;
		int n = 24;
		for (int i = 21, index = 0; i < n;) {
			T = AstronomyArithmetic.S_aLon_t((y + (double) i * 15 / 360 + 1) * 2 * Math.PI); // 精确节气时间计算
			// s2 += new JulianDate().JD2str(T * 36525 + Common.J2000 + (double) 8 / 24 -
			// Common.dt_T(T * 36525))
			// + Obb.jqmc[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; //日期转为字串
			String solarTerm = new JulianCalendar()
					.JD2str(T * 36525 + Common.Julian_for_2000 + (double) 8 / 24 - Common.dt_T(T * 36525))
					+ Annals.JIEQI[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; // 日期转为字串
			solarTerms[index] = solarTerm.trim();
			// if (i % 2 == 0) s2 += " 视黄经" + (i * 15) + "\n";
			// else s2 += " "; if (i % 50 == 0) {
			// s += s2; s2 = ""; }
			i++;
			index++;
			if (i == 24) {
				i = 0;
				n = 21;
				y += 1;
			}
		}
		return solarTerms;
	}

	/***
	 * 输入年，月，日算出儒略日 http://www.ilovematlab.cn/thread-19002-1-1.html
	 * 
	 * @param I
	 *            年
	 * @param J
	 *            月
	 * @param K
	 *            日
	 * @return 儒略日
	 */
	public int getJuLian(int I, int J, int K) {
		int jl = K - 32075 + 1461 * (I + 4800 + (J - 14) / 12) / 4 + 367 * (J - 2 - (J - 14) / 12 * 12) / 12
				- 3 * ((I + 4900 + (J - 14) / 12) / 100) / 4;
		return jl;
	}
	
	/***
	 * 查询指定节气的日期
	 * @param sola
	 * @return
	 */
	public String getSolarTermDate(String sola) {
		int nowYear = calendar.get(Calendar.YEAR);
		String[] strs1 = this.getAllSolarTerm(nowYear);
		for (String str1 : strs1) {
			String str2 = str1.substring(str1.length() - 2,str1.length());
			if(str2.equals(sola)) {
				return str1.substring(0,str1.length() - 2);
			}
		}
		return null;
	}

	/***
	 * 获取距离日期最新的节气
	 * 
	 * @param calendar
	 * @return
	 */
	public String getNextSolarTerm() {
		int nowYear = calendar.get(Calendar.YEAR);
		String[] strs1 = this.getAllSolarTerm(nowYear);
		String[] returnStr = new String[24];
		for (String str1 : strs1) {
			returnStr = solartermYorN(str1);
			//System.out.println("returnStr[0]=" + (returnStr[0] == "Y"));
			if (returnStr[0] == "Y") {// 小于15天
				str1 = str1.substring(str1.length() - 2, str1.length()) + " " + returnStr[1] + "月" + returnStr[2] + "日"
						+ str1.substring(10, str1.length() - 2);
				return str1;
			} else if (returnStr[0] == "N") {
				//System.out.println("returnStr[1]=" + (returnStr[0] == "N"));
				String[] strs2 = this.getAllSolarTerm((nowYear - 1));// 年份减一
				for (String str2 : strs2) {
					String[] returnStr2 = solartermYorN(str2);
					if (returnStr2[0] == "Y") {
						//System.out.println("returnStr[2]=" + (returnStr2[0] == "Y"));
						str2 = str2.substring(str2.length() - 2, str2.length()) + " " + returnStr2[1] + "月"
								+ returnStr2[2] + "日" + str2.substring(10, str2.length() - 2);
						return str2;
					}
				}
			} else {
				continue;
			}
		}

		return null;
	}

	/***
	 * 判断节气和当下时间相差数，为了防止公历年初时，农历是冬月和腊月,一个节气之间相差15天（按儒略日计算）
	 * 
	 * @param nowDays
	 *            当下时间儒略日
	 * @param str
	 *            节气信息
	 * @return
	 */
	public String[] solartermYorN(String str) {
		int year = this.lunarDate.getGregorian_Year();
		int month = this.lunarDate.getGregorian_Month();
		int day = this.lunarDate.getGregorian_Day();
		int nowDays = getJuLian(year, month, day);

		String[] strs_2 = str.split(" ");
		String[] strs_3 = strs_2[0].split("-");
		String[] returnStr = new String[24];

		int y = Integer.valueOf(strs_3[0]);// 年
		int m = Integer.valueOf(strs_3[1]);// 月
		int d = Integer.valueOf(strs_3[2]);// 日

		int solartermDays = getJuLian(y, m, d);

		if (nowDays <= solartermDays && (solartermDays - nowDays) < 15) {
			returnStr[0] = "Y";
		} else if (nowDays <= solartermDays && (solartermDays - nowDays) >= 15 && (solartermDays - nowDays) < 30) {
			returnStr[0] = "N";
		} else {
			returnStr[0] = "O";
		}
		returnStr[1] = String.valueOf(m);
		returnStr[2] = String.valueOf(d);
		return returnStr;
	}

}
