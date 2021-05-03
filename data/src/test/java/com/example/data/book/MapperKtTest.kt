package com.example.data.book

import com.example.domain.common.BookDetails
import org.junit.Test
import kotlin.test.assertEquals

class MapperKtTest {

    @Test
    fun `when non prime number THEN return false`() {
        assertEquals(isPrimeNumber(1928), false)
        assertEquals(isPrimeNumber(16), false)
        assertEquals(isPrimeNumber(77), false)
        assertEquals(isPrimeNumber(258), false)
        assertEquals(isPrimeNumber(826), false)
        assertEquals(isPrimeNumber(812), false)
    }

    @Test
    fun `when prime number THEN return true`() {
        assertEquals(isPrimeNumber(1), true)
        assertEquals(isPrimeNumber(2), true)
        assertEquals(isPrimeNumber(857), true)
        assertEquals(isPrimeNumber(911), true)
        assertEquals(isPrimeNumber(821), true)
    }

    @Test
    fun `when given a book filter space and apostrophe THEN return words with count`() {
        assertEquals(getWordsAndCount(book), fakeWordsWithCount())
    }

    @Test
    fun `when given a map of words and count THEN return words with count with prime number check`() {
        assertEquals(formatWithPrimeNumberCheck(getWordsAndCount(book)), fakeWordsAndCountWithPrimeNumberCheck())
    }

    private fun fakeWordsWithCount() : HashMap<String, Int> {
        val wordsWithCount = hashMapOf<String, Int>()
        wordsWithCount["next"] = 2
        wordsWithCount["came"] = 2
        wordsWithCount["peter"] = 1
        wordsWithCount["a"] = 2
        wordsWithCount["engineer"] = 1
        wordsWithCount["prime"] = 5
        wordsWithCount["number"] = 1
        wordsWithCount["butter"] = 4
        return wordsWithCount
    }

    private fun fakeWordsAndCountWithPrimeNumberCheck() : HashMap<String, BookDetails> {
        val bookDetails = hashMapOf<String, BookDetails>()
        bookDetails["next"] = BookDetails(2, true)
        bookDetails["came"] = BookDetails(2, true)
        bookDetails["peter"] = BookDetails(1, true)
        bookDetails["a"] = BookDetails(2, true)
        bookDetails["engineer"] = BookDetails(1, true)
        bookDetails["prime"] = BookDetails(5, true)
        bookDetails["number"] = BookDetails(1, true)
        bookDetails["butter"] = BookDetails(4, false)
        return bookDetails
    }

    companion object TestData {
        const val book = "Next came butter ' Peter ' a prime number butter  next  ' ' ' '   came butter a Engineer Prime butter prime prime prime"
    }
}