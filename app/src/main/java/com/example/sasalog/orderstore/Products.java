package com.example.sasalog.orderstore;

/**
 * Created by sasalog on 9/12/17.
 */

public class Products {
    int id;
    String productName;

    //constructor
    public Products(){}

    public Products(int id, String productName){
        this.id= id;
        this.productName= productName;
    }

    //setter methods
    public void setId(int id){
        this.id= id;
    }

  public void setProductName(String productName){
      this.productName= productName;
  }

  //getter methods
    public int getId() {
        return this.id;
    }

    public String getProductName(){
        return this.productName;
    }
}
