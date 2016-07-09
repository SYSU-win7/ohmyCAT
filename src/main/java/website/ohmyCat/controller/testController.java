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
		System.out.print(userList.size());
		model.addAttribute("names", userList);
		return "index";
	}
	
	@RequestMapping("/action/followed")
	public String testPageAgain(Model model) {
		model.addAttribute("pageTitle", "关注喵");
		return "action";
	}
	
	@RequestMapping("/action/hotted")
	public String testPageAgain1(Model model) {
		model.addAttribute("pageTitle", "热门喵");
		return "action";
	}
	
	@RequestMapping("/catClass")
	public String testPageAgain2(Model model) {
		model.addAttribute("pageTitle", "喵学堂");
		return "catClass";
	}
	
	@RequestMapping("/login")
	public String testPageAgain3(Model model) {
		return "userLogin";
	}
	
	@RequestMapping("/register")
	public String testPageAgain4(Model model) {
		return "userRegister";
	}
	
}
