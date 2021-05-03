package com.example.storyreader.util

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

fun makePrimeNumberText(context: Context,
                        @StringRes primeNumberDescription: Int,
                        @ColorRes colorRes: Int, @StringRes isPrimeNumberResource: Int
): CharSequence {
    val color = context.resources.getColor(colorRes)
    val isPrimeNumber = context.resources.getString(isPrimeNumberResource)
    val formatted = context.resources.getString(primeNumberDescription, isPrimeNumber)
    val span = SpannableStringBuilder(formatted)
    val start = formatted.indexOf(isPrimeNumber)
    val end = start + isPrimeNumber.length
    span.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    span.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return span
}