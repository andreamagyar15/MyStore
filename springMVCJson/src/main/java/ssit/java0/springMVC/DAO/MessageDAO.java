package ssit.java0.springMVC.DAO;

import ssit.java0.springMVC.domain.Message;

import java.util.List;

public interface MessageDAO {
    public void addMessage(Message message);
    public List<Message> listMessage();
    public void deleteMessage(int id);
}
