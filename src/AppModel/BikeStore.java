/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;


import DB_Connection.*;
import java.sql.SQLException;



/**
 *
 * @author HP
 */
public class BikeStore {
    private CustomerData customers;
    private StaffData staffs;
    private OrderData orders;
    private StoresData stores;
    private StocksData stocks;
    private BrandData brands;
    private CategoriesData categories;
    private OrderItemsData ordersItem;
    private ProductsData products;

    
    public BikeStore(String user, String pass) throws SQLException{
        this.customers = new CustomerData(user, pass);
        this.brands = new BrandData(user,pass);
        this.categories = new CategoriesData(user, pass);
        this.staffs = new StaffData(user,pass);
        this.orders = new OrderData(user, pass);
        this.stores = new StoresData(user,pass);
        this.stocks = new StocksData(user, pass);
        this.ordersItem = new OrderItemsData(user, pass);
        this.products = new ProductsData(user, pass);

    }
    
    public CustomerData getCustomerData(){
        return this.customers;
    }
    public StaffData getStaffData(){
        return this.staffs;
    }
    public OrderData getOrderData(){
        return this.orders;
    }
    public StoresData getStoreData(){
        return this.stores;
    }
    public StocksData getStockData(){
        return this.stocks;
    }
    public BrandData getBrandData(){
        return this.brands;
    }
    public CategoriesData getCategoryData(){
        return this.categories;
    }
    public OrderItemsData getOrderItemData(){
        return this.ordersItem;
    }
    public ProductsData getProductData(){
        return this.products;
    }
    
    
    
