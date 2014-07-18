package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Employee;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EmployeeExtractor implements ResultSetExtractor<Employee> {

	public Employee extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		
		Employee employee = new Employee();
		employee.setId(resultSet.getInt(1));
		employee.setFname(resultSet.getString(2));
		employee.setLname(resultSet.getString(3));
		employee.setDepartment(resultSet.getString(4));
		employee.setStatus(resultSet.getString(5));
		employee.setStart_date(resultSet.getString(6));
		employee.setDate_resigned(resultSet.getString(7));
		employee.setPosition(resultSet.getString(8));
		employee.setCost(resultSet.getDouble(9));
		
		return employee;
	}

}
