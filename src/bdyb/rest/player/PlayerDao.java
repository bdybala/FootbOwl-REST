package bdyb.rest.player;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class PlayerDao extends DataAccessObject {
	
	public List<Player> getAllPlayers() {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, team_id, position, pref_foot, is_logged "
				+ "FROM players LEFT JOIN accounts "
				+ "ON accounts.acc_id = players.acc_id";
		List<Player> playerList = new ArrayList<Player>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			playerList = PlayerParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return playerList;
	}
	
	int createPlayer(Player p) {
		String sql = PlayerParser.createInsertQuery(p);
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

	public String updatePlayer(String userid, String imie, String nazwisko, String photo, String login, String haslo,
			Integer teamiD, String position, String foot, Integer islogged) {
		
		String sql = PlayerParser.createUpdateQuery(userid, imie, nazwisko, photo, login, haslo, teamiD, position, foot, islogged);

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

	Player getPlayer(int userid) {
		final String sql = "SELECT accounts.acc_id, acc_type, "
				+ "TO_CHAR(reg_date, 'YYYY-MM-DD HH24:MI:SS') \"reg_date\", "
				+ "TO_CHAR(last_logged, 'YYYY-MM-DD HH24:MI:SS') \"last_logged\", "
				+ "first_name, last_name, birthday, photo, login, pass, team_id, position, pref_foot, is_logged "
				+ "FROM players LEFT JOIN accounts "
				+ "ON accounts.acc_id = players.acc_id "
				+ "WHERE accounts.acc_id = " + userid;
		Player p = null;
		ResultSet rs = null;
//		System.out.println(sql);
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			p = PlayerParser.parsePlayerFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return p;
	}
	
	
}
