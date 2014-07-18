package novare.com.hk.dao;

import java.util.List;

import novare.com.hk.model.Allocation;

public interface AllocationDao {
	
	public void insertData(Allocation allocation);

	public List<Allocation> getAllocationList();

	public void updateData(Allocation allocation);

	public void deleteData(String id);

	public Allocation getAllocation(String id);
	
	public List<Allocation> getViewAlloc();
}
