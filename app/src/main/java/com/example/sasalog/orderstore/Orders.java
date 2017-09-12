package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class Orders {
    int id;
    int customerId;
    int quantity;
    int price;
    int totalAmount;

    //constructor
    public Orders(){}
    public Orders(int id, int customerId, int quantity, int price, int totalAmount){
        this.id= id;
        this.customerId= customerId;
        this.quantity= quantity;
        this.price= price;
        this.totalAmount= totalAmount;
    }

    //setter methods
    public void setId(int id){
        this.id= id;
    }

    public void setCustomerId(int customerId){
        this.customerId= customerId;
    }

    public void setQuantity(int quantity){
        this.quantity= quantity;
    }

    public void setPrice(int price){
        this.price= price;
    }

    public void setTotalAmount(int totalAmount){
        this.totalAmount= totalAmount;
    }

    //getter methods
    public int getId(){
        return this.id;
    }

    public int setCustomerId(){
        return  this.customerId;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getPrice(){
        return this.price;
    }
    public int getTotalAmount(){
       return this.totalAmount;
    }

}
