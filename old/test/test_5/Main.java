package test_5;

import static test_5.CalendarUtil.fromJulianDate;
import static test_5.CalendarUtil.getJulianCentury;

import java.util.ArrayList;
import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		int[] years = { 1, 4, 100, 1900, 400, 2000, 2008, 0, -3, -200, -400 };
		for (int y : years) {
			System.out.println("是否闰年："+y + ": " + CalendarUtil.isLeapYear(y));
		}
		System.out.println(CalendarUtil.toJulianDate(2000, 1, 1));
		System.out.println(CalendarUtil.toJulianDateInGregorian(2000, 1, 1));
		System.out.println(CalendarUtil.toJulianDateInJulian(2000, 1, 1));
		
		System.out.println(CalendarUtil.toJulianDate(1582, 10, 4));
		System.out.println(CalendarUtil.toJulianDateInGregorian(1582, 10, 4));
		System.out.println(CalendarUtil.toJulianDateInJulian(1582, 10, 4));
		
		System.out.println(CalendarUtil.toJulianDate(1582, 10, 10));
		System.out.println(CalendarUtil.toJulianDateInGregorian(1582, 10, 10));
		System.out.println(CalendarUtil.toJulianDateInJulian(1582, 10, 10));
		
		System.out.println("星期："+CalendarUtil.getWeekday(2017, 10, 22));
		
		System.out.println(CalendarUtil.toJulianDate(2014, 8, 12, 0, 0, 0.0d));
		System.out.println(CalendarUtil.toJulianDate(1599, 12, 29, 12, 0, 0.0d));
		

	    System.out.println("月球的地心黄经"+Math.toDegrees(Elp82Util.getEarthEclipticLongitudeForMoon(2458049))+"(rad)");
		
        System.out.println("月球的地心黄经"+ElpMpp02Util.getEarthEclipticLongitudeForMoon(2458049)+"(rad)");

        
        lau1980();
        lau2008b();
        newMoon();//日月地合朔时间
        st();//节气时间
        vsop87();
	}
	
	 public static void vsop87() {
	        double jd = CalendarUtil.toJulianDate(2017, 10, 22, 17, 25,55);
	        double l = Vsop87dEarthUtil.getSunEclipticLongitudeForEarth(jd);
	        double b = Vsop87dEarthUtil.getSunEclipticLatitudeForEarth(jd);
	        double r = Vsop87dEarthUtil.getSunRadiusForEarth(jd);
	        double dl = Vsop87dEarthUtil.vsop2Fk5LongitudeCorrection(l, b, jd);
	        double db = Vsop87dEarthUtil.vsop2Fk5LatitudeCorrection(l, b, jd);
	        System.out.println("计算儒略日数"+jd);
	        System.out.println("地球的日心黄经"+l);
	        System.out.println("地球的日心黄纬"+b);
	        System.out.println("计算地球和太阳的距离"+r+"vsop87()");
	        System.out.println(dl);
	        System.out.println(db);
	    }
	
	public static void st() {
        for (SolarTerms term : SolarTerms.values()) {
            double jd = SolarTermsCalculator.getJulianDayInYearForTermOrder(term, 2017);
            jd -= CalendarUtil.getDeltaT(jd) / 86400; // 由TT转换成UTC
            Calendar cal = fromJulianDate(jd + 8.0 / 24.0); // 东8区
            System.out.println(term.getName()
                    + ": "
                    + String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d", cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE),
                            cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                            cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)));
        }
    }
	
	public static void newMoon() {
	        for (int month = 1; month <= 12; month++) {
	            ArrayList<Double> jds = NewMoonCalculator.getJulianDayInYearAndMonthForNewMoon(2017, 10);
	            for (double jd : jds) {
	                Calendar cal = fromJulianDate(jd);
	                System.out.println(String.format("%04d-%02d-%02d %02d:%02d:%02d.%03d",
	                        cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE),
	                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
	                        cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)));
	            }
	        }
	}
	
	public static void lau1980() {
		double jd = CalendarUtil.toJulianDate(2014, 5, 5, 5, 5, 5);
        double t = getJulianCentury(jd);
        double ln = Iau1980Util.getLongitudeNutation(jd);
        System.out.println(jd);
        System.out.println(t);
        System.out.println(Math.toDegrees(ln));
        System.out.println("计算黄经章动"+Math.toDegrees(ln) * 3600);
	}
	
	public static void lau2008b() {
        double jd = CalendarUtil.toJulianDate(1987, 4, 10, 0, 0, 0.0);
        double t = getJulianCentury(jd);
        double ln = Iau2000BUtil.getLongitudeNutation(jd);
        double on = Iau2000BUtil.getObliquityNutation(jd);
        System.out.println(jd);
        System.out.println(t);
        System.out.println("计算黄经章动"+Math.toDegrees(ln));
        System.out.println(Math.toDegrees(ln) * 3600);
        System.out.println(Math.toDegrees(on));
        System.out.println(Math.toDegrees(on) * 3600);
    }

}
