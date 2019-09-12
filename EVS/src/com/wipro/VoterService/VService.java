package com.wipro.VoterService;

import com.wipro.bean.*;

import java.util.ArrayList;

import com.wipro.PartyDAO.Pdao;
import com.wipro.VoterDAO.*;

public class VService 
{
	public String insertVoter( VoterBean vb  )
	{
		String res = "";
		
		Vdao vd = new Vdao();
		
		int status = vd.insertVoter(vb);
		
		if( status == 1 )
			res = " --- Successfully inserted a row --- ";
		else
			res = " --- Failed to insert data --- ";
		
		return res;
	}
	
	public VoterBean viewVoter( String mail )
	{
		Vdao vd = new Vdao();
		VoterBean voter = vd.findVoter(mail);
		return voter;
	}
	
	public String castVote( String mail, String party )
	{
		String res = "";
		Vdao vd = new Vdao();
		int status = vd.castVote( mail, party );
		 
		 if( status == 1 )
		 {
				res = " --- Successfully casted your vote --- ";
				vd.updateVote(party);
		 }
		 else
				res = " --- Failed to cast your vote --- ";
		 return res;
	}
	
	public String updateProfile( VoterBean vb  )
	{
		String res = "";
		
		Vdao vd = new Vdao();
		
		int status = vd.updateProfile(vb);
		
		if( status == 1 )
			res = " --- Successfully inserted a row --- ";
		else if( status == -10 )
			res = " You already updated your profile ! ";
		else 
			res = " Some technical error occured ! ";
		return res;
	}
	
	public int checkID( String email, String type )
	{
		Vdao vd = new Vdao();
		
		int b = vd.checkID(email, type);
		
		return b;
	}
	
	public ArrayList viewContestingParty( String district )
	{
		Vdao vd = new Vdao();
		
		ArrayList li = vd.viewContestingParty(district);
		
		return li;
	}
	public String applyforVID( VoterBean vb  )
	{
		String res = "";
		
		Vdao vd = new Vdao();
		
		int status = vd.applyforVID(vb);
		
		if( status == 1 )
			res = " --- Successfully inserted a row --- ";
		else if (status == -1 )
			res = "Update your Profile first before Appplying for VID";
		else if (status == 77 )
			res = "Already Applied for Voter ID";
		else if (status == 99 )
			res = "Your VID request is already approved";
		else
			res = " --- Failed to insert data --- ";
		
		return res;
	}
}
