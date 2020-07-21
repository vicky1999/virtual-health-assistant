package com.vhealth.auth;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.*;

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		LoginDao dao = new LoginDao();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		try {
			if(dao.check(username, password)) {
				HttpSession session=req.getSession();
				session.setAttribute("username",username);
				res.sendRedirect("index.jsp");
			}
			else {
				res.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
