package cn.itcast.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �ļ�����
 * @author Administrator
 *
 */
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 422987322623725234L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.��ȡ����Ĳ���
		 * 2.һ��������ȡ���ļ�����������
		 * 3.��������ͷ��Ϣ
		 * 4.io������
		 */
		// ��ȡ����Ĳ���
		String filename = request.getParameter("filename");
		// ��ȡdownload�ļ��еľ��Դ���·��
		String path = getServletContext().getRealPath("/download");
		// ����������
		InputStream in = new FileInputStream(path+"/"+filename);
		// ����������Ӧͷ��Ϣ
		// Content-Type : ֵ��ʲô	text/html;charset=UTF-8;
		// ��ServletContext�����У��ṩһ��������ͨ���ļ��������Զ��Ļ�ȡ�ļ���MIME������
		String type = getServletContext().getMimeType(filename);
		// �����ļ�����
		response.setContentType(type);
		// ������һ��ͷ���Ը�������ʽ���ļ���
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		// IO�Ŀ���
		OutputStream os = response.getOutputStream();
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
