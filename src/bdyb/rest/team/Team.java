package bdyb.rest.team;

import java.util.Arrays;

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
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public int getManageriD() {
		return manageriD;
	}
	public void setManageriD(int manageriD) {
		this.manageriD = manageriD;
	}
	public int getStatsiD() {
		return statsiD;
	}
	public void setStatsiD(int statsiD) {
		this.statsiD = statsiD;
	}
	public int getLeagueiD() {
		return leagueiD;
	}
	public void setLeagueiD(int leagueiD) {
		this.leagueiD = leagueiD;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
