package com.msb.dongbao.common.util.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.msb.dongbao.common.base.entity.SignDTO;

import java.util.*;

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
	public static String generatorSign(Map<String, String> map) {
		map.remove("sign");
		//字典序
		Map<String, String> sortMap = returnSortedMap(map);
		//转成其他格式
		StringBuilder stringBuilder = new StringBuilder();
		for (Map.Entry<String, String> entry : sortMap.entrySet()) {
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
	public static Map<String, String> returnSortedMap(Map<String, String> map) {
		Map<String, String> sortMap = new TreeMap<>(new MyMapComparator());
		if (!map.isEmpty()) {
			sortMap.putAll(map);
		}
		return sortMap;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		/*map.put("ga", 1);
		map.put("az", "张三");
		map.put("zh", "张三");
		System.out.println(map);*/
		map.put("appId", "1");
		map.put("name", "张三");
		//map.put("timestamp", 1663833376918L);

		//long l = System.currentTimeMillis() - 20 * 1000;
		//System.out.println(l);

		Map<String, String> sortedMap = returnSortedMap(map);
		System.out.println(sortedMap);

		String sign = generatorSign(sortedMap);
		//8c3a3c1513768658e707f78eff3fec04
		System.out.println(sign);
	}

	/**
	 * 校验签名
	 *
	 * @param signDTO 生成签名的实体类
	 * @return
	 */
	public static boolean checkSign(SignDTO signDTO) {
		JSONObject object = JSONUtil.parseObj(signDTO);
		//将请求参数的请求体中的实体类转换成Map
		Map<String, String> map = Convert.toMap(String.class, String.class, object);
		//对Map进行排序
		String signClient = map.get("sign");
		//sign不参与签名的生成
		map.remove("sign");
		Map<String, String> sortedMap = returnSortedMap(map);
		//生成sign
		String signServer = generatorSign(sortedMap);
		//校验sign
		return signServer.equals(signClient);
	}

	/**
	 * @param map
	 * @return
	 */
	public static boolean checkSign(SortedMap<String, String> map) {
		String sign = map.get("sign");
		map.remove("sign");
		String signServer = SignUtils.generatorSign(map);
		return signServer.equals(sign);
	}
}
