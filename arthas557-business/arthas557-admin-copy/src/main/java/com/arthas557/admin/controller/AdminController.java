package com.arthas557.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/sayAdmin")
    @ResponseBody
    public String sayAdmin(){
        System.out.println("HELLO111111111111111");
        return "hello";
    }

}
