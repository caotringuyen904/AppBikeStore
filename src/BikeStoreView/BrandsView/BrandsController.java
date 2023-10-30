/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.BrandsView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import AppModel.*;
import BikeStoreView.LoginView.LoginController;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class BrandsController implements Initializable {

    @FXML
    private TextField brandIdInput;
    @FXML
    private TextField brandNameInput;
    @FXML
    private TableView<Brands> brandTable;
    @FXML
    private TableColumn<Brands, String> brandIdColumn;
    @FXML
    private TableColumn<Brands, String> brandNameColumn;
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
    @FXML
    private Label alert;
     
    private CurrentUser currentUser = CurrentUser.getUser();
    
    private BikeStore data;

    public BrandsController() throws SQLException {
        this.data = new BikeStore(currentUser.getName(), currentUser.getPassword()); 
    }
    
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    
    public ObservableList <Brands> loadBrands(){
        ObservableList<Brands> brands = FXCollections.observableArrayList();
        for(Brands i : this.data.getBrandData().getBrands()){
            brands.add(i);
        }
        return brands;
    }
    
    
    public void handleAdd(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanInsert()==false){
            alert.setText("This user don't have permission");
            return;
        }  
        Brands temp = new Brands();
        int affectRows;
        if(this.isInteger(this.brandIdInput.getText())){
            temp.setBrandID(Integer.valueOf(this.brandIdInput.getText()));
            if(temp.getBrandID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setBrandName(this.brandNameInput.getText().toString());
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
        if(this.brandIdInput.getText()==""){
            alert.setText("Delete required ID to reference");
        }
        else{
            if(this.isInteger(this.brandIdInput.getText())){
                if(this.isIDexist()){
                    Brands temp = new Brands(Integer.valueOf(this.brandIdInput.getText()), this.brandNameInput.getText());
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

    private boolean isIDexist(){
        for(Brands i : this.data.getBrandData().getBrands()){
            if(i.getBrandID() == Integer.valueOf(this.brandIdInput.getText())){ 
                return true;
            };
        }
        return false;
    }
    
    public void handleUpdate(ActionEvent event) throws SQLException {
        if(this.currentUser.getCanUpdate()==false){
            alert.setText("This user don't have permission");
            return;
        }
            
        int result;
        if(this.brandIdInput.getText()==""){
            alert.setText("Update required ID to reference");
        }
        else{
            if(this.isInteger(this.brandIdInput.getText())){
                if(this.isIDexist()){
                    Brands temp = new Brands(Integer.valueOf(this.brandIdInput.getText()), this.brandNameInput.getText());
                    result = this.data.update(temp, "brand_name", this.brandNameInput.getText());
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
        brandTable.getColumns().clear();
        brandIdColumn.setCellValueFactory(new PropertyValueFactory<>("brandID"));
        
        brandNameColumn.setCellValueFactory(new PropertyValueFactory<>("brandName"));

        brandTable.setItems(this.loadBrands());
        brandTable.getColumns().add(brandIdColumn);
        brandTable.getColumns().add(brandNameColumn);
        
        brandTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.brandIdInput.setText(String.valueOf(newSelection.getBrandID()));
                this.brandNameInput.setText(String.valueOf(newSelection.getBrandName()));
                this.brandIdInput.setEditable(false);
            }
        });
    }


    public void handleReset(ActionEvent event) {
        this.brandIdInput.setEditable(true);
        this.brandIdInput.clear();
        this.brandNameInput.clear();
    }
    
}
