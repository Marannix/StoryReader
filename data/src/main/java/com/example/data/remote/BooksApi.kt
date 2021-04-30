package com.example.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface BooksApi {
    @Streaming
    @GET("/download/text/{book}")
    fun getBook( @Path("book") book : String ) : Single<String>
}