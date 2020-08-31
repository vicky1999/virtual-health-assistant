package com.vhealth.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.*;

public class SignUp extends HttpServlet {
	String sql="INSERT INTO users(username,password,email,mobile,role) VALUES (?,?,?,?,?)";
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		LoginDao check = new LoginDao();
		String uname=req.getParameter("username");
		String pass=req.getParameter("password");
		String email=req.getParameter("mail");
		String role=req.getParameter("role");
		String mobile=req.getParameter("mobile");
		
		try {
			if(!check.check("user",uname, pass)) {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(check.url,check.username,check.password);
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, uname);
				stmt.setString(2,pass);
				stmt.setString(3,email);
				stmt.setString(4,mobile);
				stmt.setString(5, role);
				int created=stmt.executeUpdate();
				if(created>0) 	res.sendRedirect("login.jsp");
				else res.sendRedirect("sign-up.jsp");
			}
			else {
				res.sendRedirect("sign-up.jsp");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
