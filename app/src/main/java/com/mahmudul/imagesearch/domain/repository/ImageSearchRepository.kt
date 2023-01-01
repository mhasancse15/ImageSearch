package com.mahmudul.imagesearch.domain.repository

import com.mahmudul.imagesearch.common.Resource
import com.mahmudul.imagesearch.data.model.PixabayResponse
import kotlinx.coroutines.flow.Flow

interface ImageSearchRepository {
    fun queryImage(query: String, apiKey: String, imageType: String): Flow<Resource<PixabayResponse>>
}