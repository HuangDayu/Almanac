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

    public static void pakMap(AlmanacDTO almanacDTO1) {
        handler("日期", DateTimeUtils.dateFormat(almanacDTO1.getTimeZoneDTO().getCalendar(), "yyyy-MM-dd HH:mm:ss.SS"));
        handler("星期", almanacDTO1.getTimeZoneDTO().getWeekName());
        handler("地点", almanacDTO1.getTimeZoneDTO().getPosition());
        handler("年号", almanacDTO1.getLunarDTO().getYearName());
        handler("农历", almanacDTO1.getLunarDTO().getInfo());
        handler("时辰", almanacDTO1.getLunarDTO().getTime());
        handler("黄历", almanacDTO1.getEraDTO().getInfo());
        handler("回历", almanacDTO1.getIslamicDTO().getInfo());
        handler("儒略日", String.valueOf(almanacDTO1.getJulianDTO().getDays()));
        handler("黄帝纪年", almanacDTO1.getLunarDTO().getKingChronologyName());
        handler("生肖", almanacDTO1.getLunarDTO().getZodiac());
        handler("节日", almanacDTO1.getHolidayDTO().getMajorDay());
        handler("假日", almanacDTO1.getHolidayDTO().getHappyDay());
        handler("其他节日", almanacDTO1.getHolidayDTO().getOtherDay());
        handler("经度", almanacDTO1.getSunMoonDTO().getLongitude());
        handler("纬度", almanacDTO1.getSunMoonDTO().getLatitude());
        handler("时区", almanacDTO1.getTimeZoneDTO().getTimeZone());
        handler("港口", almanacDTO1.getSunMoonDTO().getPortName());
        handler("昼长", almanacDTO1.getSunMoonDTO().getDiurnalTime());
        handler("夜长", almanacDTO1.getSunMoonDTO().getNightTime());
        handler("天亮", almanacDTO1.getSunMoonDTO().getDawnTime());
        handler("日出", almanacDTO1.getSunMoonDTO().getSunRiseTime());
        handler("中天", almanacDTO1.getSunMoonDTO().getMidDayTime());
        handler("日落", almanacDTO1.getSunMoonDTO().getSunSetTime());
        handler("天黑", almanacDTO1.getSunMoonDTO().getDarkTime());
        handler("月出", almanacDTO1.getSunMoonDTO().getMoonRiseTime());
        handler("月中", almanacDTO1.getSunMoonDTO().getMoonMiddleTime());
        handler("月落", almanacDTO1.getSunMoonDTO().getMoonSetTime());
        handler("月相", almanacDTO1.getSunMoonDTO().getMoonPhaseInfo());
        handler("月天数", String.valueOf(almanacDTO1.getLunarDTO().getDaysOfMonth()));
        handler("闰月否", almanacDTO1.getLunarDTO().getLeapDesc());
        handler("闰年否", String.valueOf(almanacDTO1.getLunarDTO().getLeapYear()));
        handler("星座", almanacDTO1.getJulianDTO().getConstellation());
        handler("当下节气", almanacDTO1.getSolarTermDTO().getName() + " " + almanacDTO1.getSolarTermDTO().getDateTime());
        handler("最近节气", almanacDTO1.getSolarTermDTO().getNextOne().getName() + " " + almanacDTO1.getSolarTermDTO().getNextOne().getDateTime());
        handler("春分", almanacDTO1.getSolarTermDTO().getByName("春分").getDateTime());
        handler("夏至", almanacDTO1.getSolarTermDTO().getByName("夏至").getDateTime());
        handler("秋分", almanacDTO1.getSolarTermDTO().getByName("秋分").getDateTime());
        handler("冬至", almanacDTO1.getSolarTermDTO().getByName("冬至").getDateTime());
    }

    private static void handler(String key, String value) {
        key = " | ".concat(key).concat(" | ");
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
