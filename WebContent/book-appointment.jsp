<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta charset="ISO-8859-1">
	<title>Book Appointment</title>
	<link rel="stylesheet" href="styles/login.css" />

</head>
<body>

    	<div class="container">
    	<div class="row">
          <div class="col-lg-12 login-form">
             <div class="col-lg-12 login-form">
	<center>
        <form action="send-mail" method="POST">
		
            <div class="form-group">
				<textarea class="messages"  name="message" placeholder="Enter additional messages" cols="50" rows="6"></textarea>
	        </div><br/>
	                 <div class="col-lg-12 loginbttm">
	            <div class="col-lg-6 login-btm login-text">
	                <!-- Error Message -->
	            </div>
	            <div class="col-lg-6 login-btm login-button">
	                <button type="submit" class="btn btn-outline-primary">Send</button>
	            </div>
	        </div>
		</form>
	</center>
	</div>
	</div>
	</div>
	</div>
</body>
</html>