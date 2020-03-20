package sweaterapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TestHelloController {
    @GetMapping
    public String main (Map<String, Object> model){
        String test_name = "Test Hello Controller";
        model.put("name", test_name);
        return "main";
    }
}
