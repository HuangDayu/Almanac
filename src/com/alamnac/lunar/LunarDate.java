package com.alamnac.lunar;

public class LunarDate {

	LunarDate lunarDate;
	LunarCalendar lunarCalendar;

	LunarDate() {
	}

	public LunarDate(LunarDate lunarDate, LunarCalendar lunarCalendar) {
		this.lunarDate = lunarDate;
		this.lunarCalendar = lunarCalendar;
	}

	/** 2000.0����������,����ʱ12:00 */
	private int julian_Days;
	/** ���ڹ������������� */
	private int gregorian_DayIndexForMonth;
	/** ���ڹ�����,ͬlun.y */
	private int gregorian_Year;
	/** ���ڹ�����,ͬlun.m */
	private int gregorian_Month;
	/** ������(����) */
	private int gregorian_Day;
	/** ���ڹ����µ������� */
	private int gregorian_DaysOfMonth;
	/** �����µ����׵�����,ͬlun.w0 */
	private int gregorian_WeekFirstForMonth;
	/** ob.week ���� */
	private int gregorian_Week;
	/** ob.weeki �ڱ����е������ */
	private int gregorian_WeekIndexForMonth;
	/** ob.weekN ���µ������� */
	private int gregorian_WeeksOfMonth;
	/** ob.Hyear ��(����) */
	private int islamic_Year;
	/** ob.Hmonth ��(����) */
	private int islamic_Month;
	/** ob.Hday ��(����) */
	private int islamic_Day;
	/** ob.Ldi ��ũ�����׵ı�����,0��Ӧ��һ */
	private int lunar_MonthOffset;
	/** ob.Lmc ũ�������� */
	private String lunar_MonthName;
	/** ob.Ldc ũ�������� */
	private String lunar_DayName;
	/** ob.Ldn ũ���´�С */
	private int lunar_Days_OfMonth;
	/** ob.Lleap ũ����״��(ֵΪ'��'��մ�) */
	private String lunar_Lunar_isLeap;
	/** ob.Lmc2 �¸�ũ��������,�жϳ�ϦʱҪ�õ� */
	private String lunar_NextLunarMonthName;
	/** ob.Ljq 24���� */
	private String lunar_SolarTerm;
	/** ob.cur_dz �ඬ�������� */
	private int lunar_Last_Dongzhi;
	/** ob.cur_xz ������������ */
	private int lunar_Last_Xiazhi;
	/** ob.cur_lq ����������� */
	private int lunar_Last_Liqiu;
	/** ob.cur_mz ��â�ֵ����� */
	private int lunar_Last_Mangzhong;
	/** ob.cur_xs ��С������� */
	private int lunar_Last_Xiaoshu;
	/** ��֧�� */
	private String chineseEra_Year;
	/** ��֧�� */
	private String chineseEra_Month;
	/** ��֧�� */
	private String chineseEra_Day;
	/** ��֧ʱ */
	private String chineseEra_Time;
	/** ob.XiZ ���� */
	private String constellation;
	/** ob.Lyear ũ����:ũ������(10����,1984������) */
	private int lunar_Period_Of_Years;
	/** ob.Lmonth ũ���� */
	private int lunar_Period_Of_Months;
	/** ob.yxmc �������� */
	private String moon_PhaseName;
	/** ob.yxjd ����ʱ��(������) */
	private double moon_PhaseTime;
	/** ob.yxsj ����ʱ�䴮 */
	private String moon_PhaseTimeStr;
	/** ob.jqmc �������� */
	private String lunar_SolarTerm_Name;
	/** ob.jqjd ����ʱ��(������) */
	private double lunar_SolarTerm_Time;
	/** ob.jqsj ����ʱ�䴮 */
	private String lunar_SolarTerm_Time_Str;
	/** ob.Lyear4 �Ƶۼ��� */
	private int lunar_king_Years;
	/** r.A ��Ҫϲ����������(�ɽ����������ú�) */
	private String happyDay_Name;
	/** r.B ��Ҫ�������� */
	private String majorDay_Name;
	/** r.C ������������(����һ��) */
	private String allDay_Name;
	/** r.Fjia �ż�����(���������������ú�) */
	private int holiday_INT;
	/** �����Ӧ����Ф */
	private static String lunarDate_Animal_Year;
	/** ��֧���� */
	private static String LunarDate_China_Era_Year;
	/** ��ż��� */
	private static String LunarDate_China_Era_Year_Number;

