package com.spring.cloud.common.util;

import java.security.MessageDigest;

public class EncryptUtil {
	public static void main(String[] args) {
		String key = getCode("oxcQvs3Uzw7B5L6pY1e2rhGMb4iE", "123");
		System.out.println(key);
		System.out.println("747846fb07");
	}

	public static String getCode(String s, String key) {
		try {
			if(md5(sha1(s + '_' + key))!=null){
				return md5(sha1(s + '_' + key));
			}else {
				return "";
			}
		}catch (NullPointerException e){
			return "";
		}


	}

	public static String md5(String s) {
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String sha1(String s) {
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("SHA1");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int a = md[i];
				if (a < 0) {
					a += 256;
				}
				if (a < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(a));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
}