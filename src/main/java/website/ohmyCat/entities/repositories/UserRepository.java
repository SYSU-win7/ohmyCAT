package website.ohmyCat.entities.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import website.ohmyCat.entities.User;
import website.ohmyCat.entities.UserSetting;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	      this.jdbcTemplate = jdbcTemplate;
	}
	
	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setPwd(rs.getString("password"));
			user.setName(rs.getString("name"));
		    return user;
		}
	}
	
	private class UserSettingMapper implements RowMapper<UserSetting>
	{

		@Override
		public UserSetting mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserSetting userSetting = new UserSetting();
			userSetting.setEmail(rs.getString("email"));
			userSetting.setImage(rs.getString("avatar"));
			userSetting.setSignature(rs.getString("signature"));
			return userSetting;
		}
		
	}
	
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserMapper());
	}
	
	public User findUser(String name)
	{
		String sql = "select * from user where name = ?";
		Object[] arg = new Object[] {name};
		List<User> lu = jdbcTemplate.query(sql, arg, new UserMapper());
		if (lu.size() == 1)
			return lu.get(0);
		else
			return null;
	}
	
	public void addUser(String name, String password)
	{
		String sql = "insert into user(name, password) values(?, ?)";
		Object[] arg = new Object[] {name, password};
		jdbcTemplate.update(sql, arg);
	}
	
	public User validateUser(String name, String pwd)
	{
		String sql = "select * from user where name = ? and password = ?";
		Object[] arg = new Object[] {name, pwd};
		List<User> lu = jdbcTemplate.query(sql, arg, new UserMapper());
		if (lu.size() == 1)
			return lu.get(0);
		else
			return null;
	}
	
	public UserSetting getUserSetting(String username)
	{
		String sql = "select email, avatar, signature from user "
				   + "where name = ?;";
		Object[] arg = new Object[] {username};
		List<UserSetting> lu = jdbcTemplate.query(sql, arg, new UserSettingMapper());
		if (lu.size() == 1)
			return lu.get(0);
		else
			return null;
	}
	
}
