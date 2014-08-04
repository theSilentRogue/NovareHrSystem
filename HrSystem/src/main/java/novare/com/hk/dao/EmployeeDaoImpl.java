package novare.com.hk.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import novare.com.hk.jdbc.EmployeeRowMapper;
import novare.com.hk.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	DataSource dataSource;
	
	public void insertData(Employee employee) {
		
		String sql = "INSERT INTO employee "
				+ "(first_name, last_name, department, status, start_date, date_resigned, "
				+ "position, cost) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if (employee.getDate_resigned().equals(""))
		{
		jdbcTemplate.update(
				sql,
				new Object[] { employee.getFname(), employee.getLname(), employee.getDepartment(),
								employee.getStatus(), employee.getStart_date(), null,
								employee.getPosition(), employee.getCost()}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { employee.getFname(), employee.getLname(), employee.getDepartment(),
									employee.getStatus(), employee.getStart_date(), employee.getDate_resigned(),
									employee.getPosition(), employee.getCost()}
							   );
		}
	}
	

	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();

		String sql = "select * from employee";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employeeList;
	}
	

	public void updateData(Employee employee) {
		String sql = "UPDATE employee set first_name = ?,last_name = ?,"
					+ " department = ?, status = ?, start_date = ?, date_resigned = ?,"
					+ " position = ?, cost = ? WHERE id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		if (employee.getDate_resigned().equals(""))
		{
		jdbcTemplate.update(
				sql,
				new Object[] { employee.getFname(), employee.getLname(), employee.getDepartment(),
								employee.getStatus(), employee.getStart_date(), null,
								employee.getPosition(), employee.getCost(), employee.getId()}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { employee.getFname(), employee.getLname(), employee.getDepartment(),
									employee.getStatus(), employee.getStart_date(), employee.getDate_resigned(),
									employee.getPosition(), employee.getCost(), employee.getId()}
							   );
		}
	}
	

	public void deleteData(String id) {
		String sql = "delete from employee where id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	
	public Employee getEmployee(String id) {
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = "select * from employee where id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employeeList.get(0);
	}

	public List<Employee> searchEmployee(String search_param) {

		List<Employee> employeeList = new ArrayList<Employee>();
		
		String sql = "select * from employee where match(first_name,last_name,department,status,position) against('"+search_param+"*' IN BOOLEAN MODE)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//jdbcTemplate.update(sql);
		employeeList = jdbcTemplate.query(sql, new EmployeeRowMapper());
		return employeeList;
	}
	
	public List<Employee> filterEmployee(String filterStat){
		List<Employee> employeeList = new ArrayList<Employee>();
		
		String sql = "select * from employee where status = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		employeeList = jdbcTemplate.query(sql, new Object[] { filterStat }, new EmployeeRowMapper());
		return employeeList;
	}
}
