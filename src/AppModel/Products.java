/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Products {
    private int productID;
    private String productName;
    private int brandID;
    private int categoryID;
    private int modelYear;
    private float listPrice;
    public Products (int pid, String pname, int bid, int cid, int model, float price){
        this.productID = pid;
        this.brandID = bid;
        this.categoryID = cid;
        this.productName = pname;
        this.modelYear = model;
        this.listPrice = price;
    }
    public Products(){};
    public void setProductID(int id){
        this.productID = id;
    }
    public void setBrandID(int id){
        this.brandID = id;
    }
    public void setCategoryID(int id){
        this.categoryID = id;
    }
    public void setProductName(String name){
        this.productName = name;
    }
    public void setModelYear(int model){
        this.modelYear = model;
    }
    public void setListPrice(float lp){
        this.listPrice =lp;
    }
    public int getProductID(){
        return this.productID;
    }
    public int getBrandID(){
        return this.brandID;
    }
    public int getCategoryID(){
        return this.categoryID;
    }
    public String getProductName(){
        return this.productName;
    }
    public int getModelYear(){
        return this.modelYear;
    }
    public float getListPrice(){
        return this.listPrice;
    }
}
