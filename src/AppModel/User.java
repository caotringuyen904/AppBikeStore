/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class User {
    private int userID;
    private String userName;
    private String Password;
    private boolean canInsert;
    private boolean canUpdate;
    private boolean canDelete;
    public User (int id, String name, String pass, boolean insert, boolean update, boolean delete){
        this.userID = id;
        this.userName = name;
        this.Password = pass;
        this.canInsert = insert;
        this.canUpdate = update;
        this.canDelete = delete;
    }
    public String getUserName (){
        return this.userName;
    }
    public String getPassword(){
        return this.Password;
    }
    public boolean getCanInsert(){
        return this.canInsert;
    }
    public boolean getCanUpdate(){
        return this.canUpdate;
    }
    public boolean getCanDelete(){
        return this.canDelete;
    }
}
