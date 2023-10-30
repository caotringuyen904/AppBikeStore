/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import AppModel.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.CallableStatement;


/**
 *
 * @author HP
 */
public class DBConnection {
    SQLServerDataSource ds = new SQLServerDataSource();
    Connection con = null;
    public ResultSet data = null;
    public DBConnection (String user, String pass){
        ds.setUser(user);
        ds.setPassword(pass);
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("BIKESTORE");
    }
    public void loadData (String db) throws SQLException {
            con = ds.getConnection();
            String query = "select * from " + db;
            CallableStatement cs = con.prepareCall(query);
            this.data = cs.executeQuery();
    }
    
    public int insert(Brands b) throws SQLServerException, SQLException{
        int affectRows = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.brands ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.brands OFF;";
            String data = "("+ b.getBrandID() + ", "+ "'"+ b.getBrandName() +"'"  +")";
            String query = modifiedOn + "insert into dbo.brands (brand_id, brand_name) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRows = cs.executeUpdate(); 
            return affectRows;
    }
    public int insert(Categories c) throws SQLException{
            int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.categories ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.categories OFF;";
            String data = "("+ c.getCategoryID() + ", "+ "'"+ c.getCategoryName() +"'"  +")";
            String query = modifiedOn + "insert into dbo.categories (category_id, category_name) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
    
    public int insert(Customers c) throws SQLServerException, SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.customers ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.customers OFF;";
            String fname = "'" + c.getFirstName() +"', ";
            String lname = "'" + c.getLastName() +"', ";
            String email = "'" + c.getEmail() +"', ";
            String phone = "'" + c.getPhone() +"', ";
            String street = "'" + c.getStreet() +"', ";
            String city = "'" + c.getCity() +"', ";
            String state = "'" + c.getState() +"', ";
            String zip = "'" + c.getZipCode() +"'";
            String data = "(" + fname + lname + email + phone + c.getCustomerID()+ ", " + street + city + state + zip + ")";
            String query = modifiedOn + "insert into dbo.customers (first_name, last_name, email, phone, customer_id, street,city,state,zip_code) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
    
      public int insert(Orders o) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.orders ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.orders OFF;";
            String orderdate = "'" + o.getOrderDate() +"', ";
            String reqdate = "'" + o.getRequiredDate() +"', ";
            String shippeddate = "'" + o.getShippedDate() +"', "; 
            String data = "(" + o.getOrderID()+"," + o.getCustomerID()+"," + o.getOrderStatus()+"," + orderdate + reqdate + shippeddate + o.getStoreID()+", " + o.getStaffID() + ")";
            String query = modifiedOn + "insert into dbo.orders (order_id, customer_id, order_status, order_date, required_date, shipped_date,store_id,staff_id) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
     public int insert(OrderItems c) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String orderID = c.getOrderID() +", ";
            String itemID = c.getItemID() + ", ";
            String productID = c.getProductID() +", ";
            String quantity = c.getQuantity() + ", ";
            String listprice = c.getListPrice() + ", ";
            String discount = c.getDiscount() +"";
            String data = "("+ orderID + itemID + productID + quantity + listprice + discount  +")";
            String query =  "insert into dbo.order_items (order_id, item_id, product_id, quantity, list_price, discount) values " + data +";";
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
      
    public int insert(Products c) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.products ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.products OFF;";
            String productID = c.getProductID() + ", ";
            String productName = "'"+ c.getProductName() +"', ";
            String brandID = c.getBrandID() +", ";
            String categoryID = c.getCategoryID() + ", ";
            String modelYear = c.getModelYear() + ", ";
            String listPrice = c.getListPrice() + "";
            String data = "("+ productID + productName + brandID + categoryID + modelYear + listPrice +")";
            String query = modifiedOn + "insert into dbo.products (product_id, product_name, brand_id, category_id, model_year, list_price) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
     
    public int insert(Staffs c) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.staffs ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.staffs OFF;";
            String fname = "'" + c.getFirstName() +"', ";
            String lname = "'" + c.getLastName() +"', ";
            String email = "'" + c.getEmail() +"', ";
            String phone = "'" + c.getPhone() +"', ";
            String staffID = c.getStaffID() + ", ";
            String active = c.getIsActive() +", ";
            String storeID = c.getStoreID() +", ";
            String managerID = c.getManagerID() +"";
            String data = "(" + fname + lname + email + phone + staffID + active + storeID + managerID + ")";
            String query = modifiedOn + "insert into dbo.staffs (first_name, last_name, email, phone, staff_id, active, store_id, manager_id) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
    public int insert(Stocks c) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String storeID = c.getStoreID() + ", ";
            String productID = c.getProductID() + ", ";
            String quantity = c.getQuantity() + "";
            String data = "(" + storeID + productID + quantity + ")";
            String query = "insert into dbo.stocks (store_id, product_id, quantity) values " + data +";";
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
    public int insert(Stores c) throws SQLException{
        int affectRow = 0;
            con = ds.getConnection();
            String modifiedOn = "SET IDENTITY_INSERT dbo.stores ON;";
            String modifiedOff = "SET IDENTITY_INSERT dbo.stores OFF;";
            String storeID = c.getStoreID() +", ";
            String storeName = "'" + c.getStoreName() + "', ";
            String phone = "'" + c.getPhone() +"', ";
            String email = "'" + c.getEmail() +"', ";
            String street = "'" + c.getStreet() + "', ";
            String city = "'" + c.getCity() + "', ";
            String state = "'" + c.getState() + "', ";
            String zip = "'"+ c.getZipCode()+"'";
            String data = "(" + storeID + storeName + phone + email + street + city + state + zip + ")";
            String query = modifiedOn + "insert into dbo.stores (store_id, store_name, phone, email, street, city, state, zip_code) values " + data +";" + modifiedOff;
            CallableStatement cs = con.prepareCall(query);
            affectRow = cs.executeUpdate();
            return affectRow;
    }
    
    
    public int update(Brands a, String att, String val) throws SQLServerException, SQLException{
        if(att == "brand_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.brands ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " brand_id = " + a.getBrandID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    
    
    public int update(Categories a, String att, String val) throws SQLServerException, SQLException{
        if(att == "categories_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.categories ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " category_id = " + a.getCategoryID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
     
    public int update(Customers a, String att, String val) throws SQLServerException, SQLException{
        if(att == "customer_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.customers ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " customer_id = " + a.getCustomerID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(OrderItems a, String att, int val) throws SQLServerException, SQLException{
        if(att == "order_id" || att == "item_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.order_items ";
        String attribute = att + " = ";
        String value = val +"" ;
        String criteria = " order_id = " + a.getOrderID() + " and " + " item_id = " + a.getItemID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(OrderItems a, String att, float val) throws SQLServerException, SQLException{
        if(att == "order_id" || att == "item_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.order_items ";
        String attribute = att + " = ";
        String value = val +"" ;
        String criteria = " order_id = " + a.getOrderID() + " and " + " item_id = " + a.getItemID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Orders a, String att, String val) throws SQLServerException, SQLException{
        if(att == "order_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.orders ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " order_id = " + a.getOrderID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Orders a, String att, int val) throws SQLServerException, SQLException{
        if(att == "order_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.orders ";
        String attribute = att + " = ";
        String value = val +"";
        String criteria = " order_id = " + a.getOrderID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;

    }
    
    public int update(Products a, String att, String val) throws SQLServerException, SQLException{
        if(att == "product_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.products ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " product_id = " + a.getProductID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Products a, String att, int val) throws SQLServerException, SQLException{
        if(att == "product_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.products ";
        String attribute = att + " = ";
        String value = val + "";
        String criteria = " product_id = " + a.getProductID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    public int update(Products a, String att, float val) throws SQLServerException, SQLException{
        if(att == "product_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.products ";
        String attribute = att + " = ";
        String value = val + "";
        String criteria = " product_id = " + a.getProductID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Staffs a, String att, String val) throws SQLServerException, SQLException{
        if(att == "staff_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.staffs ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " staff_id = " + a.getStaffID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Staffs a, String att, int val) throws SQLException{
        if(att == "staff_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.staffs ";
        String attribute = att + " = ";
        String value = val + "";
        String criteria = " staff_id = " + a.getStaffID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int update(Stocks a, String att, int val) throws SQLException{
        if(att == "store_id" || att == "product_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.stocks ";
        String attribute = att + " = ";
        String value = val + "";
        String criteria = " store_id = " + a.getStoreID()+ " and " + " product_id = " + a.getProductID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;

    }
    
    
    public int update(Stores a, String att, String val) throws SQLException{
        if(att == "store_id")return -1;
        int affectRows = 0;
        String dbChoice = " dbo.stores ";
        String attribute = att + " = ";
        String value = "'" + val + "'";
        String criteria = " store_id = " + a.getStoreID();
        String query = "update " + dbChoice + " set " + attribute + value + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
   public int delete (Brands a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.brands ";
       String criteria = "brand_id = " + a.getBrandID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
   }
    
     public int delete (Categories a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.categories ";
       String criteria = "category_id = " + a.getCategoryID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int delete (Customers a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.customers ";
       String criteria = "customer_id = " + a.getCustomerID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int delete (Orders a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.orders ";
       String criteria = "order_id = " + a.getOrderID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int delete (OrderItems a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.order_items ";
       String criteria = "order_id = " + a.getOrderID() + " and " + " item_id = " + a.getItemID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
       this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
     
    public int delete (Products a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.products ";
       String criteria = "product_id = " + a.getProductID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
       this.con = ds.getConnection();
       CallableStatement cs = con.prepareCall(query);
       affectRows = cs.executeUpdate();
       return affectRows;
    }
    
    public int delete (Staffs a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.staffs ";
       String criteria = "staff_id = " + a.getStaffID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int delete (Stocks a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.stocks ";
       String criteria = "store_id = " + a.getStoreID() + " and " + " product_id = " + a.getProductID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public int delete (Stores a) throws SQLException{
       int affectRows;
       String dbChoice = " dbo.stores ";
       String criteria = "store_id = " + a.getStoreID();
       String query = "delete from " + dbChoice + " where " + criteria + ";";
        this.con = ds.getConnection();
        CallableStatement cs = con.prepareCall(query);
        affectRows = cs.executeUpdate();
        return affectRows;
    }
    
    public void close(){
        try {
            data.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
