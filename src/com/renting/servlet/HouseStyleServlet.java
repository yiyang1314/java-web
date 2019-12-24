package com.renting.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.renting.entity.HouseStyle;
import com.renting.entity.TRegion;
import com.renting.service.HouseStyleService;
import com.renting.service.impl.HouseStyleServiceImpl;

public class HouseStyleServlet extends HttpServlet {

	private static final String GET = "findAll";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String DEL ="delete";
	private HouseStyleService service=new HouseStyleServiceImpl();
	private static final long serialVersionUID = 1L;

	@Override
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
		}else if(path.equals(DEL)) {
			this.deleteUsers(request, response);
		}else if (path.equals(PUT)) {
			this.updateUsers(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
	}

	private void updateUsers(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void deleteUsers(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			List<HouseStyle> list=service.findAll();
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

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

}
