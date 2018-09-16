package test_7;

public class LunarDate {

	/** ob.d0 2000.0����������,����ʱ12:00 */
	private int dayRL;
	/** ob.di ���ڹ������������� */
	private int dayIndex;
	/** ob.y ���ڹ�����,ͬlun.y */
	private int year;
	/** ob.m ���ڹ�����,ͬlun.m */
	private int month;
	/** ob.d ������(����) */
	private int day;
	/** ob.dn ���ڹ����µ�������,ͬlun.dn */
	private int daysOfMonth;
	/** ob.week0 �����µ����׵�����,ͬlun.w0 */
	private int weekFirst;
	/** ob.week ���� */
	private int week;
	/** ob.weeki �ڱ����е������ */
	private int weekIndex;
	/** ob.weekN ���µ������� */
	private int weeksOfMonth;
	/** ob.Hyear ��(����) */
	private int hYear;
	/** ob.Hmonth ��(����) */
	private int hMonth;
	/** ob.Hday ��(����) */
	private int hDay;
	/** ob.Lmc ������ */
	private String lunarMonthName;
	/** ob.Ldi ��ũ�����׵ı�����,0��Ӧ��һ */
	private int lunarMonthOffset;
	/** ob.Ldc ������ */
	private String lunarDayName;
	/** ob.Ldn �´�С */
	private int daysofLunarMonth;
	/** ob.Lleap ��״��(ֵΪ'��'��մ�) */
	private String lunarLunarLeap;
	/** ob.Lmc2 �¸�������,�жϳ�ϦʱҪ�õ� */
	private String nextLunarMonthName;
	/** ob.Ljq ���� */
	private String lunarSolarTerm;
	/** ob.cur_dz �ඬ�������� */
	private int daysToDZ;
	/** ob.cur_xz ������������ */
	private int daysToXZ;
	/** ob.cur_lq ����������� */
	private int daysToLQ;
	/** ob.cur_mz ��â�ֵ����� */
	private int daysToMZ;
	/** ob.cur_xs ��С������� */
	private int daysToXS;
	/**
	 * ob.Lyear2, ob.Lyear3 ��֧��. Lyear2��Lyear3����������������ж�, Lyear2������, Lyear3���Ǵ���[����]
	 */
	private String cnEraYear;
	/** ob.Lmonth2 ��֧�� */
	private String cnEraMonth;
	/** ob.Lday2 ��֧�� */
	private String cnEraDay;
	/** ob.Ltime2 ��֧ʱ */
	private String cnEraTime;
	/** ob.XiZ ���� */
	private String constellation;
	/** ob.Lyear ũ���� */
	private int lunarYear;
	/** ob.Lmonth ũ���� */
	private int lunarMonth;
	/** ob.yxmc �������� */
	private String moonPhaseName;
	/** ob.yxjd ����ʱ��(������) */
	private double moonPhaseTime;
	/** ob.yxsj ����ʱ�䴮 */
	private String moonPhaseTimeStr;
	/** ob.jqmc �������� */
	private String solarTermName;
	/** ob.jqjd ����ʱ��(������) */
	private double solarTermTime;
	/** ob.jqsj ����ʱ�䴮 */
	private String solarTermTimeStr;
	/** ob.Lyear4 �Ƶۼ��� */
	private int kingYear;

	/** r.A ��Ҫϲ����������(�ɽ����������ú�) */
	private String impHappyName;
	/** r.B ��Ҫ�������� */
	private String impName;
	/** r.C ������������(����һ��) */
	private String allName;
	/** r.Fjia �ż�����(���������������ú�) */
	private int holiday;

	public int getDayRL() {
		return dayRL;
	}

	public void setDayRL(int dayRL) {
		this.dayRL = dayRL;
	}

	public int getDayIndex() {
		return dayIndex;
	}

	public void setDayIndex(int dayIndex) {
		this.dayIndex = dayIndex;
	}

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

	public int getDaysOfMonth() {
		return daysOfMonth;
	}

