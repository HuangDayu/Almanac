package test_5;
/******
 * 计算节气时间，对比日梭万年历，数据不准确
 */
import static java.lang.Math.PI;
import static test_5.CalendarUtil.fromJulianDate;
import static test_5.CalendarUtil.toJulianDate;
import static test_5.MathUtil.modPi;
import static test_5.MathUtil.newtonIteration;
import static test_5.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;

import java.util.Calendar;
import java.util.Map;

/**
 * 使用牛顿迭代法计�?24节气的时�? 求解的方程为: <br />
 * <i>f(x) = Vsop87dEarthUtil.getEarthEclipticLongitudeForSun(x) - angle = 0</i>
 * 
 * @author oyyq
 */
public class SolarTermsCalculator {

    private static final double RADIANS_PER_TERM = PI / 12;

    /**
     * 用牛顿迭代计算节气时�?
     * 
     * @param term
     *            节气
     * @param year
     *            年份
     * @return 节气时间的儒略日
     */
    public static double getJulianDayInYearForTermOrder(SolarTerms term, int year) {
        int order = term.getOrder();
        double angle = (order - 1) * RADIANS_PER_TERM;
        int month = term.getMonth();
        int estimateDate = term.getEstimateDate();
        double jd1 = toJulianDate(year, month, estimateDate);
        double jd = newtonIteration(
                (double x) -> modPi(getEarthEclipticLongitudeForSun(x) - angle), jd1);
        return jd;
    }

}
