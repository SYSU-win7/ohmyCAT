package website.ohmyCat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import website.ohmyCat.entities.UserSetting;
import website.ohmyCat.entities.repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/setting", method=RequestMethod.GET)
	public String getSettings(HttpServletRequest request, Model m)
	{
		String username = (String) request.getSession().getAttribute("account");
		UserSetting userSetting = userRepository.getUserSetting(username);
		m.addAttribute("email", userSetting.getEmail());
		m.addAttribute("avatar", userSetting.getImage());
		m.addAttribute("signature", userSetting.getSignature());
		System.out.println("email: " + userSetting.getEmail());
		System.out.println("avatar: " + userSetting.getImage());
		System.out.println("signature: " + userSetting.getSignature());
		return "userInfo";
	}
	
	@RequestMapping(value="/updateInfo", method=RequestMethod.POST)
	public void updateSettings(MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		if (file != null)
		{
			System.out.println(file.getOriginalFilename());
		}
	}
}
