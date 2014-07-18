package novare.com.hk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import novare.com.hk.dao.ProjectDateViewDao;
import novare.com.hk.model.Allocation;

public class ProjectDateViewServiceImpl implements ProjectDateViewService {
	
	@Autowired
	ProjectDateViewDao projectdateviewdao;
	
	public List<Allocation> getProjectEndList() {
		return projectdateviewdao.getProjectEndList();
	}

	public List<Allocation> getProjectStartList() {
		return projectdateviewdao.getProjectStartList();
	}
}
