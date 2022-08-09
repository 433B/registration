package com.service.registration.services;

import com.service.registration.entities.UserInfo;
import com.service.registration.entities.ConfirmationToken;
import com.service.registration.repositories.UserRepository;
import com.service.registration.services.email.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(UserInfo userInfo) {
        boolean userExists = userRepository
                .findByEmail(userInfo.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("Email already taken");
        }

        if (userInfo.getEmail().length() < 8 || userInfo.getEmail().length() > 33) {
            throw new IllegalStateException("Your email address must contain more than 8 characters" +
                    " and no more than 32 characters");
        }

        fieldIsEmpty(userInfo);

        if (!userInfo.getPassword().equals(userInfo.getConfirmPassword())) {
            throw new IllegalStateException("Passwords do not match.");
        }

        if (userInfo.getPassword().length() < 8 || userInfo.getConfirmPassword().length() > 33) {
            throw new IllegalStateException("Password must contain more than 8 characters" +
                    " and no more than 32 characters");
        }


        String encodedPassword = bCryptPasswordEncoder
                .encode(userInfo.getPassword());

        userInfo.setPassword(encodedPassword);

        userRepository.save(userInfo);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userInfo
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    private void fieldIsEmpty(UserInfo userInfo) {
        if (userInfo.getEmail() == null) {
            throw new IllegalStateException("This field is empty");
        }

        if (userInfo.getFirstName() == null) {
            throw new IllegalStateException("This field is empty");
        }

        if (userInfo.getLastName() == null) {
            throw new IllegalStateException("This field is empty");
        }

        if (userInfo.getPhoneNumber() == null) {
            throw new IllegalStateException("This field is empty");
        }

        if (userInfo.getPassword() == null) {
            throw new IllegalStateException("This field is empty");
        }

        if (userInfo.getConfirmPassword() == null) {
            throw new IllegalStateException("This field is empty");
        }
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
