package bdyb.rest.player;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bdyb.rest.help.SqlDateAdapter;

@XmlRootElement(name = "player")
public class Player {

	private int iD;
	private Date regDate;
	private Date lastLogged;
	private String firstName;
	private String lastName;
	private Date birthday;
	private byte[] photo;
	private String login;
	private String pass;
	private int teamiD;
	private String position;
	private String prefFoot;
	
	public Player() {
	}

	Player(String imie, String nazwisko, String dataurodzenia, String login2, String haslo, 
			int iddruzyny,String pozycja, String preferowananoga) {
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		
		java.util.Date javaDate = null;
		try {
			javaDate = dbDateFormat.parse(dataurodzenia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		firstName = imie;
		lastName = nazwisko;
		birthday = new Date(javaDate.getTime());
		login = login2;
		pass = haslo;
		teamiD = iddruzyny;
		position = pozycja;
		prefFoot = preferowananoga;
	}

	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getLastLogged() {
		return lastLogged;
	}
	public void setLastLogged(Date lastLogged) {
		this.lastLogged = lastLogged;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getTeamiD() {
		return teamiD;
	}
	public void setTeamiD(int teamiD) {
		this.teamiD = teamiD;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPrefFoot() {
		return prefFoot;
	}
	public void setPrefFoot(String prefFoot) {
		this.prefFoot = prefFoot;
	}
}
