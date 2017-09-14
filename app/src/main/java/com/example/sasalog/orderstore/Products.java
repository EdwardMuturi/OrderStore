package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class Products {
    int id;
    String productName;
    int price;

    //constructor
    public Products(){}

    public Products(int id, String productName, int price){
        this.id= id;
        this.productName= productName;
        this.price= price;
    }

    //setter methods
    public void setId(int id){
        this.id= id;
    }

    public void setProductName(String productName){
      this.productName= productName;
  }

    public void setPrice(int price) {this.price= price;}

  //getter methods
    public int getId() {
        return this.id;
    }

    public String getProductName(){
        return this.productName;
    }

    public int getPrice(){ return this.price; }
}
