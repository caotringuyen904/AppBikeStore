/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BikeStoreView.StocksView;

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
public class StockController implements Initializable {
    @FXML
    private Label alert;
    @FXML
    private TextField storeIdInput;
    @FXML
    private TextField productIdInput;
    @FXML
    private TextField quantityInput;
    @FXML
    private TableView<Stocks> stocksTable;
    @FXML
    private TableColumn<Stocks, String> noCol;
    @FXML
    private TableColumn<Stocks, String> storeIdCol;
    @FXML
    private TableColumn<Stocks, String> productIdCol;
    @FXML
    private TableColumn<Stocks, String> quantityCol;
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
    public StockController() throws SQLException{
        this.data = new BikeStore(currentUser.getName(), currentUser.getPassword()); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private boolean isIDexist(){
        boolean productExist = false;
        boolean storeExist = false;
       for(Stores i : this.data.getStoreData().getStores()){
            if(i.getStoreID() == Integer.valueOf(this.storeIdInput.getText())){ 
                storeExist = true;
                break;
            };
        }
       for(Products i : this.data.getProductData().getProducts()){
            if(i.getProductID() == Integer.valueOf(this.productIdInput.getText())){ 
                productExist = true;
                break;
            };
        }
        return productExist && storeExist;
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
        Stocks temp = new Stocks();
        
        int affectRows;
        if(this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.quantityInput.getText())&& this.isInteger(this.productIdInput.getText())){
            
            temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));  
            temp.setProductID(Integer.valueOf(this.productIdInput.getText()));
            if(temp.getStoreID()<=0 || temp.getProductID()<=0){
                alert.setText("ID must ge bigger than 0");
            }
            temp.setQuantity(Integer.valueOf(this.quantityInput.getText()));
             
            
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
        
            if(this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.quantityInput.getText())&& this.isInteger(this.productIdInput.getText())){
                if(this.isIDexist()){
                    Stocks temp = new Stocks();
                    temp.setProductID(Integer.valueOf(this.productIdInput.getText()));  
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
            if(this.isInteger(this.storeIdInput.getText())&& this.isInteger(this.quantityInput.getText())&& this.isInteger(this.productIdInput.getText())){
                if(this.isIDexist()){
                    Stocks temp = new Stocks();
                    temp.setStoreID(Integer.valueOf(this.storeIdInput.getText()));  
                    temp.setProductID(Integer.valueOf(this.productIdInput.getText()));
                    
                    
                    check = this.data.update(temp, "quantity", Integer.parseInt(this.quantityInput.getText())); 
                     
                    alert.setText("You 've adjusted 1 rows");
                }else{
                    alert.setText("ID must be exist");
                }
            }
            else{
                alert.setText("ID must be a number");
            }
        
    }


    public void handleLoad(ActionEvent event) {
        this.stocksTable.getColumns().clear();
        
        this.storeIdCol.setCellValueFactory(new PropertyValueFactory<>("storeID"));
        this.productIdCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        this.quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        this.stocksTable.setItems(this.loadStocks());
        
        this.stocksTable.getColumns().add(storeIdCol);
        this.stocksTable.getColumns().add(productIdCol);
        this.stocksTable.getColumns().add(quantityCol);
        
        this.stocksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if(newSelection != null){
                this.storeIdInput.setText(String.valueOf(newSelection.getStoreID()));
                this.productIdInput.setText(String.valueOf(newSelection.getProductID()));
                this.quantityInput.setText(String.valueOf(newSelection.getQuantity()));
                
                this.productIdInput.setEditable(false);
                this.storeIdInput.setEditable(false);
            }
        });
        
    }

    public ObservableList <Stocks> loadStocks(){
        ObservableList<Stocks> stocks = FXCollections.observableArrayList();
        for(Stocks i : this.data.getStockData().getStocks()){
            stocks.add(i);
        }
        return stocks;
    }
    

    public void handleReset(ActionEvent event) {
        this.productIdInput.clear();
        this.storeIdInput.clear();
        this.quantityInput.clear();
        this.productIdInput.setEditable(false);
        this.storeIdInput.setEditable(false);
    }
    
}
