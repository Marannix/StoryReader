package com.example.data.book

import com.example.data.utils.APOSTROPHE
import com.example.data.utils.EMPTY_SPACE
import com.example.domain.common.BookDetails

fun getWordsAndCount(book: String): Map<String, Int> {
    return book.split(EMPTY_SPACE)
        .filter { it.isNotEmpty() && it != APOSTROPHE }
        .groupingBy { it.toLowerCase() }
        .eachCount()
}

fun formatWithPrimeNumberCheck(wordsWithCount: Map<String, Int>): HashMap<String, BookDetails> {
    val primeNumberMap = hashMapOf<Int, Boolean>()
    val bookDetails = hashMapOf<String, BookDetails>()

    wordsWithCount.map {
        if (primeNumberMap[it.value] == null) {
            val isPrimeNumber = isPrimeNumber(it.value)
            bookDetails[it.key] = BookDetails(it.value, isPrimeNumber)
            primeNumberMap[it.value] = isPrimeNumber
        } else {
            bookDetails[it.key] = BookDetails(it.value, primeNumberMap[it.value]!!)
        }
    }

    return bookDetails
}

fun isPrimeNumber(number: Int) : Boolean {
    if (number == 1) return false
    if (number == 2) return true
    var isPrimeNumber = true
    for (i in 2..number / 2) {
        if (number % i == 0) {
            isPrimeNumber = false
            break
        }
    }
    return isPrimeNumber
}