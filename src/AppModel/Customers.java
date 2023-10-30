/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Customers extends Person {
    private int customerID;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    
    public Customers(String fname, String lname, String email, String phone, int customerid, String street, String city, String state, String zipcode){
        super(fname,lname,email,phone);
        this.customerID = customerid;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipcode;
    }
    public Customers (){};
    
    public void setCustomerID(int cd){
        this.customerID = cd;
    }
    public void setStreet(String st){
        this.street = st;
    }
    public void setCity(String ct){
        this.city = ct;
    }
    public void setState(String stt){
        this.state = stt;
    }
    public void setZipCode(String zip){
        this.zipCode = zip;
    }
    public int getCustomerID(){
        return this.customerID;
    }
    public String getStreet(){
        return this.street;
    }
    public String getCity(){
        return this.city;
    }
    public String getState(){
        return this.state;
    }
    public String getZipCode(){
        return this.zipCode;
    }
}
