package com.pankov.roadtosenior.onlineshop.dao;

import com.pankov.roadtosenior.onlineshop.entity.User;

public interface UserDao {
    User findByName(String name);
}
