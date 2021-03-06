package bdyb.rest.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		result = "<login>" + result + "</login>";
		return result;
	}
	
	@GET
	@Path("/islogged/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public String isLogged(
			@PathParam("userid") int userid) {
		int result = accDao.isLogged(userid);
		return "<is_logged>" + result + "</is_logged>";
	}
}
