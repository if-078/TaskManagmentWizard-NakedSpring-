package com.softserve.academy.tmw.security;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.impl.UserService;
import org.apache.log4j.Logger;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${security.headerName}")
    private String HEADER_NAME;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    static Logger logger = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserProxy> getAuthenticationToken(@RequestBody UserCredential credential, HttpServletResponse response) {
        logger.info("User with credentials email " + credential.getUserEmail() + " " + credential.getPassword() + " going to login.");
        User user = getUser(credential);
        if (user != null && passwordEncoder.matches(credential.getPassword(),user.getPass())) {
            String fullToken = TokenAuthenticationService.createToken(user);
            logger.info("Token created.");
            response.addHeader(HEADER_NAME, fullToken);
            logger.info("User authentication success. User: " + user.getName() + " with email address: " + user.getEmail() + " logged in.");
            return ResponseEntity.ok(new UserProxy(user.getId(), user.getName(), null));
        } else {
            logger.info("Bad user credentials email: " + credential.getUserEmail() + " " + credential.getPassword());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String token = httpServletRequest.getHeader("Authentication");
        String username = TokenAuthenticationService.getUsernameFromToken(token);
        logger.info("User " + username + " logout");
        return new ResponseEntity(HttpStatus.OK);
    }

    private User getUser(@RequestBody UserCredential credential) {
        try {
            return userService.findByEmail(credential.getUserEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}