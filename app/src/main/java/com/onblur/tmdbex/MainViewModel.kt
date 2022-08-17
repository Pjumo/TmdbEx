package com.onblur.tmdbex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.onblur.tmdbex.repository.MovieRepository

class MainViewModel : ViewModel() {
    val moviePopularFlow = MovieRepository.getMoviePopular().cachedIn(viewModelScope)
}