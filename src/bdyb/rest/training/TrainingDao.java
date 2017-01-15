package bdyb.rest.training;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class TrainingDao extends DataAccessObject {

	List<Training> getAllTrainings() {
		final String sql = "SELECT training_id, training_desc,"
				+ "TO_CHAR(training_date, 'yyyy-mm-dd hh24:mi:ss') \"training_date\", "
				+ "training_place "
				+ "FROM trainings";
		List<Training> trainingList = new ArrayList<Training>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			trainingList = TrainingParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return trainingList;
	}

	int createTraining(Training t) {
		String sql = TrainingParser.createInsertQuery(t);

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

	String updateTraining(int training_id, String training_desc, String training_date, String training_place) {

		String sql = TrainingParser.createUpdateQuery(
				training_id, training_desc, training_date, training_place);
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

	List<Training> getAllTrainings(String from, String to) {
		final String sql = "SELECT training_id, training_desc,"
				+ "TO_CHAR(training_date, 'yyyy-mm-dd hh24:mi:ss') \"training_date\", "
				+ "training_place "
				+ "FROM trainings "
				+ "WHERE training_date > TO_DATE('" + from + "', 'yyyy-mm-dd') AND "
				+ "training_date < TO_DATE('" + to + "', 'yyyy-mm-dd')";
		List<Training> trainingList = new ArrayList<Training>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			trainingList = TrainingParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return trainingList;
	}

	int assignPlayer(int playerid, int trainingid) {
		String sql = TrainingParser.createAssignQuery(playerid, trainingid);

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

	List<Training> getPlayerTrainings(int userid, String from, String to) {
		final String sql = "SELECT trainings.training_id, training_desc,"
				+ "TO_CHAR(training_date, 'yyyy-mm-dd hh24:mi:ss') \"training_date\", "
				+ "training_place "
				+ "FROM trainings JOIN players_trainings "
				+ "ON trainings.training_id = players_trainings.training_id "
				+ "WHERE play_acc_id = " + userid + " AND "
				+ "training_date > TO_DATE('" + from + "', 'yyyy-mm-dd') AND "
				+ "training_date < TO_DATE('" + to + "', 'yyyy-mm-dd')";
		List<Training> trainingList = new ArrayList<Training>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			trainingList = TrainingParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return trainingList;
	}

	int assignTeam(int teamid, int trainingid) {
		String sql = TrainingParser.createAssignTeamQuery(teamid, trainingid);

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
}

