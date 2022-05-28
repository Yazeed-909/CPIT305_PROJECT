
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication3;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
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
import javafx.scene.control.Alert;
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
public class LoginController implements Initializable {

    @FXML
    private Button Signin_button;
    @FXML
    private Label Email_error, Password_error;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Email;
    @FXML
    private Hyperlink Signup_link;
    @FXML
    private boolean Email_flag, Password_flag;

    @FXML

    public void Switch_to_register(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene Scene = new Scene(root);
        stage.setScene(Scene);
        stage.show();

    }

    @FXML
    public void login(ActionEvent event) {

        if (Check_for_errors() == true) {

            try {
                DB d = DB.getInstance();
                UserINFO userINFO = d.retrieveUser(Email.getText());
                if (userINFO == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "User not found");

                    alert.showAndWait();

                } else if (!userINFO.getPassword().equals(Password.getText())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong password");
                    alert.showAndWait();
                } else {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    FXMLDocumentController controller = loader.getController();
                    controller.userinfo = userINFO;
                    Scene Scene = new Scene(root);
                    stage.setScene(Scene);
                    stage.show();
                }

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

        return Password_flag == true && Email_flag == true;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