    private boolean notDuplicate (Customers a){
        boolean notDup = true;
        for(Customers i: this.customers.getCustomers()){
            if (a.getCustomerID() == i.getCustomerID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    
    private boolean notDuplicate (Staffs a){
        boolean notDup = true;
        for(Staffs i: this.staffs.getStaffs()){
            if (a.getStaffID() == i.getStaffID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    
    private boolean notDuplicate (Orders a){
        boolean notDup = true;
        for(Orders i: this.orders.getOrders()){
            if (a.getOrderID() == i.getOrderID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    
    private boolean notDuplicate (Stores a){
        boolean notDup = true;
        for(Stores i: this.stores.getStores()){
            if (a.getStoreID() == i.getStoreID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
     
     private boolean notDuplicate (Stocks a){
        boolean notDup = true;
        for(Stocks i: this.stocks.getStocks()){
            if (a.getStoreID()== i.getStoreID() && a.getProductID() == i.getProductID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    
    private boolean notDuplicate (Brands a){
        boolean notDup = true;
        for(Brands i: this.brands.getBrands()){
            if (a.getBrandID() == i.getBrandID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    
    private boolean notDuplicate (Categories a){
        boolean notDup = true;
        for(Categories i: this.categories.getCategories()){
            if (a.getCategoryID() == i.getCategoryID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
     
    private boolean notDuplicate (OrderItems a){
        boolean notDup = true;
        for(OrderItems i: this.ordersItem.getOrderItems()){
            if (a.getOrderID() == i.getOrderID() && a.getItemID() == i.getItemID() ){
                notDup = false;
                break;
            }
        }
        return notDup;
    } 
    
    private boolean notDuplicate (Products a){
        boolean notDup = true;
        for(Products i: this.products.getProducts()){
            if (a.getProductID() == i.getProductID()){
                notDup = false;
                break;
            }
        }
        return notDup;
    }
    

    
    
    public int insert(Customers a) throws SQLException{
        int affectRows;
        if(this.notDuplicate(a)){
        affectRows = this.customers.insert(a);
        return affectRows;
        }
        else return -1;  
    }
    
    public int insert(Staffs a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        
        
        int affectRows;
        
        boolean isExistStoreID = false;
        for (Stores i: this.stores.getStores()){
            if(a.getStoreID() == i.getStoreID()){
                isExistStoreID = true;
                break;
            }
        }
        boolean isExistManagerID = false;
        for (Staffs i: this.staffs.getStaffs()){
            if(a.getManagerID()==i.getStaffID()){
                isExistManagerID = true;
                break;
            }
        }
        if(isExistStoreID == true && isExistManagerID ==true){
            affectRows = this.staffs.insert(a);
            return affectRows;
        }
        else return -2;
    }
    
    public int insert(Orders a) throws SQLException{
        if(this.notDuplicate(a)==false) return -1;
        
        int affectRows;
        boolean isExistCustomerID = false;
        for (Customers i : this.customers.getCustomers()){
            if(a.getCustomerID()==i.getCustomerID()){
                isExistCustomerID = true;
                break;
            }
        }
        boolean isExistStoreID = false;
        for (Stores i: this.stores.getStores()){
            if(a.getStoreID() == i.getStoreID()){
                isExistStoreID = true;
                break;
            }
        }
        boolean isExistStaffID = false;
        for (Staffs i : this.staffs.getStaffs()){
            if(a.getStaffID() == i.getStaffID()){
                isExistStaffID = true;
                break;
            }
        }
        if(isExistCustomerID && isExistStoreID && isExistStaffID){
            affectRows = this.orders.insert(a);
            return affectRows;
        }
        else return -2;
    }
    
    public int insert(Stores a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        int affectRows;
        affectRows = this.stores.insert(a);
        return affectRows;
    }
     
    public int insert(Stocks a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        int affectRows;
        affectRows = this.stocks.insert(a);
        return affectRows;
    }
    
    public int insert(Brands a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        int affectRows;
        affectRows = this.brands.insert(a);
        return affectRows;
    }
     
    public int insert(Categories a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        int affectRows;
        affectRows = this.categories.insert(a);
        return affectRows;
    }
    
    public int insert(OrderItems a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        
        int affectRows;
        
        boolean isExistProductID = false;
        for(Products i : this.products.getProducts()){
            if(i.getProductID() == a.getProductID()){
                isExistProductID = true;
                break;
            }
        }
        boolean isExistOrderID = false;
        for(Orders i : this.orders.getOrders()){
            if(i.getOrderID() == a.getOrderID()){
                isExistOrderID = true;
                break;
            }
        }
        
        if(isExistProductID && isExistOrderID){
            affectRows = this.ordersItem.insert(a);
            return affectRows;
        }
        else return -2;
    }
    
     public int insert(Products a) throws SQLException{
        if(this.notDuplicate(a)==false)return -1;
        
        int affectRows;
        
        boolean isExistBrandID = false;
        for(Brands i : this.brands.getBrands()){
            if(i.getBrandID() == a.getBrandID()){
                isExistBrandID = true;
                break;
            }
        }
        
        boolean isExistCategoryID = false;
        for(Categories i : this.categories.getCategories()){
            if(i.getCategoryID() == a.getCategoryID()){
                isExistCategoryID = true;
                break;
            }
        }
        
        if(isExistCategoryID && isExistBrandID){
            affectRows = this.products.insert(a);
            return affectRows;
        }
        else return -2;
    }
     
    public int update (Brands a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.brands.update(a, att, val);
        return affectRows;
    }
    
    public int update (Categories a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.categories.update(a, att, val);
        return affectRows;
    }
    
    public int update (Customers a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.customers.update(a, att, val);
        return affectRows;
    }
    
    public int update (OrderItems a, String att, float val) throws SQLException{
        int affectRows = 0;
        affectRows = this.ordersItem.update(a, att, val);
        return affectRows;
    }
    
    public int update (OrderItems a, String att, int val) throws SQLException{
       
        int affectRows = 0;
        if(att == "product_id"){  
            boolean isExistProductID = false;
            for(Products i: this.products.getProducts()){
                if(i.getProductID() == val){
                    isExistProductID = true;
                    break;
                }
            }
            if(isExistProductID){
                affectRows = this.ordersItem.update(a, att, val);
                return affectRows;
            }
            return -2;
        }
        else if(att == "order_id"){
            boolean isExistOrderID = false;
            for(Orders i : this.orders.getOrders()){
                if(i.getOrderID() == a.getOrderID()){
                    isExistOrderID = true;
                    break;
                }
            }
            if(isExistOrderID){
                affectRows = this.ordersItem.update(a, att, val);
                return affectRows;
            }
            return -2;
        }
        else{
            affectRows = this.ordersItem.update(a, att, val);
            return affectRows;
        }
    }
    
    public int update (Orders a, String att, int val) throws SQLException{
        int affectRows = 0;
        if(att == "customer_id"){  
            boolean isExistCustomerID = false;
            for(Customers i: this.customers.getCustomers()){
                if(i.getCustomerID() == val){
                    isExistCustomerID = true;
                    break;
                }
            }
            if(isExistCustomerID){
                affectRows = this.orders.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else if(att == "store_id"){ 
            boolean isExistStoreID = false;
            for(Stores i: this.stores.getStores()){
                if(i.getStoreID() == val){
                    isExistStoreID = true;
                    break;
                }
            }  
            if(isExistStoreID){
                affectRows = this.orders.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else if(att == "staff_id"){ 
            boolean isExistStaffID = false;
            for(Staffs i: this.staffs.getStaffs()){
                if(i.getStaffID() == val){
                    isExistStaffID = true;
                    break;
                }
            }
            if(isExistStaffID){
                affectRows = this.orders.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else{
            affectRows = this.orders.update(a, att, val);
            return affectRows;
        }
    }
    public int update (Orders a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.orders.update(a, att, val);
        return affectRows;
    }
    
    public int update (Products a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.products.update(a, att, val);
        return affectRows;
    }
    
    public int update (Products a, String att, float val) throws SQLException{
        int affectRows = 0;
        affectRows = this.products.update(a, att, val);
        return affectRows;
    }
    
    public int update (Products a, String att, int val) throws SQLException{
        int affectRows = 0;
        if(att == "brand_id"){  
            boolean isExistBrandID = false;
            for(Brands i: this.brands.getBrands()){
                if(i.getBrandID() == val){
                    isExistBrandID = true;
                    break;
                }
            }
            if(isExistBrandID){
                affectRows = this.products.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else if(att == "category_id"){ 
            boolean isExistCategoryID = false;
            for(Categories i: this.categories.getCategories()){
                if(i.getCategoryID() == val){
                    isExistCategoryID = true;
                    break;
                }
            }  
            if(isExistCategoryID){
                affectRows = this.products.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else{
            affectRows = this.products.update(a, att, val);
            return affectRows;
        }
    }
    
    public int update (Staffs a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.staffs.update(a, att, val);
        return affectRows;
    }
    
    public int update (Staffs a, String att, int val) throws SQLException{
        int affectRows = 0;
        if(att == "store_id"){  
            boolean isExistStoreID = false;
            for(Stores i: this.stores.getStores()){
                if(i.getStoreID() == val){
                    isExistStoreID = true;
                    break;
                }
            }
            if(isExistStoreID){
                affectRows = this.staffs.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else if(att == "manager_id"){ 
            boolean isExistManagerID = false;
            for(Staffs i: this.staffs.getStaffs()){
                if(i.getStaffID() == val){
                    isExistManagerID = true;
                    break;
                }
            }  
            if(isExistManagerID){
                affectRows = this.staffs.update(a, att, val);
                return affectRows;
            }
            else return -2;
        }
        else{
            affectRows = this.staffs.update(a, att, val);
            return affectRows;
        }
    }
    
    public int update (Stocks a, String att, int val) throws SQLException{
        int affectRows = 0;
        affectRows = this.stocks.update(a, att, val);
        return affectRows;
    }
    
    public int update (Stores a, String att, String val) throws SQLException{
        int affectRows = 0;
        affectRows = this.stores.update(a, att, val);
        return affectRows;
    }
    
    public int delete (Brands a) throws SQLException{
        int affectRows = 0;
        for (Products i : this.products.getProducts()){
            if(i.getBrandID() == a.getBrandID()){
                affectRows += this.update(i, "brand_id", 0);
            }
        }
        affectRows += this.brands.delete(a);
        return affectRows;
    }
    
    public int delete (Categories a) throws SQLException{
        int affectRows = 0;
        for (Products i : this.products.getProducts()){
            if(i.getCategoryID() == a.getCategoryID()){
                affectRows += this.update(i, "category_id", 0);
            }
        }
        affectRows += this.categories.delete(a);
        return affectRows;
    }
    
    public int delete (Customers a) throws SQLException{
        int affectRows = 0;
        for (Orders i : this.orders.getOrders()){
            if(i.getCustomerID() == a.getCustomerID()){
                affectRows += this.update(i, "customer_id", 0);
            }
        }
        affectRows += this.customers.delete(a);
        return affectRows;
    }
    
    public int delete (OrderItems a) throws SQLException{
        int affectRows = 0;
        affectRows = this.ordersItem.delete(a);
        return affectRows;
    }
    
    
    public int delete (Orders a) throws SQLException{
        int affectRows = 0;
        for (OrderItems i : this.ordersItem.getOrderItems()){
            if(i.getOrderID() == a.getOrderID()){
                affectRows += this.ordersItem.delete(i);
            }
        }
        affectRows += this.orders.delete(a);
        return affectRows;
    }
    
    public int delete (Stocks a) throws SQLException{
        int affectRows = 0;
        this.stocks.getStocks().remove(a);
        affectRows = this.stocks.delete(a);
        return affectRows;
    }
    
    public int delete (Products a) throws SQLException{
        int affectRows = 0;
        for (OrderItems i : this.ordersItem.getOrderItems()){
            if(i.getProductID() == a.getProductID()){
                affectRows += this.ordersItem.update(i, "product_id", 0);
            }
        }
        for (Stocks i : this.stocks.getStocks()){
            if(i.getProductID() == a.getProductID()){
                affectRows += this.stocks.delete(i);
            }
        }
        affectRows += this.products.delete(a);
        return affectRows;
    }
    
    public int delete (Staffs a) throws SQLException{
        int affectRows = 0;
        for (Staffs i : this.staffs.getStaffs()){
            if(i.getManagerID() == a.getStaffID()){
                affectRows += this.staffs.update(i, "manager_id", 0);
            }
        }
        for (Orders i : this.orders.getOrders()){
            if(i.getStaffID() == a.getStaffID()){
                affectRows += this.orders.update(i, "staff_id", 0);
            }
        }
        affectRows += this.staffs.delete(a);
        return affectRows;
    }
    
    public int delete (Stores a) throws SQLException{
        int affectRows = 0;
        this.stores.getStores().remove(a);
        affectRows = this.stores.delete(a);
        return affectRows;
    }
    
}
