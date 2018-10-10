package test_11;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһЩũ�������Ϣ
 * 
 */
public class Lunar {

	private final static int[] lunarInfo = { 0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0,
			0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0,
			0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf,
			0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f, 0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
			0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6,
			0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5, 0x4ad0, 0xa4d0,
			0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6, 0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40,
			0xaf46, 0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
			0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0, 0xbaa4,
			0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930, 0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6,
			0xa4e0, 0xd260, 0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520,
			0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
			0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef, 0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0,
			0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4,
			0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968,
			0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520 };
	private final static int[] solarTermInfo = { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
			218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532,
			504758 };
	public final static String[] Tianan = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] Deqi = { "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��" };
	public final static String[] Animals = { "��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] solarTerm = { "С��", "��", "����", "��ˮ", "����", "����", "����", "����", "����", "С��", "â��", "����",
			"С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ", "��ѩ", "����" };
	public final static String[] lunarString1 = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] lunarString2 = { "��", "ʮ", "إ", "ئ", "��", "��", "��", "��" };

	/**
	 * �������� *��ʾ�ż���
	 */
	private final static String[] sFtv = { "0101*Ԫ��", "0115 Ԫ����", "0214 ���˽�", "0308 ��Ů��", "0312 ֲ����", "0315 315",
			"0401 ���˽�", "0501*�Ͷ���", "0504 �����", "0509 ��ά��", "0512 ��ʿ��", "0601 ��ͯ��", "0701 ��ۻع�", "0801 ������",
			"0816 �������", "0909 ë������", "0910 ��ʦ��", "0928 ���ӵ���", "1001*�����", "1006 ���˽�", "1024 ���Ϲ���", "1111 �����",
			"1112 ����ɽ����", "1220 ���Żع�", "1225 ʥ����", "1226 ë�󶫵���" };
	/**
	 * ũ������ *��ʾ�ż���
	 */
	private final static String[] lFtv = { "0101*����", "0106 �����", "0115 Ԫ����", "0505 �����", "0707 ��Ϧ", "0715 ��Ԫ��",
			"0808 ���׽�", "0815 �����", "0909 ������", "1208 ���˽�", "1224 С��", "0100*��Ϧ" };
	/**
	 * ĳ�µĵڼ������ڼ�
	 */
	private static String[] wFtv = { "0520 ĸ�׽�", "0716 ������", "0730 ��ū�۹�����" };

	private static int toInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	private final static Pattern sFreg = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
	private final static Pattern wFreg = Pattern.compile("^(\\d{2})(\\d)(\\d)([\\s\\*])(.+)$");

	private synchronized void findFestival() {
		int sM = this.getSolarMonth();
		int sD = this.getSolarDay();
		int lM = this.getLunarMonth();
		int lD = this.getLunarDay();
		int sy = this.getSolarYear();
		Matcher m;
		for (int i = 0; i < Lunar.sFtv.length; i++) {
			m = Lunar.sFreg.matcher(Lunar.sFtv[i]);
			if (m.find()) {
				if (sM == Lunar.toInt(m.group(1)) && sD == Lunar.toInt(m.group(2))) {
					this.isSFestival = true;
					this.sFestivalName = m.group(4);
					if ("*".equals(m.group(3)))
						this.isHoliday = true;
					break;
				}
			}
		}
		for (int i = 0; i < Lunar.lFtv.length; i++) {
			m = Lunar.sFreg.matcher(Lunar.lFtv[i]);
			if (m.find()) {
				if (lM == Lunar.toInt(m.group(1)) && lD == Lunar.toInt(m.group(2))) {
					this.isLFestival = true;
					this.lFestivalName = m.group(4);
					if ("*".equals(m.group(3)))
						this.isHoliday = true;
					break;
				}
			}
		}

		// ���ܽ���
		int w, d;
		for (int i = 0; i < Lunar.wFtv.length; i++) {
			m = Lunar.wFreg.matcher(Lunar.wFtv[i]);
			if (m.find()) {
				if (this.getSolarMonth() == Lunar.toInt(m.group(1))) {
					w = Lunar.toInt(m.group(2));
					d = Lunar.toInt(m.group(3));
					if (this.solar.get(Calendar.WEEK_OF_MONTH) == w && this.solar.get(Calendar.DAY_OF_WEEK) == d) {
						this.isSFestival = true;
						this.sFestivalName += "|" + m.group(5);
						if ("*".equals(m.group(4)))
							this.isHoliday = true;
					}
				}
			}
		}
		if (sy > 1874 && sy < 1909)
			this.description = "����" + (((sy - 1874) == 1) ? "Ԫ" : "" + (sy - 1874));
		if (sy > 1908 && sy < 1912)
			this.description = "��ͳ" + (((sy - 1908) == 1) ? "Ԫ" : String.valueOf(sy - 1908));
		if (sy > 1911 && sy < 1950)
			this.description = "���" + (((sy - 1911) == 1) ? "Ԫ" : String.valueOf(sy - 1911));
		if (sy > 1949)
			this.description = "���͹�" + (((sy - 1949) == 1) ? "Ԫ" : String.valueOf(sy - 1949));
		this.description += "��";
		this.sFestivalName = this.sFestivalName.replaceFirst("^\\|", "");
		this.isFinded = true;
	}

	private boolean isFinded = false;
	private boolean isSFestival = false;
	private boolean isLFestival = false;
	private String sFestivalName = "";
	private String lFestivalName = "";
	private String description = "";
	private boolean isHoliday = false;

	/**
	 * ����ũ���������·�
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�������µ��·�(����,û�򷵻�0)
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		// ��4bitȫΪ1��ȫΪ0,��ʾû��, ����4bit��ֵΪ�����·�
		int leapMonth = Lunar.lunarInfo[lunarYear - 1900] & 0xf;
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	/**
	 * ����ũ�������µ�����
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�������µ�����(����)
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// ��һ�����4bitΪ1111,����30(����)
		// ��һ�����4bit��Ϊ1111,����29(С��)
		// ������û������,����0
		return Lunar.getLunarLeapMonth(lunarYear) > 0 ? ((Lunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29)
				: 0;
	}

	/**
	 * ����ũ�����������
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�����������(����)
	 */
	private static int getLunarYearDays(int lunarYear) {
		// ��С�¼���,ũ����������12 * 29 = 348��
		int daysInLunarYear = 348;
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		// ÿ�������ۼ�һ��
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			daysInLunarYear += ((Lunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// ������������
		daysInLunarYear += Lunar.getLunarLeapDays(lunarYear);

		return daysInLunarYear;
	}

	/**
	 * ����ũ���������·ݵ�������
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @param lunarMonth
	 *            ָ��ũ���·�(����)
	 * @return ��ũ�������µ��·�(����,û�򷵻�0)
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		int daysInLunarMonth = ((Lunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	/**
	 * ȡ Date ��������ȫ���׼ʱ�� (UTC) ��ʾ������
	 * 
	 * @param date
	 *            ָ������
	 * @return UTC ȫ���׼ʱ�� (UTC) ��ʾ������
	 */
	public static synchronized int getUTCDay(Date date) {
		Lunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.setTimeInMillis(date.getTime());
			return utcCal.get(Calendar.DAY_OF_MONTH);
		}
	}

	private static GregorianCalendar utcCal = null;

	private static synchronized void makeUTCCalendar() {
		if (Lunar.utcCal == null) {
			Lunar.utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		}
	}

	/**
	 * ����ȫ���׼ʱ�� (UTC) (�� GMT) �� 1970 �� 1 �� 1 �յ���ָ������֮��������ĺ�������
	 * 
	 * @param y
	 *            ָ�����
	 * @param m
	 *            ָ���·�
	 * @param d
	 *            ָ������
	 * @param h
	 *            ָ��Сʱ
	 * @param min
	 *            ָ������
	 * @param sec
	 *            ָ������
	 * @return ȫ���׼ʱ�� (UTC) (�� GMT) �� 1970 �� 1 �� 1 �յ���ָ������֮��������ĺ�����
	 */
	public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
		Lunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.set(y, m, d, h, min, sec);
			return utcCal.getTimeInMillis();
		}
	}

	/**
	 * ���ع��������������
	 * 
	 * @param solarYear
	 *            ָ���������(����)
	 * @param index
	 *            ָ���������(����,0��С������)
	 * @return ����(����,�����·ݵĵڼ���)
	 */
	private static int getSolarTermDay(int solarYear, int index) {
		long l = (long) 31556925974.7 * (solarYear - 1900) + solarTermInfo[index] * 60000L;
		l = l + Lunar.UTC(1900, 0, 6, 2, 5, 0);
		return Lunar.getUTCDay(new Date(l));
	}

	private Calendar solar;
	private int lunarYear;
	private int lunarMonth;
	private int lunarDay;
	private boolean isLeap;
	private boolean isLeapYear;
	private int solarYear;
	private int solarMonth;
	private int solarDay;
	private int cyclicalYear = 0;
	private int cyclicalMonth = 0;
	private int cyclicalDay = 0;
	private int maxDayInMonth = 29;

	/**
	 * ͨ�� Date ���󹹽�ũ����Ϣ
	 * 
	 * @param date
	 *            ָ�����ڶ���
	 */
	public Lunar(Date date) {
		if (date == null)
			date = new Date();
		this.init(date.getTime());
	}

	/**
	 * ͨ�� TimeInMillis ����ũ����Ϣ
	 * 
	 * @param TimeInMillis
	 */
	public Lunar(long TimeInMillis) {
		this.init(TimeInMillis);
	}

	private void init(long TimeInMillis) {
		this.solar = Calendar.getInstance();
		this.solar.setTimeInMillis(TimeInMillis);
		Calendar baseDate = new GregorianCalendar(1900, 0, 31);
		long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
		// ��ũ����ݼ�ÿ���ũ��������ȷ��ũ�����
		this.lunarYear = 1900;
		int daysInLunarYear = Lunar.getLunarYearDays(this.lunarYear);
		while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
			offset -= daysInLunarYear;
			daysInLunarYear = Lunar.getLunarYearDays(++this.lunarYear);
		}
		// ũ��������

		// ��ũ���µݼ�ÿ�µ�ũ��������ȷ��ũ���·�
		int lunarMonth = 1;
		// ����ũ�������ĸ���,��û�з���0
		int leapMonth = Lunar.getLunarLeapMonth(this.lunarYear);
		// �Ƿ�����
		this.isLeapYear = leapMonth > 0;
		// �����Ƿ�ݼ�
		boolean leapDec = false;
		boolean isLeap = false;
		int daysInLunarMonth = 0;
		while (lunarMonth < 13 && offset > 0) {
			if (isLeap && leapDec) { // ���������,����������
				// ����ũ�������µ�����
				daysInLunarMonth = Lunar.getLunarLeapDays(this.lunarYear);
				leapDec = false;
			} else {
				// ����ũ����ָ���µ�����
				daysInLunarMonth = Lunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			}
			if (offset < daysInLunarMonth) {
				break;
			}
			offset -= daysInLunarMonth;

			if (leapMonth == lunarMonth && isLeap == false) {
				// �¸���������
				leapDec = true;
				isLeap = true;
			} else {
				// �·ݵ���
				lunarMonth++;
			}
		}
		this.maxDayInMonth = daysInLunarMonth;
		// ũ��������
		this.lunarMonth = lunarMonth;
		// �Ƿ�����
		this.isLeap = (lunarMonth == leapMonth && isLeap);
		// ũ��������
		this.lunarDay = (int) offset + 1;
		// ȡ�ø�֧��
		this.getCyclicalData();
	}

	/**
	 * ȡ��֧�� �������꣬���¸�֧�������й��Ĵ�����������ʼ�Ľ��£����й���̫��ʮ�����������ġ�
	 * 
	 * @param cncaData
	 *            ��������(Tcnca)
	 */
	private void getCyclicalData() {
		this.solarYear = this.solar.get(Calendar.YEAR);
		this.solarMonth = this.solar.get(Calendar.MONTH);
		this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
		// ��֧��
		int cyclicalYear = 0;
		int cyclicalMonth = 0;
		int cyclicalDay = 0;

		// ��֧�� 1900��������Ϊ������(60����36)
		int term2 = Lunar.getSolarTermDay(solarYear, 2); // ��������
		// �������������·ֵ�����, ������Ϊ��
		if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
			cyclicalYear = (solarYear - 1900 + 36 - 1) % 60;
		} else {
			cyclicalYear = (solarYear - 1900 + 36) % 60;
		}

		// ��֧�� 1900��1��С����ǰΪ ������(60����12)
		int firstNode = Lunar.getSolarTermDay(solarYear, solarMonth * 2); // ���ص��¡��ڡ�Ϊ���տ�ʼ
		// ����������, �ԡ��ڡ�Ϊ��
		if (solarDay < firstNode) {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 12) % 60;
		} else {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 13) % 60;
		}

		// ����һ���� 1900/1/1 �������
		// 1900/1/1�� 1970/1/1 ���25567��, 1900/1/1 ����Ϊ������(60����10)
		cyclicalDay = (int) (Lunar.UTC(solarYear, solarMonth, solarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
		this.cyclicalYear = cyclicalYear;
		this.cyclicalMonth = cyclicalMonth;
		this.cyclicalDay = cyclicalDay;
	}

	/**
	 * ȡũ������Ф
	 * 
	 * @return ũ������Ф(��:��)
	 */
	public String getAnimalString() {
		return Lunar.Animals[(this.lunarYear - 4) % 12];
	}

	/**
	 * ���ع������ڵĽ����ַ���
	 * 
	 * @return ��ʮ�Ľ����ַ���,�����ǽ�����,���ؿմ�(��:����)
	 */
	public String getTermString() {
		// ��ʮ�Ľ���
		String termString = "";
		if (Lunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			termString = Lunar.solarTerm[solarMonth * 2];
		} else if (Lunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			termString = Lunar.solarTerm[solarMonth * 2 + 1];
		}
		return termString;
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���(��:����������¼�����)
	 */
	public String getCyclicalDateString() {
		return this.getCyclicaYear() + "��" + this.getCyclicaMonth() + "��" + this.getCyclicaDay() + "��";
	}

	/**
	 * ������
	 * 
	 * @return ������
	 */
	public int getTiananY() {
		return Lunar.getTianan(this.cyclicalYear);
	}

	/**
	 * �·����
	 * 
	 * @return �·����
	 */
	public int getTiananM() {
		return Lunar.getTianan(this.cyclicalMonth);
	}

	/**
	 * �������
	 * 
	 * @return �������
	 */
	public int getTiananD() {
		return Lunar.getTianan(this.cyclicalDay);
	}

	/**
	 * ��ݵ�֧
	 * 
	 * @return ��ֵ�֧
	 */
	public int getDeqiY() {
		return Lunar.getDeqi(this.cyclicalYear);
	}

	/**
	 * �·ݵ�֧
	 * 
	 * @return �·ݵ�֧
	 */
	public int getDeqiM() {
		return Lunar.getDeqi(this.cyclicalMonth);
	}

	/**
	 * ���ڵ�֧
	 * 
	 * @return ���ڵ�֧
	 */
	public int getDeqiD() {
		return Lunar.getDeqi(this.cyclicalDay);
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaYear() {
		return Lunar.getCyclicalString(this.cyclicalYear);
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaMonth() {
		return Lunar.getCyclicalString(this.cyclicalMonth);
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaDay() {
		return Lunar.getCyclicalString(this.cyclicalDay);
	}

	/**
	 * ����ũ�������ַ���
	 * 
	 * @return ũ�������ַ���
	 */
	public String getLunarDayString() {
		return Lunar.getLunarDayString(this.lunarDay);
	}

	/**
	 * ����ũ�������ַ���
	 * 
	 * @return ũ�������ַ���
	 */
	public String getLunarMonthString() {
		return (this.isLeap() ? "��" : "") + Lunar.getLunarMonthString(this.lunarMonth);
	}

	/**
	 * ����ũ�������ַ���
	 * 
	 * @return ũ�������ַ���
	 */
	public String getLunarYearString() {
		return Lunar.getLunarYearString(this.lunarYear);
	}

	/**
	 * ����ũ����ʾ�ַ���
	 * 
	 * @return ũ���ַ���(��:���������³���)
	 */
	public String getLunarDateString() {
		return this.getLunarYearString() + "��" + this.getLunarMonthString() + "��" + this.getLunarDayString() + "��";
	}

	/**
	 * ũ�����Ƿ�������
	 * 
	 * @return ũ�����Ƿ�������
	 */
	public boolean isLeap() {
		return isLeap;
	}

	/**
	 * ũ�����Ƿ�������
	 * 
	 * @return ũ�����Ƿ�������
	 */
	public boolean isLeapYear() {
		return isLeapYear;
	}

	/**
	 * ��ǰũ�����Ƿ��Ǵ���
	 * 
	 * @return ��ǰũ�����Ǵ���
	 */
	public boolean isBigMonth() {
		return this.getMaxDayInMonth() > 29;
	}

	/**
	 * ��ǰũ�����ж�����
	 * 
	 * @return ��ǰũ�����ж�����
	 */
	public int getMaxDayInMonth() {
		return this.maxDayInMonth;
	}

	/**
	 * ũ������
	 * 
	 * @return ũ������
	 */
	public int getLunarDay() {
		return lunarDay;
	}

	/**
	 * ũ���·�
	 * 
	 * @return ũ���·�
	 */
	public int getLunarMonth() {
		return lunarMonth;
	}

	/**
	 * ũ�����
	 * 
	 * @return ũ�����
	 */
	public int getLunarYear() {
		return lunarYear;
	}

	/**
	 * ��������
	 * 
	 * @return ��������
	 */
	public int getSolarDay() {
		return solarDay;
	}

	/**
	 * �����·�
	 * 
	 * @return �����·� (���Ǵ�0����)
	 */
	public int getSolarMonth() {
		return solarMonth + 1;
	}

	/**
	 * �������
	 * 
	 * @return �������
	 */
	public int getSolarYear() {
		return solarYear;
	}

	/**
	 * ���ڼ�
	 * 
	 * @return ���ڼ�(������Ϊ:1, ������Ϊ:7)
	 */
	public int getDayOfWeek() {
		return this.solar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * ��ɫ������
	 * 
	 * @return �Ƿ��ɫ������
	 */
	public boolean isBlackFriday() {
		return (this.getSolarDay() == 13 && this.solar.get(Calendar.DAY_OF_WEEK) == 6);
	}

	/**
	 * �Ƿ��ǽ���
	 * 
	 * @return �Ƿ��ǽ���
	 */
	public boolean isToday() {
		Calendar clr = Calendar.getInstance();
		return clr.get(Calendar.YEAR) == this.solarYear && clr.get(Calendar.MONTH) == this.solarMonth
				&& clr.get(Calendar.DAY_OF_MONTH) == this.solarDay;
	}

	/**
	 * ȡ�ù�����������
	 * 
	 * @return ������������,������ǽ��շ��ؿմ�
	 */
	public String getSFestivalName() {
		return this.sFestivalName;
	}

	/**
	 * ȡ��ũ����������
	 * 
	 * @return ũ����������,������ǽ��շ��ؿմ�
	 */
	public String getLFestivalName() {
		return this.lFestivalName;
	}

	/**
	 * �Ƿ���ũ������
	 * 
	 * @return �Ƿ���ũ������
	 */
	public boolean isLFestival() {
		if (!this.isFinded)
			this.findFestival();
		return this.isLFestival;
	}

	/**
	 * �Ƿ��ǹ�������
	 * 
	 * @return �Ƿ��ǹ�������
	 */
	public boolean isSFestival() {
		if (!this.isFinded)
			this.findFestival();
		return this.isSFestival;
	}

	/**
	 * �Ƿ��ǽ���
	 * 
	 * @return �Ƿ��ǽ���
	 */
	public boolean isFestival() {
		return this.isSFestival() || this.isLFestival();
	}

	/**
	 * �Ƿ��Ƿż���
	 * 
	 * @return �Ƿ��Ƿż���
	 */
	public boolean isHoliday() {
		if (!this.isFinded)
			this.findFestival();
		return this.isHoliday;
	}

	/**
	 * ��������˵��
	 * 
	 * @return ����˵��(��:���2��)
	 */
	public String getDescription() {
		if (!this.isFinded)
			this.findFestival();
		return this.description;
	}

	/**
	 * ��֧�ַ���
	 * 
	 * @param cyclicalNumber
	 *            ָ����֧λ��(����,0Ϊ����)
	 * @return ��֧�ַ���
	 */
	private static String getCyclicalString(int cyclicalNumber) {
		return Lunar.Tianan[Lunar.getTianan(cyclicalNumber)] + Lunar.Deqi[Lunar.getDeqi(cyclicalNumber)];
	}

	/**
	 * ��õ�֧
	 * 
	 * @param cyclicalNumber
	 * @return ��֧ (����)
	 */
	private static int getDeqi(int cyclicalNumber) {
		return cyclicalNumber % 12;
	}

	/**
	 * ������
	 * 
	 * @param cyclicalNumber
	 * @return ��� (����)
	 */
	private static int getTianan(int cyclicalNumber) {
		return cyclicalNumber % 10;
	}

	/**
	 * ����ָ�����ֵ�ũ����ݱ�ʾ�ַ���
	 * 
	 * @param lunarYear
	 *            ũ�����(����,0Ϊ����)
	 * @return ũ������ַ���
	 */
	private static String getLunarYearString(int lunarYear) {
		return Lunar.getCyclicalString(lunarYear - 1900 + 36);
	}

	/**
	 * ����ָ�����ֵ�ũ���·ݱ�ʾ�ַ���
	 * 
	 * @param lunarMonth
	 *            ũ���·�(����)
	 * @return ũ���·��ַ��� (��:��)
	 */
	private static String getLunarMonthString(int lunarMonth) {
		String lunarMonthString = "";
		if (lunarMonth == 1) {
			lunarMonthString = Lunar.lunarString2[4];
		} else {
			if (lunarMonth > 9)
				lunarMonthString += Lunar.lunarString2[1];
			if (lunarMonth % 10 > 0)
				lunarMonthString += Lunar.lunarString1[lunarMonth % 10];
		}
		return lunarMonthString;
	}

	/**
	 * ����ָ�����ֵ�ũ���ձ�ʾ�ַ���
	 * 
	 * @param lunarDay
	 *            ũ����(����)
	 * @return ũ�����ַ��� (��: إһ)
	 */
	private static String getLunarDayString(int lunarDay) {
		if (lunarDay < 1 || lunarDay > 30)
			return "";
		int i1 = lunarDay / 10;
		int i2 = lunarDay % 10;
		String c1 = Lunar.lunarString2[i1];
		String c2 = Lunar.lunarString1[i2];
		if (lunarDay < 11)
			c1 = Lunar.lunarString2[0];
		if (i2 == 0)
			c2 = Lunar.lunarString2[1];
		return c1 + c2;
	}
}