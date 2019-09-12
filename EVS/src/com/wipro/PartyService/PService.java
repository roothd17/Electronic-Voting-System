package com.wipro.PartyService;

import java.util.ArrayList;

import com.wipro.PartyDAO.Pdao;
import com.wipro.bean.ElectionBean;
import com.wipro.bean.PartyBean;

public class PService
{
	public String insertParty( PartyBean p  )
	{
		String res = "";
		
		Pdao pd = new Pdao();
		
		int status = pd.insertParty(p);
		
		if( status == 1 )
			res = " --- Successfully inserted a row --- ";
		else
			res = " --- Failed to insert data --- ";
		
		return res;
		
	}
	
	public ArrayList viewPartyDetails( String name )
	{
		Pdao pd = new Pdao();
		
		ArrayList li = pd.viewPartyDetails(name);
		
		return li;
	}
	
	public String insertElection( ElectionBean e  )
	{
		String res = "";
		
		Pdao pd = new Pdao();
		System.out.println(e.getCname());
		int status = pd.insertElection(e);
		
		if( status == 1 )
			res = " --- Successfully inserted a row --- ";
		else
			res = " --- Failed to insert data --- ";
		
		return res;
		
	}
	
	public ArrayList viewElectionDetails( String eid )
	{
		Pdao pd = new Pdao();
		
		ArrayList li = pd.viewElectionDetails(eid);
		
		return li;
	}
	
	public ArrayList viewUpcomingElection( )
	{
		Pdao pd = new Pdao();
		
		ArrayList li = pd.viewUpcomingElection();
		
		return li;
	}
	
	public ArrayList approveResults( String eid )
	{
		Pdao pd = new Pdao();
		
		ArrayList li = pd.approveResults(eid);
		
		return li;
	}
}
