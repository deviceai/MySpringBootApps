package sweaterapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweaterapp.entities.Message;
import sweaterapp.repos.MessagesRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MessagesController {
    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping
    public String main (Map<String, Object> model){
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag,
                      Map<String, Object> model){
        Message message = new Message(text, tag);
        messagesRepo.save(message);

        //return messages list to user
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    String filter (@RequestParam String filter,
                                Map<String, Object> model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()){
            messages = messagesRepo.findByTag(filter);
        } else {
            messages = messagesRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
