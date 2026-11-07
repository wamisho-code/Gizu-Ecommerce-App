package com.example.ecomerceapp.local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ecomerceapp.local_database.entity.CartEntity;

import java.util.List;

@Dao
public interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCart(CartEntity cart);
    @Update
    void updateCart(CartEntity cart);
    @Query("delete from cart where id = :cartID")
    void deleteCart(int cartID);
    @Query("select * from cart ")
    LiveData<List<CartEntity>> getCartItems();
    @Query("SELECT * FROM cart WHERE productId = :productId LIMIT 1")
    CartEntity getCartItem(String productId);
}
