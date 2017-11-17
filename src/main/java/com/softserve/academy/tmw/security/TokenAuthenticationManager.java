package com.softserve.academy.tmw.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {
    static Logger logger = Logger.getLogger(TokenAuthenticationManager.class.getName());
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserAuthentication userAuthentication = (UserAuthentication) authentication;

        try {
            int userId = TokenAuthenticationService.getUserIdFromToken(userAuthentication.getToken());
            String username = TokenAuthenticationService.getUsernameFromToken(userAuthentication.getToken());
            List<Role> authorities = mapper.convertValue(
                    TokenAuthenticationService.getRolesFromToken(userAuthentication.getToken()),
                    new TypeReference<List<Role>>() {
                    }
            );

            UserProxy userProxy = new UserProxy(userId, username, authorities);
            userAuthentication.setPrincipal(userProxy);
            userAuthentication.setAuthenticated(true);
        } catch (SignatureException se) {
            logger.info("Token is not valid");
            throw new BadCredentialsException("Token is not valid");
        } catch (ExpiredJwtException ee) {
            logger.info(ee.getMessage() + " Token must be refreshed");
            throw new BadCredentialsException("Token must be refreshed");
        }

        return userAuthentication;
    }
}