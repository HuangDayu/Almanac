package test_7;

import java.util.Calendar;
import java.util.Date;
import test_7.LunarConstant.Common;
import test_7.LunarConstant.Oba;
import test_7.LunarConstant.Obb;
import test_7.LunarConstant.SSQ;
import test_7.LunarConstant.XL;
/****
 * http://blog.csdn.net/wangpeng047/article/details/38559591
 */
public class LunarCalendar {

	/** ����. ĳ��ȫ������������ */
	private LunarDate[] lunarDates;
	/** ���� */
	public  LunarDate lunarDate;

	/** ĳ����˷��������Ϣ */
	public SSQ ssq;

	/** lun.y ������� */
	private int year;
	/** lun.m �����·� */
	@SuppressWarnings("unused")
	private int month;
	/** lun.d0 ������������ */
	@SuppressWarnings("unused")
	private int dayRL;
	/** lun.dn ������ */
	@SuppressWarnings("unused")
	private int daysOfMonth;
	/** lun.w0 ���µ�һ������� */
	private int weekFirst;
	/** lun.Ly ��֧���� */
	@SuppressWarnings("unused")
	private String cnEraYear;
	/** lun.ShX �����Ӧ����Ф */
	private String animalYear;
	/** lun.nianhao ��ż��� */
	@SuppressWarnings("unused")
	private String nianHao;
	
	private int hour_Of_Day;
	

	public LunarCalendar() {
		this(new Date());
	}


	public LunarCalendar(Date date) {
		this(date.getTime());
	}

