package novare.com.hk.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import novare.com.hk.model.Project;
import novare.com.hk.services.ProjectService;

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

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping (value = "/addProject", method = RequestMethod.GET)
	public String addProject(@ModelAttribute Project project){

		System.out.println("Viewing pre-adding page. . .");
		
		return "addProject";
	}
	
	@RequestMapping("/viewProjectList")
	public ModelAndView getProjectList(@ModelAttribute Project project) {
		List<Project> projectList = projectService.getProjectList();
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for (Project p : projectList){
			proj_names.add(p.getProject_name());
		}
		
		Set<String> se = new HashSet<String>(proj_names);
		proj_names.clear();
		
		proj_names = new ArrayList<String>(se);
		for(Object obj : proj_names){
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(names);
		
		map.put("names", names);
		map.put("projectList", projectList);
		
		return new ModelAndView("viewProjectList", "map", map);
	}
	
	@RequestMapping("/editProject")
	public ModelAndView editProject(@RequestParam String id, 
			@ModelAttribute Project project) {
		
		project = projectService.getProject(Integer.parseInt(id));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", project);
		return new ModelAndView ("editProject", "map", map);
	}
	
	@RequestMapping("/updateProject")
	public String updateProject(@ModelAttribute Project project){
		projectService.updateData(project);
		return "redirect:/viewProjectList";
	}
	
	
	@RequestMapping("/deleteProject")
	public String deleteProject(@RequestParam String id){
		System.out.println("id = " + id);
		projectService.deleteData(Integer.parseInt(id));
		return "redirect:/viewProjectList";
	}
	
	
	@RequestMapping("/insertProject")
	public String insertProject(@ModelAttribute Project project) {
		try{
			if (project != null) {
				projectService.insertData(project);
				System.out.println("Inserted Project: " + project.getProject_name());
				}
			return "redirect:/viewProjectList";
		} catch(Exception ex){
			System.out.println("Invalid date input. Error is: " + ex.getMessage());
			return "redirect:/errorPage";
		}
	}
	
	@RequestMapping("/searchProject")
	public ModelAndView searchProjcetList(@RequestParam String searchquery, @ModelAttribute Project project){
		List<Project> projectList = projectService.searchProject(searchquery);
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
		for(Object obj : proj_names) {
			names.add(obj.toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Collections.sort(names);
		
		map.put("names", names);
		map.put("projectList", projectList);
		
		return new ModelAndView("/viewProjectList", "map", map);
	}
	
	@RequestMapping("/filterProject")
	public ModelAndView filterProjectList(@RequestParam String project_name, @ModelAttribute Project project){
		List<Project> projectList = projectService.filterProject(project_name);
		List<Project> projectListView = projectService.getProjectList();		
		
		List<String> proj_names = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		
		for(Project p : projectListView){
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
		map.put("projectList", projectList);
		
		return new ModelAndView("viewProjectList", "map", map);
	}
	
	@RequestMapping(value = "/reportPDFProj", method = RequestMethod.GET)
	public ModelAndView jasperDownloadPdf(ModelAndView mv){
		System.out.println("------------------Downloading PDF------------------");
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Project> projectList = projectService.getProjectList();
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(projectList,false);
		
		parameterMap.put("dataSource", jrDataSource);
		
		mv = new ModelAndView("pdfReportProj", parameterMap);
		
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
