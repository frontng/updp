package com.upanda.updp.common.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

 
/**
 * MD5加密工具类
 * @date 2017年9月1日
 * @author Front Ng
 *
 */
public class MD5Utils {

	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}

	public static String md5(String source) {
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}
	
	/**
	 * 随机生成一个盐值
	 * @return
	 */
	public static  String createRandomSaltValue(){
		return MD5Utils.md5(UUID.randomUUID().toString());
	}
	
	/**
	 * MD5加盐
	 * @param md5Password
	 * @param salt
	 * @return
	 */
	public static String md5AndSalt(String md5Password,String salt) {
		if(StringUtils.isEmpty(salt)) {
			salt=createRandomSaltValue();
		}
		return md5(md5Password+salt);
	}

}