package com.service.registration.services.email;

import org.hibernate.query.criteria.internal.IllegalDereferenceException;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidatorService implements Predicate<String> {
    @Override
    public boolean test(String s) {
//        TODO: Regex to validate email
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        boolean matchFound = matcher.find();

        if (!matchFound) {
            throw new IllegalStateException("This email address is incorrect.");
        }


        return true;
    }
}
