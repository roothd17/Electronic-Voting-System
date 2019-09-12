package com.wipro.VoterDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.wipro.DBConnect.*;
import com.wipro.bean.*;

public class Vdao 
{
	public int insertVoter( VoterBean vb )
	{
		int crap1 = 0;
		
		try
		{
			Connection conn = DBConnect.connect();
			String query = " INSERT INTO crap1 (name, num, email, dob, key, cardreq) VALUES (?, ?, ?, ?, ?, 'false') ";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			preparedStatement.setString(1, vb.getName());
			preparedStatement.setString(2, vb.getNumber() );
			preparedStatement.setString(3, vb.getEmail() );
			preparedStatement.setString(4, vb.getDob() );
			preparedStatement.setString(5, vb.getKey() );
			//preparedStatement.setString(6, vb.isCardRequest() );
			
			crap1 = preparedStatement.executeUpdate();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap1;
	}
	
	public VoterBean findVoter( String mail )
	{
		VoterBean voter = null;
		
		try 
		{             
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM Crap1 WHERE email = ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, mail);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() )
			{
				voter = new VoterBean();
				voter.setName(resultSet.getString("name"));
				voter.setNumber(resultSet.getString("num"));
				voter.setCardRequest(resultSet.getString("cardreq"));
				voter.setDob(resultSet.getString("dob"));
				voter.setDistrict(resultSet.getString("district"));
				voter.setPan( resultSet.getString("pan") );
				voter.setEmail(mail);
				voter.setVoterID( resultSet.getString("voterid") );
			}
			
			resultSet.close();
			
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return voter;
	}
	
