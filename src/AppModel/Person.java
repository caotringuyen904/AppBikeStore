/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppModel;

/**
 *
 * @author HP
 */
public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    public Person(){};
    protected Person(String fname, String lname, String email, String phone){
        this.firstName = fname;
        this.lastName = lname;
        this.phone = phone;
        this.email = email;
    }
    public void setFirstName (String fname){
        this.firstName = fname;
    }
    public void setLastName (String lname){
        this.lastName = lname;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setPhone (String phone){
        this.phone = phone;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
}
