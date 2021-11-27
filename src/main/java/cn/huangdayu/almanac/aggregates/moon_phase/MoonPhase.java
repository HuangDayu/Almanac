package cn.huangdayu.almanac.aggregates.moon_phase;

import cn.huangdayu.almanac.aggregates.BaseAlmanac;
import cn.huangdayu.almanac.aggregates.astronomical.Astronomical;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 月相
 *
 * @author huangdayu create at 2021/2/13 8:38
 */
@Getter
@Setter
public class MoonPhase extends BaseAlmanac {

    private MoonPhase() {
    }

    public MoonPhase(int julianDayForToday, Julian julianOfMonth, Astronomical astronomical) {
        List<MoonPhase> moonPhasesList = new ArrayList<>();
        int julianDays = julianOfMonth.getFirstJulianDayOfMonth() + julianOfMonth.getNumberDayOfMonth();
        double moonLon = astronomical.getLunarRetina();
        for (int j = 0; j < julianOfMonth.getNumberDayOfMonth(); j++) {
            double moonLonValue = AnnalsUtils.so_accurate(moonLon);
            julianDay = (int) Math.floor(moonLonValue + 0.5);
            int xn = (int) Math.floor(moonLon / CommonUtils.PI_2 * 4 + 4000000.01) % 4;
            moonLon += CommonUtils.PI_2 / 4;
            if (julianDay < julianOfMonth.getFirstJulianDayOfMonth() || julianDay >= julianDays) {
                continue;
            }
            julianDay = CommonUtils.JULIAN_FOR_2000 + julianDay;
            int afterDays = julianDay - CommonUtils.JULIAN_FOR_2000 - julianDayForToday;
            MoonPhase moonPhase = afterDays == 0 ? this : new MoonPhase();
            // 取得月相名称
            moonPhase.setName(AnnalsUtils.YUEXIANG[xn]);
            //月相时刻(儒略日)
            moonPhase.setJulianTime(CommonUtils.JULIAN_FOR_2000 + moonLonValue);
            //月相时间串
            moonPhase.setDateTime(JulianCalendarUtils.julianDays2str(CommonUtils.JULIAN_FOR_2000 + moonLonValue));
            moonPhasesList.add(moonPhase);
        }
        this.setNext(moonPhasesList);
    }

    private Integer index;
    /**
     * 月相名称
     */
    private String name;
    private Integer julianDay;
    private Integer afterDay;
    /**
     * 月相时间串
     */
    private String dateTime;
    private String desc;
    /**
     * 月相时刻(儒略日)
     */
    private double julianTime;

    private List<MoonPhase> next;

    @Override
    public String getInfo() {
        return name != null ? name + " " + dateTime : "无";
    }


    @Override
    public String toString() {
        return "MoonPhase{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", julianDay=" + julianDay +
                ", afterDay=" + afterDay +
                ", dateTime='" + dateTime + '\'' +
                ", desc='" + desc + '\'' +
                ", julianTime=" + julianTime +
                ", next=" + next +
                '}';
    }
}
