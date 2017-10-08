package com.jarddel.desafio.api.infrastructure.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {

    public static String encriptar(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

}
