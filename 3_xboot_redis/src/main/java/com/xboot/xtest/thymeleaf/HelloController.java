package com.xboot.xtest.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("message", "http://www.ityouknow.com");
        return "test/index";
    }

}