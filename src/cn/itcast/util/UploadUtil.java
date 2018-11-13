package cn.itcast.util;

public class UploadUtil {
	
	/**
	 * 通过唯一的文件名称，获取对应的目录（二级的目录）
	 * @param filename
	 * @return
	 */
	public static String getPath(String filename){
		// 把filename转成int
		int code = filename.hashCode();
		// 和0xf进行&操作
		int dir1 = code & 0xf;
		// 把code右移4位
		int dir2 = (code >>> 4) & 0xf;
		return "/"+dir1+"/"+dir2;
	}

}
