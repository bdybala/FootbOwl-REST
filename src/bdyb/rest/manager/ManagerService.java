package bdyb.rest.manager;

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

@Path("/ManagerService")
public class ManagerService {
	
	ManagerDao managerDao = new ManagerDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String LOGIN_TAKEN_RESULT="<result>login taken</result>";

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
	public String createManager(
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("birthday") String dataurodzenia,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@Context HttpServletResponse servletResponse) {
		
		if(managerDao.selectLogin(login) == 1) 
			return LOGIN_TAKEN_RESULT;
		
		Manager manager = new Manager(imie, nazwisko, dataurodzenia, login, haslo);
		managerDao.createManager(manager);
		
		if(managerDao.selectLogin(login) == 1)
			return SUCCESS_RESULT;
		else 
			return FAILURE_RESULT;
	}
	
	@POST
	@Path("/manager/{userid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateManager(
			@PathParam("userid") String userid,
			@FormParam("first_name") String imie,
			@FormParam("last_name") String nazwisko,
			@FormParam("login") String login,
			@FormParam("pass") String haslo,
			@FormParam("photo") String photo,
			@FormParam("is_logged") Integer islogged) {
		return managerDao.updateManager(userid, imie, nazwisko, login, haslo, photo, islogged);
	}
	
	
}
