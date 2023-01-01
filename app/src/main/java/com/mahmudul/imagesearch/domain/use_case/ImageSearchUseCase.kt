package com.mahmudul.imagesearch.domain.use_case

import com.mahmudul.imagesearch.domain.repository.ImageSearchRepository
import javax.inject.Inject

class ImageSearchUseCase @Inject constructor(private val repository: ImageSearchRepository) {
    operator fun invoke(query: String, apiKey: String, imageType: String) = repository.queryImage(query, apiKey, imageType)
}