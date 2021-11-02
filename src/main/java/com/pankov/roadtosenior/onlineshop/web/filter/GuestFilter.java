package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;

public class GuestFilter extends SecurityFilter{

    public GuestFilter(SecurityService securityService) {
        super(securityService);
    }

    @Override
    Role requiredRole() {
        return Role.GUEST;
    }
}
