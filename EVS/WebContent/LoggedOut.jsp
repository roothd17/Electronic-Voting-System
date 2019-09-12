<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
<link rel="stylesheet" type="text/css" href="homepage.css">
<!--  
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
-->

<%
	//request.setAttribute("Error", "Session has ended.  Please logenter code herein.");
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	//Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.

	//Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0);
	
	//Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache");
	//HTTP 1.0 backward enter code here
%>

<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">

</head>
<body  onload="fix()">

<div id="mySidenav" class="sidenav">
	<a href="HomePage.jsp" id="home"> Home </a>
  	<a href="Register.jsp" id="newuser">New User  </a>
  	<a href="EOfficer.jsp" id="eo">E Officer </a>
  	<a href="Admin.jsp" id="admin">Admin&nbsp;&nbsp; </a> 
</div>
<center> <img src="images/error.png" height="350" width="350" style=" margin-top:95px">
<h1> <%= request.getAttribute("error") %> </h1>
</body>
<script>
    if ( window.history.replaceState ) 
    {
      window.history.replaceState( null, null, window.location.href );
    }
</script>
</html>

 
    