package bdyb.rest.match;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MatchParser {

	private static final String DB_MATCH_ID = 	"match_id";
	private static final String DB_LEAGUE_ID =	"league_id";
	private static final String DB_HOME_ID_=	"home_id";
	private static final String DB_AWAY_ID = 	"away_id"; 
	private static final String DB_GOALS_HOME = "goals_home";
	private static final String DB_GOALS_AWAY =	"goals_away";
	private static final String DB_MATCH_DATE = "match_date";
	
	static List<Match> parseListFromResultSet(ResultSet rs) {
		List<Match> matchList = new ArrayList<Match>();
		Match m;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				m = new Match();
				
				m.setMatch_id(rs.getInt(DB_MATCH_ID));
				m.setLeague_id(rs.getInt(DB_LEAGUE_ID));
				m.setHome_id(rs.getInt(DB_HOME_ID_));
				m.setAway_id(rs.getInt(DB_AWAY_ID));
				m.setGoals_home(rs.getInt(DB_GOALS_HOME));
				m.setGoals_away(rs.getInt(DB_GOALS_AWAY));
				m.setMatch_date(new Date(dbDateFormat.parse(rs.getString(DB_MATCH_DATE)).getTime()));
				
				matchList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return matchList;
	}

	static String createInsertQuery(Match m) {
		String query = String.format("CALL insert_match ("
				+ "'%s', '%s', '%s', '%s', '%s', TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss')",
				m.getLeague_id(), m.getHome_id(), m.getAway_id(), 
				m.getGoals_home(), m.getGoals_away(), m.getMatch_date());
		return query;
	}

	static String createUpdateQuery(int match_id, Integer league_id, Integer home_id, Integer away_id, Integer goals_home,
			Integer goals_away, String match_date) {
		
		String query = "UPDATE matches SET ";
		if(league_id != null) query +=	"league_id = " + 	league_id + ", ";
		if(home_id != 	null) query +=	"home_id = " + 		home_id + ", ";
		if(away_id != 	null) query +=	"away_id = " + 		away_id + ", ";
		if(goals_home !=null) query +=	"goals_home = " + 	goals_home + ", ";
		if(goals_away!= null) query +=	"goals_away = " + 	goals_away+ ", ";
		if(match_date !=null) query +=	"match_date = " + "TO_DATE('" + match_date + "', 'yyyy-mm-dd hh24:mi:ss'), ";
		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE match_id = %s", match_id);
		return null;
	}

}
