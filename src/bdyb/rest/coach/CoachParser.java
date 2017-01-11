package bdyb.rest.coach;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CoachParser {

	private static final String DB_ID = 		"acc_id";
//	private static final String DB_ACC_TYPE =	"acc_type";
	private static final String DB_REG_DATE =	"reg_date";
	private static final String DB_LAST_LOGGED ="last_logged";
	private static final String DB_FIRST_NAME = "first_name"; 
	private static final String DB_LAST_NAME = 	"last_name";
	private static final String DB_BIRTHDAY = 	"birthday";
	private static final String DB_LOGIN = 		"login";
	private static final String DB_PASSWORD = 	"pass";
	private static final String DB_PHOTO = 		"photo";
	private static final String DB_LICENCE =	"licence";
	private static final String DB_TEAM_ID =	"team_id";
	
	public static List<Coach> parseListFromResultSet(ResultSet rs) {
		List<Coach> coachList = new ArrayList<Coach>();
		Coach c;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				c = new Coach();

				c.setiD(			rs.getInt(		DB_ID));
				c.setRegDate(		new Date(dbDateFormat.parse(rs.getString(DB_REG_DATE)).getTime()));
				if (rs.getString(DB_LAST_LOGGED) != null)
					c.setLastLogged(new Date(dbDateFormat.parse(rs.getString(DB_LAST_LOGGED)).getTime()));
				c.setFirstName(		rs.getString(	DB_FIRST_NAME));
				c.setLastName(		rs.getString(	DB_LAST_NAME));
				c.setBirthday(		rs.getDate(		DB_BIRTHDAY));
				c.setPhoto(			rs.getBytes(	DB_PHOTO));
				c.setLogin(			rs.getString(	DB_LOGIN));
				c.setPass(			rs.getString(	DB_PASSWORD));
				c.setLicence(		rs.getString(	DB_LICENCE));
				c.setTeamiD(		rs.getInt(		DB_TEAM_ID));
				
				coachList.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coachList;
	}

	static  String createInsertQuery(Coach c) {
		String query = String.format("CALL insert_coach ("
				+ "'%s','%s',TO_DATE('%s','yyyy-mm-dd'),'%s','%s','%s','%s',%s)",
				c.getFirstName(), c.getLastName(), c.getBirthday(),
				c.getLogin(), c.getPass(), c.getLicence(), c.getTeamiD(), "null");
		
		return query;
	}

	static String createUpdateQuery(String userid, String imie, String nazwisko, String login, String haslo,
			String photo, String licence, Integer teamiD, Integer islogged) {
		
		String query = String.format("CALL update_coach (" + userid);
		
		if (imie != null) query += 		", '" + imie + 		"'";
		else query += ", null";
		if (nazwisko != null) query += 	", '" + nazwisko + 	"'";
		else query += ", null";
		if (photo!= null) query += 		", ?";
		else query += ", null";
		if (login != null) query += 	", '" + login + 	"'";
		else query += ", null";
		if (haslo != null) query += 	", '" + haslo + 	"'";
		else query += ", null";
		if (islogged != null) query += 	", '" + islogged + 	"'";
		else query += ", null";
		if (licence != null) query += 	", '" + licence + 	"'";
		else query += ", null";
		if (teamiD != null) query += 	", '" + teamiD + 	"'";
		else query += ", null";
		query += ")";
		
		return query;
	}

}
