package bdyb.rest.team;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
public class Team {

	private int iD;
	private int manageriD;
	private int statsiD;
	private int leagueiD;
	private String fullName;
	private String abbreviation;
	private String country;
	private String county;
	private String town;
	private byte[] photo;
	
	@Override
	public String toString() {
		return String.format(
				"Team [iD=%s, manageriD=%s, statsiD=%s, leagueiD=%s, fullName=%s, abbreviation=%s, country=%s, county=%s, town=%s, photo=%s]",
				iD, manageriD, statsiD, leagueiD, fullName, abbreviation, country, county, town,
				Arrays.toString(photo));
	}
	
	public Team(int manageriD, String fullName, String abbreviation, String country, String county, String town) {
		this.manageriD = manageriD;
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.country = country;
		this.county = county;
		this.town = town;
	}
	public Team() {
	}
	@XmlElement(name = "team_id")
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	@XmlElement(name = "man_acc_id")
	public int getManageriD() {
		return manageriD;
	}
	public void setManageriD(int manageriD) {
		this.manageriD = manageriD;
	}
	@XmlElement(name = "stats_id")
	public int getStatsiD() {
		return statsiD;
	}
	public void setStatsiD(int statsiD) {
		this.statsiD = statsiD;
	}
	@XmlElement(name = "league_id")
	public int getLeagueiD() {
		return leagueiD;
	}
	public void setLeagueiD(int leagueiD) {
		this.leagueiD = leagueiD;
	}
	@XmlElement(name = "full_name")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@XmlElement(name = "abb_name")
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	@XmlElement(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@XmlElement(name = "county")
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	@XmlElement(name = "town")
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	@XmlElement(name = "photo")
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
