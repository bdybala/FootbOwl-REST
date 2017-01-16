package bdyb.rest.team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamParser {

	private static final String DB_ID = 		"team_id";
	private static final String DB_MANAGER_ID =	"man_acc_id";
	private static final String DB_STATS_ID =	"stats_id";
	private static final String DB_LEAGUE_ID =	"league_id";
	private static final String DB_FULL_NAME = 	"full_name";
	private static final String DB_ABB_NAME =	"abb_name";
	private static final String DB_COUNTRY = 	"country";
	private static final String DB_COUNTY = 	"county";
	private static final String DB_TOWN = 		"town";
	private static final String DB_PHOTO =		"photo";
	
	
	static List<Team> parseListFromResultSet(ResultSet rs) {
		List<Team> teamList = new ArrayList<Team>();
		Team t;
		try {
			while(rs.next()) {
				t = new Team();

				t.setiD(			rs.getInt(		DB_ID));
				t.setManageriD(		rs.getInt(		DB_MANAGER_ID));
				t.setStatsiD(		rs.getInt(		DB_STATS_ID));
				t.setLeagueiD(		rs.getInt(		DB_LEAGUE_ID));
				t.setFullName(		rs.getString(	DB_FULL_NAME));
				t.setAbbreviation(	rs.getString(	DB_ABB_NAME));
				t.setCountry(		rs.getString(	DB_COUNTRY));
				t.setCounty(		rs.getString(	DB_COUNTY));
				t.setTown(			rs.getString(	DB_TOWN));
				t.setPhoto(			rs.getBytes(	DB_PHOTO));

				teamList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamList;	}

	static String createInsertQuery(Team t) {
		String query =  String.format("INSERT INTO teams VALUES("
				+ "team_seq.nextval, %s, null, null, '%s', '%s', '%s', '%s', '%s', %s)",
				t.getManageriD(), t.getFullName(), t.getAbbreviation(), t.getCountry(), t.getCounty(), t.getTown(), "null");
		return query;
	}

	static String createUpdateQuery(Integer teamid, Integer stats, Integer league, String fullname, String abbname,
			String country, String county, String town, String photo) {

		String query = String.format("UPDATE teams SET ");
		if(stats != null) 
			query += String.format("stats_id = '%s', ", stats);
		if(league != null) 
			query += String.format("league_id = '%s', ", league);
		if(fullname != null)
			query += String.format("full_name = '%s', ", fullname);
		if(abbname != null)
			query += String.format("abb_name = '%s', ", abbname);
		if(country != null)
			query += String.format("country = '%s', ", country);
		if(county != null)
			query += String.format("county  = '%s', ", county);
		if(town != null)
			query += String.format("town = '%s', ", town);
		if(photo != null)
			query += String.format("photo = ?, ");
		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE team_id = %s", teamid);
		return query;
	}

}
