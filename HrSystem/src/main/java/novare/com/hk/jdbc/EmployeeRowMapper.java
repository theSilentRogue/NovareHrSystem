package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Employee;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	public Employee mapRow(ResultSet resultSet, int line) throws SQLException {
		EmployeeExtractor employeeExtractor = new EmployeeExtractor();
		return employeeExtractor.extractData(resultSet);
		
	}

}
