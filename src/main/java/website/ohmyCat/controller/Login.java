package website.ohmyCat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import website.ohmyCat.entities.repositories.UserRepository;
import website.ohmyCat.services.ActiveUsers;

@Controller
public class Login
{
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginPage(HttpServletRequest request)
    {
		if (request.getSession().getAttribute("account") != null)
		{
			return "redirect:/action";
		}
		else
		{
			return "userLogin";
		}
    }
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void loginActivity(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		String username =  request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if(userRepository.validateUser(username, password) != null)
		{
			if (ActiveUsers.addUser(username))
			{
				ActiveUsers.printUsers();
				request.getSession().setAttribute("account", username);
				//res = new ModelAndView("redirect:/action");
				Cookie cookie = new Cookie("UserName", username);
				cookie.setMaxAge(24*60*60);
				response.addCookie(cookie);
				out.write("{\"Status\":\"Success\"}");
			}
			else
			{
				out.write("{\"Status\":\"Occupied\"}");
			}
		}
		else
		{
			out.write("{\"Status\":\"NotUser\"}");
		}
		//return new ModelAndView(resStr);
	}
}
