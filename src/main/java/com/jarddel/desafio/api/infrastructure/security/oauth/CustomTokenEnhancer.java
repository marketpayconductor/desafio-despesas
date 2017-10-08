package com.jarddel.desafio.api.infrastructure.security.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.jarddel.desafio.api.infrastructure.security.UsuarioAuth;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UsuarioAuth usuarioSistema = (UsuarioAuth) authentication.getPrincipal();
        Map<String, Object> informacoesExtras = new HashMap<>();
        informacoesExtras.put("login", usuarioSistema.getUsuario().getLogin());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(informacoesExtras);
        return accessToken;
    }

}