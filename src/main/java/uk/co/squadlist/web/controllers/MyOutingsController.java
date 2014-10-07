package uk.co.squadlist.web.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.co.eelpieconsulting.common.views.EtagGenerator;
import uk.co.squadlist.web.api.InstanceSpecificApiClient;
import uk.co.squadlist.web.auth.LoggedInUserService;
import uk.co.squadlist.web.model.OutingAvailability;
import uk.co.squadlist.web.services.OutingAvailabilityCountsService;
import uk.co.squadlist.web.urls.UrlBuilder;
import uk.co.squadlist.web.views.DateHelper;
import uk.co.squadlist.web.views.RssOuting;
import uk.co.squadlist.web.views.ViewFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Controller
public class MyOutingsController {
		
	private final LoggedInUserService loggedInUserService;
	private final InstanceSpecificApiClient api;
	private final ViewFactory viewFactory;
	private final OutingAvailabilityCountsService outingAvailabilityCountsService;
	private final UrlBuilder urlBuilder;
	
	@Autowired
	public MyOutingsController(LoggedInUserService loggedInUserService, InstanceSpecificApiClient api, ViewFactory viewFactory,
			OutingAvailabilityCountsService outingAvailabilityCountsService, UrlBuilder urlBuilder) {
		this.loggedInUserService = loggedInUserService;
		this.api = api;
		this.viewFactory = viewFactory;
		this.outingAvailabilityCountsService = outingAvailabilityCountsService;
		this.urlBuilder = urlBuilder;
	}
	
	@RequestMapping("/")
    public ModelAndView outings() throws Exception {
    	final ModelAndView mv = viewFactory.getView("myOutings");
    	final String loggedInUser = loggedInUserService.getLoggedInUser();
		mv.addObject("member", api.getMemberDetails(loggedInUser));
		
		final Date startDate = DateHelper.startOfCurrentOutingPeriod().toDate();
		final Date endDate = DateHelper.oneYearFromNow().toDate();
		
		mv.addObject("outings", api.getAvailabilityFor(loggedInUser, startDate, endDate));
		
		mv.addObject("title", "My outings");		
    	mv.addObject("availabilityOptions", api.getAvailabilityOptions());
    	return mv;
    }
	
	@RequestMapping("/rss")
    public ModelAndView outingsRss(@RequestParam(value="user", required=false) String user) throws Exception {
		if (Strings.isNullOrEmpty(user)) {
			return null;	// TODO 404
		}
		
		api.getMemberDetails(user);	// TODO could be removed if getavailability for 404ed nicely
		
    	final String loggedInUser = user;	// TODO access control
		
		final Date startDate = DateHelper.startOfCurrentOutingPeriod().toDate();
		final Date endDate = DateHelper.oneYearFromNow().toDate();		
		List<OutingAvailability> availabilityFor = api.getAvailabilityFor(loggedInUser, startDate, endDate);
				
		List<RssOuting> outings = Lists.newArrayList();
		for (OutingAvailability outingAvailability : availabilityFor) {
			outings.add(new RssOuting(outingAvailability.getOuting(), urlBuilder.outingUrl(outingAvailability.getOuting())));					
		}

		final String title = api.getInstance().getName() + " outings";
		final ModelAndView mv = new ModelAndView(new uk.co.eelpieconsulting.common.views.ViewFactory(new EtagGenerator()).getRssView(title, urlBuilder.getBaseUrl(), title));
		mv.addObject("data", outings);
		return mv;
    }
	
	@RequestMapping("/myoutings/ajax")
    public ModelAndView ajax() throws Exception {
    	final ModelAndView mv = viewFactory.getView("myOutingsAjax");
    	int pendingOutingsCountFor = outingAvailabilityCountsService.getPendingOutingsCountFor(loggedInUserService.getLoggedInUser());
    	if (pendingOutingsCountFor > 0) {
    		mv.addObject("pendingOutingsCount", pendingOutingsCountFor);
    	}
    	return mv;
    }
	
}
