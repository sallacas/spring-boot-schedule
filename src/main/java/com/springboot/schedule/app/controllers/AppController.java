package com.springboot.schedule.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @Value("${config.schedule.open}")
    private Integer open;
    @Value("${config.schedule.close}")
    private Integer close;
    @GetMapping({"/","index"})
    public String index(Model model){
        model.addAttribute("title","Welcome to customer service schedule");
        return "index";
    }
    @GetMapping("/closed")
    public String closed(Model model){
        model.addAttribute("title","Customer service is closed");
        model.addAttribute("message","Customer service is closed, please return between "
                .concat(String.valueOf(open))
                .concat("and ")
                .concat(String.valueOf(close))
                .concat(" hours."));
        return "closed";
    }
}
