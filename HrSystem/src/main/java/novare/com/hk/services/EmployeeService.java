package novare.com.hk.services;

import java.util.List;

import novare.com.hk.model.Employee;

public interface EmployeeService {
	
	public void insertData(Employee employee);

	public List<Employee> getEmployeeList();

	public void updateData(Employee employee);

	public void deleteData(String id);

	public Employee getEmployee(String id);

}
