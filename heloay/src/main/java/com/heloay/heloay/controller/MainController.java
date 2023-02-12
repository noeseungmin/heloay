package com.heloay.heloay.controller;

import com.heloay.heloay.domain.Movie;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("main")
    public String main(Model model){
        model.addAttribute("data", new Movie());
        return "view/main";

    }
}
