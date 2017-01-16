package bdyb.rest.account;

import java.sql.ResultSet;
import java.sql.SQLException;

import bdyb.rest.dao.DataAccessObject;

public class AccountDao extends DataAccessObject {

	String login(String login, String pass) {
		String sql = String.format("SELECT log_in('%s', '%s') AS login FROM dual",
				login, pass);
		ResultSet rs = null;
		String result = "failed";
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			result = AccountParser.parseLoginFromResultset(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return result;
	}

	int isLogged(int userid) {
		String sql = "SELECT is_logged FROM accounts WHERE acc_id = " + userid;
		ResultSet rs = null;
		int result = 0;
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			result = AccountParser.parseLoggedFromResultset(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return result;
	}
}
