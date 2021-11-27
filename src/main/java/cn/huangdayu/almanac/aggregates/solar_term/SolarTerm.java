package cn.huangdayu.almanac.aggregates.solar_term;

import cn.huangdayu.almanac.aggregates.astronomical.Astronomical;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangdayu create at 2021/1/22 11:37
 */
@Data
public class SolarTerm {

    private SolarTerm() {
    }

    public SolarTerm(int julianDayForToday, QiShuo qiShuo, Astronomical astronomical) {
        // 计算当前月份的前一个节气，并从该节气开始结算接下来的24个节气
        int qk = (int) Math.floor((julianDayForToday - qiShuo.zhongQi[0] - 7) / 15.2184);
        if (qk < 23 && julianDayForToday >= qiShuo.zhongQi[qk + 1]) {
            // 节气的取值范围是0-23
            qk++;
        }
        double sunLonValue = astronomical.getSunSolarRetina();
        double sunLonTime;
        List<SolarTerm> solarTermAfterDTOS = new ArrayList<>();
        int qj = 24;
        int qn;
        for (int qi = qk; qi < qj; ) {
            // FIXME 2021-01-17 计算太阳视黄经较耗时
            sunLonTime = AnnalsUtils.qi_accurate(sunLonValue);
            julianDay = (int) Math.floor(sunLonTime + 0.5);
            qn = (int) Math.floor(sunLonValue / CommonUtils.PI_2 * 24 + 24000006.01) % 24;
            sunLonValue += CommonUtils.PI_2 / 24;
            // BUG 2021-01-23 qiShuoDO.ZQ[qi]的儒略日与sunLonTime的值有出入,与julianDayOfThisDay的值一致
            julianDay = CommonUtils.JULIAN_FOR_2000 + julianDay;
            int afterDays = julianDay - CommonUtils.JULIAN_FOR_2000 - julianDayForToday;
            SolarTerm solarTerm = afterDays == 0 ? this : new SolarTerm();
            solarTerm.setJulianTime(sunLonTime);
            solarTerm.setDateTime(JulianCalendarUtils.julianDays2str(CommonUtils.JULIAN_FOR_2000 + sunLonTime));
            solarTerm.setIndex(qn);
            solarTerm.setName(AnnalsUtils.JIEQI[qn]);
            solarTerm.setJulianDay(julianDay);
            solarTerm.setDesc(ConstantsUtils.getDesc(AnnalsUtils.JIEQI[qn]));
            solarTerm.setAfterDay(afterDays);
            solarTermAfterDTOS.add(solarTerm);
            qi++;
            if (qi == 24) {
                qi = 0;
                qj = qk;
            }
        }
        this.setNext(solarTermAfterDTOS);
    }

    private Integer index;
    private String name;
    private Integer julianDay;
    private Integer afterDay;
    private String dateTime;
    private String desc;
    /**
     * 节气时刻(儒略日)
     */
    private double julianTime;

    private List<SolarTerm> next;

    public SolarTerm getNextOne() {
        for (SolarTerm solarTerm : next) {
            if (solarTerm.getAfterDay() > 0) {
                // 列表中，第一个大于0的则是下一个节气
                return solarTerm;
            }
        }
        return null;
    }

    public SolarTerm getByName(String name) {
        for (SolarTerm solarTerm : next) {
            if (solarTerm.getName().equalsIgnoreCase(name)) {
                return solarTerm;
            }
        }
        return null;
    }

    public String getDetails() {
        return name + " " + dateTime + (afterDay != 0 ? " 至今" + afterDay + "天" : " 今天");
    }

    public String getInfo() {
        return name != null ? getDetails() : getNextOne().getDetails();
    }


    @Override
    public String toString() {
        return "SolarTermDTO{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", julianDay=" + julianDay +
                ", afterDay=" + afterDay +
                ", date='" + dateTime + '\'' +
                ", julianTime=" + julianTime +
                '}';
    }
}
