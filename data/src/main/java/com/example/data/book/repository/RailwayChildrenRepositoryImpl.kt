package com.example.data.book.repository

import com.example.data.remote.BooksApi
import com.example.domain.book.repository.RailwayChildrenRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

//TODO: Extract to a common
const val RAILWAY_CHILDREN_LINK = "Railway-Children-by-E-Nesbit.txt"

class RailwayChildrenRepositoryImpl (private val booksApi: BooksApi) : RailwayChildrenRepository {
    override fun getBook(): Observable<String> {
        return booksApi.getBook(RAILWAY_CHILDREN_LINK)
            .map { removeAllSpecialCharacters(it) }
            .toObservable()
            .subscribeOn(Schedulers.io())
    }

    //TODO: Lol at this string Wewantedtotellyouonlywethoughtitwouldbestalenews
    private fun removeAllSpecialCharacters(book: String) : String {
        val regex = Regex("[^A-Za-z0-9]")
        return book.replace(regex, " ")
    }
}