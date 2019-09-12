<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
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
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EO Login Panel</title>
<link rel="stylesheet" type="text/css" href="AfterLogin.css">

</head>
<body>


<div id="mySidenav" class="sidenav">
  <a href="<%=request.getContextPath()%>/LogoutServlet" id="logout">Logout</a>
</div>

<h1> Hii <%= request.getSession(false).getAttribute("eo")%> </h1><br><br><br><br>

<center>
         <table>
     <!-- VIEW VOTER LIST -->
            <td>
            <form  action="EOControl" method="POST">
	            <div class="box">
	                <div class ="first">
	                    <div class="first1">
	                    </div>
	                    <div class="first2">
	                    </div>
	                </div>
	                <div class ="second">
	                    <div class="second1">
	                        <div class="image">
	                            <img src="back.png" alt="img" width="200" height="100"/>
	                        </div>
	                    </div>
	                    <div class="second2">
	                    </div>
	                </div>
	                <div class ="third">
	                      <input type="submit" value="View Voter List" class="button" name="btn1"/>
	                </div>
	            </div>
            </form>
        </td>
	<!-- UPDATE PROFILE SECTION -->
        <td>
        	<form  action="EOControl" method="POST">
                <div class="box">
                    <div class ="first">
                        <div class="first1">
                        </div>
                        <div class="first2">
                        </div>
                    </div>
                    <div class ="second">
                        <div class="second1">
                            <div class="image">
                                <img src="back.png" alt="img" width="200" height="100"/>
                            </div>
                        </div>
                        <div class="second2">
                        </div>
                    </div>
                    <div class ="third">
                          <input type="submit" value="Pending VoterID req." class="button" name="btn1"/>
                    </div>
                </div>
               </form>
            </td>
      <!-- APPLY FOR VOTER ID -->
            <td>
	            <form  action="EOControl" method="POST">	 			
                    <div class="box">
                        <div class ="first">
                            <div class="first1">
                            </div>
                            <div class="first2">
                            </div>
                        </div>
                        <div class ="second">
                            <div class="second1">
                                <div class="image">
                                    <img src="back.png" alt="img" width="200" height="100"/>
                                </div>
                            </div>
                            <div class="second2">
                            </div>
                        </div>
                        <div class ="third">
                              <input type="submit" value="Generate VotedID" class="button" name="btn1"/>
                        </div>
                    </div>
                    </form>
                </td>
        </table> 

<br><br>
<!--  
<form  action="EOControl" method="POST">
 	<input type="submit" value="View Pending Voter ID request" name="btn1"/>

</form>
<br><br>

<form  action="EOControl" method="POST">
 	<input type="submit" value="Generate VotedID" name="btn1"/>

</form>


<br><br>
<a href="%=//request.getContextPath()%>/LogoutServlet" onclick=preventBack()>Logout</a> -->

</body>
</html>