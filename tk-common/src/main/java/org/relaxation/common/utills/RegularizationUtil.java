package org.relaxation.common.utills;




import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 正则验证工具类
 *
 */
public class RegularizationUtil {

	
	/**
	 * seq需要验证的字符 ，reg正则表达式
	 * 
	 * @param reg
	 * @return
	 */
	public static boolean rularization(String rex, String reg) {
		boolean result = false;
		if (rex != null && reg != null && !"".equals(rex) && !"".equals(reg)) {
			Pattern pattern = Pattern.compile(rex);
			Matcher matcher = pattern.matcher(reg);
			return matcher.find();
		}
		return result;
	}
	
	/**
	 * 验证是否包含script
	 * @param arg
	 * @return
	 */
	public static boolean scriptVetiry(String arg){
		if(StringUtils.isEmpty(arg)){
			return false;
		}
		if(arg.indexOf("<script>")>=0){
			return false;
		}
		return true;
	}
	
	/**
	 * 邮箱验证
	 * @param email
	 * @return
	 */
	public static boolean emailVerify(String email){
		String rex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		return rularization(rex, email);
	}
	
	/**
	 * 验证手机号码
	 * @param phone
	 * @return
	 */
	public static boolean phoneVerify(String phone){
		String rex = "\\d{11}";
		return rularization(rex, phone);
	}
	
	
	public static boolean numberVerify(String number){
		String rex = "^\\d+(\\.\\d+)?$";
		return rularization(rex, number);
	}

	public static boolean integerVerify(String number){
		String rex = "^\\d+$";
		return rularization(rex, number);
	}

	public static boolean strDateVerify(String strDate){
		String rex = "^\\d{4}/\\d{1,2}/\\d{1,2}$";
		return rularization(rex, strDate);
	}

	public static void main(String[] args) {
		System.out.println(strDateVerify("201/8/01"));
	}

}
