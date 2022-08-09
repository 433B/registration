package com.service.registration.webserice;

import com.service.registration.registration.RegistrationRequest;
import com.service.registration.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationRestController {

    private final RegistrationService registrationService;

    @PostMapping(path ="/api/v1/registration")
    public String register(RegistrationRequest request) {
        registrationService.register(request);
        return "redirect:/api/v1/registration/verify";
    }

}
