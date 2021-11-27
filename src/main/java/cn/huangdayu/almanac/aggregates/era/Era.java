package cn.huangdayu.almanac.aggregates.era;

import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import lombok.Data;

/**
 * 黄历，天干地支，以 [立春]  作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 11:00
 */
@Data
public class Era {

    public Era(int julianDayForToday, Lunar lunar, TimeZoneDTO timeZoneDTO) {
        int value = lunar.getYearChronology() + 12000;
        // 干支纪年(立春)
        this.setYear(AnnalsUtils.TIANGAN[value % 10] + AnnalsUtils.DIZHI[value % 12]);

        value = lunar.getMonthChronologySum();
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

    public String getInfo() {
        return year + "年" + month + "月" + day + "日" + time + "时";
    }

    @Override
    public String toString() {
        return "EraDTO{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
