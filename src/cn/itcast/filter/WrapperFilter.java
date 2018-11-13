package cn.itcast.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class WrapperFilter
 */
public class WrapperFilter implements Filter {

    
	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		// 在放行执行掉包
		MyRequest myreq = new MyRequest(req);
		chain.doFilter(myreq, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
