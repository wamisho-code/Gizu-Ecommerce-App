package com.example.ecomerceapp.repository

import android.util.Log
import com.example.ecomerceapp.model.product_models.ProductModel
import com.example.ecomerceapp.utility.BasicDataCheckUtility
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

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
    suspend fun getRelatedProducts(
        product: ProductModel
    ): List<ProductModel> {

        val tokens = (product.searchTokens).take(30)
        Log.d("RELATED", "Product: ${product.productName}")
        Log.d("RELATED", "Tokens: ${product.searchTokens}")

        if (tokens.isEmpty()) return emptyList()

        val snapshot = FirebaseFirestore.getInstance()
            .collection("products")
            .whereArrayContainsAny("searchTokens", tokens)
            .limit(50)
            .get()
            .await()

        return snapshot.documents.mapNotNull {
            it.toObject(ProductModel::class.java)
        }
            .filter {
                it.productId != product.productId &&
                        it.quantity > 0
            }
            .map { candidate ->
                candidate to calculateSimilarity(product, candidate)
            }
            .filter { it.second >= 0.15 }
            .sortedByDescending { it.second }
            .take(10)
            .map { it.first }
    }
    fun calculateSimilarity(
        current: ProductModel,
        candidate: ProductModel
    ): Double {

        val candidateTokens = candidate.searchTokens
            .ifEmpty { BasicDataCheckUtility.generateSearchTokens(candidate) }
            .toSet()

        val nameTokens = current.productName
            .lowercase()
            .split(Regex("[^a-z0-9]+"))
            .filter { it.isNotBlank() }
            .toSet()

        val categoryTokens = current.productCategory
            .lowercase()
            .split(Regex("[^a-z0-9]+"))
            .filter { it.isNotBlank() }
            .toSet()

        val descriptionTokens = current.productDescription
            .lowercase()
            .split(Regex("[^a-z0-9]+"))
            .filter { it.isNotBlank() }
            .toSet()

        val nameScore = nameTokens.intersect(candidateTokens).size.toDouble() /
                nameTokens.size.coerceAtLeast(1)

        val categoryScore = categoryTokens.intersect(candidateTokens).size.toDouble() /
                categoryTokens.size.coerceAtLeast(1)

        val descriptionScore = descriptionTokens.intersect(candidateTokens).size.toDouble() /
                descriptionTokens.size.coerceAtLeast(1)

        return (
                nameScore * 0.50 +
                        categoryScore * 0.35 +
                        descriptionScore * 0.15
                )
    }
}