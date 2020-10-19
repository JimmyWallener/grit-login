package se.gritacademy.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import se.gritacademy.login.validate.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label message;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passWord;
    @FXML
    private Button submit;
    Validator v = new Validator();


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) { isEmailValid();
    }

    /*
    Checking if email address is correct directly with focusedProperty.
    Uses regex to sort through.
    */
    public void isEmailValid() {
        final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        email.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if (!email.getText().trim().matches(EMAIL_PATTERN)) {
                message.setText("Not valid Email");
                message.setTextFill(Color.RED);
            } else {
                message.setText("");
            }
        });
    }

    // Validates all inputs before sending data through. In this dummy it just displays success or error
    public void loginValidation() {

        submit.setOnAction(e -> {
            if (passWord.getText() != null && email.getText() != null) {
                if (v.isPasswordValid(passWord.getText().trim()) && v.isEmailReallyValid(email.getText().trim())) {
                    message.setText("Login Successful!");
                    message.setTextFill(Color.GREEN);
                } else {
                    message.setText("Not valid Password or Email");
                    message.setTextFill(Color.RED);
                }
            }
        });
    }}

