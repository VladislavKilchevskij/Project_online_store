package com.academy.project.controller;


import com.academy.project.dto.AddressDto;
import com.academy.project.dto.UserDto;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;


    @GetMapping("/register")
    public String registerForm(Model model) {
        var userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("userDto", userDto);
        model.addAttribute("address", new AddressDto());
        return "profile/account";
    }
}