	private LunarCalendar(long TimeInMillis) {
		//System.out.println("TimeInMillis:"+TimeInMillis);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(TimeInMillis);
		this.hour_Of_Day = calendar.get(Calendar.HOUR_OF_DAY);
		//System.out.println(hour_Of_Day);
		yueLiCalc(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
		lunarDate = lunarDates[calendar.get(Calendar.DATE) - 1];
		
	}

	/***
	 * ũ����Ф
	 * 
	 * @return
	 */
	public String getAnimalString() {
		return animalYear;
	}

	/**
	 * ��ȡũ�������ַ���
	 */
	public String getDateString() {
		return lunarDate.getKingYear() + "��" + lunarDate.getLunarMonthName() + "��" + (isBigMonth() ? "��" : "С")
				+ lunarDate.getLunarDayName() + "��";
	}

	/***
	 * ��ȡ��֧ũ�������ַ���
	 * 
	 * @return
	 */
	public String getGanZhiDateString() {
		return lunarDate.getCnEraYear() + "��" + lunarDate.getCnEraMonth() + "��" + lunarDate.getCnEraDay() + "��"+lunarDate.getCnEraTime()+"ʱ";
	}

	/***
	 * ũ����
	 * 
	 * @return
	 */
	public int getDay() {
		return lunarDate.getLunarMonthOffset() + 1;
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getDayString() {
		return lunarDate.getLunarDayName();
	}

	/***
	 * ĳũ�����ж�����
	 * 
	 * @return
	 */
	public int getMaxDayInMonth() {
		return lunarDate.getDaysofLunarMonth();
	}

	/***
	 * ũ����
	 * 
	 * @return
	 */
	public int getMonth() {
		int month = 0;
		String monthName = lunarDate.getLunarMonthName();
		if ("��".equals(monthName)) {
			month = 1;
		} else if ("��".equals(monthName)) {
			month = 2;
		} else if ("��".equals(monthName)) {
			month = 3;
		} else if ("��".equals(monthName)) {
			month = 4;
		} else if ("��".equals(monthName)) {
			month = 5;
		} else if ("��".equals(monthName)) {
			month = 6;
		} else if ("��".equals(monthName)) {
			month = 7;
		} else if ("��".equals(monthName)) {
			month = 8;
		} else if ("��".equals(monthName)) {
			month = 9;
		} else if ("ʮ".equals(monthName)) {
			month = 10;
		} else if ("ʮһ".equals(monthName)) {
			month = 11;
		} else if ("ʮ��".equals(monthName)) {
			month = 12;
		}
		return month;
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getMonthString() {
		return lunarDate.getLunarMonthName();
	}

	/***
	 * ũ����
	 * 
	 * @return
	 */
	public int getYear() {
		return lunarDate.getKingYear();
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getYearString() {
		return lunarDate.getCnEraYear();
	}

	/***
	 * �Ƿ��Ǵ���
	 * 
	 * @return
	 */
	public boolean isBigMonth() {
		return lunarDate.getDaysofLunarMonth() > 29;
	}

	/***
	 * �Ƿ�������
	 * 
	 * @return
	 */
	public boolean isLeap() {
		return "��".equals(lunarDate.getLunarLunarLeap());
	}

	/**
	 * �Ƿ�������
	 * 
	 * @return
	 */
	public boolean isLeapYear() {
		return ssq.leap > 0;
	}

	/**
	 * ��ȡĳ��Ķ�ʮ�Ľ�����Ϣ
	 * 
	 * @return
	 */
	public String[] getAllSolarTerm() {
		return qiCalc(year);
	}

	/***
	 * ��ȡĳ�µ�ũ������
	 * 
	 * @return
	 */
	public LunarDate[] getMonthLunarDates() {
		return lunarDates;
	}

	/***
	 * ��ȡĳһ���ũ������
	 * 
	 * @return
	 */
	public LunarDate getDayLunarDate() {
		return lunarDate;
	}

	private void yueLiCalc(int year, int month) {
		int c, Bd0, Bdn;
		// ���������ʼ��
		ssq = new SSQ();
		JulianDate julianDate = new JulianDate();
		julianDate.setHour(12);
		julianDate.setMinute(0);
		julianDate.setSecond(0.1);
		julianDate.setYear(year);
		julianDate.setMonth(month);
		julianDate.setDay(1);
		Bd0 = (int) (Math.floor(julianDate.toJD()) - Common.J2000); // ��������,����
		julianDate.setMonth(julianDate.getMonth() + 1);
		if (julianDate.getMonth() > 12) {
			julianDate.setYear(julianDate.getYear() + 1);
			julianDate.setMonth(1);
		}
		Bdn = (int) (Math.floor(julianDate.toJD()) - Common.J2000 - Bd0); // ��������(����)

		this.weekFirst = (Bd0 + Common.J2000 + 1 + 7000000) % 7; // ���µ�һ�������
		this.year = year; // �������
		this.month = month; // �����·�
		this.dayRL = Bd0;
		this.daysOfMonth = Bdn;

		// �����������Ӧ��ũ����֧����
		c = year - 1984 + 12000;
		this.cnEraYear = Obb.Gan[c % 10] + Obb.Zhi[c % 12]; // ��֧����
		this.animalYear = Obb.ShX[c % 12]; // �����Ӧ����Ф
		this.nianHao = Obb.getNH(year);

		int D;
		double w;

		// ��ȡ������Ϣ
		LunarDate[] lunarDates = new LunarDate[Bdn];
		for (int i = 0, j = 0; i < Bdn; i++) {
			LunarDate lunarDate = new LunarDate();
			lunarDate.setDayRL(Bd0 + i); // ������,����ʱ12:00
			lunarDate.setDayIndex(i); // ��������������
			lunarDate.setYear(year); // ������
			lunarDate.setMonth(month); // ������
			lunarDate.setDaysOfMonth(Bdn); // ����������
			lunarDate.setWeekFirst(this.weekFirst); // ���׵�����
			lunarDate.setWeek((this.weekFirst + i) % 7); // ��ǰ�յ�����
			lunarDate.setWeekIndex((int) Math.floor((this.weekFirst + i) / 7)); // �������ڵ������
			lunarDate.setWeeksOfMonth((int) Math.floor((this.weekFirst + Bdn - 1) / 7) + 1); // ���µ�������
			julianDate.setFromJD(lunarDate.getDayRL() + Common.J2000);
			lunarDate.setDay(julianDate.getDay()); // ����������

			// ũ������
			// =======================
			// �˴����̰߳�ȫ����, ��ʱֱ��ʵ������ssq.calcY��Ϊ���ټ�������������жϡ���ͬһ������һ����
			if (lunarDate.getDayRL() < ssq.ZQ[0] || lunarDate.getDayRL() >= ssq.ZQ[24]) {
				ssq.calcY(lunarDate.getDayRL());
			}
			// =======================
			int mk = (int) Math.floor((lunarDate.getDayRL() - ssq.HS[0]) / 30);
			if (mk < 13 && ssq.HS[mk + 1] <= lunarDate.getDayRL())
			 {
				mk++; // ũ�������µ�����
			}

			lunarDate.setLunarMonthOffset(lunarDate.getDayRL() - ssq.HS[mk]); // ��ũ�����׵ı�����,0��Ӧ��һ
			lunarDate.setLunarDayName(Obb.rmc[lunarDate.getLunarMonthOffset()]); // ũ��������
			lunarDate.setDaysToDZ(lunarDate.getDayRL() - ssq.ZQ[0]); // �ඬ��������
			lunarDate.setDaysToXZ(lunarDate.getDayRL() - ssq.ZQ[12]); // ������������
			lunarDate.setDaysToLQ(lunarDate.getDayRL() - ssq.ZQ[15]); // �����������
			lunarDate.setDaysToMZ(lunarDate.getDayRL() - ssq.ZQ[11]); // ��â�ֵ�����
			lunarDate.setDaysToXS(lunarDate.getDayRL() - ssq.ZQ[13]); // ��С�������
			if (lunarDate.getDayRL() == ssq.HS[mk] || lunarDate.getDayRL() == Bd0) { // �µ���Ϣ
				lunarDate.setLunarMonthName(ssq.ym[mk]); // ������
				lunarDate.setDaysofLunarMonth(ssq.dx[mk]); // �´�С
				lunarDate.setLunarLunarLeap((ssq.leap != 0 && ssq.leap == mk) ? "��" : ""); // ��״��
				lunarDate.setNextLunarMonthName(mk < 13 ? ssq.ym[mk + 1] : "δ֪"); // �¸�������,�жϳ�ϦʱҪ�õ�
			} else {
				LunarDate lunarDate2 = lunarDates[i - 1];
				lunarDate.setLunarMonthName(lunarDate2.getLunarMonthName());
				lunarDate.setDaysofLunarMonth(lunarDate2.getDaysofLunarMonth());
				lunarDate.setLunarLunarLeap(lunarDate2.getLunarLunarLeap());
				lunarDate.setNextLunarMonthName(lunarDate2.getNextLunarMonthName());
			}
			int qk = (int) Math.floor((lunarDate.getDayRL() - ssq.ZQ[0] - 7) / 15.2184);
			if (qk < 23 && lunarDate.getDayRL() >= ssq.ZQ[qk + 1])
			 {
				qk++; // ������ȡֵ��Χ��0-23
			}
			if (lunarDate.getDayRL() == ssq.ZQ[qk]) {
				lunarDate.setLunarSolarTerm(Obb.jqmc[qk]);
			} else {
				lunarDate.setLunarSolarTerm("");
			}

			// ob.yxmc = ob.yxjd = ob.yxsj ="";//��������,����ʱ��(������),����ʱ�䴮
			// ob.jqmc = ob.jqjd = ob.jqsj ="";//��������,����ʱ��(������),����ʱ�䴮

			// ��֧���괦��
			// ������Ϊ�綨����
			D = (int) (ssq.ZQ[3] + (lunarDate.getDayRL() < ssq.ZQ[3] ? -365 : 0) + 365.25 * 16 - 35); // ������Ϊ�綨����
			lunarDate.setLunarYear((int) Math.floor(D / 365.2422 + 0.5)); // ũ������(10����,1984������)
			// ���¼��������³�һ������
			D = ssq.HS[2]; // һ���3����Ϊ����
			for (j = 0; j < 14; j++) { // �Ҵ���
				if (!ssq.ym[j].equals("��") || ssq.leap == j && j != 0) {
					continue;
				}
				D = ssq.HS[j];
				if (lunarDate.getDayRL() < D) {
					D -= 365;
					break;
				} // ����������һ������
			}
			D = D + 5810; // ������괺����1984��ƽ������(��������)�����������
			int Lyear0 = (int) Math.floor(D / 365.2422 + 0.5); // ũ������(10����,1984������)

			D = lunarDate.getLunarYear() + 12000;
			lunarDate.setCnEraYear(Obb.Gan[D % 10] + Obb.Zhi[D % 12]); // ��֧����(����)
			D = Lyear0 + 12000;
			// String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // ��֧����(����) ,
			// ��Lyear2�������ڴ��ڲ���Ϊ����
			lunarDate.setKingYear(Lyear0 + 1984 + 2698); // �Ƶۼ���

			// ���´���,1998��12��7(��ѩ)��ʼ�������н�������,0Ϊ����
			mk = (int) Math.floor((lunarDate.getDayRL() - ssq.ZQ[0]) / 30.43685);
			if (mk < 12 && lunarDate.getDayRL() >= ssq.ZQ[2 * mk + 1])
			 {
				mk++; // ��Դ�ѩ����������,mk��ȡֵ��Χ0-12
			}

			D = mk + (int) Math.floor((ssq.ZQ[12] + 390) / 365.2422) * 12 + 900000; // �����1998��12��7(��ѩ)������,900000Ϊ��������
			lunarDate.setLunarMonth(D % 12);
			lunarDate.setCnEraMonth(Obb.Gan[D % 10] + Obb.Zhi[D % 12]);

			// ����,2000��1��7������
			D = lunarDate.getDayRL() - 6 + 9000000;
			lunarDate.setCnEraDay(Obb.Gan[D % 10] + Obb.Zhi[D % 12]);
			
			
			/************���������д****************/
			int shi = this.hour_Of_Day;
			shi = (shi + 1) / 2;
			lunarDate.setCnEraTime(Obb.Gan[(shi + D * 12) % 10] + Obb.Zhi[shi % 12]);
			

			// ����
			mk = (int) Math.floor((lunarDate.getDayRL() - ssq.ZQ[0] - 15) / 30.43685);
			if (mk < 11 && lunarDate.getDayRL() >= ssq.ZQ[2 * mk + 2])
			 {
				mk++; // ���������µ�����,(���j=13,ob.d0���ᳬ����14������)
			}
			lunarDate.setConstellation(Obb.XiZ[(mk + 12) % 12] + "��");
			// ����
			Oba.getHuiLi(lunarDate.getDayRL(), lunarDate);
			// ����
			// ob.A = ob.B = ob.C = ""; ob.Fjia = 0;
			Oba.getDayName(lunarDate, lunarDate); // ����
			Obb.getDayName(lunarDate, lunarDate); // ũ��

			lunarDates[i] = lunarDate;
		}

		// ����������������Ĵ���
		double d;
		int xn;
		double jd2 = Bd0 + Common.dt_T(Bd0) - (double) 8 / 24;
		// �������
		w = XL.MS_aLon(jd2 / 36525, 10, 3);
		w = (int) Math.floor((w - 0.78) / Math.PI * 2) * Math.PI / 2;
		do {
			d = Obb.so_accurate(w);
			D = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 4 + 4000000.01) % 4;
			w += Common.pi2 / 4;
			if (D >= Bd0 + Bdn) {
				break;
			}
			if (D < Bd0) {
				continue;
			}
			LunarDate lunarDate = lunarDates[D - Bd0];
			lunarDate.setMoonPhaseName(Obb.yxmc[xn]); // ȡ����������
			//System.out.println(Obb.yxmc[xn]);
			lunarDate.setMoonPhaseTime(d);
			lunarDate.setMoonPhaseTimeStr(julianDate.timeStr(d));
		} while (D + 5 < Bd0 + Bdn);

		// ��������
		w = XL.S_aLon(jd2 / 36525, 3);
		w = (int) Math.floor((w - 0.13) / Common.pi2 * 24) * Common.pi2 / 24;
		do {
			d = Obb.qi_accurate(w);
			D = (int) Math.floor(d + 0.5);
			xn = (int) Math.floor(w / Common.pi2 * 24 + 24000006.01) % 24;
			w += Common.pi2 / 24;
			if (D >= Bd0 + Bdn) {
				break;
			}
			if (D < Bd0) {
				continue;
			}
			LunarDate lunarDate = lunarDates[D - Bd0];
			lunarDate.setSolarTermName(Obb.jqmc[xn]); // ȡ�ý�������
			lunarDate.setSolarTermTime(d);
			lunarDate.setSolarTermTimeStr(julianDate.timeStr(d));
		} while (D + 12 < Bd0 + Bdn);

		this.lunarDates = lunarDates;
	}

	/***
	 * �������Ժ���
	 * 
	 * @param year
	 * @return
	 */
	private String[] qiCalc(int year) {
		String[] solarTerms = new String[24];
		double T;
		// String s = "", s2 = "";
		int y = Common.year2Ayear(String.valueOf(year)) - 2001;
		int n = 24;
		for (int i = 21, index = 0; i < n;) {
			T = XL.S_aLon_t((y + (double) i * 15 / 360 + 1) * 2 * Math.PI); // ��ȷ����ʱ�����
			// s2 += new JulianDate().JD2str(T * 36525 + Common.J2000 + (double) 8 / 24 -
			// Common.dt_T(T * 36525))
			// + Obb.jqmc[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; //����תΪ�ִ�
			String solarTerm = new JulianDate()
					.JD2str(T * 36525 + Common.J2000 + (double) 8 / 24 - Common.dt_T(T * 36525))
					+ Obb.jqmc[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; // ����תΪ�ִ�
			solarTerms[index] = solarTerm.trim();
			// if (i % 2 == 0)
			// s2 += " �ӻƾ�" + (i * 15) + "\n";
			// else
			// s2 += " ";
			// if (i % 50 == 0) {
			// s += s2;
			// s2 = "";
			// }
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
class JulianDate {

	/** ���ڹ����� */
	private int year = 2000;
	/** ���ڹ����� */
	private int month = 1;
	/** ���ڹ����� */
	private int day = 1;
	/** ���ڹ���Сʱ */
	private int hour = 12;
	/** ���ڹ������� */
	private int minute = 0;
	/** ���ڹ������� */
	private double second = 0;

	// private final String[] weeks = { "��", "һ", "��", "��", "��", "��", "��", "��" };

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public double getSecond() {
		return second;
	}

	public void setSecond(double second) {
		this.second = second;
	}

	/***
	 * ����ת������
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private double JD(int year, int month, double day) {
		int n = 0, G = 0;
		if (year * 372 + month * 31 + Math.floor(day) >= 588829)
		 {
			G = 1; // �ж��Ƿ�Ϊ�����������1582*372+10*31+15
		}
		if (month <= 2) {
			month += 12;
			year--;
		}
		if (G != 0) {
			n = (int) Math.floor(year / 100);
			n = 2 - n + (int) Math.floor(n / 4); // �Ӱ�����
		}
		return Math.floor(365.25 * (year + 4716)) + Math.floor(30.6001 * (month + 1)) + day + n - 1524.5;
	}

	/****
	 * ��������ת����
	 * 
	 * @param jd
	 * @return
	 */
	private JulianDate DD(double jd) {
		JulianDate julianDate = new JulianDate();
		int year, month, day, hour, minute;
		double second;
		int D = (int) Math.floor(jd + 0.5), c;
		double F = jd + 0.5 - D; // ȡ����������������A��С������F

		if (D >= 2299161) {
			c = (int) Math.floor((D - 1867216.25) / 36524.25);
			D += 1 + c - Math.floor(c / 4);
		}
		D += 1524;
		year = (int) Math.floor((D - 122.1) / 365.25);// ����
		D -= Math.floor(365.25 * year);
		month = (int) Math.floor(D / 30.601); // ����
		D -= Math.floor(30.601 * month);
		day = D; // ����
		if (month > 13) {
			month -= 13;
			year -= 4715;
		} else {
			month -= 1;
			year -= 4716;
		}
		// �յ�С��תΪʱ����
		F *= 24;
		hour = (int) Math.floor(F);
		F -= hour;
		F *= 60;
		minute = (int) Math.floor(F);
		F -= minute;
		F *= 60;
		second = F;

		julianDate.setYear(year);
		julianDate.setMonth(month);
		julianDate.setDay(day);
		julianDate.setHour(hour);
		julianDate.setMinute(minute);
		julianDate.setSecond(second);
		return julianDate;
	}

	/***
	 * ����תΪ��
	 * 
	 * @param julianDate
	 * @return
	 */
	private String DD2str(JulianDate julianDate) {
		String Y = "     " + julianDate.getYear(), M = "0" + julianDate.getMonth(), D = "0" + julianDate.getDay();
		int h = julianDate.getHour(), m = julianDate.getMinute(), s = (int) Math.floor(julianDate.getSecond() + .5);
		if (s >= 60) {
			s -= 60;
			m++;
		}
		if (m >= 60) {
			m -= 60;
			h++;
		}
		String hStr, mStr, sStr;
		hStr = "0" + h;
		mStr = "0" + m;
		sStr = "0" + s;
		Y = Common.subString(Y, Y.length() - 5);
		M = Common.subString(M, M.length() - 2);
		D = Common.subString(D, D.length() - 2);
		hStr = Common.subString(hStr, hStr.length() - 2);
		mStr = Common.subString(mStr, mStr.length() - 2);
		sStr = Common.subString(sStr, sStr.length() - 2);
		return Y + "-" + M + "-" + D + " " + hStr + ":" + mStr + ":" + sStr;
	}

	/****
	 * JDתΪ��
	 * 
	 * @param jd
	 * @return
	 */
	protected String JD2str(double jd) {
		JulianDate julianDate = this.DD(jd);
		return this.DD2str(julianDate);
	}

	/****
	 * ����ת������
	 * 
	 * @return
	 */
	protected double toJD() {
		return this.JD(this.year, this.month, this.day + ((this.second / 60 + this.minute) / 60 + this.hour) / 24);
	}

	/****
	 * ��������ת����
	 * 
	 * @param jd
	 */
	protected void setFromJD(double jd) {
		JulianDate julianDate = this.DD(jd);
		this.year = julianDate.getYear();
		this.month = julianDate.getMonth();
		this.day = julianDate.getDay();
		this.minute = julianDate.getMinute();
		this.hour = julianDate.getHour();
		this.second = julianDate.getSecond();
	}

	/***
	 * ��ȡjd�е�ʱ��(ȥ������)
	 * 
	 * @param jd
	 * @return
	 */
	protected String timeStr(double jd) {

		int h, m, s;
		jd += 0.5;
		jd = (jd - Math.floor(jd));
		s = (int) Math.floor(jd * 86400 + 0.5);
		h = (int) Math.floor(s / 3600);
		s -= h * 3600;
		m = (int) Math.floor(s / 60);
		s -= m * 60;
		String hStr, mStr, sStr;
		hStr = "0" + h;
		mStr = "0" + m;
		sStr = "0" + s;
		return Common.subString(hStr, hStr.length() - 2, hStr.length()) + ":"
				+ Common.subString(mStr, mStr.length() - 2, mStr.length()) + ":"
				+ Common.subString(sStr, sStr.length() - 2, sStr.length());
	}

	/****
	 * ���ڼ���
	 * 
	 * @param jd
	 * @return
	 */
	protected int getWeek(double jd) {
		return (int) Math.floor(jd + 1.5 + 7000000) % 7;
	}

	/****
	 * ��y��m�µĵ�n������w����������
	 * 
	 * @param year
	 * @param month
	 * @param n
	 * @param week
	 * @return
	 */
	protected int nnweek(int year, int month, int n, int week) {
		// =================�����д�

		// ��y��m�µĵ�n������w����������
		double jd = this.JD(year, month, 1.5); // ����������
		double w0 = (jd + 1 + 7000000) % 7; // ���׵�����
		int r = (int) (jd - w0 + 7 * n + week);
		// jd-w0+7*n�Ǻ�n������0,�����±��µ�һ�е�������(����������һ��)����w��Ϊ��n������w
		if (week >= w0)
		 {
			r -= 7; // ��1������w���������ϸ���,��ɶ���1��,���Կ��Ǽ�1��
		}
		if (n == 5) {
			month++;
			if (month > 12) {
				month = 1;
				year++;
			} // �¸���
			if (r >= this.JD(year, month, 1.5))
			 {
				r -= 7; // r�ܵ��¸������1��
			}
		}
		return r;
	}
}
}
