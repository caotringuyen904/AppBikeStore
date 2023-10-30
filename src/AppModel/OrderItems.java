/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class OrderItems {
    private int orderID;
    private int itemID;
    private int productID;
    private int quantity;
    private float listPrice;
    private float discount;
    public OrderItems(){};
    public OrderItems(int oid, int iid, int pid, int qtt, float lp, float disc){
        this.orderID = oid;
        this.itemID = iid;
        this.productID = pid;
        this.quantity = qtt;
        this.listPrice = lp;
        this.discount = disc;
    }
    public void setOrderID(int oid){
        this.orderID = oid;
    }
    public void setItemID(int iid){
        this.itemID = iid;
    }
    public void setProductID(int pid){
        this.productID = pid;
    }
    public void setQuantity(int qtt){
        this.quantity = qtt;
    }
    public void setListPrice(float lp){
        this.listPrice = lp;
    }
    public void setDiscount(float disc){
        this.discount = disc;
    }
    public int getOrderID(){
       return this.orderID;
    }
    public int getItemID(){
        return this.itemID;
    }
    public int getProductID(){
        return this.productID;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public float getListPrice(){
        return this.listPrice;
    }
    public float getDiscount(){
        return this.discount;
    }
}
