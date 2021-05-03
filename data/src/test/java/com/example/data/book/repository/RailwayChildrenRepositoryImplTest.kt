package com.example.data.book.repository

import com.example.data.remote.BooksApi
import com.example.data.utils.FileDataProvider
import com.example.domain.book.repository.RailwayChildrenRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

const val RAILWAY_CHILDREN_BOOK = "TheRailwayChildrenBookResponse.text"

class RailwayChildrenRepositoryImplTest {

    lateinit var repository: RailwayChildrenRepository
    private val booksApi: BooksApi = mockk()
    private val fileDataProvider: FileDataProvider = mockk()

    @Before
    fun setUp() {
        repository = RailwayChildrenRepositoryImpl(booksApi, fileDataProvider)
    }

    @Test
    fun `retrieve railway children book from api`() {
        every { booksApi.getBook(RAILWAY_CHILDREN_BOOK) } returns Single.just(book)

        val testObserver: TestObserver<String> =
            repository.getBook(RAILWAY_CHILDREN_BOOK).test()

        testObserver.awaitTerminalEvent()

        testObserver
            .assertValue(filteredBook)
            .assertNoErrors()
    }

    @Test
    fun `throw error when retrieving railway children book from api fails`() {
        every { booksApi.getBook(RAILWAY_CHILDREN_BOOK) } returns Single.error(Exception())

        val testObserver: TestObserver<String> =
            repository.getBook(RAILWAY_CHILDREN_BOOK).test()

        testObserver.awaitTerminalEvent()

        testObserver
            .assertError { it is Exception }
    }

    @Test
    fun `retrieve railway children book from the local storage`() {
        every { fileDataProvider.loadFileFromAsset(RAILWAY_CHILDREN_BOOK) } returns localBook

        assertEquals(repository.getBookFromLocalStorage(RAILWAY_CHILDREN_BOOK), filteredLocalBook)
    }

    companion object TestData {
        const val book = "Next came Peter, who wished to be an Engineer when he\n grew up;"
        const val filteredBook = "Next came Peter  who wished to be an Engineer when he  grew up "

        const val localBook = "That'll do, Ruth,\" said Mother gently; \"you can go."
        const val filteredLocalBook = "That'll do  Ruth   said Mother gently   you can go "
    }
}