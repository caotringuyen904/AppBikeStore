package BikeStoreView.ManageView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import AppModel.*;

public class ManageController implements Initializable {

    @FXML
    private ListView<String> listViewTable;
    @FXML
    private AnchorPane itemDetailsAnchorPane;

    private HashMap<String, String> folderToFxmlMapping;
    
    private CurrentUser currentUser = CurrentUser.getUser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        folderToFxmlMapping = new HashMap<>();
        folderToFxmlMapping.put("ProductView", "Product.fxml");
        folderToFxmlMapping.put("CustomersView", "Customer.fxml");
        folderToFxmlMapping.put("StoreView", "Store.fxml");
        folderToFxmlMapping.put("StaffsView", "Staff.fxml");
        folderToFxmlMapping.put("BrandsView", "Brands.fxml");
        folderToFxmlMapping.put("CategoriesView", "Categories.fxml");
        folderToFxmlMapping.put("OrderItemView", "OrderItem.fxml");
        folderToFxmlMapping.put("OrdersView", "Orders.fxml");
        folderToFxmlMapping.put("StocksView", "Stock.fxml");

        ObservableList<String> data = FXCollections.observableArrayList(
                "ProductView",
                "CustomersView",
                "StoreView",
                "StaffsView",
                "BrandsView",
                "CategoriesView",
                "OrderItemView",
                "OrdersView",
                "StocksView"
        );
        listViewTable.setItems(data);

        // Select "ProductView" by default
        listViewTable.getSelectionModel().select("ProductView");

        // Load the default view
        loadDefaultView();

        // Set up the listener for selecting items in the listViewTable
        setupListViewListener();

    }

    private void loadDefaultView() {
        String defaultItem = "ProductView";
        String fxmlFileName = folderToFxmlMapping.get(defaultItem);

        if (fxmlFileName != null) {
            // Construct the correct path to the FXML file relative to the "src" folder
            String fxmlFile = "/BikeStoreView/" + defaultItem + "/" + fxmlFileName;
            URL location = getClass().getResource(fxmlFile);

            if (location != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(location);
                    Node component = loader.load();
                    itemDetailsAnchorPane.getChildren().setAll(component);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("FXML file not found: " + fxmlFile);
            }
        } else {
            System.err.println("Mapping not found for: " + defaultItem);
        }
    }

    private void setupListViewListener() {
        listViewTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    String fxmlFileName = folderToFxmlMapping.get(newValue);
                    if (fxmlFileName != null) {
                        // Construct the correct path to the FXML file relative to the "src" folder
                        String fxmlFile = "/BikeStoreView/" + newValue + "/" + fxmlFileName;
                        URL location = getClass().getResource(fxmlFile);

                        if (location != null) {
                            FXMLLoader loader = new FXMLLoader(location);
                            Node component = loader.load();
                            itemDetailsAnchorPane.getChildren().setAll(component);
                        } else {
                            System.err.println("FXML file not found: " + fxmlFile);
                        }
                    } else {
                        System.err.println("Mapping not found for: " + newValue);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
