package test_0;
public class TestSolarTermMain {

	public static void main(String[] argv) throws Exception {
		double jd = 2458049;
		SolarTerm st = new SolarTerm();
		for (int i = 0; i < 24; i++) {
			System.out.println(st.jqB[i] +MyBazi.df.format(MyBazi.getjq(2017)[i]));
			//st.paiYue(2017);
		}
			
		System.out.println("至今："+st.day1_To_day2("20171026","20171017"));
		
		System.out.println("黄赤交角："+st.hcjj1(jd));
		
		double[] d = st.earCal(jd);
		for (int i = 0; i < d.length; i++) {
			System.out.println(d[i]);
		}
		
		double[] e = st.sunCal2(jd);
		for (int i = 0; i < e.length; i++) {
			System.out.println("太阳的地心视黄经及黄纬"+e[i]);
		}
		
		
		double[] f = st.moonCal2(jd);
		for (int i = 0; i < f.length; i++) {
			System.out.println("月球的地心视黄经及视黄纬"+f[i]);
		}
		
		System.out.println(2%100);
		System.out.println(Integer.valueOf("000"));
	}
}
