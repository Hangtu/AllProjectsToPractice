package com.example.wicket.javawicket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MapController {
    
    @RequestMapping(value = "/home")
    public String index() {
        return "index";
    }
}