package com.projet.evalBtp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MyController {
    
    @GetMapping
    public ModelAndView pageLogin()
    {
        ModelAndView mv = new ModelAndView("redirect:/user/page-login-client");

        return mv;
    }
}
