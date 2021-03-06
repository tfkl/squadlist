package uk.co.squadlist.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.co.squadlist.web.localisation.BritishRowing;
import uk.co.squadlist.web.views.ViewFactory;

@Controller
public class GoverningBodyController {
		
	private ViewFactory viewFactory;
	
	public GoverningBodyController() {
	}
	
	@Autowired
	public GoverningBodyController(ViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}
	
	@RequestMapping(value="/governing-body/british-rowing", method=RequestMethod.GET)
    public ModelAndView member() throws Exception {		
		final BritishRowing governingBody = new BritishRowing();	// TODO make general

		final ModelAndView mv = viewFactory.getView("governingBody");
		mv.addObject("governingBody", governingBody);
    	mv.addObject("title", governingBody.getName());
		mv.addObject("ageGrades", governingBody.getAgeGrades());
    	mv.addObject("statuses", governingBody.getStatusPoints());
    	mv.addObject("boatSizes", governingBody.getBoatSizes());
    	return mv;
    }
	
}