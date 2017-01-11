package bdyb.rest.supporter;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class SupporterParser {

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

	static List<Supporter> parseListFromResultSet(ResultSet rs) {
		List<Supporter> supporterList = new ArrayList<Supporter>();
		Supporter s;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				s = new Supporter();

				s.setiD(			rs.getInt(		DB_ID));
				s.setRegDate(		new Date(dbDateFormat.parse(rs.getString(DB_REG_DATE)).getTime()));
				if(rs.getString(DB_LAST_LOGGED) != null)
					s.setLastLogged(new Date(dbDateFormat.parse(rs.getString(DB_LAST_LOGGED)).getTime()));
				s.setFirstName(		rs.getString(	DB_FIRST_NAME));
				s.setLastName(		rs.getString(	DB_LAST_NAME));
				s.setBirthday(		rs.getDate(		DB_BIRTHDAY));
				s.setPhoto(			rs.getBytes(	DB_PHOTO));
				s.setLogin(			rs.getString(	DB_LOGIN));
				s.setPass(			rs.getString(	DB_PASSWORD));

				supporterList.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return supporterList;
	}

	static String createInsertQuery(Supporter s) {
		String query = String.format("CALL insert_supporter ("
				+ "'%s','%s',TO_DATE('%s','yyyy-mm-dd'),'%s','%s',%s)",
				s.getFirstName(), s.getLastName(), s.getBirthday(),
				s.getLogin(), s.getPass(), "null");
		return query;
	}

	static String createUpdateQuery(String userid, String imie, String nazwisko, String login, String haslo, String photo, Integer islogged) {
		String query = String.format("UPDATE accounts SET ");
		if(login != null) 
			query += String.format("login = '%s', ", login);
		if(imie != null) 
			query += String.format("first_name = '%s', ", imie);
		if(nazwisko != null)
			query += String.format("last_name = '%s', ", nazwisko);
		if(haslo != null)
			query += String.format("pass = '%s', ", haslo);
		if(photo != null)
			query += String.format("photo = ?, ");
		if(islogged != null)
			query += String.format("islogged = ?, ", islogged);
		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE acc_id = %s", userid);
		return query;
	}

}
