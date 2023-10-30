/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.LoginView;
import AppModel.BikeStore;
import AppModel.CurrentUser;
import AppModel.User;
import java.sql.*;
import BikeStoreView.MultiView.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import DB_Connection.DBConnection;
import DB_Connection.UserData;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private TextField inputUserName;
    private TextField inputPassword;

    @FXML
    private Button btnLogin;
    @FXML
    private Object errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox showPasswordCheckBox;
    @FXML
    private TextField visiblePasswordField;
    
    private UserData validation;
    
    private CurrentUser currentUser = CurrentUser.getUser();
   

    /**
     * Initializes the controller class.
     */
   
    public LoginController() throws SQLException{
        this.validation = new UserData();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleClickCheckBox();
    }

    public void handleClickCheckBox() {
        // Set up the event handler for the "Show Password" checkbox
        showPasswordCheckBox.setOnAction(event -> {
            boolean showPassword = showPasswordCheckBox.isSelected();
            passwordField.setVisible(!showPassword);
            visiblePasswordField.setVisible(showPassword);
            showPasswordCheckBox.setText(showPassword ? "Show" : "Hide");

            // When "Show Password" is checked, copy the password to the visible field
            if (showPassword) {
                visiblePasswordField.setText(passwordField.getText());
            } else {
                visiblePasswordField.clear();
            }
        });
    }

    public void setErrorMessage(String message, boolean visible) {
        Label label = (Label) errorLabel;
        label.setText(message);
        label.setVisible(visible);
    }
    
    public boolean isLoginValid(String username, String password) {
        boolean result=false;
        for(User i : validation.users){
            if(i.getUserName().toString().equals(username) && i.getPassword().toString().equals(password)){
                result = true;
                this.currentUser.setName(i.getUserName().toString());
                this.currentUser.setPassword(i.getPassword().toString());
                this.currentUser.setCanInsert(Boolean.valueOf(i.getCanInsert()));
                this.currentUser.setCanUpdate(Boolean.valueOf(i.getCanUpdate()));
                this.currentUser.setCanDelete(Boolean.valueOf(i.getCanDelete()));
                return result;
            }
        }
        return result;  
    }
    
    public void handleLogin(ActionEvent event) throws Exception {
        String Username = this.inputUserName.getText();
        String Password = this.passwordField.getText();
        
        if(this.isLoginValid(Username, Password)){
          
            Utility.changeToScene(getClass(), event, "../ManageView/Manage.fxml");
            
        }
        else{
            setErrorMessage("Username or password is wrong.", true);
        }
  
    }

}
