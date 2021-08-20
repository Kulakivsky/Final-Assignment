package com.example.demo.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Registration {

    @RequestMapping (value="main/registration", method = RequestMethod.POST, params = "submit")
    public void button() {
        System.out.println("Button work");
    }
}
