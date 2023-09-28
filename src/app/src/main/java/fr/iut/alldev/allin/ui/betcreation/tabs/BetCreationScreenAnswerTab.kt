package fr.iut.alldev.allin.ui.betcreation.tabs

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInTextAndIcon

@Composable
fun BetCreationScreenAnswerTab() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        AllInTextAndIcon(
            text = stringResource(id = R.string.Answer),
            icon = Icons.Outlined.QuestionAnswer
        ){
            Log.d("ALLINNN", "AAAA")
        }
    }
}