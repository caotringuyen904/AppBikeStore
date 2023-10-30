/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Stocks {
    private int storeID;
    private int productID;
    private int quantity;
    public Stocks (){};
    public Stocks(int id, int pid, int qtt){
        this.productID = pid;
        this.storeID = id;
        this.quantity = qtt;
    }
    public void setStoreID(int id){
        this.storeID = id;
    }
    public void setProductID(int id){
        this.productID = id;
    }
    public void setQuantity(int qtt){
        this.quantity = qtt;
    }
    public int getStoreID(){
        return this.storeID;
    }
    public int getProductID(){
        return this.productID;
    }
    public int getQuantity(){
        return this.quantity;
    }
}
