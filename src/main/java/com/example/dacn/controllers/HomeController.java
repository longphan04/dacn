package com.example.dacn.controllers;

import com.example.dacn.dtos.UserDTO;
import com.example.dacn.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"home"})
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    @GetMapping
    public String home(Model model) {
        String username = userService.getLoginUsername();
        UserDTO userDTO = userService.getUserByUsername(username);

        model.addAttribute("categoryList", userDTO.getCategoryList());
        return "home";
    }
}
