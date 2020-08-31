	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<style>table,thead,th,td,tr {border: 1px solid black; border-collapse:  collapse;}"
		table { text-align: center;margin-top: 50px;}
		thead{background-color: gray;color: white;}
	</style>
	</head>
	<body>
		<%
			com.vhealth.people.doctor.DoctorHandler handler = new com.vhealth.people.doctor.DoctorHandler();
				   java.util.List<String[]> data=handler.getPeople("doctor");
		%>
		  <table>
		  	<tr>
		  		<th>ID</th>
			  	<th>Doctor name</th>
			  	<th>E-mail</th>
			  	<th>Gender</th>
			  	<th>Age</th>
			  	<th>Fees</th>
			  	<th>Department</th>
			  	<th>Degree</th>
			  	<th>Mobile</th>
			  	<th>Booking</th>
			</tr>
			<tr>
				 <%
				   for(int i=0;i<data.size();i++) { %>
				   <tr>
				   <%
					   String[] val=data.get(i);
						for(int j=0;j<9;j++) { 
						%>   
							<td><%=val[j] %></td>
				   <%} %>
					   <td><a href="/Virtual_Health_Assistant/book-appointment.jsp"  onclick= <%
							   Cookie c = new Cookie("to",val[2]);
					   %>>Book Appointment</a></td>
				   </tr>
				   <% 
				   } %>
			</tr>
		  </table>
	</body>
	</html>