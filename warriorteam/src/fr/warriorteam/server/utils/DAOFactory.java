package fr.warriorteam.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DAOFactory {

	private static final Logger logger = Logger.getLogger(DAOFactory.class);

	private DAOFactory() {

	}

	public static Connection getConnection() {
		Connection connection = null;

		StringBuilder msg = new StringBuilder();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			msg.append("DRIVER JDBC MySQL OK ! ");

			// String url = "jdbc:mysql://localhost/warriorteam";
			// String user = "root";
			// String passwd = "";

			String url = PropertiesUtils.getProperties("url-db");
			String user = PropertiesUtils.getProperties("user-db");
			String passwd = PropertiesUtils.getProperties("pwd-db");

			connection = DriverManager.getConnection(url, user, passwd);

			msg.append("Connection effective !");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (connection == null) {
			logger.error("La connection SQL n'a pas pu être obtenue !");
			throw new IllegalArgumentException("Problème interne du serveur");
		}

		//logger.debug(msg.toString());

		return connection;
	}
}
