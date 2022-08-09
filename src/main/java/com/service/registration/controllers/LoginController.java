package com.service.registration.controllers;

import com.service.registration.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@AllArgsConstructor
public class LoginController {
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/v1/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/api/v1/registration/verify")
    public String verify() {
        return "verify";
    }
}
