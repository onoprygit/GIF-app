package com.onopry.gif_app.app.di

import com.onopry.gif_app.app.data.datasource.GIFApiDataSource
import com.onopry.gif_app.app.data.datasource.GifService
import com.onopry.gif_app.app.data.datasource.RemoteDataSource
import com.onopry.gif_app.app.data.repository.GIFRepository
import com.onopry.gif_app.app.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDataSource(api: GifService): RemoteDataSource =
        GIFApiDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(dataSource: RemoteDataSource): Repository =
        GIFRepository(dataSource)


}