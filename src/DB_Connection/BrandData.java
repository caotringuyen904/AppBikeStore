/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;

import AppModel.Brands;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class BrandData extends Data{
    private ArrayList<Brands> brandData = new ArrayList<>();
    
    public BrandData(String user, String pass) throws SQLException{
        super(user,pass);
            this.dbcon.loadData("dbo.brands");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                int bid = rs.getInt("brand_id");
                String bname = rs.getString("brand_name");
                Brands temp = new Brands (bid,bname);
                brandData.add(temp);
            }

    }
    public ArrayList<Brands> getBrands (){
        return this.brandData;
    }
    
    public int insert(Brands b) throws SQLException{
        int affectRows = 0;
        this.brandData.add(b);
        affectRows = this.dbcon.insert(b);
        return affectRows;
    }
    
    public int update(Brands b, String att, String val) throws SQLException{
        if (att == "brand_id") return -1;
        int affectRows =0;
        for(Brands i : this.brandData){
            if(b.getBrandID() == i.getBrandID()){
                i.setBrandName(val);
                break;
            }
        }
        affectRows = this.dbcon.update(b, att, val);
        return affectRows;
    }
    
    public int delete(Brands a) throws SQLException{
        int affectRows = 0;
        for(Brands i: this.brandData){
            if(i.getBrandID() == a.getBrandID()){
                this.brandData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
