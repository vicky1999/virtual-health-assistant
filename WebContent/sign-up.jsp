<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign UP</title>
</head>
<body>
	<form action="signup" method="post">
		Username : <input type="text" name="username" required /> <br/>
		Password : <input type="password" name="password"  required /> <br/>
		E-mail : <input type="email" name="mail"  required /><br/>
		Role : 
		<input type="radio" id="patient" name="role" value="Patient">
		<label for="patient">Patient</label><br>
		<input type="radio" id="doctor" name="role" value="Doctor">
		<label for="doctor">Doctor</label><br>
		<input type="radio" id="lab" name="role" value="Lab Technician">
		<label for="lab" >Lab Technician</label> <br/>
		<input type="submit" />
	</form>
</body>
</html>