package com.pankov.roadtosenior.onlineshop.web.filter;

import com.pankov.roadtosenior.onlineshop.entity.Role;
import lombok.NoArgsConstructor;

//@Component
@NoArgsConstructor
public class GuestFilter extends SecurityFilter {

    @Override
    Role requiredRole() {
        return Role.GUEST;
    }
}
