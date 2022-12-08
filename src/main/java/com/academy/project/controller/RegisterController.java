package com.academy.project.controller;


import com.academy.project.dto.AddressDto;
import com.academy.project.dto.UserRegDto;
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
        var userRegDto = new UserRegDto();
        model.addAttribute("userDto", userRegDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegDto userRegDto, Model model) {
        userService.save(userRegDto);
        var userDto = userService.getUserByUsername(userRegDto.getUsername());
        model.addAttribute("userDto", userDto);
        model.addAttribute("address", new AddressDto());
        return "redirect:/account?username=" + userDto.getUsername();
    }
}
