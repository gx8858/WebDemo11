package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrapperServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4835496744139076709L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���Ѿ���Tomcatʵ����ǿ��
		// MyRequest myreq = new MyRequest(request);
		
		// ��ȡ��������
		String username = request.getParameter("username");
		System.out.println("GET��ʽ��"+username);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MyRequest myreq = new MyRequest(request);
		// ��ȡ��������Ľ��
		String username = request.getParameter("username");
		System.out.println("POST��ʽ��"+username);
	}

}
