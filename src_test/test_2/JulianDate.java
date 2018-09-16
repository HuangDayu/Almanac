package test_2;

/****
 * http://blog.csdn.net/wangpeng047/article/details/38559591
 */
import test_2.LunarConstant.Common;

/******
 * ��������Julian calendar�����������͹����ù����ԡ�������������˹��������˹��������������ѧ�Ҽ�����ѧ����������ļ����
 * �ڹ�Ԫǰ45��1��1����ִ�е�ȡ��������������һ���������������У�һ�걻����Ϊ12���£���С�½��棻����һ��ƽ��365�գ�
 * ����366��Ϊ�ڵ�����µ�����һ���գ���ƽ������Ϊ365.25�ա�����ʵ��ʹ�ù������ۻ����������ʱ��Խ��Խ��
 * 1582��̻ʸ������ʮ�����䲼����������������Ϊ�������ƶ����ĸ�����������������Ĺ�����[
 * 
 * @author Administrator
 *
 */
public class JulianDate {

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
