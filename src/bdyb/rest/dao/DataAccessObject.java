package bdyb.rest.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

public class DataAccessObject {

	private static final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String username = "dybix";
	private static final String password = "123456";
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pStmt;
	
	public DataAccessObject() {
		registerDriver();
	}
	
	protected void registerDriver() {
		try {
			Class.forName(driverName).newInstance();
		} catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
			System.exit(1);
		} catch (InstantiationException e) {
			System.out.println("Error: unable to instantiate driver!");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Error: access problem while loading!");
			e.printStackTrace();
		}
	}
	
	protected void connectToDatabase() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error: unable to connect with SQL database!");
			e.printStackTrace();
		}
	}
	protected void disconnectFromDatabase() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error: unable to close connection");
			e.printStackTrace();
		}
	}
	
	protected void createPreparedStatement(String sql) {
		try {
			pStmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Error: unable to create prepared statement");
			e.printStackTrace();
		}
	}
	protected void closePreparedStatement() {
		try {
			if(!pStmt.isClosed()) {
				pStmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: unable to close prepared statement");
			e.printStackTrace();
		}
	}
	
	protected void createStatement() {
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error: unable to create statement");
			e.printStackTrace();
		}
	}
	protected void closeStatement() {
		try {
			if(!stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: unable to close statement");
			e.printStackTrace();
		}
	}
	
	protected InputStream createFileFromUri(String photo) throws FileNotFoundException, URISyntaxException {
		InputStream in = null;
		File file = null;
		String tDir = System.getProperty("java.io.tmpdir"); String path = tDir + "tmp" + ".jpg";
		URI uri = new URI(photo);

		if(uri.getScheme().contains("file")) 
			file = new File(uri.getRawPath());
		else if (uri.getScheme().contains("http")) {
			try {
				file = new File(path);
				file.createNewFile();
				file.deleteOnExit();
				FileUtils.copyURLToFile(uri.toURL(), file);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file != null)
			in = new FileInputStream(file);
		return in;
	}
	
}
