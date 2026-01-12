package cn.huangdayu.almanac.aggregates.holiday;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.FestivalHolidayUtils;

/**
 * @author huangdayu create at 2021/1/21 11:11
 */
public class Holiday extends AbstractAlmanac {

    public Holiday(TimeZoneDTO timeZoneDTO, Lunar lunar, SolarTerm solarTerm, Era era) {
        // 计算农历节日
        this.lunarHolidays = AnnalsUtils.getCalendarHolidays(lunar, solarTerm);
        // 计算节气伏梅
        this.solarTermHolidays = AnnalsUtils.getSolarTermHolidays(solarTerm, era);
        // 计算公历节日
        this.calendarHolidays = FestivalHolidayUtils.getCalendarHolidays(timeZoneDTO);
    }

    public Holiday(String lunarHolidays, int flag) {
        this.lunarHolidays = lunarHolidays;
        this.flag = flag;
    }

    /**
     * 西历节日
     */
    private String calendarHolidays;
    /**
     * 农历节日
     */
    private String lunarHolidays;
    /**
     * 节气
     */
    private String solarTermHolidays;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private int flag;

    @Override
    public String getInfo() {
        return calendarHolidays + " " + lunarHolidays + " " + solarTermHolidays;
    }

    public String getCalendarHolidays() {
        return calendarHolidays;
    }

    public void setCalendarHolidays(String calendarHolidays) {
        this.calendarHolidays = calendarHolidays;
    }

    public String getLunarHolidays() {
        return lunarHolidays;
    }

    public void setLunarHolidays(String lunarHolidays) {
        this.lunarHolidays = lunarHolidays;
    }

    public String getSolarTermHolidays() {
        return solarTermHolidays;
    }

    public void setSolarTermHolidays(String solarTermHolidays) {
        this.solarTermHolidays = solarTermHolidays;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
// Expected : | `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   |
// Actual   : | `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   |
