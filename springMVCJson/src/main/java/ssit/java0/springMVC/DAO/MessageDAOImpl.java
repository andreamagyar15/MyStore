package ssit.java0.springMVC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ssit.java0.springMVC.domain.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Add a new message
     * @param message object with message details
     */
    @Override
    public void addMessage(Message message) {
        String sql ="INSERT INTO messages (name,message,email,number) VALUES ('"+message.getName()+"','"+message.getMessage()+"','"+message.getEmail()+"','"+message.getNumber()+"')";
        jdbcTemplate.execute(sql);
    }

    /**
     *
     * @return messages from DB
     */
    @Override
    public List<Message> listMessage() {
        String sql="SELECT * FROM messages";
        return jdbcTemplate.query(sql, new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet resultSet, int i) throws SQLException {
                Message message=new Message();
                message.setId(resultSet.getInt("id"));
                message.setName(resultSet.getString("name"));
                message.setMessage(resultSet.getString("message"));
                message.setEmail(resultSet.getString("email"));
                message.setNumber(resultSet.getString("number"));
                return message;
            }
        });
    }

    /**
     * Delete a message from DB
     * @param id of the message
     */
    @Override
    public void deleteMessage(int id) {
        String sql="DELETE FROM messages WHERE id="+id;
        jdbcTemplate.update(sql);
    }
}
