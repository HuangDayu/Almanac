package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 10:53
 */
public class LunarDTO {

    /**
     * 距农历月首的编移量,0对应初一
     */
    private int monthOffset;
    /**
     * 干支年
     */
    private String year;
    /**
     * 农历月名称
     */
    private String month;
    /**
     * 农历日名称
     */
    private String day;

    /**
     * 农历时
     */
    private String time;
    /**
     * 农历月大小
     */
    private int daysOfMonth;
    /**
     * 农历闰状况(值为'闰'或空串)
     */
    private String leapDesc;
    /**
     * 是否闰年
     */
    private Boolean leapYear;
    /**
     * 下个农历月名称,判断除夕时要用到
     */
    private String nextMonth;
    /**
     * 黄帝纪年
     */
    private int kingChronology;
    /**
     * 农历年,农历纪年(10进制,1984年起算)
     */
    private int yearChronology;
    /**
     * 农历月,农历纪月
     */
    private int monthChronology;

    /**
     * 生肖
     */
    private String zodiac;

    /**
     * 年号纪年
     */
    private String yearName;

    /**
     * 皇帝纪年
     */
    private String kingChronologyName;

    public int getMonthOffset() {
        return monthOffset;
    }

    public void setMonthOffset(int monthOffset) {
        this.monthOffset = monthOffset;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public String getLeapDesc() {
        return leapDesc;
    }

    public void setLeapDesc(String leapDesc) {
        this.leapDesc = leapDesc;
    }

    public Boolean getLeapYear() {
        return leapYear;
    }

    public void setLeapYear(Boolean leapYear) {
        this.leapYear = leapYear;
    }

    public String getNextMonth() {
        return nextMonth;
    }

    public void setNextMonth(String nextMonth) {
        this.nextMonth = nextMonth;
    }

    public int getYearChronology() {
        return yearChronology;
    }

    public void setYearChronology(int yearChronology) {
        this.yearChronology = yearChronology;
    }

    public int getMonthChronology() {
        return monthChronology;
    }

    public void setMonthChronology(int monthChronology) {
        this.monthChronology = monthChronology;
    }

    public int getKingChronology() {
        return kingChronology;
    }

    public void setKingChronology(int kingChronology) {
        this.kingChronology = kingChronology;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getKingChronologyName() {
        return kingChronologyName;
    }

    public void setKingChronologyName(String kingChronologyName) {
        this.kingChronologyName = kingChronologyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInfo() {
        return year + zodiac + "年" + month + (month.length() < 2 ? "月" : "") + day;
    }

    @Override
    public String toString() {
        return "LunarDTO{" +
                "monthOffset=" + monthOffset +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", daysOfMonth=" + daysOfMonth +
                ", leapDesc='" + leapDesc + '\'' +
                ", leapYear=" + leapYear +
                ", nextMonth='" + nextMonth + '\'' +
                ", kingChronology=" + kingChronology +
                ", yearChronology=" + yearChronology +
                ", monthChronology=" + monthChronology +
                ", zodiac='" + zodiac + '\'' +
                ", yearName='" + yearName + '\'' +
                ", kingChronologyName='" + kingChronologyName + '\'' +
                '}';
    }
}
