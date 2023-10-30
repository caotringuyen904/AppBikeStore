/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;


import AppModel.Staffs;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class StaffData extends Data {
    private ArrayList<Staffs> staffData = new ArrayList<>();
    public StaffData (String user, String pass) throws SQLException{
        super(user, pass);

            this.dbcon.loadData("dbo.staffs");
            ResultSet rs = this.dbcon.data;
            while(rs.next()){
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int sid = rs.getInt("staff_id");
                int active = rs.getInt("active");
                int stid = rs.getInt("store_id");
                int mid = rs.getInt("manager_id");
                Staffs temp = new Staffs (fname,lname,email,phone,sid,active,stid,mid);
                staffData.add(temp);
            }

    }
    
    public ArrayList<Staffs> getStaffs (){
        return this.staffData;
    }
    
    public int insert(Staffs b) throws SQLException{
        int affectRows = 0;
        this.staffData.add(b);
        affectRows = this.dbcon.insert(b);
        this.dbcon.close();
        return affectRows;
    }
    
    public int update(Staffs b, String att, String val) throws SQLException{
        int affectRows = 0;
        for(Staffs i : this.staffData){
            if(b.getStaffID() == i.getStaffID()){
                switch(att){
                    case "first_name":
                        i.setFirstName(val);
                        break;
                    case "last_name":
                        i.setLastName(val);
                        break;
                    case "email":
                        i.setEmail(val);
                        break;
                    case "phone":
                        i.setPhone(val);
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
    
    public int update(Staffs b, String att, int val) throws SQLException{
        if(att == "staff_id") return -1;
        int affectRows = 0;
        for(Staffs i : this.staffData){
            if(b.getStaffID() == i.getStaffID()){
                switch(att){
                    case "avtive":
                        i.setIsActive(val);
                        break;
                    case "store_id":
                        i.setStoreID(val);
                        break;
                    case "manager_id":
                        i.setManagerID(val);
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
    
    public int delete(Staffs a) throws SQLException{
        int affectRows = 0;
        for(Staffs i: this.staffData){
            if(i.getStaffID() == a.getStaffID()){
                this.staffData.remove(i);
                affectRows = this.dbcon.delete(a);
                break;
            }
        }
        return affectRows;
    }
    
}
