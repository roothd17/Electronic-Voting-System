<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@page language="java" import="java.util.*" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Declare Result</title>
<link rel="stylesheet" type="text/css" href="tableview.css">

</head>
<body>
<% //In case, if Voter session is not set, redirect to Login page
	if( (request.getSession(false).getAttribute("admin")== null) )
	{
	%>
		<jsp:forward page="Admin.jsp"></jsp:forward>
	<%
	} 
%>
<center>
<h1> Declare Result </h1>

<form action="ADControl" method="POST">
	<table align="center">
		<tr>
			<th width="119" height="70"><b>Election ID</b></th>
			<th width="168"><b>Party Name</b></th>
			<th width="168"><b>Candidate Name</b></th>
			<th width="168"><b>CID</b></th>
			<th width="168"><b>District </b></th>
			<th width="168"><b>Date</b></th>
			<th width="168"><b>Votes Recieved</b></th>
			<th width="168"><b>Declare Result</b></th>
		</tr>
		
		<%Iterator itr; System.out.println("Inside JSP");%>
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
			<input type="hidden" name="eid" value="<%=itr.next()%>">
			<td width="168"><input type="submit" value="<%=itr.next()%>" name = "declareRes" /></td>
		</tr>
		<%}%>
	</table>

</form>
</center>
</body>
</html>