<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%= request.getSession(false).getAttribute("voter") %></title>
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
  System.out.println("Session has ended.  Please login.");
}
%>

<div id="container-login">
<br>
      <div id="title">
          <i class="material-icons lock"></i> Update Voter Profile
      </div>
<br><br>
	<form  action="DBControl" method="POST">
		<input type="hidden" name="mail" value="<%= request.getSession(false).getAttribute("voter") %>"/>
		
		<div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/loc.png" width="28" height="22" alt="Name"></i>
                </div>
                <input type="text" name="district" placeholder="District"  required class="validate" autocomplete="off"/>
            </div>

            <div class="clearfix"></div>
		 
		 <div class="input">
                <div class="input-addon">
                    <i class="material-icons"><img src="images/card.png" width="23" height="22" alt="Name"></i>
                </div>
                <input type="text" name="pan" placeholder="Pan card"  required class="validate" autocomplete="off" />
            </div>
		 
		 <br><br><br><br>
	 	<input type="submit" value="UpdateProfile" name="btn1"/>
	</form>
</div>
</body>
</html>