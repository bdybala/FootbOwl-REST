package bdyb.rest.supporter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class SupporterDao extends DataAccessObject {

	public List<Supporter> getAllSupporters() {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, is_logged "
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

	String updateSupporter(String userid, String imie, String nazwisko, String login, String haslo, String photo, Integer islogged) {
		
		String sql = SupporterParser.createUpdateQuery(userid, imie, nazwisko, login, haslo, photo, islogged);
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			if(photo != null) {
				InputStream in = createFileFromUri(photo);
				pStmt.setBlob(1, in);
			}
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return sql;
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
