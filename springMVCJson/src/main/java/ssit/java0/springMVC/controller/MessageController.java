package ssit.java0.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ssit.java0.springMVC.domain.Message;
import ssit.java0.springMVC.service.MessageService;

import java.util.List;

//@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * Mapping: add new message
     * @param message
     */
    @RequestMapping(value="/message" ,method=RequestMethod.POST)
    public void addMessage(Message message){
        messageService.addMessage(message);
    }

    /**
     * Mapping list messages
     * @return all messages
     */
    @RequestMapping(value="/secured/message/all" ,method=RequestMethod.GET)
    @ResponseBody
    public List<Message> getMessage(){
        return messageService.listMessages();
    }

    /**
     * Mapping: delete message
     * @param id
     */
    @RequestMapping(value="/secured/message/delete/{id}" ,method=RequestMethod.DELETE)
    public void deleteMessage(@PathVariable int id){
        messageService.deleteMessage(id);
    }
}


