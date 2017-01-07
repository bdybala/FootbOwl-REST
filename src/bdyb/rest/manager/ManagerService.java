package bdyb.rest.manager;

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

@Path("/ManagerService")
public class ManagerService {
	ManagerDao managerDao = new ManagerDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	@GET
	@Path("/managers")
	@Produces(MediaType.APPLICATION_XML)
	public List<Manager> getManagers() {
		List<Manager> allManagers = managerDao.getAllManagers();
		return allManagers;
	}
	
	@PUT
	@Path("/managers")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createManager(@FormParam("imie") String imie,
			@FormParam("nazwisko") String nazwisko,
			@FormParam("dataurodzenia") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("haslo") String haslo,
			@Context HttpServletResponse servletResponse) {
		
		Manager manager = new Manager(imie, nazwisko, dataurodzenia, login, haslo);
		int rows = managerDao.createManager(manager);
		
		if(rows == 2) 
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}
	
}
