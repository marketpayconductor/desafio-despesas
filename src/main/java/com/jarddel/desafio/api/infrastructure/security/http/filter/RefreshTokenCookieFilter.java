package com.jarddel.desafio.api.infrastructure.security.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jarddel.desafio.api.infrastructure.security.http.factory.CookieFactory;
import com.jarddel.desafio.api.infrastructure.security.http.servlet.ApplicationHttpServletRequestWrapper;
import com.jarddel.desafio.api.infrastructure.security.oauth.AuthorizationServer;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookieFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (AuthorizationServer.checarTokenPath(req) && AuthorizationServer.possuiTokenRefresh(req)
                && req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(CookieFactory.COOKIE_NOME)) {
                    req = new ApplicationHttpServletRequestWrapper(req, cookie.getValue());
                }
            }
        }

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
        /** API Servlet ferindo a segregação de interfaces do SOL(I)D */
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        /** API Servlet ferindo a segregação de interfaces do SOL(I)D */
    }
}
