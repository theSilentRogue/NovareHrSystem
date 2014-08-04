package novare.com.hk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import novare.com.hk.dao.EmployeeDao;
import novare.com.hk.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeedao;
	
	public void insertData(Employee employee) {
		employeedao.insertData(employee);
		
	}

	public List<Employee> getEmployeeList() {
		return employeedao.getEmployeeList();
	}

	public void updateData(Employee employee) {
		employeedao.updateData(employee);
	}

	public void deleteData(String id) {
		employeedao.deleteData(id);
	}

	public Employee getEmployee(String id) {
		return employeedao.getEmployee(id);
	}

	public List<Employee> searchEmployee(String search_param) {
		return employeedao.searchEmployee(search_param);
	}
	
	public List<Employee> filterEmployee(String filterStat){
		return employeedao.filterEmployee(filterStat);
	}
}
