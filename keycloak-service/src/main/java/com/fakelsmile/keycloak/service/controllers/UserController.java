package com.fakelsmile.keycloak.service.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for users.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * Authentication for users.
     */
    @GetMapping("/me")
    public Authentication me(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
