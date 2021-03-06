package bdyb.rest.manager;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class ManagerDao extends DataAccessObject {

	public List<Manager> getAllManagers() {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, is_logged "
				+ "FROM managers LEFT JOIN accounts "
				+ "ON accounts.acc_id = managers.acc_id";
		List<Manager> managerList = new ArrayList<Manager>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			managerList = ManagerParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return managerList;
	}

	int createManager(Manager manager) {
		String sql = ManagerParser.createInsertQuery(manager);
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

	int selectLogin(String login) {
		String sql = String.format("SELECT * FROM accounts WHERE login = '%s'", login);
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

	String updateManager(String userid, String imie, String nazwisko, String login, String haslo, String photo, Integer islogged) {
		String sql = ManagerParser.createUpdateQuery(userid, imie, nazwisko, login, haslo, photo, islogged);

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

	Manager getManager(int userid) {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, is_logged "
				+ "FROM managers LEFT JOIN accounts "
				+ "ON accounts.acc_id = managers.acc_id "
				+ "WHERE accounts.acc_id = " + userid;
		Manager m = null;
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			m = ManagerParser.parseManagerFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return m;
	}


}
