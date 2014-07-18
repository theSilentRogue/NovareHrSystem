package novare.com.hk.dao;

import java.util.List;

import novare.com.hk.model.Allocation;

public interface ProjectDateViewDao {

	public List<Allocation> getProjectEndList();
	
	public List<Allocation> getProjectStartList();
}
