package org.vbushko.skylon.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.addSeconds;

@Component
public class JwtProvider {

    @Value("${application.security.jwt.secret}")
    private String jwtSecret;

    @Value("${application.security.jwt.access-token-expiration-sec}")
    private int jwtAccessTokenExpirationSec;

    @Value("${application.security.jwt.refresh-token-expiration-sec}")
    private int jwtRefreshTokenExpirationSec;

    public String generateAccessToken(String login) {
        Date now = Calendar.getInstance().getTime();
        Date expiration = addSeconds(now, jwtAccessTokenExpirationSec);
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String login) {
        Date now = Calendar.getInstance().getTime();
        Date expiration = addSeconds(now, jwtRefreshTokenExpirationSec);
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
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

    public String getLoginFromToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
