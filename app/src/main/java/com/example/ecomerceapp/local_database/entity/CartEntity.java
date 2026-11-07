package com.example.ecomerceapp.local_database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ecomerceapp.model.product_models.ProductModel;

@Entity(tableName = "cart")
public class CartEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String productId;
    public String productName;
    public String productImage;
    public double price;
    public int quantity=1 ;
}
