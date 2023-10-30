/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB_Connection;

import AppModel.User;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UserData  {
    public ArrayList<User> users = new ArrayList<>();
    public UserData() throws SQLException{  
    SQLServerDataSource ds = new SQLServerDataSource();
    Connection con = null;
    ResultSet data = null;
    ds.setUser("sa");
    ds.setPassword("sa");
    ds.setServerName("localhost");
    ds.setPortNumber(Integer.parseInt("1433"));
    ds.setDatabaseName("USER_BIKESTORE");
    con = ds.getConnection();
    String query = "select * from Bike_Store_User";
    CallableStatement cs = con.prepareCall(query);
    data = cs.executeQuery();
    while(data.next()){
        int id = data.getInt("user_id");
        String name = data.getString("username");
        String password = data.getString("user_password");
        boolean insert = data.getBoolean("canInsert");
        boolean update = data.getBoolean("canUpdate");
        boolean delete = data.getBoolean("canDelete");
        User temp = new User(id,name,password,insert,update,delete);
        this.users.add(temp);
    }
    }
}
