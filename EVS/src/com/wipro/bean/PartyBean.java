package com.wipro.bean;

public class PartyBean 
{
	private String name;
	private String cname;
	private String crole;
	private String cid;
	
	public PartyBean()
	{
	}
	public PartyBean( String name, String cname, String crole, String cid )
	{
		this.name = name;
		this.cname = cname;
		this.crole = crole;
		this.cid = cid;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getCname() 
	{
		return cname;
	}
	public void setCname(String cname) 
	{
		this.cname = cname;
	}
	public String getCrole()
	{
		return crole;
	}
	public void setCrole(String crole) 
	{
		this.crole = crole;
	}
	public String getCid()
	{
		return cid;
	}
	public void setCid( String cid )
	{
		this.cid = cid;
	}
}
