package com.academy.project.controller;

import com.academy.project.dto.OrderDetailsDto;
import com.academy.project.service.AddressService;
import com.academy.project.service.CartService;
import com.academy.project.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final OrderDetailsService orderDetailsService;
    private final CartService cartService;
    private final AddressService addressService;

    @GetMapping("/account/{userId}/orders")
    public String getOrderHistory(@PathVariable Integer userId,
                                  Model model) {

        var ordersDto = orderDetailsService.getAllOrdersByUserId(userId);
        model.addAttribute("ordersDto", ordersDto);
        return "profile/orderHistory";
    }

    @PostMapping("/{userId}/add_to_cart")
    public String addToCart(@PathVariable Integer userId,
                            @RequestParam Integer productId,
                            @RequestParam Integer amount,
                            @RequestParam BigDecimal price,
                            HttpServletRequest request) {

        cartService.addProductToCart(userId, productId, amount, price);
        var referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/account/{userId}/cart")
    public String getCartPage(@PathVariable Integer userId,
                              Model model) {
        var orderDto = orderDetailsService.getInCartOrderDetailsDtoByUserId(userId);
        model.addAttribute("orderDto", orderDto);
        return "profile/cart";
    }

    @GetMapping("/{userId}/order")
    public String getOrderPage(@PathVariable Integer userId,
                               Model model) {

        var updateOrder = orderDetailsService.getInCartOrderDetailsDtoByUserId(userId);
        model.addAttribute("orderDto", updateOrder);
        model.addAttribute("addresses", addressService.findAllByUserId(userId));
        return "profile/order";
    }

    @PostMapping("/{userId}/ordered")
    public String updateOrder(@PathVariable Integer userId,
                              @ModelAttribute OrderDetailsDto order) {
        orderDetailsService.updateOrderStatus(order);
        return "redirect:/account/" + userId;
    }
}
