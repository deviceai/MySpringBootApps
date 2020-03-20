package sweaterapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sweaterapp.entities.Role;
import sweaterapp.entities.User;
import sweaterapp.repos.UserRepo;
import java.util.Collections;
import java.util.Map;

@Controller
public class Registration {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null){
            model.put("message", "User exist!");
            return "registration";
        }

        user.setActive(true);
        user.setRole(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
