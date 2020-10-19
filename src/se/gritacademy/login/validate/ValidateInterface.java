package se.gritacademy.login.validate;

public interface ValidateInterface {

    boolean isPasswordValid(String password);
    boolean isEmailReallyValid(String email);
}
