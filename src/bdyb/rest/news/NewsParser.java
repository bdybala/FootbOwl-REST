package bdyb.rest.news;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NewsParser {
	
	private static final String DB_ID = 		"acc_id";
	private static final String DB_TEAM_ID =	"team_id";
	private static final String DB_ACC_ID =		"acc_id";
	private static final String DB_CREATED = 	"created"; 
	private static final String DB_TITLE = 		"title";
	private static final String DB_BODY = 		"body";
	private static final String DB_PHOTO = 		"photo";
	
	static List<News> parseListFromResultSet(ResultSet rs) {
		List<News> newsList = new ArrayList<News>();
		News n;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()) {
				n = new News();

				n.setNews_id(		rs.getInt(		DB_ID));
				n.setTeam_id(		rs.getInt(		DB_TEAM_ID));
				n.setAcc_id(		rs.getInt(		DB_ACC_ID));
				n.setCreated(		new Date(dbDateFormat.parse(rs.getString(DB_CREATED)).getTime()));
				n.setTitle(			rs.getString(	DB_TITLE));
				n.setBody(			rs.getString(	DB_BODY));
				n.setPhoto(			rs.getBytes(	DB_PHOTO));
				
				newsList.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newsList;
	}

	static News parseNewsFromResultSet(ResultSet rs) {
		News n = null;
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			if(rs.next()) {
				n = new News();

				n.setNews_id(		rs.getInt(		DB_ID));
				n.setTeam_id(		rs.getInt(		DB_TEAM_ID));
				n.setAcc_id(		rs.getInt(		DB_ACC_ID));
				n.setCreated(		new Date(dbDateFormat.parse(rs.getString(DB_CREATED)).getTime()));
				n.setTitle(			rs.getString(	DB_TITLE));
				n.setBody(			rs.getString(	DB_BODY));
				n.setPhoto(			rs.getBytes(	DB_PHOTO));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return n;
	}

	static String createInsertQuery(News n) {
		String query = String.format("CALL insert_news ("
				+ "'%s', '%s', '%s', '%s', null)", 
				n.getTeam_id(), n.getAcc_id(), n.getTitle(), n.getBody());
		return query;
	}

	static String createUpdateQuery(int news_id, Integer team_id, Integer acc_id, String title, String body,
			String photo) {
		String query = String.format("UPDATE news SET ");
		if(team_id != null) 
			query += String.format("team_id = '%s', ", team_id);
		if(acc_id != null) 
			query += String.format("acc_id = '%s', ", acc_id);
		if(title != null)
			query += String.format("title = '%s', ", title);
		if(body != null)
			query += String.format("body = '%s', ", body);
		if(photo != null)
			query += String.format("photo = ?, ");
		query = query.substring(0, query.length()-2);
		query += String.format(" WHERE news_id = %s", news_id);
		return query;
	}

}
