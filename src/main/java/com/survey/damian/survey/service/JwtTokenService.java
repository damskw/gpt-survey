package com.survey.damian.survey.service;

import com.survey.damian.survey.config.auth.AuthConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtTokenService {

    private final AuthConfigProperties authConfigProperties;

    public String generateToken(String username) {
        var currentTime = LocalDateTime.now();
        var expirationTime = currentTime.plusMinutes(authConfigProperties.validity());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(transformLDTToDate(currentTime))
                .setExpiration(transformLDTToDate(expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Date getExpirationTimeFromToken(String token) {
        return getClaims(token).getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        boolean isExpired = getExpirationTimeFromToken(token).before(new Date());

        return username.equals(userDetails.getUsername()) && !isExpired;
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Date transformLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authConfigProperties.secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
