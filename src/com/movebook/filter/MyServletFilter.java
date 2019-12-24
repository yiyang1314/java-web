package com.movebook.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServletFilter implements Filter {


	public void destroy() {
		System.out.println("服务器已断开连接.....");
	}

	//进入主页
	private String []mappings={
			"move_","users_login","users_reg"
			};

	public boolean checkContains(String mapping){
		if(mapping==null||mapping.equals("")){
			return false;
		}
		for(String i:mappings){
			if(mapping.contains(i)){
				return true;
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rsp=(HttpServletResponse) response;
		String serverpath=req.getServletPath();
		System.out.println("serverpath: "+ serverpath);
		if(checkContains(serverpath)){
			chain.doFilter(request, response);
		}
		Object user=req.getSession().getAttribute("u");
		if(user==null){
			req.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("服务器已连接,正在初始化配置....");
	}

}
