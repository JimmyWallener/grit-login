package se.gritacademy.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.apache.commons.validator.routines.EmailValidator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

    @FXML
    private Label message;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button submit;
    private final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) { isEmailValid();
    }

    // Checking if email address is correct directly with focusedProperty. AtomicBoolean updates state in response.
    // Uses regex to sort through.
    public AtomicBoolean isEmailValid() {
        AtomicBoolean isCorrectEmail = new AtomicBoolean(true);
        email.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if (!email.getText().matches(EMAIL_PATTERN)) {
                message.setText("Not valid Email");
                message.setTextFill(Color.RED);
                isCorrectEmail.set(false);
            } else {
                message.setText("");
            }
        });
        return isCorrectEmail;
    }
    // Regex for checking if password contains requirements 2 digits,
    // 2 lowercase, 2 uppercase, 1 special char and is atleast 8 chars in length

    public boolean isPassWordValid() {
        final String PASSWORD_PATTERN = "(?=^.{8}$)(?=(.*\\d){2})(?=.*[a-z].*[a-z])(?=.*[A-Z].*[A-Z])(?=.*[!@#$%^&+=])(?!.*[\\s])^.*";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passWord.getText());
        System.out.println(passWord.getText());
        System.out.println(matcher.matches());
        return matcher.matches();

    }

    // Validates all inputs before sending data through. In this dummy it just displays success or error
    public void loginValidation() {

        submit.setOnAction(e -> {
            System.out.println(isEmailValidSecond());
            if (passWord.getText() != null && email.getText() != null) {
                if (isPassWordValid() && isEmailValid().get()) {
                    message.setText("Login Successful!");
                    message.setTextFill(Color.GREEN);
                } else {
                    message.setText("Not valid Password");
                    message.setTextFill(Color.RED);
                }
            }
        });
    }

    // Second way to check if email is valid by using commons-validator dependency. jar_files.zip contains needed files
    public boolean isEmailValidSecond() {
        return EmailValidator.getInstance(true).isValid(email.getText());
    }
}
