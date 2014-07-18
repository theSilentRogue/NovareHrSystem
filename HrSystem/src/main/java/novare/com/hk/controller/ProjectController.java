package novare.com.hk.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import novare.com.hk.model.Project;
import novare.com.hk.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService ProjectService;
	
	@RequestMapping (value = "/addProject", method = RequestMethod.GET)
	public String addProject(@ModelAttribute Project project){

		System.out.println("Viewing pre-adding page. . .");
		
		return "addProject";
	}
	
	@RequestMapping("/viewProjectList")
	public ModelAndView getProjectList() {
		List<Project> ProjectList = ProjectService.getProjectList();
		return new ModelAndView("viewProjectList", "projectList", ProjectList);
	}
	
	@RequestMapping("/editProject")
	public ModelAndView editProject(@RequestParam String id, 
			@ModelAttribute Project project) {
		
		project = ProjectService.getProject(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", project);
		return new ModelAndView ("editProject", "map", map);
	}
	
	@RequestMapping("/updateProject")
	public String updateProject(@ModelAttribute Project project){
		ProjectService.updateData(project);
		return "redirect:/viewProjectList";
	}
	
	
	@RequestMapping("/deleteProject")
	public String deleteProject(@RequestParam String id){
		System.out.println("id = " + id);
		ProjectService.deleteData(id);
		return "redirect:/viewProjectList";
	}
	
	
	@RequestMapping("/insertProject")
	public String insertProject(@ModelAttribute Project project) {
		if (project != null){
			ProjectService.insertData(project);
			System.out.println("Inserted Project: " + project.getProject_name());
			}
		return "redirect:/viewProjectList";
	}
}
