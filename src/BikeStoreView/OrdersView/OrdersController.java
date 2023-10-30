/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.OrdersView;


import AppModel.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
public class OrdersController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField orderIdInput;
    @FXML
    private TextField customerIdInput;
    @FXML
    private TextField statusInput;
    @FXML
    private TextField orderDateInput;
    @FXML
    private TextField requiredDateInput;
    @FXML
    private TextField shippedDateInput;
    @FXML
    private TextField storeIdInput;
    @FXML
    private TextField staffIdInput;
    @FXML
    private TableView<Orders> orderTable;

    @FXML
    private TableColumn<Orders, String> orderIdCol;
    @FXML
    private TableColumn<Orders, String> customerIdCol;
    @FXML
    private TableColumn<Orders, String> statusCol;
    @FXML
    private TableColumn<Orders, String> shippedDateCol;
    @FXML
    private TableColumn<Orders, String> orderDateCol;
    @FXML
    private TableColumn<Orders, String> requiredDateCol;
    @FXML
    private TableColumn<Orders, String> storeIdCol;
    @FXML
    private TableColumn<Orders, String> staffIdCol;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnLoad;
    
    private CurrentUser currentUser = CurrentUser.getUser();
    
    private BikeStore data;

    /**
     * Initializes the controller class.
     */
    public OrdersController() throws SQLException{
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
    
    private boolean isDate(String s){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            formatter.parse(s);
            return true;
        }
        catch(Exception e){
            return false;
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
    
    public void handleAdd(ActionEvent event) throws SQLException {
        
        if(this.currentUser.getCanInsert()==false){
            alert.setText("This user don't have permission");
            return;
        }  
        Orders temp = new Orders();
        int affectRows;
        if(this.isInteger(this.customerIdInput.getText())&& this.isInteger(this.orderIdInput.getText())&& this.isInteger(this.staffIdInput.getText())){
            temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));
            if(temp.getOrderID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setStaffID(Integer.valueOf(this.staffIdInput.getText()));
            temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));
            temp.setOrderStatus(Integer.valueOf(this.statusInput.getText()));
            temp.setCustomerID(Integer.valueOf(this.customerIdInput.getText()));
            temp.setOrderDate(this.orderDateInput.getText());
            temp.setRequiredDate(this.requiredDateInput.getText());
            temp.setShippedDate(this.shippedDateInput.getText());
            
            if(this.isDate(temp.getOrderDate())==false || this.isDate(temp.getRequiredDate())==false || this.isDate(temp.getShippedDate())==false){
                alert.setText("Date must be yyyy-MM-dd");
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
        
            if(this.isInteger(this.customerIdInput.getText())&& this.isInteger(this.orderIdInput.getText())&& this.isInteger(this.staffIdInput.getText())){
                if(this.isIDexist()){
                    Orders temp = new Orders();
                    temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));  
                    result = this.data.delete(temp);
                    alert.setText("You 've deleted and modified relational" + result + " rows");
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
        if(this.isDate(this.orderDateInput.getText())==false || this.isDate(this.requiredDateInput.getText())==false || this.isDate(this.shippedDateInput.getText())==false){
                alert.setText("Date must be yyyy-MM-dd");
                return;
            }
            int check;
            if(this.isInteger(this.customerIdInput.getText())&& this.isInteger(this.orderIdInput.getText())&& this.isInteger(this.staffIdInput.getText())){
                if(this.isIDexist()){
                    Orders temp = new Orders();
                    temp.setOrderID(Integer.valueOf(this.orderIdInput.getText()));
                    
                    
                    check = this.data.update(temp, "customer_id", Integer.parseInt(this.customerIdInput.getText())); 
                    check = this.data.update(temp, "store_id", Integer.parseInt(this.storeIdInput.getText()));
                    check = this.data.update(temp, "staff_id", Integer.parseInt(this.staffIdInput.getText()));
                    if(check == -2){
                        alert.setText("Reference ID must be exist");
                        return;
                    }
                    check = this.data.update(temp, "order_date", this.orderDateInput.getText().toString());
                    check = this.data.update(temp, "required_date", this.requiredDateInput.getText().toString());
                    check = this.data.update(temp, "shipped_date", this.shippedDateInput.getText().toString());
                    check = this.data.update(temp, "order_status", Integer.parseInt(this.statusInput.getText()));
                    alert.setText("You 've adjusted 1 rows");
                }else{
                    alert.setText("ID must be exist");
                }
            }
            else{
                alert.setText("ID must be a number");
            }
        
    }

 
    public void handleReset(ActionEvent event) {
        this.orderIdInput.clear();
        this.customerIdInput.clear();
        this.orderDateInput.clear();
        this.storeIdInput.clear();
        this.statusInput.clear();
        this.staffIdInput.clear();
        this.shippedDateInput.clear();
        this.orderDateInput.clear();
        this.requiredDateInput.clear();
        this.customerIdInput.setEditable(true);
    }


    public void handleLoad(ActionEvent event) {
        
        this.orderTable.getColumns().clear();
        
        this.orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        this.customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        this.statusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        this.orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        this.requiredDateCol.setCellValueFactory(new PropertyValueFactory<>("requiredDate"));
        this.shippedDateCol.setCellValueFactory(new PropertyValueFactory<>("shippedDate"));
        this.storeIdCol.setCellValueFactory(new PropertyValueFactory<>("storeID"));
        this.staffIdCol.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        
        this.orderTable.setItems(this.loadOrders());
        this.orderTable.getColumns().add(orderIdCol);
        this.orderTable.getColumns().add(customerIdCol);
        this.orderTable.getColumns().add(statusCol);
        this.orderTable.getColumns().add(orderDateCol);
        this.orderTable.getColumns().add(requiredDateCol);
        this.orderTable.getColumns().add(shippedDateCol);
        this.orderTable.getColumns().add(storeIdCol);
        this.orderTable.getColumns().add(staffIdCol);
        
        
        this.orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.orderIdInput.setText(String.valueOf(newSelection.getOrderID()));
                this.customerIdInput.setText(String.valueOf(newSelection.getCustomerID()));
                this.statusInput.setText(String.valueOf(newSelection.getOrderStatus()));
                this.orderDateInput.setText(String.valueOf(newSelection.getOrderDate()));
                this.requiredDateInput.setText(String.valueOf(newSelection.getRequiredDate()));
                this.shippedDateInput.setText(String.valueOf(newSelection.getShippedDate()));
                this.storeIdInput.setText(String.valueOf(newSelection.getStoreID()));
                this.staffIdInput.setText(String.valueOf(newSelection.getStaffID()));
                this.orderIdInput.setEditable(false);
            }
        });
    }
    
    public ObservableList <Orders> loadOrders(){
        ObservableList<Orders> orders = FXCollections.observableArrayList();
        for(Orders i : this.data.getOrderData().getOrders()){
            orders.add(i);
        }
        return orders;
    }
    
}
