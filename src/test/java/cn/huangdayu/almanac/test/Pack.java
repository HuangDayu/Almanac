package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.utils.DateTimeUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangdayu create at 2021/1/25 10:10
 */
public class Pack {

    private Map<String, String> MAP = new LinkedHashMap<>();

    public void toMap(AlmanacDTO almanacDTO) {
        handler("西历", almanacDTO.getTimeZoneDTO().getInfo());
        handler("地点", almanacDTO.getTimeZoneDTO().getPosition());
        handler("年号", almanacDTO.getLunar().getYearName());
        handler("农历", almanacDTO.getLunar().getInfo());
        handler("黄历", almanacDTO.getEra().getInfo());
        handler("回历", almanacDTO.getIslamic().getInfo());
        handler("儒略日", String.valueOf(almanacDTO.getJulian().getDays()));
        handler("黄帝纪元", almanacDTO.getLunar().getKingChronologyName());
        handler("生肖", almanacDTO.getLunar().getZodiac());
        handler("节日", almanacDTO.getHoliday().getMajorDay());
        handler("假日", almanacDTO.getHoliday().getHappyDay());
        handler("其他节日", almanacDTO.getHoliday().getOtherDay());
        handler("经度", almanacDTO.getSunriseMoonset().getLongitude());
        handler("纬度", almanacDTO.getSunriseMoonset().getLatitude());
        handler("时区", almanacDTO.getTimeZoneDTO().getTimeZone());
        handler("港口", almanacDTO.getSunriseMoonset().getPortName());
        handler("昼长", almanacDTO.getSunriseMoonset().getDiurnalTime());
        handler("夜长", almanacDTO.getSunriseMoonset().getNightTime());
        handler("天亮", almanacDTO.getSunriseMoonset().getDawnTime());
        handler("日出", almanacDTO.getSunriseMoonset().getSunRiseTime());
        handler("中天", almanacDTO.getSunriseMoonset().getMidDayTime());
        handler("日落", almanacDTO.getSunriseMoonset().getSunSetTime());
        handler("天黑", almanacDTO.getSunriseMoonset().getDarkTime());
        handler("月出", almanacDTO.getSunriseMoonset().getMoonRiseTime());
        handler("月中", almanacDTO.getSunriseMoonset().getMoonMiddleTime());
        handler("月落", almanacDTO.getSunriseMoonset().getMoonSetTime());
        handler("月相", almanacDTO.getMoonPhase().getInfo());
        handler("月天数", String.valueOf(almanacDTO.getLunar().getDaysOfMonth()));
        handler("闰月否", String.valueOf(almanacDTO.getLunar().getLeapMonth()));
        handler("闰年否", String.valueOf(almanacDTO.getLunar().getLeapYear()));
        handler("星座", almanacDTO.getJulian().getConstellation());
        handler("节气", almanacDTO.getSolarTerm().getInfo());
        handler("春分", almanacDTO.getSolarTerm().getByName("春分").getDateTime());
        handler("夏至", almanacDTO.getSolarTerm().getByName("夏至").getDateTime());
        handler("秋分", almanacDTO.getSolarTerm().getByName("秋分").getDateTime());
        handler("冬至", almanacDTO.getSolarTerm().getByName("冬至").getDateTime());
    }

    public void handler(String key, String value) {
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

    public Map<String, String> getMap() {
        return MAP;
    }
}
