package bdyb.rest.news;

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


@Path("/NewsService")
public class NewsService {
	
	NewsDao newsDao = new NewsDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	
	@GET
	@Path("/news")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<News> getNews() {
		List<News> allNews= newsDao.getAllNews();
		return allNews;
	}
	
	@GET
	@Path("/news/{newsid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public News getNews(
			@PathParam("newsid") int newsid) {
		News news= newsDao.getNews(newsid);
		return news;
	}
	
	@GET
	@Path("/teamNews/{teamid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<News> getTeamNews(
			@PathParam("teamid") int teamid) {
		List<News> allNews= newsDao.getTeamNews(teamid);
		return allNews;
	}
	
	@PUT
	@Path("/news")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createNews(
			@FormParam("team_id") int team_id,
			@FormParam("acc_id") int acc_id,
			@FormParam("title") String title,
			@FormParam("body") String body){
		
		News n = new News(team_id, acc_id, title, body);
		newsDao.createNews(n);
		return SUCCESS_RESULT;
	}
	
	@POST
	@Path("/news/{newsid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updatePlayer(
			@PathParam("newsid") int news_id,
			@FormParam("team_id") Integer team_id,
			@FormParam("acc_id") Integer acc_id,
			@FormParam("title") String title,
			@FormParam("body") String body,
			@FormParam("photo") String photo) {
		return newsDao.updateNews(news_id, team_id, acc_id, title, body, photo);
	}
	
}
