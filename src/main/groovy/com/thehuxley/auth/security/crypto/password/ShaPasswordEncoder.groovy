package com.thehuxley.auth.security.crypto.password

import org.springframework.security.crypto.codec.Hex
import org.springframework.security.crypto.codec.Utf8
import org.springframework.security.crypto.password.PasswordEncoder

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ShaPasswordEncoder implements PasswordEncoder {

    def messageDigest

    ShaPasswordEncoder(String algorithm) {
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }
    }

    @Override
    String encode(CharSequence rawPassword) {
        byte[] digest = messageDigest.digest(Utf8.encode(rawPassword));
        return new String(Hex.encode(digest));
    }

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword)
    }
}
