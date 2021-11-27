package cn.huangdayu.almanac.aggregates.lunar;

import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import lombok.Data;

/**
 * 农历，阴历，以 [正月初一] 作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 10:53
 */
@Data
public class Lunar {

    public Lunar(TimeZoneDTO timeZoneDTO, int julianDayOfToday, QiShuo qiShuo) {
        //------------------------------------农历排月序计算------------------------------------//
        // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
        if (julianDayOfToday < qiShuo.zhongQi[0] || julianDayOfToday >= qiShuo.zhongQi[24]) {
            qiShuo.calculateMonth(julianDayOfToday);
        }

        // 农历所在月的序数
        int mk = (julianDayOfToday - qiShuo.heShuo[0]) / 30;
        if (mk < 13 && qiShuo.heShuo[mk + 1] <= julianDayOfToday) {
            mk++;
        }

        // 距农历月首的编移量,0对应初一
        this.setMonthOffset(julianDayOfToday - qiShuo.heShuo[mk]);
        // 农历日名称
        this.setDay(AnnalsUtils.DAY_NAME[julianDayOfToday - qiShuo.heShuo[mk]]);
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
        int julianDay = qiShuo.heShuo[2];
        for (int l = 0; l < 14; l++) {
            // 找春节
            if (!"正".equals(qiShuo.monthNames[l]) || qiShuo.leapMonthIndex == l && l != 0) {
                continue;
            }
            julianDay = qiShuo.heShuo[l];
            if (julianDayOfToday < julianDay) {
                julianDay -= 365;
                // 无需再找下一个正月
                break;
            }
        }
        // 计算该年春节与1984年平均春节(立春附近)相差天数估计
        julianDay = julianDay + 5810;
        // 农历纪年(10进制,1984年起算)
        int lunarYears = (int) Math.floor(julianDay / 365.2422 + 0.5);
        julianDay = lunarYears + 12000;
        // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
        // 黄帝纪年,春节才视为新年
        int kingChronology = lunarYears + 1984 + 2698;
        this.setKingChronology(kingChronology);
        this.setKingChronologyName("开元" + kingChronology + "年");
        // 干支纪年（春节）
        this.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);
        // 该年对应的生肖
        this.setZodiac(AnnalsUtils.SHENGXIAO[julianDay % 12]);
        // 年号
        this.setYearName(AnnalsUtils.getYearName(timeZoneDTO.getEraYear()));
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
