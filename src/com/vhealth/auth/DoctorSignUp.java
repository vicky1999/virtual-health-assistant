	package com.vhealth.auth;
	
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	
	@WebServlet("/DoctorSignUp")
	public class DoctorSignUp extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public DoctorSignUp() {
	        super();
	    }
	
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String sql="INSERT INTO doctor(id,name,password,email,gender,age,fees,department,degree,mobile) VALUES (?,?,?,?,?,?,?,?,?,?);";
			LoginDao dao = new LoginDao();
			String id="";
			try {
				id=dao.getID("doctor");
			} catch (Exception e) {
				e.printStackTrace();
			} 
			String uname=req.getParameter("username");
			String pass=req.getParameter("password");
			String email=req.getParameter("mail");
			String gender=req.getParameter("gender");
			String age=req.getParameter("age");
			String fees=req.getParameter("fees");
			String department=req.getParameter("department");
			String degree=req.getParameter("degree");
			String mobile=req.getParameter("mobile");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection(dao.url,dao.username,dao.password);
					PreparedStatement stmt=con.prepareStatement(sql);
					stmt.setString(1,"DOC-"+id);
					stmt.setString(2, uname);
					stmt.setString(3,pass);
					stmt.setString(4,email);
					stmt.setString(5,gender);
					stmt.setString(6,age);
					stmt.setString(7,fees);
					stmt.setString(8,department);
					stmt.setString(9,degree);
					stmt.setString(10,mobile);	
					int created=stmt.executeUpdate();
					if(created>0) 	res.sendRedirect("login.jsp");
					else res.sendRedirect("doctor-signup.jsp");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