	public int getJulian_Days() {
		return julian_Days;
	}

	public void setJulian_Days(int julian_Days) {
		this.julian_Days = julian_Days;
	}

	public int getGregorian_DayIndexForMonth() {
		return gregorian_DayIndexForMonth;
	}

	public void setGregorian_DayIndexForMonth(int gregorian_DayIndexForMonth) {
		this.gregorian_DayIndexForMonth = gregorian_DayIndexForMonth;
	}

	public int getGregorian_DaysOfMonth() {
		return gregorian_DaysOfMonth;
	}

	public int getIslamic_Year() {
		return islamic_Year;
	}

	public int getIslamic_Month() {
		return islamic_Month;
	}

	public int getIslamic_Day() {
		return islamic_Day;
	}

	public String getConstellation() {
		return constellation;
	}

	public String getMoon_PhaseName() {
		return moon_PhaseName;
	}

	public int getLunar_king_Years() {
		return lunar_king_Years;
	}

	public int getGregorian_Year() {
		return gregorian_Year;
	}

	public void setGregorian_Year(int gregorian_Year) {
		this.gregorian_Year = gregorian_Year;
	}

	public int getGregorian_Month() {
		return gregorian_Month;
	}

	public void setGregorian_Month(int gregorian_Month) {
		this.gregorian_Month = gregorian_Month;
	}

	public int getGregorian_Day() {
		return gregorian_Day;
	}

	public void setGregorian_Day(int gregorian_Day) {
		this.gregorian_Day = gregorian_Day;
	}

	public void setGregorian_DaysOfMonth(int gregorian_DaysOfMonth) {
		this.gregorian_DaysOfMonth = gregorian_DaysOfMonth;
	}

	public int getGregorian_WeekFirstForMonth() {
		return gregorian_WeekFirstForMonth;
	}

	public void setGregorian_WeekFirstForMonth(int gregorian_WeekFirstForMonth) {
		this.gregorian_WeekFirstForMonth = gregorian_WeekFirstForMonth;
	}

	public int getGregorian_Week() {
		return gregorian_Week;
	}

	public void setGregorian_Week(int gregorian_Week) {
		this.gregorian_Week = gregorian_Week;
	}

	public int getGregorian_WeekIndexForMonth() {
		return gregorian_WeekIndexForMonth;
	}

	public void setGregorian_WeekIndexForMonth(int gregorian_WeekIndexForMonth) {
		this.gregorian_WeekIndexForMonth = gregorian_WeekIndexForMonth;
	}

	public int getGregorian_WeeksOfMonth() {
		return gregorian_WeeksOfMonth;
	}

	public void setGregorian_WeeksOfMonth(int gregorian_WeeksOfMonth) {
		this.gregorian_WeeksOfMonth = gregorian_WeeksOfMonth;
	}

	public void setIslamic_Year(int islamic_Year) {
		this.islamic_Year = islamic_Year;
	}

	public void setIslamic_Month(int islamic_Month) {
		this.islamic_Month = islamic_Month;
	}

	public void setIslamic_Day(int islamic_Day) {
		this.islamic_Day = islamic_Day;
	}

	public int getLunar_MonthOffset() {
		return lunar_MonthOffset;
	}

	public void setLunar_MonthOffset(int lunar_MonthOffset) {
		this.lunar_MonthOffset = lunar_MonthOffset;
	}

	public String getLunar_MonthName() {
		return lunar_MonthName;
	}

