package novare.com.hk.repository;

import java.util.List;

import novare.com.hk.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT e FROM Employee e WHERE first_name = ?1 OR last_name = ?1 OR department = ?1 OR status = ?1")
	public List<Employee> searchEmployee(String search_param);
	
	@Query("SELECT e FROM Employee e WHERE status = ?1")
	public List<Employee> filterEmployee(String filterStat);
	
	public List<Employee> findByFnameContainingOrLnameContaining(String search_param, String search_param1);

}
