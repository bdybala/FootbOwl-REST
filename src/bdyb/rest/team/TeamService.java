package bdyb.rest.team;

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

@Path("/TeamService")
public class TeamService {
	
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	TeamDao teamDao = new TeamDao();
	
	@GET
	@Path("/teams")
	@Produces(MediaType.APPLICATION_XML)
	public List<Team> getTeams() {
		List<Team> allTeams = teamDao.getAllTeams();
		return allTeams;
	}
	
	@PUT
	@Path("/teams")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createTeam(
			@FormParam("man_id") int idkierownika,
			@FormParam("full_name") String pelnanazwa,
			@FormParam("abb_name") String krotkanazwa,
			@FormParam("country") String kraj,
			@FormParam("county") String wojewodztwo,
			@FormParam("town") String miejscowosc,
			@Context HttpServletResponse servletResponse) {
		Team t = new Team(idkierownika, pelnanazwa, krotkanazwa, kraj, wojewodztwo, miejscowosc);
		int rows = teamDao.createTeam(t);
		
		if(rows ==1) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}

	@POST
	@Path("/team/{teamid}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateTeam(
			@PathParam("teamid") int teamid,
			@FormParam("stats_id") Integer stats,
			@FormParam("league_id") Integer league,
			@FormParam("full_name") String fullname,
			@FormParam("abb_name") String abbname,
			@FormParam("country") String country,
			@FormParam("county") String county,
			@FormParam("town") String town,
			@FormParam("photo") String photo) {
		return teamDao.updateTeam(teamid, stats, league, fullname, abbname, country, county, town, photo);
	}
}
