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
	
}
