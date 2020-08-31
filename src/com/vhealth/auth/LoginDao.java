package com.vhealth.auth;

import java.sql.*;

public class LoginDao {
	String url="jdbc:mysql://localhost:3306/vhealth";
	String username="vhealth";
	String password="vhealth";
	public boolean check(String table,String uname,String pass) throws SQLException, ClassNotFoundException {
		String sql="SELECT * FROM "+table+" WHERE name = ? and password = ?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement stmt=con.prepareStatement(sql);
		stmt.setString(1, uname);
		stmt.setString(2,pass);
		ResultSet res=stmt.executeQuery();
		if(res.next()) return true;		
		return false;
	}
	
	public String[] getUserDetails(String table,String uname,String pass) throws SQLException, ClassNotFoundException {
		String sql="SELECT * FROM "+table+" where name=? and password=?";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement stmt=con.prepareStatement(sql);
		stmt.setString(1, uname);
		stmt.setString(2,pass);
		ResultSet res=stmt.executeQuery();
		if(res.next()) {
			String[] user=new String[3];
			user[0]=res.getString("name");
			user[1]=res.getString("email");
			user[2]=res.getString("mobile");
			return user;
		}
		return null;
	}
	public String getID(String role) throws ClassNotFoundException, SQLException {
		String sql="SELECT COUNT(*) as len FROM "+role+";";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement stmt=con.prepareStatement(sql);
		ResultSet res=stmt.executeQuery();
		if(res.next()) {
			return res.getString("len");
		}
		return null;
	}
}
