package util;

import javax.servlet.http.Cookie;

public class CookieCreater {

    public Cookie create(String name, String value) {
        Cookie cookie = new Cookie(name, value); //"current-user"
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }
}

