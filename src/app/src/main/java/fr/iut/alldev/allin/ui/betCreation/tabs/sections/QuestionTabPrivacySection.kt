package fr.iut.alldev.allin.ui.betCreation.tabs.sections

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenFriendLine
import fr.iut.alldev.allin.ui.core.AllInIconChip
import fr.iut.alldev.allin.ui.core.AllInRetractableCard
import fr.iut.alldev.allin.ui.core.AllInTitleInfo

@Composable
fun QuestionTabPrivacySection(
    isPublic: Boolean,
    setIsPublic: (Boolean) -> Unit,
    nbFriends: Int,
    selectedFriends: MutableList<Int>,
    interactionSource: MutableInteractionSource,
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.Bet_privacy),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.Privacy_tooltip),
        interactionSource = interactionSource
    )
    Row(
        modifier = Modifier.padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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

    Column(
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        var isOpen by remember {
            mutableStateOf(false)
        }

        if (isPublic) {
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                BetCreationScreenBottomText(text = stringResource(id = R.string.public_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.public_bottom_text_2))
            }
        } else {
            AllInRetractableCard(
                text = stringResource(
                    id = R.string.n_friends_available,
                    nbFriends,
                    nbFriends
                ),
                borderWidth = 1.dp,
                boldText = nbFriends.toString(),
                isOpen = isOpen,
                setIsOpen = { isOpen = it }
            ) {
                LazyColumn(
                    modifier = Modifier.height(165.dp)
                ) {
                    items(nbFriends) {
                        var isSelected by remember {
                            mutableStateOf(selectedFriends.contains(it))
                        }

                        if (it != 0) {
                            HorizontalDivider(color = AllInTheme.themeColors.border)
                        }
                        BetCreationScreenFriendLine(
                            username = "Dave",
                            allCoinsAmount = 542,
                            isSelected = isSelected
                        ) {
                            if (isSelected) {
                                selectedFriends.remove(it)
                            } else {
                                selectedFriends.add(it)
                            }
                            isSelected = !isSelected
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_2))
                BetCreationScreenBottomText(text = stringResource(id = R.string.private_bottom_text_3))
            }
        }
    }
}