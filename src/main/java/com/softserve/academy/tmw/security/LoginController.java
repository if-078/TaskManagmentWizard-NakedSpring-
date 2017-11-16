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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${security.headerName}")
    private String HEADER_NAME;

    @Autowired
    private UserService userService;

    static Logger logger= Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserProxy> getAuthenticationToken(@RequestBody UserCredential credential, HttpServletResponse response) {
       logger.info("User with credentials email "+credential.getUserEmail()+" "+credential.getPassword()+" going to login");
        User user = getUser(credential);
        if (user != null && user.getPass().equals(credential.getPassword())) {
            String fullToken = TokenAuthenticationService.createToken(user);
            response.addHeader(HEADER_NAME, fullToken);
            return ResponseEntity.ok(new UserProxy(user.getId(), null, null));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private User getUser(@RequestBody UserCredential credential) {
        try {
            return userService.findByEmail(credential.getUserEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}