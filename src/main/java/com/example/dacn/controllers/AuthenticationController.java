package com.example.dacn.controllers;

import com.example.dacn.dtos.LoginDTO;
import com.example.dacn.dtos.RegisterDTO;
import com.example.dacn.dtos.UserDTO;
import com.example.dacn.services.LoginService;
import com.example.dacn.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/auth"})
public class AuthenticationController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;

    @GetMapping("register")
    public String registerIndex() {
        return "register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute RegisterDTO registerDTO) {
        registerService.save(registerDTO);
        return "login";
    }

    @GetMapping("login")
    public String loginIndex() {
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginDTO loginDTO, RedirectAttributes redirectAttributes) {
        UserDTO user = loginService.login(loginDTO);
        redirectAttributes.addFlashAttribute("user", user);
        return user != null ? "redirect:/home" : "redirect:/auth/login";
    }
}
