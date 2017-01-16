package bdyb.rest.league;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class LeagueDao extends DataAccessObject {

	List<League> getAllLeagues() {
		final String sql = "SELECT * FROM leagues";
		List<League> leagueList = new ArrayList<League>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			leagueList = LeagueParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
			
		return leagueList;
	}

	League getLeague(int leagueid) {
		final String sql = "SELECT * FROM leagues WHERE league_id = " + leagueid;
		League l = null;
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			l= LeagueParser.parseLeagueFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
			
		return l;
	}
	
	int createLeague(League l) {
		String sql = LeagueParser.createInsertQuery(l);
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


	String updateLeague(int leagueid, String full_name, String short_name, String age_group, int birth_year) {
		String sql = LeagueParser.createUpdateQuery(leagueid, full_name, short_name, age_group, birth_year);

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
