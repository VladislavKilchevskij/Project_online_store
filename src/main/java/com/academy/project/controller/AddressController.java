package com.academy.project.controller;

import com.academy.project.dto.AddressDto;
import com.academy.project.dto.AddressUserIdDto;
import com.academy.project.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/account/{userId}/save_address")
    public String saveAddress(@PathVariable Integer userId,
                              @ModelAttribute AddressUserIdDto newAddressDto) {

        addressService.save(newAddressDto, userId);
        return "redirect:/account/" + userId;
    }

    @GetMapping("/account/{userId}/update_address/{addrId}")
    public String initUpdatingAddress(@PathVariable Integer userId,
                                @PathVariable Integer addrId,
                                Model model) {
        var updateAddressDto = addressService.findById(addrId);
        model.addAttribute("updateAddressDto", updateAddressDto);
        model.addAttribute("userId", userId);
        return "profile/editAddress";
    }

    @PostMapping("/account/{userId}/update_address")
    public String updateAddress(@PathVariable Integer userId,
                                @ModelAttribute AddressDto updateAddressDto,
                                Model model) {
        addressService.update(updateAddressDto);
        return "redirect:/account/" + userId;
    }

    @GetMapping("/account/{userId}/delete_address/{addrId}")
    public String initDeletingAddress(@PathVariable Integer userId,
                                       @PathVariable Integer addrId,
                                       Model model) {
        return deleteAddress(userId, addrId, model);
    }

    @PostMapping("/account/{userId}/delete_address/{addressId}")
    public String deleteAddress(@PathVariable Integer userId,
                                @PathVariable Integer addressId,
                                Model model) {

        addressService.delete(addressId);
        return "redirect:/account/" + userId;
    }
}
