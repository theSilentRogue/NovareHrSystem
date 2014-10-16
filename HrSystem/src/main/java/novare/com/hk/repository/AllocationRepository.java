package novare.com.hk.repository;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AllocationRepository extends JpaRepository<Allocation, Integer>{
	
	@Query("SELECT a FROM Allocation a WHERE project.project_name LIKE ?1")
	public List<Allocation> filterAllocation(String filterStat);
	
	@Query("SELECT a FROM Allocation a WHERE LOWER(project.project_name) LIKE ?1 OR LOWER(employee.fname) LIKE ?1 OR LOWER(employee.lname) LIKE ?1")
	public List<Allocation> searchAllocation(String search_param);
	
	/*@Query("SELECT a FROM Allocation a"
			+ " JOIN a.project AS proj"
			+ " JOIN a.employee AS emp"
			+ " WHERE a.start_date = ?1")*/
			/*+ " GROUP BY a.start_date"
			+ " HAVING count(*) > 0")*/
	/*@Query("SELECT p.project_name, concat(e.fname, \' \', e.lname), "
			+ "sum(e.cost) AS cost, a.start_date, a.end_date "
			+ "FROM Employee e, Allocation a, Project p "
			+ "JOIN a.project AS proj "
			+ "JOIN a.employee AS emp "
			+ "WHERE a.start_date = ?1 AND concat(e.fname, \' \', e.lname) = concat(e.first_name, \' \', e.last_name) AND p.project_name = p.project_name AND a.percent = a.percent AND e.cost = e.cost ")*/
	/*@Query(value="SELECT projects.project_name, count((CONCAT(employee.first_name, \" \", employee.last_name))) AS \"Plan\",  sum(allocation.percent)/100 AS \"Allocation\", sum(employee.cost) * (sum(allocation.percent)/100) AS \"Daily Cost\", allocation.start_date, allocation.end_date "
				 +	"FROM employee "
				 +	"INNER JOIN allocation on allocation.employee_id = employee.id "
				 +	"INNER JOIN projects on projects.id = allocation.project_id "
				 +	"WHERE allocation.start_date BETWEEN date(:start_date) AND date(:end_date) OR allocation.start_date = :start_date AND concat(employee.first_name, \" \", employee.last_name) = concat(employee.first_name, \" \", employee.last_name) AND projects.project_name = projects.project_name AND allocation.percent = allocation.percent AND employee.cost = employee.cost "
				 +	"GROUP BY projects.project_name "
				 +	"HAVING COUNT(*) > 0", nativeQuery= true)*/
	@Query("SELECT a FROM Allocation a"
			+ " JOIN a.project AS proj"
			+ " JOIN a.employee AS emp"
			+ " WHERE a.start_date BETWEEN :start_date AND :end_date OR a.start_date = :start_date"
			+ " GROUP BY a.start_date"
			+ " ORDER BY MONTH(a.start_date)")
	public List<Allocation> dateSearch(@Param("start_date")Date start_date,@Param("end_date")Date end_date);
	
	/*@Query("SELECT a.project, count(a.employee) AS plan,  "
			+ "sum(a.percent) AS percent, sum(e.cost), a.start_date, a.end_date AS cost FROM Employee e, Allocation a "
			+ "JOIN a.project AS proj "
			+ "JOIN a.employee AS emp "
			+ "WHERE a.employee = a.employee AND a.project = a.project AND a.percent = a.percent AND e.cost = e.cost AND DATE(a.start_date) BETWEEN ?1 AND ?2 "
			+ "GROUP BY a.project "
			+ "HAVING count(*) > 0")*/
	@Query("SELECT a FROM Allocation a"
			+ " JOIN a.project AS proj"
			+ " JOIN a.employee AS emp"
			+ " WHERE a.start_date BETWEEN :start_date AND :end_date OR a.start_date = :start_date"
			+ " GROUP BY a.start_date"
			+ " ORDER BY MONTH(a.start_date)")
	public List<Allocation> reportMonth(@Param("start_date")Date start_date, @Param("end_date")Date end_date);
	
	@Query(value="SELECT p.project_name AS project_name, "
			+ "alloc.start_date, " 
			+ "alloc.end_date, "  
			+ "(alloc.percent/100.0000), " 
			+ "CONCAT(e.fname, \' \', e.lname) AS employee_name "
			+ "from Allocation alloc " 
			+ "INNER JOIN alloc.project as p " 
			+ "INNER JOIN alloc.employee as e "  
			+ "GROUP BY MONTH(alloc.start_date)")
	public List<Object[]> defaultAlloc();

}
