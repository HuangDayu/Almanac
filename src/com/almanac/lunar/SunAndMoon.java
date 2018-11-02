package com.almanac.lunar;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import com.almanac.lunar.JulianCalendar;
import com.almanac.lunar.SunAndMoon;

public class SunAndMoon {

	private AlmanacBean bean;

	private static double RAD = 180.0 * 3600 / Math.PI;

	private static double midDayTime;

	private static double dawnTime;

	private double moonRise;

	private double moonSet;

	private static String codeStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	/***
	 * 纬度
	 */
	private static double latitude;
	/***
	 * 经度
	 */
	private static double longitude;

	public SunAndMoon(String address, Calendar calendar,AlmanacBean bean) {
		this.bean = bean;
		dailytime(address, calendar);
		getMoonTime(calendar);
	}
	
	public SunAndMoon(TimeBean dataBean,AlmanacBean bean) {
		this(dataBean.getAddress(),dataBean.getCalendar(), bean);
	}

	/***
	 * 经度（正：东经 负：西经） 纬度（正：北纬 负：南纬）
	 * 
	 * @param d
	 * @return
	 */
	public static String setItude(double d, boolean b) {
		if (!b) {
			if (d < 0) {
				return "南纬 ";
			} else {
				return "北纬 ";
			}
		} else {
			if (d < 0) {
				return "西经 ";
			} else {
				return "东经 ";
			}
		}

	}

	/***
	 * 保留4个小数点 改变经纬度格式 :东经 110°16'67"
	 */
	public static String setStringPointDouble(double itude, boolean boo) {
		// 经度（正：东经 负：西经）
		// 纬度（正：北纬 负：南纬）
		BigDecimal big = new BigDecimal(itude);
		// String str0 = String.valueOf(big.setScale(4,
		// BigDecimal.ROUND_HALF_UP).doubleValue());// 转成字符串
		String str0 = String.valueOf(roundByScale(itude, 4));// 转成字符串
		int leng = str0.length();// 获取长度
		String str1 = str0.substring(0, leng - 5);
		String str2 = str0.substring(leng - 4, leng - 2);
		String str3 = str0.substring(leng - 2, leng);// 最后两位
		String str4 = setItude(itude, boo) + str1 + "°" + str2 + "'" + str3 + "\"";
		return str4;
	}

