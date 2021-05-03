package com.example.data.book.usecase

import com.example.data.book.formatWithPrimeNumberCheck
import com.example.data.book.getWordsAndCount
import com.example.domain.book.repository.RailwayChildrenRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Test

class GetRailwayChildrenWordsUseCaseTest {

    private val repository: RailwayChildrenRepository = mockk()
    private val railwayChildrenWordsUseCase: GetRailwayChildrenWordsUseCase = mockk()

    @Test
    fun `WHEN get book returns success THEN success state is returned`() {
        every { repository.getBook(any()) } returns Observable.just(book)
        every { railwayChildrenWordsUseCase.invoke() } returns Observable.just(
            GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Success(listOfWords))

        railwayChildrenWordsUseCase.invoke().test()
            .assertValueAt(0, GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Success(listOfWords))
    }

    @Test
    fun `WHEN get book returns error THEN error state is returned`() {
        every { repository.getBook(any()) } returns Observable.error(Throwable())
        every { railwayChildrenWordsUseCase.invoke() } returns Observable.just(
            GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Error(errorMessage, bookFromStorage))

        railwayChildrenWordsUseCase.invoke().test()
            .assertValueAt(0, GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Error(errorMessage, bookFromStorage))
    }

    @Test
    fun `WHEN get book returns error AND local storage is null THEN error state is returned with no book`() {
        every { repository.getBook(any()) } returns Observable.error(Throwable())
        every { repository.getBookFromLocalStorage(any()) } returns null
        every { railwayChildrenWordsUseCase.invoke() } returns Observable.just(
            GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Error(errorMessage, null))

        railwayChildrenWordsUseCase.invoke().test()
            .assertValueAt(0, GetRailwayChildrenWordsUseCase.RailwayChildrenDataState.Error(errorMessage, null))
    }

    companion object {
        const val book = "This is a fake book"
        const val errorMessage = "An Error"
        val listOfWords = formatWithPrimeNumberCheck(getWordsAndCount(book))
        val bookFromStorage = formatWithPrimeNumberCheck(getWordsAndCount(book))
    }
}