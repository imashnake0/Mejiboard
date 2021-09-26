package com.github.uragiristereo.mejiboard.util

import java.io.File
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

object FileHelper {
    fun convertSize(sizeBytes: Int): String {
        if (sizeBytes == 0)
            return "0 B"

        val sizeName = listOf("B", "KB", "MB", "GB")

        val i = floor(ln(sizeBytes.toDouble()) / ln(1024.0))
        val p = 1024.0.pow(i)
        val s = (sizeBytes / p * 100).roundToInt() / 100.0

        return "$s ${sizeName[i.toInt()]}"
    }

    fun getFolderSize(file: File): Long {
        var size: Long = 0

        if (file.isDirectory) {
            file.walk().forEach {
                if (it.isFile)
                    size += it.length()
            }
        }

        return size
    }
}