package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class OrderLines {
    int id;
    int orderId;
    int productId;

    //constructors
    public OrderLines(){}

    public OrderLines(int id, int orderId, int productId){
        this.id= id;
        this.orderId= orderId;
        this.productId= productId;
    }

    //setter methods
    public void setId(int id){
        this.id= id;
    }

    public void setOrderId(int orderId){
        this.orderId= orderId;
    }

    public void setProductId(int productId){
        this.productId= productId;
    }

    //getter methods
    public int getId(){
        return this.id;
    }

    public int getOrderId(){
        return this.orderId;
    }

    public int getProductId(){
        return this.productId;
    }

}
