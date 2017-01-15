package bdyb.rest.match;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/MatchService")
public class MatchService {

	MatchDao matchDao = new MatchDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
//	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/matches")
	@Produces(MediaType.APPLICATION_XML)
	public List<Match> getMatches() {
		List<Match> allMatches = matchDao.getAllMatches();
		return allMatches;
	}
	
	@PUT
	@Path("/matches")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createMatch(
			@FormParam("league_id") int league_id,
			@FormParam("home_id") int home_id,
			@FormParam("away_id") int away_id,
			@FormParam("goals_home") int goals_home,
			@FormParam("goals_away") int goals_away,
			@FormParam("match_date") String match_date) {
		
		Match matchToCreate = new Match(league_id, home_id, away_id, goals_home, goals_away, match_date);
		matchDao.createMatch(matchToCreate);
		return SUCCESS_RESULT;
	}
	
	@POST
	@Path("/matches")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Match> getMatches(
			@FormParam("from") String from,
			@FormParam("to") String to){
		List<Match> allMatches = matchDao.getAllMatches(from, to);
		return allMatches; 
	}
	
	@POST
	@Path("/match/{matchid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateMatch(
			@PathParam("matchid") int match_id,
			@FormParam("league_id") Integer league_id,
			@FormParam("home_id") Integer home_id,
			@FormParam("away_id") Integer away_id,
			@FormParam("goals_home") Integer goals_home,
			@FormParam("goals_away") Integer goals_away,
			@FormParam("match_date") String match_date){
		
		return matchDao.updateMatch(match_id, league_id, home_id, away_id, goals_home, goals_away, match_date);
	}
}
