package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	@Query("SELECT p FROM Project p WHERE project_name LIKE CONCAT('%',:searchParam,'%') OR client LIKE CONCAT('%',:searchParam,'%')")
	public List<Project> searchProject(@Param("searchParam") String search_param);
	
	@Query("SELECT p FROM Project p WHERE project_name = ?1")
	public List<Project> filterProject(String filterStat);
	
	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ " WHERE p.start_date BETWEEN :start_date AND :end_date"
			+ " GROUP BY p.start_date"
			+ " ORDER BY MONTH(p.start_date)")
	public List<Project> generateReport(@Param("start_date")Date start_date,@Param("end_date")Date end_date);

	@Query("SELECT DISTINCT p FROM Project p"
			+ " JOIN p.allocations AS alloc"
			+ " WHERE p.start_date >= :start_date")
	public List<Project> generateReport(@Param("start_date")Date start_date);
}
