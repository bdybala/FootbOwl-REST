package bdyb.rest.coach;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class CoachDao extends DataAccessObject {
	
	public List<Coach> getAllCoaches() {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, licence, team_id "
				+ "FROM coaches LEFT JOIN accounts "
				+ "ON accounts.acc_id = coaches.acc_id";
		List<Coach> coachList = new ArrayList<Coach>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			coachList = CoachParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		
		return coachList;
	}
	
	int createCoach(Coach c) {
		String sql = CoachParser.createInsertQuery(c);

		int rows = 0;
		try {

			connectToDatabase();
			createPreparedStatement(sql);
			rows = pStmt.executeUpdate();
			System.out.println(rows);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return rows;
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

	String updateCoach(String userid, String imie, String nazwisko, String login, String haslo, String photo,
			String licence, Integer teamiD, Integer islogged) {
		String sql = CoachParser.createUpdateQuery(userid, imie, nazwisko, login, haslo, photo, licence, teamiD, islogged);

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
	
}
