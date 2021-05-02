package com.example.storyreader.hilt

import com.example.data.book.repository.RailwayChildrenRepositoryImpl
import com.example.data.remote.BooksApi
import com.example.data.utils.FileDataProvider
import com.example.domain.book.repository.RailwayChildrenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideRailwayChildrenRepository(booksApi: BooksApi, fileDataProvider: FileDataProvider): RailwayChildrenRepository {
        return RailwayChildrenRepositoryImpl(booksApi, fileDataProvider)
    }
}