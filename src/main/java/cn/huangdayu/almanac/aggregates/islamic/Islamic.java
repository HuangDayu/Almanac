package cn.huangdayu.almanac.aggregates.islamic;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.dto.InfoDTO;

import java.util.LinkedList;

/**
 * 回历
 *
 * @author huangdayu create at 2021/1/21 10:51
 */
public class Islamic extends AbstractAlmanac {

    public Islamic(int julianDays) {
        // 以下算法使用Excel测试得到,测试时主要关心年临界与月临界
        int z, y, m, d;
        d = julianDays + 503105;
        // 10631为一周期(30年)
        z = d / 10631;
        d -= z * 10631;
        // 加0.5的作用是保证闰年正确(一周中的闰年是第2,5,7,10,13,16,18,21,24,26,29年)
        y = (int) Math.floor((d + 0.5) / 354.366);
        d -= (int) Math.floor(y * 354.366 + 0.5);
        // 分子加0.11,分母加0.01的作用是第354或355天的的月分保持为12月(m=11)
        m = (int) Math.floor((d + 0.11) / 29.51);
        d -= (int) Math.floor(m * 29.5 + 0.5);
        this.setYear(z * 30 + y + 1);
        this.setMonth(m + 1);
        this.setDay(d + 1);
    }

    /**
     * 年(回历)
     */
    private int year;
    /**
     * 月(回历)
     */
    private int month;
    /**
     * 日(回历)
     */
    private int day;

    @Override
    public InfoDTO getBaseInfo() {
        return new InfoDTO("回历", "Islamic", year + "年" + month + "月" + day + "日");
    }

    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("回历年", "year", year + ""));
        list.add(new InfoDTO("回历月", "month", month + ""));
        list.add(new InfoDTO("回历日", "day", day + ""));
        return list;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
