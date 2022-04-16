package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;

//@Component
public class AdminFilter extends SecurityFilter {

    @Override
    Role requiredRole() {
        return Role.ADMIN;
    }
}
