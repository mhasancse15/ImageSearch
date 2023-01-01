package com.mahmudul.imagesearch.data.source

import com.mahmudul.imagesearch.common.Constants.QUERY_IMAGE_PATH
import com.mahmudul.imagesearch.data.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageSearchService{

    @GET(QUERY_IMAGE_PATH)
    suspend fun getQueryImages(
        @Query("q") query:String,
        @Query("key") apiKey:String,
        @Query("image_type") imageType:String
    ): PixabayResponse

}