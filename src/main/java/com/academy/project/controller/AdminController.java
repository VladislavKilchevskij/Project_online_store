package com.academy.project.controller;

import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/account/{userId}/block_users")
    public String getBlockingPage(@PathVariable Integer userId,
                                  Model model) {

        var customers = userService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "profile/blockUsers";
    }

    @PostMapping("/account/{userId}/block_users/{customerId}")
    public String changeAccessToCustomer(@PathVariable Integer userId,
                                         @PathVariable Integer customerId) {

        userService.changeAccessStatus(customerId);
        return "redirect:/account/" + userId + "/block_users";
    }
}
