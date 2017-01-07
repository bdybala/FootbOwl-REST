package bdyb.rest.team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamParser {

	private static final String DB_ID = 		"team_id";
	private static final String DB_MANAGER_ID =	"man_acc_id";
	private static final String DB_LEAGUE_ID =	"league_id";
	private static final String DB_FULL_NAME = 	"full_name";
	private static final String DB_ABB_NAME =	"abb_name";
	private static final String DB_COUNTRY = 	"country";
	private static final String DB_COUNTY = 	"county";
	private static final String DB_TOWN = 		"town";
//	private static final String DB_PHOTO =		"photo";
	
	
	static List<Team> parseListFromResultSet(ResultSet rs) {
		List<Team> teamList = new ArrayList<Team>();
		Team t;
		try {
			while(rs.next()) {
				t = new Team();

				t.setiD(			rs.getInt(		DB_ID));
				t.setManageriD(		rs.getInt(		DB_MANAGER_ID));
				t.setLeagueiD(		rs.getInt(		DB_LEAGUE_ID));
				t.setFullName(		rs.getString(	DB_FULL_NAME));
				t.setAbbreviation(	rs.getString(	DB_ABB_NAME));
				t.setCountry(		rs.getString(	DB_COUNTRY));
				t.setCounty(		rs.getString(	DB_COUNTY));
				t.setTown(			rs.getString(	DB_TOWN));

				teamList.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teamList;	}

	static String createInsertQuery(Team t) {
		String query =  String.format("INSERT INTO teams VALUES("
				+ "team_seq.nextval, %s, null, null, '%s', '%s', '%s', '%s', '%s', %s",
				t.getManageriD(), t.getFullName(), t.getAbbreviation(), t.getCountry(), t.getCounty(), t.getTown(), "null");
		return query;
	}

}
