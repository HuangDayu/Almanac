package cn.huangdayu.almanac.aggregates.holiday;

import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.FestivalHolidayUtils;
import lombok.Data;

/**
 * @author huangdayu create at 2021/1/21 11:11
 */
@Data
public class Holiday {

    public Holiday(TimeZoneDTO timeZoneDTO, Lunar lunar, SolarTerm solarTerm, Era era) {
        // 计算农历节日
        AnnalsUtils.getHolidayInfo(this, lunar, solarTerm, era);
        // 计算公历节日
        FestivalHolidayUtils.getDayName(timeZoneDTO, this);
    }

    /**
     * 重要喜庆日子名称(可将日子名称置红)
     */
    private String happyDay;
    /**
     * 重要日子名称
     */
    private String majorDay;
    /**
     * 各种日子名称(连成一大串)
     */
    private String otherDay;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private int flag;

    public String getInfo() {
        return getHappyDay() + " " + getMajorDay() + " " + getOtherDay();
    }

    @Override
    public String toString() {
        return "HolidayDTO{" +
                "happyDay='" + happyDay + '\'' +
                ", majorDay='" + majorDay + '\'' +
                ", otherDay='" + otherDay + '\'' +
                ", flag=" + flag +
                '}';
    }
}
