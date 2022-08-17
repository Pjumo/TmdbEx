package com.onblur.tmdbex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onblur.tmdbex.data.remote.model.MovieData
import com.onblur.tmdbex.repository.pagingsource.movie.MoviePopularPagingSource
import com.onblur.tmdbex.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

object MovieRepository {
    fun getMoviePopular(): Flow<PagingData<MovieData>> = Pager(
        PagingConfig(pageSize = PAGE_SIZE)
    ){
        MoviePopularPagingSource()
    }.flow
}