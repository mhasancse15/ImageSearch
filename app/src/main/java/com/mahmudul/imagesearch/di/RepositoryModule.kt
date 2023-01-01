package com.mahmudul.imagesearch.di

import com.mahmudul.imagesearch.data.repository.ImageSearchRepositoryImpl
import com.mahmudul.imagesearch.domain.repository.ImageSearchRepository
import com.mahmudul.imagesearch.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDallERepository(
        remoteDataSource: RemoteDataSource
    ): ImageSearchRepository =
        ImageSearchRepositoryImpl(remoteDataSource)
}