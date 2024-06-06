package fr.iut.alldev.allin.ui.betCreation.tabs.sections

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.nonLinkedScroll
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenBottomText
import fr.iut.alldev.allin.ui.betCreation.components.BetCreationScreenFriendLine
import fr.iut.alldev.allin.ui.core.AllInIconChip
import fr.iut.alldev.allin.ui.core.AllInRetractableCard
import fr.iut.alldev.allin.ui.core.AllInTitleInfo
import fr.iut.alldev.allin.ui.core.bet.AllInEmptyView

@Composable
fun QuestionTabPrivacySection(
    isPrivate: Boolean,
    setIsPrivate: (Boolean) -> Unit,
    friends: List<User>,
    selectedFriends: List<String>,
    interactionSource: MutableInteractionSource,
    toggleFriend: (String) -> Unit
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.bet_creation_bet_privacy),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.bet_creation_privacy_tooltip),
        interactionSource = interactionSource
    )
    Row(
        modifier = Modifier.padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AllInIconChip(
            text = stringResource(id = R.string.bet_public),
            leadingIcon = Icons.Default.Public,
            onClick = { setIsPrivate(false) },
            isSelected = !isPrivate
        )
        AllInIconChip(
            text = stringResource(id = R.string.bet_private),
            leadingIcon = Icons.Default.Lock,
            onClick = { setIsPrivate(true) },
            isSelected = isPrivate
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        var isOpen by remember { mutableStateOf(false) }

        if (isPrivate) {
            AllInRetractableCard(
                text = pluralStringResource(
                    id = R.plurals.bet_creation_friends_available_format,
                    friends.size,
                    friends.size
                ),
                borderWidth = 1.dp,
                boldText = friends.size.toString(),
                isOpen = isOpen,
                setIsOpen = { isOpen = it }
            ) {
                Box {
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 440.dp)
                            .nonLinkedScroll()
                    ) {
                        itemsIndexed(friends, key = { _, it -> it.id }) { idx, it ->
                            var isSelected by remember {
                                mutableStateOf(selectedFriends.contains(it.id))
                            }

                            if (idx != 0) {
                                HorizontalDivider(color = AllInTheme.colors.border)
                            }
                            BetCreationScreenFriendLine(
                                username = it.username,
                                allCoinsAmount = it.coins,
                                image = it.image,
                                isSelected = isSelected
                            ) {
                                toggleFriend(it.id)
                                isSelected = !isSelected
                            }
                        }
                    }

                    if (friends.isEmpty()) {
                        AllInEmptyView(
                            text = stringResource(id = R.string.friends_empty_text),
                            subtext = stringResource(id = R.string.friends_empty_subtext),
                            image = painterResource(id = R.drawable.silhouettes),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        )
                    }
                }

            }
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_private_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_private_bottom_text_2))
                BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_private_bottom_text_3))
            }
        } else {
            Column(
                modifier = Modifier.padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_public_bottom_text_1))
                BetCreationScreenBottomText(text = stringResource(id = R.string.bet_creation_public_bottom_text_2))
            }
        }
    }
}