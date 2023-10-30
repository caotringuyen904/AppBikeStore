/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BikeStoreView.MultiView;

/**
 *
 * @author Admin
 */
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MichaelO
 */
public class Utility 
{
    // Example use:
    //  Utility.changeToScene (getClass(), event, "myXLM.fxml");
    public static void changeToScene(Class aClass, Event aEvent, String sceneFileStr) throws Exception
    {
        Parent root = FXMLLoader.load (aClass.getResource(sceneFileStr));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) aEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();  
    }
}