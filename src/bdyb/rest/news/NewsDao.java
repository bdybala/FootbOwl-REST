package bdyb.rest.news;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bdyb.rest.dao.DataAccessObject;

public class NewsDao extends DataAccessObject {

	List<News> getAllNews() {
		final String sql = "SELECT news_id, team_id, acc_id, "
				+ "TO_CHAR(created, 'YYYY-MM-DD HH24:MI:SS') \"created\",  "
				+ "title, body, photo "
				+ "FROM news";
		List<News> newsList = new ArrayList<News>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			newsList = NewsParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return newsList;
	}

	News getNews(int newsid) {
		final String sql = "SELECT news_id, team_id, acc_id, "
				+ "TO_CHAR(created, 'YYYY-MM-DD HH24:MI:SS') \"created\", "
				+ "title, body, photo"
				+ "FROM news "
				+ "WHERE news_id = " + newsid;
		News n = null;
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			n = NewsParser.parseNewsFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return n;
	}

	List<News> getTeamNews(int teamid) {
		final String sql = "SELECT news_id, team_id, acc_id, "
				+ "TO_CHAR(created, 'YYYY-MM-DD HH24:MI:SS') \"created\", "
				+ "title, body, photo"
				+ "FROM news "
				+ "WHERE team_id = " + teamid;
		List<News> newsList = new ArrayList<News>();
		ResultSet rs = null;

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rs = pStmt.executeQuery();
			newsList = NewsParser.parseListFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}

		return newsList;
	}

	int createNews(News n) {
		String sql = NewsParser.createInsertQuery(n);
		int rows = 0;
		try {
			connectToDatabase();
			createPreparedStatement(sql);
			rows = pStmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return rows;		
	}

	String updateNews(int news_id, Integer team_id, Integer acc_id, String title, String body, String photo) {
		String sql = NewsParser.createUpdateQuery(news_id, team_id, acc_id, title, body, photo);

		try {
			connectToDatabase();
			createPreparedStatement(sql);
			if(photo != null) {
				InputStream in = createFileFromUri(photo);
				pStmt.setBlob(1, in);
			}
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement();
			disconnectFromDatabase();
		}
		return sql;
	}

}
