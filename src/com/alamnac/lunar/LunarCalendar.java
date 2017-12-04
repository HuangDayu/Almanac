package com.alamnac.lunar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.alamnac.lunar.Annals;
import com.alamnac.lunar.AstronomyArithmetic;
import com.alamnac.lunar.Common;
import com.alamnac.lunar.FestivalAndHoliday;
import com.alamnac.lunar.QiShuo;

public class LunarCalendar {

	/** ����. ĳ��ȫ������������ */
	private LunarDate[] lunarDates;
	/** ���� */
	public LunarDate lunarDate;
	/** ĳ����˷��������Ϣ */
	private QiShuo qiShuo;
	
	Calendar calendar = Calendar.getInstance();

	/** ���µ�һ������� */
	private int lc_WeekFirst_Fo_Month;
	

	public LunarCalendar() {
		this(new Date());
	}

	public LunarCalendar(Date date) {
		this(date.getTime());
	}

	private LunarCalendar(long TimeInMillis) {
		
		calendar.setTimeInMillis(TimeInMillis);
		lunarCalendarCalc(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.HOUR_OF_DAY));
		lunarDate = lunarDates[calendar.get(Calendar.DATE) - 1];
	}
	/***
	 * ��ȡũ�������༯�϶���
	 * 
	 * @return
	 */
	public LunarDate[] getLunarDateListObj() {
		return lunarDates;
	}

	/***
	 * ��ȡũ�����������
	 * 
	 * @return
	 */
	public LunarDate getLunarDateClassObj() {
		return lunarDate;
	}
	/***
	 * ��ȡ��˷�����
	 * @return
	 */
	public QiShuo getQiShuoClassObj() {
		return qiShuo;
	}
	

	/**
	 * ��ȡĳ��Ķ�ʮ�Ľ�����Ϣ
	 * 
	 * @return
	 */
	public String[] getAllSolarTerm() {
		return set24SolarTerm(calendar.get(Calendar.YEAR));
	}

	

	/***
	 * ũ�����㷽�������췽����ʼ����ȡÿ���µ�1��
	 * 
	 * @param year
	 * @param month
	 */
	private void lunarCalendarCalc(int year, int month, int hours) {
		int ChineseEra_Year, julianMutualValue_year, julianMutualValue_month;
		// ���������ʼ��
		qiShuo = new QiShuo();
		JulianCalendar julianCalendar = new JulianCalendar(year, month, 1, 12, 0, 0.1);
		julianMutualValue_year = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000); // ��������,����
		julianCalendar.setMonth(julianCalendar.getMonth() + 1);
		if (julianCalendar.getMonth() > 12) {
			julianCalendar.setYear(julianCalendar.getYear() + 1);
			julianCalendar.setMonth(1);
		}
		julianMutualValue_month = (int) (Math.floor(julianCalendar.getJulianDayNumber()) - Common.Julian_for_2000
				- julianMutualValue_year); // ��������(����)

		this.lc_WeekFirst_Fo_Month = (julianMutualValue_year + Common.Julian_for_2000 + 1 + 7000000) % 7; // ���µ�һ�������


		// �����������Ӧ��ũ����֧����
		ChineseEra_Year = year - 1984 + 12000;
		
		//Ϊ��̬����
		LunarDate lunarDateCalc1 = new LunarDate();
		lunarDateCalc1.setLunarDate_China_Era_Year(Annals.TIANGAN[ChineseEra_Year % 10] + Annals.DIZHI[ChineseEra_Year % 12]); // ��֧����
		lunarDateCalc1.setLunarDate_Animal_Year(Annals.SHENGXIAO[ChineseEra_Year % 12]); // �����Ӧ����Ф
		lunarDateCalc1.setLunarDate_China_Era_Year_Number(Annals.getNH(year));//��֧����
		
		// ������
		int julian_Days;
		// �����ӻƾ��Ĳ�ֵ
		double w;

		// ��ȡ������Ϣ
		LunarDate[] getSetDates = new LunarDate[julianMutualValue_month];
		
		for (int i = 0, j = 0; i < julianMutualValue_month; i++) {
			LunarDate lunarDateCalc = new LunarDate();
			lunarDateCalc.setJulian_Days(julianMutualValue_year + i); // ������,����ʱ12:00
			lunarDateCalc.setGregorian_DayIndexForMonth(i); // ��������������
			lunarDateCalc.setGregorian_Year(year); // ������
			lunarDateCalc.setGregorian_Month(month); // ������
			lunarDateCalc.setGregorian_DaysOfMonth(julianMutualValue_month); // ����������
			lunarDateCalc.setGregorian_WeekFirstForMonth(this.lc_WeekFirst_Fo_Month); // ���׵�����
			lunarDateCalc.setGregorian_Week((this.lc_WeekFirst_Fo_Month + i) % 7); // ��ǰ�յ�����
			lunarDateCalc.setGregorian_WeekIndexForMonth((int) Math.floor((this.lc_WeekFirst_Fo_Month + i) / 7)); // �������ڵ������
			lunarDateCalc.setGregorian_WeeksOfMonth(
					(int) Math.floor((this.lc_WeekFirst_Fo_Month + julianMutualValue_month - 1) / 7) + 1); // ���µ�������
			julianCalendar.setFromJD(lunarDateCalc.getJulian_Days() + Common.Julian_for_2000);
			lunarDateCalc.setGregorian_Day(julianCalendar.getDay()); // ����������

			// ũ������
			// =======================
			// �˴����̰߳�ȫ����, ��ʱֱ��ʵ������ssq.calcY��Ϊ���ټ�������������жϡ���ͬһ������һ����
			if (lunarDateCalc.getJulian_Days() < qiShuo.ZQ[0] || lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[24]) {
				qiShuo.calcY(lunarDateCalc.getJulian_Days());
			}
			// =======================
			int mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.HS[0]) / 30);
			if (mk < 13 && qiShuo.HS[mk + 1] <= lunarDateCalc.getJulian_Days()) {
				mk++; // ũ�������µ�����
			}

			lunarDateCalc.setLunar_MonthOffset(lunarDateCalc.getJulian_Days() - qiShuo.HS[mk]); // ��ũ�����׵ı�����,0��Ӧ��һ
			lunarDateCalc.setLunar_DayName(Annals.DAY_NAME[lunarDateCalc.getLunar_MonthOffset()]); // ũ��������
			lunarDateCalc.setLunar_Last_Dongzhi(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0]); // �ඬ��������
			lunarDateCalc.setLunar_Last_Xiazhi(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[12]); // ������������
			lunarDateCalc.setLunar_Last_Liqiu(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[15]); // �����������
			lunarDateCalc.setLunar_Last_Mangzhong(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[11]); // ��â�ֵ�����
			lunarDateCalc.setLunar_Last_Xiaoshu(lunarDateCalc.getJulian_Days() - qiShuo.ZQ[13]); // ��С�������
			if (lunarDateCalc.getJulian_Days() == qiShuo.HS[mk] || lunarDateCalc.getJulian_Days() == julianMutualValue_year) { // �µ���Ϣ
				lunarDateCalc.setLunar_MonthName(qiShuo.ym[mk]); // ������
				lunarDateCalc.setLunar_Days_OfMonth(qiShuo.dx[mk]); // �´�С
				lunarDateCalc.setLunar_Lunar_isLeap((qiShuo.leap != 0 && qiShuo.leap == mk) ? "��" : ""); // ��״��
				lunarDateCalc.setLunar_NextLunarMonthName(mk < 13 ? qiShuo.ym[mk + 1] : "δ֪"); // �¸�������,�жϳ�ϦʱҪ�õ�
			} else {
				LunarDate lunarDate2 = getSetDates[i - 1];
				lunarDateCalc.setLunar_MonthName(lunarDate2.getLunar_MonthName());
				lunarDateCalc.setLunar_Days_OfMonth(lunarDate2.getLunar_Days_OfMonth());
				lunarDateCalc.setLunar_Lunar_isLeap(lunarDate2.getLunar_Lunar_isLeap());
				lunarDateCalc.setLunar_NextLunarMonthName(lunarDate2.getLunar_NextLunarMonthName());
			}
			int qk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0] - 7) / 15.2184);
			if (qk < 23 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[qk + 1]) {
				qk++; // ������ȡֵ��Χ��0-23
			}
			if (lunarDateCalc.getJulian_Days() == qiShuo.ZQ[qk]) {
				lunarDateCalc.setLunar_SolarTerm(Annals.JIEQI[qk]);
			} else {
				lunarDateCalc.setLunar_SolarTerm("");
			}

			// ob.yxmc = ob.yxjd = ob.yxsj ="";//��������,����ʱ��(������),����ʱ�䴮
			// ob.jqmc = ob.jqjd = ob.jqsj ="";//��������,����ʱ��(������),����ʱ�䴮

			// ��֧���괦��
			// ������Ϊ�綨����
			julian_Days = (int) (qiShuo.ZQ[3] + (lunarDateCalc.getJulian_Days() < qiShuo.ZQ[3] ? -365 : 0) + 365.25 * 16
					- 35); // ������Ϊ�綨����
			lunarDateCalc.setLunar_Period_Of_Years((int) Math.floor(julian_Days / 365.2422 + 0.5)); // ũ������(10����,1984������)
			// ���¼��������³�һ������
			julian_Days = qiShuo.HS[2]; // һ���3����Ϊ����
			for (j = 0; j < 14; j++) { // �Ҵ���
				if (!qiShuo.ym[j].equals("��") || qiShuo.leap == j && j != 0) {
					continue;
				}
				julian_Days = qiShuo.HS[j];
				if (lunarDateCalc.getJulian_Days() < julian_Days) {
					julian_Days -= 365;
					break;
				} // ����������һ������
			}
			julian_Days = julian_Days + 5810; // ������괺����1984��ƽ������(��������)�����������
			int Lyear0 = (int) Math.floor(julian_Days / 365.2422 + 0.5); // ũ������(10����,1984������)

			julian_Days = lunarDateCalc.getLunar_Period_Of_Years() + 12000;
			lunarDateCalc.setChineseEra_Year(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]); // ��֧����(����)
			julian_Days = Lyear0 + 12000;
			// String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // ��֧����(����) ,
			// ��Lyear2�������ڴ��ڲ���Ϊ����
			lunarDateCalc.setLunar_king_Years(Lyear0 + 1984 + 2698); // �Ƶۼ���

			// ���´���,1998��12��7(��ѩ)��ʼ�������н�������,0Ϊ����
			mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0]) / 30.43685);
			if (mk < 12 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[2 * mk + 1]) {
				mk++; // ��Դ�ѩ����������,mk��ȡֵ��Χ0-12
			}

			julian_Days = mk + (int) Math.floor((qiShuo.ZQ[12] + 390) / 365.2422) * 12 + 900000; // �����1998��12��7(��ѩ)������,900000Ϊ��������
			lunarDateCalc.setLunar_Period_Of_Months(julian_Days % 12);
			lunarDateCalc.setChineseEra_Month(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]);

			// ����,2000��1��7������
			julian_Days = lunarDateCalc.getJulian_Days() - 6 + 9000000;
			lunarDateCalc.setChineseEra_Day(Annals.TIANGAN[julian_Days % 10] + Annals.DIZHI[julian_Days % 12]);

			/************ ��ɵ�֧ʱ�����������д���㷨�������� ****************/
			int shi = (hours + 1) / 2;
			lunarDateCalc.setChineseEra_Time(Annals.TIANGAN[(shi + julian_Days * 12) % 10] + Annals.DIZHI[shi % 12]);
			/************ ��ɵ�֧ʱ�����������д���㷨�������� ****************/

			// ����
			mk = (int) Math.floor((lunarDateCalc.getJulian_Days() - qiShuo.ZQ[0] - 15) / 30.43685);
			if (mk < 11 && lunarDateCalc.getJulian_Days() >= qiShuo.ZQ[2 * mk + 2]) {
				mk++; // ���������µ�����,(���j=13,ob.d0���ᳬ����14������)
			}
			lunarDateCalc.setConstellation(Annals.XINGZUO[(mk + 12) % 12] + "��");
			// ����
			IslamicCalendar.setIslamicCalendar(lunarDateCalc.getJulian_Days(), lunarDateCalc);
			// ����
			// ob.A = ob.B = ob.C = ""; ob.Fjia = 0;
			
			//���㹫������
			FestivalAndHoliday.getDayName(lunarDateCalc, lunarDateCalc); // ����
			//����ũ������
			Annals.getDayName(lunarDateCalc, lunarDateCalc); // ũ��

			getSetDates[i] = lunarDateCalc;
		}

		// ����������������Ĵ���
		double d;
		int xn;
		double jd2 = julianMutualValue_year + Common.dt_T(julianMutualValue_year) - (double) 8 / 24;
		// �������
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
			lunarDate.setMoon_PhaseName(Annals.YUEXIANG[xn]); // ȡ����������
			lunarDate.setMoon_PhaseTime(d);
			lunarDate.setMoon_PhaseTimeStr(julianCalendar.timeStr(d));
		} while (julian_Days + 5 < julianMutualValue_year + julianMutualValue_month);

		// ��������
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
			lunarDate.setLunar_SolarTerm_Name(Annals.JIEQI[xn]); // ȡ�ý�������
			lunarDate.setLunar_SolarTerm_Time(d);
			lunarDate.setLunar_SolarTerm_Time_Str(julianCalendar.timeStr(d));
		} while (julian_Days + 12 < julianMutualValue_year + julianMutualValue_month);

		this.lunarDates = getSetDates;//ʵ��������
	}

	/***
	 * �������Ժ���
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
			T = AstronomyArithmetic.S_aLon_t((y + (double) i * 15 / 360 + 1) * 2 * Math.PI); // ��ȷ����ʱ�����
			// s2 += new JulianDate().JD2str(T * 36525 + Common.J2000 + (double) 8 / 24 -
			// Common.dt_T(T * 36525))
			// + Obb.jqmc[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; //����תΪ�ִ�
			String solarTerm = new JulianCalendar()
					.JD2str(T * 36525 + Common.Julian_for_2000 + (double) 8 / 24 - Common.dt_T(T * 36525))
					+ Annals.JIEQI[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; // ����תΪ�ִ�
			solarTerms[index] = solarTerm.trim();
			// if (i % 2 == 0) s2 += " �ӻƾ�" + (i * 15) + "\n";
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
	 * �����꣬�£������������ http://www.ilovematlab.cn/thread-19002-1-1.html
	 * 
	 * @param I
	 *            ��
	 * @param J
	 *            ��
	 * @param K
	 *            ��
	 * @return ������
	 */
	public int getJuLian(int I, int J, int K) {
		int jl = K - 32075 + 1461 * (I + 4800 + (J - 14) / 12) / 4 + 367 * (J - 2 - (J - 14) / 12 * 12) / 12
				- 3 * ((I + 4900 + (J - 14) / 12) / 100) / 4;
		return jl;
	}
	/***
	 * ��ȡ�����������µĽ���
	 * @param calendar
	 * @return
	 */
	public String getNowSolarTerm() {
		String[] strs = this.getAllSolarTerm();
		String str1 = null,str2 =null,str3=null;
		int year = this.lunarDate.getGregorian_Year();
		int month = this.lunarDate.getGregorian_Month();
		int day = this.lunarDate.getGregorian_Day();
		
		int now = getJuLian(year,month,day);
		
		for (String str : strs) {
			String[] strs_2 = str.split(" ");
			
			String[] strs_3 = strs_2[0].split("-");
			//str1 = str.substring(0, 4);
			//str2 = str.substring(5, 7);
			//str3 = str.substring(8, 10);
			int a = Integer.valueOf(strs_3[0]);
			int b = Integer.valueOf(strs_3[1]);
			int c = Integer.valueOf(strs_3[2]);
			
			int solarterm = getJuLian(a,b,c);
			if(now <= solarterm) {
				str = str.substring(str.length()-2,str.length())+" "+strs_3[1]+"��"+strs_3[2]+"��"+str.substring(10,str.length()-2);
				//str = str.substring(str.length()-2,str.length())+str.substring(0,str.length()-2);
				return str;
			}
		}
		
		return null;
	}


	public void DateToCalendar(Date date) {
		getDateAndCalendar(date.getTime());
	}
	
	private Calendar getDateAndCalendar(long TimeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(TimeInMillis);
		return calendar;
	}
	
	/***
	 * ָ��Date��ʱ��  3������
	 * http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 */
	public static Date getCalendarToDate(int y, int M, int d) {
		String str = y + "-" + M + "-" + d;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	
	/***
	 * ָ��Date��ʱ��  6������
	 * @param y ��
	 * @param M ��
	 * @param d ��
	 * @param h ʱ
	 * @param m ��
	 * @param s ��
	 * @return Date����
	 */
	public static Date getCalendarToDate(int y, int M, int d,int h,int m,int s) {
		String str = y + "-" + M + "-" + d+" "+h+":"+m+":"+s;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	
}
