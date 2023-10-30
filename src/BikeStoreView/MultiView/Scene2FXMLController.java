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
public class Scene2FXMLController implements Initializable {

    @FXML
    private Label lbScene2;
    @FXML
    private Button btnReturnToMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void handleClickReturnToMain(ActionEvent event) throws IOException, Exception {

        System.out.println("handleClickReturnToMain method called"); // Add this line

        Utility.changeToScene (getClass(), event, "MultiFXML.fxml");
    }

}
