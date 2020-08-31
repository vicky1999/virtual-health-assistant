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
	<title>Lab Technician Signup</title>
	<link rel="stylesheet" href="styles/login.css" />
</head>

<body>
	    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-2"></div>
            <div class="col-lg-6 col-md-8 login-box">
                <div class="col-lg-12 login-key">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </div>
                <div class="col-lg-12 login-title">
                    SIGN UP
                </div>

                <div class="col-lg-12 login-form">
                    <div class="col-lg-12 login-form">
                        <form action="lab-signup" method="post">
                            <div class="form-group">
                                <label class="form-control-label">NAME</label>
                                <input type="text" class="form-control" name="username" required>
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">PASSWORD</label>
                                <input type="password" class="form-control"  name="password" required>
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">E-Mail</label>
                                <input type="email" class="form-control"  name="mail" required>
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">Mobile Number</label>
                                <input type="text" class="form-control"  name="mobile" required>
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">Gender</label><br/>
                                 <div class="radios">
	                                <input type="radio" id="male" name="gender" value="Male">
									<label for="male">Male</label>
									<input type="radio" id="female" name="gender" value="Female">
									<label for="female">Female</label>
                            	</div>
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">Age</label>
                                <input type="text" class="form-control"  name="age" required>
                            </div>
                            
                            <div class="col-lg-12 loginbttm">
                                <div class="col-lg-6 login-btm login-text">
                                    <!-- Error Message -->
                                </div>
                                <div class="col-lg-6 login-btm login-button">
                                    <button type="submit" class="btn btn-outline-primary">SIGN UP</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-3 col-md-2"></div>
            </div>
        </div>
        </div>
	
</body>
</html>
