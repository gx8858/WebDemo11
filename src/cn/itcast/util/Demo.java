package cn.itcast.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Demo {
	
	@Test
	public void run() throws IOException{
		File f = new File("../hello.text");
		System.out.println(f.getAbsolutePath());
		// ��ȡ�ļ��ľ���·��
		System.out.println(f.getCanonicalPath());
	}
	
}
