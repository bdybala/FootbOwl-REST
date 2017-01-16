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

}
