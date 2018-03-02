# Almanac
## 本项目类似寿星天文历和日梭万年历
## 我只是代码的搬运工！如有侵权，联系删除。联系邮箱：1161946342@qq.com
### 部分代码源于：
### http://blog.csdn.net/wangpeng047/article/details/38559591
### http://blog.csdn.net/lxslove/article/details/6083396<br>
### http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html
### http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html
## 类介绍
### Annals.java：纪年表类(农历，节假日，纪年表等数据表)
### AstronomyArithmetic.java：天文算法类
### CalendarTime.java:时区，时间格式转换，类型转换和处理类
### Common.java:公用天文数据类
### DataMapList.java:数据泛型集合类,所有key对应的volue都在该泛型集合中
### FestivalAndHoliday.java:节假日计算类
### JulianCalendar.java:儒略日计算类
### GetPort.java:港口数据类（根据配置文件中的经纬度来确定最近港口）
### GetSetData.java:日落日出数据类
### IslamicCalendar.java :伊斯兰历（回历）算法类
### LunarCalendar.java 农历算法类
### LunarDate.java 农历数据类（注意是否属于对象
### QiShuo.java 气朔计算和参数数据表类
### SunAndMoon.java 日出日落时间计算类（根据配置文件的中经纬度和公历时间）

## 使用方法:
### 只需要在main类中定义时间和地点，即可打印出数据信息
### CalendarTime类的构造方法：传入参数可指定时间，不传入参数使用最新时间

## 目前缺陷：缺少以下数据：
### 天文历：高度、方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等
### 已发现bug：时间无法进入公元前

## 程序运行效果：
#### 日期 : 2018年3月2日 
#### 时间 : 16时28分13秒747毫秒
#### 星期 : 周五
#### 地点 : 广东 深圳
#### 经度 : 东经 114°05'00"
#### 纬度 : 北纬 22°55'00"
#### 时区 : +0800 东八区
#### 昼长 : 11:44:05
#### 夜长 : 12:15:54
#### 天亮 : 06:21:17
#### 日出 : 06:43:48
#### 中天 : 12:35:50
#### 日落 : 18:27:53
#### 天黑 : 18:50:23
#### 月出 : 18:48:46
#### 月中 : 12:50:00
#### 月落 : 06:51:14
#### 港口 : 海沁沙港  
#### 儒略日 : 2458180
#### 干支年 : 戊戌
#### 干支月 : 甲寅
#### 干支日 : 癸巳
#### 干支时 : 庚申
#### 黄帝纪年 : 4716年
#### 生肖 : 狗年
#### 农历年 : 戊戌
#### 农历月 : 正月
#### 农历日 : 十五
#### 月天数 : 29天
#### 大月否 : 否
#### 闰月否 : 否
#### 闰年否 : 否
#### 回历年 : 1439年
#### 回历月 : 6月
#### 回历日 : 14日
#### 星座 : 双鱼座
#### 节气 : 惊蛰 3月5日 23:28:06
#### 天干 : 戊甲癸庚
#### 地支 : 戌寅巳申
#### 八字 : 戊戌甲寅癸巳庚申
#### 黄历 : 戊戌年甲寅月癸巳日庚申时
#### 伊斯兰历 : 1439年6月14日
#### 年号 : [当代]新中国  公历纪元2018年
#### 节假日 : 壮族歌墟节 苗族踩山节 达斡尔族卡钦 八九第8天 
#### 月相 : 望月
#### 农历 : 戊戌年正月十五日
#### 立春 : 2018-02-04 05:28:25
#### 雨水 : 2018-02-19 01:17:57
#### 惊蛰 : 2018-03-05 23:28:06
#### 春分 : 2018-03-21 00:15:24
#### 清明 : 2018-04-05 04:12:43
#### 谷雨 : 2018-04-20 11:12:29
#### 立夏 : 2018-05-05 21:25:18
#### 小满 : 2018-05-21 10:14:33
#### 芒种 : 2018-06-06 01:29:04
#### 夏至 : 2018-06-21 18:07:12
#### 小暑 : 2018-07-07 11:41:47
#### 大暑 : 2018-07-23 05:00:16
#### 立秋 : 2018-08-07 21:30:34
#### 处暑 : 2018-08-23 12:08:30
#### 白露 : 2018-09-08 00:29:37
#### 秋分 : 2018-09-23 09:54:01
#### 寒露 : 2018-10-08 16:14:37
#### 霜降 : 2018-10-23 19:22:18
#### 立冬 : 2018-11-07 19:31:39
#### 小雪 : 2018-11-22 17:01:24
#### 大雪 : 2018-12-07 12:25:48
#### 冬至 : 2018-12-22 06:22:38
#### 小寒 : 2019-01-05 23:38:52
#### 大寒 : 2019-01-20 16:59:27
#### 2018-02-04 05:28:25

