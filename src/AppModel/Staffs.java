/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Staffs extends Person{
    private int staffID;
    private int isActive;
    private int storeID;
    private int managerID;
    public Staffs(){};
    public Staffs(String fname, String lname, String email, String phone, int staffid, int isactive, int storeid, int managerid){
        super(fname,lname,email,phone);
        this.staffID = staffid;
        this.isActive = isactive;
        this.storeID = storeid;
        this.managerID = managerid;
    }
    public void setStaffID(int stfid){
        this.staffID = stfid;
    }
    public void setStoreID(int strid){
        this.storeID = strid;
    }
    public void setManagerID(int mngid){
        this.managerID = mngid;
    }
    public void setIsActive(int isact){
        this.isActive = isact;
    }
    public int getStaffID(){
       return this.staffID;
    }
    public int getStoreID(){
        return this.storeID;
    }
    public int getManagerID(){
        return this.managerID;
    }
    public int getIsActive(){
        return this.isActive;
    }
}
