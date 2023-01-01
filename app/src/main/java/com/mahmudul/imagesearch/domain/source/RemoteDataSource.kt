package com.mahmudul.imagesearch.domain.source

import com.mahmudul.imagesearch.data.model.PixabayResponse

interface RemoteDataSource {
    suspend fun queryImage(query: String, apiKey: String, imageType: String): PixabayResponse
}