/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;

import AppModel.Customers;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CustomerData extends Data {
    private ArrayList<Customers> customerData = new ArrayList<>();
    
    public CustomerData(String user, String pass) throws SQLException{
        super(user,pass);

            this.dbcon.loadData("dbo.customers");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int cid = rs.getInt("customer_id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zip_code");
                Customers temp = new Customers(fname,lname,email,phone,cid,street,city,state,zip);
                customerData.add(temp);
        }
    }
    
    public ArrayList<Customers> getCustomers (){
        return this.customerData;
    }
    
    public int insert(Customers b) throws SQLException{
        int affectRows = 0;
        this.customerData.add(b);
        affectRows = this.dbcon.insert(b);
        return affectRows;
    }
    
    public int update(Customers b, String att, String val) throws SQLException{
        if (att == "customer_id") return -1;
        int affectRows = 0;
        for(Customers i : this.customerData){
            if(b == i){
                switch(att){
                    case "first_name":
                        i.setFirstName(val);
                        break;
                    case "last_name":
                        i.setLastName(val);
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
        return 0;
    }
    
    public int delete(Customers a) throws SQLException{
        int affectRows = 0;
        for(Customers i: this.customerData){
            if(i.getCustomerID() == a.getCustomerID()){
                this.customerData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