	public void setLunar_MonthName(String lunar_MonthName) {
		this.lunar_MonthName = lunar_MonthName;
	}

	public String getLunar_DayName() {
		return lunar_DayName;
	}

	public void setLunar_DayName(String lunar_DayName) {
		this.lunar_DayName = lunar_DayName;
	}

	public int getLunar_Days_OfMonth() {
		return lunar_Days_OfMonth;
	}

	public void setLunar_Days_OfMonth(int lunar_Days_OfMonth) {
		this.lunar_Days_OfMonth = lunar_Days_OfMonth;
	}

	public String getLunar_Lunar_isLeap() {
		return lunar_Lunar_isLeap;
	}

	public void setLunar_Lunar_isLeap(String lunar_Lunar_isLeap) {
		this.lunar_Lunar_isLeap = lunar_Lunar_isLeap;
	}

	public String getLunar_NextLunarMonthName() {
		return lunar_NextLunarMonthName;
	}

	public void setLunar_NextLunarMonthName(String lunar_NextLunarMonthName) {
		this.lunar_NextLunarMonthName = lunar_NextLunarMonthName;
	}

	public String getLunar_SolarTerm() {
		return lunar_SolarTerm;
	}

	public void setLunar_SolarTerm(String lunar_SolarTerm) {
		this.lunar_SolarTerm = lunar_SolarTerm;
	}

	public int getLunar_Last_Dongzhi() {
		return lunar_Last_Dongzhi;
	}

	public void setLunar_Last_Dongzhi(int lunar_Last_Dongzhi) {
		this.lunar_Last_Dongzhi = lunar_Last_Dongzhi;
	}

	public int getLunar_Last_Xiazhi() {
		return lunar_Last_Xiazhi;
	}

	public void setLunar_Last_Xiazhi(int lunar_Last_Xiazhi) {
		this.lunar_Last_Xiazhi = lunar_Last_Xiazhi;
	}

	public int getLunar_Last_Liqiu() {
		return lunar_Last_Liqiu;
	}

	public void setLunar_Last_Liqiu(int lunar_Last_Liqiu) {
		this.lunar_Last_Liqiu = lunar_Last_Liqiu;
	}

	public int getLunar_Last_Mangzhong() {
		return lunar_Last_Mangzhong;
	}

	public void setLunar_Last_Mangzhong(int lunar_Last_Mangzhong) {
		this.lunar_Last_Mangzhong = lunar_Last_Mangzhong;
	}

	public int getLunar_Last_Xiaoshu() {
		return lunar_Last_Xiaoshu;
	}

	public void setLunar_Last_Xiaoshu(int lunar_Last_Xiaoshu) {
		this.lunar_Last_Xiaoshu = lunar_Last_Xiaoshu;
	}

	public String getChineseEra_Year() {
		return chineseEra_Year;
	}

	public void setChineseEra_Year(String chineseEra_Year) {
		this.chineseEra_Year = chineseEra_Year;
	}

	public String getChineseEra_Month() {
		return chineseEra_Month;
	}

	public void setChineseEra_Month(String chineseEra_Month) {
		this.chineseEra_Month = chineseEra_Month;
	}

	public String getChineseEra_Day() {
		return chineseEra_Day;
	}

	public void setChineseEra_Day(String chineseEra_Day) {
		this.chineseEra_Day = chineseEra_Day;
	}

	public String getChineseEra_Time() {
		return chineseEra_Time;
	}

