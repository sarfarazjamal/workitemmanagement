package com.workitem.form.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workitem.form.model.WorkItem;
import com.workitem.form.service.UserService;
import com.workitem.form.service.WorkItemService;
import com.workitem.form.validator.WorkItemValidator;

//http://www.tikalk.com/redirectattributes-new-feature-spring-mvc-31/
//https://en.wikipedia.org/wiki/Post/Redirect/Get
//http://www.oschina.net/translate/spring-mvc-flash-attribute-example
@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	WorkItemValidator workItemValidator;
	
	@InitBinder("workItem")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(workItemValidator);
	}

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private WorkItemService workItemService;

	@Autowired
	public void setWorkItemService(WorkItemService workItemService) {
		this.workItemService = workItemService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/users";
	}

	// list page
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String showAllUsers(Model model) {
     logger.debug("showAllUsers()");
		model.addAttribute("workitemlist", workItemService.findAll());
		return "users/list";

	}

	// save or update user
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("userForm") WorkItem workItm,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateUser() : {}", workItm);
		workItemValidator.validate(workItm, result);
		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "users/userform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if(workItm.isNew()){
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			}else{
				redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
			}
			
			workItemService.saveOrUpdate(workItm);
			
			// POST/REDIRECT/GET
			return "redirect:/users/" + workItm.getId();

			// POST/FORWARD/GET
			// return "user/list";

		}

	}

	// show add user form
	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {

		logger.debug("showAddUserForm()");

		WorkItem workItem = new WorkItem();

		workItem.setWorkItem(144);
		workItem.setFileChange("Changed");
		workItem.setPeerReviewComments("Good comment");
		workItem.setImpactAnalysis("Impact analysis");
		workItem.setModule("Module");
		workItem.setApplication("FCN");
		workItem.setRequestor("Krishna");
		workItem.setStatus("Pending");
		workItem.setTeam("Enbd team");

		model.addAttribute("userForm", workItem);

		populateDefaultModel(model);

		return "users/userform";

	}

	// show update form
	@RequestMapping(value = "/users/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {

		logger.debug("showUpdateUserForm() : {}", id);

		WorkItem item = workItemService.findById(id);
		model.addAttribute("userForm", item);
		
		populateDefaultModel(model);
		
		return "users/userform";

	}

	// delete user
	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

		logger.debug("deleteUser() : {}", id);

		workItemService.delete(id);
		
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");
		
		return "redirect:/users";

	}

	// show user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {

		logger.info("findById() id: {}", id);

		WorkItem workitem = workItemService.findById(id);
		if (workitem == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("workitem", workitem);

		return "users/show";

	}

	private void populateDefaultModel(Model model) {
		Map<String, String> team = new LinkedHashMap<String, String>();
		team.put("Dev", "Dev");
		team.put("TCOE", "TCOE");
		team.put("SCM", "SCM");
		model.addAttribute("team", team);

		
		Map<String, String> finalStatus = new LinkedHashMap<String, String>();
		finalStatus.put("Deployed in SIT", "Deployed in SIT");
		finalStatus.put("SIT Testing", "SIT Testing");
		finalStatus.put("Certified in SIT", "Certified in SIT");
		finalStatus.put("Deployed in UAT", "Deployed in UAT");
		finalStatus.put("Verified in UAT for Sanity", "Verified in UAT for Sanity");
		model.addAttribute("finalStatus", finalStatus);
		
		Map<String, String> application = new LinkedHashMap<String, String>();
		application.put("SB India", "SB India");
		application.put("SB UAE DEWA", "SB UAE DEWA");
		application.put(" SB UAE Self Service", " SB UAE Self Service");
		model.addAttribute("application", application);

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("user/show");
		model.addObject("msg", "user not found");

		return model;

	}

}