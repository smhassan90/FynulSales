import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class TestDB {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try
		{
		String url = "jdbc:oracle:thin:@//192.168.0.44:1521/orcl";
		String user = "SMPSYS";
		String password = "smpsysgsit2007";

		// Load the Connector/J driver
		Class.forName("oracle.jdbc.OracleDriver").newInstance();
		// Establish connection to MySQL
		Connection conn = DriverManager.getConnection(url, user, password);
		int i =0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
