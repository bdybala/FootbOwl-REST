package bdyb.rest.player;

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

@Path("/PlayerService")
public class PlayerService {
	PlayerDao playerDao = new PlayerDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String LOGIN_TAKEN_RESULT="<result>login taken</result>";

	@GET
	@Path("/players")
	@Produces(MediaType.APPLICATION_XML)
	public List<Player> getPlayers() {
		List<Player> allPlayers = playerDao.getAllPlayers();
		return allPlayers;
	}
	
	@GET
	@Path("/player/{userid}")
	@Produces(MediaType.APPLICATION_XML)
	public Player getPlayer(
			@PathParam("userid") int userid) {
		Player p = playerDao.getPlayer(userid);
		return p;
	}

	@GET
	@Path("/team/{teamid}")
	@Produces(MediaType.APPLICATION_XML)
	public List<Player> getTeam(
			@PathParam("teamid") int teamid) {
		List<Player> teamSquad = playerDao.getTeam(teamid);
		return teamSquad;
	}
	
	@PUT
	@Path("/players")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createPlayer(@FormParam("iddruzyny") int iddruzyny,
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("birthday") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("position") String pozycja,
			@FormParam("pref_foot") String preferowananoga,
			@Context HttpServletResponse servletResponse) {

		if(playerDao.selectLogin(login) == 1) {
			return LOGIN_TAKEN_RESULT;
		} 
		Player p = new Player(imie, nazwisko, dataurodzenia, login, haslo, iddruzyny, pozycja, preferowananoga);
		playerDao.createPlayer(p);

		if(playerDao.selectLogin(login) == 1)
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}
	
	@POST
	@Path("/player/{userid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updatePlayer(
			@PathParam("userid") String userid,
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("photo") String photo,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("team_id") Integer teamiD,
			@FormParam("position") String position,
			@FormParam("pref_foot") String foot,
			@FormParam("is_logged") Integer islogged) {
		return playerDao.updatePlayer(userid, imie, nazwisko, photo, login, haslo, teamiD, position, foot, islogged);
	}


}
