package com.onblur.tmdbex.data.remote.api

import com.onblur.tmdbex.data.remote.model.PopularResponse
import com.onblur.tmdbex.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = "ko",
        @Query("page") page: Int,
        @Query("region") reg: String? = null
    ): Call<PopularResponse>
}