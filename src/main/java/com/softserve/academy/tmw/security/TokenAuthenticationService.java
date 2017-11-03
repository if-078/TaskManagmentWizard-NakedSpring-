package com.softserve.academy.tmw.security;

import com.softserve.academy.tmw.entity.Role;
import com.softserve.academy.tmw.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class TokenAuthenticationService  {

    private static String TOKEN_PREFIX;
    private static long EXPIRATION_TIME;
    private static String SECRET;

    @Value("${security.tokenPrefix}")
    private void setTokenPrefix(String tokenPrefix) {
        TOKEN_PREFIX = tokenPrefix;
    }

    @Value("${security.expirationTime}")
    private void setExpirationTime(long expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

    @Value("${security.secret}")
    private void setSecret(String secret) {
        SECRET = secret;
    }

    public static String createToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", user.getId())
//                .claim("roles", user.getAuthorities()) // TODO: real roles
                .claim("roles", Collections.singletonList("USER"))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX + " " + token;
    }

    public static String refreshToken(Authentication authentication) {
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", ((UserProxy)authentication.getDetails()).getId())
                .claim("roles", authentication.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return TOKEN_PREFIX + " " + token;
    }

    public static String getUsernameFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();

        return username;
    }

    public static int getUserIdFromToken(String token) {
        Number id = (Number) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("id");

        return id.intValue();
    }

    public static List<Role> getRolesFromToken(String token) {
        List roles = (List) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().get("roles");

        return roles;
    }


}