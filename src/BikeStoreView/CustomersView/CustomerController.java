/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.CustomersView;

import AppModel.BikeStore;
import AppModel.Categories;
import AppModel.CurrentUser;
import AppModel.Customers;
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
public class CustomerController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField customerIdInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField streetInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField stateInput;
    @FXML
    private TextField zipcodeInput;
    @FXML
    private TableView<Customers> customerTable;
    @FXML
    private TableColumn<Customers, String> cusIdCol;
    @FXML
    private TableColumn<Customers, String> firstNameCol;
    @FXML
    private TableColumn<Customers, String> lastNameCol;
    @FXML
    private TableColumn<Customers, String> phoneCol;
    @FXML
    private TableColumn<Customers, String> emailCol;
    @FXML
    private TableColumn<Customers, String> streetCol;
    @FXML
    private TableColumn<Customers, String> cityCol;
    @FXML
    private TableColumn<Customers, String> stateCol;
    @FXML
    private TableColumn<Customers, String> zipcodeCol;
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
    
    public CustomerController() throws SQLException{
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
   
  
    
    private boolean isIDexist(){
        for(Customers i : this.data.getCustomerData().getCustomers()){
            if(i.getCustomerID() == Integer.valueOf(this.customerIdInput.getText())){ 
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
        Customers temp = new Customers();
        int affectRows;
        if(this.isInteger(this.customerIdInput.getText())){
            temp.setCustomerID(Integer.valueOf(this.customerIdInput.getText()));
            if(temp.getCustomerID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setFirstName(this.firstNameInput.getText().toString());
            temp.setLastName(this.lastNameInput.getText().toString());
            temp.setEmail(this.emailInput.getText().toString());
            temp.setPhone(this.phoneInput.getText().toString());
            temp.setStreet(this.streetInput.getText().toString());
            temp.setCity(this.cityInput.getText().toString());
            temp.setState(this.stateInput.getText().toString());
            temp.setZipCode(this.zipcodeInput.getText().toString());
            
            
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

    
    public void handelDelete(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanDelete()==false){
             alert.setText("This user don't have permission");
             return;
        }
           
        int result;
        if(this.customerIdInput.getText()==""){
            alert.setText("Delete required ID to reference");
        }
        else{
            if(this.isInteger(this.customerIdInput.getText())){
                if(this.isIDexist()){
                    Customers temp = new Customers();
                    temp.setCustomerID(Integer.valueOf(this.customerIdInput.getText()));
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
        
    }

    
    public void handleUpdate(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanUpdate()==false){
            alert.setText("This user don't have permission");
            return;
        }
            
        int result = 0;
        if(this.customerIdInput.getText()==""){
            alert.setText("Update required ID to reference");
        }
        else{
            if(this.isInteger(this.customerIdInput.getText())){
                if(this.isIDexist()){
                    Customers temp = new Customers();
                    for(Customers i: this.data.getCustomerData().getCustomers()){
                        if(i.getCustomerID() == Integer.valueOf(this.customerIdInput.getText().toString())){
                            temp = i;
                            break;
                        }
                    }
                    result = this.data.update(temp, "first_name", this.firstNameInput.getText());
                    result = this.data.update(temp, "last_name", this.lastNameInput.getText());
                    result = this.data.update(temp, "phone", this.phoneInput.getText());
                    result = this.data.update(temp, "email", this.emailInput.getText());
                    result = this.data.update(temp, "street", this.streetInput.getText());
                    result = this.data.update(temp, "city", this.cityInput.getText());
                    result = this.data.update(temp, "state", this.stateInput.getText());
                    result = this.data.update(temp, "zip_code", this.zipcodeInput.getText());
                    alert.setText("You 've adjusted " + result + " rows");
                }else{
                    alert.setText("Update Row must exist");
                }
            }
            else{
                alert.setText("ID must be a number");
            }
        }
        
    }


    public void handleLoad(ActionEvent event) {
        this.customerTable.getColumns().clear();
        this.cusIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        this.cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        this.zipcodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        
        this.customerTable.setItems(this.loadCustomers());
        this.customerTable.getColumns().add(cusIdCol);
        this.customerTable.getColumns().add(firstNameCol);
        this.customerTable.getColumns().add(lastNameCol);
        this.customerTable.getColumns().add(emailCol);
        this.customerTable.getColumns().add(phoneCol);
        this.customerTable.getColumns().add(streetCol);
        this.customerTable.getColumns().add(cityCol);
        this.customerTable.getColumns().add(stateCol);
        this.customerTable.getColumns().add(zipcodeCol);
        
        
        this.customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.customerIdInput.setText(String.valueOf(newSelection.getCustomerID()));
                this.firstNameInput.setText(String.valueOf(newSelection.getFirstName()));
                this.lastNameInput.setText(String.valueOf(newSelection.getLastName()));
                this.phoneInput.setText(String.valueOf(newSelection.getPhone()));
                this.emailInput.setText(String.valueOf(newSelection.getEmail()));
                this.streetInput.setText(String.valueOf(newSelection.getStreet()));
                this.cityInput.setText(String.valueOf(newSelection.getCity()));
                this.zipcodeInput.setText(String.valueOf(newSelection.getZipCode()));
                this.customerIdInput.setEditable(false);
            }
        });
        
    }

    public ObservableList <Customers> loadCustomers(){
        ObservableList<Customers> customers = FXCollections.observableArrayList();
        for(Customers i : this.data.getCustomerData().getCustomers()){
            customers.add(i);
        }
        return customers;
    }
    
    public void handleReset(ActionEvent event) {
        this.customerIdInput.clear();
        this.firstNameInput.clear();
        this.lastNameInput.clear();
        this.phoneInput.clear();
        this.emailInput.clear();
        this.streetInput.clear();
        this.cityInput.clear();
        this.stateInput.clear();
        this.zipcodeInput.clear();
        this.customerIdInput.setEditable(true);
    }
    
}
