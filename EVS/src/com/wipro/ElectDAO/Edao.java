package com.wipro.ElectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.wipro.DBConnect.*;
import com.wipro.bean.*;

public class Edao
{
	
	public int checkUser( EOBean eb )
	{
		int crap = 0;
		
		try
		{
			Connection connection = DBConnect.connect();
			
			String query = "SELECT * FROM EO WHERE email = ? AND key = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			
			preparedStatement.setString(1, eb.getName() );
			preparedStatement.setString(2, eb.getKey() );
			
			ResultSet resultSet = preparedStatement.executeQuery();
			// The object of ResultSet maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
			System.out.println( resultSet );
			resultSet.next();
			String n = resultSet.getString("email");
			String k = resultSet.getString("key");
			 
			
			System.out.println( n+" "+k );
			if( n.equalsIgnoreCase( eb.getName() ) && k.equals( eb.getKey() )  ) 
				crap++;
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap;
	}
	
	public ArrayList viewPendingID()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM Crap1 WHERE cardreq = ? ORDER BY name ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, "false");
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				VoterBean voter = new VoterBean();
				voter.setName(resultSet.getString("name"));
				voter.setNumber(resultSet.getString("num"));
				voter.setCardRequest(resultSet.getString("cardreq") );
				voter.setDob(resultSet.getString("dob"));
				voter.setEmail(resultSet.getString("email"));
				voter.setDistrict(resultSet.getString("district"));
				voter.setPan(resultSet.getString("pan"));
				
				li.add( voter.getName() );
				li.add( voter.getNumber() );
				li.add( voter.getDob() );
				li.add( voter.getEmail() );
				li.add( voter.getDistrict() );
				li.add( voter.getPan()  );
				li.add( voter.isCardRequest() );
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList PendingID()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM Crap1 WHERE cardreq = ? ORDER BY name ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, "Pending");
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				VoterBean voter = new VoterBean();
				voter.setName(resultSet.getString("name"));
				voter.setNumber(resultSet.getString("num"));
				voter.setCardRequest(resultSet.getString("cardreq"));
				voter.setDob(resultSet.getString("dob"));
				voter.setEmail(resultSet.getString("email"));
				voter.setDistrict(resultSet.getString("district"));
				voter.setPan(resultSet.getString("pan"));
				
				li.add( voter.getName() );
				li.add( voter.getNumber() );
				li.add( voter.getDob() );
				li.add( voter.getEmail() );
				li.add( voter.getDistrict() );
				li.add( voter.getPan()  );
				li.add( voter.isCardRequest() );
				li.add( voter.getEmail() );
				
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	public ArrayList viewVoterList()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM Crap1 ORDER BY name ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				VoterBean voter = new VoterBean();
				voter.setName(resultSet.getString("name"));
				voter.setNumber(resultSet.getString("num"));
				voter.setCardRequest(resultSet.getString("cardreq"));
				voter.setDob(resultSet.getString("dob"));
				voter.setEmail(resultSet.getString("email"));
				voter.setDistrict(resultSet.getString("district"));
				voter.setPan(resultSet.getString("pan"));
				voter.setVoterID( resultSet.getString("voterid"));
				
				li.add( voter.getName() );
				li.add( voter.getNumber() );
				li.add( voter.getDob() );
				li.add( voter.getEmail() );
				li.add( voter.getDistrict() );
				li.add( voter.getPan()  );
				li.add( voter.isCardRequest() );
				li.add( voter.getVoterID() );
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public String approveID( VoterBean vb)
	{
		int crap1 = 0;
		String temp = "";
		try
		{
			Connection conn = DBConnect.connect();
			
				String vid = vb.getPan() + vb.getNumber().substring(0,5);
				System.out.println(" About to be allocated "+vid);
				System.out.println(" UPDATE crap1 SET voterid = "+vid+" , cardreq = Approved WHERE email =  "+vb.getEmail() );
				
				String query = " UPDATE crap1 SET voterid = ?, cardreq = ? WHERE email = ? ";
				
				PreparedStatement pS = conn.prepareStatement(query);
				pS.setString(1, vid);
				pS.setString(2, "Approved");
				pS.setString(3, vb.getEmail() );
				crap1 = pS.executeUpdate();
				System.out.println(crap1);
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}		
		if( crap1 != 0 )
			temp = " --- Successfully inserted a row --- ";
		System.out.println(crap1+" "+temp);
		
		return temp;
	}
	
	public String declareRes( ElectionBean elect )
	{
		int crap1 = 0;
		String temp = "";
		try
		{
			Connection conn = DBConnect.connect();
			
			System.out.println(" UPDATE elect SET result = Win  WHERE cid =  "+elect.getCid()+" and eid = "+elect.getEid() );
			
			String query = " UPDATE elect SET result = ? WHERE cid = ? AND eid = ? ";
			
			PreparedStatement pS = conn.prepareStatement(query);
			pS.setString(1, "Win");
			pS.setString(2, elect.getCid() );
			pS.setString(3, elect.getEid() );
			crap1 = pS.executeUpdate();
			System.out.println(crap1);
			
			System.out.println(" UPDATE elect SET result = Lose  WHERE cid !=  "+elect.getCid() );
			
			query = " UPDATE elect SET result = ? WHERE cid != ? AND eid = ?";
			
			pS = conn.prepareStatement(query);
			pS.setString(1, "Lose");
			pS.setString(2, elect.getCid() );
			pS.setString(3, elect.getEid() );

			crap1 = pS.executeUpdate();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}		
		if( crap1 != 0 )
			temp = " --- Successfully inserted a row --- ";
		System.out.println(crap1+" "+temp);
		
		return temp;
	}
	
	public ArrayList dropdownVal()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT DISTINCT name FROM party ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				PartyBean party = new PartyBean();
				party.setName(resultSet.getString("name"));		
				//System.out.print(party.getName());
				li.add( party.getName() );
				li.add( party.getName() );
			}
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList dropdownElect()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT DISTINCT eid FROM elect ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				ElectionBean elect = new ElectionBean();
				elect.setEid(resultSet.getString("eid"));		
				System.out.print(elect.getEid());
				li.add( elect.getEid() );
				li.add( elect.getEid() );
			}
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList dropdownApprove()
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT DISTINCT eid FROM elect WHERE result = 'UNDECLARED' ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				ElectionBean elect = new ElectionBean();
				elect.setEid(resultSet.getString("eid"));		
				System.out.print(elect.getEid());
				li.add( elect.getEid() );
				li.add( elect.getEid() );
			}
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}

}
