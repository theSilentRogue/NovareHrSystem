package novare.com.hk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import novare.com.hk.model.Employee;
import novare.com.hk.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping (value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute Employee employee){

		System.out.println("Viewing pre-adding page. . .");
		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probationary");  
		status.add("Contractual");  
		
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("status", status);  
		
		return new ModelAndView("addEmployee", "map", map);  
	}
	
	@RequestMapping("/viewEmployeeList")
	public ModelAndView getEmployeeList() {
		List<Employee> employeeList = employeeService.getEmployeeList();
		return new ModelAndView("viewEmployeeList", "employeeList", employeeList);
	}
	
	@RequestMapping("/editEmployee")
	public ModelAndView editEmployee(@RequestParam String id, 
			@ModelAttribute Employee employee) {
		
		employee = employeeService.getEmployee(id);

		List<String> status = new ArrayList<String>();  
		
		status.add("Regular");  
		status.add("Probationary");  
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
		employeeService.deleteData(id);
		return "redirect:/viewEmployeeList";
	}
	
	
	@RequestMapping("/insertEmployee")
	public String insertEmployee(@ModelAttribute Employee employee) {
		if (employee != null){
			employeeService.insertData(employee);
			System.out.println("Inserted employee: " + employee.getFname() + " " + employee.getLname());
			}
		return "redirect:/viewEmployeeList";
	}
	

}
