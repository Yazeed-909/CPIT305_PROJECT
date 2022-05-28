/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication3;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author yzeed
 */
public class RegisterController implements Initializable {

    @FXML
    private Button Signup_button;
    @FXML
    private Label Email_error, Fullname_error, Password_error, Birthday_error, Gender_error;
    @FXML
    private ComboBox<String> Gender;
    @FXML
    private DatePicker Birthday;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Email, Fullname;
    @FXML
    private Hyperlink Signin_link;
    private boolean Email_flag, Name_flag, Birthday_flag, Gender_flag, Password_flag;

    public void Switch_to_login(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(root);
        stage.setScene(Scene);
        stage.show();

    }

    @FXML
    public void Register(ActionEvent event) throws IOException {

        if (Check_for_errors() == true) {

            DB a = DB.getInstance();

            Date date = Date.from(Birthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            try {
                a.addUser(Fullname.getText(), Email.getText(), Password.getText(), date, Gender.getValue());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root = loader.load();
                FXMLDocumentController controller = loader.getController();
                controller.userinfo = a.retrieveUser(Email.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene Scene = new Scene(root);
                stage.setScene(Scene);
                stage.show();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public boolean Check_for_errors() {

        String errorStyle = String.format("-fx-border-color: RED; -fx-border-radius: 5;");
        String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-radius: 5;");

        if (Email.getText().isEmpty()) {
            Email.setStyle(errorStyle);
            Email_error.setText("Enter a Email");
            Email_flag = false;
        } else if (!Email.getText().contains("@")) {
            Email.setStyle(errorStyle);
            Email_error.setText("Enter a valid Email");
            Email_flag = false;
        } else {
            Email.setStyle(successStyle);
            Email_error.setText("");
            Email_flag = true;

        }

        if (Password.getText().isEmpty()) {
            Password.setStyle(errorStyle);
            Password_error.setText("Enter a Password");
            Password_flag = false;
        } else {
            Password.setStyle(successStyle);
            Password_error.setText("");
            Password_flag = true;
        }

        if (Birthday.getValue() == null) {
            Birthday.setStyle(errorStyle);
            Birthday_error.setText("Select a Birthday");
            Birthday_flag = false;
        } else {
            Birthday.setStyle(successStyle);
            Birthday_error.setText("");
            Birthday_flag = true;
        }

        if (Fullname.getText().isEmpty()) {
            Fullname.setStyle(errorStyle);
            Fullname_error.setText("Enter your Name");
            Name_flag = false;
        } else {
            Fullname.setStyle(successStyle);
            Fullname_error.setText("");
            Name_flag = true;
        }

        if (Gender.getValue() == null) {
            Gender.setStyle(errorStyle);
            Gender_error.setText("Select a gender");
            Gender_flag = false;
        } else {
            Gender.setStyle(successStyle);
            Gender_error.setText("");
            Gender_flag = true;
        }

        if (Gender_flag == true && Name_flag == true && Password_flag == true && Gender_flag == true && Birthday_flag == true && Email_flag == true) {
            return true;
        } else {

            return false;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gender.getItems().addAll("Male", "Female");

    }

}
