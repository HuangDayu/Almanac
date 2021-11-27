package cn.huangdayu.almanac.aggregates.islamic;

import lombok.Data;

/**
 * 回历
 *
 * @author huangdayu create at 2021/1/21 10:51
 */
@Data
public class Islamic {

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

    public String getInfo() {
        return year + "年" + month + "月" + day + "日";
    }

    @Override
    public String toString() {
        return "IslamicDTO{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
