package com.github.uragiristereo.mejiboard.common.helper

import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

object NumberHelper {
    fun convertToReadable(number: Int): String {
        if (number < 1000)
            return "$number"

        val units = listOf(' ', 'K', 'M', 'G')

        val i = floor(ln(number.toDouble()) / ln(1000.0))
        val p = 1000.0.pow(i)
        val s = (number / p * 100).roundToInt() / 100.0

        return if (s > 10) {
            "%d%s".format(s.roundToInt(), units[i.toInt()])
        } else {
            "%.1f%s".format(s, units[i.toInt()])
                .replace(oldValue = ".0", newValue = "")
        }
    }
}