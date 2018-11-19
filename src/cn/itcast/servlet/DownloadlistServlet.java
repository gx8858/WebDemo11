package cn.itcast.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.util.DownloadUtil;

/**
 * �ļ�����
 */
public class DownloadlistServlet extends HttpServlet {

	private static final long serialVersionUID = -4013387831215272150L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������������������
		String path = request.getParameter("path");
		// get������������������⣨����Ͳ�����������Ϊ�����˹���������ǿHttpServletRequest����
//		path = new String(path.getBytes("ISO-8859-1"),"UTF-8");
		// d:\\root\\aa\\xxx.mp3
		// �Լ���ȡ�ļ�������
		int index = path.lastIndexOf("\\");
		// ��ȡ
		String filename = path.substring(index + 1);
		// ��д�ļ�������
		// ��ȡMIME������
		String type = getServletContext().getMimeType(filename);
		// ����ͷһ����
		response.setContentType(type);
		
		// �Ȼ�ȡ���������Ϣ
		String agent = request.getHeader("User-Agent");
		// Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0  ��������	Base64����
		// Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36  �ȸ�
		// Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)	IE������� ʹ��URL����
		// System.out.println(agent);
		
		// �ж���ʲô�����
		if(agent.contains("Firefox")){
			// ʹ��Base64����
			filename = DownloadUtil.base64EncodeFileName(filename);
		}else{
			// IE���߹ȸ������� URL����
			filename = URLEncoder.encode(filename, "UTF-8");
			// �ѿո�����+��
			filename = filename.replace("+", " ");
		}
		
		// ���߼򵥷������������ҵģ�
//		if (agent.indexOf("Firefox") > 0) {
//			
//			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1"); // firefox�����
//		
//		} else if (agent.toUpperCase().indexOf("MSIE") > 0) {
//			
//			filename = URLEncoder.encode(filename, "UTF-8");// IE�����
//			filename = filename.replace("+", " ");
//			
//		} else if (agent.indexOf("Chrome") > 0) {
//
//			filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");// �ȸ�
//		}
		
		
		// ֱ������
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// һ��������
		InputStream in = new BufferedInputStream(new FileInputStream(path));
		// �����
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		int len = 0;
		byte [] b = new byte[1024];
		while((len = in.read(b)) != -1){
			os.write(b, 0, len);
		}
		in.close();
		os.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
