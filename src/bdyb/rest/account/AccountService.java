package bdyb.rest.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/AccountService")
public class AccountService {

	private AccountDao accDao = new AccountDao();
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkLogin(
			@FormParam("login") String login,
			@FormParam("haslo") String pass) {
		String result = accDao.login(login, pass);
//		result = "<acc_id>" + result + "</acc_id>";
		return result;
	}
	
}
