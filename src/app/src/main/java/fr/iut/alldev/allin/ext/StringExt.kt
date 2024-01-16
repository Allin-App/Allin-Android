package fr.iut.alldev.allin.ext

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun String.verifyIsFloat(locale: Locale): String? {
    val pattern = Regex("^\\d+(\\.|\\,)?\\d*\$")
    val decimalSeparator = DecimalFormatSymbols.getInstance(locale).decimalSeparator.toString()

    return if (this.matches(pattern)) {
        this.replace(Regex("[.,]"), decimalSeparator).format()
    } else if (this.isEmpty()) {
        this
    } else null
}

fun String.toFloatOrNull(locale: Locale): Float? {
    val format = DecimalFormat("0.##", DecimalFormatSymbols.getInstance(locale))
    return format.parse(this)?.toFloat()
}