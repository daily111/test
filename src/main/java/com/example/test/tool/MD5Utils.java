package com.example.test.tool;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5加密工具
 */
public class MD5Utils {

	private static final String SALT = "1qazxsw2";
	
	private static final String ALGORITH_NAME = "md5";
	
	private static final int HASH_ITERATIONS = 2;

	/**
	 * 使用md5生成加密后的密码
	 * @return
	 */
	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	/**
	 * 使用md5生成加密后的密码
	 * @return
	 */
	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
}
