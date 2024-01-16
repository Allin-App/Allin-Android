package fr.iut.alldev.allin.ext

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Float.formatToSimple(locale: Locale): String {
    return DecimalFormat("0.##", DecimalFormatSymbols.getInstance(locale)).format(this)
}
