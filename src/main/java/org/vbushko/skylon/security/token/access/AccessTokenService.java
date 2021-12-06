package org.vbushko.skylon.security.token.access;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vbushko.skylon.util.TimeUtil;

import java.util.Date;

import static java.time.ZoneId.systemDefault;
import static java.util.Date.from;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    @Value("${application.security.jwt.secret}")
    private String jwtSecret;

    @Value("${application.security.jwt.access-token-expiration-sec}")
    private int jwtTokenExpirationSec;

    private final TimeUtil timeUtil;

    public String generate(String login) {
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(from(timeUtil.getFuture(jwtTokenExpirationSec).atZone(systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String extractLogin(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
