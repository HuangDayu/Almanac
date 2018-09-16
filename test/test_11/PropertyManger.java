package test_11;
import java.io.IOException;
import java.util.Properties;

/***
 * 配置文件处理类 设计模式：Singleton模式（单例模式）：在内存中，只需要有该类的一个类的对象的实例
 */
public class PropertyManger {

	/***
	 * 工厂模式：专门用于产生对象的类（专职于某种工作） 本类私有的对象，其他类不是new，只能通过调用方法来创建对象
	 */
	private static PropertyManger mgr = null;

	/***
	 * Properties 类表示了一个持久的属性集。
	 */
	static Properties properties = new Properties();;

	/**
	 * 编译该类则执行该方法，只有虚拟机退出时垃圾收集器才清理回收此内存
	 */
	static {
		/***
		 * 使用ClassLoader()的文件流获取配置文件，Loader到内存中（只读的缓存）,直接从内存中读取，可以提高效率（可以省去读取硬盘中文件的时间）
		 * 不能在静态上下文中使用 this
		 */
		try {
			// properties.load(this.getClass().getClassLoader().getResourceAsStream("config/tank.properties"));
			properties.load(PropertyManger.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/***
	 * 获取配置文件中指定键（key）的对应的值（Volume）
	 * 
	 * @param key
	 *            键
	 * @return 值（字符串型）
	 */
	public static String getProperties(String key) {
		/**
		 * 用指定的键在此属性列表中搜索属性。 在配置文件中，搜索对应的key，并返回该key对应的值Volume（字符串类型），强制转换为整型
		 */
		return properties.getProperty(key);
	}

	/**
	 * 获取配置文件中指定键（key）的对应的值（Volume）
	 * 
	 * @param 键
	 * @return 值（整型）
	 */
	public static int getPropertiesVolome(String key) {
		/**
		 * 用指定的键在此属性列表中搜索属性。 在配置文件中，搜索对应的key，并返回该key对应的值Volume（字符串类型），强制转换为整型
		 */
		int PropertiesVolome = Integer.parseInt(properties.getProperty(key));
		return PropertiesVolome;
	}

	/**
	 * 私有的构造方法，谁都不能new我的对象 只能使用 "类名.静态方法名" 对该类中的方法进行调用
	 */
	private PropertyManger() {

	}

	/**
	 * 静态工厂方法：用于生产对象 用于创建对象的方法，创建对象的过程可以加入自己的处理
	 * 
	 * @return 返回对象的实例
	 */
	public static PropertyManger getInstance() {
		if (mgr == null) {
			mgr = new PropertyManger();
		}
		return mgr;
	}

}
