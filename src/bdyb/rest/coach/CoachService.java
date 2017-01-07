package bdyb.rest.coach;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/CoachService")
public class CoachService {

	CoachDao coachDao = new CoachDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	@GET
	@Path("/coaches")
	@Produces(MediaType.APPLICATION_XML)
	public List<Coach> getCoaches() {
		List<Coach> allCoaches = coachDao.getAllCoaches();
		return allCoaches;
	}

	@PUT
	@Path("/coaches")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createCoach(
			@FormParam("imie") String imie,
			@FormParam("nazwisko") String nazwisko,
			@FormParam("dataurodzenia") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("haslo") String haslo,
			@FormParam("licencja") String licencja,
			@FormParam("id druzyny") int iddruzyny,
			@Context HttpServletResponse servletResponse) {

		Coach c = new Coach(imie, nazwisko, dataurodzenia, login, haslo, licencja, iddruzyny);
		// CHECK IF LOGIN EXISTS
		int rows = coachDao.createCoach(c);
		
		// CHECK IF LOGIN EXISTS
		if(rows == 2) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}

}
