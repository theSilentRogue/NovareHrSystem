package novare.com.hk.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import novare.com.hk.jdbc.AllocViewRowMapper;
import novare.com.hk.jdbc.AllocationRowMapper;
import novare.com.hk.model.Allocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AllocationDaoImpl implements AllocationDao{

	@Autowired
	DataSource dataSource;
	
	public void insertData(Allocation allocation) {
		
		String sql = "INSERT INTO allocation "
				+ "(employee_id, project_id, percent, start_date, end_date) "
				+ "VALUES( " + "(SELECT id from employee where "
				+ "CONCAT(employee.first_name, \" \", last_name) = ?), " +
				"(SELECT id from projects WHERE projects.project_name = ?), " +
				"?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		/*if (allocation.getStart_date().equals(null)){
			
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
									null, allocation.getEnd_date()
									}
							   );
			}
			else{
				jdbcTemplate.update(
						sql,
						new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
										allocation.getStart_date(), allocation.getEnd_date()
										}
								   );
			}*/
		
		if (allocation.getEnd_date().equals("")){
			
		jdbcTemplate.update(
				sql,
				new Object[] { allocation.getEmployee_id(), allocation.getProject_id(),
								allocation.getPercent(), allocation.getStart_date(), null}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), 
									allocation.getPercent(), allocation.getStart_date(), 
									allocation.getEnd_date()
									}
							   );
		}
	}
	

	public List<Allocation> getAllocationList() {
		List<Allocation> allocationList = new ArrayList<Allocation>();

		String sql = "select * from allocation";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		allocationList = jdbcTemplate.query(sql, new AllocationRowMapper());
		return allocationList;
	}
	

	public void updateData(Allocation allocation) {
		String sql = "UPDATE allocation set " + "project_id = (SELECT projects.id FROM projects " + 
					"WHERE projects.project_name = ?), percent = ?, " + "start_date = ?, end_date = ? "
					+ "WHERE allocation.id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		/*if (allocation.getStart_date().equals(null)){
			
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
									null, allocation.getEnd_date(), allocation.getId()
									}
							   );
			}
			else{
				jdbcTemplate.update(
						sql,
						new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
										allocation.getStart_date(), allocation.getEnd_date(), allocation.getId()
										}
								   );
			}*/
		
		if (allocation.getEnd_date().equals("")){
			
		jdbcTemplate.update(
				sql,
				new Object[] { allocation.getProject(), allocation.getPercent(),
								allocation.getStart_date(), null, allocation.getId()}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getProject(), allocation.getPercent(),
									allocation.getStart_date(), allocation.getEnd_date(), allocation.getId()
									}
							   );
		}

	}
	

	public void deleteData(String id) {
		String sql = "delete from allocation where id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	
	public Allocation getAllocation(String id) {
		List<Allocation> allocationList = new ArrayList<Allocation>();
		String sql = "SELECT allocation.id, allocation.employee_id, allocation.project_id, " + 
		"allocation.percent, allocation.start_date, allocation.end_date, "+  
		"CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name', " +  
		"projects.project_name " + "FROM employee INNER JOIN allocation on allocation.employee_id " +
		"= employee.id INNER JOIN projects on projects.id = allocation.project_id " +
		"WHERE allocation.id = " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		allocationList = jdbcTemplate.query(sql, new AllocationRowMapper());
		return allocationList.get(0);
	}
		
		public List<Allocation> getViewAlloc() {
			List<Allocation> allocationList = new ArrayList<Allocation>();

			String sql = "SELECT allocation.id,  allocation.employee_id, allocation.project_id, "+
					"allocation.percent, allocation.start_date, allocation.end_date, "+
					"CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name'," +
					"projects.project_name " +
					"FROM employee INNER JOIN allocation on allocation.employee_id = employee.id "+
					"INNER JOIN projects on projects.id = allocation.project_id;";

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			allocationList = jdbcTemplate.query(sql, new AllocViewRowMapper());
			return allocationList;
	}
		
		public List<Allocation> filterAllocation(String project_name){
			List<Allocation> projectList = new ArrayList<Allocation>();
			String sql = "SELECT allocation.id, allocation.employee_id, allocation.project_id, " +
					"allocation.percent, allocation.start_date, allocation.end_date, " +
					"CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name', " +
					"projects.project_name " +
					"FROM employee INNER JOIN allocation on allocation.employee_id = employee.id " +
					"INNER JOIN projects on projects.id = allocation.project_id " +
					"WHERE project_id = " +
					"(SELECT id FROM projects where project_name = ?)";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			projectList = jdbcTemplate.query(sql, new Object[] { project_name },
					new AllocationRowMapper());
			return projectList;
		}
		
		public List<Allocation> searchAllocation(String searchquery){
			List<Allocation> allocationList = new ArrayList<Allocation>();
			String sql = "SELECT allocation.id, allocation.employee_id, allocation.project_id, " +
					"allocation.percent, allocation.start_date, allocation.end_date, " +
					"CONCAT(employee.first_name, \" \", employee.last_name) AS 'Employee Name', " +
					"projects.project_name " +
					"FROM employee INNER JOIN allocation on allocation.employee_id = employee.id " +
					"INNER JOIN projects on projects.id = allocation.project_id " +
					"WHERE match (first_name, last_name, department, status, position) against ('" +
					searchquery +
					"*' in boolean mode) or match (client, project_name) against" +
					"('" + searchquery + "*' in boolean mode)";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			allocationList = jdbcTemplate.query(sql, new AllocationRowMapper());
			return allocationList;
		}
}
