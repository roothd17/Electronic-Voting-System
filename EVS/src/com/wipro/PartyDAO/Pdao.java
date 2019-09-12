package com.wipro.PartyDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.wipro.DBConnect.DBConnect;
import com.wipro.bean.ElectionBean;
import com.wipro.bean.PartyBean;
import com.wipro.bean.VoterBean;

public class Pdao 
{
	public int insertParty( PartyBean pb )
	{
		int crap1 = 0;
		
		try
		{
			Connection conn = DBConnect.connect();
			String query = " INSERT INTO party VALUES ( ?, ?, ?, ? ) ";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			preparedStatement.setString(1, pb.getName());
			preparedStatement.setString(2, pb.getCname() );
			preparedStatement.setString(3, pb.getCid() );
			preparedStatement.setString(4, pb.getCrole() );
		
			crap1 = preparedStatement.executeUpdate();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap1;
	}
	
	public ArrayList viewPartyDetails( String name )
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM party WHERE name = ? ";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, name);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				PartyBean party = new PartyBean();
				party.setName(resultSet.getString("name"));
				party.setCname(resultSet.getString("cname"));
				party.setCrole(resultSet.getString("crole"));
				party.setCid(resultSet.getString("cid"));
				
				li.add( party.getName() );
				li.add( party.getCname() );
				li.add( party.getCrole() );
				li.add( party.getCid() );
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public int insertElection( ElectionBean eb )
	{
		int crap1 = 0;
		System.out.println(eb.getCid());
		try
		{
			Connection conn = DBConnect.connect();
			String query = " INSERT INTO elect ( pname, cname, eid, dist, datex, cid ) VALUES ( ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ? ) ";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			preparedStatement.setString(1, eb.getPname() );
			preparedStatement.setString(2, eb.getCname() );
			preparedStatement.setString(3, eb.getEid() );
			preparedStatement.setString(4, eb.getDist() );
			preparedStatement.setString(5, eb.getDate() );
			preparedStatement.setString(6, eb.getCid() );
			
			crap1 = preparedStatement.executeUpdate();
//				query = " commit ";
//				preparedStatement = conn.prepareStatement(query);
//				crap1 = preparedStatement.executeUpdate();
			System.out.println(" crap1 "+crap1);	
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return crap1;
	}
	
	public ArrayList viewElectionDetails( String eid )
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM elect WHERE eid = ? ";
			//String today = "" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			//String query = " SELECT * FROM electtemp WHERE eid = ? AND datex > TO_DATE('"+today+"', 'YYYY-MM-DD')";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, eid);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				ElectionBean eb = new ElectionBean();
				eb.setPname(resultSet.getString("pname"));
				eb.setCname(resultSet.getString("cname"));
				eb.setEid(resultSet.getString("eid"));
//				eb.setDate(resultSet.getString("dateX"));
//				System.out.println(resultSet.getString("dateX"));
				String dat = ""+resultSet.getString("dateX");
				eb.setDate(dat.substring(0,11));
				
				eb.setDist(resultSet.getString("dist"));
				
				li.add( eb.getEid() );
				li.add( eb.getPname() );
				li.add( eb.getCname() );
				li.add( eb.getDist() );
				li.add( eb.getDate() );

			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList viewUpcomingElection(  )
	{
		ArrayList li = new ArrayList();
		
		try 
		{		
			Connection connection = DBConnect.connect();
			//String query = " SELECT * FROM elect WHERE eid = ? ";
			String today = "" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String query = " SELECT * FROM elect WHERE datex > TO_DATE('"+today+"', 'YYYY-MM-DD')";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
		
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				ElectionBean eb = new ElectionBean();
				eb.setPname(resultSet.getString("pname"));
				eb.setCname(resultSet.getString("cname"));
				eb.setEid(resultSet.getString("eid"));
				//eb.setDate(resultSet.getString("dateX"));
				String dat = ""+resultSet.getString("dateX");
				eb.setDate(dat.substring(0,11));
				
				eb.setDist(resultSet.getString("dist"));
				
				li.add( eb.getEid() );
				li.add( eb.getPname() );
				li.add( eb.getCname() );
				li.add( eb.getDist() );
				li.add( eb.getDate() );

			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList approveResults( String eid )
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM elect WHERE eid = ? ORDER BY votes DESC";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, eid);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			while( resultSet.next() )
			{
				ElectionBean eb = new ElectionBean();
				eb.setPname(resultSet.getString("pname"));
				eb.setCname(resultSet.getString("cname"));
				eb.setEid(resultSet.getString("eid"));
				eb.setCid( resultSet.getString("cid"));
				//eb.setDate(resultSet.getString("dateX"));
				String dat = ""+resultSet.getString("dateX");
				eb.setDate(dat.substring(0,11));
				eb.setDist(resultSet.getString("dist"));
				eb.setVotes(resultSet.getInt("votes"));
				
				System.out.println("-->"+eb.getPname()+" "+eb.getVotes() );
				li.add( eb.getEid() );
				li.add( eb.getPname() );
				li.add( eb.getCname() );
				li.add( eb.getCid() );
				li.add( eb.getDist() );
				li.add( eb.getDate() );
				li.add( eb.getVotes() );
				li.add( eb.getEid() );
				li.add( eb.getCid() );
			}
			
			resultSet.close();
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
	
		return li;
	}
	
	public ArrayList viewResults( ElectionBean eb )
	{
		ArrayList li = new ArrayList();
		
		try 
		{
			Connection connection = DBConnect.connect();
			String query = " SELECT * FROM elect WHERE eid = ? AND result != ? ORDER BY votes DESC";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
			// As you can see, we are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
			
			preparedStatement.setString(1, eb.getEid());
			preparedStatement.setString(2, "UNDECLARED");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while( resultSet.next() )
			{
				eb.setPname(resultSet.getString("pname"));
				eb.setCname(resultSet.getString("cname"));
				//eb.setEid(resultSet.getString("eid"));
				eb.setCid( resultSet.getString("cid"));
				//eb.setDate(resultSet.getString("dateX"));
				String dat = ""+resultSet.getString("dateX");
				eb.setDate(dat.substring(0,11));
				eb.setDist(resultSet.getString("dist"));
				eb.setVotes(resultSet.getInt("votes"));
				eb.setResult(resultSet.getString("result"));
				//System.out.println("-->"+eb.getPname()+" "+eb.getVotes() );
				li.add( eb.getEid() );
				li.add( eb.getPname() );
				li.add( eb.getCname() );
				li.add( eb.getCid() );
				li.add( eb.getDist() );
				li.add( eb.getDate() );
				li.add( eb.getVotes() );
				li.add( eb.getResult() );

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
