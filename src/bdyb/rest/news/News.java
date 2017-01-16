package bdyb.rest.news;

import java.sql.Date;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bdyb.rest.help.SqlDateAdapter;

@XmlRootElement(name="news")
public class News {
	
	private int news_id;
	private int team_id;
	private int acc_id;
	private Date created;
	private String title;
	private String body;
	private byte[] photo;
	
	public News(int team_id, int acc_id, String title, String body) {
		super();
		this.team_id = team_id;
		this.acc_id = acc_id;
		this.title = title;
		this.body = body;
	}
	public News() {
		super();
	}
	
	@Override
	public String toString() {
		return String.format("News [news_id=%s, team_id=%s, acc_id=%s, created=%s, title=%s, body=%s, photo=%s]",
				news_id, team_id, acc_id, created, title, body, Arrays.toString(photo));
	}
	
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(int acc_id) {
		this.acc_id = acc_id;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
