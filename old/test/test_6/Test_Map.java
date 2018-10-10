package test_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test_Map {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("b","4");
		map.put("a","5");
		map.put("c","3");
		map.put("d","5");
		
		//ͨ��map.keySet()����
		//����һ��ͨ���õ�key��ֵ��Ȼ���ȡvalue;
		/*for(String key : map.keySet()){
			String value = map.get(key);
			System.out.println(key+"  "+value);
		}*/
		//ʹ�õ���������ȡkey;
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key=iter.next();
			String value = map.get(key);
			System.out.println("key"+key+" "+value);
		}
		//ͨ��map.entrySet()����
		//����һ��ѭ��map�����ÿһ�Լ�ֵ�ԣ�Ȼ���ȡkey��value
		for(Entry<String, String> vo : map.entrySet()){
			vo.getKey();
			vo.getValue();
			System.out.println("key  "+vo.getKey()+"  "+vo.getValue());
		}
		
		/*//ʹ�õ���������ȡkey
		Iterator<Entry<String,String>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String,String> entry = iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+" "+value);
		}*/
		
		//��map<String,String> ת��ΪArryList,��list�����Ԫ��ΪEntry<String,String>
		List<Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
		Collections.sort(list,new Comparator<Entry<String,String>>(){
			@Override
			public int compare(Entry<String, String> o1,
					Entry<String, String> o2) {
				int flag = o1.getValue().compareTo(o2.getValue());
				if(flag==0){
					return o1.getKey().compareTo(o2.getKey());
				}
				return flag;
			}
		});
		//����list�õ�map����������Ԫ��
		for(Entry<String, String> en : list){
			System.out.println(en.getKey()+" "+en.getValue());
		}
		
	}

}
 