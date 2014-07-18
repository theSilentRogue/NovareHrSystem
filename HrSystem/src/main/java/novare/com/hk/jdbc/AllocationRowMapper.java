package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Allocation;

import org.springframework.jdbc.core.RowMapper;


public class AllocationRowMapper implements RowMapper<Allocation> {
	
	public Allocation mapRow(ResultSet resultSet, int line) throws SQLException{
		AllocationExtractor allocationExtractor = new AllocationExtractor();
		return allocationExtractor.extractData(resultSet);
	}

}
