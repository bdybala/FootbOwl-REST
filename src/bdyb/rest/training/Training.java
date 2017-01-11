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
	private Date training_date;;
	private String training_place;
	
	public Training(String training_desc, String training_date, String training_place) {
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date javaDate = null;
		try {
			javaDate = dbDateFormat.parse(training_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.training_desc = training_desc;
		this.training_date = new Date(javaDate.getTime());
		this.training_place = training_place;
	}
	public Training() {
	}
	
	@Override
	public String toString() {
		return String.format("Training [training_id=%s, training_desc=%s, training_date=%s, training_place=%s]",
				training_id, training_desc, training_date, training_place);
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
	
}
