package com.pankov.roadtosenior.onlineshop.security;

import com.pankov.roadtosenior.onlineshop.entity.User;
import com.pankov.roadtosenior.onlineshop.service.UserService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    private final List<Session> SESSION_LIST = new CopyOnWriteArrayList<>();

    @NonNull
    private UserService userService;

    @Getter
    @Value("${sessionTimeToLive}")
    private int sessionTimeToLive;

    public synchronized String login(String username, String password) {

        User user = checkCredentials(username, password);

        if (user == null) {
            return null;
        }

        for (Session session : SESSION_LIST) {
            if (username.equals(session.getUser().getUsername())) {
                return session.getToken();
            }
        }

        String token = UUID.randomUUID().toString();
        Session session = new Session(token, user, new ArrayList<>(), LocalDateTime.now().plusSeconds(sessionTimeToLive));
        SESSION_LIST.add(session);

        return token;
    }

    public void logout(String token) {
        for (Session session : SESSION_LIST) {
            if (token.equals(session.getToken())) {
                log.info(session.getUser() + " - logout");
                SESSION_LIST.remove(session);
            }
        }
    }

    public Session getSession(String token) {
        for (Session session : SESSION_LIST) {
            if (token.equals(session.getToken())) {
                if (session.getExpiredDate().isAfter(LocalDateTime.now())) {
                    return session;
                } else {
                    SESSION_LIST.remove(session);
                    log.warn("Your session is expired");
                }
            }
        }
        log.info("Session is null");
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
}