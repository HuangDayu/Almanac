package test_7;

public class LunarDate {

	/** ob.d0 2000.0起算儒略日,北京时12:00 */
	private int dayRL;
	/** ob.di 所在公历月内日序数 */
	private int dayIndex;
	/** ob.y 所在公历年,同lun.y */
	private int year;
	/** ob.m 所在公历月,同lun.m */
	private int month;
	/** ob.d 日名称(公历) */
	private int day;
	/** ob.dn 所在公历月的总天数,同lun.dn */
	private int daysOfMonth;
	/** ob.week0 所在月的月首的星期,同lun.w0 */
	private int weekFirst;
	/** ob.week 星期 */
	private int week;
	/** ob.weeki 在本月中的周序号 */
	private int weekIndex;
	/** ob.weekN 本月的总周数 */
	private int weeksOfMonth;
	/** ob.Hyear 年(回历) */
	private int hYear;
	/** ob.Hmonth 月(回历) */
	private int hMonth;
	/** ob.Hday 日(回历) */
	private int hDay;
	/** ob.Lmc 月名称 */
	private String lunarMonthName;
	/** ob.Ldi 距农历月首的编移量,0对应初一 */
	private int lunarMonthOffset;
	/** ob.Ldc 日名称 */
	private String lunarDayName;
	/** ob.Ldn 月大小 */
	private int daysofLunarMonth;
	/** ob.Lleap 闰状况(值为'闰'或空串) */
	private String lunarLunarLeap;
	/** ob.Lmc2 下个月名称,判断除夕时要用到 */
	private String nextLunarMonthName;
	/** ob.Ljq 节气 */
	private String lunarSolarTerm;
	/** ob.cur_dz 距冬至的天数 */
	private int daysToDZ;
	/** ob.cur_xz 距夏至的天数 */
	private int daysToXZ;
	/** ob.cur_lq 距立秋的天数 */
	private int daysToLQ;
	/** ob.cur_mz 距芒种的天数 */
	private int daysToMZ;
	/** ob.cur_xs 距小暑的天数 */
	private int daysToXS;
	/**
	 * ob.Lyear2, ob.Lyear3 干支年. Lyear2和Lyear3的区别在于新年的判定, Lyear2是立春, Lyear3则是春节[正月]
	 */
	private String cnEraYear;
	/** ob.Lmonth2 干支月 */
	private String cnEraMonth;
	/** ob.Lday2 干支日 */
	private String cnEraDay;
	/** ob.Ltime2 干支时 */
	private String cnEraTime;
	/** ob.XiZ 星座 */
	private String constellation;
	/** ob.Lyear 农历年 */
	private int lunarYear;
	/** ob.Lmonth 农历月 */
	private int lunarMonth;
	/** ob.yxmc 月相名称 */
	private String moonPhaseName;
	/** ob.yxjd 月相时刻(儒略日) */
	private double moonPhaseTime;
	/** ob.yxsj 月相时间串 */
	private String moonPhaseTimeStr;
	/** ob.jqmc 节气名称 */
	private String solarTermName;
	/** ob.jqjd 节气时刻(儒略日) */
	private double solarTermTime;
	/** ob.jqsj 节气时间串 */
	private String solarTermTimeStr;
	/** ob.Lyear4 黄帝纪年 */
	private int kingYear;

	/** r.A 重要喜庆日子名称(可将日子名称置红) */
	private String impHappyName;
	/** r.B 重要日子名称 */
	private String impName;
	/** r.C 各种日子名称(连成一大串) */
	private String allName;
	/** r.Fjia 放假日子(可用于日期数字置红) */
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
