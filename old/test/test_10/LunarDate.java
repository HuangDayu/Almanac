package test_10;

public class LunarDate {

	/** ob.d0 2000.0����������,����ʱ12:00 */
	private static int dayRL;
	/** ob.di ���ڹ������������� */
	private static int dayIndex;
	/** ob.y ���ڹ�����,ͬlun.y */
	private static int year;
	/** ob.m ���ڹ�����,ͬlun.m */
	private static int month;
	/** ob.d ������(����) */
	private static int day;
	/** ob.dn ���ڹ����µ�������,ͬlun.dn */
	private static int daysOfMonth;
	/** ob.week0 �����µ����׵�����,ͬlun.w0 */
	private static int weekFirst;
	/** ob.week ���� */
	private static int week;
	/** ob.weeki �ڱ����е������ */
	private static int weekIndex;
	/** ob.weekN ���µ������� */
	private static int weeksOfMonth;
	/** ob.Hyear ��(����) */
	private static int hYear;
	/** ob.Hmonth ��(����) */
	private static int hMonth;
	/** ob.Hday ��(����) */
	private static int hDay;
	/** ob.Lmc ������ */
	private static String lunarMonthName;
	/** ob.Ldi ��ũ�����׵ı�����,0��Ӧ��һ */
	private static int lunarMonthOffset;
	/** ob.Ldc ������ */
	private static String lunarDayName;
	/** ob.Ldn �´�С */
	private static int daysofLunarMonth;
	/** ob.Lleap ��״��(ֵΪ'��'��մ�) */
	private static String lunarLunarLeap;
	/** ob.Lmc2 �¸�������,�жϳ�ϦʱҪ�õ� */
	private static String nextLunarMonthName;
	/** ob.Ljq ���� */
	private static String lunarSolarTerm;
	/** ob.cur_dz �ඬ�������� */
	private static int daysToDZ;
	/** ob.cur_xz ������������ */
	private static int daysToXZ;
	/** ob.cur_lq ����������� */
	private static int daysToLQ;
	/** ob.cur_mz ��â�ֵ����� */
	private static int daysToMZ;
	/** ob.cur_xs ��С������� */
	private static int daysToXS;
	/**
	 * ob.Lyear2, ob.Lyear3 ��֧��. Lyear2��Lyear3����������������ж�, Lyear2������, Lyear3���Ǵ���[����]
	 */
	private static String cnEraYear;
	/** ob.Lmonth2 ��֧�� */
	private static String cnEraMonth;
	/** ob.Lday2 ��֧�� */
	private static String cnEraDay;
	/** ob.Ltime2 ��֧ʱ */
	private static String cnEraTime;
	/** ob.XiZ ���� */
	private static String constellation;
	/** ob.Lyear ũ���� */
	private static int lunarYear;
	/** ob.Lmonth ũ���� */
	private static int lunarMonth;
	/** ob.yxmc �������� */
	private static String moonPhaseName;
	/** ob.yxjd ����ʱ��(������) */
	private static double moonPhaseTime;
	/** ob.yxsj ����ʱ�䴮 */
	private static String moonPhaseTimeStr;
	/** ob.jqmc �������� */
	private static String solarTermName;
	/** ob.jqjd ����ʱ��(������) */
	private static double solarTermTime;
	/** ob.jqsj ����ʱ�䴮 */
	private static String solarTermTimeStr;
	/** ob.Lyear4 �Ƶۼ��� */
	private static int kingYear;

