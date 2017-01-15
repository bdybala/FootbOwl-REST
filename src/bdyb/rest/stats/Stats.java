package bdyb.rest.stats;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="stats")
public class Stats {

	private int stats_id;
	private String season;
	private int matches;
	private int wins;
	private int draws;
	private int loses;
	private int yellow_cards;
	private int red_cards;
	private int goals;
	private int assists;
	private int mins_played;
	private int clean_sheets;
	private int shots;
	
	@Override
	public String toString() {
		return String.format(
				"Stats [stats_id=%s, season=%s, matches=%s, wins=%s, draws=%s, loses=%s, yellow_cards=%s, red_cards=%s, goals=%s, assists=%s, mins_played=%s, clean_sheets=%s, shots=%s]",
				stats_id, season, matches, wins, draws, loses, yellow_cards, red_cards, goals, assists, mins_played,
				clean_sheets, shots);
	}
	public Stats(String season, int matches, int wins, int draws, int loses, int yellow_cards,
			int red_cards, int goals, int assists, int mins_played, int clean_sheets, int shots) {
		this.season = season;
		this.matches = matches;
		this.wins = wins;
		this.draws = draws;
		this.loses = loses;
		this.yellow_cards = yellow_cards;
		this.red_cards = red_cards;
		this.goals = goals;
		this.assists = assists;
		this.mins_played = mins_played;
		this.clean_sheets = clean_sheets;
		this.shots = shots;
	}
	public Stats() {
	}
	
	public int getStats_id() {
		return stats_id;
	}
	public void setStats_id(int stats_id) {
		this.stats_id = stats_id;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public int getYellow_cards() {
		return yellow_cards;
	}
	public void setYellow_cards(int yellow_cards) {
		this.yellow_cards = yellow_cards;
	}
	public int getRed_cards() {
		return red_cards;
	}
	public void setRed_cards(int red_cards) {
		this.red_cards = red_cards;
	}
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public int getMins_played() {
		return mins_played;
	}
	public void setMins_played(int mins_played) {
		this.mins_played = mins_played;
	}
	public int getClean_sheets() {
		return clean_sheets;
	}
	public void setClean_sheets(int clean_sheets) {
		this.clean_sheets = clean_sheets;
	}
	public int getShots() {
		return shots;
	}
	public void setShots(int shots) {
		this.shots = shots;
	}
	
}
