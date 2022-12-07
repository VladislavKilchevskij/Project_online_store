package com.academy.project.controller;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.dto.UserDto;
import com.academy.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @GetMapping("/account")
    public String initEnteringAccount(@RequestParam String username) {
        var userId = userService.getUserByUsername(username).getId();
        return "redirect:/account/" + userId;
    }

    @GetMapping("/account/{userId}")
    public String enterAccount(@PathVariable Integer userId,
                               Model model) {
        var userAccountDto = userService.getDtoById(userId);
        model.addAttribute("userAccountDto", userAccountDto);
        model.addAttribute("newAddressDto", new AddressUserIdDto());
        model.addAttribute("updateAddressDto", new AddressDto());
        return "profile/account";
    }

    @GetMapping("/account/{userId}/{action}")
    public String getUpdatePage(@PathVariable Integer userId,
                                @PathVariable String action,
                                Model model) {
        var editUserDto = userService.getDtoById(userId);
        model.addAttribute("editUserDto", editUserDto);
        if (action.equals("update_info")) {
            return "profile/editUser";
        }
        return "profile/editPassword";
    }

    @PostMapping("/account/{userId}/update_pass")
    public String postUpdatePassword(@PathVariable Integer userId,
                                     @ModelAttribute UserDto editUserDto,
                                     Model model) {
        if (!(editUserDto.getPassword().equals(editUserDto.getRepeatedPassword()))) {
            var error = false;
            model.addAttribute("error", error);
            return getUpdatePage(userId, "update_pass", model);
        }
        userService.updatePassword(editUserDto);
        return enterAccount(userId, model);
    }

    @PostMapping("/account/{userId}/update_info")
    public String postUpdateInfo(@PathVariable Integer userId,
                                 @ModelAttribute UserDto editUserDto,
                                 Model model) {

        userService.updateInfo(editUserDto);
        return enterAccount(userId, model);
    }
}