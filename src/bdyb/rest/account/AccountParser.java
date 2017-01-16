package bdyb.rest.account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountParser {

	static String parseLoginFromResultset(ResultSet rs) {

		try {
			if (rs.next()) {
				String result = rs.getString("login");
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fail";
	}

	static int parseLoggedFromResultset(ResultSet rs) {
		try {
			if (rs.next()) {
				int result = rs.getInt("is_logged");
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
