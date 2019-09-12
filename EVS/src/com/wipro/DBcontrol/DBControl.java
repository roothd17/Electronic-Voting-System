package com.wipro.DBcontrol;

import javax.servlet.http.HttpServlet;
import com.wipro.DBConnect.*;
import com.wipro.ElectDAO.Edao;
import com.wipro.PartyService.PService;
import com.wipro.VoterDAO.Vdao;
import com.wipro.bean.*;
import com.wipro.VoterService.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DBControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	static{	System.out.print("HERE"); }
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
			
			VoterBean vb = new VoterBean();
			vb.setEmail(email);
			vb.setKey(key);
			
			Vdao vd = new Vdao();
			int desc = vd.checkUser( vb );
			
			// checkUser() : Function that checks weather a user with that uname and key exists in the table
			System.out.print("HERE and desc = "+desc);
			//resp.sendRedirect("LoginSuccess.jsp");
			Edao ed = new Edao();
			ArrayList li = ed.dropdownVal();
			ArrayList eid = ed.dropdownElect();
			
			if( desc != 0 )
			{
				HttpSession session = req.getSession(); //Creating a session
				session.setAttribute("user", email);
				session.setAttribute("voter", email); //setting session attribute
				session.setAttribute("mail", email);
				session.setAttribute("party", li);
				session.setAttribute("eid", eid);
				//resp.sendRedirect("LoginSuccess.jsp?mail="+email);
				req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
			}
			else
				resp.sendRedirect("LoginFail.jsp");
		}
		if( call.equals("Register") )
		{
			String name = req.getParameter("name");
			String mail = req.getParameter("mail");
			String number =  req.getParameter("phno").replaceAll("\\D+", "").toString().trim();
			String key = req.getParameter("key");
			String dob = req.getParameter("dob");
			//String cardReq = req.getParameter("cardReq");
			System.out.println(name+" "+mail+" "+number+" "+key+" "+dob+" ");
			
			VoterBean voter = new VoterBean();
			
			voter.setName(name);
			voter.setNumber(number);
			voter.setEmail(mail);
			voter.setKey( key );
			//voter.setCardRequest(cardReq);
			voter.setDob(dob);
			
			VService vs = new VService();
			String res  = vs.insertVoter(voter);
			
			if( res.equals(" --- Successfully inserted a row --- ") )
				resp.sendRedirect("RegSuccessful.jsp");
			else
				resp.sendRedirect("RegFailed.jsp");

				
			
		}
		else if( call.equals("ViewProfile") )
		{
			String mail =  req.getParameter("mail");
			VService vs = new VService();
			VoterBean voter = vs.viewVoter(mail.toLowerCase());
			System.out.println(" Mail is "+mail);
			if( voter != null )
				resp.sendRedirect("VoterProfile.jsp?name="+voter.getName()+"&mail="+mail+"&dob="+voter.getDob()+"&num="+voter.getNumber()+"&status="+voter.isCardRequest()+"&district="+voter.getDistrict()+"&pan="+voter.getPan()+"&vid="+voter.getVoterID() );
		
			else
			{
				System.out.println("No Profile found");
				resp.sendRedirect("Register.jsp");
			}
		}
		
		else if( call.equals("UpdateProfile") )
		{
			String mail =  req.getParameter("mail");
			String district = req.getParameter("district");
			String pan = req.getParameter("pan");
			
			VoterBean voter = new VoterBean();
			
			voter.setDistrict(district);
			voter.setPan(pan);
			voter.setEmail(mail);
			System.out.println(" Mail is "+mail+" "+district+" "+pan);
			
			VService vs = new VService();
			String res  = vs.updateProfile(voter);
			
			RequestDispatcher dispatcher = null;
			
			if( res.equals(" --- Successfully inserted a row --- ") )
				dispatcher = req.getRequestDispatcher("RegSuccessful.jsp");
			else
			{
				req.setAttribute("error", res);
				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
			}
			
			if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
		}
		
		// CAST VOTE : Function
		try {
			String v = req.getParameter("party");
			System.out.println(" v "+v);
			if( v.length() >= 2 && !v.equals("null") && v != null )
			{
				String mail = req.getParameter("mail");
				String party = req.getParameter("party");
				VService vs = new VService();
				System.out.println(" Party is "+party+" and mail is "+mail);
				//VoterBean voter = new VoterBean();
				String res = vs.castVote(mail.toLowerCase(),  party);
				
				if( res.equals(" --- Successfully casted your vote --- ") )
					resp.sendRedirect("HomePage.jsp");
				
				else
				{
					resp.sendRedirect("RegFailed.jsp");
					System.out.println(" Something went wrong in voting ");
				}
			
			}
		}
		catch( Exception e )
		{
			System.out.println(" !! "+e+" !! ");
		}
