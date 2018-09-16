package test_5;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static test_5.CalendarUtil.getJulianCentury;
import static test_5.ElpMpp02Constants.*;
import static test_5.MathUtil.mod2Pi;
import static test_5.MathUtil.secondsToRadians;

public class ElpMpp02Util {

    /**
     * D = W1 - T + 180˚
     * 
     * @param t
     *            儒略世纪�?
     * @return D的�??(rad)
     */
    private static double getDu(double t) {
        return getW1u(t) - getTu(t) + PI;
    }

    /**
     * F = W1 - W3
     * 
     * @param t
     *            儒略世纪�?
     * @return F的�??(rad)
     */
    private static double getFu(double t) {
        return getW1u(t) - getW3u(t);
    }

    /**
     * l = W1 - W2
     * 
     * @param t
     *            儒略世纪�?
     * @return l的�??(rad)
     */
    private static double getL(double t) {
        return getW1u(t) - getW2u(t);
    }

    /**
     * lp = T - Omp
     * 
     * @param t
     *            儒略世纪�?
     * @return lp的�??(rad)
     */
    private static double getLp(double t) {
        return getTu(t) - getGomegap(t);
    }

    /**
     * Me(rcury) = 252˚15´03.216919˝ + 538101628.68888˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return Me的�??
     */
    private static double getMeu(double t) {
        return LAMBDA_ME_0 + LAMBDA_ME_1 * t;
    }

    /**
     * V(enus) = 181˚58´44.758419˝ + 210664136.45777˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return V的�??
     */
    private static double getVu(double t) {
        return LAMBDA_V_0 + LAMBDA_V_1 * t;
    }

    /**
     * Ma(rs) = 355˚26´�?03.642778˝ + 68905077.65936˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return Ma的�??
     */
    private static double getMau(double t) {
        return LAMBDA_MA_0 + LAMBDA_MA_1 * t;
    }

    /**
     * J(upiter) = 34˚21´05.379392˝ + 10925660.57335˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return J的�??
     */
    private static double getJu(double t) {
        return LAMBDA_J_0 + LAMBDA_J_1 * t;
    }

    /**
     * S(aturn) = 50˚04´38.902495˝ + 4399609.33632˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return S的�??
     */
    private static double getSu(double t) {
        return LAMBDA_S_0 + LAMBDA_S_1 * t;
    }

    /**
     * U(ranus) = 314˚03´04.354234˝ + 1542482.57845˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return U的�??
     */
    private static double getUu(double t) {
        return LAMBDA_U_0 + LAMBDA_U_1 * t;
    }

    /**
     * N(eptune) = 304˚20´56.808371˝ + 786547.89700˝ * t
     * 
     * @param t
     *            儒略世纪�?
     * @return N的�??
     */
    private static double getNu(double t) {
        return LAMBDA_N_0 + LAMBDA_N_1 * t;
    }

    /**
     * zeta = W1 + (5029.0966˝ - 0.29965˝) * t
     * 
     * @param t
     *            儒略世纪�?
     * @return zeta的�??
     */
    private static double getGzeta(double t) {
        return getW1u(t) + secondsToRadians(P - DELTAU_P) * t;
    }

