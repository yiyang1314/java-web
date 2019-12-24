package com.movebook.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.movebook.entity.Users;
import com.movebook.service.UsersService;
import com.movebook.service.impl.UsersServiceImpl;


@SuppressWarnings("unused")
public class LoginServlet extends HttpServlet {

	private static final String LOGIN = "login";
	private static final String REG = "reg";
	private static final String LOGOUT = "logout";
	private UsersService restful=new UsersServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入LoginServlet'doGet中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);	
		if(path.equals(LOGIN)){
			String code1=(String) request.getSession().getAttribute("checkcode");
			request.getSession().setAttribute("checkcode","");
			String code2=request.getParameter("code");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			if(password==null||username==null||password.equals("")||username.equals("")){
				request.setAttribute("msg","验证码错误");
				request.getRequestDispatcher("jsp/file/movebookingtip.jsp").forward(request, response);
			}
			UsersService services=new UsersServiceImpl();
			Map map=services.login(username, password);
			
			Integer status=(Integer) map.get("status");
			if(status==200){
				request.setAttribute("msg","登录成功！");
				request.getSession().setAttribute("u",(Users)map.get("user"));
				request.getRequestDispatcher("index").forward(request, response);
			}else{
				request.setAttribute("msg","登录失败！");
				request.getRequestDispatcher("jsp/file/movebookingtip.jsp").forward(request, response);
			}
			
		}else if(path.equals(LOGOUT)){
			request.getSession().removeAttribute("u");
			request.setAttribute("msg","退出成功!");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		}
	}

}
