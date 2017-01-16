package bdyb.rest.league;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="league")
public class League {
	
	private int league_id;
	private String full_name;
	private String short_name;
	private String age_group;
	private int birth_year;
	
	public League() {
		super();
	}
	public League(String full_name, String short_name, String age_group, int birth_year) {
		super();
		this.full_name = full_name;
		this.short_name = short_name;
		this.age_group = age_group;
		this.birth_year = birth_year;
	}
	
	@Override
	public String toString() {
		return String.format("League [league_id=%s, full_name=%s, short_name=%s, age_group=%s, birth_year=%s]",
				league_id, full_name, short_name, age_group, birth_year);
	}
	
	public int getLeague_id() {
		return league_id;
	}
	public void setLeague_id(int league_id) {
		this.league_id = league_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getAge_group() {
		return age_group;
	}
	public void setAge_group(String age_group) {
		this.age_group = age_group;
	}
	public int getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}

}
