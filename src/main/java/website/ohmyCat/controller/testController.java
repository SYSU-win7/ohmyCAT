package website.ohmyCat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import website.ohmyCat.entities.User;
import website.ohmyCat.services.UserService;

@Controller
public class testController {

	@Autowired
	private UserService userService;
	
	public testController() {
		super();
	}
	
	@RequestMapping("/")
	public String testPage(Model model) {
		List<User> userList = this.userService.findAll();
		//System.out.print(userList.size());
		model.addAttribute("names", userList);
		return "index";
	}
	
}
