package com.renting.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import com.renting.service.UsersService;
import com.renting.service.impl.UsersServiceimpl;




@SuppressWarnings("unused")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";

	private UsersService restful=new UsersServiceimpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    
		System.out.println("已进入UsersServlet'doGet中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		if(path.equals(BYID)) {
			this.findById(request, response);
		}else if (path.equals(FINDPAGE)) {
			this.findPageAll(request, response);
		}else if(path.equals(IDS)||path.equals(DEL)) {
			this.deleteUsers(request, response);
		}else if (path.equals(PUT)) {
			this.updateUsers(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
		
	}


	
	
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer Usersid=Integer.parseInt(request.getParameter("id"));
		System.out.println("id:"+ Usersid);
		if(!(Usersid!=null&&Usersid.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("error.jsp");
		}
		Users Users = restful.findById(Usersid);
		if(Users ==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("WEB-INF/jsp/error.jsp");
		}else{
			request.setAttribute("u", Users);
			System.out.println(Users);
			request.getRequestDispatcher("jsp/file/userinfo.jsp").forward(request, response);
		}
	}
		
	
	public void findPageAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp=request.getParameter("currentPage");
		String ps=request.getParameter("pageSize");
		Integer pageSize=3;
		Integer currentPage=1;
		if(ps!=null){
			pageSize=Integer.parseInt(ps);
		}
		if(cp!=null){
			currentPage=Integer.parseInt(cp);
		}
		String column= request.getParameter("column");
		if(column!=null&&column.length()>0){
			column="name";
		}
		String keywords=request.getParameter("keywords");

		List<Users> list =Collections.EMPTY_LIST;// restful.findPageAll;
		int count=9;//restful.getCount(column,keywords);
		Map columns=new HashMap();
		columns.put("name", "用户名");
		request.setAttribute("columns", columns);
		request.setAttribute("keywords", keywords);
		
		request.setAttribute("enablePage", true);
		request.setAttribute("enableSearch", true);
		
		request.setAttribute("column", column);
		request.setAttribute("users_list", list);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("jsp/file/userslist.jsp")
					.forward(request, response);
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("已进入UsersServlet'doPost中.....");
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		String path=request.getParameter("path");
		System.out.println("POST	       : "+path);
		Users user=new Users();
		String id=request.getParameter("id");     
		String name=request.getParameter("username"); 
		user.setLoginname(name);
		
		String password=request.getParameter("password");
		user.setLoginpwd(password);
		
		String telephone=request.getParameter("telephone");
		user.setPhone(telephone);
		
		String realname=request.getParameter("realname");
		user.setRealname(realname);
		
		Result result=null;
		if(id!=null&&id.length()>0){
			user.setUserid(Integer.valueOf(id));;
			result=restful.update(user);
		}else{
			result=restful.save(user);
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("pages/wain.jsp").forward(request, response);

		
	}


	public void updateUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Integer Usersid=Integer.parseInt(request.getParameter("id"));
		if(!(Usersid!=null&&Usersid.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("error.jsp");
		}
		Users Users = restful.findById(Usersid);
		if(Users ==null){
			request.setAttribute("msg", "用户不存在!");
		}else{
			request.setAttribute("user", Users);
			request.getRequestDispatcher("pages/wain.jsp").forward(request, response);
		}
		
	}


	public void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("已进入UsersServlet'doDelete中.....");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		Result result=null;
		if (path.equals(IDS)) {
			String ids[]=request.getParameter("ids").split(",");
			result=restful.deleteIds(ids);
		}else {
			String id=request.getParameter("id");
			Users entity =new Users();
			entity.setUserid(Integer.valueOf(id));
			result=restful.delete(entity );
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
	}
}
