package cn.itcast.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;

/**
 * ���صĹ����ࣨ���ƻ����������ı��룩
 * @author Administrator
 *
 */
public class DownloadUtil {
	
	/**
	 * �����base64
	 * @param fileName
	 * @return
	 */
	public static String base64EncodeFileName(String filename) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?"
					+ new String(base64Encoder.encode(filename
							.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