	public void setDaysOfMonth(int daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	public int getWeekFirst() {
		return weekFirst;
	}

	public void setWeekFirst(int weekFirst) {
		this.weekFirst = weekFirst;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}

	public int getWeeksOfMonth() {
		return weeksOfMonth;
	}

	public void setWeeksOfMonth(int weeksOfMonth) {
		this.weeksOfMonth = weeksOfMonth;
	}

	public int gethYear() {
		return hYear;
	}

	public void sethYear(int hYear) {
		this.hYear = hYear;
	}

	public int gethMonth() {
		return hMonth;
	}

	public void sethMonth(int hMonth) {
		this.hMonth = hMonth;
	}

	public int gethDay() {
		return hDay;
	}

	public void sethDay(int hDay) {
		this.hDay = hDay;
	}

	public String getLunarMonthName() {
		return lunarMonthName;
	}

	public void setLunarMonthName(String lunarMonthName) {
		this.lunarMonthName = lunarMonthName;
	}

	public int getLunarMonthOffset() {
		return lunarMonthOffset;
	}

	public void setLunarMonthOffset(int lunarMonthOffset) {
		this.lunarMonthOffset = lunarMonthOffset;
	}

	public String getLunarDayName() {
		return lunarDayName;
	}

	public void setLunarDayName(String lunarDayName) {
		this.lunarDayName = lunarDayName;
	}

	public int getDaysofLunarMonth() {
		return daysofLunarMonth;
	}

	public void setDaysofLunarMonth(int daysofLunarMonth) {
		this.daysofLunarMonth = daysofLunarMonth;
	}

	public String getLunarLunarLeap() {
		return lunarLunarLeap;
	}

	public void setLunarLunarLeap(String lunarLunarLeap) {
		this.lunarLunarLeap = lunarLunarLeap;
	}

	public String getNextLunarMonthName() {
		return nextLunarMonthName;
	}

	public void setNextLunarMonthName(String nextLunarMonthName) {
		this.nextLunarMonthName = nextLunarMonthName;
	}

	public String getLunarSolarTerm() {
		return lunarSolarTerm;
	}

	public void setLunarSolarTerm(String lunarSolarTerm) {
		this.lunarSolarTerm = lunarSolarTerm;
	}

	public int getDaysToDZ() {
		return daysToDZ;
	}

	public void setDaysToDZ(int daysToDZ) {
		this.daysToDZ = daysToDZ;
	}

	public int getDaysToXZ() {
		return daysToXZ;
	}

	public void setDaysToXZ(int daysToXZ) {
		this.daysToXZ = daysToXZ;
	}

	public int getDaysToLQ() {
		return daysToLQ;
	}

	public void setDaysToLQ(int daysToLQ) {
		this.daysToLQ = daysToLQ;
	}

	public int getDaysToMZ() {
		return daysToMZ;
	}

	public void setDaysToMZ(int daysToMZ) {
		this.daysToMZ = daysToMZ;
	}

	public int getDaysToXS() {
		return daysToXS;
	}

	public void setDaysToXS(int daysToXS) {
		this.daysToXS = daysToXS;
	}

	public String getCnEraYear() {
		return cnEraYear;
	}

	public void setCnEraYear(String cnEraYear) {
		this.cnEraYear = cnEraYear;
	}

	public String getCnEraMonth() {
		return cnEraMonth;
	}

	public void setCnEraMonth(String cnEraMonth) {
		this.cnEraMonth = cnEraMonth;
	}

	public String getCnEraDay() {
		return cnEraDay;
	}

	public void setCnEraDay(String cnEraDay) {
		this.cnEraDay = cnEraDay;
	}

	public String getCnEraTime() {
		return cnEraTime;
	}

	public void setCnEraTime(String cnEraTime) {
		this.cnEraTime = cnEraTime;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public int getLunarYear() {
		return lunarYear;
	}

	public void setLunarYear(int lunarYear) {
		this.lunarYear = lunarYear;
	}

	public int getLunarMonth() {
		return lunarMonth;
	}

	public void setLunarMonth(int lunarMonth) {
		this.lunarMonth = lunarMonth;
	}

	public String getMoonPhaseName() {
		return moonPhaseName;
	}

	public void setMoonPhaseName(String moonPhaseName) {
		this.moonPhaseName = moonPhaseName;
	}

	public double getMoonPhaseTime() {
		return moonPhaseTime;
	}

	public void setMoonPhaseTime(double moonPhaseTime) {
		this.moonPhaseTime = moonPhaseTime;
	}

	public String getMoonPhaseTimeStr() {
		return moonPhaseTimeStr;
	}

	public void setMoonPhaseTimeStr(String moonPhaseTimeStr) {
		this.moonPhaseTimeStr = moonPhaseTimeStr;
	}

	public String getSolarTermName() {
		return solarTermName;
	}

	public void setSolarTermName(String solarTermName) {
		this.solarTermName = solarTermName;
	}

	public double getSolarTermTime() {
		return solarTermTime;
	}

	public void setSolarTermTime(double solarTermTime) {
		this.solarTermTime = solarTermTime;
	}

	public String getSolarTermTimeStr() {
		return solarTermTimeStr;
	}

	public void setSolarTermTimeStr(String solarTermTimeStr) {
		this.solarTermTimeStr = solarTermTimeStr;
	}

	public int getKingYear() {
		return kingYear;
	}

	public void setKingYear(int kingYear) {
		this.kingYear = kingYear;
	}

	public String getImpHappyName() {
		return impHappyName;
	}

	public void setImpHappyName(String impHappyName) {
		this.impHappyName = impHappyName;
	}

	public String getImpName() {
		return impName;
	}

	public void setImpName(String impName) {
		this.impName = impName;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}
}
