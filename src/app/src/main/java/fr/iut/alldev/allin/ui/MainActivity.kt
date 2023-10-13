package fr.iut.alldev.allin.ui

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import fr.iut.alldev.allin.ui.navigation.AllInNavHost
import fr.iut.alldev.allin.ui.theme.AllInTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AllInTheme{
                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        with((view.context as Activity)) {
                            window.statusBarColor = Color.Transparent.toArgb()
                            window.navigationBarColor = Color.Transparent.toArgb()

                            if (Build.VERSION.SDK_INT > 30) {
                                window.insetsController?.hide(WindowInsetsCompat.Type.statusBars())
                                window.insetsController?.hide(WindowInsetsCompat.Type.navigationBars())
                                WindowCompat.setDecorFitsSystemWindows(window, false)
                            }
                        }
                    }
                }
                AllInNavHost()
            }
        }
    }
}