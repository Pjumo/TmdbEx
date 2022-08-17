package com.onblur.tmdbex.repository.pagingsource.movie

import androidx.paging.PagingState
import com.onblur.tmdbex.data.remote.model.MovieData
import com.onblur.tmdbex.data.remote.model.PopularResponse
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MoviePopularPagingSource : MoviePagingSource() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return try {
            val next = params.key ?: 0
            val response = getPopularByPage(next + 1)

            LoadResult.Page(
                data = response,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getPopularByPage(page: Int): List<MovieData> =
        callbackFlow {
            val callback = object : Callback<PopularResponse> {
                override fun onResponse(
                    call: Call<PopularResponse>,
                    response: Response<PopularResponse>
                ) {
                    if (response.isSuccessful) {
                        val result: PopularResponse? = response.body()
                        Timber.d("$page - onResponse 성공: $result")
                        if (result != null) {
                            trySend(result.movies.toList())
                        } else cancel()
                    } else {
                        Timber.d("$page - onResponse 실패")
                        cancel()
                    }
                }

                override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                    Timber.d("$page - onFailure 에러: ${t.message}")
                    cancel()
                }
            }
            movieService.getPopularMovie(page = page).enqueue(callback)
            awaitClose()
        }.first()
}