	public void setChineseEra_Time(String chineseEra_Time) {
		this.chineseEra_Time = chineseEra_Time;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public int getLunar_Period_Of_Years() {
		return lunar_Period_Of_Years;
	}

	public void setLunar_Period_Of_Years(int lunar_Period_Of_Years) {
		this.lunar_Period_Of_Years = lunar_Period_Of_Years;
	}

	public int getLunar_Period_Of_Months() {
		return lunar_Period_Of_Months;
	}

	public void setLunar_Period_Of_Months(int lunar_Period_Of_Months) {
		this.lunar_Period_Of_Months = lunar_Period_Of_Months;
	}

	public void setMoon_PhaseName(String moon_PhaseName) {
		this.moon_PhaseName = moon_PhaseName;
	}

	public double getMoon_PhaseTime() {
		return moon_PhaseTime;
	}

	public void setMoon_PhaseTime(double moon_PhaseTime) {
		this.moon_PhaseTime = moon_PhaseTime;
	}

	public String getMoon_PhaseTimeStr() {
		return moon_PhaseTimeStr;
	}

	public void setMoon_PhaseTimeStr(String moon_PhaseTimeStr) {
		this.moon_PhaseTimeStr = moon_PhaseTimeStr;
	}

	public String getLunar_SolarTerm_Name() {
		return lunar_SolarTerm_Name;
	}

	public void setLunar_SolarTerm_Name(String lunar_SolarTerm_Name) {
		this.lunar_SolarTerm_Name = lunar_SolarTerm_Name;
	}

	public double getLunar_SolarTerm_Time() {
		return lunar_SolarTerm_Time;
	}

	public void setLunar_SolarTerm_Time(double lunar_SolarTerm_Time) {
		this.lunar_SolarTerm_Time = lunar_SolarTerm_Time;
	}

	public String getLunar_SolarTerm_Time_Str() {
		return lunar_SolarTerm_Time_Str;
	}

	public void setLunar_SolarTerm_Time_Str(String lunar_SolarTerm_Time_Str) {
		this.lunar_SolarTerm_Time_Str = lunar_SolarTerm_Time_Str;
	}

	public void setLunar_king_Years(int lunar_king_Years) {
		this.lunar_king_Years = lunar_king_Years;
	}

	public String getHappyDay_Name() {
		return happyDay_Name;
	}

	public void setHappyDay_Name(String happyDay_Name) {
		this.happyDay_Name = happyDay_Name;
	}

	public String getMajorDay_Name() {
		return majorDay_Name;
	}

	public void setMajorDay_Name(String majorDay_Name) {
		this.majorDay_Name = majorDay_Name;
	}

	public String getAllDay_Name() {
		return allDay_Name;
	}

	public void setAllDay_Name(String allDay_Name) {
		this.allDay_Name = allDay_Name;
	}

	public int getHoliday_INT() {
		return holiday_INT;
	}

	public void setHoliday_INT(int holiday_INT) {
		this.holiday_INT = holiday_INT;
	}

	public String getLunarDate_China_Era_Year_Number() {
		return LunarDate_China_Era_Year_Number;
	}

	public void setLunarDate_China_Era_Year_Number(String lunarDate_China_Era_Year_Number) {
		LunarDate_China_Era_Year_Number = lunarDate_China_Era_Year_Number;
	}

	public String getLunarDate_China_Era_Year() {
		return LunarDate_China_Era_Year;
	}

	public void setLunarDate_China_Era_Year(String lunarDate_China_Era_Year) {
		LunarDate_China_Era_Year = lunarDate_China_Era_Year;
	}

	public String getLunarDate_Animal_Year() {
		return lunarDate_Animal_Year;
	}

	public void setLunarDate_Animal_Year(String lunarDate_Animal_Year) {
		this.lunarDate_Animal_Year = lunarDate_Animal_Year;
	}

	/***
	 * ��ȡ��֧ũ�������ַ���
	 * 
	 * @return
	 */
	public String getChinaGanZhiDateString_Obj() {
		return lunarDate.getChineseEra_Year()  + lunarDate.getChineseEra_Month() 
				+ lunarDate.getChineseEra_Day()  + lunarDate.getChineseEra_Time();
	}
	
	public String getChineseEra_Year_Obj() {
		return lunarDate.getChineseEra_Year() ;
	}
	
	public String getChineseEra_Month_Obj() {
		return  lunarDate.getChineseEra_Month();
	}
	
	public String getChineseEra_Day_Obj() {
		return lunarDate.getChineseEra_Day() ;
	}
	public String getChineseEra_Time_Obj() {
		return  lunarDate.getChineseEra_Time();
	}

	/***
	 * ũ����
	 * 
	 * @return
	 */
	public int getLunarDayInt_Obj() {
		return lunarDate.getLunar_MonthOffset() + 1;
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getLunarDayString_Obj() {
		return lunarDate.getLunar_DayName();
	}

	/***
	 * ĳũ�����ж�����
	 * 
	 * @return
	 */
	public int getLunarDaySumInMonth_Obj() {
		return lunarDate.getLunar_Days_OfMonth();
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getLunarMonthString_Obj() {
		return lunarDate.getLunar_MonthName();
	}

	/***
	 * ũ���꣨�ʵۼ��꣩
	 * 
	 * @return
	 */
	public int getLunarYearInt_Obj() {
		return getLunar_king_Years_Obj();
	}

	/***
	 * ũ�����ַ���
	 * 
	 * @return
	 */
	public String getLunarYearString_Obj() {
		return lunarDate.getChineseEra_Year();
	}

	/***
	 * �Ƿ��Ǵ���
	 * 
	 * @return
	 */
	public boolean isBigLunarMonthBool_Obj() {
		return lunarDate.getLunar_Days_OfMonth() > 29;
	}

	/***
	 * �Ƿ�������
	 * 
	 * @return
	 */
	public boolean isLeapMonthBool_Obj() {
		return "��".equals(lunarDate.getLunar_Lunar_isLeap());
	}

	/**
	 * �Ƿ�������
	 * 
	 * @return
	 */
	public boolean isLeapYearBool_Obj() {
		return lunarCalendar.getQiShuoClassObj().leap > 0;
	}

	public int getIslamic_Year_Obj() {
		return lunarDate.islamic_Year;
	}

	public int getIslamic_Month_Obj() {
		return lunarDate.islamic_Month;
	}

	public int getIslamic_Day_Obj() {
		return lunarDate.islamic_Day;
	}

	public int getGregorian_DaysOfMonth_Obj() {
		return lunarDate.gregorian_DaysOfMonth;
	}

	public String getConstellation_Obj() {
		return lunarDate.constellation;
	}

	public String getMoon_PhaseName_Obj() {
		return lunarDate.moon_PhaseName;
	}

	public int getLunar_king_Years_Obj() {
		return lunarDate.lunar_king_Years;
	}

	public String getAllDay_Name_Obj() {
		return lunarDate.allDay_Name;
	}
	public String getNowOrNext24SolarTerm() {
		return lunarCalendar.getNowSolarTerm();
	}
	/***
	 * ���
	 * @return
	 */
	public String getChinaEra_TianGan_String() {
		String tiangan=getChinaGanZhiDateString_Obj();
		char c1=tiangan.charAt(0);
		char c2=tiangan.charAt(2);
		char c3=tiangan.charAt(4);
		char c4=tiangan.charAt(6);
		return String.valueOf(c1)+c2+c3+c4;
	}
	/***
	 * ��֧
	 * @return
	 */
	public String getChinaEra_DiZhi_String() {
		String tiangan=getChinaGanZhiDateString_Obj();
		char c1=tiangan.charAt(1);
		char c2=tiangan.charAt(3);
		char c3=tiangan.charAt(5);
		char c4=tiangan.charAt(7);
		return String.valueOf(c1)+c2+c3+c4;
	}
	/***
	 * ��˹������������
	 * @return
	 */
	public String getIslamicDate_String() {
		return getIslamic_Year_Obj() + "��"+getIslamic_Month_Obj() + "��"+getIslamic_Day_Obj() + "��";
	}
	/***
	 * �й�ũ���ַ���
	 * @return
	 */
	public String getChinaLuanrDate_String() {
		return getLunarYearString_Obj()+"��"+getLunarMonthString_Obj()+"��"+getLunarDayString_Obj()+"��";
	}
	
	
}
