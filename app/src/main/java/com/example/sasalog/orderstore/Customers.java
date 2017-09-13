package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class Customers {
    int id;
    String firstName;
    String lastName;
    String telephone;

    //constructors
    public  Customers(){
    }

    public Customers(int id, String firstName, String lastName, String telephone){
        this.id= id;
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
        return this.lastName;
    }
}
