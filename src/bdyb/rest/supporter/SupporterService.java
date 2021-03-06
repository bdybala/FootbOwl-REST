package bdyb.rest.supporter;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/SupporterService")
public class SupporterService {

	SupporterDao supporterDao = new SupporterDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String LOGIN_TAKEN_RESULT="<result>login taken</result>";

	@GET
	@Path("/supporters")
	@Produces(MediaType.APPLICATION_XML)
	public List<Supporter> getSupporters() {
		List<Supporter> allSupporters = supporterDao.getAllSupporters();
		return allSupporters;
	}
	
	@GET
	@Path("/supporter/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public Supporter getSupporter(
			@PathParam("userid") int userid) {
		Supporter s = supporterDao.getSupporter(userid);
		return s;
	}
	
	@PUT
	@Path("/supporters")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createSupporter(
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("birthday") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@Context HttpServletResponse servletResponse) {
		
		if(supporterDao.selectLogin(login) == 1) {
			return LOGIN_TAKEN_RESULT;
		} 
		Supporter s = new Supporter(imie, nazwisko, dataurodzenia, login, haslo);
		supporterDao.createSupporter(s);

		if(supporterDao.selectLogin(login) == 1) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;

	}
	
	@POST
	@Path("/supporter/{userid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateSupporter(
			@PathParam("userid") String userid,
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("photo") String photo,
			@FormParam("is_logged") Integer islogged) {
		return supporterDao.updateSupporter(userid, imie, nazwisko, login, haslo, photo, islogged);
	}
	
}