//		if( call.equals("CastVote") )
//		{
//			String mail = req.getParameter("mail");
//			String party = req.getParameter("party");
//			VService vs = new VService();
//			System.out.println(" Party is "+party);
//			//VoterBean voter = new VoterBean();
//			String res = vs.castVote(mail.toLowerCase(),  party);
//			
//			if( res.equals(" --- Successfully casted your vote --- ") )
//				resp.sendRedirect("LoginSuccess.jsp?mail="+mail);
//			
//			else
//				System.out.println(" Something went wrong in voting ");
//			
//		}
		
		if( call.equals("Cast Vote!!") )
		{
			String mail = req.getParameter("mail");
			VService vs = new VService();
			int hasID = vs.checkID(mail, "voterid");
			
			RequestDispatcher dispatcher = null;
			System.out.println(" hasID "+hasID);
			if( hasID == 1 )
			{
				System.out.println("Not applied for Voter ID yet !!!");
				String error = " You don't have Voter ID yet ! ";
				req.setAttribute("error", error);
				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
			}
			else if( vs.checkID(mail, "votedfor") == 2 )
			{
				String error = " You already Voted ! ";
				req.setAttribute("error", error);
				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
			}
			else
			{
				VoterBean voter = vs.viewVoter(mail.toLowerCase());
				
				ArrayList li = vs.viewContestingParty( voter.getDistrict() );
				
				req.setAttribute("data",li);
	
			  //Dispatching request
	
			    dispatcher = req.getRequestDispatcher("VotingSection.jsp");
			  // The RequestDispatcher interface provides the facility of dispatching the request to another resource it may be html, servlet or jsp. 
			  // This interface can also be used to include the content of another resource also. It is one of the way of servlet collaboration.
			}
			
		  if ( dispatcher != null )
			  dispatcher.forward(req, resp );
		  else
			  System.out.print("\n Dispatcher Error !! ");
		}
		
		if( call.equals("Apply for Voter ID") )
		{
			String mail = req.getParameter("mail");
			VService vs = new VService();
			
			VoterBean voter = new VoterBean();
			voter.setEmail(mail.toLowerCase());
			String res  = vs.applyforVID(voter);
			
			RequestDispatcher dispatcher = null;
			
			
			if( res.equals(" --- Successfully inserted a row --- ") )
				dispatcher = req.getRequestDispatcher("RegSuccessful.jsp");
//			
			else
			{
				req.setAttribute("error", res);
				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
			}
//			if( res.equals("Update your Profile first before Appplying for VID") )
//			{
//				req.setAttribute("error", res);
//				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
//			}
//			else
//			{
//				String error = " You already applied for Voter ID ! ";
//				req.setAttribute("error", error);
//				dispatcher = req.getRequestDispatcher("RegFailed.jsp");
//			}
			
			if ( dispatcher != null )
				  dispatcher.forward(req, resp );
			  else
				  System.out.print("\n Dispatcher Error !! ");
			
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
//			String query = "SELECT * FROM Crap1 WHERE email = ? AND key = ?";
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

}

