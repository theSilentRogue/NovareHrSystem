package novare.com.hk.controller;

//import java.util.ArrayList;
//import java.math.BigDecimal;
import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.text.ParseException;
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
/*import org.springframework.beans.factory.annotation.Qualifier;*/
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;*/
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
	
	List<Project> plist;
	
	List<Project> projectList;
	List<Allocation> allocationList;
	boolean isFiltered = false;
	
/*	Used for Spring form Validator test.
 * @Autowired
	@Qualifier("allocMonthValidator")
	private Validator validator;*/
	
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
	public ModelAndView getAllocationList(@ModelAttribute Project project, @ModelAttribute Allocation allocation) {
		
		/***
		 * setting up employee name and project name for the table JSP View of
		 * allocations
		 ***/
		plist = projectService.getProjectList();
		projectList = projectService.getProjectList();
		allocationList = allocationService.getAllocationList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", arrangeDropdownProj());
		map.put("allocationList", arrangeNames(allocationList) );
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping("/editAllocation")
	public ModelAndView editAllocation(@RequestParam String id, 
			@ModelAttribute Allocation allocation, Model model) {
		
		allocation = allocationService.getAllocation(Integer.parseInt(id));
		allocation.setEmployee_name(allocation.getEmployee().getFname() + " " + allocation.getEmployee().getLname());
		allocation.setProject_name(allocation.getProject().getProject_name());
		
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
		map.put("names", arrangeDropdownProj() );
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
			if (allocation.getPercent() > 100 || allocation.getPercent() < 0){
				throw new Exception("Invalid percentage");
			}
		if (allocation != null){
			allocationService.insertData(allocation);
		}
		return "redirect:/viewAllocationList";
		
		} catch (Exception ex){
			System.out.println("Invalid input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}
	
	@RequestMapping("/filterAlloc")
	public ModelAndView filterAllocationList(@RequestParam String project_name, @ModelAttribute Project project, @ModelAttribute Allocation allocation){
		projectList.clear();
		isFiltered = true;
		
		List<Object[]> rows = projectService.filterAlloc(project_name);
				if(rows != null){
					for (Object[] row: rows) {
					    System.out.println(" ------------------- ");
					    System.out.println("project_name: " + row[0]);
					    System.out.println("month: " + row[1]);
					    System.out.println("year: " + row[2]);
					    System.out.println("headcount: " + row[3]);
					    System.out.println("totalAllocation: " + row[4]);
					    System.out.println("dailyCost: " + row[5]);
					   
					    Project p = new Project();
					    p.setProject_name(row[0].toString());
					    p.setMonth(row[1].toString());
					    p.setYear(row[2].toString());
					    p.setPlannedheadcount(Long.parseLong(row[3].toString()));
					    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
					    p.setDailycost(Double.parseDouble(row[5].toString()));
					    projectList.add(p);
					    
					}
				}
				
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("names", arrangeDropdownProj());
		map.put("allocationList", arrangeNames(allocationService.filterAllocation(project_name) ));
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping("/searchAlloc")
	public ModelAndView searchAllocationList(@RequestParam String searchquery, @ModelAttribute Project project, @ModelAttribute Allocation allocation){
		allocationList = allocationService.searchAllocation(searchquery);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("names", arrangeDropdownProj());
		map.put("allocationList", arrangeNames(allocationList) );
		
		return new ModelAndView("viewAllocationList", "map", map);
	}
	
	@RequestMapping(value = "/reportPDFAlloc", method = RequestMethod.GET)
	public ModelAndView jasperDownloadPdf(@RequestParam Date start_date, @RequestParam Date end_date, ModelAndView mv){
		System.out.println("------------------Downloading Report PDF------------------");
		
		System.out.println("1st Condition");
		if(start_date != null || end_date != null){
					List<Object[]> rows = projectService.genReport(start_date, end_date);
					if(rows != null && !isFiltered){
						projectList.clear();
						for (Object[] row: rows) {
						    System.out.println(" ------------------- ");
						    System.out.println("project_name: " + row[0]);
						    System.out.println("month: " + row[1]);
						    System.out.println("year: " + row[2]);
						    System.out.println("plannedheadcount: " + row[3]);
						    System.out.println("totalallocation: " + row[4]);
						    System.out.println("dailycost: " + row[5]);
						    
						    Project p = new Project();
						    p.setProject_name(row[0].toString());
						    p.setMonth(row[1].toString());
						    p.setYear(row[2].toString());
						    p.setPlannedheadcount(Long.parseLong(row[3].toString()));
						    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
						    p.setDailycost(Double.parseDouble(row[5].toString()));
						    projectList.add(p);
						    
						}
					}
			}
		
		//projectList = projectService.genReport(start_date, end_date);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		/*JRDataSource jrDataSource = new JRBeanCollectionDataSource(allocationList,false);
		
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportAlloc", parameterMap);*/
		
		if(!isFiltered && (start_date == null && end_date == null)){
							System.out.println("No parameters - Default allocation search...");
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(allocationList, false);
			parameterMap.put("dataSource", jrDataSource);
			mv = new ModelAndView("pdfReportAlloc", parameterMap);
			}
			else{
				System.out.println("generate report");
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(projectList, false);
			parameterMap.put("dataSource", jrDataSource);
			mv = new ModelAndView("pdfReportMonth", parameterMap);
			}
		
			isFiltered = false;
		
		return mv;
	}
	
	/*@RequestMapping(value = "/reportMonthPDF", method = RequestMethod.GET)
	public ModelAndView jasperMonthReportPDF(ModelAndView mv, @RequestParam Date start_date, @RequestParam Date end_date){
			
		System.out.println("------------------Downloading Monthly PDF------------------");
		
Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		if(start_date != null || end_date != null){
			projectList = projectService.getReport(start_date, end_date);
					System.out.println("====\nReport \nService \nProject \n====\n");
		}
		
		for(Project p : projectList){
			System.out.print(p.getProject_name() + ": ");
			List<Allocation> allocationList = p.getAllocations();
			for(Allocation a : allocationList){
				if(end_date != null){
					if((a.getStart_date().after(start_date) || a.getStart_date().equals(start_date)) && (a.getStart_date().before(end_date) || a.getStart_date().equals(end_date))){
						p.setPlannedheadcount(p.getPlannedheadcount()+1);
						double percentage = (double)a.getPercent()/100;
						p.setTotalAllocation(p.getTotalAllocation()+percentage);
						p.setDailycost(p.getDailycost()+a.getEmployee().getCost()*percentage);
						System.out.println(a.getEmployee().getFname());
					}//End inner if
				}//End outer if
				else if(start_date != null){
					if((a.getStart_date().after(start_date) || a.getStart_date().equals(start_date))){
						p.setPlannedheadcount(p.getPlannedheadcount()+1);
					double percentage = (double)a.getPercent()/100;
					p.setTotalAllocation(p.getTotalAllocation()+percentage);
					p.setDailycost(p.getDailycost()+a.getEmployee().getCost()*percentage);
					System.out.println(a.getEmployee().getFname());
					}
				}// End inner for
			}//End outer for
		}
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(projectList,false);
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportMonth", parameterMap);
		
		return mv;
	}*/
	
	@RequestMapping("/datefilterAlloc")
	public ModelAndView searchDateList(@RequestParam Date start_date, Date end_date){
		
		plist.clear();
		List<Object[]> rows = projectService.genReport(start_date, end_date);
		if(rows != null){
			for (Object[] row: rows) {
			    System.out.println(" ------------------- ");
			    System.out.println("project_name: " + row[0]);
			    System.out.println("month: " + row[1]);
			    System.out.println("year: " + row[2]);
			    System.out.println("headcount: " + row[3]);
			    System.out.println("totalAllocation: " + row[4]);
			    System.out.println("dailyCost: " + row[5]);
			   
			    Project p = new Project();
			    p.setProject_name(row[0].toString());
			    p.setMonth(row[1].toString());
			    p.setYear(row[2].toString());
			    p.setPlannedheadcount(Long.parseLong(row[3].toString()));
			    p.setTotalAllocation(Double.parseDouble(row[4].toString()));
			    p.setDailycost(Double.parseDouble(row[5].toString()));
			    plist.add(p);
			    
			}
		}
		//List<Object[]> projectList = projectService.genReport(start_date, end_date);
//		List<Allocation> allocList = allocationService.reportMonth(start_date, end_date);
		
		/*for (Project p: projectList){
			for (Allocation a: allocList){ allocationService.reportMonth(start_date, end_date)
				if(p.getProject_name() == a.getProject().getProject_name()){
					p.setPlannedheadcount(p.getPlannedheadcount()+1);
					double percentage = (double) Math.round(a.getPercent())/100;
						p.setTotalAllocation(p.getTotalAllocation()+percentage);
						p.setDailycost(p.getDailycost()+a.getEmployee().getCost()*percentage);
				}
			}
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectList", plist);
		
		return new ModelAndView("viewProjectDateQuery", "map", map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		/* Testing Spring form Validator(Has errors)
		 * binder.setValidator(validator);*/
	}
	
	private List<Allocation> arrangeNames(List<Allocation> allocationList){
		/*setup employee name; emplyoee.fname + employee.lname to transient field and project name */
		if(allocationList != null){
			for(Allocation a :allocationList){
				a.setEmployee_name(a.getEmployee().getFname() + " " + a.getEmployee().getLname());
				a.setProject_name(a.getProject().getProject_name());
			}
		}
		return allocationList;
	}
	
	private List<String> arrangeDropdownProj(){
		/* for project names under filter drop down */
		List<Project> projectListDbox = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		for (Project p : projectListDbox){
			proj_names.add(p.getProject_name());
		}
		Set<String> se = new HashSet<String>(proj_names);
		proj_names.clear();
		proj_names = new ArrayList<String>(se);
		for (Object obj : proj_names){
			names.add(obj.toString());
		}
		Collections.sort(names);
		
		return names;
	}
	
	/* Used for parsing BigDecimal values to desired format in decimal.
	 * private BigDecimal parseBigDecimal(String stringToBeParsed){
			    // Parse BigDecimal
			    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			    symbols.setGroupingSeparator(',');
			    symbols.setDecimalSeparator('.');
			    String pattern = "#,##0.0#";
			    DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			    decimalFormat.setParseBigDecimal(true);
		
			    BigDecimal parsedTotalAlloc = null;
				try 
				{
					parsedTotalAlloc = (BigDecimal) decimalFormat.parse(stringToBeParsed);
				} catch (ParseException e) 
				{
					e.printStackTrace();
				}
			    // end parse
				System.out.println(parsedTotalAlloc);
				return parsedTotalAlloc;
			}*/
}