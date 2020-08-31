package com.vhealth.people.lab;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/lab-technicians")
public class LabTechnicians extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LabTechnicians() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			com.vhealth.people.doctor.DoctorHandler handler = new com.vhealth.people.doctor.DoctorHandler(); 
			java.util.List<String[]> data=handler.getPeople("lab"); 
			
			try{
		    	String html="<!DOCTYPE html>\r\n" + 
		    			"	<html>\r\n" + 
		    			"	<head>\r\n" + 
		    			"	<meta charset=\"ISO-8859-1\">\r\n" + 
		    			"	<title>Insert title here</title>\r\n"
		    			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
		    			"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\">\r\n" + 
		    			"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
		    			"	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
		    			"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\"></script>\r\n" + 
		    			"	</head>\r\n" + 
		    			"<style>table,thead,th,td,tr {border: 1px solid black; border-collapse:  collapse;}"
		    			+ "table { text-align: center;margin-top: 50px;}"
		    			+ "thead{background-color: gray;color: white;}"
		    			+ "</style> \r\n" + 
		    			"	<body>\r\n" + 
		    			"		<script> function setcookie(data) { document.cookie =\" to=\"+data; return true; }</script> \r\n"+
		    			"<div class=\"bs-example container\" data-example-id=\"striped-table\">\r\n" + 
		    			"  <center><table class=\"table table-striped table-bordered table-hover\">\r\n" + 
		    			"    <thead>\r\n" + 
		    			"		  	<tr>\r\n" + 
		    			"		  		<th>ID</th>\r\n" + 
		    			"			  	<th>Technician name</th>\r\n" + 
		    			"			  	<th>E-mail</th>\r\n" + 
		    			"			  	<th>Gender</th>\r\n" + 
		    			"			  	<th>Age</th>\r\n" + 
		    			"			  	<th>Mobile</th>\r\n" + 
		    			"			  	<th>Booking</th>\r\n" + 
		    			"			</tr>\r\n" + 
		    			"	</thead>\r\n" +
		    			"<tbody> \r\n" +
		    			"			<tr>\r\n";
		    					      for(int i=0;i<data.size();i++) {  
		    					    	  html+=" <tr>\r\n"; 
		    								   String[] val=data.get(i); 
		    									for(int j=0;j<6;j++) {  
		    										html+="<td>"+val[j]+"</td>\r\n"; 
		    									} 
		    			html+="<td><a href=\"/Virtual_Health_Assistant/book-appointment.jsp\"  onclick=\'return setcookie(\""+val[2]+"\"); \'>Book Appointment</a></td>\r\n" + 
		    			"				   </tr>\r\n";
		    								}
		    			html+="			</tr>\r\n" + 
		    			"  		</tbody> \r\n" +	
		    			"		  </table></center>\r\n" + 
		    			"	</body>\r\n" + 
		    			"	</html>";
		       response.setContentType("text/html");
		       PrintWriter writer = response.getWriter();
		       writer.print(html);
		       writer.close();
		    }
		    catch(Exception e) {}
			
	}
}
