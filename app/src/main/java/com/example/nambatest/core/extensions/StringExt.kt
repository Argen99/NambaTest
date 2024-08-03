package com.example.nambatest.core.extensions

import java.util.Locale

fun String.toTitleCase(): String {
    return this.split(" ").joinToString(" ") { word ->
        word.lowercase(Locale.getDefault()).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}

fun String.toImageUrl(size: Int = 2) = "http://openweathermap.org/img/wn/${this}@${size}x.png"