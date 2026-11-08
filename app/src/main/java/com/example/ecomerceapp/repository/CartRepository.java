package com.example.ecomerceapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.ecomerceapp.local_database.dao.CartDao;
import com.example.ecomerceapp.local_database.database_classes.CartDB;
import com.example.ecomerceapp.local_database.entity.CartEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class CartRepository {
    private final CartDao cartDao;
    private final LiveData<List<CartEntity>> cartItems;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    public CartRepository(Context context){
        CartDB cartDB= CartDB.getDatabase(context);
        this.cartDao=cartDB.cartDao();
        this.cartItems= cartDao.getCartItems();
    }
    public LiveData<List<CartEntity>> getCartItems(){
        return cartItems;
    }
    public void insertCart(CartEntity cart) {
        executor.execute(() -> {
            CartEntity existingCart = cartDao.getCartItem(cart.productId);

            if (existingCart != null) {
                existingCart.quantity += 1;
                cartDao.updateCart(existingCart);
            } else {
                cartDao.insertCart(cart);
            }
        });
    }
    public void deleteCart(int cartId){
        executor.execute(() -> cartDao.deleteCart(cartId));
    }

    public void updateCart(CartEntity cart){
        executor.execute(() -> cartDao.updateCart(cart));
    }
    public  LiveData<Boolean> isAddedToCart(String productId){
        return cartDao.isAddedToCart(productId);
    }
    public void fetchCart(String productId, Consumer<CartEntity> onResult) {
        executor.execute(() -> {
            CartEntity cart = cartDao.getCartItem(productId);
            onResult.accept(cart);
        });
    }

}
