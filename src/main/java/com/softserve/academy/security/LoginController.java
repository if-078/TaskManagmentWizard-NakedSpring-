package com.softserve.academy.security;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Value("${security.headerName}")
    private String HEADER_NAME;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserProxy> getAuthenticationToken(@RequestBody UserCredential credential, HttpServletResponse response) {

        User user = userService.findByEmail(credential.getUsername());
        String fullToken;

        if (user.getPassword().equals(credential.getPassword())) {
            fullToken = TokenAuthenticationService.createToken(user);
            response.addHeader(HEADER_NAME, fullToken);
            return  ResponseEntity.ok(new UserProxy(user.getId(), null, null));

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        }
    }
}