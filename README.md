# Almanac

[![GitHub watch](https://img.shields.io/github/watchers/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/watchers)
[![GitHub issues](https://img.shields.io/github/issues/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/issues)
[![GitHub forks](https://img.shields.io/github/forks/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/network)
[![GitHub stars](https://img.shields.io/github/stars/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac/stargazers)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)
[![Badge](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu/#/zh_CN)

<!-- [![GitHub license](https://img.shields.io/github/license/HuangDayu/Almanac.svg)](https://github.com/HuangDayu/Almanac) -->

# 使用示例

```java

/**
 * 日历
 *
 * @param timeZoneDTO
 * @return
 */
AlmanacUtils.dayCalendar(new TimeZoneDTO("广东省徐闻县", Calendar.getInstance()));

/**
 * 月历
 *
 * @param timeZoneDTO
 * @return
 */
AlmanacUtils.monthCalendar(new TimeZoneDTO("广东省徐闻县", Calendar.getInstance()));

```

# 说明

**本项目类似[寿星天文历](http://www.nongli.net/sxwnl/)和[日梭万年历](https://www.nongli114.com/rili/)，但是数据还不是很全，算法有待校验。**

# 申明

**我只是代码的搬运工！如有侵权，联系删除！**

# 特别感谢

- [wangpeng047](http://blog.csdn.net/wangpeng047/article/details/38559591)  
- [lxslove](http://blog.csdn.net/lxslove/article/details/6083396)  
- [moodlxs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)  
- [hanoi](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)  

# 日历说明

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

# BUG

- 时间无法进入公元前

# 不足

- 天文历信息不足：方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等

# 程序运行效果展示

| key | value | value | value |
| :--------: | :-------- | :-------- | :-------- |
| `日期` | 2018年11月01日 | 1995年08月12日 | 0001年01月01日 |
| `时间` | 11时06分48秒667毫秒 | 11时10分10秒00毫秒 | 11时10分10秒00毫秒 |
| `星期` | 星期四 | 星期六 | 星期一 |
| `地点` | 广东 徐闻 | 广东 徐闻 | 广东 徐闻 |
| `年号` | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元1995年 | [西汉]平帝 刘衍 元始1年 |
| `农历` | 戊戌狗年玖月廿四 | 乙亥猪年柒月十七 | 庚申鸡年冬月十八 |
| `时辰` | 午时 | 午时 | 午时 |
| `黄历` | 戊戌年壬戌月丁酉日丙午时 | 乙亥年甲申月乙亥日壬午时 | 庚申年戊子月丁丑日丙午时 |
| `天干` | 戊壬丁丙 | 乙甲乙壬 | 庚戊丁丙 |
| `地支` | 戌戌酉午 | 亥申亥午 | 申子丑午 |
| `八字` | 戊戌壬戌丁酉丙午 | 乙亥甲申乙亥壬午 | 庚申戊子丁丑丙午 |
| `回历` | 1440年2月21日 | 1416年3月15日 | -640年5月16日 |
| `儒略日` | 2458424 | 2449942 | 1721426 |
| `黄帝纪年` | 开元4716年 | 开元4693年 | 开元2698年 |
| `生肖` | 狗 | 猪 | 鸡 |
| `节假日` | 无 | 无 | 一九第8天  |
| `经度` | 东经 110°16'67" | 东经 110°16'67" | 东经 110°16'67" |
| `纬度` | 北纬 20°33'33" | 北纬 20°33'33" | 北纬 20°33'33" |
| `时区` | +0800 东八区 | +0800 东八区 | +0800 东八区 |
| `港口` | 乌石港   | 乌石港   | 乌石港   |
| `昼长` | 11:22:55 | 12:52:48 | 10:54:50 |
| `夜长` | 12:37:04 | 11:07:11 | 13:05:09 |
| `天亮` | 06:18:41 | 05:55:05 | 06:50:20 |
| `日出` | 06:41:27 | 06:18:10 | 07:14:22 |
| `中天` | 12:22:54 | 12:44:34 | 12:41:47 |
| `日落` | 18:04:22 | 19:10:58 | 18:09:12 |
| `天黑` | 18:27:07 | 19:34:03 | 18:33:14 |
| `月出` | 00:13:02 | 20:13:13 | 03:35:37 |
| `月中` | 06:55:44 | 13:52:54 | 09:30:08 |
| `月落` | 13:38:27 | 07:32:35 | 15:24:39 |
| `月相` | 下弦 00:40:14 | 无 | 无 |
| `月天数` | 30天 | 30天 | 30天 |
| `大月否` | 是 | 是 | 是 |
| `闰月否` | 否 | 否 | 否 |
| `闰年否` | 否 | 是 | 否 |
| `星座` | 天蝎座 | 狮子座 | 摩羯座 |
| `下一节气` | 2018-11-07 19:31:39 立冬 | 1995-08-23 22:34:50 处暑 | 1-01-06 20:41:46 小寒 |
| `春分` | 2018-03-21 00:15:24  | 1995-03-21 10:14:27  | 1-03-23 05:43:49  |
| `夏至` | 2018-06-21 18:07:12  | 1995-06-22 04:34:22  | 1-06-25 04:53:32  |
| `秋分` | 2018-09-23 09:54:01  | 1995-09-23 20:13:00  | 1-09-25 15:39:49  |
| `冬至` | 2018-12-22 06:22:38  | 1995-12-22 16:16:47  | 1-12-23 08:19:28  |

# Almanac APP 演示

<div align="center">
<img src="https://github.com/HuangDayu/AlmanacApp/blob/master/ezgif.com-video-to-gif-1.gif" height="450px" width="250px" alt="设置日期" >
<img src="https://github.com/HuangDayu/AlmanacApp/blob/master//ezgif.com-video-to-gif-2.gif" height="450px" width="250px" alt="设置时间" >
<img src="https://github.com/HuangDayu/AlmanacApp/blob/master//ezgif.com-video-to-gif-3.gif" height="450px" width="250px" alt="设置位置" >
</div>

# Almanac APP 下载

- [点击下载](https://github.com/HuangDayu/AlmanacApp/raw/master/AlmanacApp.apk)

# Almanac APP 源码

- [AlmanacAPP](https://github.com/HuangDayu/AlmanacApp)  

# 编译

```shell
javac ./com/almanac/main/Main.java 
```
# 运行

```shell
java com.almanac.AlmanacApplication
```

# 参考文献

[寿星天文历Java封装整理版](https://blog.csdn.net/wangpeng047/article/details/38559591)  
[寿星万年历---java算法实现-csdn](https://blog.csdn.net/lxslove/article/details/6083396)  
[寿星万年历---java算法实现-cnblogs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)  
[根据经纬度计算日出、日落、中天、天亮、天黑和昼长时间](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)  
