package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.security.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SecurityFilter implements Filter {

    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) {
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        this.securityService = context.getBean(SecurityService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Session session = null;

        String path = httpServletRequest.getRequestURI();

        if (path.equals("/login")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    session = securityService.getSession(cookie.getValue());
                }
            }
        }

        if (session == null) {
            httpServletResponse.sendRedirect("/login");
            return;
        }

        if (requiredRole().getId() < session.getUser().getRoleId()) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        httpServletRequest.setAttribute("session", session);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
    }

    abstract Role requiredRole();
//    {return null;}
}