	/** r.A ��Ҫϲ����������(�ɽ����������ú�) */
	private static String impHappyName;
	/** r.B ��Ҫ�������� */
	private static String impName;
	/** r.C ������������(����һ��) */
	private static String allName;
	/** r.Fjia �ż�����(���������������ú�) */
	private static int holiday;
	public static int getDayRL() {
		return dayRL;
	}
	public static void setDayRL(int dayRL) {
		LunarDate.dayRL = dayRL;
	}
	public static int getDayIndex() {
		return dayIndex;
	}
	public static void setDayIndex(int dayIndex) {
		LunarDate.dayIndex = dayIndex;
	}
	public static int getYear() {
		return year;
	}
	public static void setYear(int year) {
		LunarDate.year = year;
	}
	public static int getMonth() {
		return month;
	}
	public static void setMonth(int month) {
		LunarDate.month = month;
	}
	public static int getDay() {
		return day;
	}
	public static void setDay(int day) {
		LunarDate.day = day;
	}
	public static int getDaysOfMonth() {
		return daysOfMonth;
	}
	public static void setDaysOfMonth(int daysOfMonth) {
		LunarDate.daysOfMonth = daysOfMonth;
	}
	public static int getWeekFirst() {
		return weekFirst;
	}
	public static void setWeekFirst(int weekFirst) {
		LunarDate.weekFirst = weekFirst;
	}
	public static int getWeek() {
		return week;
	}
	public static void setWeek(int week) {
		LunarDate.week = week;
	}
	public static int getWeekIndex() {
		return weekIndex;
	}
	public static void setWeekIndex(int weekIndex) {
		LunarDate.weekIndex = weekIndex;
	}
	public static int getWeeksOfMonth() {
		return weeksOfMonth;
	}
	public static void setWeeksOfMonth(int weeksOfMonth) {
		LunarDate.weeksOfMonth = weeksOfMonth;
	}
	public static int gethYear() {
		return hYear;
	}
	public static void sethYear(int hYear) {
		LunarDate.hYear = hYear;
	}
	public static int gethMonth() {
		return hMonth;
	}
	public static void sethMonth(int hMonth) {
		LunarDate.hMonth = hMonth;
	}
	public static int gethDay() {
		return hDay;
	}
	public static void sethDay(int hDay) {
		LunarDate.hDay = hDay;
	}
	public static String getLunarMonthName() {
		return lunarMonthName;
	}
	public static void setLunarMonthName(String lunarMonthName) {
		LunarDate.lunarMonthName = lunarMonthName;
	}
	public static int getLunarMonthOffset() {
		return lunarMonthOffset;
	}
	public static void setLunarMonthOffset(int lunarMonthOffset) {
		LunarDate.lunarMonthOffset = lunarMonthOffset;
	}
	public static String getLunarDayName() {
		return lunarDayName;
	}
	public static void setLunarDayName(String lunarDayName) {
		LunarDate.lunarDayName = lunarDayName;
	}
	public static int getDaysofLunarMonth() {
		return daysofLunarMonth;
	}
	public static void setDaysofLunarMonth(int daysofLunarMonth) {
		LunarDate.daysofLunarMonth = daysofLunarMonth;
	}
	public static String getLunarLunarLeap() {
		return lunarLunarLeap;
	}
	public static void setLunarLunarLeap(String lunarLunarLeap) {
		LunarDate.lunarLunarLeap = lunarLunarLeap;
	}
	public static String getNextLunarMonthName() {
		return nextLunarMonthName;
	}
	public static void setNextLunarMonthName(String nextLunarMonthName) {
		LunarDate.nextLunarMonthName = nextLunarMonthName;
	}
	public static String getLunarSolarTerm() {
		return lunarSolarTerm;
	}
	public static void setLunarSolarTerm(String lunarSolarTerm) {
		LunarDate.lunarSolarTerm = lunarSolarTerm;
	}
	public static int getDaysToDZ() {
		return daysToDZ;
	}
	public static void setDaysToDZ(int daysToDZ) {
		LunarDate.daysToDZ = daysToDZ;
	}
	public static int getDaysToXZ() {
		return daysToXZ;
	}
	public static void setDaysToXZ(int daysToXZ) {
		LunarDate.daysToXZ = daysToXZ;
	}
	public static int getDaysToLQ() {
		return daysToLQ;
	}
	public static void setDaysToLQ(int daysToLQ) {
		LunarDate.daysToLQ = daysToLQ;
	}
	public static int getDaysToMZ() {
		return daysToMZ;
	}
	public static void setDaysToMZ(int daysToMZ) {
		LunarDate.daysToMZ = daysToMZ;
	}
	public static int getDaysToXS() {
		return daysToXS;
	}
	public static void setDaysToXS(int daysToXS) {
		LunarDate.daysToXS = daysToXS;
	}
	public static String getCnEraYear() {
		return cnEraYear;
	}
	public static void setCnEraYear(String cnEraYear) {
		LunarDate.cnEraYear = cnEraYear;
	}
	public static String getCnEraMonth() {
		return cnEraMonth;
	}
	public static void setCnEraMonth(String cnEraMonth) {
		LunarDate.cnEraMonth = cnEraMonth;
	}
	public static String getCnEraDay() {
		return cnEraDay;
	}
	public static void setCnEraDay(String cnEraDay) {
		LunarDate.cnEraDay = cnEraDay;
	}
	public static String getCnEraTime() {
		return cnEraTime;
	}
	public static void setCnEraTime(String cnEraTime) {
		LunarDate.cnEraTime = cnEraTime;
	}
	public static String getConstellation() {
		return constellation;
	}
	public static void setConstellation(String constellation) {
		LunarDate.constellation = constellation;
	}
	public static int getLunarYear() {
		return lunarYear;
	}
	public static void setLunarYear(int lunarYear) {
		LunarDate.lunarYear = lunarYear;
	}
	public static int getLunarMonth() {
		return lunarMonth;
	}
	public static void setLunarMonth(int lunarMonth) {
		LunarDate.lunarMonth = lunarMonth;
	}
	public static String getMoonPhaseName() {
		return moonPhaseName;
	}
	public static void setMoonPhaseName(String moonPhaseName) {
		LunarDate.moonPhaseName = moonPhaseName;
	}
	public static double getMoonPhaseTime() {
		return moonPhaseTime;
	}
	public static void setMoonPhaseTime(double moonPhaseTime) {
		LunarDate.moonPhaseTime = moonPhaseTime;
	}
	public static String getMoonPhaseTimeStr() {
		return moonPhaseTimeStr;
	}
	public static void setMoonPhaseTimeStr(String moonPhaseTimeStr) {
		LunarDate.moonPhaseTimeStr = moonPhaseTimeStr;
	}
	public static String getSolarTermName() {
		return solarTermName;
	}
	public static void setSolarTermName(String solarTermName) {
		LunarDate.solarTermName = solarTermName;
	}
	public static double getSolarTermTime() {
		return solarTermTime;
	}
	public static void setSolarTermTime(double solarTermTime) {
		LunarDate.solarTermTime = solarTermTime;
	}
	public static String getSolarTermTimeStr() {
		return solarTermTimeStr;
	}
	public static void setSolarTermTimeStr(String solarTermTimeStr) {
		LunarDate.solarTermTimeStr = solarTermTimeStr;
	}
	public static int getKingYear() {
		return kingYear;
	}
	public static void setKingYear(int kingYear) {
		LunarDate.kingYear = kingYear;
	}
	public static String getImpHappyName() {
		return impHappyName;
	}
	public static void setImpHappyName(String impHappyName) {
		LunarDate.impHappyName = impHappyName;
	}
	public static String getImpName() {
		return impName;
	}
	public static void setImpName(String impName) {
		LunarDate.impName = impName;
	}
	public static String getAllName() {
		return allName;
	}
	public static void setAllName(String allName) {
		LunarDate.allName = allName;
	}
	public static int getHoliday() {
		return holiday;
	}
	public static void setHoliday(int holiday) {
		LunarDate.holiday = holiday;
	}
	
}
