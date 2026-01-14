package cn.huangdayu.almanac.aggregates.lunar;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.dto.InfoDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;

import java.util.LinkedList;

/**
 * 农历，阴历，以 [正月初一] 作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 10:53
 */
public class Lunar extends AbstractAlmanac {

    public Lunar(TimeZoneDTO timeZoneDTO, int julianDayForToday, QiShuo qiShuo) {
        //------------------------------------农历排月序计算------------------------------------//

        // 农历所在月的序数
        int mk = (julianDayForToday - qiShuo.getHeShuo()[0]) / 30;
        if (mk < 13 && qiShuo.getHeShuo()[mk + 1] <= julianDayForToday) {
            mk++;
        }

        // 距农历月首的编移量,0对应初一
        this.setMonthOffset(julianDayForToday - qiShuo.getHeShuo()[mk]);
        // 农历日名称
        this.setDay(AnnalsUtils.DAY_NAME[julianDayForToday - qiShuo.getHeShuo()[mk]]);
        this.setLeapYear(qiShuo.getLeapMonthIndex() > 0);
        // 月名称
        this.setMonth(qiShuo.getMonthNames()[mk]);
        // 月大小
        this.setDaysOfMonth(qiShuo.getMonthValue()[mk]);
        // 闰状况
        this.setLeapMonth((qiShuo.getLeapMonthIndex() != 0 && qiShuo.getLeapMonthIndex() == mk));
        // 下个月名称,判断除夕时要用到
        this.setNextMonth(mk < 13 ? qiShuo.getMonthNames()[mk + 1] : "未知");
        // 时辰
        int sum = (int) (timeZoneDTO.getHour() + 0.01 * timeZoneDTO.getMinute());
        int index = (sum + 1) / 2;
        if (index >= 12) {
            index = 0;
        }
        String lunarTime = AnnalsUtils.DIZHI[index] + "时" + ConstantsUtils.GENG[index];
        if ((timeZoneDTO.getMinute() % 15) > 13) {
            int k = (timeZoneDTO.getMinute() + 3) / 15;
            lunarTime = lunarTime + ConstantsUtils.KE[k];
        }
        this.setTime(lunarTime);
        // 一般第3个月为春节
        int value = qiShuo.getHeShuo()[2];
        for (int l = 0; l < 14; l++) {
            // 找春节
            if (!"正".equals(qiShuo.getMonthNames()[l]) || qiShuo.getLeapMonthIndex() == l && l != 0) {
                continue;
            }
            value = qiShuo.getHeShuo()[l];
            if (julianDayForToday < value) {
                value -= 365;
                // 无需再找下一个正月
                break;
            }
        }
        // 计算该年春节与1984年平均春节(立春附近)相差天数估计
        value = value + 5810;
        // 农历纪年(10进制,1984年起算)
        int lunarYears = (int) Math.floor(value / 365.2422 + 0.5);
        value = lunarYears + 12000;
        // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
        // 黄帝纪年,春节才视为新年
        int kingChronology = lunarYears + 1984 + 2698;
        this.setKingChronology(kingChronology);
        this.setKingChronologyName("开元" + kingChronology + "年");
        // 干支纪年（春节）
        this.setYear(AnnalsUtils.TIANGAN[value % 10] + AnnalsUtils.DIZHI[value % 12]);
        // 该年对应的生肖
        this.setZodiac(AnnalsUtils.SHENGXIAO[value % 12]);
        // 年号
        this.setYearName(AnnalsUtils.getYearName(timeZoneDTO.getEraYear()));

        // 干支纪年处理 以立春为界定年首
        value = (int) (qiShuo.getZhongQi()[3] + (julianDayForToday < qiShuo.getZhongQi()[3] ? -365 : 0) + 365.25 * 16 - 35);
        // 以立春为界定纪年 农历纪年(10进制,1984年起算)
        this.setYearChronology((int) Math.floor(value / 365.2422 + 0.5));

        // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
        mk = (int) Math.floor((julianDayForToday - qiShuo.getZhongQi()[0]) / 30.43685);
        if (mk < 12 && julianDayForToday >= qiShuo.getZhongQi()[2 * mk + 1]) {
            // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
            mk++;
        }
        // 相对于1998年12月7(大雪)的月数,900000为正数基数
        value = mk + (int) Math.floor((qiShuo.getZhongQi()[12] + 390) / 365.2422) * 12 + 900000;
        this.setMonthChronologySum(value);
        // 农历几月
        this.setMonthChronology(value % 12);
    }


    /**
     * 距农历月首的编移量,0对应初一
     */
    private int monthOffset;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String month;
    /**
     * 日
     */
    private String day;

    /**
     * 时
     */
    private String time;
    /**
     * 农历月大小
     */
    private int daysOfMonth;
    /**
     * 农历闰状况(值为'闰'或空串)
     */
    private Boolean leapMonth = false;
    /**
     * 是否闰年
     */
    private Boolean leapYear = false;
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
     * 农历月总数
     */
    public int monthChronologySum;

    /**
     * 农历月,农历几月
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


    @Override
    public InfoDTO getBaseInfo() {
        return new InfoDTO("农历", "Lunar", year + zodiac + "年" + month + (month.length() < 2 ? "月" : "") + day + time);
    }


    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("农历年","year",year));
        list.add(new InfoDTO("农历月","month",month));
        list.add(new InfoDTO("农历日","day",day));
        list.add(new InfoDTO("农历时","time",time));
        list.add(new InfoDTO("是否闰月","leapMonth",leapMonth + ""));
        list.add(new InfoDTO("是否闰年","leapYear",leapYear + ""));
        list.add(new InfoDTO("农历生肖","zodiac",zodiac));
        list.add(new InfoDTO("皇帝年号","yearName",yearName));
        list.add(new InfoDTO("黄帝纪年","kingChronology",kingChronology + ""));
        list.add(new InfoDTO("开元纪年","kingChronologyName",kingChronologyName));
        return list;
    }

    public int getMonthOffset() {
        return monthOffset;
    }

    public void setMonthOffset(int monthOffset) {
        this.monthOffset = monthOffset;
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

    public int getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public Boolean getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(Boolean leapMonth) {
        this.leapMonth = leapMonth;
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

    public int getKingChronology() {
        return kingChronology;
    }

    public void setKingChronology(int kingChronology) {
        this.kingChronology = kingChronology;
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
}
