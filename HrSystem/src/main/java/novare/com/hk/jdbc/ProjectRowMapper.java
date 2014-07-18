package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Project;

import org.springframework.jdbc.core.RowMapper;

public class ProjectRowMapper implements RowMapper<Project> {

	public Project mapRow(ResultSet resultSet, int line) throws SQLException {
		
		ProjectExtractor projectExtractor = new ProjectExtractor();
		return projectExtractor.extractData(resultSet);
	}

}
