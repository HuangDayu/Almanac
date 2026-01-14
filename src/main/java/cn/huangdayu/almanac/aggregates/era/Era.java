package cn.huangdayu.almanac.aggregates.era;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.dto.InfoDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;

import java.util.LinkedList;

/**
 * 黄历，天干地支，以 [立春]  作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 11:00
 */
public class Era extends AbstractAlmanac {

    public Era(int julianDayForToday, QiShuo qiShuo, TimeZoneDTO timeZoneDTO) {
        // 干支纪年处理 以立春为界定年首
        int value = (int) (qiShuo.getZhongQi()[3] + (julianDayForToday < qiShuo.getZhongQi()[3] ? -365 : 0) + 365.25 * 16 - 35);
        // 以立春为界定纪年 农历纪年(10进制,1984年起算)
        this.setYearChronology((int) Math.floor(value / 365.2422 + 0.5));

        // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
        int mk = (int) Math.floor((julianDayForToday - qiShuo.getZhongQi()[0]) / 30.43685);
        if (mk < 12 && julianDayForToday >= qiShuo.getZhongQi()[2 * mk + 1]) {
            // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
            mk++;
        }
        // 相对于1998年12月7(大雪)的月数,900000为正数基数
        value = mk + (int) Math.floor((qiShuo.getZhongQi()[12] + 390) / 365.2422) * 12 + 900000;
        this.setMonthChronologySum(value);
        // 农历几月
        this.setMonthChronology(value % 12);

        value = this.getYearChronology() + 12000;
        // 干支纪年(立春)
        this.setYear(AnnalsUtils.TIANGAN[value % 10] + AnnalsUtils.DIZHI[value % 12]);

        value = this.getMonthChronologySum();
        this.setMonth(AnnalsUtils.TIANGAN[value % 10] + AnnalsUtils.DIZHI[value % 12]);

        // 纪日,2000年1月7日起算
        value = julianDayForToday - 6 + 9000000;

        this.setDay(AnnalsUtils.TIANGAN[value % 10] + AnnalsUtils.DIZHI[value % 12]);

        int hour = (timeZoneDTO.getHour() + 1) / 2;
        //天干地支时
        this.setTime(AnnalsUtils.TIANGAN[(hour + value * 12) % 10] + AnnalsUtils.DIZHI[hour % 12]);
    }

    /**
     * 干支年
     */
    private String year;
    /**
     * 干支月
     */
    private String month;
    /**
     * 干支日
     */
    private String day;
    /**
     * 干支时
     */
    private String time;

    /**
     * 农历年,农历纪年(10进制,1984年起算)
     */
    private int yearChronology;

    /**
     * 农历月总数
     */
    public int monthChronologySum;

    /**
     * 农历月,农历几月
     */
    private int monthChronology;

    @Override
    public InfoDTO getBaseInfo() {
        return new InfoDTO("黄历", "Era", year + "年" + month + "月" + day + "日" + time + "时");
    }

    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("干支年", "year", year));
        list.add(new InfoDTO("干支月", "month", month));
        list.add(new InfoDTO("干支日", "day", day));
        list.add(new InfoDTO("干支时", "time", time));
        return list;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYearChronology() {
        return yearChronology;
    }

    public void setYearChronology(int yearChronology) {
        this.yearChronology = yearChronology;
    }

    public int getMonthChronologySum() {
        return monthChronologySum;
    }

    public void setMonthChronologySum(int monthChronologySum) {
        this.monthChronologySum = monthChronologySum;
    }

    public int getMonthChronology() {
        return monthChronology;
    }

    public void setMonthChronology(int monthChronology) {
        this.monthChronology = monthChronology;
    }
}
