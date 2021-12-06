package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;
import com.pankov.roadtosenior.onlineshop.security.Session;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SecurityFilter extends HttpFilter {
    private final SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Session session = null;

        String path = req.getRequestURI();

        if (path.equals("/login")) {
            chain.doFilter(req, res);
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    session = securityService.getSession(cookie.getValue());
                }
            }
        }

        if (session == null) {
            res.sendRedirect("/login");
            return;
        }

        if (requiredRole().getId() < session.getUser().getRoleId()) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        req.setAttribute("session", session);
        chain.doFilter(req, res);
    }

    abstract Role requiredRole();
}
