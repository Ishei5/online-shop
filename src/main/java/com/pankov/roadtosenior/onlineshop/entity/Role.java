package com.pankov.roadtosenior.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public enum Role {
    ADMIN(1, "ADMIN"),
    USER(2, "USER"),
    GUEST(3, "GUEST");

    private int id;
    private String role;
}
