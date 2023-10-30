/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Brands {
    private int brandID;
    private String brandName;
    public Brands(){};
    public Brands(int id, String name){
        this.brandID = id;
        this.brandName = name;
    }
    public void setBrandID(int id){
        this.brandID = id;
    }
    public void setBrandName (String name){
        this.brandName = name;
    }
    public int getBrandID(){
        return this.brandID;
    }
    public String getBrandName(){
        return this.brandName;
    }
}
