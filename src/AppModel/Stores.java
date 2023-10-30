/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Stores {
    private int storeID;
    private String storeName;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    public Stores(){};
    public Stores (int id, String name, String phone, String email, String street, String city, String state, String zip){
        this.storeID = id;
        this.storeName = name;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }
    public void setStoreID(int id){
        this.storeID = id;
    }
    public void setStoreName(String name){
        this.storeName = name;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setEmail(String email){
        this.email = email;
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
    public int getStoreID(){
        return this.storeID;
    }
    public String getStoreName(){
        return this.storeName;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
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
