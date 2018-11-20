package ssit.java0.springMVC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ssit.java0.springMVC.domain.JWTToken;
import ssit.java0.springMVC.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOimpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User checkUser(User user) {
        String sql="SELECT username FROM users WHERE username='"+user.getUserName()+"' AND pass='"+user.getPassword()+"'";
        try {
            User logUser = jdbcTemplate.queryForObject(sql,
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(resultSet.getString("username"));
                        }
                    });
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void setTokenForUser(String username, String token) {
        String sql="UPDATE users SET token='"+token+"' WHERE username='"+username+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteTokenForUser(String token) {
        String sql="UPDATE users SET token='' WHERE token='"+token+"'";
        jdbcTemplate.update(sql);
    }

    @Override
    public void register(User user, JWTToken token) {
        String sql="INSERT INTO users (username,pass,role,token) VALUES ('"+user.getUserName()+"' , '"+user.getPassword()+"','"+user.getRole()+"','"+token.getToken()+"')";
        jdbcTemplate.update(sql);

    }

    @Override
    public String checkCredential(String token) throws EmptyResultDataAccessException{
        String sql="SELECT role FROM users WHERE token='"+token+"'";
        String role=jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role");
            }
        });
        return role;
    }
    public User checkUserNameInDB(User user) {
        String sql="SELECT username FROM users WHERE username='"+user.getUserName()+"'";
        try {
            User logUser = jdbcTemplate.queryForObject(sql,
                    new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(resultSet.getString("username"));
                        }
                    });
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
