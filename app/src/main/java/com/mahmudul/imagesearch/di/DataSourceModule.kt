package com.mahmudul.imagesearch.di

import com.mahmudul.imagesearch.data.source.ImageSearchService
import com.mahmudul.imagesearch.data.source.RemoteDateSourceImpl
import com.mahmudul.imagesearch.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDateSource(remoteService: ImageSearchService): RemoteDataSource =
        RemoteDateSourceImpl(remoteService)
}