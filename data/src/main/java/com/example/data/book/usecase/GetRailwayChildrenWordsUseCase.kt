package com.example.data.book.usecase

import com.example.data.book.formatWithPrimeNumberCheck
import com.example.data.book.getWordsAndCount
import com.example.domain.book.repository.RailwayChildrenRepository
import com.example.domain.common.BookDetails
import io.reactivex.Observable
import javax.inject.Inject

private const val RAILWAY_CHILDREN_BOOK = "TheRailwayChildrenBook.txt"

class GetRailwayChildrenWordsUseCase @Inject constructor(private val repository: RailwayChildrenRepository) {
    operator fun invoke() : Observable<RailwayChildrenDataState> {
        return repository.getBook(RAILWAY_CHILDREN_BOOK)
            .map <RailwayChildrenDataState> { book ->
                RailwayChildrenDataState.Success(getFormattedWords(book))
            }.onErrorReturn { error ->
                repository.getBookFromLocalStorage(RAILWAY_CHILDREN_BOOK).let { book ->
                    if (book == null) {
                        RailwayChildrenDataState.Error(error.message, null)
                    } else {
                        RailwayChildrenDataState.Error(error.message, getFormattedWords(book))
                    }
                }
            }
    }

    private fun getFormattedWords(books: String): HashMap<String, BookDetails> {
        return formatWithPrimeNumberCheck(getWordsAndCount(books))
    }

    sealed class RailwayChildrenDataState {
        data class Success(val listOfWords: HashMap<String, BookDetails>) : RailwayChildrenDataState()
        data class Error(val message: String?, val bookFromLocalStorage: HashMap<String, BookDetails>?) : RailwayChildrenDataState()
    }
}