package novare.com.hk.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import novare.com.hk.jdbc.AllocViewRowMapper;
import novare.com.hk.model.Allocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProjectDateViewDaoImpl implements ProjectDateViewDao{

	@Autowired
	DataSource dataSource;
	
	public List<Allocation> getProjectEndList() {
		List<Allocation> allocationList = new ArrayList<Allocation>();

		String sql = "SELECT allocation.id,  CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name',"+
				"projects.project_name, allocation.percent, allocation.start_date, allocation.end_date "+
				"FROM employee INNER JOIN allocation on allocation.employee_id = employee.id "+
				"INNER JOIN projects on projects.id = allocation.project_id;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		allocationList = jdbcTemplate.query(sql, new AllocViewRowMapper());
		return allocationList;
}
	
	public List<Allocation> getProjectStartList() {
		List<Allocation> allocationList = new ArrayList<Allocation>();

		String sql = "SELECT allocation.id,  CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name',"+
				"projects.project_name, allocation.percent, allocation.start_date, allocation.end_date "+
				"FROM employee INNER JOIN allocation on allocation.employee_id = employee.id "+
				"INNER JOIN projects on projects.id = allocation.project_id;";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		allocationList = jdbcTemplate.query(sql, new AllocViewRowMapper());
		return allocationList;
}
}
