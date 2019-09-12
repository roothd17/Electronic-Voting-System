<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generate Voter ID</title>
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
  out.println("Session has ended.  Please login.");
}
%>
<center>
<h1> Generate Voter ID </h1>

<form  action="EOControl" method="POST">
	<table align="center">
		<tr>
			<th width="119" height="70"><b>name</b></th>
			<th width="168"><b>number</b></th>
			<th width="168"><b>dob</b></th>
			<th width="168"><b>mail</b></th>
			<th width="168"><b>district</b></th>
			<th width="168"><b>pan card</b></th>
			<th width="168"><b>Req. Status</b></th>
			<th width="168"><b>Generate ID</b></th>
		</tr>
		<%Iterator itr;%>
		<% ArrayList data= (ArrayList)request.getAttribute("data");
		System.out.println(data);
		for (itr = data.iterator(); itr.hasNext(); )
		{
		%>
		<tr>
			<td width="119"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td> 
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			<td width="168"><%=itr.next()%></td>
			
			<td width="168"><input type="submit" value="<%=itr.next()%>" name="votermail"/></td>
		</tr>
		<%}%>
	</table>
</form>
</center>
</body>
</html>