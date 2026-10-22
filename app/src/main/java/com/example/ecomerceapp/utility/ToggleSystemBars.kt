package com.example.ecomerceapp.utility

import android.app.Activity
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun ToggleSystemBars(show: Boolean) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as? Activity)?.window ?: return

        DisposableEffect(show) {
            val controller = WindowCompat.getInsetsController(window, view)

            if (show) {
                // Restore bars
                WindowCompat.setDecorFitsSystemWindows(window, true)
                controller.show(WindowInsetsCompat.Type.navigationBars())
            } else {
                // Hide bottom navigation icons
                WindowCompat.setDecorFitsSystemWindows(window, false)
                controller.hide(WindowInsetsCompat.Type.navigationBars())
                // Let user swipe up temporarily to access them without keeping them open
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

            onDispose {
                // Best practice: Ensure things return to default when leaving this Composable
                WindowCompat.setDecorFitsSystemWindows(window, true)
                controller.show(WindowInsetsCompat.Type.navigationBars())
            }
        }
    }
}
