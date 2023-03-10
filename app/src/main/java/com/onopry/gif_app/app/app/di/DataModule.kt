package com.onopry.gif_app.app.app.di

import com.onopry.gif_app.app.data.datasource.GIFApiDataSource
import com.onopry.gif_app.app.data.network.GifService
import com.onopry.gif_app.app.data.datasource.DataSource
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
    fun provideDataSource(api: GifService): DataSource =
        GIFApiDataSource(api)

    @Provides
    @Singleton
    fun provideRepository(dataSource: DataSource, api: GifService): Repository =
        GIFRepository(dataSource, api)


}