	public int checkUser( VoterBean vb )
	{
		int crap = 0;
		
		try
		{
			Connection connection = DBConnect.connect();
			
			String query = "SELECT * FROM Crap1 WHERE email = ? AND key = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			
			preparedStatement.setString(1, vb.getEmail().toLowerCase() );
			preparedStatement.setString(2, vb.getKey());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			// The object of ResultSet maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
			System.out.println( resultSet );
			if( resultSet.next() )
			{
			String n = resultSet.getString("email");
			String k = resultSet.getString("key");
			 
			
			System.out.println( n+" "+k );
			if( n.equalsIgnoreCase( vb.getEmail() ) && k.equals( vb.getKey() )  ) 
				crap++;
			}
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap;
	}
	
	public int updateProfile( VoterBean vb)
	{
		int crap1 = 0;
		try
		{
			Connection conn = DBConnect.connect();
			String query = "SELECT pan FROM crap1 WHERE email = ? ";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, vb.getEmail());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() )
			{
				if( ! resultSet.getString("pan").equals("NOT SPECIFIED") )
				{
					System.out.println("Profile Already Updated ");
					crap1 = -10;
				}
				
				else
				{
					query = " UPDATE crap1 SET district = ?, pan =  ? WHERE email = ? ";
					
					preparedStatement = conn.prepareStatement(query);
					// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
					// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
					preparedStatement.setString(1, vb.getDistrict());
					preparedStatement.setString(2, vb.getPan() );
					preparedStatement.setString(3, vb.getEmail() );
					//preparedStatement.setString(6, vb.isCardRequest() );
					
					crap1 = preparedStatement.executeUpdate();
				}
			}
			
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}		
		return crap1;
	}
	
	public int applyforVID( VoterBean vb)
	{
		int crap1 = 0;
		try
		{
			Connection conn = DBConnect.connect();
			
			String checkQuery = " SELECT * FROM crap1 WHERE email = ? ";
			PreparedStatement preparedStatement = conn.prepareStatement(checkQuery);
			preparedStatement.setString(1, vb.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() )
			{
				String cond = resultSet.getString("pan");
				if( cond.equalsIgnoreCase("NOT SPECIFIED") )
				{
					System.out.println("Update your Profile first before Appplying for VID");
					crap1 -= 1;
				}
				
				else 
				{
					String resp = resultSet.getString("cardreq");
					if( resp.equalsIgnoreCase("False" ) )
					{
						String query = " UPDATE crap1 SET cardreq = ? WHERE email = ? ";
						PreparedStatement pS = conn.prepareStatement(query);
						pS.setString(1, "Pending");
						pS.setString(2, vb.getEmail() );
						crap1 = pS.executeUpdate();
					}
					
					else if( resp.equalsIgnoreCase("Pending" ) )
					{
						System.out.println("Already Applied for Voter ID");
						return 77;
					}
					
					else if( resp.equalsIgnoreCase("Approved" ) )
					{
						System.out.println(" Your VID request is already approved ");
						return 99;
					}
				}
			}
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}		
		return crap1;
	}
	
	public ArrayList viewContestingParty( String district )
	{
		ArrayList li = new ArrayList();
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM elect WHERE dist = ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, district);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(" dist = "+district);
			while( resultSet.next() )
			{
				ElectionBean elect = new ElectionBean();
				elect.setPname(resultSet.getString("pname"));
				elect.setCname(resultSet.getString("cname"));
				elect.setEid(resultSet.getString("eid"));
				elect.setDist(resultSet.getString("dist"));
				li.add( elect.getPname() );
				li.add( elect.getCname() );
				li.add( elect.getDist() );	
				li.add( elect.getEid() );
				li.add( elect.getPname() );
				System.out.println(elect.getPname());
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return li;
	}
	
	public int checkID( String email, String type )
	{
		String id ="";
		try
		{
			Connection connection = DBConnect.connect();
			
			String query = "SELECT * FROM Crap1 WHERE email = ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			
			preparedStatement.setString(1, email.toLowerCase() );
			
			ResultSet resultSet = preparedStatement.executeQuery();
			// The object of ResultSet maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
			
			if( resultSet.next() )
				 id = resultSet.getString(type).trim();
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		System.out.println(" id -> "+id);
		if( id.equals("NOT ALLOCATED") )
		{
			System.out.println(" NOT ALLOCATED and return false");
			return 1;
		}
		else if( !id.equals("NOT VOTED") )
		{
			System.out.println(" NOT VOTED and return truue");
			return 2;
		}
		else 
			return 0;
	}
	
	public int castVote( String mail, String party )
	{
		int res = 0;
		try 
		{             
			Connection conn = DBConnect.connect();
			String query = " SELECT * FROM crap1 WHERE email = ? ";
			String votedfor = "";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, mail);
			//System.out.println(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() )
			{
				votedfor =  resultSet.getString("votedfor");
		
				System.out.println(" votedforX = "+votedfor);
				VoterBean vb = new VoterBean();
				
				if( votedfor.equalsIgnoreCase("NOT VOTED") )
				{
					vb.setVotedFor(party);
					System.out.println(" getVotedFor() : "+vb.getVotedFor() );
					String query1 = " UPDATE crap1 SET votedfor = ? WHERE email = ? ";
					System.out.println(query1);
					PreparedStatement ps = conn.prepareStatement(query1);
					// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
					// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
					ps.setString(1, vb.getVotedFor() );
					ps.setString(2, mail );
					System.out.println( ps );
					res = ps.executeUpdate();
					System.out.println("castvote() : res "+res);
				}
				
				else
					System.out.println(" You already voted " );
			}
			
			resultSet.close();
			
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return res;
	}
	
	
	public void updateVote(String party)
	{
		try 
		{             
			Connection conn = DBConnect.connect();
			String query = " SELECT * FROM elect WHERE pname = ? ";
			int votes, res = 0;
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			ElectionBean eb = new ElectionBean();
			preparedStatement.setString(1, party);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() )
			{
				votes =  resultSet.getInt("votes");
				votes++;
				System.out.println(" Current votes for "+party+" is "+votes);
				eb.setVotes(votes);
				
				String query1 = " UPDATE elect SET votes = ? WHERE pname = ? ";
				preparedStatement = conn.prepareStatement(query1);
				// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
				// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
				preparedStatement.setInt(1, eb.getVotes() );
				preparedStatement.setString(2, party );
				
				res = preparedStatement.executeUpdate();
				System.out.println( " updateVotes() : res = "+res);
			}
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	}
	
}
