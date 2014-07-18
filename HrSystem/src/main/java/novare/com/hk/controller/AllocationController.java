package novare.com.hk.controller;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import novare.com.hk.model.Allocation;
import novare.com.hk.services.AllocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllocationController {
	@Autowired
	AllocationService allocationService;
	
	@RequestMapping (value = "/addAllocation", method = RequestMethod.GET)
	public ModelAndView addAllocation(@ModelAttribute Allocation allocation){

		System.out.println("Viewing pre-adding page. . .");
		
		return new ModelAndView("addAllocation");  
	}
	
	@RequestMapping("/viewAllocationList")
	public ModelAndView getAllocationList() {
		List<Allocation> allocationList = allocationService.getViewAlloc();
		return new ModelAndView("viewAllocationList", "allocationList", allocationList);
	}
	
	@RequestMapping("/editAllocation")
	public ModelAndView editAllocation(@RequestParam String id, 
			@ModelAttribute Allocation allocation) {
		
		allocation = allocationService.getAllocation(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allocation", allocation);
		
		return new ModelAndView ("editAllocation", "map", map);
	}
	
	@RequestMapping("/updateAllocation")
	public String updateAllocation(@ModelAttribute Allocation allocation){
		allocationService.updateData(allocation);
		return "redirect:/viewAllocationList";
	}
	
	
	@RequestMapping("/deleteAllocation")
	public String deleteAllocation(@RequestParam String id){
		System.out.println("id = " + id);
		allocationService.deleteData(id);
		return "redirect:/viewAllocationList";
	}
	
	
	@RequestMapping("/insertAllocation")
	public String insertAllocation(@ModelAttribute Allocation allocation) {
		if (allocation != null){
			allocationService.insertData(allocation);
			System.out.println("Inserted allocation: " + "Employee_id:" +allocation.getEmployee_id() + " Project_id: " + allocation.getProject_id());
			}
		return "redirect:/viewAllocationList";
	}
	
}
