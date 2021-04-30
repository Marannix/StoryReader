package com.example.domain.book.repository

import io.reactivex.Observable

interface RailwayChildrenRepository {
    fun getBook() : Observable<String>
}