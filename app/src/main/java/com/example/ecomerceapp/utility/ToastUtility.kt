package com.example.ecomerceapp.utility


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable

class ToastUtility {
    companion object {
        fun makeLongToast(context: Context, message: String) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
        fun makeShortToast(context: Context, message: String) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
