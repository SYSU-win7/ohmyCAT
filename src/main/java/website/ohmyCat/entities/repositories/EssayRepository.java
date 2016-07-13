package website.ohmyCat.entities.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import website.ohmyCat.entities.Essay;

@Repository
public class EssayRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class EssayMapper implements RowMapper<Essay>
	{

		@Override
		public Essay mapRow(ResultSet res, int num) throws SQLException {
			Essay e = new Essay();
			e.setContent(res.getString("content"));
			e.setImage(res.getString("image"));
			e.setAuthor(res.getString("author"));
			return e;
		}
		
	}
	
	public String getFollowedEssays(String username)
	{
		String sql = "select content, image, author "
				   + "from essay, (select noticer from friendship where follower = ?) followed "
				   + "where essay.author = followed.noticer order by essay.submitTime desc;";
		List<Essay> le = jdbcTemplate.query(sql, new Object[]{username}, new EssayMapper());
		return JSONArray.fromObject(le).toString();
	}
	
	public String getHottestEssay()
	{
		String sql = "select * from hotessay order by hotdegree desc;";
		List<Essay> le = jdbcTemplate.query(sql, new EssayMapper());
		return JSONArray.fromObject(le).toString();
	}
	
	
}
