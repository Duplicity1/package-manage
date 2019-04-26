package com.fxdigital.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StringUtils
{

	private static final DecimalFormat DF = new DecimalFormat("###,###,###,###.00");
	//一万以内
	private static final DecimalFormat DF_MILLION = new DecimalFormat("#####0.00");


	/**
	 *
	 * @Description: 将 fastjson的JSONArray转化为泛型列表
	 * @param jsonArray 源数据
	 * @param clz 泛型类
	 * @return List<T>
	 * @author mayn
	 * @date 2018年5月29日下午1:58:17
	 */
	public static <T> List<T> convertJSONArrayToTypeList(JSONArray jsonArray, Class<T> clz){
		if (CollectionUtils.isEmpty(jsonArray)){
			return new ArrayList<T>();
		}
		List<T> result = new ArrayList<T>(jsonArray.size());
		jsonArray.forEach(element->{
			// 基础类型不可以转化为JSONObject，需要特殊处理
			if (element instanceof String
					|| element instanceof Number
					|| element instanceof Boolean
					){
				result.add((T)element);
			}else {
				T t = JSONObject.toJavaObject((JSONObject)element, clz);
				result.add(t);
			}
		});
		return result;
	}

	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * 
	 * @param valStr
	 * @return String[]
	 */
	public static String[] strList(String valStr)
	{
		int i = 0;
		String TempStr = valStr;
		String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
		valStr = valStr + ",";
		while (valStr.indexOf(',') > 0)
		{
			returnStr[i] = valStr.substring(0, valStr.indexOf(','));
			valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

			i++;
		}
		return returnStr;
	}

	/**
	 * 验证string串是否null或"" 是空返回true
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isStringBlankOrNull(String value)
	{
		if (value == null || "".equals(value) || "null".equals(value))
			return true;
		return false;
	}

	/**
	 * 生成32位UUID，并去除横杠
	 */
	public static String generateUUID()
	{

		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String null2string(Object obj)
	{
		if (obj == null)
			return "";
		String str = String.valueOf(obj);
		str = str.trim();
		return str;
	}

	public static boolean isNotEmpty(Object obj)
	{
		if (obj == null)
			return false;
		return true;
	}

	public static boolean isEmpty(Object obj)
	{
		if (obj == null)
			return true;
		return false;
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s)
	{
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s)
	{
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * 随机生成数字
	 * @param len
	 * @return
	 */
	public static String getFixedRandomStr(int len)
	{
		Random rm = new Random();
		double pross = (1 + rm.nextDouble()) * Math.pow(10, len);
		String fixLenthString = String.valueOf(pross);
		return fixLenthString.substring(1, len + 1);
	}

	public static String formatMoney(BigDecimal money)
	{
		if (money == null || money.doubleValue() == 0)
		{
			return "0.00";
		}
		if (money.doubleValue() >= 10000)
		{
			return DF.format(money.doubleValue());
		}
		else
		{
			return DF_MILLION.format(money.doubleValue());
		}
	}

	/**
	 * 数据字串前补0.长度小于数据字串将原数返回
	 * 
	 * @param numStr
	 *            传入字串
	 * @param digitNum
	 *            返回字串的长度
	 * @return
	 */
	public static String beforeAppendZero(String numStr, int digitNum)
	{
		StringBuffer sb = new StringBuffer();
		if (null != numStr)
		{
			int strNum = numStr.length();
			for (int i = digitNum; i > strNum; i--)
			{
				sb.append("0");
			}
			sb.append(numStr);
		}
		else
		{
			for (int i = 0; i < digitNum; i++)
			{
				sb.append("0");
			}
		}
		return sb.toString();
	}

}
