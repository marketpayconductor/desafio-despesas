package com.jarddel.desafio.api.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jarddel.desafio.api.infrastructure.security.http.factory.CookieFactory;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private CookieFactory cookieFactory;

    @DeleteMapping("/revogar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revoke(HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(cookieFactory.criarRefreshToken(null, request));
    }

}
