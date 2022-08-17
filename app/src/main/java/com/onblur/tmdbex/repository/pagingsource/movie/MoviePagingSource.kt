package com.onblur.tmdbex.repository.pagingsource.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onblur.tmdbex.data.remote.api.MovieService
import com.onblur.tmdbex.data.remote.model.MovieData
import com.onblur.tmdbex.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class MoviePagingSource : PagingSource<Int, MovieData>() {
    private val movieRetrofit: Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val movieService: MovieService = movieRetrofit.create(MovieService::class.java)

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}