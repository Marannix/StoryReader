package com.example.data.book.usecase

import com.example.domain.book.repository.RailwayChildrenRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRailwayChildrenWordsUseCase @Inject constructor(private val repository: RailwayChildrenRepository) {
    operator fun invoke() : Observable<RailwayChildrenDataState> {
        return repository.getBook()
            .map <RailwayChildrenDataState> {
                RailwayChildrenDataState.Success(it)
            }.onErrorReturn { error ->
                RailwayChildrenDataState.Error(error.message)
            }
    }

    sealed class RailwayChildrenDataState {
        data class Success(val listOfWords: String) : RailwayChildrenDataState()
        data class Error(val message: String?) : RailwayChildrenDataState()
    }
}