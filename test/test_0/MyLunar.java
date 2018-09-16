package test_0;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

/**
 * �ṩһЩũ�������Ϣ
 * 
 */
public class MyLunar {

	private final static int[] lunarInfo = { 0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0,
			0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0,
			0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf,
			0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f, 0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
			0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6,
			0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5, 0x4ad0, 0xa4d0,
			0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6, 0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40,
			0xaf46, 0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
			0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0, 0xbaa4,
			0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930, 0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6,
			0xa4e0, 0xd260, 0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520,
			0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
			0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef, 0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0,
			0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4,
			0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968,
			0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520 };
	private final static int[] solarTermInfo = { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
			218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532,
			504758 };
	public final static String[] Tianan = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] Deqi = { "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��" };
	public final static String[] Animals = { "��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] solarTerm = { "С��", "��", "����", "��ˮ", "����", "����", "����", "����", "����", "С��", "â��", "����",
			"С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ", "��ѩ", "����" };
	public final static String[] lunarString1 = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��" };
	public final static String[] lunarString2 = { "��", "ʮ", "إ", "ئ", "��", "��", "��", "��" };
	public final static String[] lunarString3 = { "��", "��", "�q", "��", "��", "�", "��", "��", "��", "ʲ", "��", "��" };
	public final static String[] lunarString4 = { "ˮƿ�� ", "˫����", "������", "��ţ��", "˫����", "��з��", "ʨ����", "��Ů��", "�����", "��Ы��",
			"������", "Ħ����" };
	public final static String[] lunarString5 = { "�� ", "һ", "��", "��", "��", "��", "��" };
	public final static String[] lunarString6 = { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ", "ʮ��" };
	/**
	 * �������� *��ʾ�ż���
	 */
	private final static String[] sFtv = { "0101*Ԫ��", "0115 Ԫ����", "0214 ���˽�", "0308 ��Ů��", "0312 ֲ����", "0315 315",
			"0401 ���˽�", "0501*�Ͷ���", "0504 �����", "0509 ��ά��", "0512 ��ʿ��", "0601 ��ͯ��", "0701 ��ۻع�", "0801 ������",
			"0816 �������", "0909 ë������", "0910 ��ʦ��", "0928 ���ӵ���", "1001*�����", "1006 ���˽�", "1024 ���Ϲ���", "1111 �����",
			"1112 ����ɽ����", "1220 ���Żع�", "1225 ʥ����", "1226 ë�󶫵���" };
	/**
	 * ũ������ *��ʾ�ż���
	 */
	private final static String[] lFtv = { "0101*����", "0106 �����", "0115 Ԫ����", "0505 �����", "0707 ��Ϧ", "0715 ��Ԫ��",
			"0808 ���׽�", "0815 �����", "0909 ������", "1208 ���˽�", "1224 С��", "0100*��Ϧ" };
	/**
	 * ĳ�µĵڼ������ڼ�
	 */
	private static String[] wFtv = { "0520 ĸ�׽�", "0716 ������", "0730 ��ū�۹�����" };

	private static final String[] my_Family_Birthday = { "�������տ���", "ĸ�����տ���", "ɺ�����տ���", "�������տ���", "�������տ���" };

	public static final String[] get_the_24_solarterms = {
			"��������ָ������̫���ƾ�Ϊ315�ȡ��Ƕ�ʮ�ĸ�������ͷһ���������京���ǿ�ʼ���봺�죬���������ݣ�Ʒ��Դ�����"
					+ "�������������︴������������һ���ļ��Ӵ˿�ʼ�ˡ�һ�򶫷�ⶳ�������س�ʼ���������츺����˵���Ƕ�����ů����ؿ�ʼ�ⶳ���������պ�"
					+ "�ؾӵĳ��������ڶ������ѣ��ٹ����գ�����ı���ʼ�ܻ����㿪ʼ��ˮ�����ζ�����ʱˮ���ϻ���û��ȫ�ܽ�����Ƭ����ͬ���㸺��һ�㸡��ˮ�档",
			"��ˮ����ָ�ɡ�̫���ƾ�Ϊ330�㡣��ʱ����鴵����ѩ�ڻ�������ʪ����ˮ���࣬���Խ���ˮ�����ǳ�˵���������콥ů����ˮ�ͷ�æ����"
					+ "һ��̡���㣻����������������ľ��Ȱ���˽�����ˮ̡��ʼ�����ˣ�������ڰ�����ͬ�ȼ���ʳ�����ӣ�������󣬴��㿪ʼ���Ϸ��ɻر������ٹ����죬"
					+ "�ڡ�����ϸ�������Ĵ����У���ľ��������������ڶ���ʼ�����ѿ���Ӵˣ���ؽ�����ʼ���ֳ�һ���������ٵľ���",
			"���ݣ���ָ����̫���ƾ�Ϊ345�㡣���������ʾ���������Ժ�����תů�����׿�ʼ���죬�ݷ���������ĸ��ֶ��߶��ｫ���ѹ�����ʼ����������Խо��ݡ�"
					+ "���ʱ�ڹ����ĳ�����ҲҪ��ʼ�������ҹ����ֵ��������˴������ڡ������ƣ������ݹ���ů�ͺͣ�����Ͻǳ�ɽ�衣��������һ���������ֵ���ͨ����������û������������������������"
					+ "һ����ʼ��������ָ�����𿣩��������ӥ��Ϊ娛���������һ��졢��ף���ݺ���С��������ʱ�ڣ��󲿷ֵ������ѽ����˴������������ݷ��������ж��ߵĸ��������ʱ��"
					+ "��ʱ�����ĳ���ҲҪ��ʼ�ѻ����ɴ˿ɼ������Ƿ�ӳ��Ȼ��������һ��������",
			"���֣���ָ�ɡ�̫���ƾ�Ϊ0�㡣������̫���ڳ���Ϸ������Ǵ���90����зֵ㣬��һ���ϱ���������ҹ��ȣ����Խд��֡������Ժ�̫��ֱ��λ�ñ����ƣ��������糤ҹ�̡�"
					+ "���Դ����Ǳ����򴺼���ʼ���ҹ��󲿷ֵ���Խ��������봺�������׶Ρ�����ũ���У���������ǰ�����׶�Ǯ�����㶫���������ּ��������࣬�ķּ��ӻ����족���Ĵ���������������Ҽ�æ�����ֹ϶����������������"
					+ "�������ֲˣ�����ժ�ϡ������ϣ��������������ֶ�������������⡱�����գ���" + "һ��Ԫ�������������˷���������ʼ�硣��˵�����պ����ӱ���Ϸ������ˣ�����ʱ��ձ�Ҫ���ײ��������硣",
			"��������ָ����̫���ƾ�Ϊ15�㡣��ʱ������ˬ��ů����ľʼ����֦ѿ�����￪ʼ������ũ��æ�ڴ������֡���ǰ������������һ�죬��Щ�˼Ҷ����ſڲ�������������������̤�࣬��ɨ��Ĺ�����ǹ��ϵ�ϰ�ס�"
					+ "һ��ͩʼ������������Ϊ�ƣ������ʼ������˼�������ʱ�����ǰ�ͩ�����ţ�����ϲ�������󲻼��ˣ�ȫ�ص��˵��µĶ��У�Ȼ����������տ��Լ����ʺ��ˡ�",
			"���꣺��ָ�̫���ƾ�Ϊ30�㡣������ˮ����ȵ���˼��������ˮ��������ȵ������������ԣ�������ǡ������ٹȡ������ơ�����ǰ���ֹ��ֶ�����"
					+ "һ��Ƽʼ��������������������Ϊ���ν���ɣ����˵������������࣬��Ƽ��ʼ���������Ų�����㿪ʼ�������ǲ����ˣ�Ȼ����ɣ���Ͽ�ʼ������ʤ��",
			"���ģ���ָ���ϡ�̫���ƾ�Ϊ45�㡣���ļ��Ŀ�ʼ���Ӵ˽������죬������ʢ��ϰ���ϰ����ĵ����������������ߣ�����٣��������࣬ũ�����������������һ������Ҫ������"
					+ "һ����������������������������������˵��һ���������ȿ��������ۣ��������������������ؽ�����һ˵�������������Ŵ���ϱ�ɿ�����������Ȼ�����ϵ����ٿ�ʼ��������������",
			"С������ָ�ס�̫���ƾ�Ϊ60�㡣��С����ʼ�����󡢶�С�����������Ѿ��������������������δ���죬���Խ�С����"
					+ "һ�����㣻�����Ҳ�������������������˵С�������У�����Ѿ�֦Ҷ��ï����ϲ����һЩ֦��ϸ��Ĳ�����ǿ�ҵ������¿�ʼ��������ʱ���ӿ�ʼ���졣",
			"â�֣���ָ�򼺡�̫���ƾ�Ϊ75�㡣��ʱ���ʺϲ�����â�Ĺ����������ȡ����ȡ���������ʱ��������â������Ͳ��ó����ˡ�ͬʱ����â��ָ��â������С�󡢴���ȣ����֡�ָ���ӡ�â�ּ�����С�����â������졣"
					+ "â��ǰ���ҹ��в��ĳ����С����ε������������࣬�������ߣ��������������÷�꼾�ڣ������ǳ���ʪ�������쳣���ȣ��������ߺ��������׷�ù���������ҹ������С����ε���Ҳ�С�ù�ꡱ��"
					+ "һ���������������ʼ��������������������һ�����У������ȥ���������������ܵ������������ƿ�����С��룻ϲ���Ĳ�����ʼ��֦ͷ���֣����Ҹ�������������෴���ܹ�ѧϰ���������еķ�����ȴ���Ӧ���������ĳ��ֶ�ֹͣ�����С�",
			"��������ָ���ҡ�̫���ƾ�Ϊ90�㡣̫���ڻƾ�90�㡰�����㡱ʱ�����⼸��ֱ�䱱�ع����Ͽգ�����������̫����ߡ���һ���Ǳ�������������ҹ��̵�һ�죬����һ���𣬽������ȼ��ڣ���������ڴ�ʱ��������ʢ��"
					+ "���Թ�ʱ���ְ���һ������ձ�������˼��̫�����е����һ�ա�����������̫���������ƶ������������һ���һ�����̣���ҹһ���һ��ӳ���"
					+ "һ��¹�ǽ⣻�����ʼ�������������������¹����ͬ�ƣ���������Ϊ������һ����һ������¹�Ľǳ�ǰ��������������������������������ʼ˥���������Ե�¹�Ǳ�آʼ���䡣�����������������ڶ����սǲ����䣻���Ե�֪�����������������֮������������"
					+ "������һ��ϲ����ҩ�ݣ��������ĵ�����ػ�ˮ���г������Ե������ɴ˿ɼ��������ȵ����ģ�һЩϲ�������￪ʼ���֣������Ե�����ȴ��ʼ˥���ˡ�",
			"С���ָ����̫���ƾ�Ϊ105�㡣�����Ѿ������ˣ������������ȵ�ʱ�����Խ�С���ʱ�����ǳ���ǰ��" + "һ���·�����������������ӥʼ𺡣С��ʱ�ڴ���ϱ㲻����һ˿���磬�������еķ��ж��������ˡ�",
			"�����ָ����̫���ƾ�Ϊ120�㡣������һ�������ȵĽ�������ֵ�ڶ���ǰ�󣬳�����������ط�����������40�����������Ҫ���÷���¹��������������ˮ�࣬�ڡ�С�����������󡱵����Ҫע���Ѵ���ԡ�"
					+ "һ�򸯲�Ϊө��������������������ʱ�С�����ө���Լ�ж�ǧ���֣���ˮ����½�����֣�½����ө�������ڿݲ��ϣ�����ʱ��ө����ѻ����������Թ�����Ϊө����Ǹ��ݱ�ɵģ��ڶ�����˵������ʼ������ȣ�����Ҳ�ܳ�ʪ����������˵ʱ���д���������֣������ʹ��ʪ������������ʼ��������ɡ�",
			"�����ָ�����ϡ�̫���ƾ�Ϊ135�㡣����һ�������쿪ʼ�������ˬ���������塣�˺��������������½�"
					+ "һ���������������¶�������򺮲�������˵������󣬹η�ʱ���ǻ�о�����ˬ����ʱ�ķ��Ѳ�ͬ�������е��ȷ磻���ţ�������糿������������������������������ĺ���Ҳ��ʼ���С�",
			"�����ָ�졣̫���ƾ�Ϊ150�㡣��ʱ�ļ������Ѿ���ͷ�ˡ�������Ҫɢ�ˡ������¶��½���һ��ת�۵㡣�������������������ʾ������ֹ��"
					+ "һ��ӥ�˼��񣻶������ʼ�ࣻ������˵ǡ��˽�������ӥ��ʼ�����������ࣻ��ؼ����￪ʼ���㣻�����˵ǡ��ġ��̡�ָ������𢡢��������ũ������ܳƣ����ǡ����������˼��",
			"��¶����ָ�̫���ƾ�Ϊ165�㡣����ת��������ˮ����¶��" + "һ�������������Ԫ��飻����Ⱥ�����ߡ�˵�˽������Ǻ��������ӵȺ����Ϸɱܺ�������ʼ����ɹ���ʳ�Ա��������ɼ���¶ʵ����������ת����������",
			"��֣���ָ�ѡ�̫���ƾ�Ϊ180�㡣�����һ��ͬ����һ�������⼸��ֱ��������ҹ������ȡ�����һ��������ֱ��λ�ü����ɳ�����ϰ������ƣ�������ʼ���ҹ�������ҹ��������＾�ۣ���һ��պ����＾��ʮ���һ�룬�������֡���������ѧ�Ϲ涨��������������Ǵ���ֿ�ʼ�ġ�"
					+ "һ����ʼ�����������ݳ�����������ˮʼ�ԡ�������Ϊ������Ϊ����ʢ����������ֺ�������ʼ��ʢ�����Բ��ٴ����ˡ�",
			"��¶����ָ�ס�̫���ƾ�Ϊ195�㡣��¶������ת������ʼ����¶ˮ�����˺�¶����¶ˮ�ն࣬�����¸����ˡ����ԣ�����˵������¶֮�����Ȱ׶��󺮣���������ת�����˼����ˮ�������ɰ�ɫ¶�顣"
					+ "һ���������������ȸ�˴�ˮΪ��������лƻ����˽����к����ų�һ�ֻ������εĶ��д����Ǩ�������캮��ȸ�񶼲����ˣ����˿�������ͻȻ���ֺܶ���ۣ����ұ��ǵ����Ƽ���ɫ��ȸ������ƣ����Ա���Ϊ��ȸ���ɵģ�������ġ���ʼ�ƻ�����˵�ڴ�ʱ�ջ����ձ鿪�š�",
			"˪������ָ�硣̫���ƾ�Ϊ210�㡣�������䣬��ʼ��˪���ˣ����Խ�˪����"
					+ "һ����˼��ޣ� �˽����в��ǽ�����������ȳ��к���ʳ�ã������ľ���䣻����ϵ���Ҷ�ݻƵ��䣻�����س��̸����س�Ҳȫ�ڶ��в�����ʳ������ͷ�����붬��״̬�С�",
			"��������ָǬ��̫���ƾ�Ϊ225�㡣ϰ���ϣ��ҹ��������һ�쵱�������Ŀ�ʼ��������Ϊ����֮�⣬��ָһ��������������ˣ������ո�֮��Ҫ�ղ���������˼������һ�����ҹ��ƺ��С����ε�������������ҹ�����ũ�񶼽�½����ת��ũ��ˮ���������������ũ�»�С�"
					+ "һ��ˮʼ���������ʼ�����������˴�ˮΪ�ס��˽���ˮ�Ѿ��ܽ�ɱ�������Ҳ��ʼ���᣻�������˴�ˮΪ�ס��е�����ָҰ��һ��Ĵ�����Ϊ���������Ұ��һ��Ĵ���㲻����ˣ�������ȴ���Կ��������Ұ������������ɫ���ƵĴ�����Թ�����Ϊ������������ɴ���ˡ�",
			"Сѩ����ָ����̫���ƾ�Ϊ240�㡣�����½�����ʼ��ѩ������������ѩ�׷ɵ�ʱ�ڣ����Խ�Сѩ��Сѩǰ�󣬻ƺ�����ʼ��ѩ���Ϸ���ѩ��Ҫ�����������������������ѽ���ⶳ���ڡ�"
					+ "һ���ز����������������������½�������������ɶ�����������е��������������е������½���������ز�ͨ��������������������ʧȥ��������ر�����ת���Ϻ��Ķ��졣",
			"��ѩ����ָ�̫���ƾ�Ϊ255�㡣��ѩǰ�󣬻ƺ�����һ�����л�ѩ�������������ǡ�ǧ����⣬����ѩƮ�����϶��ˡ�"
					+ "һ���Ÿ���أ�����ʼ����������ͦ��������˵��ʱ���������䣬������Ҳ�����ؽ��ˣ����ڴ�ʱ��������ʢʱ�ڣ�����νʢ����˥�������������ȶ��������ϻ���ʼ����ż��Ϊ������ͦ��Ϊ���ݵ�һ�֣�Ҳ�е��������ȶ��������ѿ��",
			"��������ָ�ӡ�̫���ƾ�Ϊ270�㡣������һ�죬���⼸��ֱ���ϻع��ߣ����Ǳ����������̣���ҹ�����ʼ�������ź��졣����ѧ�Ϲ涨��һ���Ǳ����򶬼��Ŀ�ʼ���������Ժ�����ֱ��λ�������ƶ���������İ�����𽥳��ˣ����ƣ����˶����棬һ�쳤һ�ߡ�"
					+ "һ�����᣻�����ӽǽ⣻����ˮȪ������˵�������������������ʱ����������������������Ȼʮ��ǿʢ�����е������Ȼ���������壻����¹ͬ�ƣ�ȴ������ͬ��������Ϊ�ӵĽǳ�����������Ϊ����������һ�������Ӹ��������˶���ǣ������������������Դ�ʱɽ�е�Ȫˮ���������������ȡ�",
			"С������ָ�ӣ�̫���ƾ�Ϊ285�㡣С���Ժ󣬿�ʼ���뺮�伾�ڡ��������ö�����С�����������䵫��û�е��������˼��"
					+ "һ���㱱�磬����ȵʼ����������ʼ𶡣������Ϊ�����д�����˳������Ǩ�ƣ���ʱ�����Ѷ������Դ��㿪ʼ��Ǩ�ƣ���ʱ���������ɼ���ϲȵ�����Ҹо�����������ʼ��������������𶡱�ġ�𶡱Ϊ���е���˼�����ڽӽ��ľ�ʱ������������������С�",
			"�󺮣���ָ��̫���ƾ�Ϊ300�㡣�󺮾����������䵽�˼������˼����ǰ����һ��������ļ��ڡ�����ֵ���Ÿչ����ľ�֮�������ƣ��������ľű����ߡ���"
					+ "һ���飻������������������ˮ�󸹼ᡣ����˵���󺮽�������Է�С���ˣ���ӥ��֮�������ȴ�����ڲ�ʳ������ǿ��״̬�У������ڿ��е���Ѱ��ʳ��Բ�����������������Ϻ�����һ�����������ڣ�ˮ���еı�һֱ����ˮ���룬�����ʵ�����" };

	private static int toInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	private final static Pattern sFreg = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
	private final static Pattern wFreg = Pattern.compile("^(\\d{2})(\\d)(\\d)([\\s\\*])(.+)$");

	private synchronized void findFestival() {
		int sM = this.getSolarMonth();
		int sD = this.getSolarDay();
		int lM = this.getLunarMonth();
		int lD = this.getLunarDay();
		int sy = this.getSolarYear();
		Matcher m;
		for (int i = 0; i < MyLunar.sFtv.length; i++) {
			m = MyLunar.sFreg.matcher(MyLunar.sFtv[i]);
			if (m.find()) {
				if (sM == MyLunar.toInt(m.group(1)) && sD == MyLunar.toInt(m.group(2))) {
					this.isSFestival = true;
					this.sFestivalName = m.group(4);
					if ("*".equals(m.group(3))) {
						this.isHoliday = true;
					}
					break;
				}
			}
		}
		for (int i = 0; i < MyLunar.lFtv.length; i++) {
			m = MyLunar.sFreg.matcher(MyLunar.lFtv[i]);
			if (m.find()) {
				if (lM == MyLunar.toInt(m.group(1)) && lD == MyLunar.toInt(m.group(2))) {
					this.isLFestival = true;
					this.lFestivalName = m.group(4);
					if ("*".equals(m.group(3))) {
						this.isHoliday = true;
					}
					break;
				}
			}
		}

		// ���ܽ���
		int w, d;
		for (int i = 0; i < MyLunar.wFtv.length; i++) {
			m = MyLunar.wFreg.matcher(MyLunar.wFtv[i]);
			if (m.find()) {
				if (this.getSolarMonth() == MyLunar.toInt(m.group(1))) {
					w = MyLunar.toInt(m.group(2));
					d = MyLunar.toInt(m.group(3));
					if (this.solar.get(Calendar.WEEK_OF_MONTH) == w && this.solar.get(Calendar.DAY_OF_WEEK) == d) {
						this.isSFestival = true;
						this.sFestivalName += "|" + m.group(5);
						if ("*".equals(m.group(4))) {
							this.isHoliday = true;
						}
					}
				}
			}
		}
		if (sy > 1874 && sy < 1909) {
			this.description = "����" + (((sy - 1874) == 1) ? "Ԫ" : "" + (sy - 1874));
		}
		if (sy > 1908 && sy < 1912) {
			this.description = "��ͳ" + (((sy - 1908) == 1) ? "Ԫ" : String.valueOf(sy - 1908));
		}
		if (sy > 1911 && sy < 1950) {
			this.description = "���" + (((sy - 1911) == 1) ? "Ԫ" : String.valueOf(sy - 1911));
		}
		if (sy > 1949) {
			this.description = "�л����񹲺͹�" + (((sy - 1949) == 1) ? "Ԫ" : String.valueOf(sy - 1949));
		}
		this.description += "��";
		this.sFestivalName = this.sFestivalName.replaceFirst("^\\|", "");
		this.isFinded = true;
	}

	private boolean isFinded = false;
	private boolean isSFestival = false;
	private boolean isLFestival = false;
	private String sFestivalName = "";
	private String lFestivalName = "";
	private String description = "";
	private boolean isHoliday = false;
	private boolean isWeekday = false;

	/**
	 * ����ũ���������·�
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�������µ��·�(����,û�򷵻�0)
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		// ��4bitȫΪ1��ȫΪ0,��ʾû��, ����4bit��ֵΪ�����·�
		int leapMonth = MyLunar.lunarInfo[lunarYear - 1900] & 0xf;
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	/**
	 * ����ũ�������µ�����
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�������µ�����(����)
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// ��һ�����4bitΪ1111,����30(����)
		// ��һ�����4bit��Ϊ1111,����29(С��)
		// ������û������,����0
		return MyLunar.getLunarLeapMonth(lunarYear) > 0 ? ((MyLunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29)
				: 0;
	}

	/**
	 * ����ũ�����������
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @return ��ũ�����������(����)
	 */
	private static int getLunarYearDays(int lunarYear) {
		// ��С�¼���,ũ����������12 * 29 = 348��
		int daysInLunarYear = 348;
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		// ÿ�������ۼ�һ��
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			daysInLunarYear += ((MyLunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// ������������
		daysInLunarYear += MyLunar.getLunarLeapDays(lunarYear);

		return daysInLunarYear;
	}

	/**
	 * ����ũ���������·ݵ�������
	 * 
	 * @param lunarYear
	 *            ָ��ũ�����(����)
	 * @param lunarMonth
	 *            ָ��ũ���·�(����)
	 * @return ��ũ�������µ��·�(����,û�򷵻�0)
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// ���ݱ���,ÿ��ũ������16bit����ʾ,
		// ǰ12bit�ֱ��ʾ12���·ݵĴ�С��,���4bit��ʾ����
		int daysInLunarMonth = ((MyLunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	/**
	 * ȡ Date ��������ȫ���׼ʱ�� (UTC) ��ʾ������
	 * 
	 * @param date
	 *            ָ������
	 * @return UTC ȫ���׼ʱ�� (UTC) ��ʾ������
	 */
	public static synchronized int getUTCDay(Date date) {
		MyLunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.setTimeInMillis(date.getTime());
			return utcCal.get(Calendar.DAY_OF_MONTH);
		}
	}

	private static GregorianCalendar utcCal = null;
	private static int millisecond;
	private static int year;
	private static int month;
	private static int day;
	private static int hour;
	private static int minute;
	private static int second;
	private static String way;

	private static synchronized void makeUTCCalendar() {
		if (MyLunar.utcCal == null) {
			MyLunar.utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		}
	}

	/**
	 * ����ȫ���׼ʱ�� (UTC) (�� GMT) �� 1970 �� 1 �� 1 �յ���ָ������֮��������ĺ�������
	 * 
	 * @param y
	 *            ָ�����
	 * @param m
	 *            ָ���·�
	 * @param d
	 *            ָ������
	 * @param h
	 *            ָ��Сʱ
	 * @param min
	 *            ָ������
	 * @param sec
	 *            ָ������
	 * @return ȫ���׼ʱ�� (UTC) (�� GMT) �� 1970 �� 1 �� 1 �յ���ָ������֮��������ĺ�����
	 */
	public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
		MyLunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.set(y, m, d, h, min, sec);
			return utcCal.getTimeInMillis();
		}
	}

	/**
	 * ���ع��������������
	 * 
	 * @param solarYear
	 *            ָ���������(����)
	 * @param index
	 *            ָ���������(����,0��С������)
	 * @return ����(����,�����·ݵĵڼ���)
	 */
	private static int getSolarTermDay(int solarYear, int index) {
		long l = (long) 31556925974.7 * (solarYear - 1900) + solarTermInfo[index] * 60000L;
		l = l + MyLunar.UTC(1900, 0, 6, 2, 5, 0);
		return MyLunar.getUTCDay(new Date(l));
	}

	private Calendar solar;
	private int lunarYear;
	private static int lunarMonth;
	private int lunarDay;
	private boolean isLeap;
	private boolean isLeapYear;
	private int solarYear;
	private int solarMonth;
	private int solarDay;
	private int cyclicalYear = 0;
	private int cyclicalMonth = 0;
	private int cyclicalDay = 0;
	private int maxDayInMonth = 29;
	private int maxDaysInLunarYear = 0;
	private int lunarsMonthInYear = 0;
	private int DaysSuminyear = 0;

	/**
	 * ͨ�� Date ���󹹽�ũ����Ϣ
	 * 
	 * @param date
	 *            ָ�����ڶ���
	 */
	public MyLunar(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.init(date.getTime());
	}

	/**
	 * ͨ�� TimeInMillis ����ũ����Ϣ
	 * 
	 * @param TimeInMillis
	 */
	public MyLunar(long TimeInMillis) {
		this.init(TimeInMillis);
	}

	private void init(long TimeInMillis) {
		this.solar = Calendar.getInstance();
		this.solar.setTimeInMillis(TimeInMillis);
		Calendar baseDate = new GregorianCalendar(1900, 0, 31);
		long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
		// ��ũ����ݼ�ÿ���ũ��������ȷ��ũ�����
		this.lunarYear = 1900;
		int daysInLunarYear = MyLunar.getLunarYearDays(this.lunarYear);
		while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
			offset -= daysInLunarYear;
			daysInLunarYear = MyLunar.getLunarYearDays(++this.lunarYear);
		}
		// ũ��������

		// ��ũ���µݼ�ÿ�µ�ũ��������ȷ��ũ���·�
		int lunarMonth = 1;
		// ����ũ�������ĸ���,��û�з���0
		int leapMonth = MyLunar.getLunarLeapMonth(this.lunarYear);
		// ����ũ������
		this.lunarsMonthInYear = leapMonth;
		// �Ƿ�����
		this.isLeapYear = leapMonth > 0;
		// �����Ƿ�ݼ�
		boolean leapDec = false;
		boolean isLeap = false;
		int daysInLunarMonth = 0;
		int DaysSumInyear = 0;
		while (lunarMonth < 13 && offset > 0) {
			if (isLeap && leapDec) { // ���������,����������
				// ����ũ�������µ�����
				daysInLunarMonth = MyLunar.getLunarLeapDays(this.lunarYear);
				leapDec = false;
			} else {
				// ����ũ����ָ���µ�����
				daysInLunarMonth = MyLunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			}
			if (offset < daysInLunarMonth) {
				break;
			}
			offset -= daysInLunarMonth;

			if (leapMonth == lunarMonth && isLeap == false) {
				// �¸���������
				leapDec = true;
				isLeap = true;
			} else {
				// �·ݵ���
				lunarMonth++;
			}
		}
		int DaySum = 0;
		for (int moth = 1; moth < lunarMonth; moth++) {

			DaysSumInyear = MyLunar.getLunarMonthDays(this.lunarYear, moth);
			DaySum = DaySum + DaysSumInyear;

		}
		this.DaysSuminyear = DaySum;
		// ũ��������
		this.maxDayInMonth = daysInLunarMonth;
		// ũ�� ��������
		this.maxDaysInLunarYear = daysInLunarYear;
		// ũ��������
		this.lunarMonth = lunarMonth;
		// �Ƿ�����
		this.isLeap = (lunarMonth == leapMonth && isLeap);
		// ũ��������
		this.lunarDay = (int) offset + 1;
		// ȡ�ø�֧��
		this.getCyclicalData();
	}

	/**
	 * ȡ��֧�� �������꣬���¸�֧�������й��Ĵ�����������ʼ�Ľ��£����й���̫��ʮ�����������ġ�
	 * 
	 * @param cncaData
	 *            ��������(Tcnca)
	 */
	private void getCyclicalData() {
		this.solarYear = this.solar.get(Calendar.YEAR);
		this.solarMonth = this.solar.get(Calendar.MONTH);
		this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
		// ��֧��
		int cyclicalYear = 0;
		int cyclicalMonth = 0;
		int cyclicalDay = 0;

		// ��֧�� 1900��������Ϊ������(60����36)
		int term2 = MyLunar.getSolarTermDay(solarYear, 2); // ��������
		// �������������·ֵ�����, ������Ϊ��
		if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
			cyclicalYear = (solarYear - 1900 + 36 - 1) % 60;
		} else {
			cyclicalYear = (solarYear - 1900 + 36) % 60;
		}

		// ��֧�� 1900��1��С����ǰΪ ������(60����12)
		int firstNode = MyLunar.getSolarTermDay(solarYear, solarMonth * 2); // ���ص��¡��ڡ�Ϊ���տ�ʼ
		// ����������, �ԡ��ڡ�Ϊ��
		if (solarDay < firstNode) {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 12) % 60;
		} else {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 13) % 60;
		}

		// ����һ���� 1900/1/1 �������
		// 1900/1/1�� 1970/1/1 ���25567��, 1900/1/1 ����Ϊ������(60����10)
		cyclicalDay = (int) (MyLunar.UTC(solarYear, solarMonth, solarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
		this.cyclicalYear = cyclicalYear;
		this.cyclicalMonth = cyclicalMonth;
		this.cyclicalDay = cyclicalDay;
	}

	/**
	 * ȡũ������Ф
	 * 
	 * @return ũ������Ф(��:��)
	 */
	public String getAnimalString() {
		return MyLunar.Animals[(this.lunarYear - 4) % 12];
	}

	/**
	 * ���ع������ڵĶ�ʮ�Ľ����ַ���
	 * 
	 * @return ��ʮ�Ľ����ַ���,�����ǽ�����,���ؿմ�(��:����)
	 */
	public String getTermString() {

		String termString = "";
		if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			termString = MyLunar.solarTerm[solarMonth * 2];
		} else if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			termString = MyLunar.solarTerm[solarMonth * 2 + 1];
		}
		return termString;
	}

	/**
	 * ����24��������ϸ���� get_the_24_solarterms
	 */
	public String get_24_SolarTerms() {
		String set_24_SolarTerms = "";
		if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			set_24_SolarTerms = "����" + MyLunar.get_the_24_solarterms[solarMonth * 2 - 2];
		} else if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			set_24_SolarTerms = "����" + MyLunar.get_the_24_solarterms[solarMonth * 2 + 1 - 2];
		}
		return set_24_SolarTerms;
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���(��:����������¼�����)
	 */
	public String getCyclicalDateString() {
		return this.getCyclicaYear() + "��" + this.getCyclicaMonth() + "��" + this.getCyclicaDay() + "��";
	}

	/**
	 * ������
	 * 
	 * @return ������
	 */
	public int getTiananY() {
		return MyLunar.getTianan(this.cyclicalYear);
	}

	/**
	 * �·����
	 * 
	 * @return �·����
	 */
	public int getTiananM() {
		return MyLunar.getTianan(this.cyclicalMonth);
	}

	/**
	 * �������
	 * 
	 * @return �������
	 */
	public int getTiananD() {
		return MyLunar.getTianan(this.cyclicalDay);
	}

	/**
	 * ��ݵ�֧
	 * 
	 * @return ��ֵ�֧
	 */
	public int getDeqiY() {
		return MyLunar.getDeqi(this.cyclicalYear);
	}

	/**
	 * �·ݵ�֧
	 * 
	 * @return �·ݵ�֧
	 */
	public int getDeqiM() {
		return MyLunar.getDeqi(this.cyclicalMonth);
	}

	/**
	 * ���ڵ�֧
	 * 
	 * @return ���ڵ�֧
	 */
	public int getDeqiD() {
		return MyLunar.getDeqi(this.cyclicalDay);
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaYear() {
		// return (this.isLeapYear() ? "[��]" :
		// "")+ExampleAppWidgetLunar.getCyclicalString(this.cyclicalYear);

		return MyLunar.getCyclicalString(this.cyclicalYear);

	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaMonth() {
		return MyLunar.getCyclicalString(this.cyclicalMonth);
	}

	/**
	 * ȡ�ø�֧���ַ���
	 * 
	 * @return ��֧���ַ���
	 */
	public String getCyclicaDay() {
		return MyLunar.getCyclicalString(this.cyclicalDay);
	}

	/**
	 * ����ũ�������ַ���
	 * 
	 * @return ũ�������ַ���
	 */
	public String getLunarDayString() {
		return MyLunar.getLunarDayString(this.lunarDay);
	}

	/**
	 * ����ũ���·��ַ���
	 * 
	 * @return ũ���·��ַ���
	 * 
	 *         ����������ǰ�����[��]
	 */
	public String getLunarMonthString() {
		// return (this.isLeap() ? "[��]" : "") +
		// MyLunar.getLunarMonthString(this.lunarMonth);
		return MyLunar.getLunarMonthString(this.lunarMonth);
	}

	/**
	 * ����ũ������ַ���
	 * 
	 * @return ũ������ַ���
	 * 
	 *         ����������ǰ�����[��]
	 */
	public String getLunarYearString() {
		// return (this.isLeapYear() ? "[��]" : "") +
		// MyLunar.getLunarYearString(this.lunarYear);
		return MyLunar.getLunarYearString(this.lunarYear);
	}

	/**
	 * ����ũ����ʾ�ַ���
	 * 
	 * @return ũ���ַ���(��:���������³���)
	 */
	public String getLunarDateString() {
		return this.getLunarYearString() + "��" + this.getLunarMonthString() + "��" + this.getLunarDayString() + "��";
	}

	/**
	 * ũ�����Ƿ�������
	 * 
	 * @return ũ�����Ƿ�������
	 */
	public boolean isLeap() {
		return isLeap;
	}

	/**
	 * ũ�����Ƿ�������
	 * 
	 * @return ũ�����Ƿ�������
	 */
	public boolean isLeapYear() {
		return isLeapYear;
	}

	/**
	 * ��ǰũ�����Ƿ��Ǵ���
	 * 
	 * @return ��ǰũ�����Ǵ���
	 */
	public boolean isBigMonth() {
		return this.getMaxDayInMonth() > 29;
	}

	/**
	 * ��ǰũ�����ж�����
	 * 
	 * @return ��ǰũ�����ж�����
	 */
	public int getMaxDayInMonth() {
		return this.maxDayInMonth;
	}

	/**
	 * ��ǰũ���Ѿ���ȥ������
	 * 
	 * @return ��ǰũ���Ѿ���ȥ������
	 */
	public int getDaysSuminyear() {
		return this.DaysSuminyear + lunarDay;
	}

	/**
	 * ��ǰũ�����ж�����
	 * 
	 * @return ��ǰũ�����ж�����
	 */
	public int getMaxDaysInLunarYear() {
		return this.maxDaysInLunarYear;
	}

	/***
	 * ���������� ������ û���򷵻�0
	 * 
	 * @return
	 */
	public int getlunarsMonthInYear() {
		return this.lunarsMonthInYear;

	}

	/**
	 * ũ������
	 * 
	 * @return ũ������
	 */
	public int getLunarDay() {
		return lunarDay;
	}

	/**
	 * ũ���·�
	 * 
	 * @return ũ���·�
	 */
	public int getLunarMonth() {
		return lunarMonth;
	}

	/**
	 * ũ�����
	 * 
	 * @return ũ�����
	 */
	public int getLunarYear() {
		return lunarYear;
	}

	/**
	 * ��������
	 * 
	 * @return ��������
	 */
	public int getSolarDay() {
		return solarDay;
	}

	/**
	 * �����·�
	 * 
	 * @return �����·� (���Ǵ�0����)
	 */
	public int getSolarMonth() {
		return solarMonth + 1;
	}

	/**
	 * �������
	 * 
	 * @return �������
	 */
	public int getSolarYear() {
		return solarYear;
	}

	/**
	 * ���ڼ�
	 * 
	 * @return ���ڼ�(������Ϊ:1, ������Ϊ:7)
	 */
	public int getDayOfWeek() {
		return this.solar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * ��ɫ������
	 * 
	 * @return �Ƿ��ɫ������
	 */
	public boolean isBlackFriday() {
		return (this.getSolarDay() == 13 && this.solar.get(Calendar.DAY_OF_WEEK) == 6);
	}

	/**
	 * �Ƿ��ǽ���
	 * 
	 * @return �Ƿ��ǽ���
	 */
	public boolean isToday() {
		Calendar clr = Calendar.getInstance();
		return clr.get(Calendar.YEAR) == this.solarYear && clr.get(Calendar.MONTH) == this.solarMonth
				&& clr.get(Calendar.DAY_OF_MONTH) == this.solarDay;
	}

	/**
	 * ȡ�ù�����������
	 * 
	 * @return ������������,������ǽ��շ��ؿմ�
	 */
	public String getSFestivalName() {
		return this.sFestivalName;
	}

	/**
	 * ȡ��ũ����������
	 * 
	 * @return ũ����������,������ǽ��շ��ؿմ�
	 */
	public String getLFestivalName() {
		return this.lFestivalName;
	}

	/**
	 * �Ƿ���ũ������
	 * 
	 * @return �Ƿ���ũ������
	 */
	public boolean isLFestival() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isLFestival;
	}

	/**
	 * �Ƿ��ǹ�������
	 * 
	 * @return �Ƿ��ǹ�������
	 */
	public boolean isSFestival() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isSFestival;
	}

	/**
	 * �Ƿ��ǽ���
	 * 
	 * @return �Ƿ��ǽ���
	 */
	public boolean isFestival() {
		return this.isSFestival() || this.isLFestival();
	}

	/**
	 * �Ƿ��Ƿż���
	 * 
	 * @return �Ƿ��Ƿż���
	 */
	public boolean isHoliday() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isHoliday;
	}

	/**
	 * ��������˵��
	 * 
	 * @return ����˵��(��:���2��)
	 */
	public String getDescription() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.description;
	}

	/**
	 * ��֧�ַ���
	 * 
	 * @param cyclicalNumber
	 *            ָ����֧λ��(����,0Ϊ����)
	 * @return ��֧�ַ���
	 * 
	 *         ԭ���ǣ�private
	 */
	public static String getCyclicalString(int cyclicalNumber) {
		return MyLunar.Tianan[MyLunar.getTianan(cyclicalNumber)] + MyLunar.Deqi[MyLunar.getDeqi(cyclicalNumber)];
	}

	/**
	 * ��õ�֧
	 * 
	 * @param cyclicalNumber
	 * @return ��֧ (����)
	 */
	private static int getDeqi(int cyclicalNumber) {
		return cyclicalNumber % 12;
	}

	/**
	 * ������
	 * 
	 * @param cyclicalNumber
	 * @return ��� (����)
	 */
	private static int getTianan(int cyclicalNumber) {
		return cyclicalNumber % 10;
	}

	/**
	 * ����ָ�����ֵ�ũ����ݱ�ʾ�ַ���
	 * 
	 * @param lunarYear
	 *            ũ�����(����,0Ϊ����)
	 * @return ũ������ַ���
	 */
	private static String getLunarYearString(int lunarYear) {
		return MyLunar.getCyclicalString(lunarYear - 1900 + 36);
	}

	/**
	 * ����ָ�����ֵ�ũ���·ݱ�ʾ�ַ���
	 * 
	 * @param lunarMonth
	 *            ũ���·�(����)
	 * @return ũ���·��ַ��� (��:��)
	 */
	private static String getLunarMonthString(int lunarMonth) {
		String lunarMonthString = "";
		if (lunarMonth == 1) {
			lunarMonthString = MyLunar.lunarString2[4];
		} else {
			if (lunarMonth > 9) {
				lunarMonthString += MyLunar.lunarString2[1];
			}
			if (lunarMonth % 10 > 0) {
				lunarMonthString += MyLunar.lunarString1[lunarMonth % 10];
			}
		}
		return lunarMonthString;
	}

	/**
	 * ����ָ�����ֵ�ũ���ձ�ʾ�ַ���
	 * 
	 * @param lunarDay
	 *            ũ����(����)
	 * @return ũ�����ַ��� (��: إһ)
	 */
	private static String getLunarDayString(int lunarDay) {
		if (lunarDay < 1 || lunarDay > 30) {
			return "";
		}
		int i1 = lunarDay / 10;
		int i2 = lunarDay % 10;
		String c1 = MyLunar.lunarString2[i1];
		String c2 = MyLunar.lunarString1[i2];
		if (lunarDay < 11) {
			c1 = MyLunar.lunarString2[0];
		}
		if (i2 == 0) {
			c2 = MyLunar.lunarString2[1];
		}
		return c1 + c2;
	}

	/**
	 * ��ʱ�ĕr���r��
	 * 
	 * @return
	 */
	static String getshichengshike() {
		String Hstr = null, Mstr = null;
		// Time t = new Time(); // or Time t=new Time("GMT+8"); ����Time Zone���ϡ�
		// t.setToNow(); // ȡ��ϵͳʱ�䡣
		// int hour = t.hour; // 0-23
		// int minute = t.minute;// 0-60
		double sum = hour + 0.01 * minute;
		if (sum < 1 || sum >= 23) {
			Hstr = Deqi[0] + "ʱ" + "[����]";
		}
		if (sum >= 1 && sum < 3) {
			Hstr = Deqi[1] + "ʱ" + "[�ĸ�]";
		}
		if (sum >= 3 && sum < 5) {
			Hstr = Deqi[2] + "ʱ" + "[���]";
		}
		if (sum >= 5 && sum < 7) {
			Hstr = Deqi[3] + "ʱ";
		}
		if (sum >= 7 && sum < 9) {
			Hstr = Deqi[4] + "ʱ";
		}
		if (sum >= 9 && sum < 11) {
			Hstr = Deqi[5] + "ʱ";
		}
		if (sum >= 11 && sum < 13) {
			Hstr = Deqi[6] + "ʱ";
		}
		if (sum >= 13 && sum < 15) {
			Hstr = Deqi[7] + "ʱ";
		}
		if (sum >= 15 && sum < 17) {
			Hstr = Deqi[8] + "ʱ";
		}
		if (sum >= 17 && sum < 19) {
			Hstr = Deqi[9] + "ʱ";
		}
		if (sum >= 19 && sum < 21) {
			Hstr = Deqi[10] + "ʱ" + "[һ��]";
		}
		if (sum >= 21 && sum < 23) {
			Hstr = Deqi[11] + "ʱ" + "[����]";
		}
		if (minute >= 14 && minute <= 15) {
			Mstr = "һ��";
		} else if (minute >= 28 && minute <= 30) {
			Mstr = "����";
		} else if (minute >= 42 && minute <= 45) {
			Mstr = "����";
		} else if (minute >= 48 && minute <= 60) {
			Mstr = "�Ŀ�";
		} else {
			Mstr = "";
		}
		return Hstr + Mstr;

	}

	static String getGyuK() {
		String Hstr = "", Mstr = "", HM = "";
		// Time t = new Time(); // or Time t=new Time("GMT+8"); ����Time Zone���ϡ�
		// t.setToNow(); // ȡ��ϵͳʱ�䡣
		// int hour = t.hour; // 0-23
		// int minute = t.minute;// 0-60
		double sum = hour + 0.01 * minute;
		if (sum >= 19 && sum < 21) {
			Hstr = "һ��";
		} else if (sum >= 21 && sum < 23) {
			Hstr = "����";
		} else if (sum < 1 || sum >= 23) {
			Hstr = "����";
		} else if (sum >= 1 && sum < 3) {
			Hstr = "�ĸ�";
		} else if (sum >= 3 && sum < 5) {
			Hstr = "���";
		} else {
			Hstr = "";
		}
		if (minute >= 14 && minute <= 15) {
			Mstr = "һ��";
		} else if (minute >= 28 && minute <= 30) {
			Mstr = "����";
		} else if (minute >= 42 && minute <= 45) {
			Mstr = "����";
		} else if (minute >= 48 && minute <= 60) {
			Mstr = "�Ŀ�";
		} else {
			Mstr = "";
		}
		if (!Hstr.equals("") || !Mstr.equals("")) {
			HM = Hstr + Mstr;
		} else {
			HM = "";
		}

		return HM;

	}

	/***
	 * �ж��Ƿ���˫��
	 * 
	 * @return
	 */
	public boolean isWeekday() {
		Calendar c = Calendar.getInstance();
		int way = c.get(Calendar.DAY_OF_WEEK);
		if (way == 1 || way == 7) {
			isWeekday = true;
		} else {
			isWeekday = false;
		}
		return isWeekday;
	}

	/***
	 * ��ϸ�� ����ʱ�䣺����������ʱ�������
	 * 
	 * @return
	 */
	public static String timedata() {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		way = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);
		millisecond = c.get(Calendar.MILLISECOND);
		long mytimedata = System.currentTimeMillis();
		// CharSequence mytime = DateFormat.format("yyyy��MM��dd��", mytimedata);
		if ("1".equals(way)) {
			way = "��";
		} else if ("2".equals(way)) {
			way = "һ";
		} else if ("3".equals(way)) {
			way = "��";
		} else if ("4".equals(way)) {
			way = "��";
		} else if ("5".equals(way)) {
			way = "��";
		} else if ("6".equals(way)) {
			way = "��";
		} else if ("7".equals(way)) {
			way = "��";
		}
		return null;
		// return String.valueOf(mytime) + "����" + way + hour + "ʱ" + minute + "��" +
		// second + "��" + millisecond + "��";
	}

	/***
	 * ��������һ��
	 * 
	 * @return
	 */
	// public String getYMDEtime() {
	// long mynewtime = System.currentTimeMillis();
	// �������������������ʱ����ʾ�ĸ�ʽ ,����������������޸�
	// CharSequence YMDEtime = DateFormat.format("yyyy��MM��dd�� E", mynewtime);
	// lunarString5
	// return String.valueOf(YMDEtime);
	// }

	public String getHMStime() {
		long mynewtime = System.currentTimeMillis();
		// �������������������ʱ����ʾ�ĸ�ʽ ,����������������޸�
		// CharSequence HMStime = DateFormat.format("mm:ss", mynewtime);
		Calendar hour = Calendar.getInstance();
		int HH = hour.get(Calendar.HOUR_OF_DAY);
		String str = "";
		if (HH < 10) {
			// str = "0" + HH + ":" + HMStime;
		} else {
			// str = HH + ":" + HMStime;
		}
		// String.valueOf(HH+":"+HMStime)
		return str;
	}

	/***
	 * �����·ݵĴ�д�� �磺���³�һ
	 * 
	 * @return
	 */
	public String getBigMoth() {
		// return (this.isLeap() ? "[��]" : "") + lunarString3[lunarMonth -
		// 1]+"��"+getLunarDayString();

		return this.getLunarYearString() + lunarString3[lunarMonth - 1] + "��" + getLunarDayString();
	}

	/**
	 * my_Family_Birthday
	 * 
	 * @return
	 */
	public String getBirthday() {
		int m = lunarMonth;
		int d = lunarDay;
		String Famimly = "";
		if (m == 10 && d == 21) {
			Famimly = my_Family_Birthday[0];
		} else if (m == 8 && d == 4) {
			Famimly = my_Family_Birthday[1];
		} else if (m == 5 && d == 21) {
			Famimly = my_Family_Birthday[2];
		} else if (m == 12 && d == 13) {
			Famimly = my_Family_Birthday[3];
		} else if (m == 7 && d == 17) {
			Famimly = my_Family_Birthday[4];
		} else {
			Famimly = "";
		}
		return Famimly;
	}

	/***
	 * ��������
	 */
	public static String getConstellation() {
		String str = null;
		double in = 0;
		Calendar d = Calendar.getInstance();
		month = d.get(Calendar.MONTH) + 1;
		day = d.get(Calendar.DAY_OF_MONTH);
		in = month + 0.01 * day;
		if (in >= 1.20 && in <= 2.18) {
			str = lunarString4[0];
		}
		if (in >= 2.19 && in <= 3.20) {
			str = lunarString4[1];
		}
		if (in >= 3.21 && in <= 4.19) {
			str = lunarString4[2];
		}
		if (in >= 4.20 && in <= 5.20) {
			str = lunarString4[3];
		}
		if (in >= 5.21 && in <= 6.21) {
			str = lunarString4[4];
		}
		if (in >= 6.22 && in <= 7.22) {
			str = lunarString4[5];
		}
		if (in >= 7.23 && in <= 8.22) {
			str = lunarString4[6];
		}
		if (in >= 8.23 && in <= 9.22) {
			str = lunarString4[7];
		}
		if (in >= 9.23 && in <= 10.23) {
			str = lunarString4[8];
		}
		if (in >= 10.24 && in <= 11.22) {
			str = lunarString4[9];
		}
		if (in >= 11.23 && in <= 12.21) {
			str = lunarString4[10];
		}
		if (in >= 12.22 || in <= 1.19) {
			str = lunarString4[11];
		}
		return str;
	}

	/***
	 * [��֧��]ũ��
	 * 
	 * @return ��ϸ�� ��Ф [��֧��]ũ�� ʱ��
	 */
	public String getCyclicaAndgetLunar() {
		String Date = "[" + this.getAnimalString() + "]" + "[" + this.getCyclicaYear() + "]" + this.getLunarYearString()
				+ "��" + "[" + this.getCyclicaMonth() + "]" + this.getLunarMonthString() + "��" + "["
				+ this.getCyclicaDay() + "]" + this.getLunarDayString() + "��" + this.getshichengshike();
		return Date;
	}

	/***
	 * ��������+ũ������+24����
	 * 
	 * @return
	 */
	public String getTermSLFestival() {
		String str = getSFestivalName() + getLFestivalName() + getTermString();
		return str;
	}

	/**
	 * ȡũ������Ф���Ƿ�������
	 * 
	 * @return ũ������Ф(��:[��|��])
	 */
	public String getAnimalALeapString() {

		return "[" + MyLunar.Animals[(this.lunarYear - 4) % 12] + (this.isLeapYear() ? "|��" : "") + "]";
	}

	/***
	 * ��Ф+�Ƿ�����+��������
	 */
	public String getshengchenbazi() {
		String shengchenbazi = "";
		try {
			// ����һ��java.util.Date ���󣬲����ú�����ʱ�䡣����
			Date date = new Date();
			// ������ʽ���࣬�趨��ʽΪ yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long mytime = System.currentTimeMillis();
			// CharSequence time = DateFormat.format("yyyy-MM-dd HH:mm:ss", mytime);
			// String date_newtime = ""+time;//String.valueOf(time)
			// ��date���ʱ�丳�Ͼ���ֵ
			// date = sdf.parse(date_newtime);
			// �½�һ��bazi���󣬴� ��Date����
			// MyBazi test = new MyBazi(date);
			// ������Ҫ������Ӧ��ֵ ���·���������� String ������������
			// System.out.print(test.getbazi());MyBaZi()
			// shengchenbazi = test.getbazi() + getGyuK();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAnimalALeapString() + shengchenbazi;
	}

	/***
	 * �жϹ����Ƿ�������
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (year % 400 == 0 || year % 100 != 0 && year % 4 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * ָ���·ݶ�����
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		default:
			if (isLeapYear(year)) {
				return 29;
			}
			return 28;
		}
	}

	/**
	 * ָ���������
	 * 
	 * @param year
	 * @return
	 */
	public int issumnewyear(int year) {
		int two = 0;
		if (isLeapYear(year)) {
			two = 29;
		} else {
			two = 28;
		}
		int sum = 7 * 31 + 4 * 30 + two;
		return sum;
	}

	/**
	 * ���ع��������ϸ��Ϣ
	 * 
	 * @return
	 */
	public String getdayofnewyear() {
		Calendar clr = Calendar.getInstance();
		int y = clr.get(Calendar.YEAR);
		int d = clr.get(Calendar.DAY_OF_YEAR);
		int m = clr.get(Calendar.MONTH) + 1;
		String S = " ������" + this.issumnewyear(y) + "��  ��" + d + "��  " + lunarString6[m - 1] + "��" + getDaysOfMonth(y, m)
				+ "��";
		;
		return S;

	}

	public String getRequestFocus() {
		 //2017��01��31 ���� ������365���58������28�� ũ������384���31������30�� ������
		// String mRF = getDescription() +" ��Ԫ"+getYMDEtime()+getdayofnewyear()+"ũ��"+getAnimalString()+"��"+getMaxDaysInLunarYear() + "�� ��" +getDaysSuminyear() + "�� " +lunarString3[lunarMonth - 1]+"��"+ getMaxDayInMonth()+"�� ";
		if (this.isLeapYear()) {
			// mRF += " ��"+lunarString3[getlunarsMonthInYear() - 1 ]+"��";
		}
		// return mRF+" "+get_24_SolarTerms();
		return null;
	}

}