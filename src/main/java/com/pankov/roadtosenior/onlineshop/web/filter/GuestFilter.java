package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;

public class GuestFilter extends SecurityFilter{

    @Override
    Role requiredRole() {
        return Role.GUEST;
    }
}
