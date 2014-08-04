package novare.com.hk.services;

import java.util.List;

import novare.com.hk.dao.AllocationDao;
import novare.com.hk.model.Allocation;

import org.springframework.beans.factory.annotation.Autowired;

public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	AllocationDao allocationdao;
	
	public void insertData(Allocation allocation) {
		allocationdao.insertData(allocation);
		
	}

	public List<Allocation> getAllocationList() {
		return allocationdao.getAllocationList();
	}

	public void updateData(Allocation allocation) {
		allocationdao.updateData(allocation);
	}

	public void deleteData(String id) {
		allocationdao.deleteData(id);
	}

	public Allocation getAllocation(String id) {
		return allocationdao.getAllocation(id);
	}
	
	public List<Allocation> getViewAlloc() {
		return allocationdao.getViewAlloc();
	}
	
	public List<Allocation> filterAllocation(String project_name){
		return allocationdao.filterAllocation(project_name);	
	}
	
	public List<Allocation> searchAllocation(String searchquery){
		return allocationdao.searchAllocation(searchquery);
	}
}
