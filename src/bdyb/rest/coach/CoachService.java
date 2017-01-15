package bdyb.rest.coach;

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

@Path("/CoachService")
public class CoachService {

	CoachDao coachDao = new CoachDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String LOGIN_TAKEN_RESULT="<result>login taken</result>";

	@GET
	@Path("/coaches")
	@Produces(MediaType.APPLICATION_XML)
	public List<Coach> getCoaches() {
		List<Coach> allCoaches = coachDao.getAllCoaches();
		return allCoaches;
	}
	
	@GET
	@Path("/coach/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public Coach getCoach(
			@PathParam("userid") int userid) {
		Coach c = coachDao.getCoach(userid);
		return c;
	}
		
	@PUT
	@Path("/coaches")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createCoach(
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("birthday") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("licence") String licencja,
			@FormParam("team_id") int iddruzyny,
			@Context HttpServletResponse servletResponse) {

		if(coachDao.selectLogin(login) == 1) {
			return LOGIN_TAKEN_RESULT;
		} 
		Coach c = new Coach(imie, nazwisko, dataurodzenia, login, haslo, licencja, iddruzyny);
		coachDao.createCoach(c);
		
		if(coachDao.selectLogin(login) == 1) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}
	
	@POST
	@Path("/coach/{userid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateSupporter(
			@PathParam("userid") String userid,
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("photo") String photo,
			@FormParam("licence") String licence,
			@FormParam("team_id") Integer teamiD,
			@FormParam("is_logged") Integer islogged) {
		return coachDao.updateCoach(userid, imie, nazwisko, login, haslo, photo, licence, teamiD, islogged);
	}

}
