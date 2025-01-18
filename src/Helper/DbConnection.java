package Helper;
import java.sql.*;
public class DbConnection
{
	
	Connection c=null;
	
	public DbConnection()
	{
		
	}
	
	public Connection connDb()
	{
		try {
			this.c=DriverManager.getConnection("jdbc:mariadb://localhost:3307/hospitaldb?user=root&password=2004");
			return c;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return c;
	}
}
