package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class Customers {
    int id;
    String firstName;
    String lastName;
    String telephone;
    String password;
    String priviledge;

    //constructors
    public  Customers(){
    }

    public Customers(int id, String firstName, String lastName, String telephone, String password, String priviledge){
        this.id= id;
        this.password= password;
        this.priviledge= priviledge;
        this.firstName= firstName;
        this.lastName= lastName;
        this.telephone= telephone;
    }

    //setter methods
    public void setId(int id){
        this.id= id;
    }

    public void setNames(String firstName, String lastName){
        this.firstName= firstName;
        this.lastName= lastName;
    }

    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    public void setPassword(String password){
        this.password= password;
    }

    public void setPriviledge(String priviledge){
        this.priviledge= priviledge;
    }

    public  void setLastName(String lastName){
        this.lastName= lastName;
    }

    public void setTelephone(String telephone){
        this.telephone= telephone;
    }

    //getter method
    public int getId(){
        return this.id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public  String getPassword(){return this.password; }

    public String getPriviledge(){return this.priviledge; }
}
