package com.onblur.tmdbex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.onblur.tmdbex.adapter.PopularMovieAdapter
import com.onblur.tmdbex.data.remote.api.MovieService
import com.onblur.tmdbex.data.remote.model.PopularResponse
import com.onblur.tmdbex.databinding.FragmentMainBinding
import com.onblur.tmdbex.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(layoutInflater, container, false).apply {
        val adapter = PopularMovieAdapter()
        mainPopularContents.adapter = adapter

        lifecycleScope.launch {
            viewModel.moviePopularFlow.collectLatest {
                adapter.submitData(it)
            }
        }
    }.also {
        binding = it
    }.root
}