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
        wordsWithCount["Next"] = 2
        wordsWithCount["Came"] = 2
        wordsWithCount["Peter"] = 1
        wordsWithCount["A"] = 2
        wordsWithCount["Engineer"] = 1
        wordsWithCount["Prime"] = 5
        wordsWithCount["Number"] = 1
        wordsWithCount["Butter"] = 4
        return wordsWithCount
    }

    private fun fakeWordsAndCountWithPrimeNumberCheck() : HashMap<String, BookDetails> {
        val bookDetails = hashMapOf<String, BookDetails>()
        bookDetails["Next"] = BookDetails(2, true)
        bookDetails["Came"] = BookDetails(2, true)
        bookDetails["Peter"] = BookDetails(1, true)
        bookDetails["A"] = BookDetails(2, true)
        bookDetails["Engineer"] = BookDetails(1, true)
        bookDetails["Prime"] = BookDetails(5, true)
        bookDetails["Number"] = BookDetails(1, true)
        bookDetails["Butter"] = BookDetails(4, false)
        return bookDetails
    }

    companion object TestData {
        const val book = "Next came butter ' Peter ' a prime number butter  next  ' ' ' '   came butter a Engineer Prime butter prime prime prime"
    }
}