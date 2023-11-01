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
import fr.iut.alldev.allin.vo.bet.factory.toBetVO
import fr.iut.alldev.allin.vo.bet.visitor.BetTestVisitor
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
        val visitor = BetTestVisitor()
    }

    @Test
    fun testVisitor_shouldDisplayYesNoBetUI(){
        //Given

        //When
        composeTestRule.activity.setContent {
             AllInTheme{
                 Bets.bets[0].toBetVO()?.accept(v = visitor)
            }
        }
        //Expect
        composeTestRule.onNodeWithTag(TestTags.YES_NO_BET.tag).assertExists()
        composeTestRule.onNodeWithTag(TestTags.MATCH_BET.tag).assertDoesNotExist()
    }

    @Test
    fun testVisitor_shouldDisplayMatchUI(){
        //Given

        //When
        composeTestRule.activity.setContent {
            AllInTheme{
                Bets.bets[1].toBetVO()?.accept(v = visitor)
            }
        }
        //Expect
        composeTestRule.onNodeWithTag(TestTags.MATCH_BET.tag).assertExists()
        composeTestRule.onNodeWithTag(TestTags.YES_NO_BET.tag).assertDoesNotExist()
    }

}