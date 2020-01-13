package com.chaindemo.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.chaindemo.demo.model.User;
import com.chaindemo.demo.utils.JwtHelper;

import io.jsonwebtoken.Claims;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
        	response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
       
		HttpSession session = request.getSession();
		String contextPath = session.getServletContext().getContextPath();
		
		String[] requireAuthPages = new String[] {"/wallet"};
		
		String uri = request.getRequestURI();
		if(!StringUtils.isBlank(contextPath)) {
			uri = StringUtils.remove(uri, contextPath);	
		}
		
		if(isRequireAuthPage(uri,requireAuthPages)) {
			String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
		    if(StringUtils.isEmpty(auth)) {
		    	response.sendRedirect("login?redirect="+uri);
				return false;
		    }
		    
		    Claims obj = JwtHelper.parseJWT(auth);
	    	if(obj == null) {
	    		response.sendRedirect("login");
				return false;
	    	}
	    	else {
	    		
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
