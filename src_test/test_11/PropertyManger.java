package test_11;
import java.io.IOException;
import java.util.Properties;

/***
 * �����ļ������� ���ģʽ��Singletonģʽ������ģʽ�������ڴ��У�ֻ��Ҫ�и����һ����Ķ����ʵ��
 */
public class PropertyManger {

	/***
	 * ����ģʽ��ר�����ڲ���������ࣨרְ��ĳ�ֹ����� ����˽�еĶ��������಻��new��ֻ��ͨ�����÷�������������
	 */
	private static PropertyManger mgr = null;

	/***
	 * Properties ���ʾ��һ���־õ����Լ���
	 */
	static Properties properties = new Properties();;

	/**
	 * ���������ִ�и÷�����ֻ��������˳�ʱ�����ռ�����������մ��ڴ�
	 */
	static {
		/***
		 * ʹ��ClassLoader()���ļ�����ȡ�����ļ���Loader���ڴ��У�ֻ���Ļ��棩,ֱ�Ӵ��ڴ��ж�ȡ���������Ч�ʣ�����ʡȥ��ȡӲ�����ļ���ʱ�䣩
		 * �����ھ�̬��������ʹ�� this
		 */
		try {
			// properties.load(this.getClass().getClassLoader().getResourceAsStream("config/tank.properties"));
			properties.load(PropertyManger.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/***
	 * ��ȡ�����ļ���ָ������key���Ķ�Ӧ��ֵ��Volume��
	 * 
	 * @param key
	 *            ��
	 * @return ֵ���ַ����ͣ�
	 */
	public static String getProperties(String key) {
		/**
		 * ��ָ���ļ��ڴ������б����������ԡ� �������ļ��У�������Ӧ��key�������ظ�key��Ӧ��ֵVolume���ַ������ͣ���ǿ��ת��Ϊ����
		 */
		return properties.getProperty(key);
	}

	/**
	 * ��ȡ�����ļ���ָ������key���Ķ�Ӧ��ֵ��Volume��
	 * 
	 * @param ��
	 * @return ֵ�����ͣ�
	 */
	public static int getPropertiesVolome(String key) {
		/**
		 * ��ָ���ļ��ڴ������б����������ԡ� �������ļ��У�������Ӧ��key�������ظ�key��Ӧ��ֵVolume���ַ������ͣ���ǿ��ת��Ϊ����
		 */
		int PropertiesVolome = Integer.parseInt(properties.getProperty(key));
		return PropertiesVolome;
	}

	/**
	 * ˽�еĹ��췽����˭������new�ҵĶ��� ֻ��ʹ�� "����.��̬������" �Ը����еķ������е���
	 */
	private PropertyManger() {

	}

	/**
	 * ��̬���������������������� ���ڴ�������ķ�������������Ĺ��̿��Լ����Լ��Ĵ���
	 * 
	 * @return ���ض����ʵ��
	 */
	public static PropertyManger getInstance() {
		if (mgr == null) {
			mgr = new PropertyManger();
		}
		return mgr;
	}

}
