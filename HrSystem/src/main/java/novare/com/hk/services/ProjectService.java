package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Project;

public interface ProjectService {
	public void insertData(Project project);

	public List<Project> getProjectList();

	public void updateData(Project project);

	public void deleteData(int id);

	public Project getProject(int id);
	
	public List<Project> searchProject(String searchquery);
	
	public List<Project> filterProject(String project_name);
	
	public List<Project> getReport(Date start_date, Date end_date);
}
