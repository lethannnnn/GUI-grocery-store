package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.HttpServlet;

public class ConnectionProvider extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Connection connection;

	public static Connection getConnection() {

		try {
			if (connection == null) {
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				connection = DriverManager.getConnection("jdbc:derby://localhost:1527/GUIassignmentDB", "nbuser", "nbuser");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
