# Almanac

[![GitHub watch](https://img.shields.io/github/watchers/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/watchers)
[![GitHub issues](https://img.shields.io/github/issues/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/issues)
[![GitHub forks](https://img.shields.io/github/forks/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/network)
[![GitHub stars](https://img.shields.io/github/stars/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/stargazers)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)
[![Badge](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu/#/zh_CN)

<!-- [![GitHub license](https://img.shields.io/github/license/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac) -->

**[历](https://almanac.huangdayu.cn/)**：包括万年历、皇历、日出月落、农历、黄历、干支，节气、月相，经纬度、节假日、伊斯兰历、儒略历、西历等。


## 引入依赖

```xml

<dependency>
    <groupId>cn.huangdayu</groupId>
    <artifactId>almanac</artifactId>
    <version>[1+,)</version>
</dependency>
```

## 使用示例

```java

/**
 * 日历
 *
 * @param timeZoneDTO
 * @return
 */
AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县",Calendar.getInstance()));

/**
 * 月历
 *
 * @param timeZoneDTO
 * @return
 */
AlmanacUtils.ofMonth(new TimeZoneDTO("广东省","徐闻县",Calendar.getInstance()));

/**
 * 集成抽象类的用法
 */
public class AlmanacApp extends Almanac {

    public static void main(String[] args) {
        Almanac almanac = new AlmanacApp();
        AlmanacDTO almanacDTO = almanac.ofYear()[7][11];
        TestUtils.pakMap(almanacDTO);
        for (Map.Entry<String, String> entry : TestUtils.MAP.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    @Override
    public TimeZoneDTO initTimeZone() {
        return new TimeZoneDTO("广东省","徐闻县", "2021-01-29 11:13:29");
    }
}
```

## 说明

**本项目类似[寿星天文历](http://www.nongli.net/sxwnl/)和[日梭万年历](https://www.nongli114.com/rili/)，但是数据还不是很全，算法有待校验！**

## 特别感谢

**本项目部分算法和数据来源于网络，如有侵权，联系删除！**

- [wangpeng047](http://blog.csdn.net/wangpeng047/article/details/38559591)
- [lxslove](http://blog.csdn.net/lxslove/article/details/6083396)
- [moodlxs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)
- [hanoi](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)

## BUG & TODO

- 天文历信息不足：方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等；
- 缺少五行计算；
- 缺少[古六历](https://baike.baidu.com/item/%E5%8F%A4%E5%85%AD%E5%8E%86/5458291);
- 传统节日和现代节日的计算没有分离；

## 展示

| key | value | value | value |value | value |
| :--------: | :-------- | :-------- | :-------- |:-------- | :-------- |
| `地点` | 广东 徐闻 | 广东 湛江 | 广东 徐闻 | 广东 徐闻 | 广东 广州 | 
| `西历` | 公元前-211-01-01 11:10:10 星期日 | 1-01-01 11:10:10 星期六 | 1995-08-12 11:10:10 星期六 | 2018-11-01 11:06:48 星期四 | 2021-10-11 11:10:10 星期一 | 
| `年号` | [秦]始皇帝 嬴政 始皇10年 | [西汉]平帝 刘衍 元始1年 | [当代]新中国  公历纪元1995年 | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元2021年 | 
| `黄帝纪元` | 开元2486年 | 开元2698年 | 开元4693年 | 开元4716年 | 开元4719年 | 
| `农历` | 戊子鼠年冬月十四午时 | 庚申猴年冬月十八午时 | 乙亥猪年柒月十七午时 | 戊戌狗年玖月廿四午时 | 辛丑牛年玖月初六午时 | 
| `黄历` | 戊子年甲子月甲辰日庚午时 | 庚申年戊子月丁丑日丙午时 | 乙亥年甲申月乙亥日壬午时 | 戊戌年壬戌月丁酉日丙午时 | 辛丑年戊戌月壬辰日丙午时 | 
| `节气` | 小寒 -211-01-08 08:40:46 至今7天 | 小寒 1-01-06 20:41:46 至今5天 | 处暑 1995-08-23 22:34:50 至今11天 | 立冬 2018-11-07 19:31:38 至今6天 | 霜降 2021-10-23 12:51:00 至今12天 | 
| `月相` | 无 | 无 | 无 | 下弦 2018-11-01 00:40:13 | 无 | 
| `儒略历` | 1643991 摩羯座 | 1721424 摩羯座 | 2449942 狮子座 | 2458424 天蝎座 | 2459499 天秤座 | 
| `回历` | -859年11月12日 | -640年5月16日 | 1416年3月15日 | 1440年2月21日 | 1443年3月4日 | 
| `节假日` |   一九第9天  |  『二九』   |   三九第3天  |  『二九』   |  『三九』   | 
| `经度` | 东经 110°16'67" | 东经 110°35'00" | 东经 110°16'67" | 东经 110°16'67" | 东经 113°26'67" | 
| `纬度` | 北纬 20°33'33" | 北纬 21°26'67" | 北纬 20°33'33" | 北纬 20°33'33" | 北纬 23°13'33" | 
| `时区` | +0800 东八区 | +0800 东八区 | +0800 东八区 | +0800 东八区 | +0800 东八区 | 
| `港口` | 乌石港   | 水东港   | 乌石港   | 乌石港   | 黄埔港   | 
| `昼长` | 10:55:10 | 10:51:11 | 12:52:48 | 11:22:55 | 11:43:15 | 
| `夜长` | 13:04:49 | 13:08:48 | 11:07:11 | 12:37:04 | 12:16:44 | 
| `天亮` | 06:50:48 | 06:51:15 | 05:55:05 | 06:18:41 | 05:59:31 | 
| `日出` | 07:14:48 | 07:15:27 | 06:18:10 | 06:41:27 | 06:22:07 | 
| `中天` | 12:42:24 | 12:41:03 | 12:44:34 | 12:22:54 | 12:13:45 | 
| `日落` | 18:09:59 | 18:06:39 | 19:10:58 | 18:04:22 | 18:05:23 | 
| `天黑` | 18:34:00 | 18:30:51 | 19:34:03 | 18:27:07 | 18:27:59 | 
| `月出` | 14:33:19 | 03:35:51 | 20:13:13 | 00:13:02 | 11:17:07 | 
| `月中` | 08:20:58 | 09:29:29 | 13:52:54 | 06:55:44 | 16:43:08 | 
| `月落` | 02:08:38 | 15:23:06 | 07:32:35 | 13:38:27 | 22:09:10 | 
| `月期` | 30 | 30 | 30 | 30 | 30 | 
| `闰月` | false | false | false | false | false | 
| `闰年` | false | false | true | false | false |

## Almanac APP

### 演示

<div align="center">
<img src="https://almanac.huangdayu.cn/doc/image/ezgif.com-video-to-gif-1.gif" height="450px" width="250px" alt="设置日期" >
<img src="https://almanac.huangdayu.cn/doc/image/ezgif.com-video-to-gif-2.gif" height="450px" width="250px" alt="设置时间" >
<img src="https://almanac.huangdayu.cn/doc/image/ezgif.com-video-to-gif-3.gif" height="450px" width="250px" alt="设置位置" >
</div>

### 下载

- [点击下载](https://almanac.huangdayu.cn/doc/app/AlmanacApp-0.0.3.apk)

### 源码

- [AlmanacAPP](https://github.com/HuangDayu/AlmanacApp)

## 历法说明

- 西历 公历 阳历
- 农历 阴阳历 黄历
- 伊斯兰历 回历 阴历
- 儒略历 格里历
- 天文历
- [黄帝纪年](https://baike.baidu.com/item/%E9%BB%84%E5%B8%9D%E7%BA%AA%E5%B9%B4) [黄帝历](https://baike.baidu.com/item/%E9%BB%84%E5%B8%9D%E5%8E%86) [古六历](https://baike.baidu.com/item/%E5%8F%A4%E5%85%AD%E5%8E%86)
- [寿星天文历](http://www.nongli.net/sxwnl/)
- [日梭万年历](http://www.nongli114.com/rili/2017.html)
- [紫金天文台](http://www.pmo.ac.cn/)
- [中华农历网](http://www.nongli.net/)
- [时间科普](http://www.time.ac.cn/serve/down.htm)
- [中国科学院国家授时中心](http://www.ntsc.ac.cn/)
- [中国科学院紫金天文台](http://almanac.pmo.ac.cn/)
- [电子天文历表](http://almanac.pmo.ac.cn/dianzili.htm)
- [天之文](http://www.astron.ac.cn/index.htm)

## 参考文献

- [寿星天文历Java封装整理版](https://blog.csdn.net/wangpeng047/article/details/38559591)
- [寿星万年历---java算法实现-csdn](https://blog.csdn.net/lxslove/article/details/6083396)
- [寿星万年历---java算法实现-cnblogs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)
- [根据经纬度计算日出、日落、中天、天亮、天黑和昼长时间](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)  
- [日历相关的算法](https://fancyerii.github.io/2019/03/27/calendar/)

## 在线工具

- [儒略日数和日干支计算器](https://ytliu0.github.io/ChineseCalendar/Julian_simp.html)

## 相关开源

- [time4j](http://time4j.net/)
- [icu4j](https://mvnrepository.com/artifact/com.ibm.icu/icu4j)
- [lunar-java](https://github.com/6tail/lunar-java)

## 回馈开源

![](https://www.huangdayu.cn/assets/private/images/image-100.jpg "您的支持是我持续维护的动力源泉")

## License

```license
Copyright 2018 huangdayu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
