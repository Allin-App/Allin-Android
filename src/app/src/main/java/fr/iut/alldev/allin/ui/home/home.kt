package fr.iut.alldev.allin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.core.AllInChip
import fr.iut.alldev.allin.ui.home.components.HomeBetCard
import fr.iut.alldev.allin.ui.home.components.HomePopularCards

@Composable
@Preview
fun Home(){

    val horizontalPadding = 23.dp

    Column {
        HomePopularCards(
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .padding(top = 23.dp),
            nbPlayers = 12,
            points = "2.35k",
            title = "Emre va réussir son TP de CI/CD mercredi?")
        LazyRow(
            modifier = Modifier.padding(vertical = 19.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ){
            item{
                Spacer(modifier = Modifier.width(horizontalPadding))
            }
            items(items){
                var isSelected  by remember { mutableStateOf(false) }
                AllInChip(text = it, isSelected = isSelected, onClick = {isSelected = !isSelected})
            }
            item{
                Spacer(modifier = Modifier.width(horizontalPadding))
            }
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = horizontalPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){
            items(5){
                HomeBetCard(
                    creator = "Lucas",
                    category = "Études",
                    title = "Emre va réussir son TP de CI/CD mercredi?",
                    date = "11 Sept.",
                    time = "13:00",
                    nbPlayer = 4,
                    onClickParticipate = { /* TODO */ }
                )
            }
        }
    }
}

val items = listOf(
    "Public",
    "En cours",
    "Invitation",
    "Terminés"
)