package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.AlmanacService;
import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.CoordinatesDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 测试驱动重构测试用例
 */
public class TestDrivenRefactoring {

    TimeZoneDTO[] timeZoneDTOS = {
            new TimeZoneDTO("广东省", "徐闻县", "-211-1-1 11:10:10"),
            new TimeZoneDTO("广东省", "湛江市", "1-1-1 11:10:10"),
            new TimeZoneDTO("广东省", "徐闻县", "1995-08-12 11:10:10"),
            new TimeZoneDTO("广东省", "徐闻县", "2018-11-01 11:06:48"),
            new TimeZoneDTO("广东省", "广州市", "2021-10-11 11:10:10"),
    };

    TimeZoneDTO[] timeZoneDTOS2 = {
            new TimeZoneDTO(new CoordinatesDTO("广东省", "徐闻县", 110.1666667d, 20.3333332d), "-211-1-1 11:10:10"),
            new TimeZoneDTO(new CoordinatesDTO("广东省", "湛江市", 110.3500000d, 21.2666667d), "1-1-1 11:10:10"),
            new TimeZoneDTO(new CoordinatesDTO("广东省", "徐闻县", 110.1666667d, 20.3333332d), "1995-08-12 11:10:10"),
            new TimeZoneDTO(new CoordinatesDTO("广东省", "徐闻县", 110.1666667d, 20.3333332d), "2018-11-01 11:06:48"),
            new TimeZoneDTO(new CoordinatesDTO("广东省", "广州市", 113.2666667d, 23.1333332d), "2021-10-11 11:10:10"),
    };

    @Test
    public void tdd() {
        test(timeZoneDTOS);
        test(timeZoneDTOS2);
    }

