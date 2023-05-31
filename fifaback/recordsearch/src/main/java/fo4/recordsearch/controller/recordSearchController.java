package fo4.recordsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class recordSearchController {

    @GetMapping("/")
    public String home(){return "home";}


}
