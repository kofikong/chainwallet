package com.chaindemo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.chaindemo.demo.model.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String contextPath = session.getServletContext().getContextPath();
		
		String[] requireAuthPages = new String[] {"/wallet"};
		
		String uri = request.getRequestURI();
		if(!StringUtils.isBlank(contextPath)) {
			uri = StringUtils.remove(uri, contextPath);	
		}
		
		
		if(isRequireAuthPage(uri,requireAuthPages)) {
			User user = (User)session.getAttribute("user");
			if(user == null) {
				response.sendRedirect("wallet");
				return false;
			}
		}
		
		return true;
	}

	private boolean isRequireAuthPage(String uri, String[] requireAuthPages) {
		boolean isOk = false;
		
		for(String item : requireAuthPages) {
			if(item.startsWith(uri)) {
				isOk = true;
				break;
			}
		}
		
		return isOk;
	}

	
}
