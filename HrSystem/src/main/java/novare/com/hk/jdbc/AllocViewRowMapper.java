package novare.com.hk.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Allocation;

import org.springframework.jdbc.core.RowMapper;

public class AllocViewRowMapper implements RowMapper<Allocation> {

	public Allocation mapRow(ResultSet resultSet, int line) throws SQLException {
		AllocViewExtractor allocExtractor = new AllocViewExtractor();
		return allocExtractor.extractData(resultSet);
		
	}
}
