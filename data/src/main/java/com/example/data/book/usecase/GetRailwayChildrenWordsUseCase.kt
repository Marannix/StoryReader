package com.example.data.book.usecase

import com.example.domain.book.repository.RailwayChildrenRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRailwayChildrenWordsUseCase @Inject constructor(private val repository: RailwayChildrenRepository) {
    operator fun invoke() : Observable<RailwayChildrenDataState> {
        return repository.getBook()
            .map <RailwayChildrenDataState> { book ->
                RailwayChildrenDataState.Success(getWordsAndCount(book))
            }.onErrorReturn { error ->
                RailwayChildrenDataState.Error(error.message)
            }
    }

    private fun getWordsAndCount(book: String): HashMap<String, Int> {
        val hashMap = hashMapOf<String, Int>()

        //TODO: Remove special characters
        book.split(" ").forEach { word ->
            if (hashMap[word] == null) {
                hashMap[word] = 1
            } else {
                val count = hashMap[word]!!
                hashMap[word] = count + 1
            }
        }

        return hashMap
    }

    sealed class RailwayChildrenDataState {
        data class Success(val listOfWords: HashMap<String, Int>) : RailwayChildrenDataState()
        data class Error(val message: String?) : RailwayChildrenDataState()
    }
}