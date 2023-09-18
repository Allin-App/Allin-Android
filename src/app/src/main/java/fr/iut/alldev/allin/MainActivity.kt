package fr.iut.alldev.allin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import fr.iut.alldev.allin.ui.core.AllInDrawer
import fr.iut.alldev.allin.ui.theme.AllInTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllInTheme {
                AllInDrawer()
            }

        }
    }
}