package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.utils.DateTimeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangdayu create at 2021/1/25 10:10
 */
public class TestUtils {

    public static Map<String, String> MAP = new LinkedHashMap<>();

    public static void pakMap(AlmanacDTO almanacDTO) {
        handler("西历", almanacDTO.getTimeZoneDTO().getInfo());
        handler("地点", almanacDTO.getTimeZoneDTO().getPosition());
        handler("年号", almanacDTO.getLunarDTO().getYearName());
        handler("农历", almanacDTO.getLunarDTO().getInfo());
        handler("黄历", almanacDTO.getEraDTO().getInfo());
        handler("回历", almanacDTO.getIslamicDTO().getInfo());
        handler("儒略日", String.valueOf(almanacDTO.getJulianDTO().getDays()));
        handler("黄帝纪年", almanacDTO.getLunarDTO().getKingChronologyName());
        handler("生肖", almanacDTO.getLunarDTO().getZodiac());
        handler("节日", almanacDTO.getHolidayDTO().getMajorDay());
        handler("假日", almanacDTO.getHolidayDTO().getHappyDay());
        handler("其他节日", almanacDTO.getHolidayDTO().getOtherDay());
        handler("经度", almanacDTO.getSunMoonDTO().getLongitude());
        handler("纬度", almanacDTO.getSunMoonDTO().getLatitude());
        handler("时区", almanacDTO.getTimeZoneDTO().getTimeZone());
        handler("港口", almanacDTO.getSunMoonDTO().getPortName());
        handler("昼长", almanacDTO.getSunMoonDTO().getDiurnalTime());
        handler("夜长", almanacDTO.getSunMoonDTO().getNightTime());
        handler("天亮", almanacDTO.getSunMoonDTO().getDawnTime());
        handler("日出", almanacDTO.getSunMoonDTO().getSunRiseTime());
        handler("中天", almanacDTO.getSunMoonDTO().getMidDayTime());
        handler("日落", almanacDTO.getSunMoonDTO().getSunSetTime());
        handler("天黑", almanacDTO.getSunMoonDTO().getDarkTime());
        handler("月出", almanacDTO.getSunMoonDTO().getMoonRiseTime());
        handler("月中", almanacDTO.getSunMoonDTO().getMoonMiddleTime());
        handler("月落", almanacDTO.getSunMoonDTO().getMoonSetTime());
        handler("月相", almanacDTO.getMoonPhaseDTO().getInfo());
        handler("月天数", String.valueOf(almanacDTO.getLunarDTO().getDaysOfMonth()));
        handler("闰月否", String.valueOf(almanacDTO.getLunarDTO().getLeapMonth()));
        handler("闰年否", String.valueOf(almanacDTO.getLunarDTO().getLeapYear()));
        handler("星座", almanacDTO.getJulianDTO().getConstellation());
        handler("当下节气", almanacDTO.getSolarTermDTO().getName() + " " + almanacDTO.getSolarTermDTO().getDateTime());
        handler("最近节气", almanacDTO.getSolarTermDTO().getNextOne().getName() + " " + almanacDTO.getSolarTermDTO().getNextOne().getDateTime());
        handler("春分", almanacDTO.getSolarTermDTO().getByName("春分").getDateTime());
        handler("夏至", almanacDTO.getSolarTermDTO().getByName("夏至").getDateTime());
        handler("秋分", almanacDTO.getSolarTermDTO().getByName("秋分").getDateTime());
        handler("冬至", almanacDTO.getSolarTermDTO().getByName("冬至").getDateTime());
    }

    private static void handler(String key, String value) {
        key = " | `".concat(key).concat("` | ");
        if (value == null) {
            value = String.valueOf(value);
        }
        if (MAP.containsKey(key)) {
            MAP.put(key, MAP.get(key).concat(value).concat(" | "));
        } else {
            MAP.put(key, value.concat(" | "));
        }
    }

}
