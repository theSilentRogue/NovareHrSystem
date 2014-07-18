package novare.com.hk.dao;

import java.util.List;

import novare.com.hk.model.Employee;

public interface EmployeeDao {
	
	public void insertData(Employee employee);

	public List<Employee> getEmployeeList();

	public void updateData(Employee employee);

	public void deleteData(String id);

	public Employee getEmployee(String id);


}
