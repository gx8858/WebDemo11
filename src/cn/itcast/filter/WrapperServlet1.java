package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrapperServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = -4835496744139076709L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ��ȡ��������
		String username = request.getParameter("username");
		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("GET��ʽ��"+username);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ��ȡ��������Ľ��
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		System.out.println("POST��ʽ��"+username);
	}

}
