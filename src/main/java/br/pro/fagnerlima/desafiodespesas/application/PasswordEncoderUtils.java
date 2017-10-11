package br.pro.fagnerlima.desafiodespesas.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtils {

    public static String encode(CharSequence rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(rawPassword);
    }
}
