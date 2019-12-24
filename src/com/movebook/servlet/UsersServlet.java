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
public class UsersServlet extends HttpServlet {

	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	private static final String HMENUID="Bymenuid";
	private UsersService restful=new UsersServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入UsersServlet'doGet中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		if (path.equals(GET)) {
			this.findAll(request, response);
		}else if(path.equals(BYID)) {
			this.findById(request, response);
		}else if (path.equals(FINDPAGE)) {
			this.findPageAll(request, response);
		}else if(path.equals(IDS)||path.equals(DEL)) {
			this.deleteUsers(request, response);
		}else if (path.equals(PUT)) {
			this.updateUsers(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("admin/files/wain.jsp").forward(request, response);
		}
		
	}


	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Users> list=new ArrayList<Users>();
	    Users Users=new Users();
		list=restful.findAll();
		System.out.println(list);
		if(list.size()==0){
			request.getSession().setAttribute("msg", "查询有误!");
			response.sendRedirect("error.jsp");
		}
		request.setAttribute("Users_list", list);
		request.setAttribute("enablePage", false);
		request.setAttribute("enableSearch", false);
		request.getRequestDispatcher("admin/menu_container/Users-list.jsp").forward(request, response);
	
	}
	
	
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer Usersid=Integer.parseInt(request.getParameter("Usersid"));
		System.out.println("Usersid:"+ Usersid);
		if(!(Usersid!=null&&Usersid.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("error.jsp");
		}
		Users Users = restful.findById(Usersid);
		if(Users ==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("WEB-INF/jsp/error.jsp");
		}else{
			request.setAttribute("h", Users);
			System.out.println(Users);
			request.getRequestDispatcher("admin/files/Users/Users_info.jsp").forward(request, response);
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
		String keywords=request.getParameter("keywords");
		List<Users> list = restful.findPageAll(
				column,
				keywords,
				currentPage,
				pageSize);
		int count=restful.getCount(column,keywords);
		Map columns=new HashMap();
		columns.put("title", "名称");
		request.setAttribute("columns", columns);
		request.setAttribute("keywords", keywords);
		
		request.setAttribute("enablePage", true);
		request.setAttribute("enableSearch", true);
		
		request.setAttribute("column", column);
		request.setAttribute("Users_list", list);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("admin/menu_container/Users-list.jsp")
					.forward(request, response);
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("已进入UsersServlet'doPost中.....");
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		String path=request.getParameter("path");
		System.out.println("POST	       : "+path);
		Users Users=new Users();
		
		String Usersid=request.getParameter("Usersid");     
		String title=request.getParameter("title");      
		String picpath=request.getParameter("picpath");    
		String summary=request.getParameter("summary");    
		String contentUrl=request.getParameter("contentUrl"); 
		String menuid=request.getParameter("menuid");       
		String UsersStatus=request.getParameter("UsersStatus");
		
	
		Map map=new HashMap();
		if(Usersid!=null&&Usersid.length()>0){
			Integer id=Integer.valueOf(Usersid);
			Users.setId(id);
			map=restful.update(Users);
		}else{
			map=restful.save(Users);
		}
		
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("admin/files/wain.jsp").forward(request, response);

		
	}


	public void updateUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Users Users=null;
		String id=request.getParameter("Usersid");
		System.out.println("已进入UsersServlet'doPut中.....");
		Integer Usersid=null;
		if(id!=null){
			Usersid=Integer.parseInt(id);
		}
		
		System.out.println(Usersid);
		
		if(!(Usersid!=null&&Usersid.intValue()>0)){
			request.setAttribute("msg", "编号不能为空！");
			request.getRequestDispatcher("admin/files/wain.jsp").forward(request, response);
		}
		Users =restful.findById(Usersid);
		if(Users ==null){
			request.setAttribute("msg", "用户不存在!");
			request.getRequestDispatcher("admin/files/wain.jsp").forward(request, response);
		}else{
			request.setAttribute("h", Users );
			request.getRequestDispatcher("admin/files/Users/Users_update.jsp").forward(request, response);
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
		Map map=new HashMap();
		if (path.equals(IDS)) {
			String ids[]=request.getParameter("ids").split(",");
			map=restful.deleteIds(ids);
		}else {
			String id=request.getParameter("Usersid");
			map=restful.delete(id);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("admin/files/wain.jsp").forward(request, response);
	}
}
