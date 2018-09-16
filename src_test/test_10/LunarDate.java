package test_10;

public class LunarDate {

	/** ob.d0 2000.0起算儒略日,北京时12:00 */
	private static int dayRL;
	/** ob.di 所在公历月内日序数 */
	private static int dayIndex;
	/** ob.y 所在公历年,同lun.y */
	private static int year;
	/** ob.m 所在公历月,同lun.m */
	private static int month;
	/** ob.d 日名称(公历) */
	private static int day;
	/** ob.dn 所在公历月的总天数,同lun.dn */
	private static int daysOfMonth;
	/** ob.week0 所在月的月首的星期,同lun.w0 */
	private static int weekFirst;
	/** ob.week 星期 */
	private static int week;
	/** ob.weeki 在本月中的周序号 */
	private static int weekIndex;
	/** ob.weekN 本月的总周数 */
	private static int weeksOfMonth;
	/** ob.Hyear 年(回历) */
	private static int hYear;
	/** ob.Hmonth 月(回历) */
	private static int hMonth;
	/** ob.Hday 日(回历) */
	private static int hDay;
	/** ob.Lmc 月名称 */
	private static String lunarMonthName;
	/** ob.Ldi 距农历月首的编移量,0对应初一 */
	private static int lunarMonthOffset;
	/** ob.Ldc 日名称 */
	private static String lunarDayName;
	/** ob.Ldn 月大小 */
	private static int daysofLunarMonth;
	/** ob.Lleap 闰状况(值为'闰'或空串) */
	private static String lunarLunarLeap;
	/** ob.Lmc2 下个月名称,判断除夕时要用到 */
	private static String nextLunarMonthName;
	/** ob.Ljq 节气 */
	private static String lunarSolarTerm;
	/** ob.cur_dz 距冬至的天数 */
	private static int daysToDZ;
	/** ob.cur_xz 距夏至的天数 */
	private static int daysToXZ;
	/** ob.cur_lq 距立秋的天数 */
	private static int daysToLQ;
	/** ob.cur_mz 距芒种的天数 */
	private static int daysToMZ;
	/** ob.cur_xs 距小暑的天数 */
	private static int daysToXS;
	/**
	 * ob.Lyear2, ob.Lyear3 干支年. Lyear2和Lyear3的区别在于新年的判定, Lyear2是立春, Lyear3则是春节[正月]
	 */
	private static String cnEraYear;
	/** ob.Lmonth2 干支月 */
	private static String cnEraMonth;
	/** ob.Lday2 干支日 */
	private static String cnEraDay;
	/** ob.Ltime2 干支时 */
	private static String cnEraTime;
	/** ob.XiZ 星座 */
	private static String constellation;
	/** ob.Lyear 农历年 */
	private static int lunarYear;
	/** ob.Lmonth 农历月 */
	private static int lunarMonth;
	/** ob.yxmc 月相名称 */
	private static String moonPhaseName;
	/** ob.yxjd 月相时刻(儒略日) */
	private static double moonPhaseTime;
	/** ob.yxsj 月相时间串 */
	private static String moonPhaseTimeStr;
	/** ob.jqmc 节气名称 */
	private static String solarTermName;
	/** ob.jqjd 节气时刻(儒略日) */
	private static double solarTermTime;
	/** ob.jqsj 节气时间串 */
	private static String solarTermTimeStr;
	/** ob.Lyear4 黄帝纪年 */
	private static int kingYear;

	/** r.A 重要喜庆日子名称(可将日子名称置红) */
	private static String impHappyName;
	/** r.B 重要日子名称 */
	private static String impName;
	/** r.C 各种日子名称(连成一大串) */
	private static String allName;
	/** r.Fjia 放假日子(可用于日期数字置红) */
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
