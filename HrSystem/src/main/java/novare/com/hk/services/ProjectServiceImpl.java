package novare.com.hk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import novare.com.hk.dao.ProjectDao;
import novare.com.hk.model.Project;

public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectDao projectdao;
	

	public void insertData(Project project) {
		projectdao.insertData(project);
		
	}

	public List<Project> getProjectList() {
		return projectdao.getProjectList();
	}

	public void updateData(Project project) {
		projectdao.updateData(project);
	}

	public void deleteData(String id) {
		projectdao.deleteData(id);
	}

	public Project getProject(String id) {
		return projectdao.getProject(id);
	}

	public List<Project> searchProject(String searchquery){
		return projectdao.searchProject(searchquery);
	}
	
	public List<Project> filterProject(String project_name){
		return projectdao.filterProject(project_name);
	}
}
