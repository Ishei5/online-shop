package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginServlet {

    @Autowired
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

   /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        log.info("Try to login Username - {}", username);
        String token = securityService.login(username, password);

        if (token == null) {
            resp.sendError(SC_UNAUTHORIZED);
            return;
        }
        Cookie cookie = new Cookie("user-token", token);
//            cookie.setMaxAge();
        resp.addCookie(cookie);
        resp.sendRedirect("/");

    }*/
}
