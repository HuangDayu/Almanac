package test_5;
import static test_5.CalendarUtil.fromJulianDate;
import static test_5.CalendarUtil.toJulianDate;
import static test_5.Elp82Util.getEarthEclipticLongitudeForMoon;
import static test_5.MathUtil.modPi;
import static test_5.MathUtil.newtonIteration;
import static test_5.Vsop87dEarthUtil.getEarthEclipticLongitudeForSun;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * 计算日月合朔时间：指太阳和月球的地心视黄经之差为零，此时日月地位于垂直于黄道面的同一平面上且月球位于日地之间。月球未被太阳光照亮的半面正对向地球，在地球上看不到月亮的存在�??
 * 
 * 使用牛顿迭代法计算日月合朔的时间 求解的方程为: <br />
 * <i>f(x) = Vsop87dEarthUtil.getEarthEclipticLongitudeForSun(x) -
 * ElpMpp02Util.getEarthEclipticLongitudeForMoon(x) = 0</i>
 * 
 * @author oyyq
 */
public class NewMoonCalculator {

    /**
     * 用牛顿迭代计算日月合朔时�?
     * 
     * @param term
     *            节气
     * @param year
     *            年份
     * @return 节气时间的儒略日
     */
    public static ArrayList<Double> getJulianDayInYearAndMonthForNewMoon(int year, int month) {
        ArrayList<Double> jds = new ArrayList<Double>();
        double lastJd = 0.0d;
        for (int i = 0; i < 2; i++) {
            double jd1 = toJulianDate(year, month, 10 * (i + 1));
            double jd = newtonIteration((double x) -> modPi(getEarthEclipticLongitudeForSun(x)
                    - getEarthEclipticLongitudeForMoon(x)), jd1);
            Calendar cal = fromJulianDate(jd);
            if (cal.get(Calendar.MONTH) + 1 == month && (jd - lastJd > 1e-7)) {
                jds.add(jd);
                lastJd = jd;
            }
        }
        return jds;
    }

}
