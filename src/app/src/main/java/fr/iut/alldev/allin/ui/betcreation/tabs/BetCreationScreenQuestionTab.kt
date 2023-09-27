package fr.iut.alldev.allin.ui.betcreation.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInIconChip

@Composable
fun BetCreationScreenQuestionTab() {
    Column(Modifier.padding(20.dp)){
        var isPublic by remember{
            mutableStateOf(true)
        }
        Text(text = stringResource(id = R.string.Question))
        Spacer(modifier = Modifier.height(7.dp))
        AllInIconChip(
            text = "Public",
            leadingIcon = Icons.Default.Public,
            onClick = {
                isPublic = true
            },
            isSelected = isPublic
        )
        AllInIconChip(
            text = "Privé",
            leadingIcon = Icons.Default.Lock,
            onClick = {
                isPublic = false
            },
            isSelected = !isPublic
        )
    }
}