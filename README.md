# Almanac

__本项目类似寿星天文历和日梭万年历__  
__我只是代码的搬运工！如有侵权，联系删除。联系邮箱：1161946342@qq.com__

### 感谢：  
[wangpeng047](http://blog.csdn.net/wangpeng047/article/details/38559591)  
[lxslove](http://blog.csdn.net/lxslove/article/details/6083396<br>)  
[moodlxs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)  
[hanoi](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)  

### 类介绍：
1. Annals.java：纪年表类(农历，节假日，纪年表等数据表)
1. AstronomyArithmetic.java：天文算法类
1. CalendarTime.java:时区，时间格式转换，类型转换和处理类
1. Common.java:公用天文数据类
1. DataMapList.java:数据泛型集合类,所有key对应的volue都在该泛型集合中
1. FestivalAndHoliday.java:节假日计算类
1. JulianCalendar.java:儒略日计算类
1. GetPort.java:港口数据类（根据配置文件中的经纬度来确定最近港口）
1. GetSetData.java:日落日出数据类
1. IslamicCalendar.java :伊斯兰历（回历）算法类
1. LunarCalendar.java 农历算法类
1. LunarDate.java 农历数据类（注意是否属于对象
1. QiShuo.java 气朔计算和参数数据表类
1. SunAndMoon.java 日出日落时间计算类（根据配置文件的中经纬度和公历时间）

### 说明：
`src_new`	,`src_old`,	`src_test` 这3个源码包是旧版本的，由于改动比较大，所以没有删掉。请忽略。

### bug：
1. 时间无法进入公元前
1. 天文历信息不足：太阳高度、方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等

### 程序运行效果展示：

| key | value | value | value |
|:-------- | :-------- | :-------- | :--------:| 
| 日期 | 2018年10月06日 | 1995年08月12日 | 0001年01月01日 |
| 时间 | 21时42分23秒762毫秒 | 11时10分10秒00毫秒 | 11时10分10秒00毫秒 |
| 星期 | 星期六 | 星期六 | 星期一 |
| 年号 | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元1995年 | [西汉]平帝 刘衍 元始1年 |
| 农历 | 戊戌狗年捌月廿七 | 乙亥猪年柒月十七 | 庚申鸡年冬月十八 |
| 时辰 | 亥时[二更]三刻 | 午时 | 午时 |
| 黄历 | 戊戌年辛酉月辛未日己亥时 | 乙亥年甲申月乙亥日壬午时 | 庚申年戊子月丁丑日丙午时 |
| 天干 | 戊辛辛己 | 乙甲乙壬 | 庚戊丁丙 |
| 地支 | 戌酉未亥 | 亥申亥午 | 申子丑午 |
| 八字 | 戊戌辛酉辛未己亥 | 乙亥甲申乙亥壬午 | 庚申戊子丁丑丙午 |
| 回历 | 1440年1月25日 | 1416年3月15日 | -640年5月16日 |
| 儒略日 | 2458398 | 2449942 | 1721426 |
| 黄帝纪年 | 4716年 | 4693年 | 2698年 |
| 生肖 | 狗 | 猪 | 鸡 |
| 地点 | 广东 徐闻 | 广东 徐闻 | 广东 徐闻 |
| 节假日 | 老人节  | 无 | 一九第8天  |
| 经度 | 东经 110°16'67" | 东经 110°16'67" | 东经 110°16'67" |
| 纬度 | 北纬 20°33'33" | 北纬 20°33'33" | 北纬 20°33'33" |
| 时区 | +0800 东八区 | +0800 东八区 | +0800 东八区 |
| 港口 | 乌石港   | 乌石港   | 乌石港   |
| 昼长 | 11:51:09 | 12:52:48 | 10:54:50 |
| 夜长 | 12:08:50 | 11:07:11 | 13:05:09 |
| 天亮 | 06:09:38 | 05:55:05 | 06:50:20 |
| 日出 | 06:31:45 | 06:18:10 | 07:14:22 |
| 中天 | 12:27:20 | 12:44:34 | 12:41:47 |
| 日落 | 18:22:54 | 19:10:58 | 18:09:12 |
| 天黑 | 18:45:01 | 19:34:03 | 18:33:14 |
| 月出 | 03:22:58 | 20:13:13 | 03:35:37 |
| 月中 | 09:56:31 | 13:52:54 | 09:30:08 |
| 月落 | 16:30:04 | 07:32:35 | 15:24:39 |
| 月相 | 无 | 无 | 无 |
| 月天数 | 29天 | 30天 | 30天 |
| 大月否 | 否 | 是 | 是 |
| 闰月否 | 否 | 否 | 否 |
| 闰年否 | 否 | 是 | 否 |
| 星座 | 天秤座 | 狮子座 | 摩羯座 |
| 下一节气 | 2018-10-08 16:14:37 寒露 | 1995-08-23 22:34:50 处暑 | 1-01-06 20:41:46 小寒 |
| 春分 | 2018-03-21 00:15:24  | 1995-03-21 10:14:27  | 1-03-23 05:43:49  |
| 夏至 | 2018-06-21 18:07:12  | 1995-06-22 04:34:22  | 1-06-25 04:53:32  |
| 秋分 | 2018-09-23 09:54:01  | 1995-09-23 20:13:00  | 1-09-25 15:39:49  |
| 冬至 | 2018-12-22 06:22:38  | 1995-12-22 16:16:47  | 1-12-23 08:19:28  |

### 编译： 
```shell
javac ./com/almanac/main/Main.java 
```
### 运行：
```shell
java com.almanac.main.Main
```
