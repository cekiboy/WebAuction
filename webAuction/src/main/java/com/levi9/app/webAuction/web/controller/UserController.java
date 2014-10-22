/**
 * 
 */
package com.levi9.app.webAuction.web.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levi9.app.webAuction.model.User;
import com.levi9.app.webAuction.service.MailService;
import com.levi9.app.webAuction.service.UserService;

/**
 * @author Ceki
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

	
	/**
	 * User service
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Mail service
	 */
	@Autowired
	private MailService mailService;
	
	/**
	 * Returns the view for registering of new user or editing existing. Adds empty user as model attribute <code>user</code>.
	 * 
	 * @param model the model object map
	 * @return the name of the view for adding/editing a user
	 * 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew (Model model){
		model.addAttribute("user", new User());
		return "addEditUser";
	}
	
	/**
	 * Returns the view for editing a user. Adds user, found by passed id, as model attribute
	 * <code>user</code>.
	 * 
	 * @param id the id of the user to edit
	 * @param model the model object map
	 * @return the name of the view for adding/editing a user
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEdit(Model model, Principal principal) {
		String viewName;
		if (principal == null){
			viewName = "home";
		}
		else {
			model.addAttribute("user", userService.findByUsername(principal.getName()));
			viewName = "addEditUser";
		}
		return viewName;
	}
	
	/**
	 * Persists the passed user object.
	 * 
	 * @param user the user to persist
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the redirect view name, which redirects to home
	 */
	@RequestMapping (params = "save")
	public String post (@Valid User user, BindingResult bindingResult, Model model, Principal principal){
		String viewName;
		try {
			if (!bindingResult.hasErrors()){
				user.setAuthority("ROLE_ADMIN");
				userService.save(user);
				if (principal == null){
					mailService.sendRegistrationMail(user);
				}
				viewName = "redirect:home"; 
			}
			else{
				model.addAttribute("user", user);
				viewName = "addEditUser";
			}
			return viewName;
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("user", user);
			model.addAttribute("usernameExistsError", "username already exists" );
			return "addEditUser";
		}
		
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:home";
	}
	
	@RequestMapping(value = "/{id}",  method = RequestMethod.GET)
	public String registerUser(@PathVariable Long id) throws FileNotFoundException, UnsupportedEncodingException {
		User user = userService.findOne(id);
		user.setEnabled(true);
		userService.save(user);
		return "redirect:..";
	}
	
}
