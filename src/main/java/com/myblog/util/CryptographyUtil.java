package com.myblog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 * @author lizhen
 *
 */
public class CryptographyUtil {

	
	/**
	 * Md5����
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="lizhen1995";
		
		System.out.println("Md5���ܣ�"+CryptographyUtil.md5(password, "lizhen1995"));
	}
}
