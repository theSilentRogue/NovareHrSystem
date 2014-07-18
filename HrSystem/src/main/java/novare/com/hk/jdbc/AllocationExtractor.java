package novare.com.hk.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Allocation;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AllocationExtractor implements ResultSetExtractor<Allocation> {
	
	public Allocation extractData(ResultSet resultSet) throws SQLException, DataAccessException{
		
		Allocation allocation = new Allocation();
		allocation.setId(resultSet.getInt(1));
		allocation.setEmployee_id(resultSet.getInt(2));
		allocation.setProject_id(resultSet.getInt(3));
		allocation.setPercent(resultSet.getInt(4));
		allocation.setStart_date(resultSet.getString(5));
		allocation.setEnd_date(resultSet.getString(6));
		
		return allocation;
	}

}
