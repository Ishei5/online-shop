package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

public class UserFilter extends SecurityFilter {

    public UserFilter(SecurityService securityService) {
        super(securityService);
    }

    @Override
    Role requiredRole() {
        return Role.USER;
    }
}
