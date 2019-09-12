package com.wippro.logout;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LogoutServlet extends HttpServlet
{
 private static final long serialVersionUID = 1L;
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 {
 
	 HttpSession session = request.getSession(false); //Fetch session object
	 
	 if( session != null ) //If session is not null
	 {
//		 response.setContentType("text/html;charset=UTF-8");
//		 response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//		 response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
//		 response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		 response.setDateHeader("Expires", 0); // Proxies.
		System.out.println(" Before Invalidate : "+ request.getSession(false).getAttribute("user") );

		session.invalidate(); //removes all session attributes bound to the session
		
		//System.out.println(" After Invalidate : "+ request.getSession(false).getAttribute("voter") );
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoggedOut.jsp");
		request.setAttribute("error", "You have logged out successfully");
		requestDispatcher.forward(request, response);
		System.out.println("Logged out");
	 }
	 else
	 {
		 System.out.println("Session is null");
	 }
 }
}