	/**
	 * 将double格式化为指定小数位的String，不足小数位用0补全
	 * 
	 * @param v
	 *            需要格式化的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return
	 */
	public static String roundByScale(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
		}
		if (scale == 0) {
			return new DecimalFormat("0").format(v);
		}
		String formatStr = "0.";
		for (int i = 0; i < scale; i++) {
			formatStr = formatStr + "0";
		}
		return new DecimalFormat(formatStr).format(v);
	}

	/***
	 * 保留两个小数点方法包装
	 */
	public static double getTwoPointDouble(double d) {
		BigDecimal b = new BigDecimal(d);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/***
	 * 获取经度
	 * 
	 * @return double型
	 */
	public static double getDoubleLongitude() {
		return getTwoPointDouble(longitude);
	}

	/**
	 * 获取纬度
	 * 
	 * @return double型
	 */
	public static double getDoubleLatitude() {
		return getTwoPointDouble(latitude);
	}


	/****
	 * 算出日出日落方法
	 * 
	 * @param area
	 *            所在地址
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            时
	 * @param min
	 *            分
	 * @param sec
	 *            秒
	 * @param tz
	 *            时区
	 * @return 返回泛型的日出日落时间
	 */
	public void dailytime(String area, Calendar calendar) {

		double tz = TimeUtil.getTimZoneInt(calendar);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);

		String jwd = decodeJWD(area);

		/***
		 * 纬度
		 */
		latitude = (Double.parseDouble(jwd.substring(0, 2)) + Double.parseDouble(jwd.substring(2, 4)) / 60);
		/***
		 * 经度
		 */
		longitude = (Double.parseDouble(jwd.substring(4, 7)) + Double.parseDouble(jwd.substring(7)) / 60);

		/***
		 * 设置经度
		 */
		bean.setLongitude(setStringPointDouble(longitude, true));
		/***
		 * 设置纬度
		 */
		bean.setLatitude(setStringPointDouble(latitude, false));

		Double wd = latitude / 180 * Math.PI;
		Double jd = -longitude / 180 * Math.PI;

		double richu = getJuLian_old(year, month, day, hour, min, sec) - JulianCalendar.getJuLian_INT(year);// 2451545
		// 2451544.5

		for (int i = 0; i < 10; i++) {
			richu = sunRiseTime(richu, jd, wd, tz / 24);// 逐步逼近法算10次
		}

		// 日出
		bean.setSunRise(doubleToStr(richu));
		// 日落
		bean.setSunSet(doubleToStr(midDayTime + midDayTime - richu));
		// 中天
		bean.setMidTime(doubleToStr(midDayTime));
		// 天亮
		bean.setDawnTime(doubleToStr(dawnTime));
		// 天黑
		bean.setNightTime(doubleToStr(midDayTime + midDayTime - dawnTime));
		// 昼长 = 日落-日出
		bean.setDayTime(doubleToStr((midDayTime - richu) * 2 - 0.5));
		// 夜长
		bean.setEveningTime(doubleToStr(24 - ((midDayTime - richu) * 2 - 0.5)));

	}

	/****
	 * 加密
	 * 
	 * @param encode
	 * @return
	 */
	public static String decodeJWD(String encode) {
		StringBuilder jwd = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			if (2 == i) {
				jwd.append(String.format("%03d", codeStr.indexOf(encode.charAt(i)) + 73));
			} else {
				jwd.append(String.format("%02d", codeStr.indexOf(encode.charAt(i))));
			}
		}
		return jwd.toString();
	}

	/****
	 * 解密
	 * 
	 * @param decode
	 * @return
	 */
	public static String encodeJWD(Integer decode) {
		StringBuilder jwd = new StringBuilder();
		int i = 230811316;
		int ge = i % 100;
		int shi = i % 100000 - ge;
		int bai = i % 10000000 - shi;
		int qian = i % 1000000000 - bai;
		shi = shi / 100 - 73;
		bai = bai / 100000;
		qian = qian / 10000000;
		jwd.append(codeStr.charAt(qian)).append(codeStr.charAt(bai)).append(codeStr.charAt(shi))
				.append(codeStr.charAt(ge));
		return jwd.toString();
	}

	/**
	 * 太阳升起时间
	 * 
	 * @param date
	 *            儒略日平午
	 * @param lo
	 *            地理经度
	 * @param la
	 *            地理纬度
	 * @param tz
	 *            时区
	 * @return 太阳升起时间
	 */
	public static Double sunRiseTime(double date, double lo, double la, double tz) {
		// System.out.println(lo+"--"+la);
		date = date - tz;
		// 太阳黄经以及它的正余弦值
		double t = date / 36525;
		double j = sunHJ(t);
		// 太阳黄经以及它的正余弦值
		double sinJ = Math.sin(j);
		double cosJ = Math.cos(j);
		// 其中2*PI*(0.7790572732640 + 1.00273781191135448*jd)恒星时（子午圈位置）
		double gst = 2 * Math.PI * (0.779057273264 + 1.00273781191135 * date)
				+ (0.014506 + 4612.15739966 * t + 1.39667721 * t * t) / RAD;
		double E = (84381.406 - 46.836769 * t) / RAD; // 黄赤交角
		double a = Math.atan2(sinJ * Math.cos(E), cosJ);// '太阳赤经
		double D = Math.asin(Math.sin(E) * sinJ); // 太阳赤纬
		double cosH0 = (Math.sin(-50 * 60 / RAD) - Math.sin(la) * Math.sin(D)) / (Math.cos(la) * Math.cos(D)); // 日出的时角计算，地平线下50分
		double cosH1 = (Math.sin(-6 * 3600 / RAD) - Math.sin(la) * Math.sin(D)) / (Math.cos(la) * Math.cos(D)); // 天亮的时角计算，地平线下6度，若为航海请改为地平线下12度
		// 严格应当区分极昼极夜，本程序不算
		if (cosH0 >= 1 || cosH0 <= -1) {
			return -0.5;// 极昼
		}
		double H0 = -Math.acos(cosH0); // 升点时角（日出）若去掉负号 就是降点时角，也可以利用中天和升点计算
		double H1 = -Math.acos(cosH1);
		double H = gst - lo - a; // 太阳时角
		midDayTime = date - degree(H) / Math.PI / 2 + tz; // 中天时间
		dawnTime = date - degree(H - H1) / Math.PI / 2 + tz;// 天亮时间
		return date - degree(H - H0) / Math.PI / 2 + tz; // 日出时间，函数返回值
	}

	/**
	 * 保证角度∈(-π,π)
	 * 
	 * @param ag
	 * @return ag
	 */
	public static Double degree(double ag) {
		ag = mod(ag, 2 * Math.PI);
		return ag <= -Math.PI ? ag + 2 * Math.PI : ag > Math.PI ? ag - 2 * Math.PI : ag;
	}

	public static Double mod(double num1, double num2) {
		num2 = Math.abs(num2);
		// 只是取决于Num1的符号
		return num1 >= 0 ? num1 - ((int) (num1 / num2)) * num2
				: ((int) (Math.abs(num1) / num2)) * num2 - Math.abs(num1);
	}

	/**
	 * @param t
	 *            儒略世纪数
	 * @return 太阳黄经
	 */
	public static Double sunHJ(double t) {
		t = t + (32.0 * (t + 1.8) * (t + 1.8) - 20) / 86400.0 / 36525.0;
		// 儒略世纪年数,力学时
		double j = 48950621.66 + 6283319653.318 * t + 53 * t * t - 994 + 334166 * Math.cos(4.669257 + 628.307585 * t)
				+ 3489 * Math.cos(4.6261 + 1256.61517 * t) + 2060.6 * Math.cos(2.67823 + 628.307585 * t) * t;
		return j / 10000000;
	}

	/**
	 * 计算出儒略日 方法1 多出1
	 * 
	 * @param y
	 *            年
	 * @param M
	 *            月
	 * @param d
	 *            日
	 * @param h
	 *            小时
	 * @param m
	 *            分
	 * @param s
	 *            秒
	 * @return int
	 */
	public static int getJuLian_old(int y, int M, int d, int h, int m, int s) {
		double time = 0;
		if (M <= 2) {
			M += 12;
			y -= 1;
		}
		if (y * 372 + M * 31 + d >= 588829) {
			time = (int) (y / 100);
			time = 2 - time + (int) (time / 4);
		}
		time += (int) Math.round(365.25 * (y + 4716) + 0.01) + (int) (30.60001 * (M + 1)) + d
				+ (h * 3600 + m * 60 + s) / (24 * 3600) - 1524.5;
		return (int) Math.round(time);
	}

	/***
	 * 公历转儒略日 方法2 少了0.5
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static double getJuLian_new(int year, int month, double day) {
		int n = 0, G = 0;
		if (year * 372 + month * 31 + Math.floor(day) >= 588829) {
			G = 1; // 判断是否为格里高利历日1582*372+10*31+15
		}
		if (month <= 2) {
			month += 12;
			year--;
		}
		if (G != 0) {
			n = (int) Math.floor(year / 100);
			n = 2 - n + (int) Math.floor(n / 4); // 加百年闰
		}
		return (Math.floor(365.25 * (year + 4716)) + Math.floor(30.6001 * (month + 1)) + day + n - 1524.5) + 0.5;
	}

	/***
	 * 格式化成时间
	 * 
	 * @param time
	 * @return
	 */
	public static String doubleToStr(double time) {
		double t = time + 0.5;
		t = (t - (int) t) * 24;
		int h = (int) t;
		t = (t - h) * 60;
		int m = (int) t;
		t = (t - m) * 60;
		int s = (int) t;
		return h + ":" + m + ":" + s;
	}

	/***
	 * 格式化时间
	 * 
	 * @param sum
	 * @return
	 */
	public static String timeToStr(double sum) {
		if (sum < 0) {
			return "--:--:--";
		}
		int hh = (int) (sum);
		int mm = (int) ((sum - hh) * 60);
		int ss = (int) ((((sum - hh) * 60) - mm) * 60);
		return hh + ":" + mm + ":" + ss;
	}

	/***
	 * 
	 * @param dbLon
	 *            经度
	 * @param dbLat
	 *            纬度
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @param TimeZone
	 *            时区
	 * @param pTime
	 *            PTime对象
	 */
	public void getMoonTime(Calendar calendar) {
		double dbLon = getDoubleLongitude();
		double dbLat = getDoubleLatitude();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		double mjdd = mjd(day, month, year, 0);
		find_moonrise_set(mjdd, TimeUtil.getTimZoneInt(calendar), dbLon, dbLat, 0, 0);
	}

	private void find_moonrise_set(double mjd, double tz, double glong, double glat, int dls, int ST) {
		double sglat, date, ym, yz, utrise = 0, utset = 0, sinho, cglat, xe, ye;
		double yp, nz, hour, z1, z2, rads = 0.0174532925;
		Boolean rise, sett, above;
		double quadout[] = new double[5];

		sinho = Math.sin(rads * 8 / 60); // moonrise taken as centre of moon at +8 arcmin
		sglat = Math.sin(rads * glat);
		cglat = Math.cos(rads * glat);
		date = mjd - tz / 24;
		rise = false;
		sett = false;
		above = false;
		hour = 1.0;
		ym = sin_alt(1, date, hour - 1.0, glong, cglat, sglat) - sinho;
		if (ym > 0.0) {
			above = true;
		}
		while (hour < 25 && (sett == false || rise == false)) {
			yz = sin_alt(1, date, hour, glong, cglat, sglat) - sinho;
			yp = sin_alt(1, date, hour + 1.0, glong, cglat, sglat) - sinho;
			quadout = quad(ym, yz, yp);
			nz = quadout[0];
			z1 = quadout[1];
			z2 = quadout[2];
			xe = quadout[3];
			ye = quadout[4];
			// case when one event is found in the interval
			if (nz == 1) {
				if (ym < 0.0) {
					utrise = hour + z1;
					rise = true;
				} else {
					utset = hour + z1;
					sett = true;
				}
			} // end of nz = 1 case

			// case where two events are found in this interval
			// (rare but whole reason we are not using simple iteration)
			if (nz == 2) {
				if (ye < 0.0) {
					utrise = hour + z2;
					utset = hour + z1;
				} else {
					utrise = hour + z1;
					utset = hour + z2;
				}
			}

			// set up the next search interval
			ym = yp;
			hour += 2.0;
		} // end of while loop

		if (rise == true || sett == true) {
			if (rise == true) {
				if (ST == 0) {
					this.moonRise = utrise + dls;
				} else {
					this.moonRise = lmst(mjd + (utrise - tz) / 24, glong);
				}
			} else {
				this.moonRise = -1;
			}
			if (sett == true) {
				if (ST == 0) {
					this.moonSet = utset + dls;
				} else {
					this.moonSet = lmst(mjd + (utset - tz) / 24, glong);
				}
			} else {
				this.moonSet = -1;
			}
		} else {
			this.moonSet = -2;
		}
		/***
		 * 月出时间
		 * 
		 * @return
		 */
		bean.setMoonRise(timeToStr(moonRise));
		/***
		 * 月落时间
		 * 
		 * @return
		 */
		bean.setMoonSet(timeToStr(moonSet));
		/***
		 * 月中时间
		 * 
		 * @return
		 */
		bean.setMoonMiddleTime(timeToStr((this.moonSet + this.moonRise) / 2));

	}

	private static double lmst(double mjd, double glong) {
		double lst, t, d;
		d = mjd - 51544.5;
		t = d / 36525.0;
		lst = range(280.46061837 + 360.98564736629 * d + 0.000387933 * t * t - t * t * t / 38710000);
		lst = lst / 15.0 + glong / 15.0;
		if (lst >= 24.0) {
			lst -= 24.0;
		}
		return (lst);
	}

	private static double range(double x) {
		// TODO Auto-generated method stub
		double a, b;
		b = x / 360;
		a = 360 * (b - ipart(b));
		if (a < 0) {
			a = a + 360;
		}
		return a;
	}

	private static double ipart(double x) {
		// TODO Auto-generated method stub
		double a;
		if (x > 0) {
			a = Math.floor(x);
		} else {
			a = Math.ceil(x);
		}
		return a;
	}

	private static double[] quad(double ym, double yz, double yp) {
		// TODO Auto-generated method stub
		double a, b, c, dis, dx, xe, ye, z1 = 0, z2 = 0, nz;
		double quadout[] = { 0, 0, 0, 0, 0 };

		nz = 0;
		a = 0.5 * (ym + yp) - yz;
		b = 0.5 * (yp - ym);
		c = yz;
		xe = -b / (2 * a);
		ye = (a * xe + b) * xe + c;
		dis = b * b - 4.0 * a * c;
		if (dis > 0) {
			dx = 0.5 * Math.sqrt(dis) / Math.abs(a);
			z1 = xe - dx;
			z2 = xe + dx;
			if (Math.abs(z1) <= 1.0) {
				nz += 1;
			}
			if (Math.abs(z2) <= 1.0) {
				nz += 1;
			}
			if (z1 < -1.0) {
				z1 = z2;
			}
		}
		quadout[0] = nz;
		quadout[1] = z1;
		quadout[2] = z2;
		quadout[3] = xe;
		quadout[4] = ye;
		return quadout;
	}

	private static double sin_alt(int iobj, double mjd0, double hour, double glong, double cglat, double sglat) {
		// TODO Auto-generated method stub

		double mjd, t, ra, dec, tau, salt, rads = 0.0174532925;
		double objpos[] = { 0, 0 };
		mjd = mjd0 + hour / 24.0;
		t = (mjd - 51544.5) / 36525.0;
		if (iobj == 1) {
			objpos = minimoon(t);
		}
		// else objpos = minisun(t);
		ra = objpos[0];
		dec = objpos[1];
		// hour angle of object
		tau = 15.0 * (lmst(mjd, glong) - ra);
		// sin(alt) of object using the conversion formulas
		salt = sglat * Math.sin(rads * dec) + cglat * Math.cos(rads * dec) * Math.cos(rads * tau);
		return salt;
	}

	private static double[] minimoon(double t) {
		// TODO Auto-generated method stub
		double p2 = 6.283185307, arc = 206264.8062, coseps = 0.91748, sineps = 0.39778;
		double L0, L, LS, F, D, H, S, N, DL, CB, L_moon, B_moon, V, W, X, Y, Z, RHO, dec, ra;
		double mooneq[] = { 0, 0 };

		L0 = frac(0.606433 + 1336.855225 * t); // mean longitude of moon
		L = p2 * frac(0.374897 + 1325.552410 * t); // mean anomaly of Moon
		LS = p2 * frac(0.993133 + 99.997361 * t); // mean anomaly of Sun
		D = p2 * frac(0.827361 + 1236.853086 * t); // difference in longitude of moon and sun
		F = p2 * frac(0.259086 + 1342.227825 * t); // mean argument of latitude

		// corrections to mean longitude in arcsec
		DL = 22640 * Math.sin(L);
		DL += -4586 * Math.sin(L - 2 * D);
		DL += +2370 * Math.sin(2 * D);
		DL += +769 * Math.sin(2 * L);
		DL += -668 * Math.sin(LS);
		DL += -412 * Math.sin(2 * F);
		DL += -212 * Math.sin(2 * L - 2 * D);
		DL += -206 * Math.sin(L + LS - 2 * D);
		DL += +192 * Math.sin(L + 2 * D);
		DL += -165 * Math.sin(LS - 2 * D);
		DL += -125 * Math.sin(D);
		DL += -110 * Math.sin(L + LS);
		DL += +148 * Math.sin(L - LS);
		DL += -55 * Math.sin(2 * F - 2 * D);

		// simplified form of the latitude terms
		S = F + (DL + 412 * Math.sin(2 * F) + 541 * Math.sin(LS)) / arc;
		H = F - 2 * D;
		N = -526 * Math.sin(H);
		N += +44 * Math.sin(L + H);
		N += -31 * Math.sin(-L + H);
		N += -23 * Math.sin(LS + H);
		N += +11 * Math.sin(-LS + H);
		N += -25 * Math.sin(-2 * L + F);
		N += +21 * Math.sin(-L + F);

		// ecliptic long and lat of Moon in rads
		L_moon = p2 * frac(L0 + DL / 1296000);
		B_moon = (18520.0 * Math.sin(S) + N) / arc;

		// equatorial coord conversion - note fixed obliquity
		CB = Math.cos(B_moon);
		X = CB * Math.cos(L_moon);
		V = CB * Math.sin(L_moon);
		W = Math.sin(B_moon);
		Y = coseps * V - sineps * W;
		Z = sineps * V + coseps * W;
		RHO = Math.sqrt(1.0 - Z * Z);
		dec = (360.0 / p2) * Math.atan(Z / RHO);
		ra = (48.0 / p2) * Math.atan(Y / (X + RHO));
		if (ra < 0) {
			ra += 24;
		}
		mooneq[1] = dec;
		mooneq[0] = ra;
		return mooneq;
	}

	private static double frac(double x) {
		// TODO Auto-generated method stub
		x -= (int) x;
		return ((x < 0) ? x + 1.0 : x);
	}

	private static double mjd(int day, int month, int year, int hour) {
		// TODO Auto-generated method stub
		double a, b;
		if (month <= 2) {
			month = month + 12;
			year = year - 1;
		}
		a = 10000.0 * year + 100.0 * month + day;
		if (a <= 15821004.1) {
			b = -2 * Math.floor((year + 4716) / 4) - 1179;
		} else {
			b = Math.floor(year / 400) - Math.floor(year / 100) + Math.floor(year / 4);
		}
		a = 365.0 * year - 679004.0;
		return (a + b + Math.floor(30.6001 * (month + 1)) + day + hour / 24.0);
	}
}
