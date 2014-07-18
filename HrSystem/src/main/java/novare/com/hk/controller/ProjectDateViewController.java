package novare.com.hk.controller;

import java.util.List;

import novare.com.hk.model.Allocation;
import novare.com.hk.services.ProjectDateViewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectDateViewController {
	
	@Autowired
	ProjectDateViewService projectDateViewService;
	
	@RequestMapping("/viewProjectEnd")
	public ModelAndView getProjectEndList() {
		List<Allocation> projectEndList = projectDateViewService.getProjectEndList();
		return new ModelAndView("viewProjectEnd", "projectEndList", projectEndList);
	}
	
	@RequestMapping("/viewProjectStart")
	public ModelAndView getProjectStartList() {
		List<Allocation> projectStartList = projectDateViewService.getProjectStartList();
		return new ModelAndView("viewProjectStart", "projectStartList", projectStartList);
	}
}
