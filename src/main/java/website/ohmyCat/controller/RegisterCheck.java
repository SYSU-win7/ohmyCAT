package website.ohmyCat.controller;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONObject;
import website.ohmyCat.entities.User;
import website.ohmyCat.services.UserService;

@Controller
@RequestMapping(value="/registerCheck")
public class RegisterCheck {
	
	@Autowired
	private UserService userService;
    
	@RequestMapping(method=RequestMethod.POST)
	public void check(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
	{
		System.out.println("Check Invoked");
		request.setCharacterEncoding("UTF-8");
		
		//JSONObject res = new JSONObject();
		/*StringBuffer requestData = new StringBuffer();
		String line = null;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				requestData.append(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(requestData);*/
		//JSONObject r = JSONObject.fromObject(requestData.toString());
		//User u = userService.findUser(r.getString("username"));
		
	}
}
