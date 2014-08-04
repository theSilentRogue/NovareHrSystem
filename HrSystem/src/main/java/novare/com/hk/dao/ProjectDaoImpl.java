package novare.com.hk.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import novare.com.hk.jdbc.ProjectRowMapper;
import novare.com.hk.model.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProjectDaoImpl implements ProjectDao {
	@Autowired
	DataSource dataSource;
	
	public void insertData(Project project) {
		
		String sql = "INSERT INTO projects "
				+ "(client, project_name, start_date, end_date) "
				+ "VALUES(?, ?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		if(project.getEnd_date().equals("")){
			jdbcTemplate.update(
					sql,
					new Object[] { project.getClient(), project.getProject_name(), project.getStart_date(),
							null});
		}
		else{
			jdbcTemplate.update(
					sql,
					new Object[] { project.getClient(), project.getProject_name(), project.getStart_date(),
									project.getEnd_date()}
								);
		}
		
	}
	

	public List<Project> getProjectList() {
		List<Project> projectList = new ArrayList<Project>();

		String sql = "select * from projects";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		projectList = jdbcTemplate.query(sql, new ProjectRowMapper());
		return projectList;
	}
	

	public void updateData(Project project) {
		String sql = "UPDATE projects set client = ?,project_name = ?,"
					+ " start_date = ?, end_date = ? "
					+ " WHERE id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		if(project.getEnd_date().equals("")){
			jdbcTemplate.update(
					sql,
					new Object[] { project.getClient(), project.getProject_name(), project.getStart_date(),
							null, project.getId()});
		}
		else{
		jdbcTemplate.update(
				sql,
				new Object[] { project.getClient(), project.getProject_name(), project.getStart_date(),
						project.getEnd_date(), project.getId()});
		}
	}
	

	public void deleteData(String id) {
		String sql = "delete from projects where id=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql);
	}

	
	public Project getProject(String id) {
		List<Project> projectList = new ArrayList<Project>();
		String sql = "select * from projects where id= " + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		projectList = jdbcTemplate.query(sql, new ProjectRowMapper());
		return projectList.get(0);
	}
	
	public List<Project> searchProject(String searchquery){
		List<Project> projectList = new ArrayList<Project>();
		String sql = "SELECT * FROM projects WHERE MATCH(client, project_name) against('"+searchquery+"*' IN BOOLEAN MODE)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		projectList = jdbcTemplate.query(sql, new ProjectRowMapper());
		return projectList;
	}
	
	public List<Project> filterProject(String project_name){
		List<Project> projectList = new ArrayList<Project>();
		String sql = "SELECT * FROM projects WHERE project_name = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		projectList = jdbcTemplate.query(sql, new Object[] { project_name }, new ProjectRowMapper());
		return projectList;
	}
}
