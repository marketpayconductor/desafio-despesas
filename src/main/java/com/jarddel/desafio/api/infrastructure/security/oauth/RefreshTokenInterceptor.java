package com.jarddel.desafio.api.infrastructure.security.oauth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.jarddel.desafio.api.infrastructure.security.http.factory.CookieFactory;

@Profile("oauth")
@ControllerAdvice
public class RefreshTokenInterceptor implements ResponseBodyAdvice<OAuth2AccessToken> {

    private static final String METODO_INTERCEPTADO = "postAccessToken";

    @Autowired
    private CookieFactory cookieFactory;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals(METODO_INTERCEPTADO);
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest req, ServerHttpResponse res) {

        HttpServletRequest request = ((ServletServerHttpRequest) req).getServletRequest();
        HttpServletResponse response = ((ServletServerHttpResponse) res).getServletResponse();
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
        String refreshToken = body.getRefreshToken().getValue();
        removerRefreshToken(token);
        Cookie cookie = cookieFactory.criarRefreshToken(refreshToken, request);
        response.addCookie(cookie);
        return body;
    }

    private void removerRefreshToken(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

}