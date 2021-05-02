package com.example.domain.book.repository

import io.reactivex.Observable

interface RailwayChildrenRepository {
    fun getBook(book: String) : Observable<String>
    fun getBookFromLocalStorage(book: String) : String?
}