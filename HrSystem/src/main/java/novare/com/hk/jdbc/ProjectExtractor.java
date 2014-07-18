package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Project;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ProjectExtractor implements ResultSetExtractor<Project> {

	public Project extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		
		Project project = new Project();
		project.setId(resultSet.getInt(1));
		project.setClient(resultSet.getString(2));
		project.setProject_name(resultSet.getString(3));
		project.setStart_date(resultSet.getString(4));
		project.setEnd_date(resultSet.getString(5));
		
		return project;
	}

}
