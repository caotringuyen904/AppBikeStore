/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.Stocks;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class StocksData extends Data {
    private ArrayList<Stocks> stocksData = new ArrayList<>();
    public StocksData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData("dbo.stocks");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                int id = rs.getInt("store_id");
                int pid = rs.getInt("product_id");
                int qtt = rs.getInt("quantity");
                Stocks temp = new Stocks (id,pid,qtt);
                stocksData.add(temp);
            }

    }
    
    public ArrayList<Stocks> getStocks (){
        return this.stocksData;
    }
    
    public int insert(Stocks b) throws SQLException{
        int affectRows = 0;
        this.stocksData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
    
    public int update(Stocks b, String att, int val) throws SQLException{
        if(att == "store_id" || att == "product_id") return -1;
        int affectRows = 0;
        for(Stocks i : this.stocksData){
            if(b.getStoreID() == i.getStoreID() && b.getProductID()==i.getProductID()){
                switch(att){
                    case "store_id":
                        i.setStoreID(val);
                        break;
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
        this.dbcon.close();
        return 0;
    }
    
    public int delete(Stocks a) throws SQLException{
        int affectRows = 0;
        for(Stocks i: this.stocksData){
            if(i.getStoreID() == a.getStoreID() && i.getProductID() == a.getProductID()){
                this.stocksData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
}
