我只是代码的搬运工！如有侵权，联系删除。联系邮箱：1161946342@qq.com
部分代码源于：
	http://blog.csdn.net/wangpeng047/article/details/38559591
	http://blog.csdn.net/lxslove/article/details/6083396
	http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html
	http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html
万年历，天文历的Java实现。
Annals.java：纪年表类(农历，节假日，纪年表等数据表)
AstronomyArithmetic.java：天文算法类
CalendarTime.java:时区，时间格式转换，类型转换和处理类
Common.java:公用天文数据类
DataMapList.java:数据泛型集合类,所有key对应的volue都在该泛型集合中
FestivalAndHoliday.java:节假日计算类
JulianCalendar.java:儒略日计算类
GetPort.java:港口数据类（根据配置文件中的经纬度来确定最近港口）
GetSetData.java:日落日出数据类
IslamicCalendar.java :伊斯兰历（回历）算法类
LunarCalendar.java 农历算法类
LunarDate.java 农历数据类（注意是否属于对象）
QiShuo.java 气朔计算和参数数据表类
SunAndMoon.java 日出日落时间计算类（根据配置文件的中经纬度和公历时间）

使用方法:
		只需要在main类中定义时间和地点，即可打印出数据信息
		CalendarTime类的构造方法：传入参数可指定时间，不传入参数使用最新时间

目前缺陷：缺少以下数据：
		天文历：高度、方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等
已发现bug：时间无法进入公元前

程序运行效果：
日期 : 2017年12月3日 
时间 : 11时22分33秒44毫秒
星期 : 周日
地点 : 广东 广州
经度 : 东经 113°26'67"
纬度 : 北纬 23°13'33"
时区 : +0800 东八区
昼长 : 10:48:04
夜长 : 13:11:55
天亮 : 06:28:13
日出 : 06:52:33
中天 : 12:16:36
日落 : 17:40:38
天黑 : 18:04:58
月出 : 17:35:04
月中 : 11:48:57
月落 : 06:02:49
港口 : 黄埔港  
儒略日 : 2458091
干支年 : 丁酉
干支月 : 辛亥
干支日 : 甲子
干支时 : 庚午
黄帝纪年 : 4715年
生肖 : 鸡年
农历年 : 丁酉
农历月 : 什月
农历日 : 十六
月天数 : 30天
大月否 : 是
闰月否 : 否
闰年否 : 否
回历年 : 1439年
回历月 : 3月
回历日 : 14日
星座 : 射手座
节气 : 大雪 12月07日 06:32:35
天干 : 丁辛甲庚
地支 : 酉亥子午
八字 : 丁酉辛亥甲子庚午
伊斯兰历 : 1439年3月14日
年号 : [当代]新中国  公历纪元2017年
节假日 : 世界残疾人日 
月相 : 望月
农历 : 丁酉年什月十六日

日期 : 1年12月3日 
时间 : 11时22分33秒44毫秒
星期 : 周一
地点 : 广东 广州
经度 : 东经 113°26'67"
纬度 : 北纬 23°13'33"
时区 : +0800 东八区
昼长 : 10:49:19
夜长 : 13:10:40
天亮 : 06:26:52
日出 : 06:51:09
中天 : 12:15:49
日落 : 17:40:28
天黑 : 18:04:45
月出 : 11:50:36
月中 : 06:22:17
月落 : 00:53:58
港口 : 黄埔港  
儒略日 : 1721762
干支年 : 辛酉
干支月 : 己亥
干支日 : 癸丑
干支时 : 戊午
黄帝纪年 : 2699年
生肖 : 鸡年
农历年 : 辛酉
农历月 : 什月
农历日 : 廿九
月天数 : 30天
大月否 : 是
闰月否 : 否
闰年否 : 是
回历年 : -639年
回历月 : 4月
回历日 : 27日
星座 : 射手座
节气 : 大雪 12月08日:14:30
天干 : 辛己癸戊
地支 : 酉亥丑午
八字 : 辛酉己亥癸丑戊午
伊斯兰历 : -639年4月27日
年号 : [西汉]平帝 刘衍 元始1年
节假日 : 无
月相 : 无
农历 : 辛酉年什月廿九日