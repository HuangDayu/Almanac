package com.almanac.lunar;

import com.almanac.lunar.Common;

/***
 * 天文算法
 * @author Administrator
 *
 */
public class AstronomyArithmetic {


	public static double gxc_sunLon(double t) { // 太阳光行差,t是世纪数
		double v = -0.043126 + 628.301955 * t - 0.000002732 * t * t; // 平近点角
		double e = 0.016708634 - 0.000042037 * t - 0.0000001267 * t * t;
		return (-20.49552 * (1 + e * Math.cos(v))) / Common.rad; // 黄经光行差
	}

	/***
	 * 月球经度光行差,误差0.07"
	 * 
	 * @param t
	 * @return
	 */
	public static double gxc_moonLon(double t) {
		return -3.4E-6;
	}

	/**
	 * 地球经度计算,返回Date分点黄经,传入世纪数、取项数
	 * 
	 * @param t
	 * @param n
	 * @return
	 */
	public static double E_Lon(double t, int n) {
		return Common.XL0_calc(0, 0, t, n);
	}

	public static double M_Lon(double t, int n) {
		return Common.XL1_calc(0, t, n);
	}
	
	

	/***
	 * 地球速度,t是世纪数,误差小于万分3
	 * 
	 * @param t
	 * @return
	 */
	public static double E_v(double t) {
		double f = 628.307585 * t;
		double b = 628.332 + 21 * Math.sin(1.527 + f) + 0.44 * Math.sin(1.48 + f * 2) + 0.129 * Math.sin(5.82 + f) * t
				+ 0.00055 * Math.sin(4.21 + f) * t * t;
		return b;
	}

	/***
	 * 月球速度计算,传入世经数
	 * 
	 * @param t
	 * @return
	 */
	public static double M_v(double t) {
		double v = 8399.71 - 914 * Math.sin(0.7848 + 8328.691425 * t + 0.0001523 * t * t); // 误差小于5%
		v -= 179 * Math.sin(2.543 + 15542.7543 * t) // 误差小于0.3%
				+ 160 * Math.sin(0.1874 + 7214.0629 * t) + 62 * Math.sin(3.14 + 16657.3828 * t)
				+ 34 * Math.sin(4.827 + 16866.9323 * t) + 22 * Math.sin(4.9 + 23871.4457 * t)
				+ 12 * Math.sin(2.59 + 14914.4523 * t) + 7 * Math.sin(0.23 + 6585.7609 * t)
				+ 5 * Math.sin(0.9 + 25195.624 * t) + 5 * Math.sin(2.32 - 7700.3895 * t)
				+ 5 * Math.sin(3.88 + 8956.9934 * t) + 5 * Math.sin(0.49 + 7771.3771 * t);
		return v;
	}

	/***
	 * 月日视黄经的差值
	 * 
	 * @param t
	 * @param Mn
	 * @param Sn
	 * @return
	 */
	public static double MS_aLon(double t, int Mn, int Sn) {
		return M_Lon(t, Mn) + gxc_moonLon(t) - (E_Lon(t, Sn) + gxc_sunLon(t) + Math.PI);
	}

	/***
	 * 太阳视黄经
	 * 
	 * @param t
	 * @param n
	 * @return
	 */
	public static double S_aLon(double t, int n) {
		return E_Lon(t, n) + Common.nutationLon2(t) + gxc_sunLon(t) + Math.PI; // 注意，这里的章动计算很耗时
	}
	
	static double Julian_Shijishu; 

	public static double getJulian_Shijishu() {
		return Julian_Shijishu;
	}

	public static void setJulian_Shijishu(double julian_Shijishu) {
		Julian_Shijishu = julian_Shijishu;
	}

	/****
	 * 已知月日视黄经差求时间
	 * 
	 * @param W
	 * @return
	 */
	public static double MS_aLon_t(double W) {
		double t, v = 7771.37714500204;
		t = (W + 1.08472) / v;
		t += (W - MS_aLon(t, 3, 3)) / v;
		setJulian_Shijishu(t);
		v = M_v(t) - E_v(t); // v的精度0.5%，详见原文
		t += (W - MS_aLon(t, 20, 10)) / v;
		t += (W - MS_aLon(t, -1, 60)) / v;
		return t;
	}

	/***
	 * 已知太阳视黄经反求时间
	 * 
	 * @param W
	 * @return
	 */
	public static double S_aLon_t(double W) {
		double t, v = 628.3319653318;
		t = (W - 1.75347 - Math.PI) / v;
		v = E_v(t); // v的精度0.03%，详见原文
		t += (W - S_aLon(t, 10)) / v;
		v = E_v(t); // 再算一次v有助于提高精度,不算也可以
		t += (W - S_aLon(t, -1)) / v;
		return t;
	}

	/***
	 * 已知月日视黄经差求时间,高速低精度,误差不超过600秒(只验算了几千年)
	 * 
	 * @param W
	 * @return
	 */
	public static double MS_aLon_t2(double W) {
		double t, v = 7771.37714500204;
		t = (W + 1.08472) / v;
		double L, t2 = t * t;
		t -= (-0.00003309 * t2 + 0.10976 * Math.cos(0.784758 + 8328.6914246 * t + 0.000152292 * t2)
				+ 0.02224 * Math.cos(0.18740 + 7214.0628654 * t - 0.00021848 * t2)
				- 0.03342 * Math.cos(4.669257 + 628.307585 * t)) / v;
		L = M_Lon(t, 20) - (4.8950632 + 628.3319653318 * t + 0.000005297 * t * t
				+ 0.0334166 * Math.cos(4.669257 + 628.307585 * t)
				+ 0.0002061 * Math.cos(2.67823 + 628.307585 * t) * t + 0.000349 * Math.cos(4.6261 + 1256.61517 * t)
				- 20.5 / Common.rad);
		v = 7771.38 - 914 * Math.sin(0.7848 + 8328.691425 * t + 0.0001523 * t * t)
				- 179 * Math.sin(2.543 + 15542.7543 * t) - 160 * Math.sin(0.1874 + 7214.0629 * t);
		t += (W - L) / v;
		return t;
	}

	/***
	 * 已知太阳视黄经反求时间,高速低精度,最大误差不超过600秒
	 * 
	 * @param W
	 * @return
	 */
	public static double S_aLon_t2(double W) {
		double t, v = 628.3319653318;
		t = (W - 1.75347 - Math.PI) / v;
		t -= (0.000005297 * t * t + 0.0334166 * Math.cos(4.669257 + 628.307585 * t)
				+ 0.0002061 * Math.cos(2.67823 + 628.307585 * t) * t) / v;
		t += (W - E_Lon(t, 8) - Math.PI + (20.5 + 17.2 * Math.sin(2.1824 - 33.75705 * t)) / Common.rad) / v;
		return t;
	}


}
