package bdyb.rest.coach;

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
	
}
