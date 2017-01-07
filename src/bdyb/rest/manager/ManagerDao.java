package bdyb.rest.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
				+ "first_name, last_name, birthday, photo, login, pass "
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
		
		File blobFile = new File("E:/Obraz/Znak.jpg");
		InputStream in = null;		
		
		try {
			in = new FileInputStream(blobFile);
			connectToDatabase();
			createPreparedStatement(sql);
			if( in != null) {
				pStmt.setBlob(1, in);
				rows = pStmt.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO there's no blobFile
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return rows;
	}

}
