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

import com.renting.entity.House;
import com.renting.entity.HouseStyle;
import com.renting.entity.Result;
import com.renting.entity.TRegion;
import com.renting.entity.Users;
import com.renting.service.HouseService;
import com.renting.service.HouseStyleService;
import com.renting.service.UsersService;
import com.renting.service.impl.HouseServiceImpl;
import com.renting.service.impl.HouseStyleServiceImpl;
import com.renting.service.impl.UsersServiceimpl;

@SuppressWarnings("unused")
public class HoseServlet extends HttpServlet {

	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	private static final String ADMIN ="admin";
	
	private HouseService service=new HouseServiceImpl();
	private HouseStyleService style=new HouseStyleServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入DeptServlet'doGet中.....");
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
			this.deleteHouse(request, response);
		}else if (path.equals(PUT)) {
			this.updateHouse(request, response);
		}else if (path.equals(ADMIN)) {
			this.findPageAdmin(request, response);
		}else{
			Result result=new Result();
			result.setMsg("请求地址不存在！");
			result.setStatus(302);
			request.setAttribute("result", result);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		}
	}


	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
		List<House> list = service.findAll();
		Result result=new Result();
		if (list.size()==0) {
			result.setMsg("房源已售空！");
			result.setStatus(303);
			request.setAttribute("result", result);
			request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
		} else{
			
		
			request.setAttribute("houselist", list);
			request.getRequestDispatcher("pages/house/houselist.jsp").forward(request, response);
		}
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}


	private void deleteHouse(HttpServletRequest request, HttpServletResponse response) {
		try {
			String houseid=request.getParameter("houseid");
			Integer id=-1;
			if(houseid!=null&&houseid.length()>0){
				id=Integer.valueOf(houseid);
			}
			House entity=new House();
			entity.setHouseid(id);
			 Result result = service.delete(entity);
			if(result.getStatus()==200){
				request.setAttribute("result", result);
				Map<String, Object> map=new HashMap();
/*				map.put("title", "公");
				map.put("styleid", 2);
				
				map.put("minPrice", 35.5);
				map.put("maxPrice", 535.5);
				
				map.put("minArea", 35);
				map.put("maxArea", 535);
				 rrrrr;
				map.put("upperRegion", "110101001");*/
				map.put("start",(1-1)*5);
				map.put("pageSize",5);
				request.setAttribute("houselist",service.findPageAll(map));
				request.getRequestDispatcher("pages/user/admin.jsp").forward(request, response);
			}else{
				result.setMsg("房源信息不存在");
				result.setStatus(302);
				request.setAttribute("result", result);
				request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
			}
		}catch(Exception e){
			System.out.println("msg"+e.getMessage());
		}
		
	}


	private void updateHouse(HttpServletRequest request, HttpServletResponse response) {
		try {
			String houseid=request.getParameter("houseid");
			Integer id=-1;
			if(houseid!=null&&houseid.length()>0){
				id=Integer.valueOf(houseid);
			}
			House house = service.findById(id);
			if(house!=null){
				request.setAttribute("house", house);
				request.getRequestDispatcher("pages/house/fabu.jsp").forward(request, response);
			}else{
				Result result=new Result();
				result.setMsg("房源信息不存在");
				result.setStatus(302);
				request.setAttribute("result", result);
				request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
			}
		}catch(Exception e){
			System.out.println("msg"+e.getMessage());
		}
		
		
	}


	private void findPageAll(HttpServletRequest request, HttpServletResponse response) {
		String cp=request.getParameter("currentPage");
		String ps=request.getParameter("pageSize");
		Integer pageSize=3;
		Integer currentPage=1;
		try {
		if(ps!=null){
			pageSize=Integer.parseInt(ps);
		}
		if(cp!=null){
			currentPage=Integer.parseInt(cp);
		}
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("title", request.getParameter("title"));
		String styleid=request.getParameter("type_id");
		map.put("styleid",((styleid!=null)&&styleid.length()>0)?Integer.valueOf(styleid):null );
		String pp=request.getParameter("price");
		if(pp!=null&&pp.length()>0){
			String []p=pp.split("-");
			map.put("price", pp);
			if(p[0].contains("*")){
				map.put("minPrice",null);
				map.put("maxPrice", Integer.valueOf(p[1]));
			}else if(p[1].contains("*")){
				map.put("minPrice",Integer.valueOf(p[0]));
				map.put("maxPrice",null);
			}else{
				map.put("minPrice", Integer.valueOf(p[0]));
				map.put("maxPrice", Integer.valueOf(p[1]));
			}
		}else{
			map.put("minPrice",null);
			map.put("maxPrice",null);
		}
		String upperRegion=request.getParameter("address");
		System.out.println(upperRegion);
		map.put("upperRegion",upperRegion==null?"110101":upperRegion);
		map.put("street_id",request.getParameter("street_id")+"-"+request.getParameter("address"));
		String areas=request.getParameter("floorage");
		System.out.println(areas);
		if(areas!=null&&areas.length()>0){
			String []area=areas.split("-");
			map.put("areas", areas);
			if(area[0].contains("*")){
				map.put("minArea",null);
				map.put("maxArea", Integer.valueOf(area[1]));
			}else if(area[1].contains("*")){
				map.put("minArea",Integer.valueOf(area[0]));
				map.put("maxArea",null);
			}else{
				map.put("minArea", Integer.valueOf(area[0]));
				map.put("maxArea", Integer.valueOf(area[1]));
			}
		}else{
			map.put("minArea",null);
			map.put("maxArea",null);
		}
		List<HouseStyle> stylelist = style.findAll();
		int count=service.getCount(map);
		System.out.println(count);
		map.put("pageSize", pageSize);
		map.put("start", (currentPage-1)*pageSize);
		List<House> list=service.findPageAll(map);
		request.setAttribute("enablePage", true);
		request.setAttribute("stylelist", stylelist);
		request.setAttribute("houselist", list);
		request.setAttribute("hosemap", map);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		
			request.getRequestDispatcher("pages/house/houselist.jsp")
						.forward(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}


	private void findById(HttpServletRequest request, HttpServletResponse response) {
		try {
			String houseid=request.getParameter("houseid");
			Integer id=-1;
			if(houseid!=null&&houseid.length()>0){
				id=Integer.valueOf(houseid);
			}
			House house = service.findById(id);
			if(house!=null){
				request.setAttribute("house", house);
				request.getRequestDispatcher("pages/house/details.jsp").forward(request, response);
				
			}else{
				Result result=new Result();
				result.setMsg("房源信息不存在");
				result.setStatus(302);
				request.setAttribute("result", result);
				request.getRequestDispatcher("jsp/file/wain.jsp").forward(request, response);
			}
		
			
		} catch (Exception e) {
			
		}
		
	}
	private void saveHouse(HttpServletRequest request, HttpServletResponse response) {
		String   houseid	=request.getParameter("houseid");
		String   picPath	=request.getParameter("picPath");
		String   title		=request.getParameter("title");
		String   houseArea	=request.getParameter("houseArea");
		String   housePrice	=request.getParameter("price");
		String   houseDate	=request.getParameter("houseDate");
		System.out.println(houseDate);
		String   phone		=request.getParameter("contact");
		String   summary	=request.getParameter("description");
		String   styleid	=request.getParameter("type_id");
		String   house_address		=request.getParameter("house_address");
		Result result=null;
		House h=new House();
		h.setStreet(house_address);
		h.setSummary(summary);
		h.setPicPath(picPath);
		h.setPhone(phone);
		h.setTitle(title);
		TRegion reg=new TRegion();
		Users user=(Users) request.getSession().getAttribute("u");
		h.setUser(user);
		reg.setUpperRegion(request.getParameter("district_id"));
		reg.setCode(request.getParameter("street_id"));
		
		h.setRegion(reg);
		if(houseArea!=null&&houseArea.length()>0){
			h.setHouseArea(Integer.valueOf(houseArea));
		}
		if(styleid!=null&&styleid.length()>0){
			HouseStyle hhh=new HouseStyle();
			hhh.setStyleid(Integer.valueOf(styleid));
			h.setHouseStyle(hhh);
		}
		if(housePrice!=null&&housePrice.length()>0){
			h.setHousePrice(Double.valueOf(housePrice));
		}
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			h.setHouseDate(sdf.parse(houseDate));
			if(houseid!=null&&houseid.length()>0){
				h.setHouseid(Integer.valueOf(houseid));
				result=service.update(h);
			}else{
				h.setPublicDate(new Date());
				result=service.save(h);
			}
			if(result.getStatus()==200){
					response.sendRedirect("house_admin");
			}else{
				request.setAttribute("result", result);
				request.getRequestDispatcher("pages/wain.jsp").forward(request, response);
			}
		}catch (ServletException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入DeptServlet'doPost中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		if (path.equals(FINDPAGE)) {
			this.findPageAll(request, response);
		}else{
			this.saveHouse(request, response);
		}
			
			
	}
	
	
	private void findPageAdmin(HttpServletRequest request, HttpServletResponse response) {
		String cp=request.getParameter("currentPage");
		String ps=request.getParameter("pageSize");
		Integer pageSize=3;
		Integer currentPage=1;
		try {
		if(ps!=null){
			pageSize=Integer.parseInt(ps);
		}
		if(cp!=null){
			currentPage=Integer.parseInt(cp);
		}
		Map<String ,Object> map=new HashMap<String ,Object>();
		Users u=(Users) request.getSession().getAttribute("u");
		map.put("userid", u.getUserid());
		int count=service.getCount(map);
		map.put("pageSize", pageSize);
		map.put("start", (currentPage-1)*pageSize);
		List<House> list=service.findPageAll(map);
		request.setAttribute("enablePage", true);
		request.setAttribute("houselist", list);
		request.setAttribute("count", count);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("pages/user/admin.jsp")
						.forward(request, response);
			
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}


}
