package com.pankov.roadtosenior.onlineshop.security;

import com.pankov.roadtosenior.onlineshop.entity.User;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class SecurityService {
    private static final List<Session> sessionList = new CopyOnWriteArrayList<>();

    private UserService userService;
    private Long sessionTimeToLive;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public String login(String username, String password) {
        User user = checkCredentials(username, password);

        if (user == null) {
            return null;
        }

        for (Session session : sessionList) {
            if (username.equals(session.getUser().getUsername())) {
                return session.getToken();
            }
        }

        String token = UUID.randomUUID().toString();
        Session session = new Session(token, user, new ArrayList<>(), LocalDateTime.now().plusSeconds(sessionTimeToLive));
        sessionList.add(session);

        return token;
    }

    public void logout(String token) {
        for (Session session : sessionList) {
            if (token.equals(session.getToken())) {
                log.info(session.getUser() + " - logout");
                sessionList.remove(session);
            }
        }
    }

    public Session getSession(String token) {
        for (Session session : sessionList) {
            if (token.equals(session.getToken())) {
                if (session.getExpiredDate().isAfter(LocalDateTime.now())) {
                    return session;
                } else {
                    sessionList.remove(session);
                    log.warn("Your session is expired");
                }
            }
        }

        return null;
    }

    private User checkCredentials(String username, String password) {
        User user = userService.findByName(username);
        if (user != null) {
            if (Encryptor.encryptPassword(password, user.getSalt()).equals(user.getPassword())) {
                return user;
            }
            log.warn("Password is invalid for user - " + username);
        }
        log.warn("Unknown user");
        return null;
    }

    public void setSessionTimeToLive(Long sessionTimeToLive) {
        this.sessionTimeToLive = sessionTimeToLive;
    }

}