package com.movebook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.movebook.dao.UsersDao;
import com.movebook.db.DataBaseConnection;
import com.movebook.entity.Users;


public  class UsersDaoImpl implements UsersDao {

	private DataBaseConnection  dbc = new DataBaseConnection();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@SuppressWarnings("unchecked")
	public List<Users> findAll() {
		conn = dbc.getConnection();
		List<Users> all = new ArrayList<Users>();
		Users user = null;
		try {
			String sql = "select id,name,password from Users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user=new Users();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				all.add(user);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return Collections.EMPTY_LIST;
	}


	public Users findById(Integer id) {
		conn = dbc.getConnection();
		Users user = null;
		String sql =  "select id,name,password from Users where id=?";
				
		System.out.println("sql:"+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			System.out.println("findById(Integer id):"+rs);
			if(rs.next()) {
				user=new Users();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			return user;
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return user;
	}

	

	public List<Users> findPageAll(String column, String keywords, int currentPage, int pageSize) {
		List<Users> all = new ArrayList<Users>();
		Users user = null;
		int count=0;
		try {
			
			String col="name";
			String key=""; 
			if(!(column==null||column.length()==0)){
				col=column;
			}
			if(!(keywords==null||keywords.length()==0)){
				key=keywords;
			}
			count=getCount(col,key);
			System.out.println(count);
			if(count<1){
				return Collections.EMPTY_LIST;
			}
			conn = dbc.getConnection();
			//
			String sql ="select id,name,password "
						+" from(select rownum n, id,name,password"
						+ " from Users where "+col
						+" like ?) where n >? and rownum<=?";
					
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,"%"+key+"%");
			pstmt.setInt(2,(currentPage-1)*pageSize);
			pstmt.setInt(3,currentPage*pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user=new Users();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				all.add(user);
			}
			System.out.println(all);
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return Collections.EMPTY_LIST;
	}

	
	public int getCount(String col, String keywords) {
		int count=0;
		conn = dbc.getConnection();
		
		String column="name";
		String kw="";
		if(col!=null){
			column=col;
		}
		if(keywords!=null){
			kw=keywords;
		}
		String sql = "select count(1) from Users where "+column+" like ?";
		System.out.println("sql:\n"+sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kw+"%");
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return count;
	}

	public boolean update(Users user) {
		conn = dbc.getConnection();
		String sql = "update users set name = ?,password = ? where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getId());
			System.out.println(sql);
			if(pstmt.executeUpdate()>0) {
				conn.setAutoCommit(true);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}

	@Override
	public boolean insert(Users user) {
		conn = dbc.getConnection();
		String sql = "insert into users (id, name, password)values(Users_seq.nextval, ?, ?)";
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getPassword());
				System.out.println(sql);
			if(pstmt.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}


	@Override
	public boolean del(Integer id) {
		conn = dbc.getConnection();
		String sql = "delete Users where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			if(pstmt.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return false;
	}

	public Users login(String username, String password) {
		conn = dbc.getConnection();
		Users user = null;
		String sql = "select id, name, password from users where name=? and password=?";	
		System.out.println("sql:"+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user=new Users();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			return user;
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return null;
	}


}
