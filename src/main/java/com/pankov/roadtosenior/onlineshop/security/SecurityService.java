package com.pankov.roadtosenior.onlineshop.security;

import com.pankov.roadtosenior.onlineshop.entity.User;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

public class SecurityService {
    private UserService userService;
    private Long sessionTimeToLive;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final Map<String, Session> sessionMap = Collections.synchronizedMap(new HashMap<>());

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSessionTimeToLive(Long sessionTimeToLive) {
        this.sessionTimeToLive = sessionTimeToLive;
    }

    public String login(String username, String password) {
        User user = checkCredentials(username, password);

        if (user == null) {
           return null;
        }

        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            if (username.equals(entry.getValue().getUser().getUsername())) {
                return entry.getKey();
            }
        }

        String token = UUID.randomUUID().toString();
        Session session = new Session(user, new ArrayList<>(), LocalDateTime.now().plusSeconds(sessionTimeToLive));
        System.out.println(session);
        sessionMap.put(token, session);

        return token;
    }

    public Session getSession(String token) {
        Session session = sessionMap.get(token);
        if (session != null) {
            if (session.getExpiredDate().isAfter(LocalDateTime.now())) {
                return session;
            }
        }

        log.warn("Username or password is invalid");
        return null;
    }

    private User checkCredentials(String username, String password) {
        User user = userService.findByName(username);
        System.out.println(user);
        if (user != null) {
            if (Encryptor.encryptPassword(password, user.getSalt()).equals(user.getPassword())) {
                return user;
            }
            else {
                log.warn(Encryptor.encryptPassword(password, user.getSalt()));
            }
        }

        return null;
    }

}