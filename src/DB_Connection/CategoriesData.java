/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;

import AppModel.Categories;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class CategoriesData extends Data {
    private ArrayList<Categories> categoriesData = new ArrayList<>();
    
    public CategoriesData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData( "dbo.categories");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
               int cid = rs.getInt("category_id");
                String cname = rs.getString("category_name");
                Categories temp = new Categories (cid,cname);
                categoriesData.add(temp);
        }
    }
    
    public ArrayList<Categories> getCategories(){
        return this.categoriesData;
    }
    
    public int insert(Categories b) throws SQLException{
        int affectRows = 0;
        this.categoriesData.add(b);
        affectRows = this.dbcon.insert(b);
        return affectRows;
    }
    
    public int update(Categories b, String att, String val) throws SQLException{
        if (att == "category_id")return -1;
        int affectRows =0;
        for(Categories i : this.categoriesData){
            if(b.getCategoryID() == i.getCategoryID()){
                i.setCategoryName(val);
                break;
            }
        }
        affectRows = this.dbcon.update(b, att, val);
        return affectRows;
    }
    
    public int delete(Categories a) throws SQLException{
        int affectRows = 0;
        for(Categories i: this.categoriesData){
            if(i.getCategoryID() == a.getCategoryID()){
                this.categoriesData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
}
