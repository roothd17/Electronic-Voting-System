<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Panel : <%= request.getSession(false).getAttribute("admin") %></title>
<link rel="stylesheet" type="text/css" href="AfterLogin.css">
</head>
<body>
<%
String userName = (String) session.getAttribute("user");
if ( null == userName ) 
{
	response.setContentType("text/html;charset=UTF-8");
	response.setHeader("Cache-Control", "no-cache");

	//Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store");

	//Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", 0);

	//Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache");
	//HTTP 1.0 backward enter code here
	
  //request.setAttribute("Error", "Session has ended.  Please logenter code herein.");
  RequestDispatcher rd = request.getRequestDispatcher("LoggedOut.jsp");
  rd.include(request, response);
  System.out.println("Session has ended.  Please login.");
}
%>

<div id="mySidenav" class="sidenav">
  <a href="<%=request.getContextPath()%>/LogoutServlet" id="logout">Logout</a>
</div>
<h1> Admin Login Success...</h1>

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

<!-- PENDING VOTER ID -->
        <td>
        	<form  action="ADControl" method="POST">
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
		<!-- UPCOMING ELECTIONS --> 
                <td>
	               <form  action="ADControl" method="POST">
	               <input type="hidden" name="voter" value="<%=request.getAttribute("mail")%>"/>
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
                    	<input type="submit" value="Upcoming Elections" name="btn1" class="button"/>
                    </div>
                </div>
                </form>
            </td>
          </table>
          <table>
     
 <!-- ENTER PARTY DETAILS -->
        <td>
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
                            <a href="PartyDetails.jsp"> 
                            <input type="submit" value="Enter Party details" class="button"/> </a> 
                    </div>
                </div>
            </td>
  <!-- ENTER ELECTION DETAILS -->
        <td>
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
                            <a href="ElectionsDetails.jsp"> 
                            <input type="submit" value="Enter Election details" class="button"/> </a> 
                    </div>
                </div>
            </td>
  
           </table>
		<table>
	<!-- APPROVE RESULTS -->
            <td>
               <form  action="ADControl" method="POST">
            
	            <div class="box">
	                <div class ="first">
	                   <select name="eid" class="dropdown">
			                <option value=""> -- Select ID -- </option>
			
			                <%Iterator ite;%>
							<% 
							ArrayList da = (ArrayList)request.getSession(false).getAttribute("approve");
							System.out.print(request.getSession(false).getAttribute("approve"));
							for( ite = da.iterator(); ite.hasNext(); )
								{
							%>
			                   <option value="<%=ite.next()%>"> <%=ite.next()%>   </option>
			                	<%}%>
               			 </select>			     
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
	                	<input type="submit" value="Approve Results" name="btn1" class="button">
	                </div>
	            </div>
	        </form>
        </td>
<!-- VIEW PARTY DETAILS --> 
                <td>
	               <form  action="ADControl" method="POST">
                <div class="box">
                    <div class ="first">
                        <select name="party" class="dropdown">
			                <option value="">- Select Party -</option>
			                 <%Iterator itr;%>
							<% 
							ArrayList data = (ArrayList)request.getSession(false).getAttribute("party");
							System.out.print(request.getSession(false).getAttribute("party"));
							for( itr = data.iterator(); itr.hasNext(); )
								{
							%>
			                   <option value="<%=itr.next()%>"> <%=itr.next()%>   </option>
			                	<%}%>
                		</select>
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
                    	<input type="submit" value="View Party Details" name="btn1" class="button"/>
                    </div>
                </div>
                </form>
            </td>

<!-- VIEW ELECTION DETAILS --> 
                <td>
	               <form  action="ADControl" method="POST">
                <div class="box">
                    <div class ="first">
                        <select name="eid" class="dropdown">
			                <option value=""> -- Select ID -- </option>
			
			                <%Iterator it;%>
							<% 
							ArrayList d = (ArrayList)request.getSession(false).getAttribute("eid");
							System.out.print(request.getSession(false).getAttribute("eid"));
							for( it = d.iterator(); it.hasNext(); )
								{
							%>
			                   <option value="<%=it.next()%>"> <%=it.next()%>   </option>
			                	<%}%>
                		</select>
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
                    	<input type="submit" value="View Election Details" name="btn1" class="button"/>
                    </div>
                </div>
                </form>
            </td>
</table><br><br><br><br><br>
</body>
</html>