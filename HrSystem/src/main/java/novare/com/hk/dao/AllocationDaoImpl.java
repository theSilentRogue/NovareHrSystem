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
				+ "VALUES(?, ?, ?, ?, ?)";
		
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
				new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
								allocation.getStart_date(), null}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
									allocation.getStart_date(), allocation.getEnd_date()
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
		String sql = "UPDATE allocation set employee_id = ?, project_id = ?,"
					+ " percent = ?, start_date = ?, end_date = ? "
					+ "WHERE id = ?";
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
				new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
								allocation.getStart_date(), null, allocation.getId()}
						   );
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { allocation.getEmployee_id(), allocation.getProject_id(), allocation.getPercent(),
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
		String sql = "select * from allocation where id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		allocationList = jdbcTemplate.query(sql, new AllocationRowMapper());
		return allocationList.get(0);
	}
		
		public List<Allocation> getViewAlloc() {
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
