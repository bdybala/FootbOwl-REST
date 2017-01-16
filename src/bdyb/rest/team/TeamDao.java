package bdyb.rest.team;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class TeamDao extends DataAccessObject {

	public List<Team> getAllTeams() {
		final String sql = "SELECT * FROM teams";
		List<Team> teamList = new ArrayList<Team>();
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			teamList = TeamParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
			
		return teamList;
	}
	
	int createTeam(Team t) {
		String sql = TeamParser.createInsertQuery(t);
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

	String updateTeam(int teamid, Integer stats, Integer league, String fullname, String abbname, String country,
			String county, String town, String photo) {

		String sql = TeamParser.createUpdateQuery(teamid, stats, league, fullname, abbname, country, county, town, photo);
		
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

	Team getPlayerTeam(int playerid) {
		final String sql = "SELECT teams.* FROM teams JOIN players "
				+ "ON players.team_id = teams.team_id WHERE acc_id = " + playerid;
		Team t = null;
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			t = TeamParser.parseTeamFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return t;
	}

	Team getManagerTeam(int managerid) {
		final String sql = "SELECT * FROM teams "
				+ "WHERE man_acc_id = " + managerid;
		Team t = null;
		ResultSet rs = null;
		
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			t = TeamParser.parseTeamFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return t;
	}
}
