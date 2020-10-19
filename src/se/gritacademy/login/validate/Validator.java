package se.gritacademy.login.validate;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements ValidateInterface {

    // Regex for checking if password contains requirements 2 digits,
    // 2 lowercase, 2 uppercase, 1 special char and is atleast 8 chars in length
    @Override
    public boolean isPasswordValid(String password) {
        final String PASSWORD_PATTERN = "(?=^.{8}$)(?=(.*\\d){2})(?=(.*[a-z]){2})(?=(.*[A-Z]){2})(?=.*[!@#$%^&+=])(?!.*[\\s])^.*";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    // Second way to check if email is valid by using commons-validator dependency. jar_files.zip contains needed files
    @Override
    public boolean isEmailReallyValid(String email) {
        return EmailValidator.getInstance(true).isValid(email);
    }
}
