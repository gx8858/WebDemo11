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
 * 文件下载
 * @author Administrator
 *
 */
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 422987322623725234L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1.获取请求的参数
		 * 2.一个流（获取该文件的输入流）
		 * 3.设置两个头信息
		 * 4.io流拷贝
		 */
		// 获取请求的参数
		String filename = request.getParameter("filename");
		// 获取download文件夹的绝对磁盘路径
		String path = getServletContext().getRealPath("/download");
		// 创建输入流
		InputStream in = new FileInputStream(path+"/"+filename);
		// 设置两个响应头信息
		// Content-Type : 值是什么	text/html;charset=UTF-8;
		// 在ServletContext对象中，提供一个方法，通过文件的名称自动的获取文件的MIME的类型
		String type = getServletContext().getMimeType(filename);
		// 设置文件类型
		response.setContentType(type);
		// 设置另一个头（以附件的形式打开文件）
		response.setHeader("Content-Disposition", "attachment;filename="+filename);
		// IO的拷贝
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
