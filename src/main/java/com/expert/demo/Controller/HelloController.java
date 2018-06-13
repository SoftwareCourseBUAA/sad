package com.expert.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController
{
    @GetMapping(value = "/hello")
    public String helloWorld()
    {
        return "hello world";
    }
}
