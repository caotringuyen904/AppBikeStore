/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;

/**
 *
 * @author HP
 */
public abstract class Data {
    public DBConnection dbcon;
    public Data (String user, String pass){
        dbcon = new DBConnection(user, pass);
    }
}
