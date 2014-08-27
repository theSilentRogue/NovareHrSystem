package novare.com.hk.services;

import java.util.List;

import novare.com.hk.model.Employee;

public interface EmployeeService {
	
	public void insertData(Employee employee);

	public List<Employee> getEmployeeList();

	public void updateData(Employee employee);

	public void deleteData(int id);

	public Employee getEmployee(int id);

	public List<Employee> searchEmployee(String search_param);
	
	public List<Employee> filterEmployee(String filterStat);
}
