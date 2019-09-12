<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page language="java" import="java.util.*" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%= request.getSession(false).getAttribute("voter") %></title>
<link rel="stylesheet" type="text/css" href="tableview.css">
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
  System.out.println("Session has ended.  Please login.");
}
%>
<center>
<h1> Voting Section </h1>

<form  action="DBControl" method="POST">
	
	<table align="center" >
		<tr>
			<th width="119" height="70" ><b>Party logo</b></th>
			<th width="119" ><b>Party Name</b></th>
			<th width="168"><b>Candidate Name</b></th>
			<th width="168"><b>District</b></th>
			<th width="168"><b>Election ID </b></th>
			<th width="168"><b>Vote !! </b></th>
		</tr>
		
		<%Iterator itr;%>
		<% ArrayList data= (ArrayList)request.getAttribute("data");
		for (itr = data.iterator(); itr.hasNext(); )
		{
		%>
		<tr>
			<td width="119"><img src="images/username.png" height="50" width="50"></td>
			<td width="119"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<input type="hidden" name="mail" value="<%=request.getSession(false).getAttribute("voter")%>"/>
			<td width="168"><input type="submit" value="<%=itr.next()%>" name="party"/></td>
		</tr>
		<%}%>
	</table>
	
</form><br><br></center>
</body>
</html>