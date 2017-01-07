package bdyb.rest.supporter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bdyb.rest.help.SqlDateAdapter;

@XmlRootElement(name = "supporter")
public class Supporter {

	private int iD;
	private Date regDate;
	private Date lastLogged;
	private String firstName;
	private String lastName;
	private Date birthday;
	private byte[] photo;
	private String login;
	private String pass;
	
	@Override
	public String toString() {
		return String.format(
				"Supporter [iD=%s, regDate=%s, lastLogged=%s, firstName=%s, lastName=%s, birthday=%s, photo=%s, login=%s, pass=%s]",
				iD, regDate, lastLogged, firstName, lastName, birthday, Arrays.toString(photo), login, pass);
	}

	public Supporter() {
	}
	
	Supporter(String imie, String nazwisko, String dataurodzenia, String login2, String haslo) {
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date javaDate = null;
		try {
			javaDate = dbDateFormat.parse(dataurodzenia);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		birthday = new Date(javaDate.getTime());
		firstName = imie;
		lastName = nazwisko;
		login = login2;
		pass = haslo;
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
	
}
