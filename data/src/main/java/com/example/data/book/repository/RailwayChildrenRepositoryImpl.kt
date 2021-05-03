package com.example.data.book.repository

import com.example.data.remote.BooksApi
import com.example.data.utils.EMPTY_SPACE
import com.example.data.utils.FILTER_SPECIAL_CHARACTERS
import com.example.data.utils.FileDataProvider
import com.example.domain.book.repository.RailwayChildrenRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RailwayChildrenRepositoryImpl @Inject constructor(private val booksApi: BooksApi,
                                                        private val fileDataProvider: FileDataProvider) : RailwayChildrenRepository {
    override fun getBook(book: String): Observable<String> {
        return booksApi.getBook(book)
            .map { removeAllSpecialCharacters(it) }
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    override fun getBookFromLocalStorage(book: String) : String? {
        return fileDataProvider.loadFileFromAsset(book)?.let { removeAllSpecialCharacters(it) }
    }

    //TODO: Lol at this string Wewantedtotellyouonlywethoughtitwouldbestalenews
    private fun removeAllSpecialCharacters(book: String) : String {
        return book.replace(FILTER_SPECIAL_CHARACTERS, EMPTY_SPACE)
    }
}