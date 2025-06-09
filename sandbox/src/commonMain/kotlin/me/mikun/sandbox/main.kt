package me.mikun.sandbox

import me.mikun.mylibrary.platform
fun gradientText(
    text: String,
    startRgb: Triple<Int, Int, Int>,
    endRgb: Triple<Int, Int, Int>,
): String {
    val (r1, g1, b1) = startRgb
    val (r2, g2, b2) = endRgb
    val reset = "\u001B[0m"

    return buildString {
        text.forEachIndexed { index, char ->
            val ratio = index.toDouble() / (text.length - 1)
            val r = (r1 + (r2 - r1) * ratio).toInt()
            val g = (g1 + (g2 - g1) * ratio).toInt()
            val b = (b1 + (b2 - b1) * ratio).toInt()
            append("\u001B[38;2;$r;$g;${b}m$char")
        }
        append(reset)
    }
}

fun main() {
    val startColor = Triple(255, 127, 0)
    val endColor = Triple(0, 127, 255)
    val text = "Hello, ${platform}!"
    println(gradientText(text, startColor, endColor))
}