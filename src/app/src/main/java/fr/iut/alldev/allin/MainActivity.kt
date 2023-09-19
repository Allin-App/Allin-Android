package fr.iut.alldev.allin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import fr.iut.alldev.allin.ui.navigation.drawer.AllInDrawer
import fr.iut.alldev.allin.ui.theme.AllInTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllInTheme {
                AllInDrawer()
            }

        }
    }
}