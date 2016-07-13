package website.ohmyCat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import website.ohmyCat.services.UserService;

@Controller
@RequestMapping("/register")
public class Register
{
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String registerPage(HttpServletRequest request)
	{
		if (request.getSession().getAttribute("account") != null)
		{
			return "redirect:/action";
		}
		else
		{
			return "userRegister";
		}
		//System.out.println("Register");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("Invoking");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("name: " + name);
		System.out.println("password: " + password);
		if (userService.findUser(name) != null)
		{
			out.write("{\"Status\":\"RepetedUsername\"}");
		}
		else
		{
			userService.addUser(name, password);
			prepareNewUser(name);
			request.getSession().setAttribute("account", name);
			Cookie cookie = new Cookie("UserName", name);
			cookie.setMaxAge(24*60*60);
			response.addCookie(cookie);
			out.write("{\"Status\":\"Success\"}");
		}
	}
	
	private void prepareNewUser(String username)
	{
		String path = "D:\\CatData\\" + username;
		File f = new File(path);
		if (!f.exists() && !f.isDirectory())
		{
			f.mkdir();
		}
		
		f = new File(path + "\\image");
		if (!f.exists() && !f.isDirectory())
		{
			f.mkdir();
		}
		
		f = new File(path + "\\article");
		if (!f.exists() && !f.isDirectory())
		{
			f.mkdir();
		}
	}

}
