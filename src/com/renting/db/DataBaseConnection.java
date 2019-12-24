package com.renting.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**DataBaseConnection
 * 用于连接数据库
 * @author Administrator
 * @since 2019-11-29 17:59:55
 * @category 获取数据库连接对象
 */
public class DataBaseConnection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PWD = "root";
	
	private Connection conn = null;
	
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动加载失败");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
		}
		return conn;
	}
	
	public Connection getConnectionpp() {
		try {

			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PWD);
			   System.out.println("数据库连接成功!");
		} catch (Exception e) {
			System.out.println("数据库连接失败");
		}
		return conn;
	}
	
	
	public void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		//关闭资源
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		DataBaseConnection db=new DataBaseConnection();
		System.out.println(db.getConnection());
	}
	
}
