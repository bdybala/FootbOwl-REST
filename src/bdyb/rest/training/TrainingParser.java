package bdyb.rest.training;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TrainingParser {

	private static final String DB_ID =		"training_id";
	private static final String DB_DESC =	"training_desc";
	private static final String DB_DATE =	"training_date";
	private static final String DB_PLACE =	"training_place";
	
	static List<Training> parseListFromResultSet(ResultSet rs) {
		List<Training> trainingList = new ArrayList<Training>();
		Training t;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				t = new Training();
				
				t.setTraining_id(		rs.getInt(		DB_ID));
				t.setTraining_desc(		rs.getString(	DB_DESC));
				t.setTraining_date(		new Date(dbDateFormat.parse(rs.getString(DB_DATE)).getTime()));
				t.setTraining_place(	rs.getString(	DB_PLACE));
				
				trainingList.add(t);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return trainingList;
	}

	static String createInsertQuery(Training t) {
		String query = String.format("CALL insert_training ("
				+ "'%s', TO_DATE('%s','yyyy-mm-dd hh24:mi:ss'), '%s')", 
				t.getTraining_desc(), t.getTraining_date(), t.getTraining_place());
		return query;
	}

	static String createUpdateQuery(int training_id, String training_desc, String training_date,
			String training_place) {
		
		String query = "UPDATE trainings SET ";
		if(training_desc != null) query += 	"training_desc = '" + training_desc + "', ";
		if(training_date != null) query +=	"training_date = " + "TO_DATE('" + training_date + "', 'yyyy-mm-dd hh24:mi:ss'), ";
		if(training_place != null) query +=	"training_place = '" + training_place + "', ";
		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE training_id = %s", training_id);
		return query;
	}

	static String createAssignQuery(int playerid, int trainingid) {
		String query = String.format("INSERT INTO players_trainings VALUES (%s, %s)", 
				playerid, trainingid);
		System.out.println(query);
		return query;
	}

}
