package ssit.java0.springMVC.service;

import ssit.java0.springMVC.domain.Message;

import java.util.List;

public interface MessageService {
    public void addMessage(Message message);
    public List<Message> listMessages();
    public  void deleteMessage(int id);
}
