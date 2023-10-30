/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.Stores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class StoresData extends Data {
    private ArrayList<Stores> storesData = new ArrayList<>();
    public StoresData(String user, String pass) throws SQLException{
        super(user, pass);

            this.dbcon.loadData("dbo.stores");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                int sid = rs.getInt("store_id");
                String sname = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip_code");
                Stores temp = new Stores (sid,sname,phone,email,street,city,state,zip);
                storesData.add(temp);
            }
  
    }
    
    public ArrayList<Stores> getStores (){
        return this.storesData;
    }
    
    public int insert(Stores b) throws SQLException{
        int affectRows = 0;
        this.storesData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
    
    public int update(Stores b, String att, int val){
        if(att == "store_id")return -1;
        else return 0;
    }
    
    public int update(Stores b, String att, String val) throws SQLException{
        int affectRows = 0;
        for(Stores i : this.storesData){
            if(b.getStoreID() == i.getStoreID()){
                switch(att){
                    case "store_name":
                        i.setStoreName(val);
                        break;
                    case "phone":
                        i.setPhone(val);
                        break;
                    case "email":
                        i.setEmail(val);
                        break;
                    case "street":
                        i.setStreet(val);
                        break;
                    case "city":
                        i.setCity(val);
                        break;
                    case "state":
                        i.setState(val);
                        break;
                    case "zip_code":
                        i.setZipCode(val);
                        break;
                }
                affectRows = this.dbcon.update(b, att, val);  
                return affectRows;
            }
        }
        return affectRows;
    }
    
    public int delete(Stores a) throws SQLException{
        int affectRows = 0;
        for(Stores i: this.storesData){
            if(i.getStoreID() == a.getStoreID()){
                this.storesData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
