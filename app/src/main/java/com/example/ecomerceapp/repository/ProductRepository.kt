package com.example.ecomerceapp.repository

import com.example.ecomerceapp.model.product_models.ProductModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query

class ProductRepository {
    private val fireStore= FirebaseFirestore.getInstance()
    fun fetchProducts(onResult:(List<ProductModel>)-> Unit,
                      onError:(String?)->Unit): ListenerRegistration{
        return fireStore.collection("products")
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, exception ->
                if(exception !=null){
                    onError(exception.localizedMessage?:"Something went wrong!")
                    return@addSnapshotListener
                }
                if(snapshots==null){
                    onResult(emptyList())
                    return@addSnapshotListener
                }
                val products= snapshots.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(ProductModel::class.java)
                }
                onResult(products)

            }

    }
}