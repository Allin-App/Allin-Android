package fr.iut.alldev.allin.data.ext

fun Float.toPercentageString(precision: Int = 0) = String.format("%.${precision}f", this * 100) + "%"