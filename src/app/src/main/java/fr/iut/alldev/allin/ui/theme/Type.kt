package fr.iut.alldev.allin.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import fr.iut.alldev.allin.R

val PlusJakartaSans = FontFamily(
        Font(R.font.plusjakartasans_extralight, weight = FontWeight.ExtraLight),
        Font(R.font.plusjakartasans_light, weight = FontWeight.Light),
        Font(R.font.plusjakartasans_regular, weight = FontWeight.Normal),
        Font(R.font.plusjakartasans_medium, weight = FontWeight.Medium),
        Font(R.font.plusjakartasans_semibold, weight = FontWeight.SemiBold),
        Font(R.font.plusjakartasans_bold, weight = FontWeight.Bold),
        Font(R.font.plusjakartasans_extrabold, weight = FontWeight.ExtraBold)
    )

@Immutable
data class AllInTypography(
        val h1: TextStyle,
        val h2: TextStyle,
        val h3: TextStyle,
        val m: TextStyle,
        val r: TextStyle,
        val s: TextStyle,
        val xs: TextStyle
)

val LocalTypography = staticCompositionLocalOf {
        AllInTypography(
                h1 = TextStyle.Default,
                h2 = TextStyle.Default,
                h3 = TextStyle.Default,
                m = TextStyle.Default,
                r = TextStyle.Default,
                s = TextStyle.Default,
                xs = TextStyle.Default
        )
}