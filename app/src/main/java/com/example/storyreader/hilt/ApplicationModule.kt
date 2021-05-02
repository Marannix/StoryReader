package com.example.storyreader.hilt

import android.content.Context
import com.example.data.utils.FileDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideApplication(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideFileDataProvider(context: Context): FileDataProvider {
        return FileDataProvider(context)
    }
}