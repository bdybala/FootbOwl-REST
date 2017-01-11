package bdyb.rest.manager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ManagerParser {

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

	static List<Manager> parseListFromResultSet(ResultSet rs) {
		List<Manager> managerList = new ArrayList<Manager>();
		Manager m;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				m = new Manager();

				m.setiD(			rs.getInt(		DB_ID));
				m.setRegDate(		new Date(dbDateFormat.parse(rs.getString(DB_REG_DATE)).getTime()));
				if(rs.getString(DB_LAST_LOGGED) != null)
					m.setLastLogged(new Date(dbDateFormat.parse(rs.getString(DB_LAST_LOGGED)).getTime()));
				m.setFirstName(		rs.getString(	DB_FIRST_NAME));
				m.setLastName(		rs.getString(	DB_LAST_NAME));
				m.setBirthday(		rs.getDate(		DB_BIRTHDAY));
				m.setPhoto(			rs.getBytes(	DB_PHOTO));
				m.setLogin(			rs.getString(	DB_LOGIN));
				m.setPass(			rs.getString(	DB_PASSWORD));

				managerList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return managerList;
	}

	static String createInsertQuery(Manager m) {
		String query = String.format("CALL insert_manager ("
				+ "'%s','%s',TO_DATE('%s','yyyy-mm-dd'),'%s','%s',%s)",
				m.getFirstName(), m.getLastName(), m.getBirthday(),
				m.getLogin(), m.getPass(), "?");
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
