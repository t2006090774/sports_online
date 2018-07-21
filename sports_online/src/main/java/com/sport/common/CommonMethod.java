package com.sport.common;

import com.sport.common.constant.Constant;
import com.sport.entity.base.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author a_kai
 */
public class CommonMethod {  
	
	/**
	 * POJO转JOSN
	 * @param
	 */
	public static String pojoTransformJson(Object o) {
		JSONObject jsonObject = JSONObject.fromObject(o);
		return jsonObject.toString();
	}
	
	/**
	 * List转JOSN
	 */
	public static <T> String listTransformJson(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 获取创建时间
	 */
	public static String getCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date()).toString();
	}
	
	/**
	 * 获取详细时间 精确到秒
	 */
	public static String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date()).toString();
	}

	/**
	 * 判断是否是apk,false 不是一个apk
	 */
	public static boolean checkApk(String apkName){
		if(apkName.indexOf(".apk")!=(-1)){
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是txt,false 不是一个apk
	 */
	public static boolean checkTxt(String apkName){
		if(apkName.indexOf(".txt")!=(-1)){
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是指定的图片格式
	 */
	public static boolean checkPic(String pictureName){
		for(String s:Constant.picTypeArr){
			if(pictureName.indexOf(s)!=(-1)){
				return false;
			}
		}
		return true;
	}

	public static byte[] base64ToByte(String base64,String picName) {
		byte[] encodeBase64 = null;
		try{
			encodeBase64 = Base64.decodeBase64(base64.getBytes("UTF-8"));
			System.out.println("Result: " + new String(encodeBase64));
			String imgFilePath = Constant.PIC_URL_CONTOS;// 新生成的图片+Constant.SERVER_PORT_NUMBER+"\\"
			OutputStream out = new FileOutputStream(imgFilePath+picName);
			out.write(encodeBase64);
			out.flush();
			out.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return encodeBase64;
	}

	/**
	 * 判断该字符串是否为中文
	 * @param string
	 * @return
	 */
	public static boolean isChinese(String string){
		int n = 0;
		for(int i = 0; i < string.length(); i++) {
			n = (int)string.charAt(i);
			if(!(19968 <= n && n <40869)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否为数字
	 */
	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){
//			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * checkName
	 */

	public static Result checkName(String username){
		Result result = new Result();
		if(username.length()<5||username.length()>19){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("用户名长度应大于6位且小于19位！");
			return result;
		}
		if(CommonMethod.isChinese(username)){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("用户名不可包含中文！");
			return result;
		}
		if(CommonMethod.isNumeric(username.substring(0,1))){
			result.setCode(Constant.NUMBER_ZERO);
			result.setLog("用户名首字母不可为数字！");
			return result;
		}
		return null;
	}

	/**
	 * list转string
	 */
	public String listToString(List list, char separator) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(),separator);
	}
}  