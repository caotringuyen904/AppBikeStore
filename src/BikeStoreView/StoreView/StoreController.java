/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.StoreView;


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
public class StoreController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField storeNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField stateInput;
    @FXML
    private TextField zipcodeInput;
    @FXML
    private TableView<Stores> storeTable;
    @FXML
    private TableColumn<Stores, String> storeIdCol;
    @FXML
    private TableColumn<Stores, String> storeCol;
    @FXML
    private TableColumn<Stores, String> phoneCol;
    @FXML
    private TableColumn<Stores, String> emailCol;
    @FXML
    private TableColumn<Stores, String> streetCol;
    @FXML
    private TableColumn<Stores, String> cityCol;
    @FXML
    private TableColumn<Stores, String> stateCol;
    @FXML
    private TableColumn<Stores, String> zipcodeCol;
    @FXML
    private TextField storeIdInput;
    @FXML
    private TextField streetInput;
    @FXML
    private TextField cityInput;
    
    private CurrentUser currentUser = CurrentUser.getUser();
    
    private BikeStore data;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button loadBtn;
    @FXML
    private Button resetBtn;

    /**
     * Initializes the controller class.
     */
    public StoreController() throws SQLException{
        this.data = new BikeStore(currentUser.getName(), currentUser.getPassword()); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean isIDexist(){
       for(Stores i : this.data.getStoreData().getStores()){
            if(i.getStoreID() == Integer.valueOf(this.storeIdInput.getText())){ 
                return true;
            };
        }
        return false;
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
    
    public void handleAdd(ActionEvent event) throws SQLException {
        
        if(this.currentUser.getCanInsert()==false){
            alert.setText("This user don't have permission");
            return;
        }  
        Stores temp = new Stores();
        
        int affectRows;
        if(this.isInteger(this.storeIdInput.getText())){
            
            temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));  
            if(temp.getStoreID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            
            temp.setStoreName(this.storeNameInput.getText());
            temp.setStreet(this.streetInput.getText());
            temp.setEmail(this.emailInput.getText());
            temp.setPhone(this.phoneInput.getText());
            temp.setCity(this.cityInput.getText());
            temp.setState(this.stateInput.getText());
            temp.setZipCode(this.zipcodeInput.getText());
            
            
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
        
            if(this.isInteger(this.storeIdInput.getText())){
                if(this.isIDexist()){
                    Stores temp = new Stores();
                    temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));  
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
       
            int check;
            if(this.isInteger(this.storeIdInput.getText())){
                if(this.isIDexist()){
                    Stores temp = new Stores();
                    temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));
                    
                    check = this.data.update(temp, "store_name", this.storeNameInput.getText());
                    check = this.data.update(temp, "phone", this.phoneInput.getText());
                    check = this.data.update(temp, "email", this.emailInput.getText());
                    check = this.data.update(temp, "street", this.streetInput.getText());
                    check = this.data.update(temp, "city", this.cityInput.getText());
                    check = this.data.update(temp, "state", this.stateInput.getText());
                    check = this.data.update(temp, "zip_code", this.zipcodeInput.getText());
                    
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
        this.storeIdInput.clear();
        this.storeNameInput.clear();
        this.emailInput.clear();
        this.phoneInput.clear();
        this.streetInput.clear();
        this.cityInput.clear();
        this.stateInput.clear();
        this.zipcodeInput.clear();
        
        this.storeIdInput.setEditable(true);
    }

   
    public void handleLoad(ActionEvent event) {
        
        this.storeTable.getColumns().clear();
        
        this.storeIdCol.setCellValueFactory(new PropertyValueFactory<>("storeID"));
        this.storeCol.setCellValueFactory(new PropertyValueFactory<>("storeName"));
        this.streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        this.zipcodeCol.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        
        this.storeTable.setItems(this.loadStores());
        
        this.storeTable.getColumns().add(storeIdCol);
        this.storeTable.getColumns().add(storeCol);
        this.storeTable.getColumns().add(streetCol);
        this.storeTable.getColumns().add(emailCol);
        this.storeTable.getColumns().add(phoneCol);
        this.storeTable.getColumns().add(cityCol);
        this.storeTable.getColumns().add(stateCol);
        this.storeTable.getColumns().add(zipcodeCol);
        
         this.storeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.storeIdInput.setText(String.valueOf(newSelection.getStoreID()));
                this.storeNameInput.setText(String.valueOf(newSelection.getStoreName()));
                this.streetInput.setText(String.valueOf(newSelection.getStreet()));
                this.emailInput.setText(String.valueOf(newSelection.getEmail()));
                this.phoneInput.setText(String.valueOf(newSelection.getPhone()));
                this.stateInput.setText(String.valueOf(newSelection.getState()));
                this.cityInput.setText(String.valueOf(newSelection.getCity()));
                this.zipcodeInput.setText(String.valueOf(newSelection.getZipCode()));
               
                this.storeIdInput.setEditable(false);
            }
        });
        
    }
    
    public ObservableList <Stores> loadStores(){
        ObservableList<Stores> stores = FXCollections.observableArrayList();
        for(Stores i : this.data.getStoreData().getStores()){
            stores.add(i);
        }
        return stores;
    }
    
}
