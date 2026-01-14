package cn.huangdayu.almanac.aggregates.holiday;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.dto.InfoDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.FestivalHolidayUtils;

import java.util.LinkedList;

/**
 * @author huangdayu create at 2021/1/21 11:11
 */
public class Holiday extends AbstractAlmanac {

    public Holiday(TimeZoneDTO timeZoneDTO, Lunar lunar, SolarTerm solarTerm, Era era) {
        // 计算农历节日
        this.lunarHolidays = AnnalsUtils.getCalendarHolidays(lunar, solarTerm);
        // 计算节气伏梅
        this.shuJiu = AnnalsUtils.getSolarTermHolidays(solarTerm, era);
        // 计算公历节日
        this.calendarHolidays = FestivalHolidayUtils.getCalendarHolidays(timeZoneDTO);
    }

    public Holiday(String lunarHolidays, boolean holiday) {
        this.lunarHolidays = lunarHolidays;
        this.holiday = holiday;
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
     * 数九日
     */
    private String shuJiu;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private boolean holiday;

    @Override
    public InfoDTO getBaseInfo() {
        return new InfoDTO("节假日", "Holiday", calendarHolidays + " " + lunarHolidays + " " + shuJiu);
    }

    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("公历节日", "calendarHolidays", calendarHolidays));
        list.add(new InfoDTO("农历节日", "lunarHolidays", lunarHolidays));
        list.add(new InfoDTO("数九寒天", "solarTermHolidays", shuJiu));
        list.add(new InfoDTO("是否放假", "holiday", holiday + ""));
        return list;
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

    public String getShuJiu() {
        return shuJiu;
    }

    public void setShuJiu(String shuJiu) {
        this.shuJiu = shuJiu;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }
}
// Expected : | `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   |
// Actual   : | `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   |