    /**
     * W1 = 218˚18´59.95571˝ + 1732559343.73604˝ * t - 6.8084˝ * t² + 0.006604˝ * t³ - 0.00003169˝ *
     * t�?
     * 
     * @param t
     *            儒略世纪�?
     * @return W1的�??(rad)
     */
    private static double getW1u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return W1_0 + W1_1 * t + W1_2 * t2 + W1_3 * t3 + W1_4 * t4;
    }

    /**
     * W2 = 83˚21´11.67475˝ + 14643420.3171˝ * t - 38.2631˝ * t² - 0.045047˝ * t³ + 0.00021301˝ * t�?
     * 
     * @param t
     *            儒略世纪�?
     * @return W2的�??(rad)
     */
    private static double getW2u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return W2_0 + W2_1 * t + W2_2 * t2 + W2_3 * t3 + W2_4 * t4;
    }

    /**
     * W3 = 125˚02´40.39816˝ + 6967919.5383˝ * t - 6.3590˝ * t² + 0.007625˝ * t³ + 0.00003586˝ * t�?
     * 
     * @param t
     *            儒略世纪�?
     * @return W3的�??(rad)
     */
    private static double getW3u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return W3_0 + W3_1 * t + W3_2 * t2 + W3_3 * t3 + W3_4 * t4;
    }

    /**
     * T = 100˚27´59.13885˝ + 129597742.2930˝ * t - 0.0202˝ * t² + 0.000009˝ * t³ + 0.00000015˝ * t�?
     * 
     * @param t
     *            儒略世纪�?
     * @return T的�??(rad)
     */
    private static double getTu(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return T_0 + T_1 * t + T_2 * t2 + T_3 * t3 + T_4 * t4;
    }

    /**
     * omegap = 102˚56´14.45766˝ + 1161.24342˝ * t + 0.529265˝ * t² - 0.00011814˝ * t³ +
     * 0.000011379˝ * t�?
     * 
     * @param t
     *            儒略世纪�?
     * @return omegap的�??(rad)
     */
    private static double getGomegap(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return GOMEGAP_0 + GOMEGAP_1 * t + GOMEGAP_2 * t2 + GOMEGAP_3 * t3 + GOMEGAP_4 * t4;
    }

    private static double getMain(double t, Class<? extends Elp_Main> clazz) throws Exception {
        Number[] params = (Number[]) clazz.getField("PARAMS").get(null);
        int lineItems = clazz.getField("LINE_ITEMS").getInt(null);
        double result = 0.0d;
        double du = getDu(t);
        double fu = getFu(t);
        double l = getL(t);
        double lp = getLp(t);
        for (int i = 0; i < params.length; i += lineItems) {
            int i1 = params[i].intValue();
            int i2 = params[i + 1].intValue();
            int i3 = params[i + 2].intValue();
            int i4 = params[i + 3].intValue();
            double aui = params[i + 4].doubleValue();
            double bu1i = params[i + 5].doubleValue();
            double bu2i = params[i + 6].doubleValue();
            double bu3i = params[i + 7].doubleValue();
            double bu4i = params[i + 8].doubleValue();
            double bu5i = params[i + 9].doubleValue();
            if (clazz.getSimpleName().equals(Elp_Main_S3.class.getSimpleName())) {
                double deltaaui = -M * (bu1i + 2 / 3.0 * GALPHA / M * bu5i + 2 / 3.0 * aui / M)
                        * DELTA_GNU / GNU + (bu1i + 2 / 3.0 * GALPHA / M * bu5i) * DELTA_NP / GNU
                        + (bu2i * DELTA_GGAMMA + bu3i * DELTA_E + bu4i * DELTA_EP);
                result += (aui + deltaaui) * (cos(i1 * du + i2 * fu + i3 * l + i4 * lp));
            } else {
                double deltaaui = -M * (bu1i + 2 / 3.0 * GALPHA / M * bu5i) * DELTA_GNU / GNU
                        + (bu1i + 2 / 3.0 * GALPHA / M * bu5i) * DELTA_NP / GNU
                        + (bu2i * DELTA_GGAMMA + bu3i * DELTA_E + bu4i * DELTA_EP);
                result += (aui + deltaaui) * (sin(i1 * du + i2 * fu + i3 * l + i4 * lp));
            }
        }
        return clazz.getSimpleName().equals(Elp_Main_S3.class.getSimpleName()) ? result
                : secondsToRadians(result);
    }

    private static double getPert(double t, Class<? extends Elp_Pert> clazz) throws Exception {
        double t2 = t * t;
        double t3 = t2 * t;
        double[] pow = {1.0, t, t2, t3};
        int lineItems = clazz.getField("LINE_ITEMS").getInt(null);

        double du = getDu(t);
        double fu = getFu(t);
        double l = getL(t);
        double lp = getLp(t);
        double meu = getMeu(t);
        double vu = getVu(t);
        double tu = getTu(t);
        double mau = getMau(t);
        double ju = getJu(t);
        double su = getSu(t);
        double uu = getUu(t);
        double nu = getNu(t);
        double gzeta = getGzeta(t);

        double result = 0.0d;
        for (int n = 0; n < 4; n++) {
            Number[] params = (Number[]) clazz.getField("PARAMS" + n).get(null);
            for (int i = 0; i < params.length; i += lineItems) {
                double sui = params[i].doubleValue();
                double cui = params[i + 1].doubleValue();
                int i1 = params[i + 2].intValue();
                int i2 = params[i + 3].intValue();
                int i3 = params[i + 4].intValue();
                int i4 = params[i + 5].intValue();
                int i5 = params[i + 6].intValue();
                int i6 = params[i + 7].intValue();
                int i7 = params[i + 8].intValue();
                int i8 = params[i + 9].intValue();
                int i9 = params[i + 10].intValue();
                int i10 = params[i + 11].intValue();
                int i11 = params[i + 12].intValue();
                int i12 = params[i + 13].intValue();
                int i13 = params[i + 14].intValue();
                double gphi = i1 * du + i2 * fu + i3 * l + i4 * lp + i5 * meu + i6 * vu + i7 * tu
                        + i8 * mau + i9 * ju + i10 * su + i11 * uu + i12 * nu + i13 * gzeta;
                result += pow[n] * (sui * sin(gphi) + cui * cos(gphi));
            }
        }
        return clazz.getSimpleName().equals(Elp_Pert_S3.class.getSimpleName()) ? result
                : secondsToRadians(result);
    }

    /**
     * 按儒略日计算月球的地心黄�?
     * 
     * @param jd
     *            儒略�?
     * @return 月球的地心黄经，单位是弧�?(rad)
     */
    public static double getEarthEclipticLongitudeForMoon(double jd) {
        double t = getJulianCentury(jd);
        double main = 0.0d;
        double pert = 0.0d;
        try {
            main = getMain(t, Elp_Main_S1.class);
            pert = getPert(t, Elp_Pert_S1.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mod2Pi(main + pert + getW1u(t));
    }
}
