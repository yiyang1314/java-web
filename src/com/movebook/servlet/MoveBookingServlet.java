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

import com.movebook.entity.MoveBooking;
import com.movebook.entity.Users;
import com.movebook.service.MoveBookingService;
import com.movebook.service.impl.MoveBookingServiceImpl;


@SuppressWarnings("unused")
public class MoveBookingServlet extends HttpServlet {

	private static final String GET = "findAll";
	private static final String BYID = "findById";
	private static final String POST = "save";
	private static final String PUT = "updateById";
	private static final String IDS ="ids";
	private static final String DEL ="delete";
	private static final String FINDPAGE ="page";
	private MoveBookingService restful=new MoveBookingServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		System.out.println("已进入MoveBookingServlet'doGet中.....");
		String serverpath=request.getServletPath();
		System.out.println("serverpath: "+serverpath);
		String path=serverpath.substring(serverpath.indexOf("_")+1);
		System.out.println("path: "+path);
		if (path.equals(GET)) {
			this.findAll(request, response);
		}else if(path.equals(BYID)) {
			this.findById(request, response);
		}else if(path.equals(FINDPAGE)) {
			this.findPageAll(request, response);;
		}else if(path.equals(IDS)||path.equals(DEL)) {
			this.deleteMove(request, response);
		}else if (path.equals(PUT)) {
			this.updateMove(request, response);
		}else{
			
			//request.getSession().setAttribute(arg0, arg1);
			request.getRequestDispatcher("jsp/file/movebookinginfo.jsp").forward(request, response);
		}
		
	}



	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MoveBooking> list=new ArrayList<MoveBooking>();
	    MoveBooking MoveBooking=new MoveBooking();
		list=restful.findAll();
		System.out.println(list);
		if(list.size()==0){
			request.getSession().setAttribute("msg", "查询有误!");
			response.sendRedirect("error.jsp");
		}else{
			String []statusarr={"未处理","已派车","已结束"};
			request.setAttribute("statusarr", statusarr);
			request.setAttribute("move_list", list);
			request.setAttribute("enablePage", false);
			request.setAttribute("enableSearch", false);
			request.getRequestDispatcher("jsp/file/movebookinglist.jsp").forward(request, response);
		}
		
	}
	public void findPageAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp=request.getParameter("currentPage");
		String ps=request.getParameter("pageSize");
		Integer pageSize=2;
		Integer currentPage=1;
		if(ps!=null){
			pageSize=Integer.parseInt(ps);
		}
		if(cp!=null){
			currentPage=Integer.parseInt(cp);
		}
		System.out.println(pageSize);
		String column= request.getParameter("column");
		String keywords=request.getParameter("keywords");
		List<MoveBooking> list = restful.findPageAll(
				column,
				keywords,
				currentPage,
				pageSize);
		int count=restful.getCount(column,keywords);
		Map columns=new HashMap();
		columns.put("area", "地区");
		request.setAttribute("columns", columns);
		request.setAttribute("keywords", keywords);
		
		request.setAttribute("enablePage", true);
		request.setAttribute("enableSearch", true);
		System.out.println(list);
		request.setAttribute("column", column);
		request.setAttribute("move_list", list);
		request.setAttribute("count", count);
		String []statusarr={"未处理","已派车","已结束"};
		request.setAttribute("statusarr", statusarr);
		request.setAttribute("pageCount", (count-1)/pageSize+1);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("jsp/file/movebookinglist.jsp")
					.forward(request, response);
	}
	
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		System.out.println("id:"+ id);
		if(!(id!=null&&id.intValue()>0)){
			request.getSession().setAttribute("msg", "请求失败，请稍后重试!");
			response.sendRedirect("error.jsp");
		}
		MoveBooking move = restful.findById(id);
		if(move ==null){
			request.getSession().setAttribute("msg", "用户不存在!");
			response.sendRedirect("WEB-INF/jsp/error.jsp");
		}else{
			String []statusarr={"未处理","已派车","已结束"};
			request.setAttribute("statusarr", statusarr);
			request.setAttribute("move", move);
			request.getRequestDispatcher("jsp/file/movebookinginfo.jsp").forward(request, response);
		}
	}
		
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("已进入MoveBookingServlet'doPost中.....");
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		String path=request.getParameter("path");
		System.out.println("POST	       : "+path);

		
		String id=request.getParameter("id");     
		String cartype=request.getParameter("cartype");      
		String movedate=request.getParameter("movedate");
		Date d=null;
		if(movedate!=null&&movedate.length()>0){
			SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
			try {
				d = sft.parse(movedate);
				
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}

		String phone=request.getParameter("phone");    
		String contact=request.getParameter("contact"); 
		String status=request.getParameter("status");       
		String area=request.getParameter("area"); 
		
		MoveBooking move=new MoveBooking();
		move.setArea(area);
		move.setCartype(cartype);
		move.setContact(contact);
		move.setMovedate(d);
		move.setStatus(status);
		move.setPhone(phone);
		Map map=new HashMap();
		if(id!=null&&id.length()>0){
			Integer moveid=Integer.valueOf(id);
			move.setId(moveid);
			map=restful.update(move);
		}else{
			map=restful.save(move);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("jsp/file/movebookingtip.jsp").forward(request, response);
	}


	public void updateMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MoveBooking MoveBooking=null;
		String id=request.getParameter("id");
		System.out.println("已进入MoveBookingServlet'doPut中.....");
		Integer idd=null;
		if(id!=null){
			idd=Integer.parseInt(id);
		}
		
		System.out.println(id);
		
		if(!(idd!=null&&idd.intValue()>0)){
			request.setAttribute("msg", "编号不能为空！");
			request.getRequestDispatcher("jsp/file/movebookingtip.jsp").forward(request, response);
		}
		MoveBooking =restful.findById(idd);
		if(MoveBooking ==null){
			request.setAttribute("msg", "用户不存在!");
			request.getRequestDispatcher("jsp/file/movebookingtip.jsp").forward(request, response);
		}else{
			request.setAttribute("move", MoveBooking );
			request.getRequestDispatcher("jsp/file/movebookingupdate.jsp").forward(request, response);
		}
		
	}


	public void deleteMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("已进入MoveBookingServlet'doDelete中.....");
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
			String id=request.getParameter("id");
			map=restful.delete(id);
		}
		request.setAttribute("resultmap", map);
		request.getRequestDispatcher("move_findAll").forward(request, response);
	}
}
