package cn.huangdayu.almanac.test;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

public class ReadMe {

    public static void main(String[] args) throws Exception {

        TimeZoneDTO timeZoneDTO1 = new TimeZoneDTO("广东省徐闻县", Calendar.getInstance());

        TimeZoneDTO timeZoneDTO2 = new TimeZoneDTO("广东省徐闻县", "1995-08-12 11:10:10");

        TimeZoneDTO timeZoneDTO3 = new TimeZoneDTO("广东省徐闻县", "1-1-1 11:10:10");

        pakMap(AlmanacUtils.dayCalendar(timeZoneDTO1), AlmanacUtils.dayCalendar(timeZoneDTO2), AlmanacUtils.dayCalendar(timeZoneDTO3)).forEach((K, V) -> {
            System.out.println("| `" + K + "`" + V + " |");
        });
    }

    public static Map<String, String> pakMap(AlmanacDTO almanacDTO1, AlmanacDTO almanacDTO2, AlmanacDTO almanacDTO3) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("日期", " | " + almanacDTO1.getDate() + " | " + almanacDTO2.getDate() + " | " + almanacDTO3.getDate());
        map.put("时间", " | " + almanacDTO1.getTime() + " | " + almanacDTO2.getTime() + " | " + almanacDTO3.getTime());
        map.put("星期", " | " + almanacDTO1.getWeek() + " | " + almanacDTO2.getWeek() + " | " + almanacDTO3.getWeek());
        map.put("地点", " | " + almanacDTO1.getPosition() + " | " + almanacDTO2.getPosition() + " | " + almanacDTO3.getPosition());
        map.put("年号", " | " + almanacDTO1.getYearNumber() + " | " + almanacDTO2.getYearNumber() + " | " + almanacDTO3.getYearNumber());
        map.put("农历", " | " + almanacDTO1.getLunar() + " | " + almanacDTO2.getLunar() + " | " + almanacDTO3.getLunar());
        map.put("时辰", " | " + almanacDTO1.getLunarTime() + " | " + almanacDTO2.getLunarTime() + " | " + almanacDTO3.getLunarTime());
        map.put("黄历", " | " + almanacDTO1.getHuangLi() + " | " + almanacDTO2.getHuangLi() + " | " + almanacDTO3.getHuangLi());
        map.put("天干", " | " + almanacDTO1.getTianGan() + " | " + almanacDTO2.getTianGan() + " | " + almanacDTO3.getTianGan());
        map.put("地支", " | " + almanacDTO1.getDiZhi() + " | " + almanacDTO2.getDiZhi() + " | " + almanacDTO3.getDiZhi());
        map.put("八字", " | " + almanacDTO1.getBaZi() + " | " + almanacDTO2.getBaZi() + " | " + almanacDTO3.getBaZi());
        map.put("回历", " | " + almanacDTO1.getIslamic() + " | " + almanacDTO2.getIslamic() + " | " + almanacDTO3.getIslamic());
        map.put("儒略日", " | " + almanacDTO1.getJulianDay() + " | " + almanacDTO2.getJulianDay() + " | " + almanacDTO3.getJulianDay());
        map.put("黄帝纪年", " | " + almanacDTO1.getChronology() + " | " + almanacDTO2.getChronology() + " | " + almanacDTO3.getChronology());
        map.put("生肖", " | " + almanacDTO1.getZodiac() + " | " + almanacDTO2.getZodiac() + " | " + almanacDTO3.getZodiac());
        map.put("节假日", " | " + almanacDTO1.getHolidayVacations() + " | " + almanacDTO2.getHolidayVacations() + " | " + almanacDTO3.getHolidayVacations());
        map.put("经度", " | " + almanacDTO1.getLongitude() + " | " + almanacDTO2.getLongitude() + " | " + almanacDTO3.getLongitude());
        map.put("纬度", " | " + almanacDTO1.getLatitude() + " | " + almanacDTO2.getLatitude() + " | " + almanacDTO3.getLatitude());
        map.put("时区", " | " + almanacDTO1.getTimeZone() + " | " + almanacDTO2.getTimeZone() + " | " + almanacDTO3.getTimeZone());
        map.put("港口", " | " + almanacDTO1.getPortName() + " | " + almanacDTO2.getPortName() + " | " + almanacDTO3.getPortName());
        map.put("昼长", " | " + almanacDTO1.getDiurnalTime() + " | " + almanacDTO2.getDiurnalTime() + " | " + almanacDTO3.getDiurnalTime());
        map.put("夜长", " | " + almanacDTO1.getNightTime() + " | " + almanacDTO2.getNightTime() + " | " + almanacDTO3.getNightTime());
        map.put("天亮", " | " + almanacDTO1.getDawnTime() + " | " + almanacDTO2.getDawnTime() + " | " + almanacDTO3.getDawnTime());
        map.put("日出", " | " + almanacDTO1.getSunRiseTime() + " | " + almanacDTO2.getSunRiseTime() + " | " + almanacDTO3.getSunRiseTime());
        map.put("中天", " | " + almanacDTO1.getMidDayTime() + " | " + almanacDTO2.getMidDayTime() + " | " + almanacDTO3.getMidDayTime());
        map.put("日落", " | " + almanacDTO1.getSunSetTime() + " | " + almanacDTO2.getSunSetTime() + " | " + almanacDTO3.getSunSetTime());
        map.put("天黑", " | " + almanacDTO1.getDarkTime() + " | " + almanacDTO2.getDarkTime() + " | " + almanacDTO3.getDarkTime());
        map.put("月出", " | " + almanacDTO1.getMoonRiseTime() + " | " + almanacDTO2.getMoonRiseTime() + " | " + almanacDTO3.getMoonRiseTime());
        map.put("月中", " | " + almanacDTO1.getMoonMiddleTime() + " | " + almanacDTO2.getMoonMiddleTime() + " | " + almanacDTO3.getMoonMiddleTime());
        map.put("月落", " | " + almanacDTO1.getMoonSetTime() + " | " + almanacDTO2.getMoonSetTime() + " | " + almanacDTO3.getMoonSetTime());
        map.put("月相", " | " + almanacDTO1.getMoonPhase() + " | " + almanacDTO2.getMoonPhase() + " | " + almanacDTO3.getMoonPhase());
        map.put("月天数", " | " + almanacDTO1.getLunarDays() + " | " + almanacDTO2.getLunarDays() + " | " + almanacDTO3.getLunarDays());
        map.put("大月否", " | " + almanacDTO1.isLunarBigMonth() + " | " + almanacDTO2.isLunarBigMonth() + " | " + almanacDTO3.isLunarBigMonth());
        map.put("闰月否", " | " + almanacDTO1.isLeapMonth() + " | " + almanacDTO2.isLeapMonth() + " | " + almanacDTO3.isLeapMonth());
        map.put("闰年否", " | " + almanacDTO1.isLeapYear() + " | " + almanacDTO2.isLeapYear() + " | " + almanacDTO3.isLeapYear());
        map.put("星座", " | " + almanacDTO1.getConstellation() + " | " + almanacDTO2.getConstellation() + " | " + almanacDTO3.getConstellation());
        map.put("下一节气", " | " + almanacDTO1.getNextSolarTerm() + " | " + almanacDTO2.getNextSolarTerm() + " | " + almanacDTO3.getNextSolarTerm());
        map.put("春分", " | " + almanacDTO1.getSolarTerm("春分") + " | " + almanacDTO2.getSolarTerm("春分") + " | " + almanacDTO3.getSolarTerm("春分"));
        map.put("夏至", " | " + almanacDTO1.getSolarTerm("夏至") + " | " + almanacDTO2.getSolarTerm("夏至") + " | " + almanacDTO3.getSolarTerm("夏至"));
        map.put("秋分", " | " + almanacDTO1.getSolarTerm("秋分") + " | " + almanacDTO2.getSolarTerm("秋分") + " | " + almanacDTO3.getSolarTerm("秋分"));
        map.put("冬至", " | " + almanacDTO1.getSolarTerm("冬至") + " | " + almanacDTO2.getSolarTerm("冬至") + " | " + almanacDTO3.getSolarTerm("冬至"));
        return map;
    }

}
