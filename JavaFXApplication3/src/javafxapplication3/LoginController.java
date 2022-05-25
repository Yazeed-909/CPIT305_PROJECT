
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication3;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
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
public class LoginController implements Initializable {
    @FXML
    private Button Signin_button;
    @FXML
    private Label Email_error,Password_error;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Email;
    @FXML
    private Hyperlink Signup_link;
    @FXML
    private boolean Email_flag,Password_flag;
    @FXML
    
     public void Switch_to_register(ActionEvent event) throws IOException{
         
       FXMLLoader loader=new FXMLLoader(getClass().getResource("Register.fxml"));
       Parent root = loader.load();
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       Scene Scene=new Scene(root);
       stage.setScene(Scene);
       stage.show();
         
         
     }
  
   @FXML
   public void login(ActionEvent event) throws IOException{
       
       if(Check_for_errors()==true){
       FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       Parent root = loader.load();
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       Scene Scene=new Scene(root);
       stage.setScene(Scene);
       stage.show();
        

       }
     
   }
    public boolean Check_for_errors(){
        
         String errorStyle = String.format("-fx-border-color: RED; -fx-border-radius: 5;");
         String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-radius: 5;");
       
       if(Email.getText().isEmpty()){
         Email.setStyle(errorStyle);
         Email_error.setText("Enter a Email");
         Email_flag=false;
      }else if(!Email.getText().contains("@")){
         Email.setStyle(errorStyle);
         Email_error.setText("Enter a valid Email");
         Email_flag=false;
      }else{
           Email.setStyle(successStyle);
           Email_error.setText("");
           Email_flag=true;
           
      } 
      
      if(Password.getText().isEmpty()){
         Password.setStyle(errorStyle);
         Password_error.setText("Enter a Password");
         Password_flag=false;
      }
      else{
           Password.setStyle(successStyle);
           Password_error.setText("");
           Password_flag=true;
      } 
      
     
      
      
      
      
        return Password_flag==true&&Email_flag==true;
      
      
      
      
            
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        
        
    }    
    
    
}
