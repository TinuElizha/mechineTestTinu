package com.example.mechinetest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mechinetest.network.MovieDetailsResponse
import com.example.mechinetest.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel(){

    private val _movieDetails: MutableLiveData<MovieDetailsResponse> = MutableLiveData()
    val movieDetails: LiveData<MovieDetailsResponse>
        get() = _movieDetails

    fun  getMovieDetails(id:Long) = viewModelScope.launch {
        _movieDetails.value = MovieRepository.getMovieDetails(id)
    }
}