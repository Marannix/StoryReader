package com.example.data.book.usecase

import com.example.data.book.formatWithPrimeNumberCheck
import com.example.data.book.getWordsAndCount
import com.example.domain.book.repository.RailwayChildrenRepository
import com.example.domain.common.BookDetails
import io.reactivex.Observable
import javax.inject.Inject

class GetRailwayChildrenWordsUseCase @Inject constructor(private val repository: RailwayChildrenRepository) {
    operator fun invoke() : Observable<RailwayChildrenDataState> {
        return repository.getBook()
            .map <RailwayChildrenDataState> { book ->
                RailwayChildrenDataState.Success(formatWithPrimeNumberCheck(getWordsAndCount(book)))
            }.onErrorReturn { error ->
                RailwayChildrenDataState.Error(error.message)
            }
    }

    sealed class RailwayChildrenDataState {
        data class Success(val listOfWords: HashMap<String, BookDetails>) : RailwayChildrenDataState()
        data class Error(val message: String?) : RailwayChildrenDataState()
    }
}