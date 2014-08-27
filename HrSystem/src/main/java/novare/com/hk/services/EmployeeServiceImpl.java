package novare.com.hk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novare.com.hk.model.Employee;
import novare.com.hk.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public void insertData(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Transactional
	public List<Employee> getEmployeeList() {
		return employeeRepository.findAll();
	}

	@Transactional
	public void updateData(Employee employee) {
		employeeRepository.save(employee);
	}

	@Transactional
	public void deleteData(int id) {
		employeeRepository.delete(id);
	}

	@Transactional
	public Employee getEmployee(int id) {
		return employeeRepository.findOne(id);
	}

	@Transactional
	public List<Employee> searchEmployee(String search_param) {
		return employeeRepository.searchEmployee(search_param);
	}
	
	@Transactional
	public List<Employee> filterEmployee(String filterStat){
		return employeeRepository.filterEmployee(filterStat);
	}
}
