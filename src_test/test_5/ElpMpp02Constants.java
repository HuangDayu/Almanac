package test_5;

import static java.lang.Math.PI;

class ElpMpp02Constants {

    private static final double D2R                 = PI / 180.0;
    private static final double S2R                 = PI / 180.0 / 3600.0;

    static final double         P                   = 5029.0966;
    static final double         DELTAU_P            = -0.29965;
    static final double         GALPHA              = 0.002571881;

    static final double         LAMBDA_ME_0         = (252 + 15 / 60.0 + 3.216919 / 3600.0) * D2R;
    static final double         LAMBDA_ME_1         = 538101628.68888 * S2R;

    static final double         LAMBDA_V_0          = (181 + 58 / 60.0 + 44.758419 / 3600.0) * D2R;
    static final double         LAMBDA_V_1          = 210664136.45777 * S2R;

    static final double         LAMBDA_MA_0         = (355 + 26 / 60.0 + 3.642778 / 3600.0) * D2R;
    static final double         LAMBDA_MA_1         = 68905077.65936 * S2R;

    static final double         LAMBDA_J_0          = (34 + 21 / 60.0 + 5.379392 / 3600.0) * D2R;
    static final double         LAMBDA_J_1          = 10925660.57335 * S2R;

    static final double         LAMBDA_S_0          = (50 + 4 / 60.0 + 38.902495 / 3600.0) * D2R;
    static final double         LAMBDA_S_1          = 4399609.33632 * S2R;

    static final double         LAMBDA_U_0          = (314 + 3 / 60.0 + 4.354234 / 3600.0) * D2R;
    static final double         LAMBDA_U_1          = 1542482.57845 * S2R;

    static final double         LAMBDA_N_0          = (304 + 20 / 60.0 + 56.808371 / 3600.0) * D2R;
    static final double         LAMBDA_N_1          = 786547.89700 * S2R;

    static final double         DELTAU_W1_0_DE      = -0.07008;
    static final double         DELTAU_W2_0_DE      = +0.20794;
    static final double         DELTAU_W3_0_DE      = -0.07215;
    static final double         DELTAU_W1_1_DE      = -0.35106;
    static final double         DELTAU_NU_DE        = DELTAU_W1_1_DE;
    static final double         DELTAU_W2_1_DE      = +0.08017;
    static final double         DELTAU_W3_1_DE      = -0.04317;
    static final double         DELTAU_W1_2_DE      = -0.03743;
    static final double         DELTAU_GGAMMA_DE    = +0.00085;
    static final double         DELTAU_E_DE         = -0.00006;
    static final double         DELTAU_T_0_DE       = -0.00033;
    static final double         DELTAU_T_1_DE       = +0.00732;
    static final double         DELTAU_NP_DE        = DELTAU_T_1_DE;
    static final double         DELTAU_GOMEGAP_0_DE = -0.00749;
    static final double         DELTAU_EP_DE        = +0.00224;

    static final double         BP_21               = +0.311079095;
    static final double         BP_22               = -0.004482398;
    static final double         BP_23               = -0.001102485;
    static final double         BP_24               = +0.001056062;
    static final double         BP_25               = +0.000050928;

    static final double         BP_31               = -0.103837907;
    static final double         BP_32               = +0.000668287;
    static final double         BP_33               = -0.001298072;
    static final double         BP_34               = -0.000178028;
    static final double         BP_35               = -0.000037342;

    static final double         DELTAU_W1_3         = -0.00018865;
    static final double         DELTAU_W1_4         = -0.00001024;
    static final double         DELTAU_W2_2         = +0.00470602;
    static final double         DELTAU_W2_3         = -0.00025213;
    static final double         DELTAU_W3_2         = -0.00261070;
    static final double         DELTAU_W3_3         = -0.00010712;

    static final double         T_0                 = (100 + 27 / 60.0 + (59.13885 + DELTAU_T_0_DE) / 3600.0)
                                                            * D2R;
    static final double         T_1                 = (129597742.2930 + DELTAU_T_1_DE) * S2R;
    static final double         T_2                 = -0.0202 * S2R;
    static final double         T_3                 = 0.000009 * S2R;
    static final double         T_4                 = 0.00000015 * S2R;

