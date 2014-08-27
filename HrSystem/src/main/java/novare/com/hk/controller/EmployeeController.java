package novare.com.hk.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import novare.com.hk.model.Employee;
import novare.com.hk.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class EmployeeController extends MultiActionController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping (value = "/addEmployee", method = RequestMethod.GET)
 	public ModelAndView addEmployee(@ModelAttribute Employee employee){

		System.out.println("Viewing pre-adding page. . .");
		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probational");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("status", status);
		
		return new ModelAndView("addEmployee", "map", map);  
	}
	
	@RequestMapping("/viewEmployeeList")
	public ModelAndView getEmployeeList(@ModelAttribute Employee employee){		
		List<Employee> employeeList = employeeService.getEmployeeList();
		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probational");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status); 
		map.put("employeeList", employeeList);
		return new ModelAndView("viewEmployeeList", "map", map);
	}
	
	
	
	@RequestMapping("/editEmployee")
	public ModelAndView editEmployee(@RequestParam String id, 
			@ModelAttribute Employee employee) {
		
		employee = employeeService.getEmployee(Integer.parseInt(id));

		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probational");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status); 
		map.put("employee", employee);
		
		return new ModelAndView ("editEmployee", "map", map);
	}
	
	@RequestMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee employee){
		employeeService.updateData(employee);
		return "redirect:/viewEmployeeList";
	}
	
	
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam String id){
		System.out.println("id = " + id);
		employeeService.deleteData(Integer.parseInt(id));
		return "redirect:/viewEmployeeList";
	}
	
	
	@RequestMapping("/insertEmployee")
	public String insertEmployee(@ModelAttribute Employee employee) {
		try{
		if (employee != null){
			employeeService.insertData(employee);
			System.out.println("Inserted employee: " + employee.getFname() + " " + employee.getLname());
			}
		return "redirect:/viewEmployeeList";
		} catch (Exception ex){
			System.out.println("Invalid date input. Error is: "
					+ ex.getMessage());
			return "redirect:/errorPage";
		}
	}
	
	@RequestMapping("/errorPage")
	public String errorPage(){
		return "errorPage";
	}

	@RequestMapping(value = "/searchEmp", method = RequestMethod.POST)
	public ModelAndView searchEmployee(@RequestParam String searchquery, @ModelAttribute Employee employee) {
		
		List<Employee> employeeList = employeeService.searchEmployee(searchquery);
		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probational");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("employeeList", employeeList);
				
		return new ModelAndView("viewEmployeeList", "map", map);
	}
	
	@RequestMapping("/filterEmployee")
	public ModelAndView filterEmployee(@RequestParam String filterStat, @ModelAttribute Employee employee){
		
		List<Employee> employeeList = employeeService.filterEmployee(filterStat);
		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probational");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("employeeList", employeeList);
		
		return new ModelAndView("viewEmployeeList", "map", map);
	}
	
	@RequestMapping(value = "/reportPDFEmp", method = RequestMethod.GET)
	public ModelAndView jasperDownloadPdf(ModelAndView mv){
		System.out.println("------------------Downloading PDF------------------");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Employee> employeeList = employeeService.getEmployeeList();
		//List<Employee> filterParam = employeeService.filterEmployee(filterStat);
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(employeeList,false);
		
		//parameterMap.put("filterParam", filterParam);
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportEmp", parameterMap);
		
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
