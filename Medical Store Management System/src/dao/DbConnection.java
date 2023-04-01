package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	static Connection con=null;
	public static Connection connection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/prakashdb";
			String user = "root";
			String password = "Manoj@29";
			con = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
