package bdyb.rest.match;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bdyb.rest.help.SqlDateAdapter;

@XmlRootElement(name = "match")
public class Match {

	private int match_id;
	private int league_id;
	private int home_id;
	private int away_id;
	private int goals_home;
	private int goals_away;
	private Date match_date;
	
	public Match(int league_id, int home_id, int away_id, int goals_home, int goals_away, String match_date) {
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date javaDate = null;
		try {
			javaDate = dbDateFormat.parse(match_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.league_id = league_id;
		this.home_id = home_id;
		this.away_id = away_id;
		this.goals_home = goals_home;
		this.goals_away = goals_away;
		this.match_date = new Date(javaDate.getTime());
	}

	public Match() {
	}

	@Override
	public String toString() {
		return String.format(
				"Match [match_id=%s, league_id=%s, home_id=%s, away_id=%s, goals_home=%s, goals_away=%s, match_date=%s]",
				match_id, league_id, home_id, away_id, goals_home, goals_away, match_date);
	}
	
	public int getMatch_id() {
		return match_id;
	}
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}
	public int getHome_id() {
		return home_id;
	}
	public void setHome_id(int home_id) {
		this.home_id = home_id;
	}
	public int getAway_id() {
		return away_id;
	}
	public void setAway_id(int away_id) {
		this.away_id = away_id;
	}
	public int getGoals_home() {
		return goals_home;
	}
	public void setGoals_home(int goals_home) {
		this.goals_home = goals_home;
	}
	public int getGoals_away() {
		return goals_away;
	}
	public void setGoals_away(int goals_away) {
		this.goals_away = goals_away;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getMatch_date() {
		return match_date;
	}
	public void setMatch_date(Date match_date) {
		this.match_date = match_date;
	}
	
}
