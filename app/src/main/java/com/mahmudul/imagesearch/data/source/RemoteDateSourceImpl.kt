package com.mahmudul.imagesearch.data.source

import com.mahmudul.imagesearch.data.model.PixabayResponse
import com.mahmudul.imagesearch.domain.source.RemoteDataSource


class RemoteDateSourceImpl(private val remoteService: ImageSearchService) : RemoteDataSource {

    override suspend fun queryImage(
        query: String,
        apiKey: String,
        imageType: String
    ): PixabayResponse {
        return remoteService.getQueryImages(query, apiKey, imageType)
    }
}