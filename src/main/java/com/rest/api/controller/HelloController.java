package com.rest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping(value="/helloworld/string")
    @ResponseBody
    public String hellowordlString(){
        return "helloworld";
    }
}
