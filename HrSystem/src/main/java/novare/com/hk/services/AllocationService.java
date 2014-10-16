package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;

public interface AllocationService {
	
	public void insertData(Allocation allocation);

	public List<Allocation> getAllocationList();

	public void updateData(Allocation allocation);

	public void deleteData(int id);

	public Allocation getAllocation(int id);

	public List<Allocation> getViewAlloc();
	
	public List<Allocation> filterAllocation(String project_name);
	
	public List<Allocation> searchAllocation(String searchquery);
	
	public List<Allocation> reportMonth(Date start_date, Date end_date);
	
	public List<Allocation> dateSearch(Date start_date, Date end_date);
	
	public List<Object[]> defaultAlloc();
}
