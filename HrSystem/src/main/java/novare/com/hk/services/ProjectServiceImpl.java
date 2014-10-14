package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novare.com.hk.repository.ProjectRepository;
import novare.com.hk.model.Project;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Transactional
	public void insertData(Project project) {
		projectRepository.save(project);
	}

	@Transactional
	public List<Project> getProjectList() {
		return projectRepository.findAll();
	}

	@Transactional
	public void updateData(Project project) {
		projectRepository.save(project);
	}

	@Transactional
	public void deleteData(int id) {
		projectRepository.delete(id);
	}

	@Transactional
	public Project getProject(int id) {
		return projectRepository.findOne(id);
	}

	@Transactional
	public List<Project> searchProject(String searchquery){
		return projectRepository.searchProject(searchquery);
	}
	
	@Transactional
	public List<Project> filterProject(String project_name){
		return projectRepository.filterProject(project_name);
	}
	
	@Transactional
	public List<Project> getReport(Date start_date, Date end_date){
		if(start_date != null && end_date != null)
		{
			return projectRepository.generateReport(start_date, end_date);	
		}else if (start_date !=null && end_date == null)
		{
			return projectRepository.generateReport(start_date);
		}
		else
		{
			return projectRepository.findAll();
		}
	}
}
