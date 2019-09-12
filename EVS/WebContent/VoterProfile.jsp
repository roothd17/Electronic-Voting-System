<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%= request.getParameter("name") %></title>
	<link rel="stylesheet" href="ProfilePage.css">
</head>
<body>
<%
String userName = (String) session.getAttribute("user");
if ( null == userName ) 
{
	//request.setAttribute("Error", "Session has ended.  Please logenter code herein.");
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	
	//Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store");
	
	//Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0);
	
	//Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache");
	//HTTP 1.0 backward enter code here

  RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
  rd.forward(request, response);
  out.println("Session has ended.  Please login.");
}
%>
<div id="main-card">
        <div class="cover-photo"></div>
        <div class="photo">
            <img src="images/username.png" alt="" height="100px" width="100px">
        </div>
        <div class="content">
            <h2 class="name"><%= request.getParameter("name").toUpperCase() %></h1>
            <h3 name ="mobile"><%= request.getParameter("num") %></h3><br> 
            <h3 name="dob"><%= request.getParameter("dob") %></h3><br> 
            <h3 name="city"><%= request.getParameter("district") %></h3><br> 
            <h3 name="pan">PAN Card : <%= request.getParameter("pan") %></h3><br> 
            <h3 name="status">Card Request : <%= request.getParameter("status") %></h3><br> 
            <h3 name="voterid"> VoterID : <%= request.getParameter("vid") %></h3><br> 
            <h3 class="email">
                <a href="mailto:<%= request.getParameter("mail") %>"><%= request.getParameter("mail") %></a>
            </h3>
        </div>
    </div>
</body>
</html>