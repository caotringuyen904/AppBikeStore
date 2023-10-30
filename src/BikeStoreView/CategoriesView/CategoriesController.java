/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.CategoriesView;

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
public class CategoriesController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField categoryIdInput;
    @FXML
    private TextField categoryNameInput;
    @FXML
    private TableView<Categories> CategoriesTable;
    @FXML
    private TableColumn<Categories, String> categoryIdCol;
    @FXML
    private TableColumn<Categories, String> categoryNameCol;
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
    public CategoriesController() throws SQLException{
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
        Categories temp = new Categories();
        int affectRows;
        if(this.isInteger(this.categoryIdInput.getText())){
            temp.setCategoryID(Integer.valueOf(this.categoryIdInput.getText()));
            if(temp.getCategoryID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setCategoryName(this.categoryNameInput.getText().toString());
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
        if(this.categoryIdInput.getText()==""){
            alert.setText("Delete required ID to reference");
        }
        else{
            if(this.isInteger(this.categoryIdInput.getText())){
                if(this.isIDexist()){
                    Categories temp = new Categories(Integer.valueOf(this.categoryIdInput.getText()), this.categoryNameInput.getText());
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
    }

    private boolean isIDexist(){
        for(Categories i : this.data.getCategoryData().getCategories()){
            if(i.getCategoryID() == Integer.valueOf(this.categoryIdInput.getText())){ 
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
        if(this.categoryIdInput.getText()==""){
            alert.setText("Update required ID to reference");
        }
        else{
            if(this.isInteger(this.categoryIdInput.getText())){
                if(this.isIDexist()){
                    Categories temp = new Categories(Integer.valueOf(this.categoryIdInput.getText()), this.categoryNameInput.getText());
                    result = this.data.update(temp, "category_name", this.categoryNameInput.getText());
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
        this.CategoriesTable.getColumns().clear();
        this.categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        this.categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        
        this.CategoriesTable.setItems(this.loadCategories());
        this.CategoriesTable.getColumns().add(categoryIdCol);
        this.CategoriesTable.getColumns().add(categoryNameCol);
        
        this.CategoriesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.categoryIdInput.setText(String.valueOf(newSelection.getCategoryID()));
                this.categoryNameInput.setText(String.valueOf(newSelection.getCategoryName()));
                this.categoryIdInput.setEditable(false);
            }
        });
        
    }

    
    
    
    public ObservableList <Categories> loadCategories(){
        ObservableList<Categories> categories = FXCollections.observableArrayList();
        for(Categories i : this.data.getCategoryData().getCategories()){
            categories.add(i);
        }
        return categories;
    }
    
    
    
    
    public void handleReset(ActionEvent event) {
        this.categoryIdInput.clear();
        this.categoryNameInput.clear();
        this.categoryIdInput.setEditable(true);
    }
    
}
