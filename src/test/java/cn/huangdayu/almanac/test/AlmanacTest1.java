package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

/**
 * @author huangdayu create at 2021/1/22 12:37
 */
public class AlmanacTest1 {
    public static void main(String[] args) {
        TimeZoneDTO timeZoneDTO2 = new TimeZoneDTO("广东省", "徐闻县", "2021-01-29 11:13:29");
        AlmanacDTO almanacDTO = AlmanacUtils.ofDay(timeZoneDTO2);
        System.out.println(almanacDTO.getTimeZoneDTO());
        System.out.println(almanacDTO.getEra());
        System.out.println(almanacDTO.getLunar());
        System.out.println(almanacDTO.getHoliday());
        System.out.println(almanacDTO.getIslamic());
        System.out.println(almanacDTO.getJulian());
        System.out.println(almanacDTO.getSolarTerm());
        for (SolarTerm solarTerm : almanacDTO.getSolarTerm().getNext()) {
            System.out.println(solarTerm);
        }
        System.out.println(almanacDTO.getSunriseMoonset());
        System.out.println(almanacDTO.getSolarTerm().getDesc());

        for (MoonPhase moonPhase : almanacDTO.getMoonPhase().getNext()) {
            System.out.println(moonPhase);
        }
    }
}
