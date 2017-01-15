package bdyb.rest.stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class StatsDao extends DataAccessObject{

	List<Stats> getStats() {
		final String sql = "SELECT * FROM stats";
		List<Stats> statsList = new ArrayList<Stats>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			statsList = StatsParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		
		return statsList;
	}

	void createStats(Stats s) {
		String sql = StatsParser.createInsertQuery(s);

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
	}

	String updateStats(int stats_id, String season, Integer matches, Integer wins, Integer draws, Integer loses,
			Integer yellow_cards, Integer red_cards, Integer goals, Integer assists, Integer mins_played,
			Integer clean_sheets, Integer shots) {
		String sql = StatsParser.createUpdateQuery(stats_id, season, matches, wins, draws, loses,
				yellow_cards, red_cards, goals, assists, mins_played, clean_sheets, shots);
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
