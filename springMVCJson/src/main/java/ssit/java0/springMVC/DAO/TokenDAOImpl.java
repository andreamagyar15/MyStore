package ssit.java0.springMVC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ssit.java0.springMVC.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TokenDAOImpl implements TokenDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get the user from DB
     * @param username username
     * @param token token
     * @return the user if it exists
     */
    @Override
    public List<User> getToken(String username, String token) {
        String sql="SELECT * FROM users WHERE username='"+username+"' and token='"+token+"'";
        System.out.println(sql);
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
              User user=new User();
              user.setUsername(resultSet.getString("username"));
              System.out.println(resultSet.getString("username"));
              return user;
            }
        });
    }

    /**
     * Check if the token is valid
     * @param token user token
     * @return user with token
     * @throws EmptyResultDataAccessException
     */
    @Override
    public String isValidToken(String token) throws EmptyResultDataAccessException {
        String sql="SELECT * FROM users WHERE token='"+token+"'";
        return jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("token");
            }
        });
    }
}
