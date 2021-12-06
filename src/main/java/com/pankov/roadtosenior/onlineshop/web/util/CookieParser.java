package com.pankov.roadtosenior.onlineshop.web.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieParser {
    public static String getUserToken(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> "user-token".equals(cookie.getName()))
                .findFirst().get().getValue();
    }
}
