package com.example.dacn.controllers;

import com.example.dacn.dtos.CalendarDTO;
import com.example.dacn.dtos.DepositDTO;
import com.example.dacn.dtos.StatisticalDTO;
import com.example.dacn.dtos.UserDTO;
import com.example.dacn.dtos.WithdrawDTO;
import com.example.dacn.services.CalendarService;
import com.example.dacn.services.DepositService;
import com.example.dacn.services.LoginService;
import com.example.dacn.services.StatisticalService;
import com.example.dacn.services.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"home", "", "/"})
public class HomeController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping
    public String home(@ModelAttribute("user") UserDTO userDTO, Model model) {
        model.addAttribute("categoryList", userDTO.getCategoryList());
        model.addAttribute("userId", userDTO.getId());
        return "home";
    }

    @PostMapping("back")
    public String backToHome(@ModelAttribute("userId") Integer userId, Model model) {
        UserDTO userDTO = loginService.getUserById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("categoryList", userDTO.getCategoryList());
        return "home";
    }

    @PostMapping("deposit")
    public String deposit(@ModelAttribute DepositDTO depositDTO, Model model) {
        DepositDTO deposit = depositService.save(depositDTO);
        model.addAttribute("deposit", deposit);
        return "deposit";
    }

    @PostMapping("withdraw")
    public String withdraw(@ModelAttribute WithdrawDTO withdrawDTO, Model model) {
        WithdrawDTO withdraw = withdrawService.save(withdrawDTO);
        model.addAttribute("withdraw", withdraw);
        return "withdraw";
    }

    @PostMapping("calendar")
    public String calendar(@ModelAttribute CalendarDTO calendarDTO, Model model) {
        calendarDTO = calendarService.getCalendarInfo(calendarDTO);
        model.addAttribute("calendarDTO", calendarDTO);
        return "calendar";
    }

    @PostMapping("statistical")
    public String statistical(@ModelAttribute StatisticalDTO statisticalDTO, Model model) {
        StatisticalDTO statistical = statisticalService.getStatisticalInfo(statisticalDTO);
        model.addAttribute("statistical", statistical);
        return "statistical";
    }
}
