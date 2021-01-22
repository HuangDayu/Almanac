package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.SolarTermDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

/**
 * @author huangdayu create at 2021/1/22 12:37
 */
public class AlmanacTest1 {
    public static void main(String[] args) {
        TimeZoneDTO timeZoneDTO2 = new TimeZoneDTO("广东省徐闻县", "2021-01-29 11:13:29");
        AlmanacDTO almanacDTO = AlmanacUtils.dayCalendar(timeZoneDTO2);
        System.out.println(almanacDTO.getTimeZoneDTO());
        System.out.println(almanacDTO.getEraDTO());
        System.out.println(almanacDTO.getLunarDTO());
        System.out.println(almanacDTO.getGregorianDTO());
        System.out.println(almanacDTO.getHolidayDTO());
        System.out.println(almanacDTO.getIslamicDTO());
        System.out.println(almanacDTO.getJulianDTO());
        System.out.println(almanacDTO.getSolarTermDTO());
        for (SolarTermDTO solarTermDTO : almanacDTO.getSolarTermDTO().getNext()) {
            System.out.println(solarTermDTO);
        }
        System.out.println(almanacDTO.getSunMoonDTO());
        System.out.println(almanacDTO.getSolarTermDTO().getDesc());

    }
}
