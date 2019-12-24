package com.renting.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.renting.entity.Result;
import com.renting.entity.TRegion;
import com.renting.entity.Users;
import com.renting.service.TRegionService;
import com.renting.service.UsersService;
import com.renting.service.impl.TRegionServiceImpl;
import com.renting.service.impl.UsersServiceimpl;

public class TRegionServlet extends HttpServlet {
	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	private static final String SUPPERLIST="findBySupperId";
	private TRegionService service=new TRegionServiceImpl();
	
	
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
		}else if (path.equals(SUPPERLIST)) {
			this.findBySupperId(request, response);
		}else if (path.equals(FINDPAGE)) {
			this.findPageAll(request, response);
		}else if (path.equals(GET)) {
			this.findAll(request, response);
		}else if(path.equals(IDS)||path.equals(DEL)) {
			this.deleteUsers(request, response);
		}else if (path.equals(PUT)) {
			this.updateUsers(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
		
	}

	
	private void findBySupperId(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=utf-8");
		String areacode=request.getParameter("area");
		try{
			List<TRegion> list=service.findBySupperId(Integer.valueOf(areacode));
			System.out.println(list);
			PrintWriter out = response.getWriter();
			out.print(JSONArray.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			List<TRegion> list=service.findAll();
			//OutputStream	out=response.getOutputStream();
			System.out.println(list);
			
			PrintWriter out = response.getWriter();
			//JSON.toJSONString(list)
			out.print(JSONArray.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void updateUsers(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	public void deleteUsers(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	public void findPageAll(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	public void findById(HttpServletRequest request, HttpServletResponse response) {
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
}
