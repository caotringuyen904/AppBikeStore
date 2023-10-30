/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.StaffsView;

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
public class StaffController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField staffIdInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField activeInput;
    @FXML
    private TextField storeIdInput;
    @FXML
    private TextField manageIdInput;
    @FXML
    private TableView<Staffs> staffTable;
    @FXML
    private TableColumn<Staffs, ?> staffIdCol;
    @FXML
    private TableColumn<Staffs, ?> firstNameCol;
    @FXML
    private TableColumn<Staffs, ?> lastNameCol;
    @FXML
    private TableColumn<Staffs, ?> emailCol;
    @FXML
    private TableColumn<Staffs, ?> phoneCol;
    @FXML
    private TableColumn<Staffs, ?> activeCol;
    @FXML
    private TableColumn<Staffs, ?> storeCol;
    @FXML
    private TableColumn<Staffs, ?> manageCol;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public StaffController () throws SQLException{
        this.data = new BikeStore(currentUser.getName(), currentUser.getPassword()); 
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
       for(Staffs i : this.data.getStaffData().getStaffs()){
            if(i.getStaffID() == Integer.valueOf(this.staffIdInput.getText())){ 
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
        Staffs temp = new Staffs();
        
        int affectRows;
        if(this.isInteger(this.staffIdInput.getText())&& this.isInteger(this.activeInput.getText())&& this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.manageIdInput.getText())){
            
            temp.setStaffID(Integer.valueOf(this.staffIdInput.getText()));  
            if(temp.getStaffID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setIsActive(Integer.valueOf(this.activeInput.getText()));
            temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));
            temp.setManagerID(Integer.valueOf(this.manageIdInput.getText()));
            
            
            temp.setFirstName(this.firstNameInput.getText());
            temp.setLastName(this.lastNameInput.getText());
            temp.setEmail(this.emailInput.getText());
            temp.setPhone(this.phoneInput.getText());
            
            
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
            this.alert.setText("ID and active status must be a number");
        }
        
    }


    public void handleDelete(ActionEvent event) throws SQLException {
        
        if(this.currentUser.getCanDelete()==false){
             alert.setText("This user don't have permission");
             return;
        }
           
        int result;
        
            if(this.isInteger(this.staffIdInput.getText())&& this.isInteger(this.activeInput.getText())&& this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.manageIdInput.getText())){
                if(this.isIDexist()){
                    Staffs temp = new Staffs();
                    temp.setStaffID(Integer.valueOf(this.staffIdInput.getText()));  
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
            if(this.isInteger(this.staffIdInput.getText())&& this.isInteger(this.activeInput.getText())&& this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.manageIdInput.getText())){
                if(this.isIDexist()){
                    Staffs temp = new Staffs();
                    temp.setStaffID(Integer.valueOf(this.staffIdInput.getText()));
                    
                    
                    check = this.data.update(temp, "store_id", Integer.parseInt(this.storeIdInput.getText())); 
                    check = this.data.update(temp, "manager_id", Integer.parseInt(this.manageIdInput.getText()));
                    if(check == -2){
                        alert.setText("Reference ID must be exist");
                        return;
                    }
                    check = this.data.update(temp, "active", Integer.parseInt(this.activeInput.getText().toString())); 
                    
                    check = this.data.update(temp, "first_name", this.firstNameInput.getText());
                    check = this.data.update(temp, "last_name", this.lastNameInput.getText());
                    check = this.data.update(temp, "email", this.emailInput.getText());
                    check = this.data.update(temp, "phone", this.phoneInput.getText());
                    
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
        this.staffIdInput.clear();
        this.firstNameInput.clear();
        this.lastNameInput.clear();
        this.emailInput.clear();
        this.phoneInput.clear();
        this.activeInput.clear();
        this.storeIdInput.clear();
        this.manageIdInput.clear();
               
        this.staffIdInput.setEditable(true);
    }


    public void handleLoad(ActionEvent event) {
        
        this.staffTable.getColumns().clear();
        
        this.staffIdCol.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.activeCol.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        this.storeCol.setCellValueFactory(new PropertyValueFactory<>("storeID"));
        this.manageCol.setCellValueFactory(new PropertyValueFactory<>("managerID"));
        
        this.staffTable.setItems(this.loadStaffs());
        
        this.staffTable.getColumns().add(staffIdCol);
        this.staffTable.getColumns().add(firstNameCol);
        this.staffTable.getColumns().add(lastNameCol);
        this.staffTable.getColumns().add(emailCol);
        this.staffTable.getColumns().add(phoneCol);
        this.staffTable.getColumns().add(activeCol);
        this.staffTable.getColumns().add(storeCol);
        this.staffTable.getColumns().add(manageCol);
        
        this.staffTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.staffIdInput.setText(String.valueOf(newSelection.getStaffID()));
                this.firstNameInput.setText(String.valueOf(newSelection.getFirstName()));
                this.lastNameInput.setText(String.valueOf(newSelection.getLastName()));
                this.emailInput.setText(String.valueOf(newSelection.getEmail()));
                this.phoneInput.setText(String.valueOf(newSelection.getPhone()));
                this.activeInput.setText(String.valueOf(newSelection.getIsActive()));
                this.storeIdInput.setText(String.valueOf(newSelection.getStoreID()));
                this.manageIdInput.setText(String.valueOf(newSelection.getManagerID()));
               
                this.staffIdInput.setEditable(false);
            }
        });
        
    }
    
    public ObservableList <Staffs> loadStaffs(){
        ObservableList<Staffs> staffs = FXCollections.observableArrayList();
        for(Staffs i : this.data.getStaffData().getStaffs()){
            staffs.add(i);
        }
        return staffs;
    }
    
}
