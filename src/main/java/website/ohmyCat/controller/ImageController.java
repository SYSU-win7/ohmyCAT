package website.ohmyCat.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import website.ohmyCat.services.ActiveUsers;

@Controller
@RequestMapping("/image")
public class ImageController
{
	@RequestMapping(value="/avatar/{imgname:.*}", method=RequestMethod.GET)
	public void getAvatar(HttpServletRequest request, HttpServletResponse response, @PathVariable String imgname) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("account");
		String path = ActiveUsers.basePath + username + "\\" + imgname;
		String[] s = imgname.split("\\.");
		String type = "image/" + s[s.length - 1];
		System.out.println(type);
		response.setContentType(type);
		OutputStream out = response.getOutputStream();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		out.write(b);
		out.flush();
	}
	
}
