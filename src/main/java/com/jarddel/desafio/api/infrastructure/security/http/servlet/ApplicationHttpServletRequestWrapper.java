package com.jarddel.desafio.api.infrastructure.security.http.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;

import com.jarddel.desafio.api.infrastructure.security.oauth.AuthorizationServer;

public class ApplicationHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private String refreshToken;

    public ApplicationHttpServletRequestWrapper(HttpServletRequest request, String refreshToken) {
        super(request);
        this.refreshToken = refreshToken;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        ParameterMap<String, String[]> parametros = new ParameterMap<>(getRequest().getParameterMap());
        parametros.put(AuthorizationServer.GRANT_REFRESH_TOKEN, new String[] { refreshToken });
        parametros.setLocked(true);
        return parametros;
    }

}