package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;

public class UserFilter extends SecurityFilter {

    @Override
    Role requiredRole() {
        return Role.USER;
    }
}
