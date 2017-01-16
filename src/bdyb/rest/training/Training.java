package bdyb.rest.training;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bdyb.rest.help.SqlDateAdapter;

@XmlRootElement(name = "training")
public class Training {
	
	private int training_id;
	private String training_desc;
	private Date training_date;
	private String training_place;
	private Date training_end;
	
	public Training(String training_desc, String training_date, String training_place, String training_end) {
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date javaDate = null;
		try {
			javaDate = dbDateFormat.parse(training_date);
		
			this.training_desc = training_desc;
			this.training_date = new Date(javaDate.getTime());
			this.training_place = training_place;
			this.training_end = new Date(dbDateFormat.parse(training_end).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Training() {
	}
	
	@Override
	public String toString() {
		return String.format(
				"Training [training_id=%s, training_desc=%s, training_date=%s, training_place=%s, training_end=%s]",
				training_id, training_desc, training_date, training_place, training_end);
	}
	
	public int getTraining_id() {
		return training_id;
	}
	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}
	public String getTraining_desc() {
		return training_desc;
	}
	public void setTraining_desc(String training_desc) {
		this.training_desc = training_desc;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getTraining_date() {
		return training_date;
	}
	public void setTraining_date(Date training_date) {
		this.training_date = training_date;
	}
	public String getTraining_place() {
		return training_place;
	}
	public void setTraining_place(String training_place) {
		this.training_place = training_place;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	public Date getTraining_end() {
		return training_end;
	}
	public void setTraining_end(Date training_end) {
		this.training_end = training_end;
	}
	
}
