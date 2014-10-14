package novare.com.hk.repository;

import java.util.List;

import novare.com.hk.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT e FROM Employee e WHERE first_name LIKE CONCAT('%',:searchEmp,'%') OR last_name LIKE CONCAT('%',:searchEmp,'%') OR department LIKE CONCAT('%',:searchEmp,'%') OR status LIKE CONCAT('%',:searchEmp,'%')")
	public List<Employee> searchEmployee(@Param("searchEmp") String search_param);
	
	@Query("SELECT e FROM Employee e WHERE status = ?1")
	public List<Employee> filterEmployee(String filterStat);
	
	public List<Employee> findByFnameContainingOrLnameContaining(String search_param, String search_param1);

}
