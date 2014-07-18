package novare.com.hk.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;

import novare.com.hk.model.Allocation;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AllocViewExtractor implements ResultSetExtractor<Allocation>  {
	

	public Allocation extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		Allocation allocationView = new Allocation();
		allocationView.setId(resultSet.getInt(1));
		allocationView.setEmployee_name(resultSet.getString(2));
		allocationView.setProject(resultSet.getString(3));
		allocationView.setPercent(resultSet.getInt(4));
		allocationView.setStart_date(resultSet.getString(5));
		allocationView.setEnd_date(resultSet.getString(6));
		return allocationView;
	}
}