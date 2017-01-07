package bdyb.rest.supporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bdyb.rest.dao.DataAccessObject;

public class SupporterDao extends DataAccessObject {

	public List<Supporter> getAllSupporters() {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass "
				+ "FROM supporters LEFT JOIN accounts "
				+ "ON accounts.acc_id = supporters.acc_id";
		List<Supporter> supporterList = new ArrayList<Supporter>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			supporterList = SupporterParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
			
		return supporterList;
	}
	
	int createSupporter(Supporter s) {
		String sql = SupporterParser.createInsertQuery(s);
		int rows = 0;
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rows = pStmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return rows;
	}

	String updateSupporter(String userid, String imie, String nazwisko, String haslo, String photo) {
		
		String sql = SupporterParser.createUpdateQuery(userid, imie, nazwisko, haslo, photo);
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			if(photo != null) {
				InputStream in = createFileFromUri(photo);
				pStmt.setBlob(1, in);
			}
			pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return sql;
	}

	private InputStream createFileFromUri(String photo) throws URISyntaxException, FileNotFoundException {
		InputStream in = null;
		File file = null;
		String tDir = System.getProperty("java.io.tmpdir"); String path = tDir + "tmp" + ".jpg";
		URI uri = new URI(photo);
		
		if(uri.getScheme().contains("file")) 
			file = new File(uri);
		 else if (uri.getScheme().contains("http")) {
			try {
				file = new File(path);
				file.createNewFile();
				file.deleteOnExit();
				FileUtils.copyURLToFile(uri.toURL(), file);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (file != null)
			in = new FileInputStream(file);
		return in;
	}

	int selectLogin(String login) {
		String sql = String.format("SELECT count(*) FROM accounts WHERE login = '%s'", login);
		int rows = 0;
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rows = pStmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return rows;
	}
	
}
