package util;

import javax.servlet.http.Cookie;

public class CookieCreator {

    public Cookie create(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }
}

