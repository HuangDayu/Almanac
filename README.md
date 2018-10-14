# Almanac

__本项目类似寿星天文历和日梭万年历__  
__我只是代码的搬运工！如有侵权，联系删除。联系邮箱：1161946342@qq.com__

1. 西历 公历 阳历
2. 农历 阴阳历 黄历
3. 伊斯兰历 回历 阴历
4. 儒略历 格里历
5. 天文历
6. [黄帝纪年](https://baike.baidu.com/item/%E9%BB%84%E5%B8%9D%E7%BA%AA%E5%B9%B4) [黄帝历](https://baike.baidu.com/item/%E9%BB%84%E5%B8%9D%E5%8E%86) [古六历](https://baike.baidu.com/item/%E5%8F%A4%E5%85%AD%E5%8E%86)

- [寿星天文历](http://www.nongli.net/sxwnl/)
- [日梭万年历](http://www.nongli114.com/rili/2017.html)
- [紫金天文台](http://www.pmo.ac.cn/)
- [中华农历网](http://www.nongli.net/)
- [时间科普](http://www.time.ac.cn/serve/down.htm)
- [中国科学院国家授时中心](http://www.ntsc.ac.cn/)
- [中国科学院紫金天文台](http://almanac.pmo.ac.cn/)
- [电子天文历表](http://almanac.pmo.ac.cn/dianzili.htm)
- [天之文](http://www.astron.ac.cn/index.htm)

### 感谢
 
[wangpeng047](http://blog.csdn.net/wangpeng047/article/details/38559591)  
[lxslove](http://blog.csdn.net/lxslove/article/details/6083396)  
[moodlxs](http://www.cnblogs.com/moodlxs/archive/2010/12/18/2345392.html)  
[hanoi](http://www.cnblogs.com/hanoi/archive/2012/07/04/2576325.html)  

### 类介绍 

- [`AlmanacBean.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/AlmanacBean.java)：实体类
- [`Almanac.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/Almanac.java)：接口
- [`AlmanacImpl.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/AlmanacImpl.java)：实现类
- [`Annals.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/Annals.java)：纪年表类(农历，节假日，纪年表等数据表)
- [`AreaUtil.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/AreaUtil.java)：地址工具类
- [`AstronomyArithmetic.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/AstronomyArithmetic.java)：天文算法类
- [`Common.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/Common.java)：公用天文数据类
- [`DataBean.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/DataBean.java)：时间位置实体类
- [`FestivalAndHoliday.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/FestivalAndHoliday.java)：节假日计算类
- [`IslamicCalendar.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/IslamicCalendar.java)：伊斯兰历（回历）算法类
- [`JulianCalendar.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/JulianCalendar.java)：儒略日计算类
- [`LunarCalendar.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/LunarCalendar.java)：农历数据类
- [`Port.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/Port.java)：港口计算类
- [`Propt.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/Propt.java)：配置文件加载类
- [`QiShuo.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/QiShuo.java)：气朔计算和参数数据表类
- [`SolarTermUtil.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/SolarTermUtil.java)：24节气工具类
- [`SunAndMoon.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/SunAndMoon.java)：日出日落时间计算类（根据配置文件的中经纬度和公历时间）
- [`TimeUtil.java`](https://github.com/HuangDayu/Almanac/blob/master/src/com/almanac/lunar/TimeUtil.java)：时间工具类（格式，类型转换等）

### 说明

`old`文件夹下4个源码包是旧版本的，由于改动比较大，所以没有删掉。请忽略。

### bug

- 时间无法进入公元前

### 不足

- 天文历信息不足：方位、赤纬、时角、黄经、黄纬，潮汐，太阳高度角，地球直射位置，地球远日点，地球近日点，日食月食等

### 程序运行效果展示

| key | value | value | value |
| :--------: | :-------- | :-------- | :-------- | 
| `日期` | 2018年10月10日 | 1995年08月12日 | 0001年01月01日 |
| `时间` | 16时35分37秒132毫秒 | 11时10分10秒00毫秒 | 11时10分10秒00毫秒 |
| `星期` | 星期三 | 星期六 | 星期一 |
| `年号` | [当代]新中国  公历纪元2018年 | [当代]新中国  公历纪元1995年 | [西汉]平帝 刘衍 元始1年 |
| `农历` | 戊戌狗年玖月初二 | 乙亥猪年柒月十七 | 庚申鸡年冬月十八 |
| `时辰` | 申时 | 午时 | 午时 |
| `黄历` | 戊戌年壬戌月乙亥日甲申时 | 乙亥年甲申月乙亥日壬午时 | 庚申年戊子月丁丑日丙午时 |
| `天干` | 戊壬乙甲 | 乙甲乙壬 | 庚戊丁丙 |
| `地支` | 戌戌亥申 | 亥申亥午 | 申子丑午 |
| `八字` | 戊戌壬戌乙亥甲申 | 乙亥甲申乙亥壬午 | 庚申戊子丁丑丙午 |
| `回历` | 1440年1月29日 | 1416年3月15日 | -640年5月16日 |
| `儒略日` | 2458402 | 2449942 | 1721426 |
| `黄帝纪年` | 开元4716年 | 开元4693年 | 开元2698年 |
| `生肖` | 狗 | 猪 | 鸡 |
| `地点` | 广东 徐闻 | 广东 徐闻 | 广东 徐闻 |
| `节假日` | 世界精神卫生日  | 无 | 一九第8天  |
| `经度` | 东经 110°16'67" | 东经 110°16'67" | 东经 110°16'67" |
| `纬度` | 北纬 20°33'33" | 北纬 20°33'33" | 北纬 20°33'33" |
| `时区` | +0800 东八区 | +0800 东八区 | +0800 东八区 |
| `港口` | 乌石港   | 乌石港   | 乌石港   |
| `昼长` | 11:46:36 | 12:52:48 | 10:54:50 |
| `夜长` | 12:13:23 | 11:07:11 | 13:05:09 |
| `天亮` | 06:10:45 | 05:55:05 | 06:50:20 |
| `日出` | 06:32:55 | 06:18:10 | 07:14:22 |
| `中天` | 12:26:13 | 12:44:34 | 12:41:47 |
| `日落` | 18:19:32 | 19:10:58 | 18:09:12 |
| `天黑` | 18:41:42 | 19:34:03 | 18:33:14 |
| `月出` | 07:23:02 | 20:13:13 | 03:35:37 |
| `月中` | 13:24:06 | 13:52:54 | 09:30:08 |
| `月落` | 19:25:10 | 07:32:35 | 15:24:39 |
| `月相` | 无 | 无 | 无 |
| `月天数` | 30天 | 30天 | 30天 |
| `大月否` | 是 | 是 | 是 |
| `闰月否` | 否 | 否 | 否 |
| `闰年否` | 否 | 是 | 否 |
| `星座` | 天秤座 | 狮子座 | 摩羯座 |
| `下一节气` | 2018-10-23 19:22:18 霜降 | 1995-08-23 22:34:50 处暑 | 1-01-06 20:41:46 小寒 |
| `春分` | 2018-03-21 00:15:24  | 1995-03-21 10:14:27  | 1-03-23 05:43:49  |
| `夏至` | 2018-06-21 18:07:12  | 1995-06-22 04:34:22  | 1-06-25 04:53:32  |
| `秋分` | 2018-09-23 09:54:01  | 1995-09-23 20:13:00  | 1-09-25 15:39:49  |
| `冬至` | 2018-12-22 06:22:38  | 1995-12-22 16:16:47  | 1-12-23 08:19:28  |

### 编译
```shell
javac ./com/almanac/main/Main.java 
```
### 运行
```shell
java com.almanac.main.Main
```
