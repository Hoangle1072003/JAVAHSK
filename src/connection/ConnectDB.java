package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	public static ConnectDB getinstance() {
		return instance;
	}
	
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLLinhKien";
		String user = "sa";
		String password = "123456789";
		con = DriverManager.getConnection(url,user,password);
	}
	public void disconnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		 return con;
	}
}
