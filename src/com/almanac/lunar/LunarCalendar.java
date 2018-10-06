package com.almanac.lunar;

import java.util.Calendar;
import java.util.Date;
import com.almanac.lunar.Annals;
import com.almanac.lunar.AstronomyArithmetic;
import com.almanac.lunar.Common;
import com.almanac.lunar.FestivalAndHoliday;
import com.almanac.lunar.QiShuo;
import com.almanac.lunar.IslamicCalendar;
import com.almanac.lunar.JulianCalendar;
import com.almanac.lunar.LunarDate;

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

	public LunarCalendar(Date date) {
		this(date.getTime());
	}
	
	public LunarCalendar(Calendar calendar) {
		this(calendar.getTimeInMillis());
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
		int chineseEraYear, _year, _month;
		// 日历物件初始化
		qiShuo = new QiShuo();
		JulianCalendar julianCalendar = new JulianCalendar(year, month, 1, 12, 0, 0.1);
		_year = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000); // 公历月首,中午
		julianCalendar.setMonth(julianCalendar.getMonth() + 1);
		if (julianCalendar.getMonth() > 12) {
			julianCalendar.setYear(julianCalendar.getYear() + 1);
			julianCalendar.setMonth(1);
		}
		_month = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000
				- _year); // 本月天数(公历)

		this.lc_WeekFirst_Fo_Month = (_year + Common.Julian_for_2000 + 1 + 7000000) % 7; // 本月第一天的星期

		// 所属公历年对应的农历干支纪年
		chineseEraYear = year - 1984 + 12000;

		// 儒略日
		int _day;
		// 月日视黄经的差值
		double w;

		// 提取各日信息
		LunarDate[] getSetDates = new LunarDate[_month];

		for (int i = 0, j = 0; i < _month; i++) {
			LunarDate _lunarDate = new LunarDate();
			_lunarDate.setJulian_Days(_year + i); // 儒略日,北京时12:00
			_lunarDate.setGregorian_DayIndexForMonth(i); // 公历月内日序数
			_lunarDate.setGregorian_Year(year); // 公历年
			_lunarDate.setGregorian_Month(month); // 公历月
			_lunarDate.setGregorian_DaysOfMonth(_month); // 公历月天数
			_lunarDate.setGregorian_WeekFirstForMonth(this.lc_WeekFirst_Fo_Month); // 月首的星期
			_lunarDate.setGregorian_Week((this.lc_WeekFirst_Fo_Month + i) % 7); // 当前日的星期
			_lunarDate.setGregorian_WeekIndexForMonth((int) Math.floor((this.lc_WeekFirst_Fo_Month + i) / 7)); // 本日所在的周序号
			_lunarDate.setGregorian_WeeksOfMonth(
					(int) Math.floor((this.lc_WeekFirst_Fo_Month + _month - 1) / 7) + 1); // 本月的总周数
			julianCalendar.setFromJD(_lunarDate.getJulian_Days() + Common.Julian_for_2000);
			_lunarDate.setGregorian_Day(julianCalendar.getDay()); // 公历日名称

			// 农历月历
			// =======================
			// 此处有线程安全问题, 暂时直接实例化，ssq.calcY是为减少计算次数而做的判断。（同一年数据一样）
			if (_lunarDate.getJulian_Days() < qiShuo.ZQ[0] || _lunarDate.getJulian_Days() >= qiShuo.ZQ[24]) {
				qiShuo.calcY(_lunarDate.getJulian_Days());
			}
			// =======================
			int mk = (int) Math.floor((_lunarDate.getJulian_Days() - qiShuo.HS[0]) / 30);
			if (mk < 13 && qiShuo.HS[mk + 1] <= _lunarDate.getJulian_Days()) {
				mk++; // 农历所在月的序数
			}

			_lunarDate.setLunar_MonthOffset(_lunarDate.getJulian_Days() - qiShuo.HS[mk]); // 距农历月首的编移量,0对应初一
			_lunarDate.setLunar_DayName(Annals.DAY_NAME[_lunarDate.getLunar_MonthOffset()]); // 农历日名称
			_lunarDate.setLunar_Last_Dongzhi(_lunarDate.getJulian_Days() - qiShuo.ZQ[0]); // 距冬至的天数
			_lunarDate.setLunar_Last_Xiazhi(_lunarDate.getJulian_Days() - qiShuo.ZQ[12]); // 距夏至的天数
			_lunarDate.setLunar_Last_Liqiu(_lunarDate.getJulian_Days() - qiShuo.ZQ[15]); // 距立秋的天数
			_lunarDate.setLunar_Last_Mangzhong(_lunarDate.getJulian_Days() - qiShuo.ZQ[11]); // 距芒种的天数
			_lunarDate.setLunar_Last_Xiaoshu(_lunarDate.getJulian_Days() - qiShuo.ZQ[13]); // 距小暑的天数
			if (_lunarDate.getJulian_Days() == qiShuo.HS[mk]
					|| _lunarDate.getJulian_Days() == _year) { // 月的信息
				_lunarDate.setLunar_MonthName(qiShuo.ym[mk]); // 月名称
				_lunarDate.setLunar_Days_OfMonth(qiShuo.dx[mk]); // 月大小
				_lunarDate.setLunar_Lunar_isLeap((qiShuo.leap != 0 && qiShuo.leap == mk) ? "闰" : ""); // 闰状况
				_lunarDate.setLunar_NextLunarMonthName(mk < 13 ? qiShuo.ym[mk + 1] : "未知"); // 下个月名称,判断除夕时要用到
			} else {
				LunarDate lunarDate2 = getSetDates[i - 1];
				_lunarDate.setLunar_MonthName(lunarDate2.getLunar_MonthName());
				_lunarDate.setLunar_Days_OfMonth(lunarDate2.getLunar_Days_OfMonth());
				_lunarDate.setLunar_Lunar_isLeap(lunarDate2.getLunar_Lunar_isLeap());
				_lunarDate.setLunar_NextLunarMonthName(lunarDate2.getLunar_NextLunarMonthName());
			}
			int qk = (int) Math.floor((_lunarDate.getJulian_Days() - qiShuo.ZQ[0] - 7) / 15.2184);
			if (qk < 23 && _lunarDate.getJulian_Days() >= qiShuo.ZQ[qk + 1]) {
				qk++; // 节气的取值范围是0-23
			}
			if (_lunarDate.getJulian_Days() == qiShuo.ZQ[qk]) {
				_lunarDate.setLunar_SolarTerm(Annals.JIEQI[qk]);
			} else {
				_lunarDate.setLunar_SolarTerm("");
			}

			// ob.yxmc = ob.yxjd = ob.yxsj ="";//月相名称,月相时刻(儒略日),月相时间串
			// ob.jqmc = ob.jqjd = ob.jqsj ="";//定气名称,节气时刻(儒略日),节气时间串

			// 干支纪年处理
			// 以立春为界定年首
			_day = (int) (qiShuo.ZQ[3] + (_lunarDate.getJulian_Days() < qiShuo.ZQ[3] ? -365 : 0) + 365.25 * 16
					- 35); // 以立春为界定纪年
			_lunarDate.setLunar_Period_Of_Years((int) Math.floor(_day / 365.2422 + 0.5)); // 农历纪年(10进制,1984年起算)
			// 以下几行以正月初一定年首
			_day = qiShuo.HS[2]; // 一般第3个月为春节
			for (j = 0; j < 14; j++) { // 找春节
				if (!qiShuo.ym[j].equals("正") || qiShuo.leap == j && j != 0) {
					continue;
				}
				_day = qiShuo.HS[j];
				if (_lunarDate.getJulian_Days() < _day) {
					_day -= 365;
					break;
				} // 无需再找下一个正月
			}
			_day = _day + 5810; // 计算该年春节与1984年平均春节(立春附近)相差天数估计
			int Lyear0 = (int) Math.floor(_day / 365.2422 + 0.5); // 农历纪年(10进制,1984年起算)

			_day = _lunarDate.getLunar_Period_Of_Years() + 12000;
			//System.out.println("lunarDateCalc.setChineseEra_Year:"+julian_Days);
			_lunarDate.setChineseEra_Year(Annals.TIANGAN[_day % 10] + Annals.DIZHI[_day % 12]); // 干支纪年(立春)
			_day = Lyear0 + 12000;
			// String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
			// 与Lyear2区别在于春节才视为新年
			_lunarDate.setLunar_king_Years(Lyear0 + 1984 + 2698); // 黄帝纪年

			// 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
			mk = (int) Math.floor((_lunarDate.getJulian_Days() - qiShuo.ZQ[0]) / 30.43685);
			if (mk < 12 && _lunarDate.getJulian_Days() >= qiShuo.ZQ[2 * mk + 1]) {
				mk++; // 相对大雪的月数计算,mk的取值范围0-12
			}

			_day = mk + (int) Math.floor((qiShuo.ZQ[12] + 390) / 365.2422) * 12 + 900000; // 相对于1998年12月7(大雪)的月数,900000为正数基数
			_lunarDate.setLunar_Period_Of_Months(_day % 12);
			//System.out.println("lunarDateCalc.setChineseEra_Month"+julian_Days);
			_lunarDate.setChineseEra_Month(Annals.TIANGAN[_day % 10] + Annals.DIZHI[_day % 12]);

			// 纪日,2000年1月7日起算
			_day = _lunarDate.getJulian_Days() - 6 + 9000000;
			//System.out.println("lunarDateCalc.setChineseEra_Day"+julian_Days);
			_lunarDate.setChineseEra_Day(Annals.TIANGAN[_day % 10] + Annals.DIZHI[_day % 12]);

			/************ 天干地支时，大鱼叔叔编写，算法可能有误 ****************/
			int shi = (hours + 1) / 2;
			//System.out.println("lunarDateCalc.setChineseEra_Time"+julian_Days);
			_lunarDate.setChineseEra_Time(Annals.TIANGAN[(shi + _day * 12) % 10] + Annals.DIZHI[shi % 12]);
			/************ 天干地支时，大鱼叔叔编写，算法可能有误 ****************/

			// 星座
			mk = (int) Math.floor((_lunarDate.getJulian_Days() - qiShuo.ZQ[0] - 15) / 30.43685);
			if (mk < 11 && _lunarDate.getJulian_Days() >= qiShuo.ZQ[2 * mk + 2]) {
				mk++; // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
			}
			_lunarDate.setConstellation(Annals.XINGZUO[(mk + 12) % 12] + "座");
			// 回历
			IslamicCalendar.setIslamicCalendar(_lunarDate.getJulian_Days(), _lunarDate);
			// 节日
			// ob.A = ob.B = ob.C = ""; ob.Fjia = 0;

			// 计算公历节日
			FestivalAndHoliday.getDayName(_lunarDate, _lunarDate); // 公历
			// 计算农历节日
			Annals.getDayName(_lunarDate, _lunarDate); // 农历

			_lunarDate.setLunarDate_China_Era_Year(Annals.TIANGAN[chineseEraYear % 10] + Annals.DIZHI[chineseEraYear % 12]); // 干支纪年
			_lunarDate.setLunarDate_Animal_Year(Annals.SHENGXIAO[chineseEraYear % 12]); // 该年对应的生肖
			_lunarDate.setLunarDate_China_Era_Year_Number(Annals.getNH(year));// 干支纪年
			
			getSetDates[i] = _lunarDate;
		}

		// 以下是月相与节气的处理
		double d;
		int xn;
		double jd2 = _year + Common.dt_T(_year) - (double) 8 / 24;
		// 月相查找
		w = AstronomyArithmetic.MS_aLon(jd2 / 36525, 10, 3);
		w = (int) Math.floor((w - 0.78) / Math.PI * 2) * Math.PI / 2;
		do {
			d = Annals.so_accurate(w);
			_day = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 4 + 4000000.01) % 4;
			w += Common.pi2 / 4;
			if (_day >= _year + _month) {
				break;
			}
			if (_day < _year) {
				continue;
			}
			LunarDate lunarDate = getSetDates[_day - _year];
			lunarDate.setMoon_PhaseName(Annals.YUEXIANG[xn]); // 取得月相名称
			lunarDate.setMoon_PhaseTime(d);
			lunarDate.setMoon_PhaseTimeStr(julianCalendar.timeStr(d));
		} while (_day + 5 < _year + _month);

		// 节气查找
		w = AstronomyArithmetic.S_aLon(jd2 / 36525, 3);
		w = (int) Math.floor((w - 0.13) / Common.pi2 * 24) * Common.pi2 / 24;
		do {
			d = Annals.qi_accurate(w);
			_day = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 24 + 24000006.01) % 24;
			w += Common.pi2 / 24;
			if (_day >= _year + _month) {
				break;
			}
			if (_day < _year) {
				continue;
			}
			LunarDate lunarDate = getSetDates[_day - _year];
			lunarDate.setLunar_SolarTerm_Name(Annals.JIEQI[xn]); // 取得节气名称
			lunarDate.setLunar_SolarTerm_Time(d);
			lunarDate.setLunar_SolarTerm_Time_Str(julianCalendar.timeStr(d));
		} while (_day + 12 < _year + _month);

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
					+" "+ Annals.JIEQI[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; // 日期转为字串
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
	
	public String[] getAllSolarTerm() {
		int nowYear = calendar.get(Calendar.YEAR);
		return getAllSolarTerm(nowYear);
	}
	
	public String[] getSolarTerm() {
		int nowYear = calendar.get(Calendar.YEAR);
		return getAllSolarTerm(nowYear);
	}

	
	public String getNextSolarTerm() {
		int year = this.lunarDate.getGregorian_Year();
		int month = this.lunarDate.getGregorian_Month();
		int day = this.lunarDate.getGregorian_Day();
		int nowDays = getJuLian(year, month, day);
		for (String str_1 : getAllSolarTerm(year)) {
			String[] strs_2 = str_1.split(" ");
			String[] strs_3 = strs_2[0].split("-");
			int y = Integer.valueOf(strs_3[0]);// 年
			int m = Integer.valueOf(strs_3[1]);// 月
			int d = Integer.valueOf(strs_3[2]);// 日
			int solartermDays = getJuLian(y, m, d);
			int cha = solartermDays - nowDays;
			if (nowDays <= solartermDays) {
				if (cha < 15 || cha == 0) {
					return str_1;
				} else {
					for (String str_4 : getAllSolarTerm((year - 1))) {
						String[] strs_5 = str_4.split(" ");
						String[] strs_6 = strs_5[0].split("-");
						int y1 = Integer.valueOf(strs_6[0]);// 年
						int m1 = Integer.valueOf(strs_6[1]);// 月
						int d1 = Integer.valueOf(strs_6[2]);// 日
						int solartermDays1 = getJuLian(y1, m1, d1);
						int cha1 = solartermDays1 - nowDays;
						if (nowDays <= solartermDays1) {
							if (cha1 < 15 || cha1 == 0) {
								return str_4;
							}
						}
					}
				}
			}

		}
		return getAllSolarTerm(year)[0];
	}
	
	private String getSloarDate(String str2) {
		String[] strs_2 = str2.split(" ");
		String[] strs_3 = strs_2[0].split("-");
		int y = Integer.valueOf(strs_3[0]);// 年
		int m = Integer.valueOf(strs_3[1]);// 月
		int d = Integer.valueOf(strs_3[2]);// 日
		return str2.substring(str2.length() - 2, str2.length()) + " " + y + "年" + m + "月" + d + "日"
				+ str2.substring(10, str2.length() - 2);
	}

}
