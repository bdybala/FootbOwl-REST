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
			System.out.println(rows);
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

}
