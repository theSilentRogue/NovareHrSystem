package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Integer>{
	
	@Query("SELECT a FROM Allocation a WHERE project.project_name = ?1")
	public List<Allocation> filterAllocation(String filterStat);
	
	@Query("SELECT a FROM Allocation a WHERE project.project_name = ?1 OR employee.fname = ?1 OR employee.lname = ?1")
	public List<Allocation> searchAllocation(String search_param);
	
	/*@Query("SELECT a FROM Allocation a"
			+ " JOIN a.project AS proj"
			+ " JOIN a.employee AS emp"
			+ " WHERE a.start_date = ?1")*/
	@Query("SELECT a.project, count(a.employee) AS plan,  "
			+ "sum(a.percent) AS percent, sum(e.cost) AS cost FROM Employee e, Allocation a "
			+ "JOIN a.project AS proj "
			+ "JOIN a.employee AS emp "
			+ "WHERE a.employee = a.employee AND a.project = a.project AND a.percent = a.percent AND e.cost = e.cost AND DATE(a.start_date) BETWEEN ?1 AND ?2 "
			+ "GROUP BY a.project "
			+ "HAVING count(*) > 0")
	public List<Allocation> getMonthAllocation(Date date_start, Date date_end);

}
