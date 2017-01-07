package bdyb.rest.account;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class Account {

	private String login;
	private String pass;
	
	Account(String login, String pass) {
		this.login = login;
		this.pass = pass;
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