#### 日期 : 1年1月1日 
#### 时间 : 11时22分33秒44毫秒
#### 星期 : 周一
#### 地点 : 广东 深圳
#### 经度 : 东经 114°05'00"
#### 纬度 : 北纬 22°55'00"
#### 时区 : +0800 东八区
#### 昼长 : 10:46:06
#### 夜长 : 13:13:53
#### 天亮 : 06:38:45
#### 日出 : 07:03:12
#### 中天 : 12:26:15
#### 日落 : 17:49:18
#### 天黑 : 18:13:44
#### 月出 : 03:21:55
#### 月中 : 09:14:12
#### 月落 : 15:06:29
#### 港口 : 海沁沙港  
#### 儒略日 : 1721426
#### 干支年 : 庚申
#### 干支月 : 戊子
#### 干支日 : 丁丑
#### 干支时 : 丙午
#### 黄帝纪年 : 2698年
#### 生肖 : 鸡年
#### 农历年 : 庚申
#### 农历月 : 冬月
#### 农历日 : 十八
#### 月天数 : 30天
#### 大月否 : 是
#### 闰月否 : 否
#### 闰年否 : 否
#### 回历年 : -640年
#### 回历月 : 5月
#### 回历日 : 16日
#### 星座 : 摩羯座
#### 节气 : null
#### 天干 : 庚戊丁丙
#### 地支 : 申子丑午
#### 八字 : 庚申戊子丁丑丙午
#### 黄历 : 庚申年戊子月丁丑日丙午时
#### 伊斯兰历 : -640年5月16日
#### 年号 : [西汉]平帝 刘衍 元始1年
#### 节假日 : 一九第8天 
#### 月相 : 无
#### 农历 : 庚申年冬月十八日
#### 立春 : 1-02-05 15:18:39
#### 雨水 : 1-02-20 16:46:32
#### 惊蛰 : 1-03-07 21:40:21
#### 春分 : 1-03-23 05:43:49
#### 清明 : 1-04-07 17:06:21
#### 谷雨 : 1-04-23 07:05:54
#### 立夏 : 1-05-08 23:22:51
#### 小满 : 1-05-24 16:57:43
#### 芒种 : 1-06-09 11:08:08
#### 夏至 : 1-06-25 04:53:32
#### 小暑 : 1-07-10 21:22:17
#### 大暑 : 1-07-26 11:50:36
#### 立秋 : 1-08-10 23:33:34
#### 处暑 : 1-08-26 08:16:02
#### 白露 : 1-09-10 13:32:38
#### 秋分 : 1-09-25 15:39:49
#### 寒露 : 1-10-10 14:36:17
#### 霜降 : 1-10-25 11:05:16
#### 立冬 : 1-11-09 05:25:56
#### 小雪 : 1-11-23 22:38:36
#### 大雪 : 1-12-08 15:14:30
#### 冬至 : 1-12-23 08:19:28
#### 小寒 : 2-01-07 02:25:51
#### 大寒 : 2-01-21 22:33:38
#### 1-02-05 15:18:39
