package com.movebook.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.movebook.dao.MoveBookingDao;
import com.movebook.db.DataBaseConnection;
import com.movebook.entity.MoveBooking;


public  class MoveBookingDaoImpl implements MoveBookingDao {

	private DataBaseConnection  dbc = new DataBaseConnection();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@SuppressWarnings("unchecked")
	public List<MoveBooking> findAll() {
		conn = dbc.getConnection();
		List<MoveBooking> all = new ArrayList<MoveBooking>();
		MoveBooking move = null;
		try {
			String sql = "select id, area, cartype, movedate, contact, phone, "
					+ "status from move_booking";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				move=new MoveBooking();
				move.setId(rs.getInt(1));
				move.setArea(rs.getString(2));
				move.setCartype(rs.getString(3));
				move.setMovedate(rs.getDate(4));
				move.setContact(rs.getString(5));
				move.setPhone(rs.getString(6));
				move.setStatus(rs.getString(7));
				all.add(move);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return Collections.EMPTY_LIST;
	}


	public MoveBooking findById(Integer id) {
		conn = dbc.getConnection();
		MoveBooking move = null;
		String sql =  "select id, area, cartype, movedate,"
				+ " contact, phone, status from move_booking "
				+ "where id=?";
				
		System.out.println("sql:"+sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			System.out.println("findById(Integer id):"+rs);
			if(rs.next()) {
				move=new MoveBooking();
				move=new MoveBooking();
				move.setId(rs.getInt(1));
				move.setArea(rs.getString(2));
				move.setCartype(rs.getString(3));
				move.setMovedate(rs.getDate(4));
				move.setContact(rs.getString(5));
				move.setPhone(rs.getString(6));
				move.setStatus(rs.getString(7));
			}
			return move;
		} catch (SQLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}finally {
			dbc.close(conn, pstmt, rs);
		}
		return move;
	}

	

	public List<MoveBooking> findPageAll(String column, String keywords, int currentPage, int pageSize) {
		List<MoveBooking> all = new ArrayList<MoveBooking>();
		MoveBooking move = null;
		int count=0;
		try {
			
			String col="area";
			String key="区"; 
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
			String sql ="select id, area, cartype, movedate, contact, phone, status "
					+ "from (select rownum rn,id, area, cartype, movedate, contact,"
					+ " phone, status  from "
					+ "(select id, area, cartype, movedate, contact, phone, status  "
					+ "from move_booking where "+col
					+ " like ? order by id)t ) where rn>? and rownum<=?";
					
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+key+"%");
			pstmt.setInt(2,currentPage*pageSize-1);
			pstmt.setInt(3,pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				move=new MoveBooking();
				move.setId(rs.getInt(1));
				move.setArea(rs.getString(2));
				move.setCartype(rs.getString(3));
				move.setMovedate(rs.getDate(4));
				move.setContact(rs.getString(5));
				move.setPhone(rs.getString(6));
				move.setStatus(rs.getString(7));
				all.add(move);
			}
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
		
		String column="area";
		String kw="";
		if(col!=null){
			column=col;
		}
		if(keywords!=null){
			kw=keywords;
		}
		String sql = "select count(1) from move_booking where "+column+" like ?";
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

	public boolean update(MoveBooking move) {
		conn = dbc.getConnection();
		String sql = "update move_booking set area =?,cartype =?,movedate =?,contact = ?,phone =?,status =? where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, move.getArea());
			pstmt.setString(2, move.getCartype());
			pstmt.setDate(3, new Date(move.getMovedate().getTime()));
			pstmt.setString(4, move.getContact());
			pstmt.setString(5, move.getPhone());
			pstmt.setString(6, move.getStatus());
			pstmt.setInt(7, move.getId());
			
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
	public boolean insert(MoveBooking move) {
		conn = dbc.getConnection();
		String sql = "insert into move_booking(id, area, cartype, movedate, contact, phone, status)"
				+ "values(move_seq.nextval,?,?,?,?,?,?)";

		System.out.println(move);
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, move.getArea());
				pstmt.setString(2, move.getCartype());
				pstmt.setDate(3, new Date(move.getMovedate().getTime()));
				pstmt.setString(4, move.getContact());
				pstmt.setString(5, move.getPhone());
				pstmt.setString(6, move.getStatus());
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
		String sql = "delete move_booking where id =?";
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




}
