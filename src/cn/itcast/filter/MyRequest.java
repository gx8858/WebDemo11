package cn.itcast.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * ��ǿ����
 */
public class MyRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	
	// �Լ�����ǿ����
	/**
	 * ���ø÷�������Ҫ��עpost����get���ѱ�������ȫ�������
	 */
	public String getParameter(String name) {
		// �жϵ�ǰ������ķ�ʽ
		String method = request.getMethod();
		// get����
		if("get".equalsIgnoreCase(method)){
			// ��Ҫ�Լ���ȡ�����������ݣ��ֶ��ı���
			String param = request.getParameter(name);
			try {
				param = new String(param.getBytes("ISO-8859-1"),"UTF-8");
				return param;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}else if("post".equalsIgnoreCase(method)){
			// ����post��ʽ
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// ����ɾ�� ���ص����ݾ��Ǳ�����������
		return super.getParameter(name);
	}

}
