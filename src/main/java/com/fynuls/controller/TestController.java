package com.fynuls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/testc", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "Test.";
    }
}
