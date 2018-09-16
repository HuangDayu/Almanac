package test_5;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static test_5.CalendarUtil.getJulianCentury;

/**
 * 根据IAU 2000B的数据计算章�?
 * 
 * @author oyyq
 */
public class Iau2000BUtil {

    private static double getLongitudeNutation(double t, double l, double lp, double f, double d,
            double om) {
        double n = 0.0;
        n = n + (-172064161 - 174666 * t) * sin(om) + 33386 * cos(om);
        n = n + (-13170906 - 1675 * t) * sin(2 * (f - d + om)) - 13696 * cos(2 * (f - d + om));
        n = n + (-2276413 - 234 * t) * sin(2 * (f + om)) + 2796 * cos(2 * (f + om));
        n = n + (2074554 + 207 * t) * sin(2 * om) - 698 * cos(2 * om);
        n = n + (1475877 - 3633 * t) * sin(lp) + 11817 * cos(lp);
        n = n + (-516821 + 1226 * t) * sin(lp + 2 * (f - d + om)) - 524
                * cos(lp + 2 * (f - d + om));
        n = n + (711159 + 73 * t) * sin(l) - 872 * cos(l);
        n = n + (-387298 - 367 * t) * sin(2 * f + om) + 380 * cos(2 * f + om);
        n = n + (-301461 - 36 * t) * sin(l + 2 * (f + om)) + 816 * cos(l + 2 * (f + om));
        n = n + (215829 - 494 * t) * sin(2 * (f - d + om) - lp) + 111 * cos(2 * (f - d + om) - lp);
        n = n + (128227 + 137 * t) * sin(2 * (f - d) + om) + 181 * cos(2 * (f - d) + om);
        n = n + (123457 + 11 * t) * sin(2 * (f + om) - l) + 19 * cos(2 * (f + om) - l);
        n = n + (156994 + 10 * t) * sin(2 * d - l) - 168 * cos(2 * d - l);
        n = n + (63110 + 63 * t) * sin(l + om) + 27 * cos(l + om);
        n = n + (-57976 - 63 * t) * sin(om - l) - 189 * cos(om - l);
        n = n + (-59641 - 11 * t) * sin(2 * (f + d + om) - l) + 149 * cos(2 * (f + d + om) - l);
        n = n + (-51613 - 42 * t) * sin(l + 2 * f + om) + 129 * cos(l + 2 * f + om);
        n = n + (45893 + 50 * t) * sin(2 * (f - l) + om) + 31 * cos(2 * (f - l) + om);
        n = n + (63384 + 11 * t) * sin(2 * d) - 150 * cos(2 * d);
        n = n + (-38571 - t) * sin(2 * (f + d + om)) + 158 * cos(2 * (f + d + om));
        n = n + 32481 * sin(2 * (f - lp - d + om));
        n = n - 47722 * sin(2 * (d - l)) + 18 * cos(2 * (d - l));
        n = n + (-31046 - t) * sin(2 * (l + f + om)) + 131 * cos(2 * (l + f + om));
        n = n + 28593 * sin(l + 2 * (f - d + om)) - cos(l + 2 * (f - d + om));
        n = n + (20441 + 21 * t) * sin(2 * f + om - l) + 10 * cos(2 * f + om - l);
        n = n + 29243 * sin(2 * l) - 74 * cos(2 * l);
        n = n + 25887 * sin(2 * f) - 66 * cos(2 * f);
        n = n + (-14053 - 25 * t) * sin(lp + om) + 79 * cos(lp + om);
        n = n + (15164 + 10 * t) * sin(-l + 2 * d + om) + 11 * cos(-l + 2 * d + om);
        n = n + (-15794 + 72 * t) * sin(2 * (lp + f - d + om)) - 16 * cos(2 * (lp + f - d + om));
        n = n + 21783 * sin(2 * (d - f)) + 13 * cos(2 * (d - f));
        n = n + (-12873 - 10 * t) * sin(l - 2 * d + om) - 37 * cos(l - 2 * d + om);
        n = n + (-12654 + 11 * t) * sin(-lp + om) + 63 * cos(-lp + om);
        n = n - 10204 * sin(2 * (f + d) + om - l) - 25 * cos(2 * (f + d) + om - l);
        n = n + (16707 - 85 * t) * sin(2 * lp) - 10 * cos(2 * lp);
        n = n - 7691 * sin(l + 2 * (f + d + om)) - 44 * cos(l + 2 * (f + d + om));
        n = n - 11024 * sin(2 * (f - l)) + 14 * cos(2 * (f - l));
        n = n + (7566 - 21 * t) * sin(lp + 2 * (f + om)) - 11 * cos(lp + 2 * (f + om));
        n = n + (-6637 - 11 * t) * sin(2 * (f + d) + om) + 25 * cos(2 * (f + d) + om);
        n = n + (-7141 + 21 * t) * sin(2 * (f + om) - lp) + 8 * cos(2 * (f + om) - lp);
        n = n + (-6302 - 11 * t) * sin(2 * d + om) + 2 * cos(2 * d + om);
        n = n + (5800 + 10 * t) * sin(l + 2 * (f - d) + om) + 2 * cos(l + 2 * (f - d) + om);
        n = n + 6443 * sin(2 * (l + f - d + om)) - 7 * cos(2 * (l + f - d + om));
        n = n + (-5774 - 11 * t) * sin(2 * (d - l) + om) - 15 * cos(2 * (d - l) + om);
        n = n - 5350 * sin(2 * (l + f) + om) - 21 * cos(2 * (l + f) + om);
        n = n + (-4752 - 11 * t) * sin(2 * (f - d) + om - lp) - 3 * cos(2 * (f - d) + om - lp);
        n = n + (-4940 - 11 * t) * sin(om - 2 * d) - 21 * cos(om - 2 * d);
        n = n + 7350 * sin(2 * d - l - lp) - 8 * cos(2 * d - l - lp);
        n = n + 4065 * sin(2 * (l - d) + om) + 6 * cos(2 * (l - d) + om);
        n = n + 6579 * sin(l + 2 * d) - 24 * cos(l + 2 * d);
        n = n + 3579 * sin(lp + 2 * (f - d) + om) + 5 * cos(lp + 2 * (f - d) + om);
        n = n + 4725 * sin(l - lp) - 6 * cos(l - lp);
        n = n - 3075 * sin(2 * (f + om - l)) + 2 * cos(2 * (f + om - l));
        n = n - 2904 * sin(3 * l + 2 * (f + om)) - 15 * cos(3 * l + 2 * (f + om));
        n = n + 4348 * sin(2 * d - lp) - 10 * cos(2 * d - lp);
        n = n - 2878 * sin(l - lp + 2 * (f + om)) - 8 * cos(l - lp + 2 * (f + om));
        n = n - 4230 * sin(d) - 5 * cos(d);
        n = n - 2819 * sin(2 * (f + d + om) - l - lp) - 7 * cos(2 * (f + d + om) - l - lp);
        n = n - 4056 * sin(2 * f - l) - 5 * cos(2 * f - l);
        n = n - 2647 * sin(2 * (f + d + om) - lp) - 11 * cos(2 * (f + d + om) - lp);
        n = n - 2294 * sin(om - 2 * l) + 10 * cos(om - 2 * l);
        n = n + 2481 * sin(l + lp + 2 * (f + om)) - 7 * cos(l + lp + 2 * (f + om));
        n = n + 2179 * sin(2 * l + om) - 2 * cos(2 * l + om);
        n = n + 3276 * sin(lp + d - l) + cos(lp + d - l);
        n = n - 3389 * sin(l + lp) - 5 * cos(l + lp);
        n = n + 3339 * sin(l + 2 * f) - 13 * cos(l + 2 * f);
        n = n - 1987 * sin(2 * (f - d) + om - l) + 6 * cos(2 * (f - d) + om - l);
        n = n - 1981 * sin(l + 2 * om);
        n = n + 4026 * sin(d - l) - 353 * cos(d - l);
        n = n + 1660 * sin(2 * f + d + 2 * om) - 5 * cos(d + 2 * (f + om));
        n = n - 1521 * sin(2 * (f + 2 * d + om) - l) - 9 * cos(2 * (f + 2 * d + om) - l);
        n = n + 1314 * sin(lp + d + om - l);
        n = n - 1283 * sin(2 * (f - d - lp) + om);
        n = n - 1331 * sin(l + 2 * f + 2 * d + om) - 8 * cos(l + 2 * (f + d) + om);
        n = n + 1383 * sin(2 * (f - l + d + om)) - 2 * cos(2 * (f - l + d + om));
        n = n + 1405 * sin(2 * om - l) + 4 * cos(2 * om - l);
        n = n + 1290 * sin(l + lp + 2 * (f - d + om));
        n /= 36000000000.0d;
        return toRadians(n);
    }

