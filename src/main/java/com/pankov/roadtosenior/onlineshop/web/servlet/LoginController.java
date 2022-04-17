package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final SecurityService securityService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletResponse response) throws IOException {
        String token = securityService.login(username, password);
        if (token == null) {
            response.sendError(SC_UNAUTHORIZED);
            return "login";
        }

        Cookie cookie = new Cookie("user-token", token);
        cookie.setMaxAge(securityService.getSessionTimeToLive());
        response.addCookie(cookie);

        return "redirect:/product";
    }
}
