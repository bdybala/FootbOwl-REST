package bdyb.rest.match;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class MatchDao extends DataAccessObject {

	List<Match> getAllMatches() {
		final String sql = "SELECT match_id, league_id, "
				+ "home_id, away_id, goals_home, goals_away, "
				+ "TO_CHAR(match_date, 'YYYY-MM-DD HH24:MI:SS') \"match_date\" "
				+ "FROM matches";
		List<Match> matchList = new ArrayList<Match>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			matchList = MatchParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		
		return matchList;
	
	}

	int createMatch(Match m) {
		String sql = MatchParser.createInsertQuery(m);

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

	String updateMatch(int match_id, Integer league_id, Integer home_id, Integer away_id, Integer goals_home, Integer goals_away,
			String match_date) {
		String sql = MatchParser.createUpdateQuery(match_id, league_id, home_id, away_id, goals_home, goals_away, match_date);
	
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return sql;
	}

}
