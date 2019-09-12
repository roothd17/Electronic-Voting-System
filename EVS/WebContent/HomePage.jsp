<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Electronic Voting System</title>

<link rel="stylesheet" type="text/css" href="homepage.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
<body>

<div id="mySidenav" class="sidenav">
  <a href="Register.jsp" id="newuser">New User  </a>
  <a href="EOfficer.jsp" id="eo">E Officer </a>
  <a href="Admin.jsp" id="admin">Admin&nbsp;&nbsp; </a>
   <a href="HomePage.jsp" id="home"  style=" left: 0px;"> Home </a>
</div>

<div id="container-login">
        <div id="title">
            <i class="material-icons lock"></i> Voter Login
        </div>

        <form action ="DBControl" method="POST" >
            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/username.png" width="22" height="22" alt="Name"></i>
                </div>			
                <input id="username" placeholder="Username" type="text" name="email" required class="validate" autocomplete="off">
            </div>

            <div class="clearfix"></div>

            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/key.png" width="22" height="22" alt="Key"></i>
                </div>
                <input id="password" placeholder="Password" type="password" name="key" required class="validate" autocomplete="off">
            </div>
			<br><br>
            <input type="submit" value="Login" name="btn1" />
        </form>
        <br>
        <div class="register"><br>
            Don't have an account yet?
            <a href="Register.jsp"><button id="register-link">Register here</button></a>
        </div>
</div>
<!--  
<div style="margin-left:100px; margin-top: 100px;">
	<form action ="DBControl" method="POST">
		UName : <input type="text" name="email"/><br>
		Password : <input type="password" name="key"/><br>
		<input type="submit" value="Login" name="btn1"/>
	</form>
</div>
-->
<!-- 
<br> <a href="Register.jsp" > New User ? </a>
<br> <a href="EOfficer.jsp" > Electoral Officer Login </a>
<br> <a href="Admin.jsp" > Admin Login </a>
 -->
</body>
</html>