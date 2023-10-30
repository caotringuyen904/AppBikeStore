/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.MultiView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MultiFXMLController implements Initializable {

    @FXML
    private Button btnClickScene2;
    @FXML
    private Label lbMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleClickToScene2(ActionEvent event) throws IOException, Exception {

        Utility.changeToScene(getClass(), event, "Scene2FXML.fxml");
        System.out.println("handleClickToScene2 method called"); // Add this line

    }

}
