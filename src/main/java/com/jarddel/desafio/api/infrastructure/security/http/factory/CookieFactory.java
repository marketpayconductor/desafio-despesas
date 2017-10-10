package com.jarddel.desafio.api.infrastructure.security.http.factory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jarddel.desafio.api.application.configurations.ApplicationProperty;
import com.jarddel.desafio.api.infrastructure.security.oauth.AuthorizationServer;

@Component
public class CookieFactory {

    public static final String COOKIE_NOME = "refreshToken";
    public static final Integer COOKIE_DURACAO = 3600 * 24 * 5;

    @Autowired
    private ApplicationProperty applicationProperty;

    public Cookie criarRefreshToken(String refreshToken, HttpServletRequest request) {
        Cookie cookie = new Cookie(COOKIE_NOME, refreshToken);
        cookie.setMaxAge(COOKIE_DURACAO);
        cookie.setHttpOnly(true);
        cookie.setSecure(applicationProperty.getSeguranca().isHabilitarHttps());
        cookie.setPath(request.getContextPath() + AuthorizationServer.TOKEN_PATH);

        return cookie;
    }
}
