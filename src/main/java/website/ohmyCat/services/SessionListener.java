package website.ohmyCat.services;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg) {
		System.out.println("Session destroyed");
		ActiveUsers.removeUser((String) arg.getSession().getAttribute("account"));
		ActiveUsers.printUsers();
	}

}