    public void test(TimeZoneDTO[] timeZoneDTOS) {
        Pack pack = new Pack();
        for (int i = 0; i < timeZoneDTOS.length; i++) {
            AlmanacService almanacService = new AlmanacService(timeZoneDTOS[i]);
            AlmanacDTO almanacDTO = almanacService.dayCalendar();
            for (Map.Entry<String, String> entry : almanacDTO.toMap().entrySet()) {
                pack.handler(entry.getKey(), entry.getValue());
            }
        }
        Set<Map.Entry<String, String>> map = pack.getMap().entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : map) {
            String[] s1 = (entry.getKey() + entry.getValue()).split("\\|");
            String[] s2 = TEXTS[i].split("\\|");
            for (int j = 0; j < s1.length; j++) {
                Assert.assertEquals(s1[j].trim(), s2[j].trim());
            }
            i++;
        }
        System.out.println("Test driven refactoring pass !");
    }

    @Test
    public void testResult() {
        Pack pack = new Pack();
        for (int i = 0; i < timeZoneDTOS.length; i++) {
            TimeZoneDTO timeZoneDTO = timeZoneDTOS[i];
            AlmanacDTO almanacDTO = AlmanacUtils.ofDay(timeZoneDTO);
            for (Map.Entry<String, String> entry : almanacDTO.toMap().entrySet()) {
                pack.handler(entry.getKey(), entry.getValue());
            }
        }
        Assert.assertNotNull(pack.getMap());
        for (Map.Entry<String, String> entry : pack.getMap().entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    private static final String TEXT = " | `地点` | 广东 徐闻 | 广东 湛江 | 广东 徐闻 | 广东 徐闻 | 广东 广州 | \n" +
            " | `西历` | 公元前-211-01-01 11:10:10 星期日 | 1-01-01 11:10:10 星期六 | 1995-08-12 11:10:10 星期六 | 2018-11-01 11:06:48 星期四 | 2021-10-11 11:10:10 星期一 | \n" +
            " | `年号` | [秦]始皇帝 嬴政 始皇10年 | [西汉]平帝 刘衍 元始1年 | [当代]新中国  公历纪元1995年 | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元2021年 | \n" +
            " | `黄帝纪元` | 开元2486年 | 开元2698年 | 开元4693年 | 开元4716年 | 开元4719年 | \n" +
            " | `农历` | 戊子鼠年冬月十四午时 | 庚申猴年冬月十八午时 | 乙亥猪年柒月十七午时 | 戊戌狗年玖月廿四午时 | 辛丑牛年玖月初六午时 | \n" +
            " | `黄历` | 戊子年甲子月甲辰日庚午时 | 庚申年戊子月丁丑日丙午时 | 乙亥年甲申月乙亥日壬午时 | 戊戌年壬戌月丁酉日丙午时 | 辛丑年戊戌月壬辰日丙午时 | \n" +
            " | `节气` | 小寒 -211-01-08 08:40:46 至今7天 | 小寒 1-01-06 20:41:46 至今5天 | 处暑 1995-08-23 22:34:50 至今11天 | 立冬 2018-11-07 19:31:38 至今6天 | 霜降 2021-10-23 12:51:00 至今12天 | \n" +
            " | `月相` | 望月 -211-01-02 19:41:01 至今1天 | 下弦 1-01-05 16:12:25 至今4天 | 下弦 1995-08-18 11:03:53 至今6天 | 下弦 2018-11-01 00:40:13 今天 | 上弦 2021-10-13 11:24:58 至今2天 | \n" +
            " | `儒略历` | 1643991 摩羯座 | 1721424 摩羯座 | 2449942 狮子座 | 2458424 天蝎座 | 2459499 天秤座 | \n" +
            " | `回历` | -859年11月12日 | -640年5月16日 | 1416年3月15日 | 1440年2月21日 | 1443年3月4日 | \n" +
            " | `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   | \n" +
            " | `经度` | 东经 110°16'67\" | 东经 110°35'00\" | 东经 110°16'67\" | 东经 110°16'67\" | 东经 113°26'67\" | \n" +
            " | `纬度` | 北纬 20°33'33\" | 北纬 21°26'67\" | 北纬 20°33'33\" | 北纬 20°33'33\" | 北纬 23°13'33\" | \n" +
            " | `时区` | +0800 东八区 | +0800 东八区 | +0800 东八区 | +0800 东八区 | +0800 东八区 | \n" +
            " | `港口` | 乌石港   | 水东港   | 乌石港   | 乌石港   | 黄埔港   | \n" +
            " | `昼长` | 10:55:10 | 10:51:11 | 12:52:48 | 11:22:55 | 11:43:15 | \n" +
            " | `夜长` | 13:04:49 | 13:08:48 | 11:07:11 | 12:37:04 | 12:16:44 | \n" +
            " | `天亮` | 06:50:48 | 06:51:15 | 05:55:05 | 06:18:41 | 05:59:31 | \n" +
            " | `日出` | 07:14:48 | 07:15:27 | 06:18:10 | 06:41:27 | 06:22:07 | \n" +
            " | `中天` | 12:42:24 | 12:41:03 | 12:44:34 | 12:22:54 | 12:13:45 | \n" +
            " | `日落` | 18:09:59 | 18:06:39 | 19:10:58 | 18:04:22 | 18:05:23 | \n" +
            " | `天黑` | 18:34:00 | 18:30:51 | 19:34:03 | 18:27:07 | 18:27:59 | \n" +
            " | `月出` | 14:33:19 | 03:35:51 | 20:13:13 | 00:13:02 | 11:17:07 | \n" +
            " | `月中` | 08:20:58 | 09:29:29 | 13:52:54 | 06:55:44 | 16:43:08 | \n" +
            " | `月落` | 02:08:38 | 15:23:06 | 07:32:35 | 13:38:27 | 22:09:10 | \n" +
            " | `月期` | 30 | 30 | 30 | 30 | 30 | \n" +
            " | `闰月` | false | false | false | false | false | \n" +
            " | `闰年` | false | false | true | false | false | \n";

    private static final String[] TEXTS = TEXT.split("\n");

    public static class Pack {

        private Map<String, String> MAP = new LinkedHashMap<>();

        public void toMap(AlmanacDTO almanacDTO) {
            handler("西历", almanacDTO.getTimeZoneDTO().getInfo());
            handler("地点", almanacDTO.getTimeZoneDTO().getCoordinates().getPosition());
            handler("年号", almanacDTO.getLunar().getYearName());
            handler("农历", almanacDTO.getLunar().getInfo());
            handler("黄历", almanacDTO.getEra().getInfo());
            handler("回历", almanacDTO.getIslamic().getInfo());
            handler("儒略日", String.valueOf(almanacDTO.getJulian().getDays()));
            handler("黄帝纪元", almanacDTO.getLunar().getKingChronologyName());
            handler("生肖", almanacDTO.getLunar().getZodiac());
            handler("节日", almanacDTO.getHoliday().getLunarHolidays());
            handler("假日", almanacDTO.getHoliday().getCalendarHolidays());
            handler("其他节日", almanacDTO.getHoliday().getSolarTermHolidays());
            handler("经度", almanacDTO.getTimeZoneDTO().getCoordinates().getLongitudeStr());
            handler("纬度", almanacDTO.getTimeZoneDTO().getCoordinates().getLatitudeStr());
            handler("时区", almanacDTO.getTimeZoneDTO().getTimeZone());
            handler("港口", almanacDTO.getTimeZoneDTO().getCoordinates().getPortName());
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
}
