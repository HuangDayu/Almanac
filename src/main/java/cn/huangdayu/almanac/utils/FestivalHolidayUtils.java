package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.dto.HolidayDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;

import java.util.Calendar;


/**
 * 节假日计算工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class FestivalHolidayUtils {

    // 某月的第几个星期几,如第2个星期一指从月首开始顺序找到第2个"星期一"
    public static final String[] wFtv = {"0150I世界麻风日", "0520.国际母亲节", "0530I全国助残日", "0630.父亲节", "0730.被奴役国家周",
            "0932I国际和平日", "0940.国际聋人节 世界儿童日", "0950I世界海事日", "1011.国际住房日", "1013I国际减轻自然灾害日(减灾日)", "1144I感恩节"};

    // 假日表,由initFestival初始化
    public static String[][] sFtv;

    static {
        // 国历节日,#表示放假日,I表示重要节日或纪念日
        String s = "01#元旦|" // 1月
                + "02I世界湿地日,10.国际气象节,14I情人节|" // 2月
                + "01.国际海豹日,03.全国爱耳日,05.1963-9999学雷锋纪念日,08I妇女节,12I植树节,12.1925-9999孙中山逝世纪念日,14.国际警察日," // 3月
                + "15I1983-9999消费者权益日,17.中国国医节,17.国际航海日,21.世界森林日,21.消除种族歧视国际日,21.世界儿歌日,22I世界水日,"
                + "23I世界气象日,24.1982-9999世界防治结核病日,25.全国中小学生安全教育日,30.巴勒斯坦国土日|"
                + "01I1564-9999愚人节,01.全国爱国卫生运动月(四月),01.税收宣传月(四月),07I世界卫生日,22I世界地球日,23.世界图书和版权日,24.亚非新闻工作者日|" // 4月
                + "01#1889-9999劳动节,04I青年节,05.碘缺乏病防治日,08.世界红十字日,12I国际护士节,15I国际家庭日,17.国际电信日,18.国际博物馆日," // 5月
                + "20.全国学生营养日,23.国际牛奶日,31I世界无烟日|"
                + "01I1925-9999国际儿童节,05.世界环境保护日,06.全国爱眼日,17.防治荒漠化和干旱日,23.国际奥林匹克日,25.全国土地日,26I国际禁毒日|" // 6月
                + "01I1997-9999香港回归纪念日,01I1921-9999中共诞辰,01.世界建筑日,02.国际体育记者日,07I1937-9999抗日战争纪念日,11I世界人口日,30.非洲妇女日|" // 7月
                + "01I1927-9999建军节,08.中国男子节(爸爸节)|" // 8月
                + "03I1945-9999抗日战争胜利纪念,08.1966-9999国际扫盲日,08.国际新闻工作者日,09.毛泽东逝世纪念,10I中国教师节,14.世界清洁地球日," // 9月
                + "16.国际臭氧层保护日,18I九·一八事变纪念日,20.国际爱牙日,27.世界旅游日,28I孔子诞辰|"
                + "01#1949-9999国庆节,01.世界音乐日,01.国际老人节,02#1949-9999国庆节假日,02.国际和平与民主自由斗争日,03#1949-9999国庆节假日," // 10月
                + "04.世界动物日,06.老人节,08.全国高血压日,08.世界视觉日,09.世界邮政日,09.万国邮联日,10I辛亥革命纪念日,10.世界精神卫生日,"
                + "13.世界保健日,13.国际教师节,14.世界标准日,15.国际盲人节(白手杖节),16.世界粮食日,17.世界消除贫困日,22.世界传统医药日,24.联合国日,31.世界勤俭日|"
                + "07.1917-9999十月社会主义革命纪念日,08.中国记者日,09.全国消防安全宣传教育日,10.世界青年节,11.国际科学与和平周(本日所属的一周),12.孙中山诞辰纪念日,"
                + "14.世界糖尿病日,17.国际大学生节,17.世界学生节,20.彝族年,21.彝族年,21.世界问候日,21.世界电视日,22.彝族年,29.国际声援巴勒斯坦人民国际日|" // 11月
                + "01I1988-9999世界艾滋病日,03.世界残疾人日,05.国际经济和社会发展志愿人员日,08.国际儿童电视日,09.世界足球日,10.世界人权日," // 12月
                + "12I西安事变纪念日,13I南京大屠杀(1937年)纪念日,20.澳门回归纪念,21.国际篮球日,24I平安夜,25I圣诞节,26.毛泽东诞辰纪念";

        String[] s_Month = s.split("\\|");

        sFtv = new String[s_Month.length][];
        for (int i = 0; i < s_Month.length; i++) {
            sFtv[i] = s_Month[i].split(",");
        }
    }

    /**
     * 取某日节日
     *
     * @param timeZoneDTO
     * @param holidayDTO
     */
    public static void getDayName(TimeZoneDTO timeZoneDTO, HolidayDTO holidayDTO) {
        String happyDay = holidayDTO.getHappyDay();
        String majorDay = holidayDTO.getMajorDay();
        String otherDay = holidayDTO.getOtherDay();
        int flag = holidayDTO.getFlag();

        /****************
         * 节日名称生成 传入日物件u 返回某日节日信息
         * r.A 重要喜庆日子名称(可将日子名称置红)
         * r.B 重要日子名称
         * r.C 各种日子名称(连成一大串)
         * r.Fjia 放假日子(可用于日期数字置红)
         *****************/
        String m0 = (timeZoneDTO.getMonth() < 10 ? "0" : "") + timeZoneDTO.getMonth();
        String d0 = (timeZoneDTO.getDay() < 10 ? "0" : "") + timeZoneDTO.getDay();
        String s, s2, type;

        if (timeZoneDTO.getWeek() == 0 || timeZoneDTO.getWeek() == 6) {
            holidayDTO.setFlag(1); // 星期日或星期六放假
        }

        // 按公历日期查找
        for (int i = 0; i < sFtv[timeZoneDTO.getMonth() - 1].length; i++) { // 公历节日或纪念日,遍历本月节日表
            s = sFtv[timeZoneDTO.getMonth() - 1][i];
            if (!CommonUtils.subString(s, 0, 2).equals(d0)) {
                continue;
            }
            s = CommonUtils.subString(s, 2);
            type = CommonUtils.subString(s, 0, 1);
            if ("-".equals(CommonUtils.subString(s, 5, 6))) { // 有年限的
                if (timeZoneDTO.getYear() < Integer.parseInt(CommonUtils.subString(s, 1, 5))
                        || timeZoneDTO.getYear() > Integer.parseInt(CommonUtils.subString(s, 6, 10))) {
                    continue;
                }
                s = CommonUtils.subString(s, 10);
            } else {
                if (timeZoneDTO.getYear() < 1850) {
                    continue;
                }
                s = CommonUtils.subString(s, 1);
            }
            if ("#".equals(type)) {
                happyDay += s + " ";
                holidayDTO.setFlag(1); // 放假的节日
            }
            if ("I".equals(type)) {
                majorDay += s + " "; // 主要
            }
            if (".".equals(type)) {
                otherDay += s + " "; // 其它
            }
        }

        // 按周查找
        int w = timeZoneDTO.getWeekIndexOfMonth();
        if (timeZoneDTO.getWeek() >= timeZoneDTO.getWeekFirstOfMonth()) {
            w += 1;
        }
        int w2 = w;
        if (timeZoneDTO.getWeekIndexOfMonth() == timeZoneDTO.getWeeksOfMonth() - 1) {
            w2 = 5;
        }

        String wStr = m0 + w + timeZoneDTO.getWeek(); // d日在本月的第几个星期某
        String w2Str = m0 + w2 + timeZoneDTO.getWeek();

        for (int i = 0; i < wFtv.length; i++) {
            s = wFtv[i];
            s2 = CommonUtils.subString(s, 0, 4);
            if (!s2.equals(wStr) && !s2.equals(w2Str)) {
                continue;
            }
            type = CommonUtils.subString(s, 4, 5);
            s = CommonUtils.subString(s, 5);
            if ("#".equals(type)) {
                happyDay += s + " ";
                holidayDTO.setFlag(1);
            }
            if ("I".equals(type)) {
                majorDay += s + " ";
            }
            if (".".equals(type)) {
                otherDay += s + " ";
            }
        }

        holidayDTO.setHappyDay(happyDay);
        holidayDTO.setMajorDay(majorDay);
        holidayDTO.setOtherDay(otherDay);
        holidayDTO.setFlag(flag);
    }

}
