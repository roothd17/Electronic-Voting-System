package com.wipro.EOControl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.DBConnect.DBConnect;
import com.wipro.ElectDAO.*;
import com.wipro.VoterService.VService;
import com.wipro.bean.EOBean;
import com.wipro.bean.VoterBean;

public class EOControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String call = req.getParameter("btn1");
		 if( call == null )
		 {
			 System.out.println("Call is null");
			 call = "NA";
		 }
		if( call.equals("Login") )
		{
		
			String email = req.getParameter("email").trim();
			String key = req.getParameter("key");
			
			EOBean eb = new EOBean();
			eb.setName(email.toLowerCase() );
			eb.setKey(key);
			
			Edao ed = new Edao();
			int desc = ed.checkUser( eb );
			
			// checkUser() : Function that checks weather a user with that uname and key exists in the table
			System.out.print("HERE and desc = "+desc);
			//resp.sendRedirect("LoginSuccess.jsp");
			if( desc != 0 )
			{
				HttpSession session = req.getSession(); //Creating a session
				session.setAttribute("user", email);
				session.setAttribute("eo", eb.getName()); //setting session attribute
				 //req.setAttribute("mail", email);
				 req.getRequestDispatcher("EOLoginSuccess.jsp").forward(req, resp);
				//resp.sendRedirect("EOLoginSuccess.jsp");
			}
			else
				resp.sendRedirect("LoginFail.jsp");
		}
		
		if( call.equals("Pending VoterID req.") )
		{
			Edao ed = new Edao();
			ArrayList li = ed.viewPendingID();
			
				req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("PendingID.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		if( call.equals("View Voter List") )
		{
			Edao ed = new Edao();
			ArrayList li = ed.viewVoterList();
			
				req.setAttribute("data",li);

			  //Dispatching request
			  RequestDispatcher dispatcher = req.getRequestDispatcher("VoterList.jsp");
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		if( call.equals("Generate VotedID") )
		{
			Edao ed = new Edao();
			ArrayList li = ed.PendingID();
			
			req.setAttribute("data",li);
			
			//Dispatching request
			  RequestDispatcher dispatcher = req.getRequestDispatcher("GenerateVoterID.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		try 
		{
			String v = req.getParameter("votermail");
			System.out.println(" v "+v);
			if( v.length() > 5 ) 
			{
				String mail = req.getParameter("votermail");
				System.out.println(" Approve req for "+mail);
				VService vs = new VService();
				VoterBean voter = vs.viewVoter(mail.toLowerCase());
				System.out.println(" PAN is "+voter.getPan()+" and Ph no is "+voter.getNumber()+" and email is "+voter.getEmail() );
				Edao ed = new Edao();
				
				String res  = ed.approveID(voter);
				
				if( res.equals(" --- Successfully inserted a row --- ") )
					resp.sendRedirect("RegSuccessful.jsp");
				else
					resp.sendRedirect("RegFailed.jsp");
				
			}
		}
		catch( Exception e )
		{
			System.out.println(" !! "+e+" !! ");
		}
		
	}
}
	
//	public static int checkUser( String email, String key )
//	{
//		int crap = 0;
//		
//		try
//		{
//			Connection connection = DBConnect.connect();
//			
//			String query = "SELECT * FROM EO WHERE email = ? AND key = ?";
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
//			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
//			
//			
//			preparedStatement.setString(1, email);
//			preparedStatement.setString(2, key);
//			
//			ResultSet resultSet = preparedStatement.executeQuery();
//			// The object of ResultSet maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
//			System.out.println( resultSet );
//			resultSet.next();
//			String n = resultSet.getString("email");
//			String k = resultSet.getString("key");
//			 
//			
//			System.out.println( n+" "+k );
//			if( n.equalsIgnoreCase( email ) && k.equals(key)  ) 
//				crap++;
//			
//			resultSet.close();
//		}
//		catch( Exception e )
//		{
//			System.out.println(" !! Error -> "+e+" !! ");
//		}
//		
//		return crap;
//	}


