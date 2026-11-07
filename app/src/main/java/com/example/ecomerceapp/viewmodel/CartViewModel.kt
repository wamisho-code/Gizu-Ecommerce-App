package com.example.ecomerceapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ecomerceapp.local_database.entity.CartEntity
import com.example.ecomerceapp.repository.CartRepository

class CartViewModel(application: Application): AndroidViewModel(application) {
    private val repo = CartRepository(application)
    val cartItems: LiveData<List<CartEntity>> = repo.cartItems
}