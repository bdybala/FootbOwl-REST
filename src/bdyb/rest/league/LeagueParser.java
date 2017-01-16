package bdyb.rest.league;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LeagueParser {

	private static final String DB_ID = 			"league_id";
	private static final String DB_FULL_NAME =		"full_name";
	private static final String DB_SHORT_NAME =		"short_name";
	private static final String DB_AGE_GROUP = 		"age_group"; 
	private static final String DB_BIRTH_YEAR = 	"birth_year";

	static List<League> parseListFromResultSet(ResultSet rs) {
		List<League> leaguesList = new ArrayList<League>();
		League l;

		try {
			while(rs.next()) {
				l = new League();

				l.setLeague_id(		rs.getInt(		DB_ID));
				l.setFull_name(		rs.getString(	DB_FULL_NAME));
				l.setShort_name(	rs.getString(	DB_SHORT_NAME));
				l.setAge_group(		rs.getString(	DB_AGE_GROUP));
				l.setBirth_year(	rs.getInt(		DB_BIRTH_YEAR));
				
				leaguesList.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leaguesList;
	}

	static String createInsertQuery(League l) {
		String query = String.format("CALL insert_league ('%s','%s','%s','%s')", 
				l.getFull_name(), l.getShort_name(), l.getAge_group(), l.getBirth_year());
		return query;
	}

	static String createUpdateQuery(int leagueid, String full_name, String short_name, String age_group,
			Integer birth_year) {
		String query = String.format("UPDATE leagues SET ");
		if(full_name != null) 
			query += String.format("full_name = '%s', ", full_name);
		if(short_name != null) 
			query += String.format("short_name = '%s', ", short_name);
		if(age_group != null)
			query += String.format("age_group = '%s', ", age_group);
		if(birth_year != null)
			query += String.format("birth_year = '%s', ", birth_year);

		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE league_id = %s", leagueid);
		return query;
	}

	static League parseLeagueFromResultSet(ResultSet rs) {
		League l = null;

		try {
			if(rs.next()) {
				l = new League();

				l.setLeague_id(		rs.getInt(		DB_ID));
				l.setFull_name(		rs.getString(	DB_FULL_NAME));
				l.setShort_name(	rs.getString(	DB_SHORT_NAME));
				l.setAge_group(		rs.getString(	DB_AGE_GROUP));
				l.setBirth_year(	rs.getInt(		DB_BIRTH_YEAR));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

}
