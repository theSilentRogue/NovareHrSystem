package novare.com.hk.controller;

//import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	public String addAllocation(@ModelAttribute Allocation allocation,
			Model model){
		
		System.out.println("Viewing pre-adding page. . .");
		
		/**************EMPLOYEES**************/
		Set<Map.Entry<String, Integer>> employees;
		List<Employee> employeesList = employeeService.getEmployeeList();
		final Map<String, Integer> employeesMap = new HashMap<String, Integer>();
		if (employeesList != null && !employeesList.isEmpty()){
			for(Employee eachEmployee : employeesList){
				System.out.println("This EMP: " + eachEmployee.getFname() + " " + 
						eachEmployee.getLname() + "\n");
				if (eachEmployee != null){
					employeesMap.put(eachEmployee.getFname() + " "
							+ eachEmployee.getLname(), eachEmployee.getId());
				}
			}
		}
		employees = employeesMap.entrySet();
		model.addAttribute("employees", employees);
		
		/**************PROJECTS**************/
		Set<Map.Entry<String, Integer>> projects;
		List<Project> projectsList = projectService.getProjectList();
		final Map<String, Integer> projectsMap = new HashMap<String, Integer>();
		if (projectsList != null && !projectsList.isEmpty()){
			for(Project eachProject : projectsList){
				if(eachProject != null){
					System.out.println("This PROJ:" + eachProject.getProject_name()
							+ "\n");
					projectsMap.put(eachProject.getProject_name(), eachProject.getId());
				}
			}
		}
		projects = projectsMap.entrySet();
		model.addAttribute("projects", projects);
		
		return "addAllocation";
	}
	
	@RequestMapping("/viewAllocationList")
	public ModelAndView getAllocationList(@ModelAttribute Project project) {
		
		/***
		 * setting up employee name and project name for the table JSP View of
		 * allocations
		 ***/
		List<Allocation> allocationList = allocationService.getAllocationList();
		if(allocationList != null){
			for (Allocation a : allocationList){
				a.setEmployee_name(a.getEmployee().getFname() + " "
						+ a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
		/********************************************************************/
		
		/*****  for project names under filter dropdown  *****/
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
		/******************************************************/
		map.put("names", names);
		map.put("allocationList", allocationList);
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping("/editAllocation")
	public ModelAndView editAllocation(@RequestParam String id, 
			@ModelAttribute Allocation allocation, Model model) {
		
		allocation = allocationService.getAllocation(Integer.parseInt(id));
		allocation.setEmployee_name(allocation.getEmployee().getFname() + " " + allocation.getEmployee().getLname());
		allocation.setProject_name(allocation.getProject().getProject_name());
		
		/**			Remove duplicate names from projects list		**/
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
		/************************************************************/
		
		Set<Map.Entry<String, Integer>> projects;
		List<Project> projectsList = projectService.getProjectList();
		final Map<String, Integer> projectsMap = new HashMap<String, Integer>();
		if (projectsList != null && !projectsList.isEmpty()) {
			for (Project eachProject : projectsList) {
				if (eachProject != null){
					System.out.println("This proj: " 
							+ eachProject.getProject_name() + "\n");
					projectsMap.put(eachProject.getProject_name(), eachProject.getId());
				}
			}
		}
		projects = projectsMap.entrySet();
		model.addAttribute("projects", projects);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("allocation", allocation);
		map.put("names", names);
		map.put("empID", allocation.getEmployee().getId());
		
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
		allocationService.deleteData(Integer.parseInt(id));
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
		if(allocationList != null){
			for(Allocation a : allocationList) {
				a.setEmployee_name(a.getEmployee().getFname() + " "
						+ a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
		
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
		
		if(allocationList != null){
			for(Allocation a : allocationList){
				a.setEmployee_name(a.getEmployee().getFname() + " "
						+ a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
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
		List<Allocation> allocationList = allocationService.getAllocationList();
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(allocationList,false);
		
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportAlloc", parameterMap);
		
		return mv;
	}
	
	@RequestMapping(value = "/reportMonthPDF", method = RequestMethod.GET)
	public ModelAndView jasperMonthReportPDF(@RequestParam Date start_date, Date end_date, ModelAndView mv){
		System.out.println("------------------Downloading Monthly PDF------------------");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Project> projectList = projectService.getReport(start_date, end_date);
		
		for (Project p: projectList){
			for (Allocation a: allocationService.reportMonth(start_date, end_date)){
				if(p.getProject_name() == a.getProject().getProject_name()){
					p.setPlannedheadcount(p.getPlannedheadcount()+1);
					double percentage = (double) Math.round(a.getPercent())/100;
						p.setTotalpercent(p.getTotalpercent()+percentage);
						p.setDailycost(p.getDailycost()+a.getEmployee().getCost()*percentage);
				}
			}
		}
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(projectList,false);
		
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportMonth", parameterMap);
		
		return mv;
	}
	
	@RequestMapping("/datefilterAlloc")
	public ModelAndView searchDateList(@RequestParam Date start_date, Date end_date, @ModelAttribute Project project){
		
		List<Project> projectList = projectService.getReport(start_date, end_date);
		
		/*if(allocationList != null){
			for(Allocation a : allocationList){
				a.setEmployee_name(a.getEmployee().getFname() + " "
						+ a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
		List<Project> allocationListView = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : allocationListView){
			proj_names.add(p.getProject_name());
		}*/
		
		for (Project p: projectList){
			for (Allocation a: allocationService.reportMonth(start_date, end_date)){
				if(p.getProject_name() == a.getProject().getProject_name()){
					p.setPlannedheadcount(p.getPlannedheadcount()+1);
					double percentage = (double) Math.round(a.getPercent())/100;
						p.setTotalpercent(p.getTotalpercent()+percentage);
						p.setDailycost(p.getDailycost()+a.getEmployee().getCost()*percentage);
				}
			}
		}
		
		/*Set<String> uniqueNames = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(uniqueNames);
		for (Object obj : proj_names){
			names.add(obj.toString());
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		/*Collections.sort(names);
		
		map.put("names", names);*/
		map.put("projectList", projectList);
		/*map.put("allocationList", allocationList);*/
		
		return new ModelAndView("viewProjectDateQuery", "map", map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
