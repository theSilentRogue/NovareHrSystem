package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	@Query("SELECT p FROM Project p WHERE project_name = ?1 OR client = ?1")
	public List<Project> searchProject(String search_param);
	
	@Query("SELECT p FROM Project p WHERE project_name = ?1")
	public List<Project> filterProject(String filterStat);
	
	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ "  WHERE alloc.start_date BETWEEN ?1 AND ?2")
	public List<Project> generateReport(Date start_date, Date end_date);

}
