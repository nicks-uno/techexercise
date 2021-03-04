package techexercise;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

public class DBConnection {
	static Properties properties = new Properties();
	static Connection connection = null;
	
	static void createConnection(ServletContext context) throws IOException, SQLException
	{
		String path = "/WEB-INF/lib/config.properties";
		InputStream in = context.getResourceAsStream(path);
		properties.load(in);
		
		String connectionURL = properties.getProperty("url").trim();
		String username = properties.getProperty("user").trim();
		String password = properties.getProperty("pass").trim();
		connection = DriverManager.getConnection(connectionURL, username, password);
	}

}
