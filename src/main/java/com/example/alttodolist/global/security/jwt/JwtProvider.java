package com.example.alttodolist.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long EXPIRATION_MS = 1000 * 60 * 60; // 1시간

    // JWT 토큰 생성
    public String createToken(String username, String role) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(key)
                .compact();
    }
}
