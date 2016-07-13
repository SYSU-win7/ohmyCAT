package website.ohmyCat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import website.ohmyCat.entities.User;
import website.ohmyCat.entities.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService() {
		super();
	}
	
	public List<User> findAll() {
        return this.userRepository.findAll();
    }
	
	public User findUser(String name)
	{
		return userRepository.findUser(name);
	}
	
	public void addUser(String name, String password)
	{
		userRepository.addUser(name, password);
	}
	
	public User validateUser(String name, String pwd)
	{
		return userRepository.validateUser(name, pwd);
	}
}

