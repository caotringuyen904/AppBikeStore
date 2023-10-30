/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.OrderItems;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class OrderItemsData extends Data {
    private ArrayList<OrderItems> orderItemsData = new ArrayList<>();
    
    
    public OrderItemsData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData("dbo.order_items");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                int oid = rs.getInt("order_id");
                int iid = rs.getInt("item_id");
                int pid = rs.getInt("product_id");
                int qtt = rs.getInt("quantity");
                float lp = rs.getFloat("list_price");
                float disc = rs.getFloat("discount");
                OrderItems temp = new OrderItems (oid, iid, pid, qtt, lp, disc);
                orderItemsData.add(temp);
            }

    }
    
    
    public ArrayList<OrderItems> getOrderItems (){
        return this.orderItemsData;
    }
    
    public int insert(OrderItems b) throws SQLException{
        int affectRows = 0;
        this.orderItemsData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
    
    public int update(OrderItems b, String att, int val) throws SQLException{
        if (att == "order_id" || att == "item_id")return -1;
        int affectRows = 0;
        for(OrderItems i : this.orderItemsData){
            if(b.getOrderID() == i.getOrderID() && b.getItemID() == i.getItemID()){
                switch(att){ 
                    case "product_id":
                        i.setProductID(val);
                        break;
                    case "quantity":
                        i.setQuantity(val);
                        break;
                }
                affectRows = this.dbcon.update(b, att, val); 
                this.dbcon.close();
                return affectRows;
            }
        }
        return affectRows;
    }
    
    public int update(OrderItems b, String att, float val) throws SQLException{
        if (att == "order_id" || att == "item_id")return -1;
        int affectRows = 0;
        for(OrderItems i : this.orderItemsData){
            if(b.getOrderID() == i.getOrderID() && b.getItemID() == i.getItemID()){
                switch(att){
                    case "list_price":
                        i.setListPrice(val);
                        break;
                    case "discount":
                        i.setDiscount(val);
                        break;
                }
                affectRows = this.dbcon.update(b, att, val);  
                return affectRows;
            }
        }
            return affectRows;
    }
    
    public int delete(OrderItems a) throws SQLException{
        int affectRows = 0;
        for(OrderItems i: this.orderItemsData){
            if(i.getOrderID() == a.getOrderID() && i.getItemID() == a.getItemID()){
                this.orderItemsData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
