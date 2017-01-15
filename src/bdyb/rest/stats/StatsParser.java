package bdyb.rest.stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class StatsParser {

	private static final String DB_STATS_ID = 		"stats_id"; 
	private static final String DB_SEASON = 		"season";
	private static final String DB_MATCHES = 		"matches";
	private static final String DB_WINS = 			"wins";
	private static final String DB_DRAWS = 			"draws";
	private static final String DB_LOSES = 			"loses";
	private static final String DB_YELLOW_CARDS = 	"yellow_cards";
	private static final String DB_RED_CARDS = 		"red_cards";
	private static final String DB_GOALS = 			"goals";
	private static final String DB_ASSISTS = 		"assists";
	private static final String DB_MINS_PLAYED = 	"mins_played";
	private static final String DB_CLEAN_SHEETS = 	"clean_sheets";
	private static final String DB_SHOTS = 			"shots";

	static List<Stats> parseListFromResultSet(ResultSet rs) {
		List<Stats> statsList = new ArrayList<Stats>();
		Stats s;


		try {
			while(rs.next()) {
				s = new Stats();

				s.setStats_id(		rs.getInt(		DB_STATS_ID));
				s.setSeason(		rs.getString(	DB_SEASON));
				s.setMatches(		rs.getInt(		DB_MATCHES));
				s.setWins(			rs.getInt(		DB_WINS));
				s.setDraws(			rs.getInt(		DB_DRAWS));
				s.setLoses(			rs.getInt(		DB_LOSES));
				s.setYellow_cards(	rs.getInt(		DB_YELLOW_CARDS));
				s.setRed_cards(		rs.getInt(		DB_RED_CARDS));
				s.setGoals(			rs.getInt(		DB_GOALS));
				s.setAssists(		rs.getInt(		DB_ASSISTS));
				s.setMins_played(	rs.getInt(		DB_MINS_PLAYED));
				s.setClean_sheets(	rs.getInt(		DB_CLEAN_SHEETS));
				s.setShots(			rs.getInt(		DB_SHOTS));


				statsList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statsList;
	}

	static String createInsertQuery(Stats s) {
		String query = String.format("CALL insert_stats ('%s')", s.getSeason());
		return query;
	}

	static String createUpdateQuery(int stats_id, String season, Integer matches, Integer wins, Integer draws, Integer loses,
			Integer yellow_cards, Integer red_cards, Integer goals, Integer assists, Integer mins_played,
			Integer clean_sheets, Integer shots) {
		
		String query = "UPDATE stats SET ";
		if(season != null) 		query +=	"season = " + 		season + ", ";
		if(matches != 	null)	query +=	"matches = " + 		matches + ", ";
		if(wins != 	null) 		query +=	"wins = " + 		wins + ", ";
		if(draws !=null) 		query +=	"draws = " + 		draws + ", ";
		if(loses!= null) 		query +=	"loses = " + 		loses+ ", ";
		if(yellow_cards!= null) query +=	"yellow_cards = " + yellow_cards+ ", ";
		if(red_cards!= null) 	query +=	"red_cards = " + 	red_cards+ ", ";
		if(goals!= null) 		query +=	"goals = " + 		goals+ ", ";
		if(assists!= null) 		query +=	"assists = " + 		assists+ ", ";
		if(mins_played!= null) 	query +=	"mins_played = " + 	mins_played+ ", ";
		if(clean_sheets!= null) query +=	"clean_sheets = " + clean_sheets+ ", ";
		if(shots!= null) 		query +=	"shots = " + 		shots+ ", ";

		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE match_id = %s", stats_id);
		return query;
	}


}
