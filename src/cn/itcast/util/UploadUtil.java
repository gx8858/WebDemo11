package cn.itcast.util;

public class UploadUtil {
	
	/**
	 * ͨ��Ψһ���ļ����ƣ���ȡ��Ӧ��Ŀ¼��������Ŀ¼��
	 * @param filename
	 * @return
	 */
	public static String getPath(String filename){
		// ��filenameת��int
		int code = filename.hashCode();
		// ��0xf����&����
		int dir1 = code & 0xf;
		// ��code����4λ
		int dir2 = (code >>> 4) & 0xf;
		return "/"+dir1+"/"+dir2;
	}

}
