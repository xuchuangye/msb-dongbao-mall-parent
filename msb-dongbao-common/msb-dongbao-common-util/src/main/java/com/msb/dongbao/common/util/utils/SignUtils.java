package com.msb.dongbao.common.util.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具类
 *
 * @author xcy
 * @date 2022/9/22 - 11:15
 */
public class SignUtils {

	private static final String appSecret = "fhaiehrfaiw";

	/**
	 * 使用Md5算法根据传递的参数map生成签名
	 *
	 * @param map
	 * @return
	 */
	public static String generatorSign(Map<String, Object> map) {
		map.remove("sign");
		//字典序
		Map<String, Object> sortMap = returnSortedMap(map);
		//转成其他格式
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
			stringBuilder.append(entry.getKey()).append(",").append(entry.getValue()).append("#");
		}
		//拼接appSecret
		stringBuilder.append("secret").append(appSecret);
		//md5算法
		return MD5Util.md5(stringBuilder.toString());
	}

	private static class MyMapComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}

	/**
	 * 将原始Map按照字典序进行排序
	 *
	 * @param map 原始Map
	 * @return sortedMap
	 */
	public static Map<String, Object> returnSortedMap(Map<String, Object> map) {
		Map<String, Object> sortMap = new TreeMap<>(new MyMapComparator());
		if (!map.isEmpty()) {
			sortMap.putAll(map);
		}
		return sortMap;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		/*map.put("ga", 1);
		map.put("az", "张三");
		map.put("zh", "张三");
		System.out.println(map);*/
		map.put("appId", 1);
		map.put("name", "张三");
		//map.put("timestamp", 1663833376918L);

		//long l = System.currentTimeMillis() - 20 * 1000;
		//System.out.println(l);

		Map<String, Object> sortedMap = returnSortedMap(map);
		System.out.println(sortedMap);

		String sign = generatorSign(sortedMap);
		//8c3a3c1513768658e707f78eff3fec04
		System.out.println(sign);
	}
}
