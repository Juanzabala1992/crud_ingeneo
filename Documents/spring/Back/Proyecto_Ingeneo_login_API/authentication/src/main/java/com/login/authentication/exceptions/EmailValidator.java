package com.login.authentication.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|co|es|net)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean insertEmail(String email) {
        if (validateEmail(email)) {
            System.out.println("El correo electrónico es válido.");
            return true;
        } else {
            System.out.println("El correo electrónico no es válido.");
            return false;
        }
    }
}
