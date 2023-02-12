package com.heloay.heloay.controller;

import com.heloay.heloay.dto.SignUpDto;
import com.heloay.heloay.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("/main/login")
    public String login() {
        return "view/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "view/main";
    }

    @GetMapping("main/signup")
    public String signupForm(Model model){
        model.addAttribute("signup", new SignUpDto());
        return "view/signup";
    }

    @PostMapping("main/signup")
    public String createUser(@ModelAttribute("signup") @Valid SignUpDto dto, BindingResult result){
        if(result.hasErrors()){
            return "view/signup";
        }
        authService.createUser(dto);
        return "redirect:/main";

    }
}
