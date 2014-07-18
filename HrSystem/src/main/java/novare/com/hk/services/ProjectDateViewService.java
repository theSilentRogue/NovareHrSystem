package novare.com.hk.services;

import java.util.List;

import novare.com.hk.model.Allocation;

public interface ProjectDateViewService {
	public List<Allocation> getProjectEndList();
	
	public List<Allocation> getProjectStartList();
}
