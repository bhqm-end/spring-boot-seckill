package spring.mooc.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	/**
	 * 第一次MD5
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	private static final String salt = "1a2b3c4d";

	/**
	 *  用户输入密码转换为  服务端密码
	 * @param inputPass
	 * @return
	 */
	public static String inputPassToFormPass(String inputPass) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
//		System.out.println(str);
		return md5(str);
	}

	/**
	 * 服务端密码转换为 数据库密码
	 * @param formPass
	 * @param salt
	 * @return
	 */
	public static String formPassToDBPass(String formPass, String salt) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}


	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <10 ; i++) {
			System.out.println(inputPassToFormPass("123456"+i));//d3b1294a61a07da9b49b6e22b2cbd7f9
			System.out.println(formPassToDBPass(inputPassToFormPass("123456"+i), "1a2b3c4d"));
			System.out.println(inputPassToDbPass("123456"+i, "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
		}

	}






}
