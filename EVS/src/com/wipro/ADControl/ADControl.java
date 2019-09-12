package com.wipro.ADControl;

import javax.servlet.http.HttpServlet;

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
import com.wipro.PartyDAO.Pdao;
import com.wipro.PartyService.PService;
import com.wipro.VoterService.VService;
import com.wipro.bean.ElectionBean;
import com.wipro.bean.PartyBean;
import com.wipro.bean.VoterBean;

public class ADControl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String call = req.getParameter("btn1");
		if( call == null )
			call = "NA";
		
		if( call.equals("Login") )
		{
		
			String email = req.getParameter("email").trim();
			String key = req.getParameter("key");
			int desc = checkUser( email, key );
			
			// checkUser() : Function that checks weather a user with that uname and key exists in the table
			System.out.print("HERE and desc = "+desc);
			//resp.sendRedirect("LoginSuccess.jsp");
			Edao ed = new Edao();
			ArrayList li = ed.dropdownVal();
			ArrayList eid = ed.dropdownElect();
			ArrayList approved = ed.dropdownApprove();
			
			//System.out.println(eid);
			//System.out.println(li);
			if( desc != 0 )
			{
				HttpSession session = req.getSession(); //Creating a session
				session.setAttribute("user", email);
				session.setAttribute("admin", email); //setting session attribute
				req.setAttribute("mail", email);
				//req.setAttribute("party", li);
				session.setAttribute("party", li);
				session.setAttribute("eid", eid);
				session.setAttribute("approve", approved);
				//req.setAttribute("eid", eid);
				resp.sendRedirect("ADLoginSuccess.jsp");
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
		
		if( call.equals("Submit Party Details") )
		{
			String name = req.getParameter("name");
			String cname = req.getParameter("cname");
			String crole =  req.getParameter("crole");
			String cid = req.getParameter("cid");
			
			System.out.println(name+" "+cname+" "+crole+" "+cid);
			
			PartyBean party = new PartyBean();
			
			party.setName(name);
			party.setCname(cname);
			party.setCrole(crole);
			party.setCid(cid);
			
			PService ps = new PService();
			String res  = ps.insertParty(party);
			
			if( res.equals(" --- Successfully inserted a row --- ") )
				resp.sendRedirect("RegSuccessful.jsp");
			else
				resp.sendRedirect("RegFailed.jsp");
		}
		
		if( call.equals("View Party Details") )
		{
			String party = req.getParameter("party");
			PService ps = new PService();
			ArrayList li = ps.viewPartyDetails( party );
			
				req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewPartyDetails.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		if( call.equals("Submit Election Details") )
		{
			String pname = req.getParameter("name");
			String cname = req.getParameter("cname");
			String cid = req.getParameter("cid");
			String dist =  req.getParameter("dist");
			String eid = req.getParameter("eid");
			String date = req.getParameter("date");
			
			System.out.println(pname+" "+cname+" "+cid+" "+dist+" "+eid+" "+date);
			
			ElectionBean elect = new ElectionBean();
			
			elect.setPname(pname);
			elect.setCname(cname);
			elect.setDist(dist);
			elect.setEid(eid);
			elect.setDate(date);
			elect.setCid(cid);
			
			PService ps = new PService();
			String res  = ps.insertElection(elect);
			
			if( res.equals(" --- Successfully inserted a row --- ") )
				resp.sendRedirect("RegSuccessful.jsp");
			else
				resp.sendRedirect("RegFailed.jsp");
		}
		
		if( call.equals("View Election Details") )
		{
			String eid = req.getParameter("eid");
			
			PService ps = new PService();
			ArrayList li = ps.viewElectionDetails( eid );
			
			req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewElectionDetails.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		if( call.equals("Upcoming Elections") )
		{
			PService ps = new PService();
			ArrayList li = ps.viewUpcomingElection( );
			
			req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewElectionDetails.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		if( call.equals("Approve Results") )
		{
			String eid = req.getParameter("eid");
			
			PService ps = new PService();
			ArrayList li = ps.approveResults( eid );
			
			req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("DeclareResult.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		try 
		{
			String re =  req.getParameter("declareRes");
			System.out.println(" re : "+re);
			if( re.length() > 2 && ! re.equalsIgnoreCase("null") ) 
			{
				String cid = req.getParameter("declareRes");
				String eid = req.getParameter("eid");
				System.out.println(" Set Win for "+cid+" on eid "+eid);
				Edao ed = new Edao();
				ElectionBean elect = new ElectionBean();
				elect.setCid(cid);
				elect.setEid(eid);
				
				String res = ed.declareRes(elect);
								
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
		
		if( call.equals("View Election Results") )
		{
			String eid = req.getParameter("eid");
			
			Pdao pd = new Pdao();
			ElectionBean elect = new ElectionBean();
			elect.setEid(eid);
			
			ArrayList li = pd.viewResults(elect);
			
			req.setAttribute("data",li);

			  //Dispatching request

			  RequestDispatcher dispatcher = req.getRequestDispatcher("ViewElectionResults.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			  
			  if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
	}
	
	public static int checkUser( String email, String key )
	{
		int crap = 0;
		
		try
		{
			Connection connection = DBConnect.connect();
			
			String query = "SELECT * FROM admin WHERE email = ? AND key = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, key);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			// The object of ResultSet maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
			System.out.println( resultSet );
			resultSet.next();
			String n = resultSet.getString("email");
			String k = resultSet.getString("key");
			 
			
			System.out.println( n+" "+k );
			if( n.equalsIgnoreCase( email ) && k.equals(key)  ) 
				crap++;
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap;
	}
}
