package com.jarddel.desafio.api.infrastructure.security.oauth;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.jarddel.desafio.api.application.configurations.ApplicationProperty;
import com.jarddel.desafio.api.application.configurations.ApplicationProperty.ClientOauth;

@Profile("oauth")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    public static final String TOKEN_PATH = "/oauth/token";
    public static final Integer ACCESS_TOKEN_LIFETIME = 3600 * 12;
    public static final Integer REFRESH_TOKEN_LIFETIME = 3600 * 24 * 2;
    public static final String GRANT_REFRESH_TOKEN = "refresh_token";
    public static final String SIGNING_KEY = "!_c0ndUct0R_cH@ma_N0$_!";

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ApplicationProperty applicationProperty;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientOauth clienteAngular = applicationProperty.getClientOauthAngular();
        ClientOauth clienteMobile = applicationProperty.getClientOauthAngular();

        clients.inMemory()
                .withClient(clienteAngular.getCliente())
                .secret(clienteAngular.getCliente())
                .scopes(clienteAngular.getEscopos())
                .authorizedGrantTypes(clienteAngular.getConcessoes())
                .accessTokenValiditySeconds(ACCESS_TOKEN_LIFETIME)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_LIFETIME)
            .and()
                .withClient(clienteMobile.getCliente())
                .secret(clienteMobile.getSegredo())
                .scopes(clienteMobile.getEscopos())
                .authorizedGrantTypes(clienteMobile.getConcessoes())
                .accessTokenValiditySeconds(ACCESS_TOKEN_LIFETIME)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_LIFETIME);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints
            .tokenStore(tokenStore())
            .tokenEnhancer(tokenEnhancerChain)
            .reuseRefreshTokens(false)
            .authenticationManager(authManager);
        
    }

    public static Boolean checarTokenPath(HttpServletRequest request) {
        return TOKEN_PATH.equalsIgnoreCase(request.getRequestURI());
    }

    public static Boolean possuiTokenRefresh(HttpServletRequest request) {
        return GRANT_REFRESH_TOKEN.equals(request.getParameter("grant_type"));
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(SIGNING_KEY);
        return accessTokenConverter;
    }

}