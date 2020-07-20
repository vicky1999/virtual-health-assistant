package com.vhealth.auth;

import java.sql.*;

public class LoginDao {
	String sql="SELECT * FROM users WHERE username = ? and password = ?";
	String url="jdbc:mysql://localhost:3306/vhealth";
	String username="vhealth";
	String password="vhealth";
	public boolean check(String uname,String pass) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement stmt=con.prepareStatement(sql);
		stmt.setString(1, uname);
		stmt.setString(2,pass);
		ResultSet res=stmt.executeQuery();
		if(res.next()) return true;		
		return false;
	}
}
