package cn.huangdayu.almanac.aggregates.julian;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;

/**
 * 儒略日
 *
 * @author huangdayu create at 2021/1/21 11:25
 */
public class Julian extends AbstractAlmanac {

    public Julian(int julianDayForToday, QiShuo qiShuo) {
        this.days = julianDayForToday + CommonUtils.JULIAN_FOR_2000;
        // 星座
        int mk = (int) Math.floor((julianDayForToday - qiShuo.getZhongQi()[0] - 15) / 30.43685);
        if (mk < 11 && julianDayForToday >= qiShuo.getZhongQi()[2 * mk + 2]) {
            // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
            mk++;
        }
        this.setConstellation(AnnalsUtils.XINGZUO[(mk + 12) % 12] + "座");
    }

    public Julian(int year, int month) {
        // FIXME 2021-01-22 不知为何减去西历两千年的儒略日
        // 这个月1号的儒略日,公历月首,中午
        this.firstJulianDayOfMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(year, month)) - CommonUtils.JULIAN_FOR_2000);
        int nextMonth = month + 1;
        int nextYear = year;
        if (nextMonth > 12) {
            nextMonth = nextMonth - 12;
            nextYear = year + 1;
        }
        // 这个月1号的儒略日和下个月1号的儒略日之差,月天数(公历)
        this.numberDayOfMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(nextYear, nextMonth)) - CommonUtils.JULIAN_FOR_2000 - firstJulianDayOfMonth);
        // 本月第一天的星期
        this.weekFirstDayOfMonth = (firstJulianDayOfMonth + CommonUtils.JULIAN_FOR_2000 + 1 + 7000000) % 7;
    }

    /**
     * 月的第一天的儒略日
     */
    private int firstJulianDayOfMonth;

    /**
     * 月份的天数
     */
    private int numberDayOfMonth;

    /**
     * 本月第一天的星期
     */
    private int weekFirstDayOfMonth;

    private Integer days;

    /**
     * 星座
     */
    private String constellation;

    @Override
    public String getInfo() {
        return getDays() + " " + getConstellation();
    }

    public int getFirstJulianDayOfMonth() {
        return firstJulianDayOfMonth;
    }

    public void setFirstJulianDayOfMonth(int firstJulianDayOfMonth) {
        this.firstJulianDayOfMonth = firstJulianDayOfMonth;
    }

    public int getNumberDayOfMonth() {
        return numberDayOfMonth;
    }

    public void setNumberDayOfMonth(int numberDayOfMonth) {
        this.numberDayOfMonth = numberDayOfMonth;
    }

    public int getWeekFirstDayOfMonth() {
        return weekFirstDayOfMonth;
    }

    public void setWeekFirstDayOfMonth(int weekFirstDayOfMonth) {
        this.weekFirstDayOfMonth = weekFirstDayOfMonth;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
}
