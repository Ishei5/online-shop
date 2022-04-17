package com.pankov.roadtosenior.onlineshop.service;

import com.pankov.roadtosenior.onlineshop.dao.UserDao;
import com.pankov.roadtosenior.onlineshop.entity.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
