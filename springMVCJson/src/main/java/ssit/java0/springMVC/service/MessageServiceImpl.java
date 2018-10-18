package ssit.java0.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssit.java0.springMVC.DAO.MessageDAO;
import ssit.java0.springMVC.domain.Message;

import java.util.List;

@Service
public class MessageServiceImpl  implements MessageService{
    @Autowired
    private  MessageDAO messageDAO;
    @Override
    public void addMessage(Message message) {
        messageDAO.addMessage(message);
    }

    @Override
    public List<Message> listMessages() {
        return messageDAO.listMessage();
    }

    @Override
    public void deleteMessage(int id) {
        messageDAO.deleteMessage(id);
    }
}
