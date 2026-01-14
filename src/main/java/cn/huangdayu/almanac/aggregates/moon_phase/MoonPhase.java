package cn.huangdayu.almanac.aggregates.moon_phase;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.astronomical.Astronomical;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.dto.InfoDTO;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 月相
 *
 * @author huangdayu create at 2021/2/13 8:38
 */
public class MoonPhase extends AbstractAlmanac {

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
            moonPhase.setAfterDay(afterDays);
            moonPhasesList.add(moonPhase);
        }
        this.setNext(moonPhasesList);
    }

    private Integer index;
    /**
     * 月相名称
     */
    private String name;
    /**
     * 月相时刻(儒略日)
     */
    private Integer julianDay;
    /**
     * 相离天数
     */
    private Integer afterDay;
    /**
     * 月相时间串
     */
    private String dateTime;
    /**
     * 月相时刻(儒略日)
     */
    private double julianTime;

    /**
     * 下一个月相
     */
    private List<MoonPhase> next;

    public MoonPhase getNextOne() {
        for (MoonPhase moonPhase : next) {
            if (moonPhase.getAfterDay() > 0) {
                return moonPhase;
            }
        }
        return next.get(0);
    }

    public String getDetails() {
        return name != null ? name + " " + dateTime + (afterDay != 0 ? " " + afterDay + "天" + (afterDay > 0 ? "后" : " 前") : " 今天") : getNextOne().getDetails();
    }

    @Override
    public InfoDTO getBaseInfo() {
        return name != null ? new InfoDTO("月相", "MoonPhase", name + " " + dateTime) : getNextOne().getBaseInfo();
    }

    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("月相名称", "moonPhase", name));
        list.add(new InfoDTO("月相时间", "dateTime", dateTime));
        list.add(new InfoDTO("月相间隔", "afterDay", afterDay + ""));
        list.add(new InfoDTO("下个月相", "nextMoonPhase", getNextOne().getDetails()));
        return list;
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(Integer julianDay) {
        this.julianDay = julianDay;
    }

    public Integer getAfterDay() {
        return afterDay;
    }

    public void setAfterDay(Integer afterDay) {
        this.afterDay = afterDay;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getJulianTime() {
        return julianTime;
    }

    public void setJulianTime(double julianTime) {
        this.julianTime = julianTime;
    }

    public List<MoonPhase> getNext() {
        return next;
    }

    public void setNext(List<MoonPhase> next) {
        this.next = next;
    }
}
