package fr.iut.alldev.allin.ui.betcreation.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.betcreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.core.AllInIconChip
import fr.iut.alldev.allin.ui.core.AllInTextAndIcon
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BetCreationScreenQuestionTab(
    betTheme: String,
    setBetTheme: (String)->Unit,
    betPhrase: String,
    setBetPhrase: (String)->Unit,
    isPublic: Boolean,
    setIsPublic: (Boolean)->Unit
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        AllInTextAndIcon(
            text = stringResource(id = R.string.Theme),
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
            onClick = {}
        )
        AllInTextField(
            placeholder = stringResource(id = R.string.Theme_placeholder),
            value = betPhrase,
            onValueChange = setBetPhrase,
            bringIntoViewRequester = bringIntoViewRequester,
            borderColor = AllInTheme.colors.white,
            maxChar = 20,
            placeholderFontSize = 13.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        AllInTextAndIcon(
            text = stringResource(id = R.string.Bet_Phrase),
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
            onClick = {}
        )
        AllInTextField(
            placeholder = stringResource(id = R.string.Bet_Phrase_placeholder),
            value = betTheme,
            borderColor = AllInTheme.colors.white,
            onValueChange = setBetTheme,
            bringIntoViewRequester = bringIntoViewRequester,
            multiLine = true,
            maxChar = 100,
            placeholderFontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))
        AllInTextAndIcon(
            text = stringResource(id = R.string.Bet_privacy),
            icon = Icons.AutoMirrored.Outlined.HelpOutline,
            modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
            onClick = {}
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            AllInIconChip(
                text = stringResource(id = R.string.Public),
                leadingIcon = Icons.Default.Public,
                onClick = {
                    setIsPublic(true)
                },
                isSelected = isPublic
            )
            AllInIconChip(
                text = stringResource(id = R.string.Private),
                leadingIcon = Icons.Default.Lock,
                onClick = {
                    setIsPublic(false)
                },
                isSelected = !isPublic
            )
        }

        Spacer(modifier = Modifier.height(52.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            if(isPublic){
                BetCreationScreenBottomText(text = stringResource(id = R.string.public_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.public_bottom_text_2))
            }else{
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_2))
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_3))
            }
        }
    }
}