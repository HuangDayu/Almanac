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
 * 提供一些农历相关信息
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
	public final static String[] Tianan = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
	public final static String[] Deqi = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
	public final static String[] Animals = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
	public final static String[] solarTerm = { "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至",
			"小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至" };
	public final static String[] lunarString1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	public final static String[] lunarString2 = { "初", "十", "廿", "卅", "正", "腊", "冬", "闰" };
	public final static String[] lunarString3 = { "正", "贰", "弎", "肆", "伍", "陸", "柒", "捌", "玖", "什", "冬", "腊" };
	public final static String[] lunarString4 = { "水瓶座 ", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座",
			"射手座", "摩羯座" };
	public final static String[] lunarString5 = { "日 ", "一", "二", "三", "四", "五", "六" };
	public final static String[] lunarString6 = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };
	/**
	 * 国历节日 *表示放假日
	 */
	private final static String[] sFtv = { "0101*元旦", "0115 元宵节", "0214 情人节", "0308 妇女节", "0312 植树节", "0315 315",
			"0401 愚人节", "0501*劳动节", "0504 青年节", "0509 郝维节", "0512 护士节", "0601 儿童节", "0701 香港回归", "0801 建军节",
			"0816 燕衔泥节", "0909 毛泽东逝世", "0910 教师节", "0928 孔子诞辰", "1001*国庆节", "1006 老人节", "1024 联合国日", "1111 光棍节",
			"1112 孙中山诞辰", "1220 澳门回归", "1225 圣诞节", "1226 毛泽东诞辰" };
	/**
	 * 农历节日 *表示放假日
	 */
	private final static String[] lFtv = { "0101*春节", "0106 定光佛诞", "0115 元宵节", "0505 端午节", "0707 七夕", "0715 中元节",
			"0808 父亲节", "0815 中秋节", "0909 重阳节", "1208 腊八节", "1224 小年", "0100*除夕" };
	/**
	 * 某月的第几个星期几
	 */
	private static String[] wFtv = { "0520 母亲节", "0716 合作节", "0730 被奴役国家周" };

	private static final String[] my_Family_Birthday = { "父亲生日快乐", "母亲生日快乐", "珊姐生日快乐", "静姐生日快乐", "大鱼生日快乐" };

	public static final String[] get_the_24_solarterms = {
			"立春：斗指东北。太阳黄经为315度。是二十四个节气的头一个节气。其含义是开始进入春天，“阳和起蛰，品物皆春”，"
					+ "过了立春，万物复苏生机勃勃，一年四季从此开始了。一候东风解冻，二候蜇虫始振，三候鱼陟负冰。说的是东风送暖，大地开始解冻。立春五日后，"
					+ "蜇居的虫类慢慢在洞中苏醒，再过五日，河里的冰开始溶化，鱼开始到水面上游动，此时水面上还有没完全溶解的碎冰片，如同被鱼负着一般浮在水面。",
			"雨水：斗指壬。太阳黄经为330°。这时春风遍吹，冰雪融化，空气湿润，雨水增多，所以叫雨水。人们常说：“立春天渐暖，雨水送肥忙”。"
					+ "一候獭祭鱼；二候鸿雁来；三候草木萌劝。此节气，水獭开始捕鱼了，将鱼摆在岸边如同先祭后食的样子；五天过后，大雁开始从南方飞回北方；再过五天，"
					+ "在“润物细无声”的春雨中，草木随地中阳气的上腾而开始抽出嫩芽。从此，大地渐渐开始呈现出一派欣欣向荣的景象。",
			"惊蛰：斗指丁。太阳黄经为345°。这个节气表示“立春”以后天气转暖，春雷开始震响，蛰伏在泥土里的各种冬眠动物将苏醒过来开始活动起来，所以叫惊蛰。"
					+ "这个时期过冬的虫排卵也要开始孵化。我国部分地区进入了春耕季节。谚语云：“惊蛰过，暖和和，蛤蟆老角唱山歌。”“惊蛰一犁土，春分地气通。”“惊蛰没到雷先鸣，大雨似蛟龙。”"
					+ "一候桃始华；二候仓庚（黄鹂）鸣；三候鹰化为鸠。描述已是桃花红、李花白，黄莺鸣叫、燕飞来的时节，大部分地区都已进入了春耕。惊醒了蛰伏在泥土中冬眠的各种昆虫的时候，"
					+ "此时过冬的虫卵也要开始卵化，由此可见惊蛰是反映自然物候现象的一个节气。",
			"春分：斗指壬。太阳黄经为0°。春分日太阳在赤道上方。这是春季90天的中分点，这一天南北两半球昼夜相等，所以叫春分。这天以后太阳直射位置便向北移，北半球昼长夜短。"
					+ "所以春分是北半球春季开始。我国大部分地区越冬作物进入春季生长阶段。各地农谚有：“春分在前，斗米斗钱”（广东）、“春分甲子雨绵绵，夏分甲子火烧天”（四川）、“春分有雨家家忙，先种瓜豆后插秧”（湖北）、"
					+ "“春分种菜，大暑摘瓜”（湖南）、“春分种麻种豆，秋分种麦种蒜”（安徽）。" + "一候元鸟至；二候雷乃发声；三候始电。是说春分日后，燕子便从南方飞来了，下雨时天空便要打雷并发出闪电。",
			"清明：斗指丁。太阳黄经为15°。此时气候清爽温暖，草木始发新枝芽，万物开始生长，农民忙于春耕春种。从前，在清明节这一天，有些人家都在门口插上杨柳条，还到郊外踏青，祭扫坟墓，这是古老的习俗。"
					+ "一候桐始华；二候田鼠化为鹌；三候虹始见。意思是在这个时节先是白桐花开放，接着喜阴的田鼠不见了，全回到了地下的洞中，然后是雨后的天空可以见到彩虹了。",
			"谷雨：斗指癸。太阳黄经为30°。就是雨水生五谷的意思，由于雨水滋润大地五谷得以生长，所以，谷雨就是“雨生百谷”。谚云“谷雨前后，种瓜种豆”。"
					+ "一候萍始生；二候呜鸠拂其羽；三候为戴任降于桑。是说谷雨后降雨量增多，浮萍开始生长，接着布谷鸟便开始提醒人们播种了，然后是桑树上开始见到戴胜鸟。",
			"立夏：斗指东南。太阳黄经为45°。是夏季的开始，从此进入夏天，万物旺盛大。习惯上把立夏当作是气温显著升高，炎暑将临，雷雨增多，农作物进入旺季生长的一个最重要节气。"
					+ "一候蝼蝈鸣；二候蚯蚓出；三候王瓜生。即说这一节气中首先可听到蜊蜊（即：蝼蛄）蛄在田间的呜叫声（一说是蛙声），接着大地上便可看到蚯蚓掘土，然后王瓜的蔓藤开始快速攀爬生长。",
			"小满：斗指甲。太阳黄经为60°。从小满开始，大麦、冬小麦等夏收作物，已经结果、籽粒饱满，但尚未成熟，所以叫小满。"
					+ "一候苦菜秀；二候靡草死；三候麦秋至。是说小满节气中，苦菜已经枝叶繁茂；而喜阴的一些枝条细软的草类在强烈的阳光下开始枯死；此时麦子开始成熟。",
			"芒种：斗指向己。太阳黄经为75°。这时最适合播种有芒的谷类作物，如晚谷、黍、稷等。如过了这个时候再种有芒和作物就不好成熟了。同时，“芒”指有芒作物如小麦、大麦等，“种”指种子。芒种即表明小麦等有芒作物成熟。"
					+ "芒种前后，我国中部的长江中、下游地区，雨量增多，气温升高，进入连绵阴雨的梅雨季节，空气非常潮湿，天气异常闷热，各种器具和衣物容易发霉，所以在我国长江中、下游地区也叫“霉雨”。"
					+ "一候螳螂生；二候鹏始鸣；三候反舌无声。在这一节气中，螳螂在去年深秋产的卵因感受到阴气初生而破壳生出小螳螂；喜阴的伯劳鸟开始在枝头出现，并且感阴而鸣；与此相反，能够学习其它鸟鸣叫的反舌鸟，却因感应到了阴气的出现而停止了鸣叫。",
			"夏至：斗指向乙。太阳黄经为90°。太阳在黄经90°“夏至点”时，阳光几乎直射北回归线上空，北半球正午太阳最高。这一天是北半球白昼最长、黑夜最短的一天，从这一天起，进入炎热季节，天地万物在此时生长最旺盛。"
					+ "所以古时候又把这一天叫做日北至，意思是太阳运行到最北的一日。过了夏至，太阳逐渐向南移动，北半球白昼一天比一天缩短，黑夜一天比一天加长。"
					+ "一候鹿角解；二候蝉始鸣；三候半夏生。糜与鹿虽属同科，但古人认为，二者一属阴一属阳。鹿的角朝前生，所以属阳。夏至日阴气生而阳气始衰，所以阳性的鹿角便丌始脱落。而糜因属阴，所以在冬至日角才脱落；雄性的知了在夏至后因感阴气之生便鼓翼而鸣；"
					+ "半夏是一种喜阴的药草，因在仲夏的沼泽地或水田中出生所以得名。由此可见，在炎热的仲夏，一些喜阴的生物开始出现，而阳性的生物却开始衰退了。",
			"小暑：斗指辛。太阳黄经为105°。天气已经很热了，但还不到最热的时候，所以叫小暑。此时，已是初伏前后。" + "一候温风至；二候蟋蟀居宇；三候鹰始鸷。小暑时节大地上便不再有一丝凉风，而是所有的风中都带着热浪。",
			"大暑：斗指丙。太阳黄经为120°。大暑是一年中最热的节气，正值勤二伏前后，长江流域的许多地方，经常出现40℃高温天气。要作好防暑降温工作。这个节气雨水多，在“小暑、大暑，淹死老鼠”的谚语，要注意防汛防涝。"
					+ "一候腐草为萤；二候土润溽暑；三候大雨时行。世上萤火虫约有二千多种，分水生与陆生两种，陆生的萤火虫产卵于枯草上，大暑时，萤火虫卵化而出，所以古人认为萤火虫是腐草变成的；第二候是说天气开始变得闷热，土地也很潮湿；第三候是说时常有大的雷雨会出现，这大雨使暑湿减弱，天气开始向立秋过渡。",
			"立秋：斗指向西南。太阳黄经为135°。从这一天起秋天开始，秋高气爽，月明风清。此后，气温由最热逐渐下降"
					+ "一候凉风至；二候白露生；三候寒蝉鸣。是说立秋过后，刮风时人们会感觉到凉爽，此时的风已不同于暑天中的热风；接着，大地上早晨会有雾气产生；并且秋天感阴而鸣的寒蝉也开始鸣叫。",
			"处暑：斗指戊。太阳黄经为150°。这时夏季火热已经到头了。暑气就要散了。它是温度下降的一个转折点。是气候变凉的象征，表示暑天终止。"
					+ "一候鹰乃祭鸟；二候天地始肃；三候禾乃登。此节气中老鹰开始大量捕猎鸟类；天地间万物开始凋零；“禾乃登”的“禾”指的是黍、稷、稻、粱类农作物的总称，“登”即成熟的意思。",
			"白露：斗指癸。太阳黄经为165°。天气转凉，地面水汽结露。" + "一候鸿雁来；二候元鸟归；三候群鸟养羞。说此节气正是鸿雁与燕子等候鸟南飞避寒，百鸟开始贮存干果粮食以备过冬。可见白露实际上是天气转凉的象征。",
			"秋分：斗指已。太阳黄经为180°。秋分这一天同春分一样，阳光几乎直射赤道，昼夜几乎相等。从这一天起，阳光直射位置继续由赤道向南半球推移，北半球开始昼短夜长。依我国旧历的秋季论，这一天刚好是秋季九十天的一半，因而称秋分。但在天文学上规定，北半球的秋天是从秋分开始的。"
					+ "一候雷始收声；二候蛰虫坯户；三候水始涸。古人认为雷是因为阳气盛而发声，秋分后阴气开始旺盛，所以不再打雷了。",
			"寒露：斗指甲。太阳黄经为195°。白露后，天气转凉，开始出现露水，到了寒露，则露水日多，且气温更低了。所以，有人说，寒是露之气，先白而后寒，是气候将逐渐转冷的意思。而水气则凝成白色露珠。"
					+ "一候鸿雁来宾；二候雀人大水为蛤；三候菊有黄华。此节气中鸿雁排成一字或人字形的队列大举南迁；深秋天寒，雀鸟都不见了，古人看到海边突然出现很多蛤蜊，并且贝壳的条纹及颜色与雀鸟很相似，所以便以为是雀鸟变成的；第三候的“菊始黄华”是说在此时菊花已普遍开放。",
			"霜降：斗指戌。太阳黄经为210°。天气已冷，开始有霜冻了，所以叫霜降。"
					+ "一候豺乃祭兽； 此节气中豺狼将捕获的猎物先陈列后再食用；二候草木黄落；大地上的树叶枯黄掉落；三候蜇虫咸俯；蜇虫也全在洞中不动不食，垂下头来进入冬眠状态中。",
			"立冬：斗指乾。太阳黄经为225°。习惯上，我国人民把这一天当作冬季的开始。冬，作为终了之意，是指一年的田间操作结束了，作物收割之后要收藏起来的意思。立冬一过，我国黄河中、下游地区即将结冰，我国各地农民都将陆续地转入农田水利基本建设和其他农事活动中。"
					+ "一候水始冰；二候地始冻；三候雉人大水为蜃。此节气水已经能结成冰；土地也开始冻结；三候“雉人大水为蜃”中的雉即指野鸡一类的大鸟，蜃为大蛤，立冬后，野鸡一类的大鸟便不多见了，而海边却可以看到外壳与野鸡的线条及颜色相似的大蛤。所以古人认为雉到立冬后便变成大蛤了。",
			"小雪：斗指己。太阳黄经为240°。气温下降，开始降雪，但还不到大雪纷飞的时节，所以叫小雪。小雪前后，黄河流域开始降雪（南方降雪还要晚两个节气）；而北方，已进入封冻季节。"
					+ "一候虹藏不见；二候天气上升地气下降；三候闭塞而成冬。由于天空中的阳气上升，地中的阴气下降，导致天地不通，阴阳不交，所以万物失去生机，天地闭塞而转入严寒的冬天。",
			"大雪：斗指癸。太阳黄经为255°。大雪前后，黄河流域一带渐有积雪；而北方，已是“千里冰封，万里雪飘”的严冬了。"
					+ "一候鹃鸥不呜；二候虎始交；三候荔挺出。这是说此时因天气寒冷，寒号鸟也不再呜叫了；由于此时是阴气最盛时期，正所谓盛极而衰，阳气已有所萌动，所以老虎开始有求偶行为；“荔挺”为兰草的一种，也感到阳气的萌动而抽出新芽。",
			"冬至：斗指子。太阳黄经为270°。冬至这一天，阳光几乎直射南回归线，我们北半球白昼最短，黑夜最长，开始进入数九寒天。天文学上规定这一天是北半球冬季的开始。而冬至以后，阳光直射位置逐渐向北移动，北半球的白天就逐渐长了，谚云：吃了冬至面，一天长一线。"
					+ "一候蚯蚓结；二候糜角解；三候水泉动。传说蚯蚓是阴曲阳伸的生物，此时阳气虽已生长，但阴气仍然十分强盛，土中的蚯蚓仍然蜷缩着身体；糜与鹿同科，却阴阳不同，古人认为糜的角朝后生，所以为阴，而冬至一阳生，糜感阴气渐退而解角；由于阳气初生，所以此时山中的泉水可以流动并且温热。",
			"小寒：斗指子，太阳黄经为285°。小寒以后，开始进入寒冷季节。冷气积久而寒，小寒是天气寒冷但还没有到极点的意思。"
					+ "一候雁北乡，二候鹊始巢，三候雉始鸲。古人认为候鸟中大雁是顺阴阳而迁移，此时阳气已动，所以大雁开始向北迁移；此时北方到处可见到喜鹊，并且感觉到阳气而开始筑巢；第三候“雉鸲”的“鸲”为鸣叫的意思，雉在接近四九时会感阳气的生长而鸣叫。",
			"大寒：斗指丑，太阳黄经为300°。大寒就是天气寒冷到了极点的意思。大寒前后是一年中最冷的季节。大寒正值三九刚过，四九之初。谚云：“三九四九冰上走”。"
					+ "一候鸡乳；二候征鸟厉疾；三候水泽腹坚。就是说到大寒节气便可以孵小鸡了；而鹰隼之类的征鸟，却正处于捕食能力极强的状态中，盘旋于空中到处寻找食物，以补充身体的能量抵御严寒；在一年的最后五天内，水域中的冰一直冻到水中央，且最结实、最厚。" };

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

		// 月周节日
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
			this.description = "光绪" + (((sy - 1874) == 1) ? "元" : "" + (sy - 1874));
		}
		if (sy > 1908 && sy < 1912) {
			this.description = "宣统" + (((sy - 1908) == 1) ? "元" : String.valueOf(sy - 1908));
		}
		if (sy > 1911 && sy < 1950) {
			this.description = "民国" + (((sy - 1911) == 1) ? "元" : String.valueOf(sy - 1911));
		}
		if (sy > 1949) {
			this.description = "中华人民共和国" + (((sy - 1949) == 1) ? "元" : String.valueOf(sy - 1949));
		}
		this.description += "年";
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
	 * 返回农历年闰月月份
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 若4bit全为1或全为0,表示没闰, 否则4bit的值为闰月月份
		int leapMonth = MyLunar.lunarInfo[lunarYear - 1900] & 0xf;
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	/**
	 * 返回农历年闰月的天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的天数(数字)
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// 下一年最后4bit为1111,返回30(大月)
		// 下一年最后4bit不为1111,返回29(小月)
		// 若该年没有闰月,返回0
		return MyLunar.getLunarLeapMonth(lunarYear) > 0 ? ((MyLunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29)
				: 0;
	}

	/**
	 * 返回农历年的总天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年的总天数(数字)
	 */
	private static int getLunarYearDays(int lunarYear) {
		// 按小月计算,农历年最少有12 * 29 = 348天
		int daysInLunarYear = 348;
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 每个大月累加一天
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			daysInLunarYear += ((MyLunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// 加上闰月天数
		daysInLunarYear += MyLunar.getLunarLeapDays(lunarYear);

		return daysInLunarYear;
	}

	/**
	 * 返回农历年正常月份的总天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @param lunarMonth
	 *            指定农历月份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		int daysInLunarMonth = ((MyLunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	/**
	 * 取 Date 对象中用全球标准时间 (UTC) 表示的日期
	 * 
	 * @param date
	 *            指定日期
	 * @return UTC 全球标准时间 (UTC) 表示的日期
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
	 * 返回全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数。
	 * 
	 * @param y
	 *            指定年份
	 * @param m
	 *            指定月份
	 * @param d
	 *            指定日期
	 * @param h
	 *            指定小时
	 * @param min
	 *            指定分钟
	 * @param sec
	 *            指定秒数
	 * @return 全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数
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
	 * 返回公历年节气的日期
	 * 
	 * @param solarYear
	 *            指定公历年份(数字)
	 * @param index
	 *            指定节气序号(数字,0从小寒算起)
	 * @return 日期(数字,所在月份的第几天)
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
	 * 通过 Date 对象构建农历信息
	 * 
	 * @param date
	 *            指定日期对象
	 */
	public MyLunar(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.init(date.getTime());
	}

	/**
	 * 通过 TimeInMillis 构建农历信息
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
		// 按农历年递减每年的农历天数，确定农历年份
		this.lunarYear = 1900;
		int daysInLunarYear = MyLunar.getLunarYearDays(this.lunarYear);
		while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
			offset -= daysInLunarYear;
			daysInLunarYear = MyLunar.getLunarYearDays(++this.lunarYear);
		}
		// 农历年数字

		// 按农历月递减每月的农历天数，确定农历月份
		int lunarMonth = 1;
		// 所在农历年闰哪个月,若没有返回0
		int leapMonth = MyLunar.getLunarLeapMonth(this.lunarYear);
		// 返回农历闰月
		this.lunarsMonthInYear = leapMonth;
		// 是否闰年
		this.isLeapYear = leapMonth > 0;
		// 闰月是否递减
		boolean leapDec = false;
		boolean isLeap = false;
		int daysInLunarMonth = 0;
		int DaysSumInyear = 0;
		while (lunarMonth < 13 && offset > 0) {
			if (isLeap && leapDec) { // 如果是闰年,并且是闰月
				// 所在农历年闰月的天数
				daysInLunarMonth = MyLunar.getLunarLeapDays(this.lunarYear);
				leapDec = false;
			} else {
				// 所在农历年指定月的天数
				daysInLunarMonth = MyLunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			}
			if (offset < daysInLunarMonth) {
				break;
			}
			offset -= daysInLunarMonth;

			if (leapMonth == lunarMonth && isLeap == false) {
				// 下个月是闰月
				leapDec = true;
				isLeap = true;
			} else {
				// 月份递增
				lunarMonth++;
			}
		}
		int DaySum = 0;
		for (int moth = 1; moth < lunarMonth; moth++) {

			DaysSumInyear = MyLunar.getLunarMonthDays(this.lunarYear, moth);
			DaySum = DaySum + DaysSumInyear;

		}
		this.DaysSuminyear = DaySum;
		// 农历月天数
		this.maxDayInMonth = daysInLunarMonth;
		// 农历 年总天数
		this.maxDaysInLunarYear = daysInLunarYear;
		// 农历月数字
		this.lunarMonth = lunarMonth;
		// 是否闰月
		this.isLeap = (lunarMonth == leapMonth && isLeap);
		// 农历日数字
		this.lunarDay = (int) offset + 1;
		// 取得干支历
		this.getCyclicalData();
	}

	/**
	 * 取干支历 不是历年，历月干支，而是中国的从立春节气开始的节月，是中国的太阳十二宫，阳历的。
	 * 
	 * @param cncaData
	 *            日历对象(Tcnca)
	 */
	private void getCyclicalData() {
		this.solarYear = this.solar.get(Calendar.YEAR);
		this.solarMonth = this.solar.get(Calendar.MONTH);
		this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
		// 干支历
		int cyclicalYear = 0;
		int cyclicalMonth = 0;
		int cyclicalDay = 0;

		// 干支年 1900年立春後为庚子年(60进制36)
		int term2 = MyLunar.getSolarTermDay(solarYear, 2); // 立春日期
		// 依节气调整二月分的年柱, 以立春为界
		if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
			cyclicalYear = (solarYear - 1900 + 36 - 1) % 60;
		} else {
			cyclicalYear = (solarYear - 1900 + 36) % 60;
		}

		// 干支月 1900年1月小寒以前为 丙子月(60进制12)
		int firstNode = MyLunar.getSolarTermDay(solarYear, solarMonth * 2); // 传回当月「节」为几日开始
		// 依节气月柱, 以「节」为界
		if (solarDay < firstNode) {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 12) % 60;
		} else {
			cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 13) % 60;
		}

		// 当月一日与 1900/1/1 相差天数
		// 1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
		cyclicalDay = (int) (MyLunar.UTC(solarYear, solarMonth, solarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
		this.cyclicalYear = cyclicalYear;
		this.cyclicalMonth = cyclicalMonth;
		this.cyclicalDay = cyclicalDay;
	}

	/**
	 * 取农历年生肖
	 * 
	 * @return 农历年生肖(例:龙)
	 */
	public String getAnimalString() {
		return MyLunar.Animals[(this.lunarYear - 4) % 12];
	}

	/**
	 * 返回公历日期的二十四节气字符串
	 * 
	 * @return 二十四节气字符串,若不是节气日,返回空串(例:冬至)
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
	 * 返回24节气的详细解释 get_the_24_solarterms
	 */
	public String get_24_SolarTerms() {
		String set_24_SolarTerms = "";
		if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			set_24_SolarTerms = "今日" + MyLunar.get_the_24_solarterms[solarMonth * 2 - 2];
		} else if (MyLunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			set_24_SolarTerms = "今日" + MyLunar.get_the_24_solarterms[solarMonth * 2 + 1 - 2];
		}
		return set_24_SolarTerms;
	}

	/**
	 * 取得干支历字符串
	 * 
	 * @return 干支历字符串(例:甲子年甲子月甲子日)
	 */
	public String getCyclicalDateString() {
		return this.getCyclicaYear() + "年" + this.getCyclicaMonth() + "月" + this.getCyclicaDay() + "日";
	}

	/**
	 * 年份天干
	 * 
	 * @return 年份天干
	 */
	public int getTiananY() {
		return MyLunar.getTianan(this.cyclicalYear);
	}

	/**
	 * 月份天干
	 * 
	 * @return 月份天干
	 */
	public int getTiananM() {
		return MyLunar.getTianan(this.cyclicalMonth);
	}

	/**
	 * 日期天干
	 * 
	 * @return 日期天干
	 */
	public int getTiananD() {
		return MyLunar.getTianan(this.cyclicalDay);
	}

	/**
	 * 年份地支
	 * 
	 * @return 年分地支
	 */
	public int getDeqiY() {
		return MyLunar.getDeqi(this.cyclicalYear);
	}

	/**
	 * 月份地支
	 * 
	 * @return 月份地支
	 */
	public int getDeqiM() {
		return MyLunar.getDeqi(this.cyclicalMonth);
	}

	/**
	 * 日期地支
	 * 
	 * @return 日期地支
	 */
	public int getDeqiD() {
		return MyLunar.getDeqi(this.cyclicalDay);
	}

	/**
	 * 取得干支年字符串
	 * 
	 * @return 干支年字符串
	 */
	public String getCyclicaYear() {
		// return (this.isLeapYear() ? "[闰]" :
		// "")+ExampleAppWidgetLunar.getCyclicalString(this.cyclicalYear);

		return MyLunar.getCyclicalString(this.cyclicalYear);

	}

	/**
	 * 取得干支月字符串
	 * 
	 * @return 干支月字符串
	 */
	public String getCyclicaMonth() {
		return MyLunar.getCyclicalString(this.cyclicalMonth);
	}

	/**
	 * 取得干支日字符串
	 * 
	 * @return 干支日字符串
	 */
	public String getCyclicaDay() {
		return MyLunar.getCyclicalString(this.cyclicalDay);
	}

	/**
	 * 返回农历日期字符串
	 * 
	 * @return 农历日期字符串
	 */
	public String getLunarDayString() {
		return MyLunar.getLunarDayString(this.lunarDay);
	}

	/**
	 * 返回农历月份字符串
	 * 
	 * @return 农历月份字符串
	 * 
	 *         是闰月则在前面加上[闰]
	 */
	public String getLunarMonthString() {
		// return (this.isLeap() ? "[闰]" : "") +
		// MyLunar.getLunarMonthString(this.lunarMonth);
		return MyLunar.getLunarMonthString(this.lunarMonth);
	}

	/**
	 * 返回农历年份字符串
	 * 
	 * @return 农历年份字符串
	 * 
	 *         是闰年则在前面加上[闰]
	 */
	public String getLunarYearString() {
		// return (this.isLeapYear() ? "[闰]" : "") +
		// MyLunar.getLunarYearString(this.lunarYear);
		return MyLunar.getLunarYearString(this.lunarYear);
	}

	/**
	 * 返回农历表示字符串
	 * 
	 * @return 农历字符串(例:甲子年正月初三)
	 */
	public String getLunarDateString() {
		return this.getLunarYearString() + "年" + this.getLunarMonthString() + "月" + this.getLunarDayString() + "日";
	}

	/**
	 * 农历年是否是闰月
	 * 
	 * @return 农历年是否是闰月
	 */
	public boolean isLeap() {
		return isLeap;
	}

	/**
	 * 农历年是否是闰年
	 * 
	 * @return 农历年是否是闰年
	 */
	public boolean isLeapYear() {
		return isLeapYear;
	}

	/**
	 * 当前农历月是否是大月
	 * 
	 * @return 当前农历月是大月
	 */
	public boolean isBigMonth() {
		return this.getMaxDayInMonth() > 29;
	}

	/**
	 * 当前农历月有多少天
	 * 
	 * @return 当前农历月有多少天
	 */
	public int getMaxDayInMonth() {
		return this.maxDayInMonth;
	}

	/**
	 * 当前农历已经过去多少天
	 * 
	 * @return 当前农历已经过去多少天
	 */
	public int getDaysSuminyear() {
		return this.DaysSuminyear + lunarDay;
	}

	/**
	 * 当前农历月有多少天
	 * 
	 * @return 当前农历月有多少天
	 */
	public int getMaxDaysInLunarYear() {
		return this.maxDaysInLunarYear;
	}

	/***
	 * 返回所在年 的闰月 没有则返回0
	 * 
	 * @return
	 */
	public int getlunarsMonthInYear() {
		return this.lunarsMonthInYear;

	}

	/**
	 * 农历日期
	 * 
	 * @return 农历日期
	 */
	public int getLunarDay() {
		return lunarDay;
	}

	/**
	 * 农历月份
	 * 
	 * @return 农历月份
	 */
	public int getLunarMonth() {
		return lunarMonth;
	}

	/**
	 * 农历年份
	 * 
	 * @return 农历年份
	 */
	public int getLunarYear() {
		return lunarYear;
	}

	/**
	 * 公历日期
	 * 
	 * @return 公历日期
	 */
	public int getSolarDay() {
		return solarDay;
	}

	/**
	 * 公历月份
	 * 
	 * @return 公历月份 (不是从0算起)
	 */
	public int getSolarMonth() {
		return solarMonth + 1;
	}

	/**
	 * 公历年份
	 * 
	 * @return 公历年份
	 */
	public int getSolarYear() {
		return solarYear;
	}

	/**
	 * 星期几
	 * 
	 * @return 星期几(星期日为:1, 星期六为:7)
	 */
	public int getDayOfWeek() {
		return this.solar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 黑色星期五
	 * 
	 * @return 是否黑色星期五
	 */
	public boolean isBlackFriday() {
		return (this.getSolarDay() == 13 && this.solar.get(Calendar.DAY_OF_WEEK) == 6);
	}

	/**
	 * 是否是今日
	 * 
	 * @return 是否是今日
	 */
	public boolean isToday() {
		Calendar clr = Calendar.getInstance();
		return clr.get(Calendar.YEAR) == this.solarYear && clr.get(Calendar.MONTH) == this.solarMonth
				&& clr.get(Calendar.DAY_OF_MONTH) == this.solarDay;
	}

	/**
	 * 取得公历节日名称
	 * 
	 * @return 公历节日名称,如果不是节日返回空串
	 */
	public String getSFestivalName() {
		return this.sFestivalName;
	}

	/**
	 * 取得农历节日名称
	 * 
	 * @return 农历节日名称,如果不是节日返回空串
	 */
	public String getLFestivalName() {
		return this.lFestivalName;
	}

	/**
	 * 是否是农历节日
	 * 
	 * @return 是否是农历节日
	 */
	public boolean isLFestival() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isLFestival;
	}

	/**
	 * 是否是公历节日
	 * 
	 * @return 是否是公历节日
	 */
	public boolean isSFestival() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isSFestival;
	}

	/**
	 * 是否是节日
	 * 
	 * @return 是否是节日
	 */
	public boolean isFestival() {
		return this.isSFestival() || this.isLFestival();
	}

	/**
	 * 是否是放假日
	 * 
	 * @return 是否是放假日
	 */
	public boolean isHoliday() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.isHoliday;
	}

	/**
	 * 其它日期说明
	 * 
	 * @return 日期说明(如:民国2年)
	 */
	public String getDescription() {
		if (!this.isFinded) {
			this.findFestival();
		}
		return this.description;
	}

	/**
	 * 干支字符串
	 * 
	 * @param cyclicalNumber
	 *            指定干支位置(数字,0为甲子)
	 * @return 干支字符串
	 * 
	 *         原来是：private
	 */
	public static String getCyclicalString(int cyclicalNumber) {
		return MyLunar.Tianan[MyLunar.getTianan(cyclicalNumber)] + MyLunar.Deqi[MyLunar.getDeqi(cyclicalNumber)];
	}

	/**
	 * 获得地支
	 * 
	 * @param cyclicalNumber
	 * @return 地支 (数字)
	 */
	private static int getDeqi(int cyclicalNumber) {
		return cyclicalNumber % 12;
	}

	/**
	 * 获得天干
	 * 
	 * @param cyclicalNumber
	 * @return 天干 (数字)
	 */
	private static int getTianan(int cyclicalNumber) {
		return cyclicalNumber % 10;
	}

	/**
	 * 返回指定数字的农历年份表示字符串
	 * 
	 * @param lunarYear
	 *            农历年份(数字,0为甲子)
	 * @return 农历年份字符串
	 */
	private static String getLunarYearString(int lunarYear) {
		return MyLunar.getCyclicalString(lunarYear - 1900 + 36);
	}

	/**
	 * 返回指定数字的农历月份表示字符串
	 * 
	 * @param lunarMonth
	 *            农历月份(数字)
	 * @return 农历月份字符串 (例:正)
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
	 * 返回指定数字的农历日表示字符串
	 * 
	 * @param lunarDay
	 *            农历日(数字)
	 * @return 农历日字符串 (例: 廿一)
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
	 * 古时的時辰時刻
	 * 
	 * @return
	 */
	static String getshichengshike() {
		String Hstr = null, Mstr = null;
		// Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
		// t.setToNow(); // 取得系统时间。
		// int hour = t.hour; // 0-23
		// int minute = t.minute;// 0-60
		double sum = hour + 0.01 * minute;
		if (sum < 1 || sum >= 23) {
			Hstr = Deqi[0] + "时" + "[三更]";
		}
		if (sum >= 1 && sum < 3) {
			Hstr = Deqi[1] + "时" + "[四更]";
		}
		if (sum >= 3 && sum < 5) {
			Hstr = Deqi[2] + "时" + "[五更]";
		}
		if (sum >= 5 && sum < 7) {
			Hstr = Deqi[3] + "时";
		}
		if (sum >= 7 && sum < 9) {
			Hstr = Deqi[4] + "时";
		}
		if (sum >= 9 && sum < 11) {
			Hstr = Deqi[5] + "时";
		}
		if (sum >= 11 && sum < 13) {
			Hstr = Deqi[6] + "时";
		}
		if (sum >= 13 && sum < 15) {
			Hstr = Deqi[7] + "时";
		}
		if (sum >= 15 && sum < 17) {
			Hstr = Deqi[8] + "时";
		}
		if (sum >= 17 && sum < 19) {
			Hstr = Deqi[9] + "时";
		}
		if (sum >= 19 && sum < 21) {
			Hstr = Deqi[10] + "时" + "[一更]";
		}
		if (sum >= 21 && sum < 23) {
			Hstr = Deqi[11] + "时" + "[二更]";
		}
		if (minute >= 14 && minute <= 15) {
			Mstr = "一刻";
		} else if (minute >= 28 && minute <= 30) {
			Mstr = "二刻";
		} else if (minute >= 42 && minute <= 45) {
			Mstr = "三刻";
		} else if (minute >= 48 && minute <= 60) {
			Mstr = "四刻";
		} else {
			Mstr = "";
		}
		return Hstr + Mstr;

	}

	static String getGyuK() {
		String Hstr = "", Mstr = "", HM = "";
		// Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
		// t.setToNow(); // 取得系统时间。
		// int hour = t.hour; // 0-23
		// int minute = t.minute;// 0-60
		double sum = hour + 0.01 * minute;
		if (sum >= 19 && sum < 21) {
			Hstr = "一更";
		} else if (sum >= 21 && sum < 23) {
			Hstr = "二更";
		} else if (sum < 1 || sum >= 23) {
			Hstr = "三更";
		} else if (sum >= 1 && sum < 3) {
			Hstr = "四更";
		} else if (sum >= 3 && sum < 5) {
			Hstr = "五更";
		} else {
			Hstr = "";
		}
		if (minute >= 14 && minute <= 15) {
			Mstr = "一刻";
		} else if (minute >= 28 && minute <= 30) {
			Mstr = "二刻";
		} else if (minute >= 42 && minute <= 45) {
			Mstr = "三刻";
		} else if (minute >= 48 && minute <= 60) {
			Mstr = "四刻";
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
	 * 判断是否是双休
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
	 * 详细的 公历时间：年月日星期时分秒毫秒
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
		// CharSequence mytime = DateFormat.format("yyyy年MM月dd日", mytimedata);
		if ("1".equals(way)) {
			way = "日";
		} else if ("2".equals(way)) {
			way = "一";
		} else if ("3".equals(way)) {
			way = "二";
		} else if ("4".equals(way)) {
			way = "三";
		} else if ("5".equals(way)) {
			way = "四";
		} else if ("6".equals(way)) {
			way = "五";
		} else if ("7".equals(way)) {
			way = "六";
		}
		return null;
		// return String.valueOf(mytime) + "星期" + way + hour + "时" + minute + "分" +
		// second + "秒" + millisecond + "毫";
	}

	/***
	 * 公历的另一种
	 * 
	 * @return
	 */
	// public String getYMDEtime() {
	// long mynewtime = System.currentTimeMillis();
	// 引号里面的内容是设置时间显示的格式 ,你可以随心所欲的修改
	// CharSequence YMDEtime = DateFormat.format("yyyy年MM月dd日 E", mynewtime);
	// lunarString5
	// return String.valueOf(YMDEtime);
	// }

	public String getHMStime() {
		long mynewtime = System.currentTimeMillis();
		// 引号里面的内容是设置时间显示的格式 ,你可以随心所欲的修改
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
	 * 返回月份的大写字 如：正月初一
	 * 
	 * @return
	 */
	public String getBigMoth() {
		// return (this.isLeap() ? "[闰]" : "") + lunarString3[lunarMonth -
		// 1]+"月"+getLunarDayString();

		return this.getLunarYearString() + lunarString3[lunarMonth - 1] + "月" + getLunarDayString();
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
	 * 返回星座
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
	 * [干支厉]农历
	 * 
	 * @return 详细的 生肖 [干支厉]农历 时辰
	 */
	public String getCyclicaAndgetLunar() {
		String Date = "[" + this.getAnimalString() + "]" + "[" + this.getCyclicaYear() + "]" + this.getLunarYearString()
				+ "年" + "[" + this.getCyclicaMonth() + "]" + this.getLunarMonthString() + "月" + "["
				+ this.getCyclicaDay() + "]" + this.getLunarDayString() + "日" + this.getshichengshike();
		return Date;
	}

	/***
	 * 公历节日+农历节日+24节气
	 * 
	 * @return
	 */
	public String getTermSLFestival() {
		String str = getSFestivalName() + getLFestivalName() + getTermString();
		return str;
	}

	/**
	 * 取农历年生肖和是否是闰年
	 * 
	 * @return 农历年生肖(例:[鸡|闰])
	 */
	public String getAnimalALeapString() {

		return "[" + MyLunar.Animals[(this.lunarYear - 4) % 12] + (this.isLeapYear() ? "|闰" : "") + "]";
	}

	/***
	 * 生肖+是否闰年+生辰八字
	 */
	public String getshengchenbazi() {
		String shengchenbazi = "";
		try {
			// 创建一个java.util.Date 对象，并设置好他的时间。。。
			Date date = new Date();
			// 创建格式化类，设定格式为 yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long mytime = System.currentTimeMillis();
			// CharSequence time = DateFormat.format("yyyy-MM-dd HH:mm:ss", mytime);
			// String date_newtime = ""+time;//String.valueOf(time)
			// 将date类的时间赋上具体值
			// date = sdf.parse(date_newtime);
			// 新建一个bazi对象，传 入Date参数
			// MyBazi test = new MyBazi(date);
			// 根据需要输出相对应的值 如下方法输出的是 String 类型完整八字
			// System.out.print(test.getbazi());MyBaZi()
			// shengchenbazi = test.getbazi() + getGyuK();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAnimalALeapString() + shengchenbazi;
	}

	/***
	 * 判断公历是否是闰年
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
	 * 指定月份多少天
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
	 * 指定年多少天
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
	 * 返回公历天的详细信息
	 * 
	 * @return
	 */
	public String getdayofnewyear() {
		Calendar clr = Calendar.getInstance();
		int y = clr.get(Calendar.YEAR);
		int d = clr.get(Calendar.DAY_OF_YEAR);
		int m = clr.get(Calendar.MONTH) + 1;
		String S = " 公历年" + this.issumnewyear(y) + "天  第" + d + "天  " + lunarString6[m - 1] + "月" + getDaysOfMonth(y, m)
				+ "天";
		;
		return S;

	}

	public String getRequestFocus() {
		 //2017年01月31 周六 公历年365天第58天这月28天 农历鸡年384天第31天这月30天 闰六月
		// String mRF = getDescription() +" 公元"+getYMDEtime()+getdayofnewyear()+"农历"+getAnimalString()+"年"+getMaxDaysInLunarYear() + "天 第" +getDaysSuminyear() + "天 " +lunarString3[lunarMonth - 1]+"月"+ getMaxDayInMonth()+"天 ";
		if (this.isLeapYear()) {
			// mRF += " 闰"+lunarString3[getlunarsMonthInYear() - 1 ]+"月";
		}
		// return mRF+" "+get_24_SolarTerms();
		return null;
	}

}