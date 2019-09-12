package com.wipro.bean;

public class ElectionBean
{
	private String pname;
	private String cname;
	private String dist;
	private String eid;
	private String cid;
	private String date;
	private int votes;
	private String result;
	public ElectionBean()
	{}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult( String result )
	{
		this.result = result;
	}
	public int getVotes()
	{
		return votes;
	}
	public void setVotes( int votes )
	{
		this.votes = votes;
	}
}
