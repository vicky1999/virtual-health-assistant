package com.vhealth.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LabSignUp")
public class LabSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LabSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String sql="INSERT INTO lab(id,name,password,email,gender,age,mobile) VALUES (?,?,?,?,?,?,?);";
		LoginDao dao = new LoginDao();
		String id="";
		try {
			id=dao.getID("lab");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String uname=req.getParameter("username");
		String pass=req.getParameter("password");
		String email=req.getParameter("mail");
		String gender=req.getParameter("gender");
		String age=req.getParameter("age");
		String mobile=req.getParameter("mobile");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(dao.url,dao.username,dao.password);
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, "LAB-"+id);
				stmt.setString(2, uname);
				stmt.setString(3,pass);
				stmt.setString(4,email);
				stmt.setString(5,gender);
				stmt.setString(6,age);
				stmt.setString(7,mobile);	
				int created=stmt.executeUpdate();
				if(created>0) 	res.sendRedirect("login.jsp");
				else res.sendRedirect("lab-signup.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
