package bdyb.rest.training;

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

@Path("/TrainingService")
public class TrainingService {

	TrainingDao trainingDao = new TrainingDao();
	private static final String SUCCESS_RESULT="<result>success</result>";
	
	@GET
	@Path("/trainings")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public List<Training> getTrainings() {
		List<Training> allTrainings = trainingDao.getAllTrainings();
		return allTrainings;
	}
	
	@POST
	@Path("/trainings/between")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Training> getTrainingsBetween(
			@FormParam("from") String from,
			@FormParam("to") String to) {
		List<Training> allTrainings = trainingDao.getAllTrainings(from, to);
		return allTrainings;
	}
	
	@PUT
	@Path("/trainings")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createTraining(
			@FormParam("training_desc") String training_desc,
			@FormParam("training_date") String training_date,
			@FormParam("training_place") String training_place) {
		
		Training t = new Training(training_desc, training_date, training_place);
		trainingDao.createTraining(t);
		return SUCCESS_RESULT;
	}
	
	@POST
	@Path("/training/{trainingid}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateTraining(
			@PathParam("trainingid") int training_id,
			@FormParam("training_desc") String desc,
			@FormParam("training_date") String date,
			@FormParam("training_place") String place) {
		return trainingDao.updateTraining(training_id, desc, date, place);
	}
}
