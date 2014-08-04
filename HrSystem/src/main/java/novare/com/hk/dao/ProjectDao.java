package novare.com.hk.dao;

import java.util.List;

import novare.com.hk.model.Project;

public interface ProjectDao {
	public void insertData(Project project);

	public List<Project> getProjectList();

	public void updateData(Project project);

	public void deleteData(String id);

	public Project getProject(String id);
	
	public List<Project> searchProject(String searchquery);
	
	public List<Project> filterProject(String project_name);
}
