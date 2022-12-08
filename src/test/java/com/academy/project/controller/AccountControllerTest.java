package com.academy.project.controller;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.academy.project.dto.UserDto;
import com.academy.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private UserService userService;

    @Test
    void enterAccountShouldReturnUserIdFromService() throws Exception {
        String username = "test";
        var userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("test");
        Mockito.when(userService.getUserByUsername(username)).thenReturn(userDto);

        this.mockMvc.perform(get("/account").param("username", userDto.getUsername()).secure(true)).andDo(print())
                .andExpect(redirectedUrl("/account/" + userDto.getId())).andExpect(status().isFound());
    }
}