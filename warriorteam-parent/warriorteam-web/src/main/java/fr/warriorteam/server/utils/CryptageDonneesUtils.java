package fr.warriorteam.server.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO
 * @author Yvan
 * Date : 02/11/2011
 */
public class CryptageDonneesUtils {

	private static final String HEX_DIGITS = "0123456789abcdef";

	/**
	 * TODO
	 * @param target
	 * @return
	 */
	public static String crypt(String target) {
		if (target != null) {
			try {
				MessageDigest msgDigest = MessageDigest.getInstance("MD5");
				msgDigest.update(target.getBytes());
				byte[] digest = msgDigest.digest();

				return toHexString(digest);

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return null;
	}

	/**
	 * TODO
	 * @param v
	 * @return
	 */
	private static String toHexString(byte[] v) {

		StringBuffer sb = new StringBuffer(v.length * 2);
		for (int i = 0; i < v.length; i++) {
			int b = v[i] & 0xFF;
			sb.append(HEX_DIGITS.charAt(b >>> 4)).append(
					HEX_DIGITS.charAt(b & 0xF));
		}
		return sb.toString();
	}
}
