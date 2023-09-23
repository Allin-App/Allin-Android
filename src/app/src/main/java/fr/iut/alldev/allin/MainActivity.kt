package fr.iut.alldev.allin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import fr.iut.alldev.allin.ui.navigation.drawer.AllInDrawer
import fr.iut.alldev.allin.ui.theme.AllInTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            AllInTheme{

                systemUiController.setStatusBarColor(AllInTheme.colors.allIn_Dark)
                systemUiController.setNavigationBarColor(AllInTheme.colors.allIn_White)
                systemUiController.isNavigationBarVisible = false
                systemUiController.isStatusBarVisible = false

                AllInDrawer()
            }
        }
    }
}