    private static double getObliquityNutation(double t, double l, double lp, double f, double d,
            double om) {
        double n = 0.0;
        n = n + (92052331 + 9086 * t) * cos(om) + 15377 * sin(om);
        n = n + (5730336 - 3015 * t) * cos(2 * (f - d + om)) - 4587 * sin(2 * (f - d + om));
        n = n + (978459 - 485 * t) * cos(2 * (f + om)) + 1374 * sin(2 * (f + om));
        n = n + (-897492 + 470 * t) * cos(2 * om) - 291 * sin(2 * om);
        n = n + (73871 - 184 * t) * cos(lp) - 1924 * sin(lp);
        n = n + (224386 - 677 * t) * cos(lp + 2 * (f - d + om)) - 174 * sin(lp + 2 * (f - d + om));
        n = n - 6750 * cos(l) - 358 * sin(l);
        n = n + (200728 + 18 * t) * cos(2 * f + om) + 318 * sin(2 * f + om);
        n = n + (129025 - 63 * t) * cos(l + 2 * (f + om)) + 367 * sin(l + 2 * (f + om));
        n = n + (-95929 + 299 * t) * cos(2 * (f - d + om) - lp) + 132 * sin(2 * (f - d + om) - lp);
        n = n + (-68982 - 9 * t) * cos(2 * (f - d) + om) + 39 * sin(2 * (f - d) + om);
        n = n + (-53311 + 32 * t) * cos(2 * (f + om) - l) - 4 * sin(2 * (f + om) - l);
        n = n - 1235 * cos(2 * d - l) - 82 * sin(2 * d - l);
        n = n - 33228 * cos(l + om) + 9 * sin(l + om);
        n = n + 31429 * cos(om - l) - 75 * sin(om - l);
        n = n + (25543 - 11 * t) * cos(2 * (f + d + om) - l) + 66 * sin(2 * (f + d + om) - l);
        n = n + 26366 * cos(l + 2 * f + om) + 78 * sin(l + 2 * f + om);
        n = n + (-24236 - 10 * t) * cos(2 * (f - l) + om) + 20 * sin(2 * (f - l) + om);
        n = n - 1220 * cos(2 * d) - 29 * sin(2 * d);
        n = n + (16452 - 11 * t) * cos(2 * (f + d + om)) + 68 * sin(2 * (f + d + om));
        n = n - 13870 * cos(2 * (f - lp - d + om));
        n = n + 477 * cos(2 * (d - l)) - 25 * sin(2 * (d - l));
        n = n + (13238 - 11 * t) * cos(2 * (l + f + om)) + 59 * sin(2 * (l + f + om));
        n = n + (-12338 + 10 * t) * cos(l + 2 * (f - d + om)) - 3 * sin(l + 2 * (f - d + om));
        n = n - 10758 * cos(2 * f + om - l) + 3 * sin(2 * f + om - l);
        n = n - 609 * cos(2 * l) - 13 * sin(2 * l);
        n = n - 550 * cos(2 * f) - 11 * sin(2 * f);
        n = n + (8551 - 2 * t) * cos(lp + om) - 45 * sin(lp + om);
        n = n - 8001 * cos(2 * d - l + om) + sin(2 * d - l + om);
        n = n + (6850 - 42 * t) * cos(2 * (lp + f - d + om)) - 5 * sin(2 * (lp + f - d + om));
        n = n - 167 * cos(2 * (d - f)) - 13 * sin(2 * (d - f));
        n = n + 6953 * cos(l - 2 * d + om) - 14 * sin(l - 2 * d + om);
        n = n + 6415 * cos(om - lp) + 26 * sin(om - lp);
        n = n + 5222 * cos(2 * (f + d) + om - l) + 15 * sin(2 * (f + d) + om - l);
        n = n + (168 - t) * cos(2 * lp) + 10 * sin(2 * lp);
        n = n + 3268 * cos(l + 2 * (f + d + om)) + 19 * sin(l + 2 * (f + d + om));
        n = n + 104 * cos(2 * (f - l)) + 2 * sin(2 * (f - l));
        n = n - 3250 * cos(lp + 2 * (f + om)) + 5 * sin(lp + 2 * (f + om));
        n = n + 3353 * cos(2 * (f + d) + om) + 14 * sin(2 * (f + d) + om);
        n = n + 3070 * cos(2 * (f + om) - lp) + 4 * sin(2 * (f + om) - lp);
        n = n + 3272 * cos(2 * d + om) + 4 * sin(2 * d + om);
        n = n - 3045 * cos(l + 2 * (f - d) + om) + sin(l + 2 * (f - d) + om);
        n = n - 2768 * cos(2 * (l + f - d + om)) + 4 * sin(2 * (l + f - d + om));
        n = n + 3041 * cos(2 * (d - l) + om) - 5 * sin(2 * (d - l) + om);
        n = n + 2695 * cos(2 * (l + f) + om) + 12 * sin(2 * (l + f) + om);
        n = n + 2719 * cos(2 * (f - d) + om - lp) - 3 * sin(2 * (f - d) + om - lp);
        n = n + 2720 * cos(om - 2 * d) - 9 * sin(om - 2 * d);
        n = n - 51 * cos(2 * d - l - lp) - 4 * sin(2 * d - l - lp);
        n = n - 2206 * cos(2 * (l - d) + om) - sin(2 * (l - d) + om);
        n = n - 199 * cos(l + 2 * d) - 2 * sin(l + 2 * d);
        n = n - 1900 * cos(lp + 2 * (f - d) + om) - sin(lp + 2 * (f - d) + om);
        n = n - 41 * cos(l - lp) - 3 * sin(l - lp);
        n = n + 1313 * cos(2 * (f - l + om)) - sin(2 * (f - l + om));
        n = n + 1233 * cos(3 * l + 2 * (f + om)) + 7 * sin(3 * l + 2 * (f + om));
        n = n - 81 * cos(-lp + 2 * d) - 2 * sin(-lp + 2 * d);
        n = n + 1232 * cos(l - lp + 2 * (f + om)) + 4 * sin(l - lp + 2 * (f + om));
        n = n - 20 * cos(d) + 2 * sin(d);
        n = n + 1207 * cos(2 * (f + d + om) - l - lp) + 3 * sin(2 * (f + d + om) - l - lp);
        n = n + 40 * cos(2 * f - l) - 2 * sin(2 * f - l);
        n = n + 1129 * cos(2 * (f + d + om) - lp) + 5 * sin(2 * (f + d + om) - lp);
        n = n + 1266 * cos(om - 2 * l) - 4 * sin(om - 2 * l);
        n = n - 1062 * cos(l + lp + 2 * (f + om)) + 3 * sin(l + lp + 2 * (f + om));
        n = n - 1129 * cos(2 * l + om) + 2 * sin(2 * l + om);
        n = n - 9 * cos(lp + d - l);
        n = n + 35 * cos(l + lp) - 2 * sin(l + lp);
        n = n - 107 * cos(l + 2 * f) - sin(l + 2 * f);
        n = n + 1073 * cos(2 * (f - d) + om - l) - 2 * sin(2 * (f - d) + om - l);
        n = n + 854 * cos(l + 2 * om);
        n = n - 553 * cos(d - l) + 139 * sin(d - l);
        n = n - 710 * cos(2 * (f + om) + d) + 2 * sin(2 * (f + om) + d);
        n = n + 647 * cos(2 * (f + 2 * d + om) - l) + 4 * sin(2 * (f + 2 * d + om) - l);
        n = n - 700 * cos(lp + d + om - l);
        n = n + 672 * cos(2 * (f - lp - d) + om);
        n = n + 663 * cos(l + 2 * (f + d) + om) + 4 * sin(l + 2 * (f + d) + om);
        n = n - 594 * cos(2 * (f - l + d + om)) + 2 * sin(2 * (f - l + d + om));
        n = n - 610 * cos(2 * om - l) - 2 * sin(2 * om - l);
        n = n - 556 * cos(l + lp + 2 * (f - d + om));
        n /= 36000000000.0d;
        return toRadians(n);
    }

