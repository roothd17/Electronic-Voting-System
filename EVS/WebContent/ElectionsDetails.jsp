<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page language="java" import="java.text.SimpleDateFormat" import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Election Details</title>
<link rel="stylesheet" type="text/css" href="homepage.css">
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
<div id="container-electiondetails">
	<br>
	   <div id="title">
	       <i class="material-icons lock"></i> Enter Election Details
	   </div>
	<br>
	<form  action="ADControl" method="POST">
	
		<div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/id.jpg" width="22" height="22" alt="Name"></i>
             </div>
             <input type="text" name="eid" placeholder=" Election ID"  required class="validate" autocomplete="off"/>
        </div>
        <div class="clearfix"></div>
        <% 	String today = "" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime() );
	    	%>
        <div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/call.jpg" width="26" height="22" alt="Name"></i>
             </div>
             <input type="date" name="date" min="<%=today%>"required class="validate" autocomplete="off"/>
        </div>
        <div class="clearfix"></div>
        
		<div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/party.jpg" width="22" height="22" alt="Name"></i>
             </div>
             <input type="text" name="name" placeholder=" Party Name"  required class="validate" autocomplete="off"/>
        </div>
        <div class="clearfix"></div>
        
        <div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/username.png" width="22" height="22" alt="Name"></i>
             </div>
             <input type="text" name="cname" placeholder=" Candidate Name"  required class="validate" autocomplete="off"/>
        </div>
        <div class="clearfix"></div>
        
		<div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/id.jpg" width="22" height="22" alt="Name"></i>
             </div>
             <input type="text" name="cid" placeholder=" Candidate ID"  required class="validate" autocomplete="off"/>
        </div>
        <div class="clearfix"></div>
        
		<div class="input">
             <div class="input-addon">
                 <i class="material-icons"><img src="images/loc.png" width="26" height="22" alt="Name"></i>
             </div>
             <input type="text" name="dist" placeholder=" District"  required class="validate" autocomplete="off"/>
        </div>      
        <br><br><br>
	 	<input type="submit" value="Submit Election Details" name="btn1"/>
	</form>
</div>

</body>
</html>