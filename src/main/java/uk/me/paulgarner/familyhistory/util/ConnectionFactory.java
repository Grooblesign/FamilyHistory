package uk.me.paulgarner.familyhistory.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	static Logger logger = Logger.getLogger("uk.me.paulgarner");	

	public static Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:jboss/postgresDS");
			conn = dataSource.getConnection();
		} catch (Exception exception) {
			logger.info(exception.getMessage());
		}
		
		return conn;
		
//		return DriverManager.getConnection(
//				"jdbc:postgresql://localhost:5432/familyhistory",
//				"postgres", "Quizzle1792");
	}
		
	public static void closeConnection(Connection connection) {
		if (connection == null) return;
		try {
			connection.close();
		} catch (SQLException exception) {
			logger.info(exception.getMessage());
		}
	}

}
