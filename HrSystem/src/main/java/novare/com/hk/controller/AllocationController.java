package novare.com.hk.controller;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import novare.com.hk.model.Allocation;
import novare.com.hk.model.Employee;
import novare.com.hk.model.Project;
import novare.com.hk.services.AllocationService;
import novare.com.hk.services.EmployeeService;
import novare.com.hk.services.ProjectService;

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
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping (value = "/addAllocation", method = RequestMethod.GET)
	public ModelAndView addAllocation(@ModelAttribute Allocation allocation){

		List<Employee> employeeList = employeeService.getEmployeeList();
		List<Project> projectList = projectService.getProjectList();
		
		List<String> emp_names = new ArrayList<String>();
		List<String> proj_names = new ArrayList<String>();
		
		for (Employee e : employeeList)
		{
			emp_names.add(e.getFname()+ " " + e.getLname());
		}
		
		for (Project p : projectList)
		{
			proj_names.add(p.getProject_name());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(emp_names);
		Collections.sort(proj_names);
		
		map.put("employeeList", employeeList);
		map.put("emp_names", emp_names);
		map.put("proj_names", proj_names);
		
		System.out.println("Viewing pre-adding page. . .");
		
		return new ModelAndView("addAllocation", "map", map);  
	}
	
	@RequestMapping("/viewAllocationList")
	public ModelAndView getAllocationList(@ModelAttribute Project project) {
		List<Allocation> allocationList = allocationService.getViewAlloc();
		List<Project> projectList = projectService.getProjectList();
		
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : projectList)
		{
			proj_names.add(p.getProject_name());
		}
		
		Set<String> se = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(se);
		for (Object obj : proj_names)
		{
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(names);
		
		map.put("names", names);
		map.put("allocationList", allocationList);
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping("/editAllocation")
	public ModelAndView editAllocation(@RequestParam String id, 
			@ModelAttribute Allocation allocation) {
		
		allocation = allocationService.getAllocation(id);
		
		List<Project> projectListView = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : projectListView)
		{
			proj_names.add(p.getProject_name());
		}
		
		Set<String> uniqueNames = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(uniqueNames);

		for (Object obj : proj_names)
		{
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allocation", allocation);
		map.put("names", names);
		
		return new ModelAndView ("editAllocation", "map", map);
	}
	
	@RequestMapping("/updateAllocation")
	public String updateAllocation(@ModelAttribute Allocation allocation){
		try{
			if(allocation.getPercent() > 100 || allocation.getPercent() < 0){
				throw new Exception("Invalid Percentage");
			}
			else {
				allocationService.updateData(allocation);
				return "redirect:/viewAllocationList";
			}
		}catch (Exception ex){
			System.out.println("Invalid input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}
	
	
	@RequestMapping("/deleteAllocation")
	public String deleteAllocation(@RequestParam String id){
		System.out.println("id = " + id);
		allocationService.deleteData(id);
		return "redirect:/viewAllocationList";
	}
	
	
	@RequestMapping("/insertAllocation")
	public String insertAllocation(@ModelAttribute Allocation allocation) {
		try{
			if (allocation.getPercent() > 100 || allocation.getPercent() < 0)
			{
				throw new Exception("Invalid percentage");
				}
		if (allocation != null)
		{
			allocationService.insertData(allocation);
			System.out.println("Inserted allocation: " + "Employee_id:" +allocation.getEmployee_id() + " Project_id: " + allocation.getProject_id());
			}
		return "redirect:/viewAllocationList";
		
		} catch (Exception ex)
		{
			System.out.println("Invalid input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}
	
	@RequestMapping("/filterAlloc")
	public ModelAndView filterAllocationList(@RequestParam String project_name, @ModelAttribute Project project){
		List<Allocation> allocationList = allocationService.filterAllocation(project_name);
		List<Project> allocationListView = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : allocationListView){
			proj_names.add(p.getProject_name());
		}
		
		Set<String> uniqueNames = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(uniqueNames);
		for (Object obj : proj_names){
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(names);
		
		map.put("names", names);
		map.put("allocationList", allocationList);
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping("/searchAlloc")
	public ModelAndView searchAllocationList(@RequestParam String searchquery, @ModelAttribute Project project){
		List<Allocation> allocationList = allocationService.searchAllocation(searchquery);
		List<Project> allocationListView = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : allocationListView){
			proj_names.add(p.getProject_name());
		}
		
		Set<String> uniqueNames = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(uniqueNames);
		for (Object obj : proj_names){
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(names);
		
		map.put("names", names);
		map.put("allocationList", allocationList);
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping(value = "/reportPDFAlloc", method = RequestMethod.GET)
	public ModelAndView jasperDownloadPdf(ModelAndView mv){
		System.out.println("------------------Downloading PDF------------------");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Allocation> allocationList = allocationService.getViewAlloc();
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(allocationList,false);
		
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportAlloc", parameterMap);
		
		return mv;
	}
}
