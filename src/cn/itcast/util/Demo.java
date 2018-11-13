package cn.itcast.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Demo {
	
	@Test
	public void run() throws IOException{
		File f = new File("../hello.text");
		System.out.println(f.getAbsolutePath());
		// 获取文件的绝对路径
		System.out.println(f.getCanonicalPath());
	}
	
}
