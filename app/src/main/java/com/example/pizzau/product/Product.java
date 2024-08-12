package com.example.pizzau.product;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String id;
    private String name;
    private  String price;
    private  boolean deleted;

    public Product(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }
    public Product(){

    }

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return  price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public Map<String,Object> toMap(){
        Map<String , Object> resultado = new HashMap<>();
        resultado.put("id" , id);
        resultado.put("name" , name);
        resultado.put("price", price);
        return resultado;


    }
}
