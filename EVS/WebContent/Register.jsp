<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<%@page language="java" import="java.text.SimpleDateFormat" import="java.util.Calendar" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="homepage.css">
</head>
<body>
<!--  <p>  REGISTER.... </p> -->

<div id="mySidenav" class="sidenav">
  <a href="Register.jsp" id="newuser" style=" left: 0px;" >New User  </a>
  <a href="EOfficer.jsp" id="eo">E Officer </a>
  <a href="Admin.jsp" id="admin">Admin&nbsp;&nbsp;&nbsp; </a>
  <a href="HomePage.jsp" id="home"> Home </a>
</div>

<div id="container-register">
        <div id="title">
            <i class="material-icons lock"></i> Register
        </div>

        <form action="DBControl" method="POST">
            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/username.png" width="22" height="22" alt="Name"></i>
                </div>
                <input id="username" placeholder="Name" type="text" name="name" required class="validate" autocomplete="off">
            </div>

            <div class="clearfix"></div>

            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/email.jpg" width="22" height="22" alt="mail"></i>
                </div>
                <input id="email" placeholder="Email" type="email" name="mail" required class="validate" autocomplete="off">
            </div>

            <div class="clearfix"></div>
			
			<div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/phone.jpg" width="22" height="22" alt="Ph"></i>
                </div>
                <input id="phno" placeholder="Phone Number" type="number" name="phno" min=10 required class="validate" autocomplete="off">
            </div>
            <div class="clearfix"></div>
            <% 	String today = "" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime() );
	    		String [] now = today.split("-");
	    		int year = Integer.parseInt( now[0] ) - 18;
	    	%>
            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/call.jpg" width="22" height="20" alt="Cal"></i>
                </div>
                <input id="dob" placeholder="dd/mm/yyyy" type="date" max="<%=year%>-12-31" name="dob" required class="validate" autocomplete="off">
            </div>
            
            <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/key.png" width="22" height="22" alt="Key"></i>
                </div>
                <input id="password" placeholder="Password" type="password" name="key" min=8 required class="validate" autocomplete="off">
            </div>
			<br>
            <div class="remember-me">
                <input type="checkbox">
                <span style="color: #DDD">I accept Terms of Service</span>
            </div>
			
			<!--  <div class="input">
					Apply for Voter Card : 
					  <input type="radio" name="cardReq" value="true"> Yes !&nbsp;&nbsp;&nbsp;&nbsp;
					  <input type="radio" name="cardReq" value="false"> No !<br>
  			</div> -->
  			<br>
            <input type="submit" value="Register" name="btn1" />
            
           
        </form>
         <div class="register">
            Do you already have an account?
            <a href="HomePage.jsp"><button id="register-link">Log In here</button></a>
        </div>
<!--  
<form action="DBControl" method="POST">
	 Name : <input type="text" name="name"/><br>
	 Email :  <input type="email" name="mail"/><br>
	 Ph. No. :  <input type="number" name="phno" min=10/><br>
	 D.O.B. : <input type="date" name="dob" /><br>
	Apply for Voter Card : 
  <input type="radio" name="cardReq" value="true"> Yes !&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="radio" name="cardReq" value="false"> No !<br>
	Password : <input type="password" name="key" min=8/><br>
	
	<input type="submit" value="Register" name="btn1"/>
</form>
-->
</body>
</html>