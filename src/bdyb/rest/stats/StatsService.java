package bdyb.rest.stats;

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

@Path("/StatsService")
public class StatsService {
	
	StatsDao statsDao = new StatsDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_XML)
	public List<Stats> getStats() {
		return statsDao.getStats();
	}
	
	@PUT
	@Path("/stats")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createStats(
			@FormParam("season") String season) {
		
		// TODO if exists return DUPLICATE
		Stats s = new Stats(season);
		statsDao.createStats(s);
		// TODO if exists return SUCCESS
		return SUCCESS_RESULT;
	}
	
	@POST
	@Path("/stats/{statsid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateStats(
			@PathParam("statsid") int statsid,
			@FormParam("season") String season,
			@FormParam("matches") Integer matches,
			@FormParam("wins") Integer wins,
			@FormParam("draws") Integer draws,
			@FormParam("loses") Integer loses,
			@FormParam("yellow_cards") Integer yellow_cards,
			@FormParam("red_cards") Integer red_cards,
			@FormParam("goals") Integer goals,
			@FormParam("assists") Integer assists,
			@FormParam("mins_played") Integer mins_played,
			@FormParam("clean_sheets") Integer clean_sheets,
			@FormParam("shots") Integer shots) {
		return statsDao.updateStats(statsid, season, matches, wins, 
				draws, loses,yellow_cards, red_cards, 
				goals, assists, mins_played, clean_sheets, shots);
	}
	
}
