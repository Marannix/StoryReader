package com.example.data.book

import com.example.domain.common.BookDetails

fun getWordsAndCount(book: String): HashMap<String, Int> {
    val wordsWithCount = hashMapOf<String, Int>()
    book.split(" ").forEach { word ->
        val capitalizedWord = word.toLowerCase().capitalize()
        if (wordsWithCount[capitalizedWord] == null) {
            wordsWithCount[capitalizedWord] = 1
        } else {
            val count = wordsWithCount[capitalizedWord]!!
            wordsWithCount[capitalizedWord] = count + 1
        }
    }
    return wordsWithCount
}

fun formatWithPrimeNumberCheck(wordsWithCount: HashMap<String, Int>): HashMap<String, BookDetails> {
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

private fun isPrimeNumber(number: Int) : Boolean {
    var isPrimeNumber = true
    for (i in 2..number / 2) {
        if (number % i == 0) {
            isPrimeNumber = false
            break
        }
    }

    return isPrimeNumber
}