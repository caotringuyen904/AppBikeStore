/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.ProductView;


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
public class ProductController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField productIdInput;
    @FXML
    private TextField productNameInput;
    @FXML
    private TextField brandsIdInput;
    @FXML
    private TextField categoryIdInput;
    @FXML
    private TextField modelYearInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TableView<Products> productTable;
    @FXML
    private TableColumn<Products, String> productIdColumn;
    @FXML
    private TableColumn<Products, String> productNameColumn;
    @FXML
    private TableColumn<Products, String> brandIdColumn;
    @FXML
    private TableColumn<Products, String> categoryColumn;
    @FXML
    private TableColumn<Products, String> modelYearColumn;
    @FXML
    private TableColumn<Products, String> priceColumn;
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
    public ProductController () throws SQLException{
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
    
     private boolean isIDexist(){
       for(Products i : this.data.getProductData().getProducts()){
            if(i.getProductID() == Integer.valueOf(this.productIdInput.getText())){ 
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
        Products temp = new Products();
        
        int affectRows;
        if(this.isInteger(this.productIdInput.getText())&& this.isInteger(this.brandsIdInput.getText())&& this.isInteger(this.categoryIdInput.getText())){
            temp.setProductID(Integer.valueOf(this.productIdInput.getText()));
            if(temp.getProductID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setCategoryID(Integer.valueOf(this.categoryIdInput.getText()));
            temp.setBrandID(Integer.valueOf(this.brandsIdInput.getText()));
            temp.setModelYear(Integer.valueOf(this.modelYearInput.getText()));
            if(this.isFloat(this.priceInput.getText())== false){
                this.alert.setText("Price must be a real number");
                return;
            }
            temp.setListPrice(Float.valueOf(this.priceInput.getText()));
            
           
            
            temp.setProductName(this.productNameInput.getText());
            
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


    public void handleDetete(ActionEvent event) throws SQLException {
        
        if(this.currentUser.getCanDelete()==false){
             alert.setText("This user don't have permission");
             return;
        }
           
        int result;
        
            if(this.isInteger(this.categoryIdInput.getText())&& this.isInteger(this.brandsIdInput.getText())&& this.isInteger(this.modelYearInput.getText())){
                if(this.isIDexist()){
                    Products temp = new Products();
                    temp.setProductID(Integer.valueOf(this.productIdInput.getText()));  
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
        if(this.isFloat(this.priceInput.getText())==false){
                alert.setText("Price must be a real number");
                return;
            }
            int check;
            if(this.isInteger(this.categoryIdInput.getText())&& this.isInteger(this.brandsIdInput.getText())&& this.isInteger(this.modelYearInput.getText())){
                if(this.isIDexist()){
                    Products temp = new Products();
                    temp.setProductID(Integer.valueOf(this.productIdInput.getText()));
                    
                    
                    check = this.data.update(temp, "brand_id", Integer.parseInt(this.brandsIdInput.getText())); 
                    check = this.data.update(temp, "category_id", Integer.parseInt(this.categoryIdInput.getText()));
                    if(check == -2){
                        alert.setText("Reference ID must be exist");
                        return;
                    }
                    check = this.data.update(temp, "model_year", Integer.parseInt(this.modelYearInput.getText().toString()));
                    check = this.data.update(temp, "list_price", Float.parseFloat(this.priceInput.getText()));
                    check = this.data.update(temp, "product_name", this.productNameInput.getText());
                    
                    alert.setText("You 've adjusted 1 rows");
                }else{
                    alert.setText("ID must be exist");
                }
            }
            else{
                alert.setText("ID and model must be a number");
            }
        
    }


    public void handleReset(ActionEvent event) {
        this.productIdInput.clear();
        this.productNameInput.clear();
        this.brandsIdInput.clear();
        this.categoryIdInput.clear();
        this.modelYearInput.clear();
        this.priceInput.clear();
         this.productIdInput.setEditable(true);
        
    }


    public void handleLoad(ActionEvent event) {
        
        this.productTable.getColumns().clear();
        
        this.productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        this.productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        this.brandIdColumn.setCellValueFactory(new PropertyValueFactory<>("brandID"));
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        this.modelYearColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        
        this.productTable.setItems(this.loadProducts());
        
        this.productTable.getColumns().add(productIdColumn);
        this.productTable.getColumns().add(productNameColumn);
        this.productTable.getColumns().add(brandIdColumn);
        this.productTable.getColumns().add(categoryColumn);
        this.productTable.getColumns().add(modelYearColumn);
        this.productTable.getColumns().add(priceColumn);
        
        this.productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.productIdInput.setText(String.valueOf(newSelection.getProductID()));
                this.productNameInput.setText(String.valueOf(newSelection.getProductName()));
                this.brandsIdInput.setText(String.valueOf(newSelection.getBrandID()));
                this.categoryIdInput.setText(String.valueOf(newSelection.getCategoryID()));
                this.modelYearInput.setText(String.valueOf(newSelection.getModelYear()));
                this.priceInput.setText(String.valueOf(newSelection.getListPrice()));
               
                this.productIdInput.setEditable(false);
            }
        });
    }
    
    
    public ObservableList <Products> loadProducts(){
        ObservableList<Products> products = FXCollections.observableArrayList();
        for(Products i : this.data.getProductData().getProducts()){
            products.add(i);
        }
        return products;
    }
}
