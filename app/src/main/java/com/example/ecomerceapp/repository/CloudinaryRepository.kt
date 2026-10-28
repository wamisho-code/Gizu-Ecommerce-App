package com.example.ecomerceapp.repository


import android.net.Uri
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.ecomerceapp.model.product_models.ProductImageModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class CloudinaryRepository {

    suspend fun uploadImage(uri: Uri): ProductImageModel =
        suspendCancellableCoroutine { continuation ->

            MediaManager.get()
                .upload(uri)
                .unsigned("gizu_products")
                .callback(object : UploadCallback {

                    override fun onStart(requestId: String?) = Unit

                    override fun onProgress(
                        requestId: String?,
                        bytes: Long,
                        totalBytes: Long
                    ) = Unit

                    override fun onSuccess(
                        requestId: String?,
                        resultData: Map<Any?, Any?>?
                    ) {
                        val secureUrl = resultData?.get("secure_url") as? String
                        val publicID = resultData?.get("public_id") as? String

                        if (secureUrl != null && publicID != null) {
                            if (continuation.isActive) {
                                continuation.resume(
                                    ProductImageModel(
                                        url = secureUrl,
                                        publicID = publicID
                                    )
                                )
                            }
                        } else {
                            if (continuation.isActive) {
                                continuation.resumeWithException(
                                    Exception("Cloudinary returned no URL or public ID")
                                )
                            }
                        }
                    }

                    override fun onError(
                        requestId: String?,
                        error: ErrorInfo?
                    ) {
                        if (continuation.isActive) {
                            continuation.resumeWithException(
                                Exception(
                                    error?.description ?: "Image upload failed"
                                )
                            )
                        }
                    }

                    override fun onReschedule(
                        requestId: String?,
                        error: ErrorInfo?
                    ) = Unit
                })
                .dispatch()
        }


    suspend fun uploadImages(
        uris: List<Uri>
    ): List<ProductImageModel> {

        require(uris.isNotEmpty()) {
            "Select at least one image."
        }

        return uris.map { uri ->
            uploadImage(uri)
        }
    }

}
