package cn.itcast.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 增强的类
 */
public class MyRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	
	// 自己想增强方法
	/**
	 * 调用该方法，不要关注post还是get，把编码问题全部都解决
	 */
	public String getParameter(String name) {
		// 判断当前是请求的方式
		String method = request.getMethod();
		// get请求
		if("get".equalsIgnoreCase(method)){
			// 需要自己获取到参数的内容，手动的编码
			String param = request.getParameter(name);
			try {
				param = new String(param.getBytes("ISO-8859-1"),"UTF-8");
				return param;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}else if("post".equalsIgnoreCase(method)){
			// 处理post方式
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 不能删除 返回的内容就是编码过后的内容
		return super.getParameter(name);
	}

}
