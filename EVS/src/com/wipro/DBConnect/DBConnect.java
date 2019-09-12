package com.wipro.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect 
{
	public static Connection connect() 
	{
		Connection conn = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// The forName() method of Java Class class returns the Class object associated with the class 
			// or interface with the given name in the parameter as String.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		}
		catch( Exception e )
		{
			System.out.println(" !! Error -> "+e+" !! ");
		}
		
		return conn;
	}

}
