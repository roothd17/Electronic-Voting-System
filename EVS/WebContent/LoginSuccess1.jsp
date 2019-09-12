<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
      <%@page language="java" import="java.util.*" %>
   <% //In case, if Voter session is not set, redirect to Login page
	if( (request.getSession(false).getAttribute("voter")== null) )
	{
	%>
		<jsp:forward page="HomePage.jsp"></jsp:forward>
	<%
	} 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%= request.getAttribute("mail") %></title>
	<style>
		#body
		{
			background-image: url("images/back.jpg");
			background-size:  cover;
			background-position: center;
			
		}
		#mySidenav a {
		  position: absolute;
		  right: -50px;
		  transition: 0.4s;
		  padding: 15px;
		  width: 100px;
		  text-decoration: none;
		  font-size: 20px;
		  color: white;
		  border-radius: 0 5px 5px 0;
		}
		
		#mySidenav a:hover {
		  right: 0;
		}
		#logout {
			top: 50px;
			background-color: #2196F3;
		}
		
		
	</style>
	
<% //In case, if Voter session is not set, redirect to Login page
	if( request.getSession(false).getAttribute("voter") == null )
	{
		System.out.println("Inside if");
	%>
		<jsp:forward page="HomePage.jsp"></jsp:forward>
	<%
	} 
%>
</head>
<body BGbackground="back.jpg">

<div id="mySidenav" class="sidenav">
  <a href="<%=request.getContextPath()%>/LogoutServlet" id="logout">Logout</a>

</div>
<br><h1> Hi <%= request.getSession(false).getAttribute("voter") %></h1><br>

<!-- < % String mail=request.getAttribute("mail"); %> --> 
<form  action="DBControl" method="POST">
 	<input type="hidden" name="mail" value="<%=request.getAttribute("mail")%>"/>
 	<input type="submit" value="ViewProfile" name="btn1"/>
</form><br><br>


<a href="UpdateVoterProfile.jsp"> <input type="button" value ="Update Profile"></a> 
<br><br><br>

<form  action="DBControl" method="POST">
	<input type="hidden" name="mail" value="<%=request.getAttribute("mail")%>"/>
 	<input type="submit" value="Apply for Voter ID" name="btn1"/>
</form><br><br>

<form  action="ADControl" method="POST">
 	<input type="submit" value="Upcoming Elections" name="btn1"/>
</form><br><br> 

<form  action="ADControl" method="POST">
	<!-- Party Name : <input type="text" name="party"/> -->
	   <select name="party">
              <option value="">- Select Party -</option>

              <%Iterator itr;%>
				<% 
				ArrayList data = (ArrayList)request.getAttribute("party");
				System.out.print(data);
				for( itr = data.iterator(); itr.hasNext(); )
					{
				%>
                 <option value="<%=itr.next()%>"> <%=itr.next()%>   </option>
              	<%}%>
         </select>
 	<input type="submit" value="View Party Details" name="btn1"/>
</form><br><br>

	<form  action="ADControl" method="POST">
	<!--  Election ID : <input type="text" name="eid"/> -->
		<select name="eid">
                <option value=""> -- Select ID -- </option>

                <%Iterator it;%>
				<% 
				ArrayList d = (ArrayList)request.getAttribute("eid");
				System.out.print(d);
				for( it = d.iterator(); it.hasNext(); )
					{
				%>
                   <option value="<%=it.next()%>"> <%=it.next()%>   </option>
                	<%}%>
                </select>
 	<input type="submit" value="View Election Details" name="btn1"/>
</form><br><br>

<form  action="ADControl" method="POST">
	<!--  Election ID : <input type="text" name="eid"/> -->
			<select name="eid">
                <option value=""> -- Select ID -- </option>

                <%Iterator ite;%>
				<% 
				ArrayList dx = (ArrayList)request.getAttribute("eid");
				System.out.print(dx);
				for( ite = d.iterator(); ite.hasNext(); )
					{
				%>
                   <option value="<%=ite.next()%>"> <%=ite.next()%>   </option>
                	<%}%>
                </select>
 	<input type="submit" value="View Election Results" name="btn1"/>
</form><br><br>

<form action="DBControl" method="POST">
	<input type="hidden" name="mail" value="<%=request.getAttribute("mail")%>"/>
	<input type="submit" value="Cast Vote!!" name="btn1"/>
<!--  <a href="VotingSection.jsp"> <input type="button" value ="Cast Vote"></a> -->
</form>
<br><br>

<!--  <form  action="DBControl" method="POST">
	<input type="hidden" name="mail" value="//request.getAttribute("mail")%>"/>
	Vote for : <input type="text" name="party"/>
 	<input type="submit" value="CastVote" name="btn1"/>
</form><br><br>
-->
<a href="LogoutServlet">Logout</a>
<br><br>
</body>
</html>


