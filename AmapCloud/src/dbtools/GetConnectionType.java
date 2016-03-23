package dbtools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnectionType {

	public static Connection getConnectionFromJDBC()
			throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://172.16.1.101:3306/mapdata?useUnicode=true&characterEncoding=UTF-8";
		String driverName = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "123456";
		Class.forName(driverName);
		return DriverManager.getConnection(url, username, password);
	}

	public static Connection getConnectionFromJNDI() {

		return null;
	}
}
