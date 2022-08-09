package com.service.registration.controllers;

import com.service.registration.registration.RegistrationRequest;
import com.service.registration.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@AllArgsConstructor
public class UserController {
    private final RegistrationService registrationService;

    @GetMapping(path = "/api/v1/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "confirm";
    }
}
