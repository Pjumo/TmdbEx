package com.onblur.tmdbex.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("poster_path")
    var poster: String,
    @SerializedName("adult")
    var isAdult: Boolean,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("release_date")
    var data: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originTitle: String,
    @SerializedName("original_language")
    var language: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("backdrop_path")
    var backdrop: String?,
    @SerializedName("popularity")
    var popularity: Number,
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("video")
    var isVideo: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Number
)

data class PopularResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var movies: Array<MovieData>,
    @SerializedName("total_results")
    var totalRes: Int,
    @SerializedName("total_pages")
    var totalPage: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PopularResponse

        if (page != other.page) return false
        if (!movies.contentEquals(other.movies)) return false
        if (totalRes != other.totalRes) return false
        if (totalPage != other.totalPage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = page
        result = 31 * result + movies.contentHashCode()
        result = 31 * result + totalRes
        result = 31 * result + totalPage
        return result
    }
}