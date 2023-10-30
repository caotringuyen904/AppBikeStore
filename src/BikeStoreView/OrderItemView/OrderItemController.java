/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.OrderItemView;

import AppModel.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class OrderItemController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField orderIdInput;
    @FXML
    private TextField itemIdInput;
    @FXML
    private TextField productIdInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private TextField listPriceInput;
    @FXML
    private TextField discountInput;
    @FXML
    private TableView<OrderItems> OderItemsTable;
    @FXML
    private TableColumn<OrderItems, String> orderIdCol;
    @FXML
    private TableColumn<OrderItems, String> itemIdCol;
    @FXML
    private TableColumn<OrderItems, String> productIdCol;
    @FXML
    private TableColumn<OrderItems, String> quantityCol;
    @FXML
    private TableColumn<OrderItems, String> listPriceCol;
    @FXML
    private TableColumn<OrderItems, String> discountCol;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnReset;
    
    private CurrentUser currentUser = CurrentUser.getUser();
    
    private BikeStore data;

    /**
     * Initializes the controller class.
     */
    public OrderItemController () throws SQLException{
        this.data = new BikeStore(currentUser.getName(), currentUser.getPassword()); 
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    private boolean isInteger(String s){
        try{
            Integer.valueOf(s);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    private boolean isFloat(String s){
        try{
            Float.valueOf(s);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public void handleAdd(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanInsert()==false){
            alert.setText("This user don't have permission");
            return;
        }  
        OrderItems temp = new OrderItems();
        int affectRows;
        if(this.isInteger(this.itemIdInput.getText())&& this.isInteger(this.orderIdInput.getText())&& this.isInteger(this.productIdInput.getText())){
            temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));
            temp.setProductID(Integer.valueOf(this.productIdInput.getText()));
            temp.setItemID(Integer.valueOf(this.itemIdInput.getText()));
            if(temp.getOrderID()<=0 || temp.getItemID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setQuantity(Integer.valueOf(this.quantityInput.getText()));
            temp.setListPrice(Float.valueOf(this.listPriceInput.getText()));
            temp.setDiscount(Float.valueOf(this.discountInput.getText()));
            if(this.isFloat(this.listPriceInput.getText())==false || this.isFloat(this.discountInput.getText())==false){
                alert.setText("List Price and Discount must be a real number");
                return;
            }
            affectRows = this.data.insert(temp);  
            if(affectRows == -1){
                alert.setText("The ID cannot be dubplicated");
            }
            else if(affectRows == -2){
                alert.setText("The reference id must be exist");
            }
            else alert.setText("You 've add " + affectRows + "more rows");
        }
        else{
            this.alert.setText("ID must be a number");
        }
    }


    public void handleDelete(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanDelete()==false){
             alert.setText("This user don't have permission");
             return;
        }
           
        int result;
        
            if(this.isInteger(this.orderIdInput.getText()) && this.isInteger(this.itemIdInput.getText())){
                if(this.isIDexist()){
                    OrderItems temp = new OrderItems();
                    temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));
                    temp.setItemID(Integer.valueOf(this.itemIdInput.getText()));
                    result = this.data.delete(temp);
                    alert.setText("You 've deleted and modified relational " + result + " rows");
                }else{
                    alert.setText("Delete Row must exist");
                }
            }
            else{
                alert.setText("ID must be a number");
            }
        
    }


    public void handleUpdate(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanUpdate()==false){
            alert.setText("This user don't have permission");
            return;
        }
            
        int result = 0;
      

            if(this.isInteger(this.productIdInput.getText())){
                if(this.isIDexist()){
                    OrderItems temp = new OrderItems();
                    temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));
                    temp.setItemID(Integer.valueOf(this.itemIdInput.getText()));
                    
                    result = this.data.update(temp, "product_id", Integer.parseInt(this.productIdInput.getText()));
                    if(result == -2){
                        alert.setText("Product ID must be exist");
                        return;
                    }
                    result = this.data.update(temp, "quantity", Integer.parseInt(this.quantityInput.getText()));
                    if(this.isFloat(this.listPriceInput.getText())==false || this.isFloat(this.discountInput.getText())==false){
                            alert.setText("List Price and Discount must be a real number");
                            return;
                        }
                    result = this.data.update(temp, "list_price", Float.parseFloat(this.listPriceInput.getText()));
                    result = this.data.update(temp, "discount", Float.parseFloat(this.discountInput.getText()));
                    alert.setText("You 've adjusted " + result + " rows");
                }else{
                    alert.setText("ID must be exist");
                }
            }
            else{
                alert.setText("ID must be a number");
            }
    }

    private boolean isIDexist(){
 
        for(Orders i : this.data.getOrderData().getOrders()){
            if(i.getOrderID() == Integer.valueOf(this.orderIdInput.getText())){ 
                return true;
            };
        }
        return false;
    }

    public void handleLoad(ActionEvent event) {
        
        this.OderItemsTable.getColumns().clear();
        
        this.orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        this.itemIdCol.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        this.productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        this.quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        this.listPriceCol.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        this.discountCol.setCellValueFactory(new PropertyValueFactory<>("discount"));
        
        
        this.OderItemsTable.setItems(this.loadOrderItems());
        this.OderItemsTable.getColumns().add(orderIdCol);
        this.OderItemsTable.getColumns().add(itemIdCol);
        this.OderItemsTable.getColumns().add(productIdCol);
        this.OderItemsTable.getColumns().add(quantityCol);
        this.OderItemsTable.getColumns().add(listPriceCol);
        this.OderItemsTable.getColumns().add(discountCol);
        
        
        this.OderItemsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.orderIdInput.setText(String.valueOf(newSelection.getOrderID()));
                this.itemIdInput.setText(String.valueOf(newSelection.getItemID()));
                this.productIdInput.setText(String.valueOf(newSelection.getProductID()));
                this.quantityInput.setText(String.valueOf(newSelection.getQuantity()));
                this.listPriceInput.setText(String.valueOf(newSelection.getListPrice()));
                this.discountInput.setText(String.valueOf(newSelection.getDiscount()));
                orderIdInput.setEditable(false);
                itemIdInput.setEditable(false);
            }
        });
        
    }

    public ObservableList <OrderItems> loadOrderItems(){
        ObservableList<OrderItems> orderItems = FXCollections.observableArrayList();
        for(OrderItems i : this.data.getOrderItemData().getOrderItems()){
            orderItems.add(i);
        }
        return orderItems;
    }
    
    public void handleReset(ActionEvent event) {
        orderIdInput.setEditable(true);
        itemIdInput.setEditable(true);
        this.orderIdInput.clear();
        this.itemIdInput.clear();
        this.productIdInput.clear();
        this.quantityInput.clear();
        this.discountInput.clear();
        this.listPriceInput.clear();
    }
    
}
