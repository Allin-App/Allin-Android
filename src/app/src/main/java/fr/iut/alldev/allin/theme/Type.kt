package fr.iut.alldev.allin.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import fr.iut.alldev.allin.R

private val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
)

private val fontNamePlusJakartaSans = GoogleFont(name = "Plus Jakarta Sans")

val fontFamilyPlusJakartaSans = FontFamily(
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.ExtraLight),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.Light),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.Normal),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.Medium),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.SemiBold),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.Bold),
        Font(googleFont = fontNamePlusJakartaSans, fontProvider = provider, weight = FontWeight.ExtraBold),
)


@Immutable
data class AllInTypography(
        val h1: TextStyle,
        val h2: TextStyle,
        val sm1: TextStyle,
        val sm2: TextStyle,
        val p1: TextStyle,
        val p2: TextStyle,
        val l1: TextStyle
)

internal val LocalTypography = staticCompositionLocalOf {
        AllInTypography(
                h1 = TextStyle.Default,
                h2 = TextStyle.Default,
                sm1 = TextStyle.Default,
                sm2 = TextStyle.Default,
                p1 = TextStyle.Default,
                p2 = TextStyle.Default,
                l1 = TextStyle.Default
        )
}