package sweaterapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweaterapp.entities.Message;
import sweaterapp.entities.User;
import sweaterapp.repos.MessagesRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping
    public String root (Map<String, Object> model){
        return "home";
    }

    @GetMapping("/main")
    public String main (Map<String, Object> model){
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ){
        Message message = new Message(text, tag, user);
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
