package bdyb.rest.player;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PlayerParser {
	
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
	private static final String DB_TEAM_ID =	"team_id";
	private static final String DB_POSITION =	"position";
	private static final String DB_PREF_FOOT =	"pref_foot";

	static List<Player> parseListFromResultSet(ResultSet rs) {
		List<Player> playerList = new ArrayList<Player>();
		Player p;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				p = new Player();

				p.setiD(			rs.getInt(		DB_ID));
				p.setRegDate(		new Date(dbDateFormat.parse(rs.getString(DB_REG_DATE)).getTime()));
				if (rs.getString(DB_LAST_LOGGED) != null)
					p.setLastLogged(new Date(dbDateFormat.parse(rs.getString(DB_LAST_LOGGED)).getTime()));
				p.setFirstName(		rs.getString(	DB_FIRST_NAME));
				p.setLastName(		rs.getString(	DB_LAST_NAME));
				p.setBirthday(		rs.getDate(		DB_BIRTHDAY));
				p.setPhoto(			rs.getBytes(	DB_PHOTO));
				p.setLogin(			rs.getString(	DB_LOGIN));
				p.setPass(			rs.getString(	DB_PASSWORD));
				p.setTeamiD(		rs.getInt(		DB_TEAM_ID));
				p.setPosition(		rs.getString(	DB_POSITION));
				p.setPrefFoot(		rs.getString(	DB_PREF_FOOT));

				playerList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerList;
	}

	static String createInsertQuery(Player p) {
		String query = String.format("CALL insert_player ("
				+ "'%s','%s',TO_DATE('%s','yyyy-mm-dd'),'%s','%s','%s','%s','%s',%s)",
				p.getFirstName(), p.getLastName(), p.getBirthday(),
				p.getLogin(), p.getPass(), p.getTeamiD(), p.getPosition(), p.getPrefFoot(), "null");
		
		return query;
	}

}
