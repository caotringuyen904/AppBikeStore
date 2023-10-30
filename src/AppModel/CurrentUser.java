/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class CurrentUser {
    private static final CurrentUser instance = new CurrentUser();
    public CurrentUser(){};
    private String userName;
    private String password;
    private boolean canInsert;
    private boolean canUpdate;
    private boolean canDelete;
    
    public static CurrentUser getUser (){
        return instance;
    }
    public void setName(String name){
        this.userName = name;
    }
    public String getName (){
        return this.userName;
    }
    public void setPassword (String pass){
        this.password = pass;
    }
    public String getPassword (){
        return this.password;
    }
    public void setCanInsert(boolean a){
        this.canInsert = a;
    }
    public boolean getCanInsert (){
        return this.canInsert;
    }
    public void setCanUpdate(boolean a){
        this.canUpdate = a;
    }
    public boolean getCanUpdate (){
        return this.canUpdate;
    }
    public void setCanDelete(boolean a){
        this.canDelete = a;
    }
    public boolean getCanDelete (){
        return this.canDelete;
    }
    
}
