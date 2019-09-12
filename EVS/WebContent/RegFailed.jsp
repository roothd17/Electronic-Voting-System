<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" type="text/css" href="homepage.css">

</head>
<body>
<center><img src="images/error.png" height="350" width="350" style=" margin-top:95px">
<!--  <h1> Some technical error occoured !  </h1> -->
<h1> <%= request.getAttribute("error") %> </h1>
</body>
</html>