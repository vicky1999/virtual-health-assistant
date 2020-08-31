package com.vhealth.people.doctor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import javax.servlet.http.*;


@WebServlet("/DoctorHandler")
public class DoctorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String url="jdbc:mysql://localhost:3306/vhealth";
	String username="vhealth";
	String password="vhealth";
	
	public List<String[]> getPeople(String table) {
		List<String[]> res=new ArrayList<String[]>();
		try {
			String sql="SELECT * FROM "+table+";";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement stmt=con.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			 while(rs.next()){
		         //Retrieve by column name
				 String[] temp=new String[9];
		         temp[0] = rs.getString("id");
		         temp[1] = rs.getString("name");
		         temp[2] = rs.getString("email");
		         temp[3] = rs.getString("gender");
		         temp[4] = rs.getString("age");
		         if(table=="doctor") {
			         temp[5] = rs.getString("fees");
			         temp[6] = rs.getString("department");
			         temp[7] = rs.getString("degree");
			         temp[8] = rs.getString("mobile");
		         }
		         else temp[5]=rs.getString("mobile");
		         
		         res.add(temp);
			 }
		}
		catch(Exception e) {}
		return res;
	}
}
