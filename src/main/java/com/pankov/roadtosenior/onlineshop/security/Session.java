package com.pankov.roadtosenior.onlineshop.security;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public final class Session {
    private String token;
    private User user;
    private List<Product> cart;
    private LocalDateTime expiredDate;
}
