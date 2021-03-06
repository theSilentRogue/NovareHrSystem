package novare.com.hk.services;

import java.util.Date;
import java.util.List;

import novare.com.hk.model.Allocation;
import novare.com.hk.repository.AllocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("allocationService")
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	AllocationRepository allocationRepository;
	
	@Transactional
	public void insertData(Allocation allocation) {
		allocationRepository.save(allocation);
	}

	@Transactional
	public List<Allocation> getAllocationList() {
		return allocationRepository.findAll();
	}
	
	@Transactional
	public void updateData(Allocation allocation) {
		allocationRepository.save(allocation);
	}
	
	@Transactional
	public void deleteData(int id) {
		allocationRepository.delete(id);
	}

	@Transactional
	public Allocation getAllocation(int id) {
		return allocationRepository.findOne(id);
	}
	
	@Transactional
	public List<Allocation> getViewAlloc() {
		return allocationRepository.findAll();
	}
	
	@Transactional
	public List<Allocation> filterAllocation(String project_name){
		return allocationRepository.filterAllocation(project_name);	
	}
	
	@Transactional
	public List<Allocation> searchAllocation(String searchquery){
		return allocationRepository.searchAllocation(searchquery);
	}
	
	@Transactional
	public List<Allocation> reportMonth(Date start_date, Date end_date){
		return allocationRepository.reportMonth(start_date, end_date);
	}
	
	@Transactional
	public List<Allocation> dateSearch(Date start_date, Date end_date){
		return allocationRepository.dateSearch(start_date, end_date);
	}
	
	public List<Object[]> defaultAlloc() {
		return allocationRepository.defaultAlloc();
	}
}
