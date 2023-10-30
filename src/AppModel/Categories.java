/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public class Categories {
    private int categoryID;
    private String categoryName;
    
    public Categories(){};
    public Categories(int id, String name){
        this.categoryID = id;
        this.categoryName = name;
    }
    public void setCategoryID(int id){
        this.categoryID = id;
    }
    public void setCategoryName (String name){
        this.categoryName = name;
    }
    public int getCategoryID(){
        return this.categoryID;
    }
    public String getCategoryName(){
        return this.categoryName;
    }
}
