package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.holiday.Holiday;
import cn.huangdayu.almanac.aggregates.islamic.Islamic;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
@Builder
@Data
public class AlmanacDTO {

    private Lunar lunar;
    private Era era;
    private Holiday holiday;
    private Islamic islamic;
    private Julian julian;
    private SolarTerm solarTerm;
    private SunriseMoonset sunriseMoonset;
    private TimeZoneDTO timeZoneDTO;
    private MoonPhase moonPhase;

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("地点", timeZoneDTO.getPosition());
        map.put("西历", timeZoneDTO.getInfo());
        map.put("年号", lunar.getYearName());
        map.put("黄帝纪元", lunar.getKingChronologyName());
        map.put("农历", lunar.getInfo());
        map.put("黄历", era.getInfo());
        map.put("节气", solarTerm.getDetails());
        map.put("月相", moonPhase.getDetails());
        map.put("儒略历", julian.getInfo());
        map.put("回历", islamic.getInfo());
        map.put("节假日", holiday.getInfo());
        map.put("经度", timeZoneDTO.getLongitude());
        map.put("纬度", timeZoneDTO.getLatitude());
        map.put("时区", timeZoneDTO.getTimeZone());
        map.put("港口", timeZoneDTO.getPortName());
        map.put("昼长", sunriseMoonset.getDiurnalTime());
        map.put("夜长", sunriseMoonset.getNightTime());
        map.put("天亮", sunriseMoonset.getDawnTime());
        map.put("日出", sunriseMoonset.getSunRiseTime());
        map.put("中天", sunriseMoonset.getMidDayTime());
        map.put("日落", sunriseMoonset.getSunSetTime());
        map.put("天黑", sunriseMoonset.getDarkTime());
        map.put("月出", sunriseMoonset.getMoonRiseTime());
        map.put("月中", sunriseMoonset.getMoonMiddleTime());
        map.put("月落", sunriseMoonset.getMoonSetTime());
        map.put("月期", String.valueOf(lunar.getDaysOfMonth()));
        map.put("闰月", String.valueOf(lunar.getLeapMonth()));
        map.put("闰年", String.valueOf(lunar.getLeapYear()));
        return map;
    }
}
