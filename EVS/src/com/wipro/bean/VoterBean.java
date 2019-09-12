package com.wipro.bean;

public class VoterBean 
{
	private String voterID;
	private String name;
	private String email;
	private String dob;
	private String number;
	private String cardRequest;
	private String key;
	private String votedFor;
	private String district;
	private String pan;

	public VoterBean()
	{
		
	}
	
	public VoterBean( String name, String email, String dob, String number, String cardRequest, String key )
	{
		 //this.voterID = voterID;
		 this.name = name;
		 this.email = email;
		 this.dob = dob;
		 this.number = number;
		 this.cardRequest =  cardRequest;
		 this.key = key;
		
	}
	public String getVoterID() 
	{
		return voterID;
	}
	public void setVoterID(String voterID) 
	{
		this.voterID = voterID;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getDob() 
	{
		return dob;
	}
	public void setDob(String dob) 
	{
		this.dob = dob;
	}
	public String getNumber() 
	{
		return number;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}
	public String isCardRequest() 
	{
		return cardRequest;
	}
	public void setCardRequest(String cardRequest)
	{
		this.cardRequest = cardRequest;
	}
	public String getKey() 
	{
		return key;
	}
	public void setKey(String key) 
	{
		this.key = key;
	}
	public void setVotedFor( String votedFor )
	{
		this.votedFor = votedFor;
	}
	public String getVotedFor()
	{
		return votedFor;
	}
	public String getDistrict( )
	{
		return district;
	}
	public void setDistrict( String district )
	{
		this.district = district;
	}
	public void setPan( String pan )
	{
		this.pan = pan;
	}
	public String getPan()
	{
		return pan;
	}
}



