package com.pankov.roadtosenior.onlineshop.security;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Session {
    private User user;
    private List<Product> cart;
    private LocalDateTime expiredDate;
}
