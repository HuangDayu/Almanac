package cn.huangdayu.almanac.aggregates.lunar;

import cn.huangdayu.almanac.aggregates.BaseAlmanac;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 农历，阴历，以 [正月初一] 作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 10:53
 */
@Getter
@Setter
public class Lunar extends BaseAlmanac {

    public Lunar(TimeZoneDTO timeZoneDTO, int julianDayForToday, QiShuo qiShuo) {
        //------------------------------------农历排月序计算------------------------------------//

        // 农历所在月的序数
        int mk = (julianDayForToday - qiShuo.heShuo[0]) / 30;
        if (mk < 13 && qiShuo.heShuo[mk + 1] <= julianDayForToday) {
            mk++;
        }

        // 距农历月首的编移量,0对应初一
        this.setMonthOffset(julianDayForToday - qiShuo.heShuo[mk]);
        // 农历日名称
        this.setDay(AnnalsUtils.DAY_NAME[julianDayForToday - qiShuo.heShuo[mk]]);
        this.setLeapYear(qiShuo.leapMonthIndex > 0);
        // 月名称
        this.setMonth(qiShuo.monthNames[mk]);
        // 月大小
        this.setDaysOfMonth(qiShuo.monthValue[mk]);
        // 闰状况
        this.setLeapMonth((qiShuo.leapMonthIndex != 0 && qiShuo.leapMonthIndex == mk));
        // 下个月名称,判断除夕时要用到
        this.setNextMonth(mk < 13 ? qiShuo.monthNames[mk + 1] : "未知");
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
        int value = qiShuo.heShuo[2];
        for (int l = 0; l < 14; l++) {
            // 找春节
            if (!"正".equals(qiShuo.monthNames[l]) || qiShuo.leapMonthIndex == l && l != 0) {
                continue;
            }
            value = qiShuo.heShuo[l];
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
        value = (int) (qiShuo.zhongQi[3] + (julianDayForToday < qiShuo.zhongQi[3] ? -365 : 0) + 365.25 * 16 - 35);
        // 以立春为界定纪年 农历纪年(10进制,1984年起算)
        this.setYearChronology((int) Math.floor(value / 365.2422 + 0.5));

        // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
        mk = (int) Math.floor((julianDayForToday - qiShuo.zhongQi[0]) / 30.43685);
        if (mk < 12 && julianDayForToday >= qiShuo.zhongQi[2 * mk + 1]) {
            // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
            mk++;
        }
        // 相对于1998年12月7(大雪)的月数,900000为正数基数
        value = mk + (int) Math.floor((qiShuo.zhongQi[12] + 390) / 365.2422) * 12 + 900000;
        this.setMonthChronologySum(value);
        // 农历纪月
        this.setMonthChronology(value % 12);
    }


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


    @Override
    public String getInfo() {
        return year + zodiac + "年" + month + (month.length() < 2 ? "月" : "") + day + time;
    }

    @Override
    public String toString() {
        return "LunarDTO{" +
                "monthOffset=" + monthOffset +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", daysOfMonth=" + daysOfMonth +
                ", leapDesc='" + leapMonth + '\'' +
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