    private static double getL(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return toRadians((485868.249036 + 1717915923.2178 * t + 31.8792 * t2 + 0.051635 * t3 - 0.00024470 * t4) / 3600.0);
    }

    private static double getLp(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return toRadians((1287104.79305 + 129596581.0481 * t - 0.5532 * t2 + 0.000136 * t3 - 0.00001149 * t4) / 3600.0);
    }

    private static double getF(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return toRadians((335779.526232 + 1739527262.8478 * t - 12.7512 * t2 - 0.001037 * t3 + 0.00000417 * t4) / 3600.0);
    }

    private static double getD(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return toRadians((1072260.70369 + 1602961601.2090 * t - 6.3706 * t2 + 0.006593 * t3 - 0.00003169 * t4) / 3600.0);
    }

    private static double getOm(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return toRadians((450160.398036 - 6962890.5431 * t + 7.4722 * t2 + 0.007702 * t3 - 0.00005939 * t4) / 3600.0);
    }

    /**
     * 计算黄经章动
     * 
     * @param jd
     *            儒略�?
     * @return 章动的�??(rad)
     */
    public static double getLongitudeNutation(double jd) {
        double t = getJulianCentury(jd);
        double l = getL(t);
        double lp = getLp(t);
        double f = getF(t);
        double d = getD(t);
        double om = getOm(t);
        return getLongitudeNutation(t, l, lp, f, d, om);
    }

    /**
     * 计算交角章动
     * 
     * @param jd
     *            儒略�?
     * @return 章动的�??(rad)
     */
    public static double getObliquityNutation(double jd) {
        double t = getJulianCentury(jd);
        double l = getL(t);
        double lp = getLp(t);
        double f = getF(t);
        double d = getD(t);
        double om = getOm(t);
        return getObliquityNutation(t, l, lp, f, d, om);
    }

    
}
