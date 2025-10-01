package com.example.firstproject.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/greetings")
    public String nicetoMeetYou(Model model) {
        model.addAttribute("username", "이용석");
        return "greetings";
    }

    @GetMapping("/bye")
    public String goodBye(Model model) {
        model.addAttribute("username", "이용석");
        return "goodbye";
    }
}