    static final double         NP                  = T_1;

    static final double         W1_0                = (218 + 18 / 60.0 + (59.95571 + DELTAU_W1_0_DE) / 3600.0)
                                                            * D2R;
    static final double         W1_1                = (1732559343.73604 + DELTAU_W1_1_DE) * S2R;
    static final double         GNU                 = W1_1;
    static final double         M                   = NP / GNU;
    static final double         W1_2                = (-6.8084 + DELTAU_W1_2_DE) * S2R;
    static final double         W1_3                = (0.006604 + DELTAU_W1_3) * S2R;
    static final double         W1_4                = (-0.00003169 + DELTAU_W1_4) * S2R;

    static final double         W2_0                = (83 + 21 / 60.0 + (11.67475 + DELTAU_W2_0_DE) / 3600.0)
                                                            * D2R;
    private static final double W21                 = (14643420.3171 + DELTAU_W2_1_DE) * S2R;
    static final double         W2_1                = W21
                                                            + ((W21 / GNU - M
                                                                    * (BP_21 + 2 / 3.0 * GALPHA / M
                                                                            * BP_25))
                                                                    * DELTAU_W1_1_DE
                                                                    + (BP_21 + 2 / 3.0 * GALPHA / M
                                                                            * BP_25)
                                                                    * DELTAU_T_1_DE + GNU
                                                                    * (BP_22 * DELTAU_GGAMMA_DE
                                                                            + BP_23 * DELTAU_E_DE + BP_24
                                                                            * DELTAU_EP_DE)) * S2R;
    static final double         W2_2                = (-38.2631 + DELTAU_W2_2) * S2R;
    static final double         W2_3                = (-0.045047 + DELTAU_W2_3) * S2R;
    static final double         W2_4                = 0.00021301 * S2R;

    static final double         W3_0                = (125 + 2 / 60.0 + (40.39816 + DELTAU_W3_0_DE) / 3600.0)
                                                            * D2R;
    private static final double W31                 = (-6967919.5383 + DELTAU_W3_1_DE) * S2R;
    static final double         W3_1                = W31
                                                            + ((W31 / GNU - M
                                                                    * (BP_31 + 2 / 3.0 * GALPHA / M
                                                                            * BP_35))
                                                                    * DELTAU_W1_1_DE
                                                                    + (BP_31 + 2 / 3.0 * GALPHA / M
                                                                            * BP_35)
                                                                    * DELTAU_T_1_DE + GNU
                                                                    * (BP_32 * DELTAU_GGAMMA_DE
                                                                            + BP_33 * DELTAU_E_DE + BP_34
                                                                            * DELTAU_EP_DE)) * S2R;
    static final double         W3_2                = (6.3590 + DELTAU_W3_2) * S2R;
    static final double         W3_3                = (0.007625 + DELTAU_W3_3) * S2R;
    static final double         W3_4                = -0.00003586 * S2R;

    static final double         DELTA_GNU           = (0.55604 + DELTAU_W1_1_DE) * S2R / W1_1;
    static final double         DELTA_GGAMMA        = (-0.08066 + DELTAU_GGAMMA_DE) * S2R;
    static final double         DELTA_E             = (0.01789 + DELTAU_E_DE) * S2R;
    static final double         DELTA_EP            = (-0.12879 + DELTAU_EP_DE) * S2R;
    static final double         DELTA_NP            = (-0.0642 + DELTAU_NP_DE) * S2R / W1_1;

    static final double         GOMEGAP_0           = (102 + 56 / 60.0 + (14.45766 + DELTAU_GOMEGAP_0_DE) / 3600.0)
                                                            * D2R;
    static final double         GOMEGAP_1           = 1161.24342 * S2R;
    static final double         GOMEGAP_2           = 0.529265 * S2R;
    static final double         GOMEGAP_3           = -0.00011814 * S2R;
    static final double         GOMEGAP_4           = 0.000011379 * S2R;
}
