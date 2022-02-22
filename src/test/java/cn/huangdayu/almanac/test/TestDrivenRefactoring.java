package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;
import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void tdd() {
        Pack pack = new Pack();
        for (int i = 0; i < timeZoneDTOS.length; i++) {
            TimeZoneDTO timeZoneDTO = timeZoneDTOS[i];
            AlmanacDTO almanacDTO = AlmanacUtils.ofDay(timeZoneDTO);
            pack.toMap(almanacDTO);
        }
        Set<Map.Entry<String, String>> map = pack.getMap().entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : map) {
            Assert.assertEquals(entry.getKey() + entry.getValue(), TEXTS[i]);
            i++;
        }
    }

    @Test
    public void testResult() {
        Pack pack = new Pack();
        for (int i = 0; i < timeZoneDTOS.length; i++) {
            TimeZoneDTO timeZoneDTO = timeZoneDTOS[i];
            AlmanacDTO almanacDTO = AlmanacUtils.ofDay(timeZoneDTO);
            pack.toMap(almanacDTO);
        }
        Assert.assertNotNull(pack.getMap());
        for (Map.Entry<String, String> entry : pack.getMap().entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    private static final String TEXT = " | `西历` | 公元前-211-01-01 11:10:10 星期日 | 1-01-01 11:10:10 星期六 | 1995-08-12 11:10:10 星期六 | 2018-11-01 11:06:48 星期四 | 2021-10-11 11:10:10 星期一 | \n" +
            " | `地点` | 广东 徐闻 | 广东 湛江 | 广东 徐闻 | 广东 徐闻 | 广东 广州 | \n" +
            " | `年号` | [秦]始皇帝 嬴政 始皇10年 | [西汉]平帝 刘衍 元始1年 | [当代]新中国  公历纪元1995年 | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元2021年 | \n" +
            " | `农历` | 戊子鼠年冬月十四午时 | 庚申猴年冬月十八午时 | 乙亥猪年柒月十七午时 | 戊戌狗年玖月廿四午时 | 辛丑牛年玖月初六午时 | \n" +
            " | `黄历` | 戊子年甲子月甲辰日庚午时 | 庚申年戊子月丁丑日丙午时 | 乙亥年甲申月乙亥日壬午时 | 戊戌年壬戌月丁酉日丙午时 | 辛丑年戊戌月壬辰日丙午时 | \n" +
            " | `回历` | -859年11月12日 | -640年5月16日 | 1416年3月15日 | 1440年2月21日 | 1443年3月4日 | \n" +
            " | `儒略日` | 1643991 | 1721424 | 2449942 | 2458424 | 2459499 | \n" +
            " | `黄帝纪元` | 开元2486年 | 开元2698年 | 开元4693年 | 开元4716年 | 开元4719年 | \n" +
            " | `生肖` | 鼠 | 猴 | 猪 | 狗 | 牛 | \n" +
            " | `节日` |  | 『二九』  |  | 『二九』  | 『三九』  | \n" +
            " | `假日` |  |  |  |  |  | \n" +
            " | `其他节日` | 一九第9天  |  | 三九第3天  |  |  | \n" +
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
            " | `月相` | 无 | 无 | 无 | 下弦 2018-11-01 00:40:13 | 无 | \n" +
            " | `月天数` | 30 | 30 | 30 | 30 | 30 | \n" +
            " | `闰月否` | false | false | false | false | false | \n" +
            " | `闰年否` | false | false | true | false | false | \n" +
            " | `星座` | 摩羯座 | 摩羯座 | 狮子座 | 天蝎座 | 天秤座 | \n" +
            " | `当下节气` | null null | null null | null null | null null | null null | \n" +
            " | `最近节气` | 小寒 -211-01-08 08:40:46 | 小寒 1-01-06 20:41:46 | 处暑 1995-08-23 22:34:50 | 立冬 2018-11-07 19:31:38 | 霜降 2021-10-23 12:51:00 | \n" +
            " | `春分` | -211-03-24 21:13:44 | 1-03-23 05:43:48 | 1996-03-20 16:03:04 | 2019-03-21 05:58:20 | 2022-03-20 23:33:14 | \n" +
            " | `夏至` | -211-06-26 22:25:48 | 1-06-25 04:53:31 | 1996-06-21 10:23:44 | 2019-06-21 23:54:09 | 2022-06-21 17:13:40 | \n" +
            " | `秋分` | -211-09-27 05:21:29 | 1-09-25 15:39:49 | 1995-09-23 20:12:59 | 2019-09-23 15:50:02 | 2021-09-23 03:20:54 | \n" +
            " | `冬至` | -212-12-24 14:06:47 | 0-12-23 02:27:09 | 1995-12-22 16:16:47 | 2018-12-22 06:22:38 | 2021-12-21 23:59:08 | \n";

    private static final String[] TEXTS = TEXT.split("\n");

}
