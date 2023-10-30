/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.Products;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class ProductsData extends Data {
    private ArrayList<Products> productsData = new ArrayList<>();
    
    public ProductsData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData("dbo.products");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                int pid = rs.getInt("product_id");
                String pname = rs.getString("product_name");
                int bid = rs.getInt("brand_id");
                int cid = rs.getInt("category_id");
                int model = rs.getInt("model_year");
                float lp = rs.getFloat("list_price");
                Products temp = new Products (pid,pname,bid,cid,model,lp);
                productsData.add(temp);
            }

    }
    
    public ArrayList<Products> getProducts (){
        return this.productsData;
    }
    
    public int insert(Products b) throws SQLException{
        int affectRows = 0;
        this.productsData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
       
    public int update(Products b, String att, float val) throws SQLException{
        int affectRows = 0;
        for(Products i : this.productsData){
            if(b.getProductID() == i.getProductID()){
                i.setListPrice(val);
                affectRows = this.dbcon.update(b, att, val); 
                this.dbcon.close();
                return affectRows;
            }
        }
        this.dbcon.close();
        return 0;
    }
    
    public int update(Products b, String att, String val) throws SQLException{
        int affectRows = 0;
        for(Products i : this.productsData){
            if(b.getProductID() == i.getProductID()){
                i.setProductName(val);
                affectRows = this.dbcon.update(b, att, val);  
                this.dbcon.close();
                return affectRows;
            }
        }
        this.dbcon.close();
        return 0;
    }
    
    public int update(Products b, String att, int val) throws SQLException{
        if(att == "product_id")return -1;
        int affectRows = 0;
        for(Products i : this.productsData){
            if(b.getProductID() == i.getProductID()){
                switch(att){                  
                    case "brand_id":
                        i.setBrandID(val);
                        break;
                    case "categories_id":
                        i.setCategoryID(val);
                        break;
                    case "model_year":
                        i.setModelYear(val);
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
    
    public int delete(Products a) throws SQLException{
        int affectRows = 0;
        for(Products i: this.productsData){
            if(i.getProductID() == a.getProductID()){
                this.productsData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
