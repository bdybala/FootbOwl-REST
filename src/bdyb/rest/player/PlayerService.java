package bdyb.rest.player;

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

@Path("/PlayerService")
public class PlayerService {
	PlayerDao playerDao = new PlayerDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	@GET
	@Path("/players")
	@Produces(MediaType.APPLICATION_XML)
	public List<Player> getPlayers() {
		List<Player> allPlayers = playerDao.getAllPlayers();
		return allPlayers;
	}

	@PUT
	@Path("/players")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createPlayer(@FormParam("iddruzyny") int iddruzyny,
			@FormParam("imie") String imie,
			@FormParam("nazwisko") String nazwisko,
			@FormParam("dataurodzenia") String dataurodzenia,
			@FormParam("pozycja") String pozycja,
			@FormParam("preferowananoga") String preferowananoga,
			@FormParam("login") String login,
			@FormParam("haslo") String haslo,
			@Context HttpServletResponse servletResponse) {

		Player p = new Player(imie, nazwisko, dataurodzenia, login, haslo, iddruzyny, pozycja, preferowananoga);
		int rows = playerDao.createPlayer(p);

		if(rows ==1) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}

}
