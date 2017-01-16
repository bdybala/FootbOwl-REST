package bdyb.rest.league;

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


@Path("/LeagueService")
public class LeagueService {

	LeagueDao leagueDao = new LeagueDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/leagues")
	@Produces(MediaType.APPLICATION_XML)
	public List<League> getLeague() {
		List<League> allLeagues= leagueDao.getAllLeagues();
		return allLeagues;
	}
	
	@GET
	@Path("/league/{leagueid}")
	@Produces(MediaType.APPLICATION_XML)
	public League getLeague(
			@PathParam("leagueid") int leagueid) {
		League league= leagueDao.getLeague(leagueid);
		return league;
	}
	
	@PUT
	@Path("/leagues")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createLeague(
			@FormParam("full_name") String full_name,
			@FormParam("short_name") String short_name,
			@FormParam("age_group") String age_group,
			@FormParam("birth_year") int birth_year,
			@Context HttpServletResponse servletResponse) {
		
		//TODO if exists return faczken
		League l = new League(full_name, short_name, age_group, birth_year);
		int rows = leagueDao.createLeague(l);
		
		if(rows == 1)
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}
	
	@POST
	@Path("/league/{leagueid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateLeague(
			@PathParam("leagueid") int leagueid,
			@FormParam("full_name") String full_name,
			@FormParam("short_name") String short_name,
			@FormParam("age_group") String age_group,
			@FormParam("birth_year") int birth_year) {
		return leagueDao.updateLeague(leagueid, full_name, short_name, age_group, birth_year);
	}
	
}
