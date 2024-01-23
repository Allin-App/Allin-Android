package fr.iut.alldev.allin.vo.bet

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.iut.alldev.allin.test.TestTags
import fr.iut.alldev.allin.test.mock.Bets
import fr.iut.alldev.allin.ui.MainActivity
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.vo.bet.displayer.BetTestDisplayer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BetVOTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    companion object {
        val displayer = BetTestDisplayer()
    }

    @Test
    fun testDisplayer_shouldDisplayYesNoBetUI(){
        //Given

        //When
        composeTestRule.activity.setContent {
             AllInTheme{
                 displayer.DisplayBet(Bets.bets[0])
            }
        }
        //Expect
        composeTestRule.onNodeWithTag(TestTags.YES_NO_BET.tag).assertExists()
        composeTestRule.onNodeWithTag(TestTags.MATCH_BET.tag).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_BET.tag).assertDoesNotExist()
    }

    @Test
    fun testDisplayer_shouldDisplayMatchUI(){
        //Given

        //When
        composeTestRule.activity.setContent {
            AllInTheme{
                displayer.DisplayBet(Bets.bets[1])
            }
        }
        //Expect
        composeTestRule.onNodeWithTag(TestTags.MATCH_BET.tag).assertExists()
        composeTestRule.onNodeWithTag(TestTags.YES_NO_BET.tag).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_BET.tag).assertDoesNotExist()
    }

    @Test
    fun testDisplayer_shouldDisplayCustomBetUI(){
        //Given

        //When
        composeTestRule.activity.setContent {
            AllInTheme{
                displayer.DisplayBet(Bets.bets[2])
            }
        }
        //Expect
        composeTestRule.onNodeWithTag(TestTags.MATCH_BET.tag).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.YES_NO_BET.tag).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.CUSTOM_BET.tag).assertExists()
    }

}