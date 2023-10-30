/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;


import java.util.Date;

/**
 *
 * @author HP
 */
public class Orders {
    private int orderID;
    private int customerID;
    private int orderStatus;
    private String orderDate;
    private String requiredDate;
    private String shippedDate;
    private int storeID;
    private int staffID;
    public Orders(){};
    public Orders (int orderid, int customerid, int orderstatus, String orderdate, String requireddate, String shippeddate, int storeid, int staffid){
        this.orderID = orderid;
        this.customerID = customerid;
        this.orderStatus = orderstatus;
        this.orderDate = orderdate;
        this.requiredDate = requireddate;
        this.shippedDate = shippeddate;
        this.storeID = storeid;
        this.staffID = staffid;
    }
    public void setOrderID (int id){
        this.orderID = id;
    }
    public void setCustomerID (int id){
        this.customerID = id;
    }
    public void setOrderStatus (int stt){
        this.orderStatus = stt;
    }
    public void setOrderDate (String date){
        this.orderDate = date;
    }
    public void setRequiredDate (String date){
        this.requiredDate = date;
    }
    public void setShippedDate (String date){
        this.shippedDate = date;
    }
    public void setStoreID (int id){
        this.storeID = id;
    }
    public void setStaffID (int id){
        this.staffID = id;
    }
    public int getOrderID (){
        return this.orderID;
    }
    public int getCustomerID (){
        return this.customerID;
    }
    public int getOrderStatus (){
        return this.orderStatus;
    }
    public String getOrderDate (){
        return this.orderDate;
    }
    public String getRequiredDate (){
        return this.requiredDate;
    }
    public String getShippedDate (){
        return this.shippedDate;
    }
    public int getStoreID (){
        return this.storeID;
    }
    public int getStaffID (){
        return this.staffID;
    }
}
