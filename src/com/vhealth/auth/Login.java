package com.vhealth.auth;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		LoginDao dao = new LoginDao();
		String username=req.getParameter("username");
		String password=req.getParameter("password"); 
		try {
			String table=null;
			if(dao.check("doctor",username, password)) table="doctor";
			else if(dao.check("lab",username, password)) table="lab";
			else if(dao.check("user",username, password)) table="user";
			if(table!=null) {
				String details[]=dao.getUserDetails(table,username,password);
				HttpSession session=req.getSession();
				session.setAttribute("username",username);
				session.setAttribute("mail",details[1]);
				session.setAttribute("mobile",details[2]);
				session.setAttribute("role", table);
				if(table.equalsIgnoreCase("Doctor")) res.sendRedirect("Doctor/index.jsp");
				else if(table.equalsIgnoreCase("User")) res.sendRedirect("Patient/index.jsp");
				else if(table.equalsIgnoreCase("Lab")) res.sendRedirect("Lab/index.jsp");
			}
			else {
				req.setAttribute("message", "Invalid Username or Password");
		        req.getRequestDispatcher("login.jsp").forward(req, res);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
