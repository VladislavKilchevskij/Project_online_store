package com.academy.project.controller;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.UserDto;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@EnableWebSecurity
@Controller
@RequiredArgsConstructor
public class SignInController {
    private final UserService userService;

    @GetMapping("/sign_in")
    public String signInForm(Model model) {
        var userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "sign_in";
    }

    @PostMapping("/sign_in/account/")
    public String signIn(@ModelAttribute UserDto userDto, Model model) {
        var userAccountDto = userService.findUserInfoByUsernameAndPassword(userDto);
        if (userAccountDto == null) {
            return "index";
        }
        model.addAttribute("userAccountDto", userAccountDto);
        model.addAttribute("address", new AddressDto());
        return "profile/account";
    }
}
