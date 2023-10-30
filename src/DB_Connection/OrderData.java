/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author HP
 */
public class OrderData extends Data {
    private ArrayList<Orders> orderData = new ArrayList<>();
    
    public OrderData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData("dbo.orders");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
               int oid = rs.getInt("order_id");
                int cid = rs.getInt("customer_id");
                int orstt = rs.getInt("order_status");
                String ordate = rs.getString("order_date");
                String regdate = rs.getString("required_date");
                String shipdate = rs.getString("shipped_date");
                int sid = rs.getInt("store_id");
                int stid = rs.getInt("staff_id");
                Orders temp = new Orders (oid,cid,orstt,ordate,regdate,shipdate,sid,stid);
                orderData.add(temp);
            }

    }
    
    
    public ArrayList<Orders> getOrders (){
        return this.orderData;
    }
    
    public int insert(Orders b) throws SQLException{
        int affectRows = 0;
        this.orderData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
    
    public int update(Orders b, String att, String val) throws SQLException{
        if (att == "order_id") return -1;
        int affectRows = 0;
        for(Orders i : this.orderData){
            if(b.getOrderID() == i.getOrderID()){
                switch(att){
                    case "order_date":
                        i.setOrderDate(val);
                        break;
                    case "required_date":
                        i.setRequiredDate(val);
                        break;
                    case "shipped":
                        i.getShippedDate();
                        break;  
                }
                affectRows = this.dbcon.update(b, att, val);
                this.dbcon.close();
                return affectRows;
            }
        }
        this.dbcon.close();
        return 0;
    }
    
    
    public int update(Orders b, String att, int val) throws SQLException{
        if (att == "order_id") return -1;
        int affectRows = 0;
        for(Orders i : this.orderData){
            if(b.getOrderID() == i.getOrderID()){
                switch(att){                    
                    case "customer_id":
                        i.setCustomerID(val);
                        break;
                    case "order_status":
                        i.setOrderStatus(val);
                        break;
                    case "store_id":
                        i.setStoreID(val);
                        break;
                    case "staff_id":
                        i.setStaffID(val);
                        break;
                }
                affectRows = this.dbcon.update(b, att, val);  
                this.dbcon.close();
                return affectRows;
            }
        }
        this.dbcon.close();
        return 0;
    }
    
    
    public int delete(Orders a) throws SQLException{
        int affectRows = 0;
        for(Orders i: this.orderData){
            if(i.getOrderID() == a.getOrderID()){
                this.orderData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
}
