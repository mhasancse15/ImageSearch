package com.mahmudul.imagesearch.data.repository

import com.mahmudul.imagesearch.common.Resource
import com.mahmudul.imagesearch.domain.repository.ImageSearchRepository
import com.mahmudul.imagesearch.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.flow

class ImageSearchRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ImageSearchRepository {

    override fun queryImage(
        query: String,
        apiKey: String,
        imageType: String
    ) = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.queryImage(query, apiKey, imageType)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

}