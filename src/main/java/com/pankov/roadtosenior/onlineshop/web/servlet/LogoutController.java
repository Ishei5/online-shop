package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
@AllArgsConstructor
public class LogoutController {

    private final SecurityService securityService;

    @PostMapping
    public String logout(@CookieValue(value = "user-token") String token) {
        if (token != null) {
            securityService.logout(token);
        }
        return "redirect:/login";
    }
}
