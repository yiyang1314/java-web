package com.renting.servlet;

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

import com.renting.entity.Result;
import com.renting.entity.Users;
import com.renting.service.HouseService;
import com.renting.service.UsersService;
import com.renting.service.impl.HouseServiceImpl;
import com.renting.service.impl.UsersServiceimpl;


@SuppressWarnings("unused")
public class LoginServlet extends HttpServlet {

	private static final String LOGIN = "login";
	private static final String REG = "reg";
	private static final String LOGOUT = "logout";
	private UsersService restful=new UsersServiceimpl();
	
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

			String username=request.getParameter("username");
			String password=request.getParameter("password");
			if(password==null||username==null||password.equals("")||username.equals("")){
				request.setAttribute("msg","验证码错误");
				request.getRequestDispatcher("pages/wain.jsp").forward(request, response);
			}
			UsersService services=new UsersServiceimpl();
			Result result = services.login(username, password);
			if(result.getStatus().intValue()==200){
				request.getSession().setAttribute("u",result.getData());
				HouseService service=new HouseServiceImpl();
				request.setAttribute("result",result);
				response.sendRedirect("house_admin");
			}else if(result.getStatus().intValue()==405){
				request.getSession().setAttribute("u",result.getData());
				request.setAttribute("result",result);
				request.getRequestDispatcher("pages/wain.jsp").forward(request, response);
			}else{
				request.setAttribute("result",result);
				request.getRequestDispatcher("pages/wain.jsp").forward(request, response);
			}
			
		}else if(path.equals(LOGOUT)){
			request.getSession().removeAttribute("u");
			
			Result result = new Result();
			result.setMsg("退出成功!");
			result.setStatus(303);
			request.setAttribute("result",result);
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
	}

}
